# Pixel Private Space Shortcuts

通过 LSPosed，将 Pixel 私密空间应用添加到原生主屏幕。

[![Build](https://img.shields.io/github/actions/workflow/status/asadman1523/pixel-private-space-shortcuts/build.yml?branch=main&style=flat&logo=githubactions&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/actions/workflows/build.yml)
[![Release](https://img.shields.io/github/v/release/asadman1523/pixel-private-space-shortcuts?include_prereleases&style=flat&logo=github&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/releases)
[![Downloads](https://img.shields.io/github/downloads/asadman1523/pixel-private-space-shortcuts/total?style=flat&logo=github&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/releases)
[![Android](https://img.shields.io/badge/Android-17%20%2F%20API%2037%20experimental-orange?style=flat&logo=android&logoColor=white)](#compatibility)
[![License](https://img.shields.io/badge/License-Apache--2.0-blue?style=flat&logo=apache&logoColor=white)](../../LICENSE)

Read this in other languages: [English](../../README.md), **简体中文**, [繁體中文](README.zh-TW.md), [한국어](README.ko-KR.md), [日本語](README.ja-JP.md), [Polski](README.pl-PL.md), [Français](README.fr-FR.md), [Español](README.es-ES.md), [Português](README.pt-BR.md), [Русский](README.ru-RU.md), [Türkçe](README.tr-TR.md), [Italiano](README.it-IT.md), [Bahasa Indonesia](README.id-ID.md), [Українська](README.uk-UA.md), [العربية](README.ar-AR.md), [Tiếng Việt](README.vi-VN.md), [Deutsch](README.de-DE.md), [Uzbek](README.uz-UZ.md), [עברית](README.he-IL.md)

功能演示将在真机验证后录制，不以模拟画面冒充实测结果。

<a id="features"></a>

## 功能

在已解锁的私密空间中长按应用，选择原生的“添加到主屏幕”，或直接拖拽至主屏幕。顶部推荐行中的私密应用同样支持。模块沿用 Pixel Launcher 的空位分配、数据库、图标及私密资料锁形标记，以资料序列号和启动组件识别目标，阻止重复添加，支持移动、文件夹和移除。

锁定时设计上保留快捷方式位置，点击后请求系统验证；已解锁时直接打开。待启动请求只执行一次，取消、超时或 Launcher 销毁时清除，绝不回退到主空间副本。修改仅适用于模块创建的项目，本版不包括微件或应用内部快捷方式。

私密空间锁定时，桌面仍会显示应用名称和图标。

锁定时快捷方式保留原本颜色和原生锁形标记。说明页面采用固定黑白灰配色，支持跟随系统深色／浅色模式。

<a id="compatibility"></a>

## 兼容性

**实验性测试版，真机验证尚未完成。** 目标支持 Android 15+（API 35+）Pixel 设备，但**目前仅在 Android 17 上测试**（Pixel 10a、API 37、`CP2A.260805.005`、Pixel Launcher 17）。适配器会尝试在 Android 15 和 16 上加载，但更新可能会导致内部 Hook 失效。构建成功不代表真机兼容。

<a id="installation"></a>

## 安装

从 [Releases](https://github.com/asadman1523/pixel-private-space-shortcuts/releases) 下载 APK 并安装到主空间。在 LSPosed 启用 **Pixel Private Space Shortcuts**，作用域仅选择 `com.google.android.apps.nexuslauncher`，然后重启 Launcher 或手机。CI debug APK 是测试产物，签名可能不同。

说明页面提供“打开 LSPosed”按钮。独立管理器可直接打开；内置管理器首次使用需要 Magisk 授权。此权限仅在点击该按钮时使用，不用于打开私密应用。

<a id="usage"></a>

## 使用

解锁私密空间，长按应用并选择“添加到主屏幕”，或直接拖拽至主屏幕。推荐行中的私密应用也可以同样操作。点击桌面图标打开同一个私密副本，必要时在 Android 原生界面完成验证；取消即放弃本次启动。长按桌面图标可以移动、放入文件夹或移除。重复添加会提示已存在。模块的说明应用也可安装到私密空间，作为不含敏感数据的测试应用。

<a id="build"></a>

## 构建

使用 JDK 17、Android SDK `platforms;android-37.0`、Build Tools `36.0.0` 及项目内的 Gradle wrapper。运行下方命令，Windows 使用 `gradlew.bat`。CI 构建、测试解锁状态机、运行 Android lint，并检查全部 README 和本地链接。发布签名使用 [BUILDING](../BUILDING.md) 中的四个 `PPSS_*` 环境变量，密钥保存在仓库之外。

```sh
python3 -X utf8 tools/check_docs.py
./gradlew testDebugUnitTest lintDebug assembleDebug
```

<a id="disable"></a>

## 停用或卸载

通过 Launcher 移除不需要的快捷方式，在 LSPosed 停用模块并重启 Launcher，然后按需卸载 APK。不要清除 Launcher 数据。现有项目是原生桌面项目，但停用后没有模块提供的锁定处理。确认应用或资料已删除时，使用 Launcher 的原生清理机制。

<a id="license"></a>

## 许可

[Apache-2.0](../../LICENSE)。与 Google 或 LSPosed 无隶属关系。不发布 Google Launcher APK、反编译文件、设备日志、凭据或签名密钥。参见[实现说明](../ARCHITECTURE.md)和[验证记录](../TESTING.md)。

[隐私政策（英文）](../../PRIVACY_POLICY.md)
