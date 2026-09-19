# Pixel Private Space Shortcuts

Нативні ярлики застосунків приватного простору Pixel на головному екрані за допомогою LSPosed.

[![Build](https://img.shields.io/github/actions/workflow/status/asadman1523/pixel-private-space-shortcuts/build.yml?branch=main&style=flat&logo=githubactions&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/actions/workflows/build.yml)
[![Android](https://img.shields.io/badge/Android-17%20%2F%20API%2037%20experimental-orange?style=flat&logo=android&logoColor=white)](#compatibility)
[![License](https://img.shields.io/badge/License-Apache--2.0-blue?style=flat&logo=apache&logoColor=white)](../../LICENSE)

Read this in other languages: [English](../../README.md), [简体中文](README.zh-CN.md), [繁體中文](README.zh-TW.md), [한국어](README.ko-KR.md), [日本語](README.ja-JP.md), [Polski](README.pl-PL.md), [Français](README.fr-FR.md), [Español](README.es-ES.md), [Português](README.pt-BR.md), [Русский](README.ru-RU.md), [Türkçe](README.tr-TR.md), [Italiano](README.it-IT.md), [Bahasa Indonesia](README.id-ID.md), **Українська**, [العربية](README.ar-AR.md), [Tiếng Việt](README.vi-VN.md), [Deutsch](README.de-DE.md), [Uzbek](README.uz-UZ.md), [עברית](README.he-IL.md)

Демонстрацію буде записано після перевірки пристрою. Імітація не видається за справжні результати.

<a id="features"></a>

## Можливості

Утримуйте застосунок у розблокованому приватному просторі й виберіть **Додати на головний екран**. Використовуються розміщення, база даних, піктограми та замок Pixel Launcher. Серійний номер профілю й компонент запуску визначають ціль і запобігають дублюванню. Підтримуються переміщення, папки та видалення.

Після блокування ярлик має зберігати позицію та запитувати системну автентифікацію. Розблокований профіль відкривається безпосередньо. Запит виконується один раз і скидається після скасування, завершення часу або знищення Launcher. Основна копія не використовується замість приватної. Змінюються лише елементи модуля; без віджетів і внутрішніх ярликів. Назва й піктограма видимі навіть у заблокованому стані.

Після блокування зберігаються початкові кольори й нативний замок. Інформаційна сторінка слідує світлій або темній темі системи зі сталою чорно-біло-сірою палітрою.

<a id="compatibility"></a>

## Сумісність

**Експериментальна альфа; перевірку пристрою не завершено.** Pixel 10a, Android 17 / API 37, `CP2A.260805.005`, Pixel Launcher 17 (`907`), Magisk, LSPosed 2.2.0 (7854). Потрібен точний відбиток APK із [протоколу](../TESTING.md). Інші версії вимикають адаптер із записом причини. Успішна збірка не доводить сумісність.

<a id="installation"></a>

## Встановлення

Встановіть APK із [випусків](https://github.com/asadman1523/pixel-private-space-shortcuts/releases) в основний профіль. У LSPosed увімкніть **Pixel Private Space Shortcuts**, виберіть лише `com.google.android.apps.nexuslauncher`, перезапустіть Launcher або телефон. Тестові debug APK з CI можуть мати інший підпис.

<a id="usage"></a>

## Використання

Розблокуйте приватний простір, утримуйте застосунок і додайте його на головний екран. Натискання відкриває ту саму приватну копію; за потреби пройдіть перевірку Android. Скасування відхиляє запит. Утримуйте піктограму для переміщення, папки або видалення. Повторне додавання повідомляє про наявний ярлик. Інформаційний застосунок модуля можна встановити в приватному просторі як тест без чутливих даних.

<a id="build"></a>

## Збірка

Використовуйте JDK 17, SDK `platforms;android-37.0`, Build Tools `36.0.0` і наданий Gradle wrapper. Виконайте команди нижче (`gradlew.bat` у Windows). CI збирає, тестує стани розблокування, запускає Android lint і перевіряє всі README та локальні посилання. Чотири змінні `PPSS_*` з [BUILDING](../BUILDING.md) налаштовують підпис; ключі зберігайте поза репозиторієм.

```sh
python3 -X utf8 tools/check_docs.py
./gradlew testDebugUnitTest lintDebug assembleDebug
```

<a id="disable"></a>

## Вимкнення й видалення

Видаліть непотрібні ярлики через Launcher, вимкніть модуль у LSPosed і перезапустіть Launcher; після цього можна видалити APK. Не очищуйте дані Launcher. Наявні елементи нативні, але обробка блокування модулем буде недоступна. Підтверджене видалення застосунку або профілю використовує штатне очищення.

<a id="license"></a>

## Ліцензія

[Apache-2.0](../../LICENSE). Без зв’язку з Google або LSPosed. APK Google, декомпільовані файли, журнали пристрою, облікові дані та ключі не поширюються. Див. [архітектуру](../ARCHITECTURE.md) й [перевірки](../TESTING.md).
