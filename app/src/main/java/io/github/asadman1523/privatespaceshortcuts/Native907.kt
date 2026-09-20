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
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import java.lang.ref.WeakReference
import java.lang.reflect.Method
import java.util.Collections
import java.util.WeakHashMap
import java.util.function.Consumer

/** These entrypoints are verified against the device APK fingerprint, not AOSP guesses. */
class Native907(private val app: Application, private val loader: ClassLoader) {
    private val users = app.getSystemService(UserManager::class.java)
    private val apps = app.getSystemService(LauncherApps::class.java)
    private val main = Handler(Looper.getMainLooper())
    private val gate = LaunchGate(SystemClock::elapsedRealtime)
    private val adding = mutableSetOf<TargetKey>()
    private val addActions = Collections.newSetFromMap(WeakHashMap<Any, Boolean>())
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
        val populate = method(cls("popup.PopupContainerWithArrow"), "populateAndShowRows")
        val shortcutClick = method(cls("popup.SystemShortcut\$Install"), "onClick", 1)
        val click = method(cls("touch.ItemClickHandler\$\$ExternalSyntheticLambda0"), "onClick", 1)
        val addItems = method(cls("model.BgDataModel"), "addItems", 3)
        val updateItems = method(cls("model.BgDataModel"), "updateItems", 2)
        val newIcon = method(cls("model.data.ItemInfoWithIcon"), "newIcon", 2)
        val beginDrag = method(cls("Workspace"), "beginDragShared", 6)
        val acceptWorkspaceDrop = method(cls("Workspace"), "acceptDrop", 1)
        val acceptFolderDrop = method(cls("folder.Folder"), "acceptDrop", 1)
        val newIntent = method(launcherClass, "onNewIntent", 1)
        // Validate the native factory and all required constructors before changing behavior.
        XposedHelpers.getStaticObjectField(shortcutClass, "ADD_TO_HOME_SCREEN")
        workspaceClass.getConstructor(appInfoClass)
        workspaceClass.getConstructor(workspaceClass)
        method(cls("accessibility.LauncherAccessibilityDelegate"), "addToWorkspace", 3)
        try {
            hook(populate, before = { p -> addMenuEntry(p) })
            hook(shortcutClick, before = { p ->
                val item = field(p.thisObject, "mItemInfo")
                // R8 merged Add, Remove and Install into this class. Only our exact
                // factory-created action is an Add; desktop Remove must run natively.
                if (p.thisObject in addActions && ownedKey(item) != null) {
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
            hook(beginDrag, before = { p ->
                val item = p.args[3]
                // Regular All Apps item or prediction row item (not yet owned).
                if (appInfoClass.isInstance(item) ||
                    (workspaceClass.isInstance(item) && !isOwned(item))) {
                    // Copy only the drag payload. The source list keeps its original item.
                    ownedCopy(item)?.let {
                        p.args[3] = it
                        LauncherModule.log("Private app drag uses owned workspace payload")
                    }
                }
            })

            hook(newIntent, before = { p ->
                val intent = p.args[0] as Intent
                if (intent.action == Intent.ACTION_MAIN && intent.hasCategory(Intent.CATEGORY_HOME)) {
                    gate.interacted()
                }
            })
            hook(Activity::class.java.getDeclaredMethod("dispatchTouchEvent", MotionEvent::class.java), before = { p ->
                if (launcherClass.isInstance(p.thisObject) &&
                    (p.args[0] as MotionEvent).actionMasked == MotionEvent.ACTION_DOWN) {
                    gate.interacted()
                }
            })
            app.registerActivityLifecycleCallbacks(lifecycle)
            val filter = IntentFilter().apply {
                addAction(Intent.ACTION_PROFILE_AVAILABLE)
                addAction(Intent.ACTION_PROFILE_ACCESSIBLE)
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
        val copy = ownedCopy(item) ?: return
        val key = ownedKey(copy) ?: return
        val target = field(p.thisObject, "mActivityContext") ?: return
        val view = field(p.thisObject, "originalView") as? View ?: return
        val factory = XposedHelpers.getStaticObjectField(shortcutClass, "ADD_TO_HOME_SCREEN")
        val native = call(factory, "getShortcut", target, copy, view) ?: return
        @Suppress("UNCHECKED_CAST")
        // Android 15 & 16: populateAndShowRows(BubbleTextView, int, List) -> list is args[2]
        // Android 17: populateAndShowRows(int, List) -> list is args[1]
        val listIndex = if (p.args.size == 3) 2 else 1
        val rows = (p.args[listIndex] as List<Any>).toMutableList()
        if (rows.none { it in addActions && ownedKey(field(it, "mItemInfo")) == key }) {
            addActions.add(native)
            rows.add(native)
            p.args[listIndex] = rows
        }
    }

    private fun ownedCopy(item: Any): Any? {
        val copy: Any
        if (appInfoClass.isInstance(item)) {
            // Regular All Apps item.
            copy = workspaceClass.getConstructor(appInfoClass).newInstance(item)
        } else if (workspaceClass.isInstance(item)) {
            // Prediction row item (already WorkspaceItemInfo, container -103).
            val existing = field(item, "intent") as? Intent ?: return null
            if (existing.getBooleanExtra(OWNER, false)) return null // Already owned.
            copy = workspaceClass.getConstructor(workspaceClass).newInstance(item)
        } else return null
        val user = field(copy, "user") as? UserHandle ?: return null
        if (!privateProfile(user)) return null
        val serial = users.getSerialNumberForUser(user)
        if (call(copy, "getTargetComponent") !is ComponentName || serial < 0) return null
        val intent = Intent(field(copy, "intent") as Intent)
            .putExtra(OWNER, true).putExtra(SERIAL, serial)
        XposedHelpers.setObjectField(copy, "intent", intent)
        XposedHelpers.setIntField(copy, "container", -104) // Native factory accepts All Apps items.
        prepareOwnedItem(copy)
        return copy
    }


    private fun addToHome(shortcut: Any, item: Any?) {
        val key = ownedKey(item) ?: return
        val launcher = field(shortcut, "mTarget") as? Activity ?: return
        if (!adding.add(key)) {
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
            main.removeCallbacks(readinessPoll)
            main.postDelayed(readinessPoll, 100)
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

    // PROFILE_AVAILABLE can precede credential-encrypted user readiness, and USER_UNLOCKED
    // for the private user is not guaranteed to reach this parent-user receiver. Recheck
    // while this bounded request is alive; broadcasts are a hint, not the only trigger.
    private val readinessPoll = object : Runnable {
        override fun run() {
            guarded { checkReady() }
            if (gate.current() != null) main.postDelayed(this, if (foreground) 100 else 500)
        }
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
                val unlocked = users.isUserUnlocked(user)
                gate.returned(quiet, unlocked)?.let(::start)
                LauncherModule.log(if (quiet) "Authentication cancelled; request cleared"
                    else "Returned from system authentication; profile ready=$unlocked")
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
    private fun method(type: Class<*>, name: String, count: Int? = null): Method =
        type.declaredMethods.single { it.name == name && (count == null || it.parameterCount == count) }
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
