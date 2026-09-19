# Privacy Policy

Last updated: September 19, 2026

This policy describes how Pixel Private Space Shortcuts (Android package `com.jackwu.privatespaceshortcuts`), maintained in the [project repository](https://github.com/asadman1523/pixel-private-space-shortcuts), handles information. It applies to the app and its LSPosed module as distributed by this project.

## Overview

The module works locally on your device to create and open Pixel Launcher Home screen shortcuts for apps in Android Private Space. It does not send personal information or usage data to the project maintainers, operate a backend service, or include advertising, analytics, tracking, or automatic crash-reporting SDKs. No account is required.

Local processing and storage are necessary for the shortcuts to work, as described below.

## Information processed on your device

The module uses information available to Pixel Launcher and Android, including:

- App names, icons, package names, and launch components, to display and open the selected private app.
- Android profile identifiers and serial numbers, profile type, and lock/unlock availability, to identify the correct private profile.
- Native Launcher workspace items, including shortcut placement and module ownership markers, to create and manage shortcuts.
- Temporary launch requests and Launcher lifecycle or interaction events, to coordinate authentication, cancel pending requests, and avoid unintended launches.
- The Android API level, Pixel Launcher version, and a SHA-256 fingerprint calculated from the installed Launcher APK, for compatibility checks and diagnostics.

The module does not read the contents of your private apps, such as messages, photos, documents, or account data. It does not request your contacts, location, microphone, or camera.

## Storage and retention

Shortcuts are stored as native items in Pixel Launcher's own workspace database and use Launcher's icon handling. They include the target component, private-profile serial number, and a marker identifying module-created items. The module does not maintain a separate cloud copy or shortcut database.

Pending launch requests are held in memory. They are consumed after a successful launch or cleared on cancellation, expiry (normally two minutes), or destruction of the tracked Launcher activity.

The app disables backup for its own application data and declares backup and device-transfer exclusions. These settings do not control Pixel Launcher's data, Android's own services, or other software's backup behavior.

## Local diagnostic logs

The module writes diagnostic messages to the local Xposed/LSPosed logging system. These can include Launcher version and APK fingerprint information, initialization results, shortcut-operation events, authentication status or profile readiness, and exception class names.

The module does not automatically upload these logs. Their retention and access are controlled by LSPosed and your device environment. Logs can reveal that private-profile operations occurred, even when they do not name the target app. Review and redact logs before choosing to share them.

## Private Space visibility and authentication

**A Home screen shortcut intentionally exposes the private app's name and icon, including while Private Space is locked.** Anyone who can view your Home screen or a screenshot of it may see them.

Android handles the system authentication prompt. The module does not collect, store, or receive your PIN, password, pattern, or biometric data. It checks profile availability and requests that Android unlock the intended profile; it does not provide its own credential screen.

The module runs inside Pixel Launcher through LSPosed. Device security, root access, other installed modules, and Launcher behavior can affect the protection of locally stored information. This module is not a guarantee of secrecy for the presence of private apps.

## Permissions, root access, and network activity

The app manifest declares no Android permissions, including no Internet permission. The module's implementation does not make network requests or transmit app or profile information. Because its hooks run inside Pixel Launcher, this statement describes the module's behavior and does not describe all activity of the host Launcher process.

The information page checks whether the standalone LSPosed manager is installed. If you press **Open LSPosed**, it opens that manager or requests root authorization to send a fixed local command that opens the bundled manager. This root request is only used for that button, not for opening private-app shortcuts.

If you select **View project on GitHub**, the app asks an external browser or another suitable app to open the public repository URL. That service handles the resulting connection under its own privacy policy.

## Sharing and third-party services

The module does not sell personal information or automatically share it with maintainers, advertisers, or analytics providers.

Android, Pixel Launcher, LSPosed, Magisk, your browser, and GitHub are separate products governed by their own practices. This policy covers only this project's app and module.

If you voluntarily submit an issue, comment, screenshot, or log to the repository, maintainers and other people with access to that submission can read the information you provide to help investigate or respond. Public GitHub issues and comments are visible to others. Do not include credentials, private app contents, device identifiers, or unredacted logs. GitHub controls hosting and retention of these submissions under its own policies.

## Your choices and removal

You can remove individual shortcuts using Pixel Launcher's normal Home screen controls. To stop the module, disable it in LSPosed and restart Launcher, then uninstall the app if desired. Uninstalling the module does not necessarily remove native Launcher shortcut entries or existing LSPosed logs; manage those separately through Launcher and LSPosed.

Do not clear Launcher storage just to remove this module, because doing so can erase your entire Home screen layout. The project has no server-side app account or automatically collected user dataset to delete. For information you voluntarily posted on GitHub, use GitHub's editing or deletion controls or contact the maintainers about the submission.

## Children

The app does not ask for age or knowingly collect children's personal information through its operation. The same local-only processing described in this policy applies to all users.

## Changes to this policy

Changes will be published in this file with an updated date. The repository's version history records previous revisions. Review the policy for the version you install, especially when its features or data handling change.

## Contact

For privacy questions, contact the project maintainers through the [GitHub issue tracker](https://github.com/asadman1523/pixel-private-space-shortcuts/issues). This is a public channel; describe your concern without including sensitive personal information.
