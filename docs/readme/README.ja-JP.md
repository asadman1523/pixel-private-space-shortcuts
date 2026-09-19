# Pixel Private Space Shortcuts

LSPosed を使用し、Pixel のプライベートスペースのアプリを標準ホーム画面に追加します。

[![Build](https://img.shields.io/github/actions/workflow/status/asadman1523/pixel-private-space-shortcuts/build.yml?branch=main&style=flat&logo=githubactions&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/actions/workflows/build.yml)
[![Release](https://img.shields.io/github/v/release/asadman1523/pixel-private-space-shortcuts?include_prereleases&style=flat&logo=github&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/releases)
[![Downloads](https://img.shields.io/github/downloads/asadman1523/pixel-private-space-shortcuts/total?style=flat&logo=github&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/releases)
[![Android](https://img.shields.io/badge/Android-17%20%2F%20API%2037%20experimental-orange?style=flat&logo=android&logoColor=white)](#compatibility)
[![License](https://img.shields.io/badge/License-Apache--2.0-blue?style=flat&logo=apache&logoColor=white)](../../LICENSE)

Read this in other languages: [English](../../README.md), [简体中文](README.zh-CN.md), [繁體中文](README.zh-TW.md), [한국어](README.ko-KR.md), **日本語**, [Polski](README.pl-PL.md), [Français](README.fr-FR.md), [Español](README.es-ES.md), [Português](README.pt-BR.md), [Русский](README.ru-RU.md), [Türkçe](README.tr-TR.md), [Italiano](README.it-IT.md), [Bahasa Indonesia](README.id-ID.md), [Українська](README.uk-UA.md), [العربية](README.ar-AR.md), [Tiếng Việt](README.vi-VN.md), [Deutsch](README.de-DE.md), [Uzbek](README.uz-UZ.md), [עברית](README.he-IL.md)

実機検証後にデモを録画します。模擬画面を実測結果として掲載しません。

<a id="features"></a>

## 機能

ロック解除済みのプライベートスペースでアプリを長押しし、標準の「ホーム画面に追加」を選びます。Pixel Launcher の配置、データベース、アイコン、プライベートプロファイルの鍵バッジを使用します。プロファイルのシリアル番号と起動コンポーネントで対象を識別し、重複を防止します。移動、フォルダへの収納、削除に対応します。

ロック中も位置を保持し、タップするとシステム認証を求める設計です。解除済みなら同じプロファイルで直接開きます。待機中の起動は一度だけ実行し、キャンセル、タイムアウト、Launcher の破棄で消去します。メインプロファイルのアプリに切り替えません。変更対象は本モジュールが作成した項目のみで、ウィジェットやアプリ内ショートカットは含みません。ロック中もアプリ名とアイコンはホーム画面に表示されます。

ロック中も元の色と標準の鍵バッジを保ちます。案内ページは固定の白黒・グレー配色で、システムのライト／ダークモードに従います。

<a id="compatibility"></a>

## 互換性

**実験的アルファ版。実機検証は未完了です。** 対象：Pixel 10a、Android 17 / API 37、`CP2A.260805.005`、Pixel Launcher 17（`907`）、Magisk、LSPosed 2.2.0（7854）。[検証記録](../TESTING.md)の APK 指紋との完全一致が必要です。別のバージョンではアダプターを無効にし、理由を記録します。ビルド成功は実機互換性の証明ではありません。

<a id="installation"></a>

## インストール

[Releases](https://github.com/asadman1523/pixel-private-space-shortcuts/releases) の APK をメインプロファイルにインストールします。LSPosed で **Pixel Private Space Shortcuts** を有効にし、対象を `com.google.android.apps.nexuslauncher` のみに設定して Launcher または端末を再起動します。CI の debug APK はテスト用で、署名が異なる場合があります。

案内ページには **LSPosed を開く** ボタンがあります。独立したマネージャーは直接開き、内蔵マネージャーでは初回に Magisk の許可が必要です。この権限はボタン操作時だけ使用し、プライベートアプリの起動には使いません。

<a id="usage"></a>

## 使い方

プライベートスペースを解除し、アプリを長押ししてホーム画面に追加します。ホーム画面のアイコンをタップし、必要なら Android の認証画面で認証してください。キャンセルすると今回の起動を破棄します。長押しで移動、フォルダ収納、削除ができます。再追加すると既存の項目がある旨を通知します。モジュールの案内アプリをプライベートスペースに入れ、機密データのないテストアプリとして使用できます。

<a id="build"></a>

## ビルド

JDK 17、SDK `platforms;android-37.0`、Build Tools `36.0.0` と付属の Gradle wrapper を使用し、下記を実行します。Windows は `gradlew.bat` を使います。CI はビルド、認証状態の単体テスト、Android lint、全 README とローカルリンクの検査を行います。署名には [BUILDING](../BUILDING.md) の四つの `PPSS_*` 環境変数を使用し、鍵はリポジトリ外に保管します。

```sh
python3 -X utf8 tools/check_docs.py
./gradlew testDebugUnitTest lintDebug assembleDebug
```

<a id="disable"></a>

## 無効化・削除

不要なショートカットを Launcher で削除し、LSPosed でモジュールを無効化して Launcher を再起動します。その後 APK をアンインストールできます。Launcher のデータは消去しないでください。残った項目は標準のデスクトップ項目ですが、無効化中はモジュールのロック処理を利用できません。アプリやプロファイルの削除が確認された場合は標準の削除処理に従います。

<a id="license"></a>

## ライセンス

[Apache-2.0](../../LICENSE)。Google、LSPosed とは無関係です。Launcher APK、逆コンパイルしたファイル、端末ログ、認証情報、署名鍵は配布しません。[実装](../ARCHITECTURE.md)と[検証記録](../TESTING.md)も参照してください。
