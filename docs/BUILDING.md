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

Run `./gradlew testReleaseUnitTest lintRelease assembleRelease`. Without these variables, release output is unsigned and cannot be installed. Never commit a keystore, password file, raw device dump, or extracted Google APK. Back up the release signing key privately: updates must use the same certificate.

## CI and publishing

`build.yml` checks documentation, unit tests, lint and a debug APK on both `main` and feature branches. The README Build badge reads the actual main-branch result. Release and cumulative-download badges are added only after an APK release exists. Alpha releases remain prereleases while the [acceptance matrix](TESTING.md) has unverified rows. A green CI badge certifies these automated checks, not all device behavior.

English README content is the baseline. Update all 18 translations with it, keep all 19 language links expanded, and run the documentation checker before publishing.
