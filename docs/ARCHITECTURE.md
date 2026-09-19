# Launcher 907 adapter

This is one Kotlin APK using the legacy Xposed API. The entry point rejects every package/process except `com.google.android.apps.nexuslauncher`. It expects Android API 35+ and logs warnings for mismatched launcher version codes or APK SHA-256 fingerprints, but attempts to install hooks anyway for better forward compatibility. Hook installation failures unhook already-installed entry points.

## Native menu, drag and persistence

`PopupContainerWithArrow.populateAndShowRows` receives a native `SystemShortcut.ADD_TO_HOME_SCREEN` entry for private-profile items. The adapter supports both `AppInfo` (regular All Apps, container `-104`) and `WorkspaceItemInfo` (prediction row, container `-103`). The entry is created by the existing factory with a copied `WorkspaceItemInfo`; original app-drawer and prediction metadata is not changed. `LauncherAccessibilityDelegate.addToWorkspace` performs native placement, database writes and binding.

R8 merges Add, Remove and Install into a single `SystemShortcut$Install.onClick`. A weak-reference set (`addActions`) records only the exact factory-created add actions. Only entries in this set are intercepted; native Remove and other merged actions pass through to Launcher unchanged.

`Workspace.beginDragShared` (six-parameter version) is hooked for the same item types. When a private-profile `AppInfo` or prediction-row `WorkspaceItemInfo` begins dragging, only the drag payload is replaced with a module-owned `WorkspaceItemInfo`. The source list keeps its original item. `Workspace.acceptDrop` and `Folder.acceptDrop` check new owned items (ID not yet assigned) for duplicates using **profile serial + launch component** against the native workspace model, including items in folders. Moving an existing Home screen item is not treated as a new add.

The item keeps its original `UserHandle`, component and bitmap. Two namespaced intent extras identify module ownership and the stable user serial. They survive native database serialization and folder/move operations. No separate shortcut database, proxy launch activity, shell launch command or direct database write is used.

## Appearance

Native icon creation and private-profile badges are retained without custom badge artwork. For owned items only, the adapter clears the native no-pinning bit and quiet-profile visual-disable bit (`0x2000`, `0x8`). This keeps locked icons full-color as requested while allowing native movement. Other disabled reasons remain native. Authentication checks actual `UserManager` state, independently of icon presentation flags.

The module uses an adaptive icon with a fixed blue background (`#6291F6`), a white rounded-S vector foreground and a monochrome layer reusing the same foreground. The color does not follow wallpaper or Material You dynamic colors.

The information activity uses a fixed neutral black/white/gray palette with system light/dark switching. It does not use wallpaper-derived colors or claim that module activation can be determined from the separate app process.

Its **Open LSPosed** button first uses the standalone manager's launch intent, when installed. For the target device's bundled manager, it uses the fixed secret-code broadcast from the installed LSPosed 2.2.0 Magisk `action.sh`. This optional button asks Magisk for root only when explicitly pressed. Private-app shortcuts themselves never run shell commands or request root. Denial or timeout leaves a retryable button and directs the user to Magisk's Modules page.

## Opening and authentication

The verified native workspace click listener intercepts owned entries. An invalid ownership/profile serial fails closed. A valid, unlocked target opens via `LauncherApps.startMainActivity` with the original private user. If an activity alias changed, only a unique launcher activity in the same package and profile may be used. No owner-profile fallback exists.

For a quiet profile the foreground default launcher's `UserManager.requestQuietModeEnabled(false, user)` invokes Android authentication. A transient `LaunchGate` stores one request for at most 120 seconds. On return, if the profile is not yet unlocked, a bounded readiness poll rechecks every 100 ms while Launcher is foreground and every 500 ms in background. `ACTION_PROFILE_ACCESSIBLE` broadcasts are also monitored as hints. Only when Launcher is foreground, the state machine confirms a valid return or system approval, and the target profile is confirmed non-quiet and unlocked, is the request consumed once and the app started.

Cancellation (profile still quiet on return) clears the request. Subsequent touch events, HOME intents or leaving Launcher after returning also cancel a pending launch. Launcher destruction and process death discard pending requests. No unlock credentials are read, stored or submitted.

Normal private-profile lock events are handled by the existing native model; confirmed app/profile removal remains native cleanup. The device matrix identifies which persistence scenarios have actually been verified. There are no global changes to app-drawer restrictions, system authentication, other launchers, work profiles, widgets or deep shortcuts.

## Diagnostics and limitations

Logs use `[PPSS]` and generic operation outcomes rather than app names, user identifiers or intent contents. Read [TESTING](TESTING.md) before interpreting compatibility. The public repository contains independently written adapter code, not Google's APK or decompiled implementation.
