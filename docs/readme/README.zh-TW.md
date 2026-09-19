# Pixel Private Space Shortcuts

透過 LSPosed，將 Pixel 私人空間 App 加入原生主畫面。

[![Build](https://img.shields.io/github/actions/workflow/status/asadman1523/pixel-private-space-shortcuts/build.yml?branch=main&style=flat&logo=githubactions&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/actions/workflows/build.yml)
[![Android](https://img.shields.io/badge/Android-17%20%2F%20API%2037%20experimental-orange?style=flat&logo=android&logoColor=white)](#compatibility)
[![License](https://img.shields.io/badge/License-Apache--2.0-blue?style=flat&logo=apache&logoColor=white)](../../LICENSE)

Read this in other languages: [English](../../README.md), [简体中文](README.zh-CN.md), **繁體中文**, [한국어](README.ko-KR.md), [日本語](README.ja-JP.md), [Polski](README.pl-PL.md), [Français](README.fr-FR.md), [Español](README.es-ES.md), [Português](README.pt-BR.md), [Русский](README.ru-RU.md), [Türkçe](README.tr-TR.md), [Italiano](README.it-IT.md), [Bahasa Indonesia](README.id-ID.md), [Українська](README.uk-UA.md), [العربية](README.ar-AR.md), [Tiếng Việt](README.vi-VN.md), [Deutsch](README.de-DE.md), [Uzbek](README.uz-UZ.md), [עברית](README.he-IL.md)

功能示範尚待實機驗證後錄製；不以模擬畫面冒充實測成果。

<a id="features"></a>

## 功能

在已解鎖的私人空間長按 App，即可選擇原生的「新增至主畫面」。模組沿用 Pixel Launcher 的空位配置、資料庫、圖示及私人設定檔鎖頭標記，以設定檔序號與啟動元件識別目標、阻止重複新增，並支援移動、資料夾及移除。

私人空間鎖定時，設計上保留捷徑位置，點擊後要求系統驗證；已解鎖時直接開啟。待啟動要求只執行一次，取消、逾時或 Launcher 銷毀時清除，不會改開主空間分身。這些調整僅適用於模組建立的項目；本版不包含小工具或 App 內部功能捷徑。

即使私人空間鎖定，桌面捷徑仍會顯示 App 名稱與圖示。

鎖定時捷徑維持原本顏色與原生鎖頭。說明頁面使用固定黑白灰配色，支援跟隨系統深色／淺色模式。

<a id="compatibility"></a>

## 相容性

**實驗性測試版，實機驗證尚未完成。** 目標為 Pixel 10a、Android 17／API 37、組建 `CP2A.260805.005`、Pixel Launcher 17（`907`）、Magisk 與 LSPosed 2.2.0（7854）。Hook 必須符合[驗證紀錄](../TESTING.md)中的 APK 指紋；其他版本停用適配器並記錄原因。建置成功不代表實機相容。

<a id="installation"></a>

## 安裝

從 [Releases](https://github.com/asadman1523/pixel-private-space-shortcuts/releases) 下載 APK，安裝到主空間。在 LSPosed 啟用 **Pixel Private Space Shortcuts**，作用域只勾選 `com.google.android.apps.nexuslauncher`，然後重啟 Launcher 或手機。CI 的 debug APK 屬於測試產物，可能使用不同簽章。

<a id="usage"></a>

## 使用

解鎖私人空間，長按 App，選擇「新增至主畫面」。點擊桌面圖示會開啟同一個私人分身，必要時在 Android 原生畫面完成驗證；取消便放棄此次開啟。長按桌面圖示可以移動、放入資料夾或移除。再次新增相同目標會顯示已存在提示。模組的說明 App 也能安裝到私人空間，作為無敏感資料的測試 App。

<a id="build"></a>

## 建置

使用 JDK 17、Android SDK `platforms;android-37.0`、Build Tools `36.0.0` 與專案內的 Gradle wrapper。執行下方指令，Windows 改用 `gradlew.bat`。CI 會建置、測試解鎖狀態機、執行 Android lint，並檢查所有 README 與本地連結。Release 簽章使用 [BUILDING](../BUILDING.md) 說明的四個 `PPSS_*` 環境變數，金鑰留在 repo 外。

```sh
python3 -X utf8 tools/check_docs.py
./gradlew testDebugUnitTest lintDebug assembleDebug
```

<a id="disable"></a>

## 停用或移除

先透過 Launcher 移除不需要的捷徑，再於 LSPosed 停用模組並重啟 Launcher，之後可解除安裝 APK。不要清除 Launcher 資料。既有捷徑是原生桌面項目，但停用後無法使用模組提供的鎖定處理。確認 App 或設定檔刪除時，沿用 Launcher 原生清理機制。

<a id="license"></a>

## 授權

[Apache-2.0](../../LICENSE)。本專案與 Google、LSPosed 無隸屬關係，不散布 Google Launcher APK、反編譯檔案、裝置日誌、憑證或簽章金鑰。另見[實作說明](../ARCHITECTURE.md)與[驗證紀錄](../TESTING.md)。
