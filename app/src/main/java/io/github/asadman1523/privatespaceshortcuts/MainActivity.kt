package io.github.asadman1523.privatespaceshortcuts

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import java.util.concurrent.TimeUnit

/** Local information page and harmless per-profile acceptance-test fixture. */
class MainActivity : Activity() {
    private val zh get() = resources.configuration.locales[0].language == "zh"
    private val dark get() = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    private val canvas get() = color(0xF6F6F6, 0x121212)
    private val surface get() = color(0xFFFFFF, 0x202020)
    private val primary get() = color(0x161616, 0xEEEEEE)
    private val ink get() = color(0x161616, 0xEEEEEE)
    private val secondary get() = color(0x646464, 0xAAAAAA)
    private val accentSurface get() = color(0xEAEAEA, 0x303030)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.setBackgroundColor(canvas)
        val lightBars = WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS or WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
        window.insetsController?.setSystemBarsAppearance(if (dark) 0 else lightBars, lightBars)
        val page = column().apply { setPadding(dp(24), dp(24), dp(24), dp(28)) }
        val scroll = ScrollView(this).apply {
            isFillViewport = true
            setBackgroundColor(canvas)
            addView(page)
            setOnApplyWindowInsetsListener { view, insets ->
                val bars = insets.getInsets(WindowInsets.Type.systemBars() or WindowInsets.Type.displayCutout())
                view.setPadding(bars.left, bars.top, bars.right, bars.bottom)
                insets
            }
        }
        setContentView(scroll)
        page.addView(label("PIXEL  /  LSPOSED", 12f, primary, true).apply { letterSpacing = 0.12f })
        page.addView(label(t("私人空間捷徑", "Private Space\nShortcuts"), 32f, ink, true), spaced(14))
        page.addView(label(t("開始使用", "GET STARTED"), 12f, secondary, true), spaced(28))
        val setup = card(surface)
        setup.addView(step("1", t("啟用模組", "Enable the module"), t("在 LSPosed 啟用本模組。", "Enable Private Space Shortcuts in LSPosed.")))
        lateinit var managerButton: TextView
        managerButton = action(t("開啟 LSPosed", "Open LSPosed"), false) { openLsposed(managerButton) }
        setup.addView(managerButton, spaced(12))
        setup.addView(step("2", t("選擇 Pixel Launcher", "Select Pixel Launcher"), t("作用域只勾選 Pixel Launcher，然後重新啟動桌面。", "Scope only Pixel Launcher, then restart the launcher.")), spaced(22))
        setup.addView(step("3", t("新增至主畫面", "Add to Home screen"), t("解鎖私人空間 → 長按 App → 新增至主畫面。", "Unlock Private Space → long-press an app → Add to Home screen.")), spaced(22))
        page.addView(setup, spaced(10))
        page.addView(action(t("在 GitHub 查看專案", "View project on GitHub"), true) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/asadman1523/pixel-private-space-shortcuts")))
        }, spaced(24))
    }

    private fun openLsposed(button: View) {
        val installedManager = packageManager.getLaunchIntentForPackage("org.lsposed.manager")
        if (installedManager != null) {
            try { startActivity(installedManager); return } catch (_: RuntimeException) { /* Try the bundled manager. */ }
        }
        // This fixed broadcast is the installed LSPosed 2.2.0 Magisk action.sh entrypoint.
        // Root is requested only for this explicit button press, never for private-app shortcuts.
        Toast.makeText(this, t("首次開啟請允許 Magisk 授權", "Allow the Magisk request to open the bundled manager"), Toast.LENGTH_LONG).show()
        button.isEnabled = false
        Thread({
            var process: java.lang.Process? = null
            val success = try {
                process = ProcessBuilder("su", "-c", "am broadcast -a android.telephony.action.SECRET_CODE -d android_secret_code://5776733 android")
                    .redirectErrorStream(true).start()
                val finished = process.waitFor(60, TimeUnit.SECONDS)
                finished && process.exitValue() == 0
            } catch (_: Exception) { false }
            finally {
                process?.destroy()
                runCatching { process?.inputStream?.close() }
            }
            runOnUiThread {
                if (!isDestroyed) {
                    button.isEnabled = true
                    if (!success) Toast.makeText(this, t("無法開啟 LSPosed，請從 Magisk 的模組頁開啟", "Could not open LSPosed. Open it from Magisk's Modules page."), Toast.LENGTH_LONG).show()
                }
            }
        }, "open-lsposed").start()
    }

    private fun step(number: String, title: String, description: String): View = LinearLayout(this).apply {
        gravity = Gravity.TOP
        addView(label(number, 14f, primary, true).apply {
            gravity = Gravity.CENTER
            background = rounded(accentSurface, 12)
        }, LinearLayout.LayoutParams(dp(34), dp(34)))
        addView(column().apply {
            addView(label(title, 17f, ink, true))
            addView(label(description, 14f, secondary), spaced(5))
        }, LinearLayout.LayoutParams(0, -2, 1f).apply { marginStart = dp(14) })
    }
    private fun action(text: String, filled: Boolean, click: () -> Unit) = label(text, 14f, if (filled) canvas else primary, true).apply {
        gravity = Gravity.CENTER
        minHeight = dp(52)
        setPadding(dp(18), dp(14), dp(18), dp(14))
        background = RippleDrawable(ColorStateList.valueOf(color(0xCCCCCC, 0x555555)), rounded(if (filled) primary else accentSurface, 26), null)
        isClickable = true
        isFocusable = true
        setOnClickListener { click() }
        accessibilityDelegate = object : View.AccessibilityDelegate() {
            override fun onInitializeAccessibilityNodeInfo(host: View, info: android.view.accessibility.AccessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                info.className = "android.widget.Button"
            }
        }
    }
    private fun card(tint: Int) = column().apply {
        background = rounded(tint, 24)
        setPadding(dp(22), dp(22), dp(22), dp(22))
    }
    private fun column() = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }
    private fun label(value: String, size: Float, tint: Int, bold: Boolean = false) = TextView(this).apply {
        text = value
        textSize = size
        setTextColor(tint)
        typeface = Typeface.create(if (bold) "sans-serif-medium" else "sans-serif", Typeface.NORMAL)
        setLineSpacing(dp(3).toFloat(), 1f)
        includeFontPadding = false
    }
    private fun rounded(tint: Int, radius: Int) = GradientDrawable().apply { setColor(tint); cornerRadius = dp(radius).toFloat() }
    private fun spaced(top: Int) = LinearLayout.LayoutParams(-1, -2).apply { topMargin = dp(top) }
    private fun dp(value: Int) = (value * resources.displayMetrics.density).toInt()
    private fun color(light: Int, night: Int): Int = (if (dark) night else light) or 0xFF000000.toInt()
    private fun t(zhText: String, enText: String) = if (zh) zhText else enText
}
