# Verification record

Status: **experimental alpha, acceptance testing incomplete**. Date: 2026-09-20.

## Target inspected

| Item | Value |
| --- | --- |
| Device | Pixel 10a |
| Android | 17 / API 37 |
| Build | `CP2A.260805.005` |
| Launcher | `com.google.android.apps.nexuslauncher`, version 17, code 907 |
| Launcher APK SHA-256 | `ef87b593f9131a6261691c6b6951d307610c6840c6b8a7e445a6e8026766644e` |
| Runtime | Magisk; LSPosed 2.2.0 (7854), enabled by the owner with Launcher-only scope |

The APK was inspected locally. The APK, decompiled sources, raw logs, serial numbers and signing secrets are excluded from this repository.

## Automated checks

- Local debug APK builds with JDK 17, Gradle 9.4.1 and SDK 37.0.
- Android lint passes.
- 15 `LaunchGate` unit tests pass: one-shot completion, cancellation, unsolicited broadcasts, premature return, delayed readiness, wrong-profile events, repeated taps, expiry, explicit cancellation, relocking, no-prompt approval, old timer isolation, readiness polling without broadcast, leaving Launcher during profile start, post-return interaction cancellation and initiating-tap safety (some tests cover multiple cases).
- All 19 README files and local documentation links are checked by `tools/check_docs.py`.
- The Build badge reports the real GitHub main-branch workflow; it is not a static success claim.
- GitHub Actions [run 35464883636](https://github.com/asadman1523/pixel-private-space-shortcuts/actions/runs/35464883636) passed the build, unit tests, lint and documentation checks on Linux for alpha.2. Alpha.3 CI is pending.

## Device acceptance matrix

| Scenario | Result / evidence |
| --- | --- |
| Adapter loads in Launcher | PASS: fingerprint match and enabled adapter diagnostic |
| Native long-press entry | PASS: original localized "Add to Home screen" row appears for a private app |
| Add to workspace | PASS: native workspace entry and serialized profile identity observed |
| Correct private instance while unlocked | PASS: resumed test activity belongs to the private user, not the owner |
| Duplicate add | PASS: rejection diagnostic; original shortcut retained |
| Native lock badge | PASS for ordinary home icons: one native badge, no additional overlay |
| Lock retains icon | PASS: icon and position remain after locking |
| Full color while locked | PASS after removing the owned item's quiet visual-disable flag |
| System authentication opens | PASS: Android credential confirmation activity opens |
| Cancel authentication | PASS: Back returns without launching the private app |
| Successful authentication → exactly one launch | PASS (alpha.3): user confirmed unlock and automatic open; resumed activity belongs to the private profile |
| Launcher restart while locked | PASS: shortcut and profile identity remain at the same location |
| Native Remove action | PASS (alpha.3): alpha.2 had a misidentification bug where Remove triggered the Add-to-Home path due to R8-merged `SystemShortcut$Install.onClick`; alpha.3 uses a weak-reference set of exact factory-created add actions to distinguish Add from Remove |
| Move on Home screen | PASS: moved to another empty cell and restored to the original position |
| Drag from All Apps to Home screen | PASS (alpha.3): native drag-and-drop places a module-owned shortcut with correct private profile identity |
| Duplicate drag rejection | PASS (alpha.3): second drag of the same private app is rejected; diagnostic `Duplicate shortcut rejected` |
| Adaptive icon | PASS (alpha.3): blue background (#6291F6), white rounded-S foreground, single native lock badge |
| Prediction row: long-press Add to Home screen | Pending device test |
| Prediction row: drag to Home screen | Pending device test |
| Folders | Pending: automated drag attempts did not establish a folder result |
| Themed icons; light/dark home and folder badges | Pending |
| MainActivity light/dark | PASS: neutral palette, system bars and reconfiguration visually inspected |
| Open LSPosed button | PASS: owner granted Magisk authorization and confirmed bundled manager opened; standalone-manager variant is not device-tested |
| Repeated tapping during device authentication | Pending device test; unit state machine passes |
| App deletion | Pending device test; uses native cleanup |
| Profile deletion | Not tested: the owner's existing private profile must not be deleted for a test |
| Phone reboot | Pending |
| Grid migration | Pending |
| Disable module, retain Launcher data | Pending |
| Demo GIF | Pending verified recording without private content or credentials |

## Reproducing remaining checks

Use the module's own information activity as the harmless test application in both profiles. Verify the resumed Android user, because the information page intentionally does not display profile identifiers. Never publish raw activity dumps.

After adding a shortcut, lock Private Space through its native control. Tap once, cancel, and verify no launch occurs on a later unrelated unlock. Repeat with a successful authentication, rapid taps and a Launcher restart during the prompt. Then test placement in a disposable folder, appearance modes, native removal and reinstall of the test app. Test profile deletion only on a disposable test profile/device.

For prediction row testing, verify that a private-profile app appearing in the suggestion row at the top of All Apps can be long-pressed to show "Add to Home screen" and can be dragged to the Home screen. Confirm the resulting shortcut opens the correct private instance.

Reboot, change and restore the grid, and disable/re-enable the module only with the owner's layout preserved. Do not clear Launcher storage. Record only harmless test content; omit authentication credentials entirely. Update each row from observed results before labeling a release stable.
