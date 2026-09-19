# Pixel Private Space Shortcuts

Native Home screen shortcuts for Pixel Private Space apps, powered by LSPosed.

[![Build](https://img.shields.io/github/actions/workflow/status/asadman1523/pixel-private-space-shortcuts/build.yml?branch=main&style=flat&logo=githubactions&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/actions/workflows/build.yml)
[![Release](https://img.shields.io/github/v/release/asadman1523/pixel-private-space-shortcuts?include_prereleases&style=flat&logo=github&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/releases)
[![Downloads](https://img.shields.io/github/downloads/asadman1523/pixel-private-space-shortcuts/total?style=flat&logo=github&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/releases)
[![Android](https://img.shields.io/badge/Android-17%20%2F%20API%2037%20experimental-orange?style=flat&logo=android&logoColor=white)](#compatibility)
[![License](https://img.shields.io/badge/License-Apache--2.0-blue?style=flat&logo=apache&logoColor=white)](LICENSE)

Read this in other languages: **English**, [简体中文](docs/readme/README.zh-CN.md), [繁體中文](docs/readme/README.zh-TW.md), [한국어](docs/readme/README.ko-KR.md), [日本語](docs/readme/README.ja-JP.md), [Polski](docs/readme/README.pl-PL.md), [Français](docs/readme/README.fr-FR.md), [Español](docs/readme/README.es-ES.md), [Português](docs/readme/README.pt-BR.md), [Русский](docs/readme/README.ru-RU.md), [Türkçe](docs/readme/README.tr-TR.md), [Italiano](docs/readme/README.it-IT.md), [Bahasa Indonesia](docs/readme/README.id-ID.md), [Українська](docs/readme/README.uk-UA.md), [العربية](docs/readme/README.ar-AR.md), [Tiếng Việt](docs/readme/README.vi-VN.md), [Deutsch](docs/readme/README.de-DE.md), [Uzbek](docs/readme/README.uz-UZ.md), [עברית](docs/readme/README.he-IL.md)

Demo recording is pending device verification. No simulated demonstration is presented as a device result.

<a id="features"></a>

## Features

Long-press an app in unlocked Private Space and choose the native **Add to Home screen** action, or drag it directly to the Home screen. This also works for private apps shown in the suggestion row at the top of All Apps. The module uses Pixel Launcher's placement, database, icons and private-profile lock badge. It identifies the target by profile serial number and launch component, rejects duplicates, and supports moving, folders and removal.

When locked, the shortcut is designed to retain its position and request system authentication on tap. An unlocked profile opens directly. Pending launches are consumed once and cleared on cancellation, timeout or Launcher destruction. The owner-profile copy is never a fallback. Only module-created items receive these changes; widgets and app-internal shortcuts are outside this release.

Home screen icons intentionally expose the app’s name and icon even while Private Space is locked.

Locked shortcuts retain their original colors and native lock badge. The information page follows system light/dark mode with a fixed black, white and gray palette.

<a id="compatibility"></a>

## Compatibility

**Experimental alpha; device verification incomplete.** Targeting Android 15+ (API 35+) Pixel devices, but **currently only tested on Android 17** (Pixel 10a, API 37, `CP2A.260805.005`, Pixel Launcher 17). The adapter will attempt to load on Android 15 and 16, but updates may break internal hooks. A successful build is not proof of compatibility.

<a id="installation"></a>

## Installation

Install the APK from [Releases](https://github.com/asadman1523/pixel-private-space-shortcuts/releases) in the main profile. Enable **Pixel Private Space Shortcuts** in LSPosed and select only `com.google.android.apps.nexuslauncher`. Restart Launcher or reboot. CI debug APKs are test artifacts and may use a different signing key.

The information page includes an **Open LSPosed** button. A standalone manager opens directly; the bundled manager requires Magisk authorization the first time. This permission is used only when pressing this button, not when opening private apps.

<a id="usage"></a>

## Usage

Unlock Private Space, long-press an app, and select **Add to Home screen**, or drag it to the Home screen. Private apps in the suggestion row can also be added the same way. Tap its Home screen icon to open the same private copy; authenticate in Android's own prompt if needed. Cancel to abandon that launch. Long-press the Home screen icon to move it, place it in a folder, or remove it. Adding the same target again shows an existing-shortcut message. The module's information app can also be installed in Private Space as a harmless test fixture.

<a id="build"></a>

## Build

Use JDK 17, Android SDK `platforms;android-37.0`, Build Tools `36.0.0`, and the checked-in Gradle wrapper. Run the commands below (`gradlew.bat` on Windows). CI builds, tests the unlock state machine, runs Android lint and checks all README files and local links. Release signing uses the four `PPSS_*` environment variables described in [BUILDING](docs/BUILDING.md); keys stay outside the repository.

```sh
python3 -X utf8 tools/check_docs.py
./gradlew testDebugUnitTest lintDebug assembleDebug
```

<a id="disable"></a>

## Disable or remove

Remove unwanted shortcuts through Launcher, disable the module in LSPosed, and restart Launcher; then uninstall the APK if desired. Never clear Launcher storage. Existing entries are ordinary native workspace items, but the module’s locked-profile handling is unavailable while disabled. Confirmed app/profile deletion follows Launcher’s own cleanup.

<a id="license"></a>

## License

[Apache-2.0](LICENSE). Unaffiliated with Google or LSPosed. Google’s Launcher APK, decompiled files, device logs, credentials and signing keys are not distributed. See [implementation notes](docs/ARCHITECTURE.md) and the [verification record](docs/TESTING.md).

[Privacy policy (English)](PRIVACY.md)
