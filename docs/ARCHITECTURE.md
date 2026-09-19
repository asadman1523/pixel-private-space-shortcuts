# Launcher 907 adapter

This is one Kotlin APK using the legacy Xposed API. The entry point rejects every package/process except `com.google.android.apps.nexuslauncher`. It checks Android API 37, launcher version code 907 and the exact APK SHA-256 before resolving adapter entry points. Hook installation failures unhook already-installed entry points. Unsupported versions log a reason instead of attempting name-based guesses.

## Native menu and persistence

`PopupContainerWithArrow.populateAndShowRows` receives a native `SystemShortcut.ADD_TO_HOME_SCREEN` entry for private-profile `AppInfo` only. The entry is created by the existing factory with a copied `WorkspaceItemInfo`; original app-drawer metadata is not changed. `LauncherAccessibilityDelegate.addToWorkspace` performs native placement, database writes and binding.

The item keeps its original `UserHandle`, component and bitmap. Two namespaced intent extras identify module ownership and the stable user serial. They survive native database serialization and folder/move operations. Duplicate detection examines the native workspace model using **profile serial + launch component**, including items in folders. Pending adds block a second simultaneous request. No separate shortcut database, proxy launch activity, shell launch command or direct database write is used.

## Appearance

Native icon creation and private-profile badges are retained without custom badge artwork. For owned items only, the adapter clears the native no-pinning bit and quiet-profile visual-disable bit. This keeps locked icons full-color as requested while allowing native movement. Other disabled reasons remain native. Authentication checks actual `UserManager` state, independently of icon presentation flags.

The information activity uses a fixed neutral black/white/gray palette with system light/dark switching. It does not use wallpaper-derived colors or claim that module activation can be determined from the separate app process.

## Opening and authentication

The verified native workspace click listener intercepts owned entries. An invalid ownership/profile serial fails closed. A valid, unlocked target opens via `LauncherApps.startMainActivity` with the original private user. If an activity alias changed, only a unique launcher activity in the same package and profile may be used. No owner-profile fallback exists.

For a quiet profile the foreground default launcher's `UserManager.requestQuietModeEnabled(false, user)` invokes Android authentication. A transient `LaunchGate` stores one request for at most 120 seconds. A successful return to Launcher plus real profile readiness consumes it once. Cancellation while still quiet clears it. A system-approved no-prompt transition can wait for profile readiness. Broadcasts alone do not authorize a launch. Launcher destruction and process death discard pending requests. No unlock credentials are read, stored or submitted.

Normal private-profile lock events are handled by the existing native model; confirmed app/profile removal remains native cleanup. The device matrix identifies which persistence scenarios have actually been verified. There are no global changes to app-drawer restrictions, system authentication, other launchers, work profiles, widgets or deep shortcuts.

## Diagnostics and limitations

Logs use `[PPSS]` and generic operation outcomes rather than app names, user identifiers or intent contents. Read [TESTING](TESTING.md) before interpreting compatibility. The public repository contains independently written adapter code, not Google’s APK or decompiled implementation.
