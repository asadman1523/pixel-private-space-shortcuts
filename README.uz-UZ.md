# Pixel Private Space Shortcuts

LSPosed orqali Pixel Maxfiy makon ilovalari uchun asl bosh ekran yorliqlari.

[![Build](https://img.shields.io/github/actions/workflow/status/asadman1523/pixel-private-space-shortcuts/build.yml?branch=main&style=for-the-badge&logo=githubactions&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/actions/workflows/build.yml)
[![Android](https://img.shields.io/badge/Android-17%20%2F%20API%2037%20experimental-orange?style=for-the-badge&logo=android&logoColor=white)](#compatibility)
[![License](https://img.shields.io/badge/License-Apache--2.0-blue?style=for-the-badge&logo=apache&logoColor=white)](LICENSE)

Read this in other languages: [English](README.md), [简体中文](README.zh-CN.md), [繁體中文](README.zh-TW.md), [한국어](README.ko-KR.md), [日本語](README.ja-JP.md), [Polski](README.pl-PL.md), [Français](README.fr-FR.md), [Español](README.es-ES.md), [Português](README.pt-BR.md), [Русский](README.ru-RU.md), [Türkçe](README.tr-TR.md), [Italiano](README.it-IT.md), [Bahasa Indonesia](README.id-ID.md), [Українська](README.uk-UA.md), [العربية](README.ar-AR.md), [Tiếng Việt](README.vi-VN.md), [Deutsch](README.de-DE.md), **Uzbek**, [עברית](README.he-IL.md)

Namoyish qurilmada tekshirilgandan keyin yoziladi. Taqlid haqiqiy sinov natijasi sifatida ko‘rsatilmaydi.

<a id="features"></a>

## Imkoniyatlar

Qulfi ochiq Maxfiy makondagi ilovani bosib turing va **Bosh ekranga qo‘shish** ni tanlang. Modul Pixel Launcher joylashtirishi, ma’lumotlar bazasi, belgilar va qulf nishonidan foydalanadi. Profil seriya raqami hamda ishga tushirish komponenti manzilni aniqlaydi va takrorlanishni oldini oladi. Ko‘chirish, jildlar va olib tashlash qo‘llanadi.

Qulflanganda yorliq joyini saqlashi va bosilganda tizim tasdiqlashini so‘rashi ko‘zda tutilgan. Qulf ochiq bo‘lsa, aynan maxfiy nusxa ochiladi. So‘rov bir marta bajariladi; bekor qilish, vaqt tugashi yoki Launcher yo‘q qilinishi uni tozalaydi. Asosiy profil nusxasiga o‘tilmaydi. Faqat modul yaratgan elementlar o‘zgaradi; vidjetlar va ilova ichki yorliqlari kiritilmagan. Qulf paytida ham ilova nomi va belgisi ko‘rinadi.

<a id="compatibility"></a>

## Moslik

**Tajribaviy alfa; qurilma tekshiruvi tugallanmagan.** Pixel 10a, Android 17 / API 37, `CP2A.260805.005`, Pixel Launcher 17 (`907`), Magisk, LSPosed 2.2.0 (7854). APK izi [qaydnoma](docs/TESTING.md) bilan aniq mos bo‘lishi shart. Boshqa versiyalarda adapter o‘chadi va sabab yoziladi. Muvaffaqiyatli yig‘ish qurilma mosligini isbotlamaydi.

<a id="installation"></a>

## O‘rnatish

[Relizlar](https://github.com/asadman1523/pixel-private-space-shortcuts/releases) APK faylini asosiy profilga o‘rnating. LSPosed’da **Pixel Private Space Shortcuts** ni yoqing, faqat `com.google.android.apps.nexuslauncher` ni tanlang va Launcher yoki telefonni qayta ishga tushiring. CI debug APK fayllari sinov uchun, imzosi farq qilishi mumkin.

<a id="usage"></a>

## Foydalanish

Maxfiy makon qulfini oching, ilovani bosib turing va bosh ekranga qo‘shing. Belgi aynan maxfiy nusxani ochadi; kerak bo‘lsa Android orqali tasdiqlang. Bekor qilish so‘rovni tashlaydi. Belgini bosib turib ko‘chiring, jildga joylang yoki o‘chiring. Qayta qo‘shish mavjud yorliq haqida xabar beradi. Modul ma’lumot ilovasini Maxfiy makonga maxfiy ma’lumotsiz sinov uchun o‘rnatish mumkin.

<a id="build"></a>

## Yig‘ish

JDK 17, SDK `platforms;android-37.0`, Build Tools `36.0.0` va berilgan Gradle wrapper bilan quyidagi buyruqlarni bajaring (Windows: `gradlew.bat`). CI yig‘adi, qulf holatlarini sinaydi, Android lint ishlatadi hamda barcha README va mahalliy havolalarni tekshiradi. [BUILDING](docs/BUILDING.md) dagi to‘rtta `PPSS_*` o‘zgaruvchi imzolashni sozlaydi; kalitlar repozitoriydan tashqarida saqlanadi.

```sh
python3 -X utf8 tools/check_docs.py
./gradlew testDebugUnitTest lintDebug assembleDebug
```

<a id="disable"></a>

## O‘chirish yoki olib tashlash

Keraksiz yorliqlarni Launcher orqali o‘chiring, modulni LSPosed’da o‘chirib Launcher’ni qayta ishga tushiring; keyin APK’ni olib tashlash mumkin. Launcher ma’lumotlarini tozalamang. Elementlar asl bo‘lib qoladi, ammo modulning qulf ishlovi o‘chadi. Ilova yoki profil o‘chirilgani tasdiqlansa, asl tozalash qo‘llanadi.

<a id="license"></a>

## Litsenziya

[Apache-2.0](LICENSE). Google yoki LSPosed bilan bog‘liq emas. Google APK fayllari, dekompilyatsiya fayllari, qurilma jurnallari, hisob ma’lumotlari va kalitlar tarqatilmaydi. [Arxitektura](docs/ARCHITECTURE.md) va [tekshiruvlar](docs/TESTING.md) bilan tanishing.
