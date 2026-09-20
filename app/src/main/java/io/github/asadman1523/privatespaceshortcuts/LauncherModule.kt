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
                        if (Build.VERSION.SDK_INT < 35) {
                            log("Unsupported OS (API < 35); no launcher hooks installed")
                            return
                        }
                        
                        val info = app.packageManager.getPackageInfo(Native907.PACKAGE, 0)
                        log("Targeting launcher version ${info.longVersionCode}")
                        
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
                            log("Launcher APK fingerprint differs from 907 reference ($sha), attempting to hook anyway...")
                        }
                        
                        Native907(app, param.classLoader).install()
                        log("Enabled native adapter for Launcher")
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
