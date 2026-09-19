# Pixel Private Space Shortcuts

קיצורי דרך מקוריים במסך הבית לאפליקציות המרחב הפרטי ב־Pixel, באמצעות LSPosed.

[![Build](https://img.shields.io/github/actions/workflow/status/asadman1523/pixel-private-space-shortcuts/build.yml?branch=main&style=for-the-badge&logo=githubactions&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/actions/workflows/build.yml)
[![Android](https://img.shields.io/badge/Android-17%20%2F%20API%2037%20experimental-orange?style=for-the-badge&logo=android&logoColor=white)](#compatibility)
[![License](https://img.shields.io/badge/License-Apache--2.0-blue?style=for-the-badge&logo=apache&logoColor=white)](LICENSE)

Read this in other languages: [English](README.md), [简体中文](README.zh-CN.md), [繁體中文](README.zh-TW.md), [한국어](README.ko-KR.md), [日本語](README.ja-JP.md), [Polski](README.pl-PL.md), [Français](README.fr-FR.md), [Español](README.es-ES.md), [Português](README.pt-BR.md), [Русский](README.ru-RU.md), [Türkçe](README.tr-TR.md), [Italiano](README.it-IT.md), [Bahasa Indonesia](README.id-ID.md), [Українська](README.uk-UA.md), [العربية](README.ar-AR.md), [Tiếng Việt](README.vi-VN.md), [Deutsch](README.de-DE.md), [Uzbek](README.uz-UZ.md), **עברית**

ההדגמה תוקלט לאחר בדיקה במכשיר. הדמיה לא תוצג כתוצאת בדיקה אמיתית.

<a id="features"></a>

## תכונות

לחצו לחיצה ארוכה על אפליקציה במרחב פרטי פתוח ובחרו **הוספה למסך הבית**. המודול משתמש במיקום, במסד הנתונים, בסמלים ובסימון המנעול של Pixel Launcher. המספר הסידורי של הפרופיל ורכיב ההפעלה מזהים את היעד ומונעים כפילויות. נתמכים הזזה, תיקיות והסרה.

בעת נעילה קיצור הדרך נועד לשמור על מיקומו ולבקש אימות מערכת בלחיצה. כשהמרחב פתוח, העותק הפרטי נפתח ישירות. הבקשה מתבצעת פעם אחת ונמחקת בביטול, בתום הזמן או בהשמדת Launcher. אין מעבר חלופי לעותק הראשי. רק פריטים שהמודול יצר משתנים; ללא ווידג׳טים או קיצורים פנימיים. שם האפליקציה והסמל גלויים גם בזמן נעילה.

<a id="compatibility"></a>

## תאימות

**גרסת אלפא ניסיונית; בדיקת המכשיר אינה מלאה.** Pixel 10a, Android 17 / API 37, `CP2A.260805.005`, Pixel Launcher 17 (`907`), Magisk ו־LSPosed 2.2.0 (7854). נדרשת טביעת APK זהה ל[רישום הבדיקות](docs/TESTING.md). גרסאות אחרות משביתות את המתאם ומתעדות את הסיבה. בנייה מוצלחת אינה מוכיחה תאימות.

<a id="installation"></a>

## התקנה

התקינו את APK מ[ההפצות](https://github.com/asadman1523/pixel-private-space-shortcuts/releases) בפרופיל הראשי. הפעילו **Pixel Private Space Shortcuts** ב־LSPosed, בחרו רק `com.google.android.apps.nexuslauncher` והפעילו מחדש את Launcher או את הטלפון. קובצי debug מ־CI מיועדים לבדיקה ועשויים לשאת חתימה שונה.

<a id="usage"></a>

## שימוש

פתחו את המרחב הפרטי, לחצו ארוכות על אפליקציה והוסיפו למסך הבית. לחיצה על הסמל פותחת את אותו עותק פרטי; בצעו אימות דרך Android לפי הצורך. ביטול משליך את הבקשה. לחיצה ארוכה מאפשרת הזזה, הכנסה לתיקייה או הסרה. הוספה חוזרת מודיעה שהקיצור קיים. אפשר להתקין את אפליקציית המידע של המודול במרחב הפרטי לבדיקה ללא מידע רגיש.

<a id="build"></a>

## בנייה

השתמשו ב־JDK 17, ב־SDK `platforms;android-37.0`, ב־Build Tools `36.0.0` וב־Gradle wrapper הכלול. הריצו את הפקודות להלן (`gradlew.bat` ב־Windows). CI בונה, בודק מצבי פתיחת נעילה, מריץ Android lint ובודק את כל קובצי README והקישורים המקומיים. ארבעת משתני `PPSS_*` ב[BUILDING](docs/BUILDING.md) מגדירים חתימה; שמרו מפתחות מחוץ למאגר.

```sh
python3 -X utf8 tools/check_docs.py
./gradlew testDebugUnitTest lintDebug assembleDebug
```

<a id="disable"></a>

## השבתה או הסרה

הסירו קיצורים לא רצויים דרך Launcher, השביתו את המודול ב־LSPosed והפעילו מחדש את Launcher; לאחר מכן אפשר להסיר את APK. אל תמחקו את נתוני Launcher. הפריטים קיימים כפריטים מקוריים, אך טיפול הנעילה של המודול אינו זמין כשהוא מושבת. מחיקה מאומתת של אפליקציה או פרופיל משתמשת בניקוי המקורי.

<a id="license"></a>

## רישיון

[Apache-2.0](LICENSE). ללא קשר ל־Google או ל־LSPosed. אין הפצת APK של Google, קבצים שעברו פירוק, יומני מכשיר, פרטי אימות או מפתחות. ראו [ארכיטקטורה](docs/ARCHITECTURE.md) ו[בדיקות](docs/TESTING.md).
