# Pixel Private Space Shortcuts

Native Startbildschirm-Verknüpfungen für Apps im privaten Bereich von Pixel, mit LSPosed.

[![Build](https://img.shields.io/github/actions/workflow/status/asadman1523/pixel-private-space-shortcuts/build.yml?branch=main&style=flat&logo=githubactions&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/actions/workflows/build.yml)
[![Release](https://img.shields.io/github/v/release/asadman1523/pixel-private-space-shortcuts?include_prereleases&style=flat&logo=github&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/releases)
[![Downloads](https://img.shields.io/github/downloads/asadman1523/pixel-private-space-shortcuts/total?style=flat&logo=github&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/releases)
[![Android](https://img.shields.io/badge/Android-17%20%2F%20API%2037%20experimental-orange?style=flat&logo=android&logoColor=white)](#compatibility)
[![License](https://img.shields.io/badge/License-Apache--2.0-blue?style=flat&logo=apache&logoColor=white)](../../LICENSE)

Read this in other languages: [English](../../README.md), [简体中文](README.zh-CN.md), [繁體中文](README.zh-TW.md), [한국어](README.ko-KR.md), [日本語](README.ja-JP.md), [Polski](README.pl-PL.md), [Français](README.fr-FR.md), [Español](README.es-ES.md), [Português](README.pt-BR.md), [Русский](README.ru-RU.md), [Türkçe](README.tr-TR.md), [Italiano](README.it-IT.md), [Bahasa Indonesia](README.id-ID.md), [Українська](README.uk-UA.md), [العربية](README.ar-AR.md), [Tiếng Việt](README.vi-VN.md), **Deutsch**, [Uzbek](README.uz-UZ.md), [עברית](README.he-IL.md)

Die Vorführung wird nach der Geräteprüfung aufgenommen. Simulationen werden nicht als echte Testergebnisse dargestellt.

<a id="features"></a>

## Funktionen

Eine App im entsperrten privaten Bereich lange drücken und **Zum Startbildschirm hinzufügen** wählen. Das Modul verwendet Platzierung, Datenbank, Symbole und Schlosskennzeichnung von Pixel Launcher. Profilseriennummer und Startkomponente bestimmen das Ziel und verhindern Duplikate. Verschieben, Ordner und Entfernen werden unterstützt.

Bei gesperrtem Profil soll die Position erhalten bleiben; Antippen fordert die Systemauthentifizierung an. Entsperrt öffnet sich dieselbe private App direkt. Jede Anfrage wird einmal ausgeführt und bei Abbruch, Zeitablauf oder Zerstörung des Launchers gelöscht. Kein Ausweichen auf das Hauptprofil. Nur vom Modul erstellte Einträge werden geändert; keine Widgets oder internen App-Verknüpfungen. Name und Symbol bleiben auch bei gesperrtem privaten Bereich sichtbar.

Gesperrte Verknüpfungen behalten ihre Farben und das native Schloss. Die Informationsseite folgt dem hellen oder dunklen Systemmodus mit einer festen Palette aus Schwarz, Weiß und Grau.

<a id="compatibility"></a>

## Kompatibilität

**Experimentelle Alpha; Geräteprüfung unvollständig.** Ziel: Pixel 10a, Android 17 / API 37, `CP2A.260805.005`, Pixel Launcher 17 (`907`), Magisk, LSPosed 2.2.0 (7854). Der APK-Fingerabdruck muss dem [Prüfprotokoll](../TESTING.md) entsprechen. Andere Versionen deaktivieren den Adapter mit protokollierter Begründung. Ein erfolgreicher Build beweist keine Gerätekompatibilität.

<a id="installation"></a>

## Installation

APK aus den [Veröffentlichungen](https://github.com/asadman1523/pixel-private-space-shortcuts/releases) im Hauptprofil installieren. **Pixel Private Space Shortcuts** in LSPosed aktivieren, ausschließlich `com.google.android.apps.nexuslauncher` auswählen und Launcher oder Gerät neu starten. CI-Debug-APKs sind Testdateien und können anders signiert sein.

Die Seite bietet **LSPosed öffnen**. Ein eigenständiger Manager öffnet direkt; der integrierte Manager benötigt zunächst eine Magisk-Freigabe. Die Berechtigung wird nur für diesen Knopf verwendet, nicht zum Öffnen privater Apps.

<a id="usage"></a>

## Verwendung

Privaten Bereich entsperren, App lange drücken und zum Startbildschirm hinzufügen. Das Symbol öffnet dieselbe private Kopie; bei Bedarf in Android authentifizieren. Abbrechen verwirft die Anfrage. Langes Drücken ermöglicht Verschieben, Ordner und Entfernen. Erneutes Hinzufügen meldet den vorhandenen Eintrag. Die Informations-App des Moduls eignet sich auch im privaten Bereich als Test ohne sensible Daten.

<a id="build"></a>

## Bauen

JDK 17, SDK `platforms;android-37.0`, Build Tools `36.0.0` und den enthaltenen Gradle-Wrapper verwenden. Befehle unten ausführen, unter Windows `gradlew.bat`. CI baut, testet den Entsperrzustand, führt Android lint aus und prüft alle README-Dateien sowie lokale Links. Vier `PPSS_*`-Variablen aus [BUILDING](../BUILDING.md) steuern die Signatur; Schlüssel bleiben außerhalb des Repositorys.

```sh
python3 -X utf8 tools/check_docs.py
./gradlew testDebugUnitTest lintDebug assembleDebug
```

<a id="disable"></a>

## Deaktivieren und entfernen

Unerwünschte Verknüpfungen im Launcher entfernen, Modul in LSPosed deaktivieren und Launcher neu starten; anschließend bei Bedarf APK deinstallieren. Launcher-Daten nicht löschen. Einträge bleiben native Elemente, die Sperrbehandlung des Moduls entfällt jedoch. Bestätigte App- oder Profillöschung nutzt die native Bereinigung.

<a id="license"></a>

## Lizenz

[Apache-2.0](../../LICENSE). Keine Verbindung zu Google oder LSPosed. Keine Verteilung von Google-APKs, dekompilierten Dateien, Geräteprotokollen, Zugangsdaten oder Schlüsseln. Siehe [Architektur](../ARCHITECTURE.md) und [Prüfprotokoll](../TESTING.md).
