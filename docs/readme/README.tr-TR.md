# Pixel Private Space Shortcuts

LSPosed ile Pixel Özel Alan uygulamaları için yerel ana ekran kısayolları.

[![Build](https://img.shields.io/github/actions/workflow/status/asadman1523/pixel-private-space-shortcuts/build.yml?branch=main&style=flat&logo=githubactions&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/actions/workflows/build.yml)
[![Release](https://img.shields.io/github/v/release/asadman1523/pixel-private-space-shortcuts?include_prereleases&style=flat&logo=github&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/releases)
[![Downloads](https://img.shields.io/github/downloads/asadman1523/pixel-private-space-shortcuts/total?style=flat&logo=github&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/releases)
[![Android](https://img.shields.io/badge/Android-17%20%2F%20API%2037%20experimental-orange?style=flat&logo=android&logoColor=white)](#compatibility)
[![License](https://img.shields.io/badge/License-Apache--2.0-blue?style=flat&logo=apache&logoColor=white)](../../LICENSE)

Read this in other languages: [English](../../README.md), [简体中文](README.zh-CN.md), [繁體中文](README.zh-TW.md), [한국어](README.ko-KR.md), [日本語](README.ja-JP.md), [Polski](README.pl-PL.md), [Français](README.fr-FR.md), [Español](README.es-ES.md), [Português](README.pt-BR.md), [Русский](README.ru-RU.md), **Türkçe**, [Italiano](README.it-IT.md), [Bahasa Indonesia](README.id-ID.md), [Українська](README.uk-UA.md), [العربية](README.ar-AR.md), [Tiếng Việt](README.vi-VN.md), [Deutsch](README.de-DE.md), [Uzbek](README.uz-UZ.md), [עברית](README.he-IL.md)

Tanıtım, cihaz doğrulamasından sonra kaydedilecek. Benzetimler gerçek test sonucu olarak sunulmaz.

<a id="features"></a>

## Özellikler

Kilidi açık Özel Alan’da uygulamaya uzun basıp **Ana ekrana ekle** seçeneğini kullanın veya doğrudan Ana ekrana sürükleyin. Bu, Tüm Uygulamalar’ın üstündeki öneri satırında gösterilen özel uygulamalar için de geçerlidir. Modül, Pixel Launcher yerleşimini, veritabanını, simgelerini ve kilit rozetini kullanır. Profil seri numarası ve başlatma bileşeni hedefi tanımlar, tekrarları önler. Taşıma, klasörler ve kaldırma desteklenir.

Kilitliyken kısayolun konumunu koruması ve dokununca sistem doğrulaması istemesi amaçlanır. Kilit açıkken aynı özel kopya doğrudan açılır. İstek yalnızca bir kez çalışır; iptal, zaman aşımı veya Launcher yok edilince temizlenir. Ana profil kopyasına geçilmez. Yalnızca modülün oluşturduğu öğeler değiştirilir; widget ve uygulama içi kısayollar kapsam dışıdır. Kilitliyken de uygulama adı ve simgesi görünür.

Kilitli kısayollar özgün renklerini ve yerel kilit rozetini korur. Bilgi sayfası sabit siyah, beyaz ve gri paletle sistemin açık veya koyu temasını izler.

<a id="compatibility"></a>

## Uyumluluk

**Deneysel alfa; cihaz doğrulaması tamamlanmadı.** Android 15+ (API 35+) Pixel cihazları hedeflenmektedir, ancak **şu anda yalnızca Android 17 üzerinde test edilmiştir** (Pixel 10a, API 37, `CP2A.260805.005`, Pixel Launcher 17). Bağdaştırıcı Android 15 ve 16 sürümlerinde yüklenmeyi deneyecektir, ancak güncellemeler dahili hook'ları bozabilir. Başarılı bir derleme, uyumluluğun kanıtı değildir.

<a id="installation"></a>

## Kurulum

[Sürümler](https://github.com/asadman1523/pixel-private-space-shortcuts/releases) sayfasındaki APK’yi ana profile kurun. LSPosed’de **Pixel Private Space Shortcuts** modülünü etkinleştirip yalnızca `com.google.android.apps.nexuslauncher` seçin, Launcher’ı veya telefonu yeniden başlatın. CI debug APK’leri test içindir ve farklı imza kullanabilir.

Bilgi sayfasında **LSPosed’i aç** düğmesi bulunur. Bağımsız yönetici doğrudan açılır; yerleşik yönetici ilk kullanımda Magisk izni ister. İzin yalnızca bu düğmede kullanılır, özel uygulamaları açmak için kullanılmaz.

<a id="usage"></a>

## Kullanım

Özel Alan kilidini açın, uygulamaya uzun basıp **Ana ekrana ekle** seçeneğini kullanın veya ana ekrana sürükleyin. Öneri satırındaki özel uygulamalar da aynı şekilde eklenebilir. Simgeye dokununca aynı özel kopya açılır; gerekirse Android’de doğrulayın. İptal etmek isteği siler. Simgeye uzun basarak taşıyabilir, klasöre koyabilir veya kaldırabilirsiniz. Tekrar ekleme mevcut kısayol uyarısı verir. Modülün bilgi uygulaması hassas veri içermeyen bir test olarak Özel Alan’a da kurulabilir.

<a id="build"></a>

## Derleme

JDK 17, SDK `platforms;android-37.0`, Build Tools `36.0.0` ve sağlanan Gradle wrapper ile aşağıdaki komutları çalıştırın (Windows: `gradlew.bat`). CI derler, kilit açma durumlarını test eder, Android lint çalıştırır ve tüm README dosyalarıyla yerel bağlantıları denetler. [BUILDING](../BUILDING.md) içindeki dört `PPSS_*` değişkeni imzalamayı ayarlar; anahtarları depo dışında tutun.

```sh
python3 -X utf8 tools/check_docs.py
./gradlew testDebugUnitTest lintDebug assembleDebug
```

<a id="disable"></a>

## Devre dışı bırakma veya kaldırma

İstenmeyen kısayolları Launcher’dan kaldırın, LSPosed’de modülü kapatıp Launcher’ı yeniden başlatın; sonra APK’yi kaldırabilirsiniz. Launcher verilerini silmeyin. Öğeler yereldir ancak modülün kilit işleme özelliği kapanır. Onaylanmış uygulama veya profil silinmesi yerel temizlemeyi kullanır.

<a id="license"></a>

## Lisans

[Apache-2.0](../../LICENSE). Google veya LSPosed ile bağlantılı değildir. Google APK’leri, tersine derlenen dosyalar, cihaz günlükleri, kimlik bilgileri ve anahtarlar dağıtılmaz. [Mimari](../ARCHITECTURE.md) ve [test kayıtlarına](../TESTING.md) bakın.

[Gizlilik politikası (İngilizce)](../../PRIVACY.md)
