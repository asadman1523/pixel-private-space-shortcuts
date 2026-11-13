# Building and signing

Requirements: JDK 17, Android SDK Platform 37.0 (API 37), Build Tools 36.0.0 and Python 3.10+. Gradle 9.4.1 is pinned with its distribution SHA-256 in the wrapper. Android Gradle Plugin 9.2.1 supplies built-in Kotlin support. The legacy Xposed API is compile-only and is supplied by LSPosed at runtime.

```sh
sdkmanager 'platforms;android-37.0' 'build-tools;36.0.0'
python3 -X utf8 tools/check_docs.py
./gradlew testDebugUnitTest lintDebug assembleDebug
```

On Windows use `gradlew.bat` and `py -X utf8 tools/check_docs.py`. Configure `ANDROID_HOME`, or create an ignored `local.properties` with an escaped SDK path, such as `sdk.dir=C\:/Android/Sdk`. Debug output is `app/build/outputs/apk/debug/app-debug.apk`. It is a test artifact, not a stable release; different builders can have different debug certificates.

## Release signing

Use a private keystore stored outside the repository. Configure these environment variables in your local shell or secret manager:

| Variable | Meaning |
| --- | --- |
| `PPSS_KEYSTORE` | Absolute path to the private keystore |
| `PPSS_STORE_PASSWORD` | Keystore password |
| `PPSS_KEY_ALIAS` | Signing alias |
| `PPSS_KEY_PASSWORD` | Key password |

Run `./gradlew --no-configuration-cache testDebugUnitTest lintRelease assembleRelease`. The current plugin configuration exposes unit tests for debug, not a separate `testReleaseUnitTest` task. Disabling the configuration cache for signed builds avoids persisting signing configuration in it. Without these variables, release output is unsigned and cannot be installed. Never commit a keystore, password file, raw device dump, or extracted Google APK. Back up the release signing key privately: updates must use the same certificate.

Version 0.1.2 starts a new signing lineage. Users of earlier releases or debug builds must reinstall only the module and re-enable its Launcher-only LSPosed scope; do not clear Launcher data. See the [0.1.2 release notes](releases/0.1.2.md) for migration instructions and the new public certificate fingerprint.

## Google Play signing and CI

The existing Play Store app already uses Play App Signing. Its distribution certificate is separate from the locally signed LSPosed 0.1.2 APK. To unify future distribution, upload a signed AAB to Play, then download the Play-signed universal APK for the LSPosed release. Do not re-sign that APK locally. Existing installations signed with a different certificate may require a one-time module reinstall; preserve Launcher storage.

The manual **Build Play upload bundle** workflow runs only on `main`. Configure repository Actions secrets `PPSS_KEYSTORE_BASE64` (the base64-encoded upload keystore), `PPSS_STORE_PASSWORD`, `PPSS_KEY_ALIAS`, and `PPSS_KEY_PASSWORD`. The workflow validates source, restores the key into the temporary runner directory, builds an upload-signed AAB without Gradle configuration caching, verifies its certificate and uploads only the AAB/checksum. The temporary key is removed even on failure. No Play service-account credential is needed for this build-only workflow; it does not submit or publish releases.

The candidate upload certificate uses SHA-256 `6b1bff92feca83d88939af20b04e7a380a6fe7a8920a25e91fa0faf175005c56`. Play Console must approve the upload-key reset before accepting this bundle; the previously registered upload certificate differs. Resetting the upload key does not change the Play distribution signing key. Retain an independent encrypted backup of the keystore and its credentials; GitHub Secrets are not a downloadable backup.

See [Play App Signing](https://support.google.com/googleplay/android-developer/answer/9842756) for upload-key resets and downloading signed universal APKs.

## CI and publishing

`build.yml` checks documentation, unit tests, lint and a debug APK on both `main` and feature branches. The README Build badge reads the actual main-branch result. Release and cumulative-download badges are added only after an APK release exists. Alpha releases remain prereleases while the [acceptance matrix](TESTING.md) has unverified rows. A green CI badge certifies these automated checks, not all device behavior.

The root English `README.md` is the baseline and GitHub homepage. All 18 translations live together under `docs/readme/`. Update them together, keep all 19 language links expanded, and run the documentation checker before publishing.
