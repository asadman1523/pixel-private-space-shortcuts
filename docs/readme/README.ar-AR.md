# Pixel Private Space Shortcuts

اختصارات أصلية على الشاشة الرئيسية لتطبيقات المساحة الخاصة في Pixel باستخدام LSPosed.

[![Build](https://img.shields.io/github/actions/workflow/status/asadman1523/pixel-private-space-shortcuts/build.yml?branch=main&style=flat&logo=githubactions&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/actions/workflows/build.yml)
[![Release](https://img.shields.io/github/v/release/asadman1523/pixel-private-space-shortcuts?include_prereleases&style=flat&logo=github&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/releases)
[![Downloads](https://img.shields.io/github/downloads/asadman1523/pixel-private-space-shortcuts/total?style=flat&logo=github&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/releases)
[![Android](https://img.shields.io/badge/Android-17%20%2F%20API%2037%20experimental-orange?style=flat&logo=android&logoColor=white)](#compatibility)
[![License](https://img.shields.io/badge/License-Apache--2.0-blue?style=flat&logo=apache&logoColor=white)](../../LICENSE)

Read this in other languages: [English](../../README.md), [简体中文](README.zh-CN.md), [繁體中文](README.zh-TW.md), [한국어](README.ko-KR.md), [日本語](README.ja-JP.md), [Polski](README.pl-PL.md), [Français](README.fr-FR.md), [Español](README.es-ES.md), [Português](README.pt-BR.md), [Русский](README.ru-RU.md), [Türkçe](README.tr-TR.md), [Italiano](README.it-IT.md), [Bahasa Indonesia](README.id-ID.md), [Українська](README.uk-UA.md), **العربية**, [Tiếng Việt](README.vi-VN.md), [Deutsch](README.de-DE.md), [Uzbek](README.uz-UZ.md), [עברית](README.he-IL.md)

سيُسجّل العرض بعد التحقق على الجهاز. لن تُعرض محاكاة على أنها نتيجة اختبار حقيقي.

<a id="features"></a>

## الميزات

اضغط مطولاً على تطبيق في المساحة الخاصة المفتوحة واختر **إضافة إلى الشاشة الرئيسية**. تستخدم الوحدة مواضع Pixel Launcher وقاعدة بياناته وأيقوناته وشارة القفل الأصلية. يُحدَّد الهدف بالرقم التسلسلي للملف الشخصي ومكوّن التشغيل لمنع التكرار. تدعم النقل والمجلدات والإزالة.

صُمّم الاختصار ليحتفظ بموضعه عند القفل ويطلب مصادقة النظام عند الضغط. إذا كانت المساحة مفتوحة، يشغّل النسخة الخاصة مباشرة. يُنفَّذ الطلب مرة واحدة ويُمسح عند الإلغاء أو انتهاء المهلة أو تدمير Launcher. لا ينتقل إلى نسخة الملف الرئيسي. التعديلات تخص عناصر الوحدة فقط؛ لا تشمل الأدوات أو اختصارات التطبيق الداخلية. يبقى اسم التطبيق وأيقونته ظاهرين أثناء القفل.

تحتفظ الاختصارات المقفلة بألوانها الأصلية وشارة القفل. تتبع صفحة المعلومات وضع النظام الفاتح أو الداكن بلوحة ثابتة من الأسود والأبيض والرمادي.

<a id="compatibility"></a>

## التوافق

**نسخة ألفا تجريبية؛ التحقق على الجهاز غير مكتمل.** Pixel 10a، Android 17 / API 37، `CP2A.260805.005`، Pixel Launcher 17 (`907`)، Magisk وLSPosed 2.2.0 (7854). يجب تطابق بصمة APK مع [سجل التحقق](../TESTING.md). تُعطّل الإصدارات الأخرى المهايئ وتسجل السبب. نجاح البناء لا يثبت التوافق.

<a id="installation"></a>

## التثبيت

ثبّت APK من [الإصدارات](https://github.com/asadman1523/pixel-private-space-shortcuts/releases) في الملف الرئيسي. فعّل **Pixel Private Space Shortcuts** في LSPosed وحدد `com.google.android.apps.nexuslauncher` فقط ثم أعد تشغيل Launcher أو الهاتف. ملفات debug من CI للاختبار وقد تحمل توقيعاً مختلفاً.

<a id="usage"></a>

## الاستخدام

افتح المساحة الخاصة، واضغط مطولاً على التطبيق وأضفه إلى الشاشة الرئيسية. اضغط الأيقونة لفتح النسخة الخاصة نفسها وصادق عبر Android عند الحاجة. الإلغاء يتخلى عن الطلب. اضغط مطولاً على الأيقونة لنقلها أو وضعها في مجلد أو إزالتها. الإضافة مجدداً تعرض تنبيهاً بوجود الاختصار. يمكن تثبيت تطبيق معلومات الوحدة في المساحة الخاصة للاختبار دون بيانات حساسة.

<a id="build"></a>

## البناء

استخدم JDK 17 وSDK `platforms;android-37.0` وBuild Tools `36.0.0` وGradle wrapper المرفق. نفّذ الأوامر أدناه؛ في Windows استخدم `gradlew.bat`. يبني CI المشروع ويختبر حالات فتح القفل ويشغّل Android lint ويتحقق من جميع ملفات README والروابط المحلية. متغيرات `PPSS_*` الأربعة في [BUILDING](../BUILDING.md) تضبط التوقيع؛ احفظ المفاتيح خارج المستودع.

```sh
python3 -X utf8 tools/check_docs.py
./gradlew testDebugUnitTest lintDebug assembleDebug
```

<a id="disable"></a>

## التعطيل والإزالة

أزل الاختصارات غير المرغوبة عبر Launcher، وعطّل الوحدة في LSPosed وأعد تشغيل Launcher، ثم أزل APK إن أردت. لا تمسح بيانات Launcher. العناصر الموجودة أصلية، لكن معالجة القفل الخاصة بالوحدة تصبح غير متاحة. الحذف المؤكد للتطبيق أو الملف الشخصي يستخدم التنظيف الأصلي.

<a id="license"></a>

## الترخيص

[Apache-2.0](../../LICENSE). لا ارتباط بـGoogle أو LSPosed. لا نوزع APK الخاص بـGoogle أو الملفات المفككة أو سجلات الجهاز أو بيانات الاعتماد أو المفاتيح. راجع [البنية](../ARCHITECTURE.md) و[سجل التحقق](../TESTING.md).
