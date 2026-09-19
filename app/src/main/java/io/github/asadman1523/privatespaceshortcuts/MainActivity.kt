package io.github.asadman1523.privatespaceshortcuts

import android.app.Activity
import android.os.Bundle
import android.os.Process
import android.os.UserManager
import android.widget.LinearLayout
import android.widget.TextView

/** Also serves as a harmless per-profile fixture during device acceptance testing. */
class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val zh = resources.configuration.locales[0].language == "zh"
        val serial = getSystemService(UserManager::class.java).getSerialNumberForUser(Process.myUserHandle())
        val profile = if (serial == 0L) {
            if (zh) "主空間測試頁面" else "Main profile test screen"
        } else {
            if (zh) "獨立設定檔測試頁面" else "Separate profile test screen"
        }
        val text = if (zh) {
            "私人空間捷徑\n${BuildConfig.VERSION_NAME}\n\n$profile\n\n在 LSPosed 啟用此模組，作用域只勾選 Pixel Launcher，然後重新啟動桌面。\n\n解鎖私人空間，長按 App → 新增至主畫面。\n\n相容版本：Android 17 / Pixel Launcher 907。\n這是測試版本；啟用狀態與相容性原因請查看 LSPosed 的 [PPSS] 模組日誌。"
        } else {
            "Private Space Shortcuts\n${BuildConfig.VERSION_NAME}\n\n$profile\n\nEnable this module in LSPosed, scope it only to Pixel Launcher, then restart the launcher.\n\nUnlock Private Space, long-press an app, and choose Add to Home screen.\n\nTarget: Android 17 / Pixel Launcher 907.\nThis is an alpha. Check [PPSS] entries in LSPosed logs for activation and compatibility diagnostics."
        }
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            val padding = (24 * resources.displayMetrics.density).toInt()
            setPadding(padding, padding * 2, padding, padding)
            addView(TextView(context).apply { this.text = text; textSize = 18f })
        }
        setContentView(layout)
    }
}
