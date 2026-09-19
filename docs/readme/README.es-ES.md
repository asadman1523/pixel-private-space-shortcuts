# Pixel Private Space Shortcuts

Accesos directos nativos en la pantalla de inicio para las apps del Espacio privado de Pixel, mediante LSPosed.

[![Build](https://img.shields.io/github/actions/workflow/status/asadman1523/pixel-private-space-shortcuts/build.yml?branch=main&style=flat&logo=githubactions&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/actions/workflows/build.yml)
[![Release](https://img.shields.io/github/v/release/asadman1523/pixel-private-space-shortcuts?include_prereleases&style=flat&logo=github&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/releases)
[![Downloads](https://img.shields.io/github/downloads/asadman1523/pixel-private-space-shortcuts/total?style=flat&logo=github&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/releases)
[![Android](https://img.shields.io/badge/Android-17%20%2F%20API%2037%20experimental-orange?style=flat&logo=android&logoColor=white)](#compatibility)
[![License](https://img.shields.io/badge/License-Apache--2.0-blue?style=flat&logo=apache&logoColor=white)](../../LICENSE)

Read this in other languages: [English](../../README.md), [简体中文](README.zh-CN.md), [繁體中文](README.zh-TW.md), [한국어](README.ko-KR.md), [日本語](README.ja-JP.md), [Polski](README.pl-PL.md), [Français](README.fr-FR.md), **Español**, [Português](README.pt-BR.md), [Русский](README.ru-RU.md), [Türkçe](README.tr-TR.md), [Italiano](README.it-IT.md), [Bahasa Indonesia](README.id-ID.md), [Українська](README.uk-UA.md), [العربية](README.ar-AR.md), [Tiếng Việt](README.vi-VN.md), [Deutsch](README.de-DE.md), [Uzbek](README.uz-UZ.md), [עברית](README.he-IL.md)

La demostración se grabará tras verificar el dispositivo; no se presentará una simulación como prueba real.

<a id="features"></a>

## Funciones

Mantén pulsada una app del Espacio privado desbloqueado y elige **Añadir a la pantalla de inicio**. Se reutilizan la distribución, base de datos, iconos y candado de Pixel Launcher. El número de serie del perfil y el componente de inicio identifican el destino y evitan duplicados. Permite mover, agrupar en carpetas y eliminar.

Cuando está bloqueado, el acceso está diseñado para conservar su posición y pedir autenticación del sistema. Desbloqueado, abre directamente la copia privada. Cada solicitud se ejecuta una sola vez y se borra al cancelar, caducar o destruirse el Launcher. Nunca abre la copia principal como alternativa. Solo modifica elementos del módulo; no incluye widgets ni accesos internos. El nombre y el icono siguen visibles con el Espacio privado bloqueado.

Los accesos bloqueados conservan sus colores y el candado nativo. La página informativa sigue el modo claro u oscuro del sistema con una paleta fija de negro, blanco y gris.

<a id="compatibility"></a>

## Compatibilidad

**Alfa experimental; verificación en dispositivo incompleta.** Destino: Pixel 10a, Android 17 / API 37, `CP2A.260805.005`, Pixel Launcher 17 (`907`), Magisk y LSPosed 2.2.0 (7854). Exige la huella APK exacta del [registro](../TESTING.md). Otras versiones desactivan el adaptador y registran el motivo. Compilar correctamente no demuestra compatibilidad real.

<a id="installation"></a>

## Instalación

Instala el APK de [Releases](https://github.com/asadman1523/pixel-private-space-shortcuts/releases) en el perfil principal. Activa **Pixel Private Space Shortcuts** en LSPosed, selecciona solo `com.google.android.apps.nexuslauncher` y reinicia el Launcher o el teléfono. Los APK debug de CI son de prueba y pueden tener otra firma.

La página incluye **Abrir LSPosed**. El gestor independiente se abre directamente; el integrado requiere autorización inicial de Magisk. Este permiso solo se usa al pulsar el botón, no para abrir apps privadas.

<a id="usage"></a>

## Uso

Desbloquea el Espacio privado, mantén pulsada una app y añádela al inicio. Toca su icono para abrir la misma copia privada y autentícate en Android si hace falta. Cancelar descarta la solicitud. Mantén pulsado el icono para moverlo, colocarlo en una carpeta o eliminarlo. Un segundo intento de añadirlo avisa que ya existe. La app informativa del módulo puede instalarse también en el Espacio privado como prueba sin datos sensibles.

<a id="build"></a>

## Compilación

Usa JDK 17, SDK `platforms;android-37.0`, Build Tools `36.0.0` y el wrapper Gradle incluido. Ejecuta los comandos inferiores (`gradlew.bat` en Windows). CI compila, prueba los estados de desbloqueo, ejecuta Android lint y comprueba todos los README y enlaces locales. Las cuatro variables `PPSS_*` de [BUILDING](../BUILDING.md) configuran la firma; guarda las claves fuera del repositorio.

```sh
python3 -X utf8 tools/check_docs.py
./gradlew testDebugUnitTest lintDebug assembleDebug
```

<a id="disable"></a>

## Desactivar o eliminar

Elimina los accesos que no quieras desde el Launcher, desactiva el módulo en LSPosed y reinicia el Launcher; después puedes desinstalar el APK. No borres los datos del Launcher. Los elementos existentes son nativos, pero el manejo del bloqueo del módulo deja de estar disponible. La eliminación confirmada de una app o perfil usa la limpieza nativa.

<a id="license"></a>

## Licencia

[Apache-2.0](../../LICENSE). Sin afiliación con Google ni LSPosed. No se distribuyen APK de Google, archivos descompilados, registros del dispositivo, credenciales ni claves. Consulta la [arquitectura](../ARCHITECTURE.md) y las [verificaciones](../TESTING.md).
