package io.github.asadman1523.privatespaceshortcuts

import android.app.Activity
import android.app.Application
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.LauncherApps
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.os.UserHandle
import android.os.UserManager
import android.util.SparseArray
import android.view.View
import android.widget.Toast
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import java.lang.ref.WeakReference
import java.lang.reflect.Method
import java.util.function.Consumer

/** These entrypoints are verified against the device APK fingerprint, not AOSP guesses. */
class Native907(private val app: Application, private val loader: ClassLoader) {
    private val users = app.getSystemService(UserManager::class.java)
    private val apps = app.getSystemService(LauncherApps::class.java)
    private val main = Handler(Looper.getMainLooper())
    private val gate = LaunchGate(SystemClock::elapsedRealtime)
    private val adding = mutableSetOf<TargetKey>()
    private var activity = WeakReference<Activity>(null)
    private var foreground = false
    private val hooks = mutableListOf<XC_MethodHook.Unhook>()
    private lateinit var workspaceClass: Class<*>
    private lateinit var appInfoClass: Class<*>
    private lateinit var shortcutClass: Class<*>
    private lateinit var launcherClass: Class<*>

    fun install() {
        workspaceClass = cls("model.data.WorkspaceItemInfo")
        appInfoClass = cls("model.data.AppInfo")
        shortcutClass = cls("popup.SystemShortcut")
        launcherClass = cls("Launcher")
        val populate = method(cls("popup.PopupContainerWithArrow"), "populateAndShowRows", 2)
        val shortcutClick = method(cls("popup.SystemShortcut\$Install"), "onClick", 1)
        val click = method(cls("touch.ItemClickHandler\$\$ExternalSyntheticLambda0"), "onClick", 1)
        val addItems = method(cls("model.BgDataModel"), "addItems", 3)
        val updateItems = method(cls("model.BgDataModel"), "updateItems", 2)
        val newIcon = method(cls("model.data.ItemInfoWithIcon"), "newIcon", 2)
        // Validate the native factory and all required constructors before changing behavior.
        XposedHelpers.getStaticObjectField(shortcutClass, "ADD_TO_HOME_SCREEN")
        workspaceClass.getConstructor(appInfoClass)
        workspaceClass.getConstructor(workspaceClass)
        method(cls("accessibility.LauncherAccessibilityDelegate"), "addToWorkspace", 3)
        try {
            hook(populate, before = { p -> addMenuEntry(p) })
            hook(shortcutClick, before = { p ->
                val item = field(p.thisObject, "mItemInfo")
                if (ownedKey(item) != null) {
                    p.result = null
                    addToHome(p.thisObject, item)
                }
            })
            hook(click, before = { p ->
                val view = p.args[0] as View
                if (isOwned(view.tag)) {
                    p.result = null
                    val key = ownedKey(view.tag)
                    if (key == null) unavailable()
                    else if (view.windowToken != null) launch(key)
                }
            })
            hook(addItems, before = { p -> (p.args[1] as List<*>).forEach(::prepareOwnedItem) })
            hook(updateItems, before = { p -> (p.args[0] as List<*>).forEach(::prepareOwnedItem) })
            hook(newIcon, before = { p -> prepareOwnedItem(p.thisObject) })
            app.registerActivityLifecycleCallbacks(lifecycle)
            val filter = IntentFilter().apply {
                addAction(Intent.ACTION_PROFILE_AVAILABLE)
                addAction(Intent.ACTION_USER_UNLOCKED)
            }
            app.registerReceiver(profileReceiver, filter, Context.RECEIVER_NOT_EXPORTED)
        } catch (error: Throwable) {
            hooks.forEach { it.unhook() }
            hooks.clear()
            throw error
        }
    }

    private fun addMenuEntry(p: XC_MethodHook.MethodHookParam) {
        val item = field(p.thisObject, "itemInfo") ?: return
        if (!appInfoClass.isInstance(item)) return
        val user = field(item, "user") as? UserHandle ?: return
        if (!privateProfile(user)) return
        val serial = users.getSerialNumberForUser(user)
        val component = call(item, "getTargetComponent") as? ComponentName ?: return
        if (serial < 0) return
        val copy = workspaceClass.getConstructor(appInfoClass).newInstance(item)
        val intent = Intent(field(copy, "intent") as Intent)
            .putExtra(OWNER, true).putExtra(SERIAL, serial)
        XposedHelpers.setObjectField(copy, "intent", intent)
        XposedHelpers.setIntField(copy, "container", -104) // Native factory accepts All Apps items.
        prepareOwnedItem(copy)
        val target = field(p.thisObject, "mActivityContext") ?: return
        val view = field(p.thisObject, "originalView") as? View ?: return
        val factory = XposedHelpers.getStaticObjectField(shortcutClass, "ADD_TO_HOME_SCREEN")
        val native = call(factory, "getShortcut", target, copy, view) ?: return
        @Suppress("UNCHECKED_CAST")
        val rows = (p.args[1] as List<Any>).toMutableList()
        if (rows.none { ownedKey(field(it, "mItemInfo")) == TargetKey(serial, component.flattenToString()) }) {
            rows.add(native)
            p.args[1] = rows
        }
    }

    private fun addToHome(shortcut: Any, item: Any?) {
        val key = ownedKey(item) ?: return
        val launcher = field(shortcut, "mTarget") as? Activity ?: return
        val model = field(field(launcher, "mModel"), "mBgDataModel") ?: return
        val holder = field(model, "itemsIdMap") ?: return
        @Suppress("UNCHECKED_CAST")
        val map = field(holder, "itemsIdMap") as SparseArray<Any>
        val exists = synchronized(model) {
            (0 until map.size()).any { i ->
                val existing = map.valueAt(i)
                val user = field(existing, "user") as? UserHandle
                user != null && users.getSerialNumberForUser(user) == key.profileSerial &&
                    (call(existing, "getTargetComponent") as? ComponentName)?.flattenToString() == key.component
            }
        }
        if (exists || !adding.add(key)) {
            LauncherModule.log("Duplicate shortcut rejected")
            toast("Already on the Home screen", "已經加入主畫面")
            return
        }
        call(shortcut, "dismissTaskMenuView")
        try {
            val delegate = call(launcher, "getAccessibilityDelegate")!!
            call(delegate, "addToWorkspace", item, false, Consumer<Boolean> { success ->
                main.post {
                    adding.remove(key)
                    if (!success) toast("No space on the Home screen", "主畫面沒有可用空間")
                    else LauncherModule.log("Native workspace shortcut added")
                }
            })
        } catch (error: Throwable) {
            adding.remove(key)
            throw error
        }
    }

    private fun prepareOwnedItem(item: Any?) {
        if (ownedKey(item) == null) return
        val flags = XposedHelpers.getIntField(item, "runtimeStatusFlags")
        // Keep owned icons movable and full-color even while the profile is quiet.
        // Authentication reads UserManager state, independently of these presentation flags.
        XposedHelpers.setIntField(item, "runtimeStatusFlags", flags and (0x2000 or 0x8).inv())
    }

    private fun isOwned(item: Any?): Boolean = item != null && workspaceClass.isInstance(item) &&
        (field(item, "intent") as? Intent)?.getBooleanExtra(OWNER, false) == true

    private fun ownedKey(item: Any?): TargetKey? {
        if (item == null || !workspaceClass.isInstance(item)) return null
        val intent = field(item, "intent") as? Intent ?: return null
        if (!intent.getBooleanExtra(OWNER, false)) return null
        val component = intent.component ?: return null
        val serial = intent.getLongExtra(SERIAL, -1)
        val user = field(item, "user") as? UserHandle ?: return null
        if (serial < 0 || users.getSerialNumberForUser(user) != serial || !privateProfile(user)) return null
        return TargetKey(serial, component.flattenToString())
    }

    private fun privateProfile(user: UserHandle): Boolean =
        apps.getLauncherUserInfo(user)?.userType == UserManager.USER_TYPE_PROFILE_PRIVATE

    private fun launch(key: TargetKey) {
        if (gate.current() != null) return
        val user = resolveUser(key) ?: return unavailable()
        if (users.isQuietModeEnabled(user) || !users.isUserUnlocked(user)) {
            if (!gate.begin(key)) return
            LauncherModule.log("Awaiting private profile authentication")
            // Expire by the current request's own timestamp; an old timer cannot cancel a new tap.
            main.postDelayed({ gate.current() }, 120_000)
            try {
                // The foreground default launcher's system API presents the real credential UI.
                val accepted = users.requestQuietModeEnabled(false, user)
                if (accepted) gate.approvedWithoutPrompt(
                    !users.isQuietModeEnabled(user) && users.isUserUnlocked(user))?.let(::start)
            } catch (error: Throwable) {
                gate.cancel()
                throw error
            }
        } else start(key)
    }

    private fun resolveUser(key: TargetKey): UserHandle? =
        users.getUserForSerialNumber(key.profileSerial)?.takeIf(::privateProfile)

    private fun start(key: TargetKey) {
        val user = resolveUser(key) ?: return unavailable()
        if (users.isQuietModeEnabled(user) || !users.isUserUnlocked(user)) return
        val original = ComponentName.unflattenFromString(key.component) ?: return unavailable()
        val activities = apps.getActivityList(original.packageName, user)
        val component = activities.firstOrNull { it.componentName == original }?.componentName
            ?: activities.singleOrNull()?.componentName ?: return unavailable()
        apps.startMainActivity(component, user, null, null)
        LauncherModule.log("Opened shortcut in its original private profile")
    }

    private fun unavailable() = toast("Private app is no longer available", "原本的私人空間 App 已無法使用")
    private fun toast(en: String, zh: String) {
        Toast.makeText(app, if (app.resources.configuration.locales[0].language == "zh") zh else en,
            Toast.LENGTH_SHORT).show()
    }
    private fun checkReady() {
        if (!foreground) return
        val key = gate.current() ?: return
        val user = resolveUser(key) ?: run { gate.cancel(); return }
        gate.ready(key, users.isQuietModeEnabled(user), users.isUserUnlocked(user))?.let(::start)
    }

    private val profileReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) = guarded { checkReady() }
    }

    private val lifecycle = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityResumed(a: Activity) {
            if (!launcherClass.isInstance(a)) return
            activity = WeakReference(a)
            foreground = true
            guarded {
                val key = gate.current() ?: return@guarded
                val user = resolveUser(key) ?: run { gate.cancel(); return@guarded }
                val quiet = users.isQuietModeEnabled(user)
                gate.returned(quiet, users.isUserUnlocked(user))?.let(::start)
                LauncherModule.log(if (quiet) "Authentication cancelled; request cleared"
                    else "Returned from system authentication")
            }
        }
        override fun onActivityPaused(a: Activity) {
            if (a === activity.get()) { foreground = false; gate.departed() }
        }
        override fun onActivityDestroyed(a: Activity) {
            if (a === activity.get()) { gate.cancel(); activity.clear(); foreground = false }
        }
        override fun onActivityCreated(a: Activity, state: Bundle?) = Unit
        override fun onActivityStarted(a: Activity) = Unit
        override fun onActivityStopped(a: Activity) = Unit
        override fun onActivitySaveInstanceState(a: Activity, state: Bundle) = Unit
    }

    private fun cls(relative: String): Class<*> = XposedHelpers.findClass("com.android.launcher3.$relative", loader)
    private fun method(type: Class<*>, name: String, count: Int): Method =
        type.declaredMethods.single { it.name == name && it.parameterCount == count }
    private fun field(target: Any?, name: String): Any? = target?.let { XposedHelpers.getObjectField(it, name) }
    private fun call(target: Any, name: String, vararg args: Any?): Any? = XposedHelpers.callMethod(target, name, *args)
    private fun guarded(action: () -> Unit) {
        try { action() } catch (error: Throwable) {
            gate.cancel()
            LauncherModule.log("Operation stopped: ${error.javaClass.simpleName}")
        }
    }
    private fun hook(method: Method, before: (XC_MethodHook.MethodHookParam) -> Unit) {
        hooks += XposedBridge.hookMethod(method, object : XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) = guarded { before(param) }
        })
    }
    companion object {
        const val PACKAGE = "com.google.android.apps.nexuslauncher"
        const val APK_SHA256 = "ef87b593f9131a6261691c6b6951d307610c6840c6b8a7e445a6e8026766644e"
        private const val OWNER = "io.github.asadman1523.privatespaceshortcuts.OWNED"
        private const val SERIAL = "io.github.asadman1523.privatespaceshortcuts.PROFILE_SERIAL"
    }
}
