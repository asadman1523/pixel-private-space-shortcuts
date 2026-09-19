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
- 11 `LaunchGate` unit tests pass: one-shot completion, cancellation, unsolicited broadcasts, premature return, delayed readiness, wrong-profile events, repeated taps, expiry, explicit cancellation, relocking, no-prompt approval and old timer isolation (some tests cover multiple cases).
- All 19 README files and local documentation links are checked by `tools/check_docs.py`.
- The Build badge reports the real GitHub main-branch workflow; it is not a static success claim.
- GitHub Actions [run 35464207868](https://github.com/asadman1523/pixel-private-space-shortcuts/actions/runs/35464207868) passed the build, unit tests, lint and documentation checks on Linux. The earlier SDK-setup failure was fixed by pinning current command-line tools and omitting the obsolete `tools` package.

## Device acceptance matrix

| Scenario | Result / evidence |
| --- | --- |
| Adapter loads in Launcher | PASS: fingerprint match and enabled adapter diagnostic |
| Native long-press entry | PASS: original localized “Add to Home screen” row appears for a private app |
| Add to workspace | PASS: native workspace entry and serialized profile identity observed |
| Correct private instance while unlocked | PASS: resumed test activity belongs to the private user, not the owner |
| Duplicate add | PASS: rejection diagnostic; original shortcut retained |
| Native lock badge | PASS for ordinary home icons: one native badge, no additional overlay |
| Lock retains icon | PASS: icon and position remain after locking |
| Full color while locked | PASS after removing the owned item's quiet visual-disable flag |
| System authentication opens | PASS: Android credential confirmation activity opens |
| Cancel authentication | PASS: Back returns without launching the private app |
| Successful authentication → exactly one launch | Pending controlled confirmation |
| Launcher restart while locked | PASS: shortcut and profile identity remain at the same location |
| Native Remove action | PASS on a module-created workspace item |
| Move on Home screen | PASS: moved to another empty cell and restored to the original position |
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

Reboot, change and restore the grid, and disable/re-enable the module only with the owner's layout preserved. Do not clear Launcher storage. Record only harmless test content; omit authentication credentials entirely. Update each row from observed results before labeling a release stable.
