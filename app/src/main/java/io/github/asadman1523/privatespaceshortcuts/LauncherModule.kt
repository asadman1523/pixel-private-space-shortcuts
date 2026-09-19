package io.github.asadman1523.privatespaceshortcuts

import android.app.Application
import android.content.Context
import android.os.Build
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage
import java.io.File
import java.security.MessageDigest

class LauncherModule : IXposedHookLoadPackage {
    override fun handleLoadPackage(param: XC_LoadPackage.LoadPackageParam) {
        if (param.packageName != Native907.PACKAGE || param.processName != Native907.PACKAGE) return
        XposedHelpers.findAndHookMethod(Application::class.java, "attach", Context::class.java,
            object : XC_MethodHook() {
                override fun afterHookedMethod(hook: MethodHookParam) {
                    val app = hook.thisObject as Application
                    try {
                        val info = app.packageManager.getPackageInfo(Native907.PACKAGE, 0)
                        if (Build.VERSION.SDK_INT != 37 || info.longVersionCode != 907L) {
                            log("Unsupported OS or launcher version; no launcher hooks installed")
                            return
                        }
                        val digest = MessageDigest.getInstance("SHA-256")
                        File(app.applicationInfo.sourceDir).inputStream().use { input ->
                            val buffer = ByteArray(65536)
                            while (true) {
                                val n = input.read(buffer)
                                if (n < 0) break
                                digest.update(buffer, 0, n)
                            }
                        }
                        val sha = digest.digest().joinToString("") { "%02x".format(it) }
                        if (sha != Native907.APK_SHA256) {
                            log("Unsupported launcher APK fingerprint; no launcher hooks installed")
                            return
                        }
                        Native907(app, param.classLoader).install()
                        log("Enabled native adapter for Launcher 907")
                    } catch (error: Throwable) {
                        // Installation validates all entrypoints before registering hooks.
                        log("Adapter initialization failed: ${error.javaClass.simpleName}")
                    }
                }
            })
    }

    companion object {
        fun log(message: String) = XposedBridge.log("[PPSS] $message")
    }
}
