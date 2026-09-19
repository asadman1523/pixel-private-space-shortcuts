# Pixel Private Space Shortcuts

Natywne skróty aplikacji Przestrzeni prywatnej Pixel na ekranie głównym dzięki LSPosed.

[![Build](https://img.shields.io/github/actions/workflow/status/asadman1523/pixel-private-space-shortcuts/build.yml?branch=main&style=for-the-badge&logo=githubactions&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/actions/workflows/build.yml)
[![Android](https://img.shields.io/badge/Android-17%20%2F%20API%2037%20experimental-orange?style=for-the-badge&logo=android&logoColor=white)](#compatibility)
[![License](https://img.shields.io/badge/License-Apache--2.0-blue?style=for-the-badge&logo=apache&logoColor=white)](../../LICENSE)

Read this in other languages: [English](../../README.md), [简体中文](README.zh-CN.md), [繁體中文](README.zh-TW.md), [한국어](README.ko-KR.md), [日本語](README.ja-JP.md), **Polski**, [Français](README.fr-FR.md), [Español](README.es-ES.md), [Português](README.pt-BR.md), [Русский](README.ru-RU.md), [Türkçe](README.tr-TR.md), [Italiano](README.it-IT.md), [Bahasa Indonesia](README.id-ID.md), [Українська](README.uk-UA.md), [العربية](README.ar-AR.md), [Tiếng Việt](README.vi-VN.md), [Deutsch](README.de-DE.md), [Uzbek](README.uz-UZ.md), [עברית](README.he-IL.md)

Nagranie demonstracyjne powstanie po weryfikacji urządzenia. Symulacje nie zastępują rzeczywistych testów.

<a id="features"></a>

## Funkcje

Przytrzymaj aplikację w odblokowanej Przestrzeni prywatnej i wybierz **Dodaj do ekranu głównego**. Moduł korzysta z układu, bazy danych, ikon i kłódki Pixel Launcher. Numer seryjny profilu i komponent uruchamiania określają cel oraz zapobiegają duplikatom. Obsługiwane są przenoszenie, foldery i usuwanie.

Po zablokowaniu skrót ma zachować pozycję i wywołać uwierzytelnianie systemowe po dotknięciu. Odblokowany profil otwiera się bezpośrednio. Żądanie wykonywane jest raz, a anulowanie, przekroczenie czasu lub zniszczenie Launchera usuwa je. Nigdy nie uruchamia zastępczo kopii głównej. Zmiany dotyczą tylko elementów modułu; bez widżetów i skrótów wewnątrz aplikacji. Nazwa i ikona pozostają widoczne również po zablokowaniu.

Zablokowane skróty zachowują oryginalne kolory i natywną kłódkę. Strona informacyjna używa stałej palety czerni, bieli i szarości oraz systemowego jasnego lub ciemnego motywu.

<a id="compatibility"></a>

## Zgodność

**Eksperymentalna alfa; weryfikacja urządzenia niepełna.** Pixel 10a, Android 17 / API 37, `CP2A.260805.005`, Pixel Launcher 17 (`907`), Magisk i LSPosed 2.2.0 (7854). Wymagany dokładny odcisk APK z [raportu](../TESTING.md). Inne wersje wyłączają adapter i zapisują przyczynę. Udana kompilacja nie dowodzi zgodności.

<a id="installation"></a>

## Instalacja

Zainstaluj APK z [wydań](https://github.com/asadman1523/pixel-private-space-shortcuts/releases) w profilu głównym. Włącz **Pixel Private Space Shortcuts** w LSPosed, wybierz tylko `com.google.android.apps.nexuslauncher` i uruchom ponownie Launcher lub telefon. Testowe APK debug z CI mogą mieć inny podpis.

<a id="usage"></a>

## Użycie

Odblokuj Przestrzeń prywatną, przytrzymaj aplikację i dodaj ją do ekranu głównego. Dotknij ikony, aby otworzyć tę samą prywatną kopię; w razie potrzeby uwierzytelnij się w Androidzie. Anulowanie porzuca żądanie. Przytrzymanie ikony pozwala przenosić, dodawać do folderu i usuwać. Ponowna próba dodania wyświetla komunikat o istniejącym skrócie. Aplikacja informacyjna modułu może służyć jako test bez danych wrażliwych w profilu prywatnym.

<a id="build"></a>

## Kompilacja

Użyj JDK 17, SDK `platforms;android-37.0`, Build Tools `36.0.0` i dołączonego wrappera Gradle. Wykonaj polecenia poniżej (`gradlew.bat` w Windows). CI kompiluje, testuje stany odblokowania, uruchamia Android lint i sprawdza wszystkie README oraz lokalne odnośniki. Cztery zmienne `PPSS_*` z [BUILDING](../BUILDING.md) konfigurują podpis; klucze przechowuj poza repozytorium.

```sh
python3 -X utf8 tools/check_docs.py
./gradlew testDebugUnitTest lintDebug assembleDebug
```

<a id="disable"></a>

## Wyłączanie i usuwanie

Usuń zbędne skróty w Launcherze, wyłącz moduł w LSPosed i uruchom Launcher ponownie, a następnie opcjonalnie odinstaluj APK. Nie czyść danych Launchera. Istniejące elementy są natywne, lecz obsługa blokady przez moduł nie działa po wyłączeniu. Potwierdzone usunięcie aplikacji lub profilu podlega natywnemu czyszczeniu.

<a id="license"></a>

## Licencja

[Apache-2.0](../../LICENSE). Brak powiązań z Google i LSPosed. Bez dystrybucji APK Google, zdekompilowanych plików, dzienników urządzenia, danych logowania ani kluczy. Zobacz [architekturę](../ARCHITECTURE.md) i [raport](../TESTING.md).
