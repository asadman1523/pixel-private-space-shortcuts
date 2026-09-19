# Pixel Private Space Shortcuts

LSPosed로 Pixel 비공개 공간 앱의 기본 홈 화면 바로가기를 만듭니다.

[![Build](https://img.shields.io/github/actions/workflow/status/asadman1523/pixel-private-space-shortcuts/build.yml?branch=main&style=flat&logo=githubactions&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/actions/workflows/build.yml)
[![Release](https://img.shields.io/github/v/release/asadman1523/pixel-private-space-shortcuts?include_prereleases&style=flat&logo=github&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/releases)
[![Downloads](https://img.shields.io/github/downloads/asadman1523/pixel-private-space-shortcuts/total?style=flat&logo=github&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/releases)
[![Android](https://img.shields.io/badge/Android-17%20%2F%20API%2037%20experimental-orange?style=flat&logo=android&logoColor=white)](#compatibility)
[![License](https://img.shields.io/badge/License-Apache--2.0-blue?style=flat&logo=apache&logoColor=white)](../../LICENSE)

Read this in other languages: [English](../../README.md), [简体中文](README.zh-CN.md), [繁體中文](README.zh-TW.md), **한국어**, [日本語](README.ja-JP.md), [Polski](README.pl-PL.md), [Français](README.fr-FR.md), [Español](README.es-ES.md), [Português](README.pt-BR.md), [Русский](README.ru-RU.md), [Türkçe](README.tr-TR.md), [Italiano](README.it-IT.md), [Bahasa Indonesia](README.id-ID.md), [Українська](README.uk-UA.md), [العربية](README.ar-AR.md), [Tiếng Việt](README.vi-VN.md), [Deutsch](README.de-DE.md), [Uzbek](README.uz-UZ.md), [עברית](README.he-IL.md)

실기기 검증 후 시연을 녹화합니다. 모의 화면을 실측 결과로 표시하지 않습니다.

<a id="features"></a>

## 기능

잠금 해제된 비공개 공간에서 앱을 길게 눌러 기본 **홈 화면에 추가**를 선택하거나 홈 화면으로 직접 드래그합니다. 상단 추천 행의 비공개 앱도 같은 방식으로 추가할 수 있습니다. Pixel Launcher의 배치, 데이터베이스, 아이콘과 비공개 프로필 자물쇠 표시를 그대로 사용합니다. 프로필 일련번호와 실행 컴포넌트로 대상을 식별하여 중복을 막고 이동, 폴더, 삭제를 지원합니다.

잠긴 상태에서도 위치를 유지하고 탭하면 시스템 인증을 요청하도록 설계했습니다. 이미 해제되었다면 해당 프로필에서 바로 실행합니다. 대기 중인 실행은 한 번만 처리하며 취소, 시간 초과, Launcher 종료 시 지웁니다. 기본 프로필의 앱으로 대신 실행하지 않습니다. 모듈이 만든 항목만 변경하며 위젯과 앱 내부 바로가기는 지원하지 않습니다. 잠금 중에도 홈 화면에 앱 이름과 아이콘이 노출됩니다.

잠금 중에도 바로가기는 원래 색상과 기본 자물쇠 표시를 유지합니다. 안내 페이지는 고정된 흑백·회색 팔레트로 시스템 밝은 모드와 어두운 모드를 따릅니다.

<a id="compatibility"></a>

## 호환성

**실험용 알파 버전이며 실기기 검증은 미완료입니다.** 대상: Pixel 10a, Android 17 / API 37, `CP2A.260805.005`, Pixel Launcher 17 (`907`), Magisk, LSPosed 2.2.0 (7854). [검증 기록](../TESTING.md)의 APK 지문과 정확히 일치해야 합니다. 다른 버전에서는 어댑터를 끄고 이유를 기록합니다. 빌드 성공이 기기 호환성을 보증하지는 않습니다.

<a id="installation"></a>

## 설치

[Releases](https://github.com/asadman1523/pixel-private-space-shortcuts/releases)의 APK를 기본 프로필에 설치합니다. LSPosed에서 **Pixel Private Space Shortcuts**를 켜고 범위를 `com.google.android.apps.nexuslauncher`만 선택한 뒤 Launcher나 기기를 재시작합니다. CI debug APK는 테스트용이며 서명 키가 다를 수 있습니다.

안내 페이지에 **LSPosed 열기** 버튼이 있습니다. 별도 관리자는 바로 열리며 내장 관리자는 처음에 Magisk 승인이 필요합니다. 이 권한은 버튼을 누를 때만 사용하고 비공개 앱 실행에는 사용하지 않습니다.

<a id="usage"></a>

## 사용

비공개 공간을 해제하고 앱을 길게 눌러 홈 화면에 추가하거나 직접 드래그합니다. 추천 행의 비공개 앱도 같은 방식으로 추가할 수 있습니다. 홈 아이콘을 누르면 같은 비공개 앱이 열리며 필요하면 Android 인증 화면에서 인증합니다. 취소하면 해당 실행 요청을 버립니다. 홈 아이콘을 길게 눌러 이동, 폴더 배치, 삭제할 수 있습니다. 중복 추가 시 이미 존재한다는 안내를 표시합니다. 모듈 안내 앱을 비공개 공간에도 설치하여 민감한 정보 없는 테스트 앱으로 사용할 수 있습니다.

<a id="build"></a>

## 빌드

JDK 17, SDK `platforms;android-37.0`, Build Tools `36.0.0`, 포함된 Gradle wrapper를 사용하여 아래 명령을 실행합니다. Windows에서는 `gradlew.bat`를 사용합니다. CI는 빌드, 인증 상태 단위 테스트, Android lint, 모든 README 및 로컬 링크 검사를 실행합니다. 배포 서명은 [BUILDING](../BUILDING.md)의 네 가지 `PPSS_*` 환경 변수를 사용하며 키는 저장소 밖에 보관합니다.

```sh
python3 -X utf8 tools/check_docs.py
./gradlew testDebugUnitTest lintDebug assembleDebug
```

<a id="disable"></a>

## 비활성화 및 제거

Launcher에서 불필요한 바로가기를 지우고 LSPosed에서 모듈을 끈 뒤 Launcher를 재시작합니다. 이후 APK를 제거할 수 있습니다. Launcher 데이터를 지우지 마세요. 기존 항목은 기본 홈 항목이지만 모듈이 꺼지면 잠금 처리 기능은 제공되지 않습니다. 앱이나 프로필 삭제가 확인되면 Launcher 기본 정리를 따릅니다.

<a id="license"></a>

## 라이선스

[Apache-2.0](../../LICENSE). Google 및 LSPosed와 제휴하지 않습니다. Launcher APK, 역컴파일 파일, 기기 로그, 인증 정보, 서명 키를 배포하지 않습니다. [구현 설명](../ARCHITECTURE.md)과 [검증 기록](../TESTING.md)을 참고하세요.
