# Project instructions

- Reply to the owner in Traditional Chinese.
- Use UTF-8 explicitly for text files and Python (`PYTHONUTF8=1`, `PYTHONIOENCODING=utf-8`, or `python -X utf8`).
- Only hook `com.google.android.apps.nexuslauncher`; no APK version or fingerprint allowlist is required.
- Preserve native profile identity, system authentication, icon badging, and the existing workspace.
- Never commit extracted Google APKs, decompiled sources, device identifiers, raw device logs, or signing keys.
- Keep all 19 README translations and their language navigation in sync.
- Run unit tests, lint, the debug build, and `python -X utf8 tools/check_docs.py` before publishing changes.
- Record actual device test results; never imply an untested scenario passed.
