# Pixel Private Space Shortcuts

Нативные ярлыки приложений личного пространства Pixel на главном экране через LSPosed.

[![Build](https://img.shields.io/github/actions/workflow/status/asadman1523/pixel-private-space-shortcuts/build.yml?branch=main&style=flat&logo=githubactions&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/actions/workflows/build.yml)
[![Release](https://img.shields.io/github/v/release/asadman1523/pixel-private-space-shortcuts?include_prereleases&style=flat&logo=github&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/releases)
[![Downloads](https://img.shields.io/github/downloads/asadman1523/pixel-private-space-shortcuts/total?style=flat&logo=github&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/releases)
[![Android](https://img.shields.io/badge/Android-17%20%2F%20API%2037%20experimental-orange?style=flat&logo=android&logoColor=white)](#compatibility)
[![License](https://img.shields.io/badge/License-Apache--2.0-blue?style=flat&logo=apache&logoColor=white)](../../LICENSE)

Read this in other languages: [English](../../README.md), [简体中文](README.zh-CN.md), [繁體中文](README.zh-TW.md), [한국어](README.ko-KR.md), [日本語](README.ja-JP.md), [Polski](README.pl-PL.md), [Français](README.fr-FR.md), [Español](README.es-ES.md), [Português](README.pt-BR.md), **Русский**, [Türkçe](README.tr-TR.md), [Italiano](README.it-IT.md), [Bahasa Indonesia](README.id-ID.md), [Українська](README.uk-UA.md), [العربية](README.ar-AR.md), [Tiếng Việt](README.vi-VN.md), [Deutsch](README.de-DE.md), [Uzbek](README.uz-UZ.md), [עברית](README.he-IL.md)

Демонстрация будет записана после проверки на устройстве. Имитации не выдаются за результаты испытаний.

<a id="features"></a>

## Возможности

Удерживайте приложение в разблокированном личном пространстве и выберите **Добавить на главный экран** или перетащите его прямо на главный экран. Это также работает для личных приложений в строке предложений вверху списка всех приложений. Используются размещение, база данных, значки и отметка замка Pixel Launcher. Серийный номер профиля и компонент запуска определяют цель и предотвращают дублирование. Поддерживаются перемещение, папки и удаление.

При блокировке ярлык должен сохранять положение и запрашивать системную аутентификацию. После разблокировки открывается именно личная копия. Запрос выполняется один раз и сбрасывается при отмене, истечении времени или уничтожении Launcher. Перехода к основной копии нет. Изменяются только элементы модуля; виджеты и внутренние ярлыки не входят в выпуск. Название и значок видны даже при блокировке.

При блокировке сохраняются исходные цвета и нативная отметка замка. Информационная страница следует светлой или тёмной теме системы с фиксированной чёрно-бело-серой палитрой.

<a id="compatibility"></a>

## Совместимость

**Экспериментальная альфа; проверка на устройстве не завершена.** Рассчитано на устройства Pixel с Android 15+ (API 35+), но **в настоящее время протестировано только на Android 17** (Pixel 10a, API 37, `CP2A.260805.005`, Pixel Launcher 17). Адаптер попытается загрузиться на Android 15 и 16, но обновления могут нарушить работу внутренних хуков. Успешная сборка не является доказательством совместимости.

<a id="installation"></a>

## Установка

Установите APK из [выпусков](https://github.com/asadman1523/pixel-private-space-shortcuts/releases) в основной профиль. Включите **Pixel Private Space Shortcuts** в LSPosed, выберите только `com.google.android.apps.nexuslauncher` и перезапустите Launcher или телефон. Тестовые debug APK из CI могут иметь другую подпись.

На странице есть кнопка **Открыть LSPosed**. Отдельный менеджер открывается напрямую; встроенный впервые требует разрешения Magisk. Оно используется только по нажатию кнопки, не для запуска личных приложений.

<a id="usage"></a>

## Использование

Разблокируйте личное пространство, удерживайте приложение и выберите **Добавить на главный экран** или перетащите его на главный экран. Личные приложения из строки предложений можно добавить точно так же. Нажатие открывает ту же личную копию; при необходимости пройдите проверку Android. Отмена сбрасывает запрос. Удерживайте значок для перемещения, помещения в папку или удаления. Повторное добавление сообщает о существующем ярлыке. Информационное приложение модуля можно установить в личное пространство для проверки без конфиденциальных данных.

<a id="build"></a>

## Сборка

Используйте JDK 17, SDK `platforms;android-37.0`, Build Tools `36.0.0` и включённый Gradle wrapper. Команды ниже, в Windows — `gradlew.bat`. CI выполняет сборку, тесты состояний разблокировки, Android lint и проверку всех README и локальных ссылок. Четыре переменные `PPSS_*` из [BUILDING](../BUILDING.md) задают подпись; ключи храните вне репозитория.

```sh
python3 -X utf8 tools/check_docs.py
./gradlew testDebugUnitTest lintDebug assembleDebug
```

<a id="disable"></a>

## Отключение и удаление

Удалите ненужные ярлыки через Launcher, отключите модуль в LSPosed и перезапустите Launcher; затем можно удалить APK. Не очищайте данные Launcher. Существующие элементы нативные, но обработка блокировки модулем станет недоступна. Подтверждённое удаление приложения или профиля обрабатывается штатной очисткой.

<a id="license"></a>

## Лицензия

[Apache-2.0](../../LICENSE). Не связан с Google или LSPosed. APK Google, декомпилированные файлы, журналы устройства, учётные данные и ключи не распространяются. См. [архитектуру](../ARCHITECTURE.md) и [проверки](../TESTING.md).

[Политика конфиденциальности (на английском)](../../PRIVACY_POLICY.md)
