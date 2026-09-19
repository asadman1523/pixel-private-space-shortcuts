# Pixel Private Space Shortcuts

Pintasan layar utama asli untuk aplikasi Ruang Pribadi Pixel melalui LSPosed.

[![Build](https://img.shields.io/github/actions/workflow/status/asadman1523/pixel-private-space-shortcuts/build.yml?branch=main&style=flat&logo=githubactions&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/actions/workflows/build.yml)
[![Release](https://img.shields.io/github/v/release/asadman1523/pixel-private-space-shortcuts?include_prereleases&style=flat&logo=github&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/releases)
[![Downloads](https://img.shields.io/github/downloads/asadman1523/pixel-private-space-shortcuts/total?style=flat&logo=github&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/releases)
[![Android](https://img.shields.io/badge/Android-17%20%2F%20API%2037%20experimental-orange?style=flat&logo=android&logoColor=white)](#compatibility)
[![License](https://img.shields.io/badge/License-Apache--2.0-blue?style=flat&logo=apache&logoColor=white)](../../LICENSE)

Read this in other languages: [English](../../README.md), [简体中文](README.zh-CN.md), [繁體中文](README.zh-TW.md), [한국어](README.ko-KR.md), [日本語](README.ja-JP.md), [Polski](README.pl-PL.md), [Français](README.fr-FR.md), [Español](README.es-ES.md), [Português](README.pt-BR.md), [Русский](README.ru-RU.md), [Türkçe](README.tr-TR.md), [Italiano](README.it-IT.md), **Bahasa Indonesia**, [Українська](README.uk-UA.md), [العربية](README.ar-AR.md), [Tiếng Việt](README.vi-VN.md), [Deutsch](README.de-DE.md), [Uzbek](README.uz-UZ.md), [עברית](README.he-IL.md)

Demonstrasi akan direkam setelah verifikasi perangkat; simulasi tidak dinyatakan sebagai hasil uji nyata.

<a id="features"></a>

## Fitur

Tekan lama aplikasi di Ruang Pribadi yang terbuka lalu pilih **Tambahkan ke layar utama**, atau seret langsung ke layar utama. Ini juga berfungsi untuk aplikasi pribadi di baris saran di bagian atas Semua Aplikasi. Modul memakai penempatan, basis data, ikon, dan lencana gembok Pixel Launcher. Nomor seri profil dan komponen peluncuran menentukan tujuan serta mencegah duplikasi. Mendukung pemindahan, folder, dan penghapusan.

Saat terkunci, pintasan dirancang untuk mempertahankan posisi dan meminta autentikasi sistem ketika diketuk. Jika terbuka, salinan pribadi langsung dijalankan. Permintaan hanya dijalankan sekali dan dihapus saat dibatalkan, kedaluwarsa, atau Launcher dihancurkan. Tidak beralih ke salinan profil utama. Hanya item buatan modul yang diubah; widget dan pintasan internal tidak termasuk. Nama dan ikon tetap terlihat saat Ruang Pribadi terkunci.

Pintasan terkunci mempertahankan warna dan lencana gembok asli. Halaman informasi mengikuti mode terang atau gelap sistem dengan palet tetap hitam, putih, dan abu-abu.

<a id="compatibility"></a>

## Kompatibilitas

**Alfa eksperimental; verifikasi perangkat belum lengkap.** Pixel 10a, Android 17 / API 37, `CP2A.260805.005`, Pixel Launcher 17 (`907`), Magisk, LSPosed 2.2.0 (7854). Sidik APK harus persis sesuai [catatan](../TESTING.md). Versi lain menonaktifkan adaptor dan mencatat alasannya. Build berhasil bukan bukti kompatibilitas.

<a id="installation"></a>

## Pemasangan

Pasang APK dari [rilis](https://github.com/asadman1523/pixel-private-space-shortcuts/releases) di profil utama. Aktifkan **Pixel Private Space Shortcuts** di LSPosed, pilih hanya `com.google.android.apps.nexuslauncher`, lalu mulai ulang Launcher atau ponsel. APK debug CI untuk pengujian dan mungkin memakai tanda tangan berbeda.

Halaman menyediakan tombol **Buka LSPosed**. Pengelola mandiri langsung terbuka; pengelola bawaan memerlukan izin Magisk pertama kali. Izin hanya digunakan saat menekan tombol ini, bukan untuk membuka aplikasi pribadi.

<a id="usage"></a>

## Penggunaan

Buka Ruang Pribadi, tekan lama aplikasi lalu pilih **Tambahkan ke layar utama**, atau seret ke layar utama. Aplikasi pribadi di baris saran juga dapat ditambahkan dengan cara yang sama. Ketuk ikon untuk membuka salinan pribadi yang sama; autentikasi lewat Android bila diperlukan. Membatalkan membuang permintaan. Tekan lama ikon untuk memindah, memasukkan ke folder, atau menghapus. Penambahan ulang menampilkan pesan bahwa pintasan sudah ada. Aplikasi informasi modul dapat dipasang di Ruang Pribadi sebagai uji tanpa data sensitif.

<a id="build"></a>

## Build

Gunakan JDK 17, SDK `platforms;android-37.0`, Build Tools `36.0.0`, dan Gradle wrapper yang disertakan. Jalankan perintah di bawah (`gradlew.bat` di Windows). CI membangun, menguji keadaan buka kunci, menjalankan Android lint, serta memeriksa seluruh README dan tautan lokal. Empat variabel `PPSS_*` dalam [BUILDING](../BUILDING.md) mengatur penandatanganan; simpan kunci di luar repositori.

```sh
python3 -X utf8 tools/check_docs.py
./gradlew testDebugUnitTest lintDebug assembleDebug
```

<a id="disable"></a>

## Menonaktifkan atau menghapus

Hapus pintasan yang tidak diperlukan melalui Launcher, nonaktifkan modul di LSPosed, lalu mulai ulang Launcher sebelum menghapus APK bila diinginkan. Jangan hapus data Launcher. Item tetap asli, tetapi penanganan kunci modul tidak tersedia saat nonaktif. Penghapusan aplikasi atau profil yang terkonfirmasi mengikuti pembersihan asli.

<a id="license"></a>

## Lisensi

[Apache-2.0](../../LICENSE). Tidak berafiliasi dengan Google atau LSPosed. Tidak menyebarkan APK Google, berkas dekompilasi, log perangkat, kredensial, atau kunci. Lihat [arsitektur](../ARCHITECTURE.md) dan [verifikasi](../TESTING.md).
