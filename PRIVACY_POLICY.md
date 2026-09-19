# Privacy Policy

**Pixel Private Space Shortcuts**

**Effective date: September 19, 2026**

This policy describes how Pixel Private Space Shortcuts (Android package `com.jackwu.privatespaceshortcuts`), maintained by GitHub user **asadman1523**, handles information. The app is an open-source LSPosed module that adds native Home screen shortcuts for apps in Android's Private Space.

## Information processed on your device

The module works inside Pixel Launcher (`com.google.android.apps.nexuslauncher`). To create and open shortcuts in the correct private profile, it processes:

- App names, icons, package names and launch components available to Launcher.
- Android profile identifiers, including the profile serial number, and whether a profile is private, locked or unlocked.
- Native workspace items and shortcut metadata, including a marker identifying shortcuts created by the module.
- A temporary pending-launch request containing the target profile, app component and request timing. Launcher lifecycle, touch and Home-button events help cancel requests when you leave or abandon an authentication flow.
- Android API level, Pixel Launcher version and a SHA-256 fingerprint computed from the installed Launcher APK, for compatibility diagnostics.

This information supports shortcut placement, profile selection, authentication coordination and troubleshooting. The module does not read the contents of your private apps, such as messages, photos or account data. It does not read, record or receive your PIN, password or biometric data. Android handles authentication through its own system interface.

## Network access, collection and sharing

The module does not send app or profile information to the maintainer or a remote backend. It has no accounts, advertising, analytics, tracking SDKs or automatic crash-report uploads, and its code makes no network requests. We do not sell or rent information processed by the module.

The standalone APK does not request Android's `INTERNET` permission. The hooks run in Pixel Launcher's process, so this does not restrict Launcher's own permissions or independent network activity.

The **View project on GitHub** button opens the repository in your browser or another app. That external service may process information under its own policies; the button does not attach private-app or profile data to the link.

## Storage, retention and removal

Shortcuts are stored in Pixel Launcher's native workspace database, including their app and profile metadata. The module does not maintain a separate shortcut database or a cloud copy. Workspace entries remain until you remove them through Launcher or Launcher performs its own cleanup.

Pending-launch requests exist only in memory. They expire after 120 seconds and are discarded when consumed, cancelled or when Launcher is destroyed or its process ends. They are not saved as a launch-history database.

To remove shortcut data, remove the shortcuts through Launcher. To stop the module, disable it in LSPosed and restart Launcher; you may then uninstall the APK. Disabling or uninstalling the module does not itself remove native workspace entries or LSPosed logs. **Do not clear Launcher storage to remove this module**, as that can erase your entire Home screen layout.

The module's standalone app disables Android backup and declares backup and device-transfer exclusions. These settings do not control Pixel Launcher's database, LSPosed logs or backups managed independently by Android, Launcher or other software.

## Local diagnostic logs

The module writes diagnostic messages to LSPosed's logging system with the `[PPSS]` prefix. These messages include generic operation outcomes, authentication-flow status, exception class names, Launcher version and, when it differs from the reference build, the Launcher APK fingerprint. The module's log messages do not include private-app names, profile serial numbers, full intents or authentication credentials.

Logs are not automatically uploaded by the module. Their retention and removal are controlled by LSPosed and the device's logging facilities. Review and redact any diagnostics before voluntarily sharing them; logs from other software may contain additional information.

## Permissions and visible information

Enable the module in LSPosed with scope limited to Pixel Launcher. The information app checks whether the standalone LSPosed manager is installed so the **Open LSPosed** button can launch it. If that manager is unavailable, pressing the button attempts to open the bundled manager using a fixed system broadcast through `su`, which may prompt for Magisk authorization. This optional action is separate from opening private-app shortcuts; those shortcuts use Android's native profile and launch APIs.

**A Home screen shortcut intentionally shows the private app's name and icon even while Private Space is locked.** Anyone who can see your Home screen may therefore learn that the app is installed. The module retains native profile identity and requests system authentication when required, but it is not a tool for hiding Home screen icons. Remove a shortcut if you do not want that information visible there.

## GitHub, support and third parties

If you voluntarily open an issue or otherwise contact the maintainer through GitHub, the maintainer can see your GitHub username and the information you submit and may use it to answer your request or troubleshoot the project. Public issues and comments are visible to others. Do not post passwords, device identifiers, raw device logs or private screenshots.

Support posts remain on GitHub under its retention and deletion rules. You may edit or delete your own content where GitHub permits, or ask the maintainer to remove information under their control. The maintainer cannot delete copies retained by GitHub or other people. See the [GitHub Privacy Statement](https://docs.github.com/en/site-policy/privacy-policies/github-general-privacy-statement).

Android, Pixel Launcher, LSPosed, Magisk, your browser and apps you launch operate independently. This policy covers this module's behavior; those products' own data handling is governed by their respective policies and settings.

## Children's privacy

The module does not knowingly collect children's personal information. If a child has shared personal information in a project support post, contact the maintainer to request removal of information under their control.

## Changes and contact

Updates to this policy will be published in this file with a revised effective date. Previous versions remain available in the repository's commit history.

For privacy questions or requests, contact **asadman1523** through the [project's GitHub issue tracker](https://github.com/asadman1523/pixel-private-space-shortcuts/issues). Start with a general description and do not include sensitive information in a public issue.
