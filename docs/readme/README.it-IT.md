# Pixel Private Space Shortcuts

Collegamenti nativi nella schermata Home per le app dello Spazio privato Pixel, tramite LSPosed.

[![Build](https://img.shields.io/github/actions/workflow/status/asadman1523/pixel-private-space-shortcuts/build.yml?branch=main&style=flat&logo=githubactions&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/actions/workflows/build.yml)
[![Android](https://img.shields.io/badge/Android-17%20%2F%20API%2037%20experimental-orange?style=flat&logo=android&logoColor=white)](#compatibility)
[![License](https://img.shields.io/badge/License-Apache--2.0-blue?style=flat&logo=apache&logoColor=white)](../../LICENSE)

Read this in other languages: [English](../../README.md), [简体中文](README.zh-CN.md), [繁體中文](README.zh-TW.md), [한국어](README.ko-KR.md), [日本語](README.ja-JP.md), [Polski](README.pl-PL.md), [Français](README.fr-FR.md), [Español](README.es-ES.md), [Português](README.pt-BR.md), [Русский](README.ru-RU.md), [Türkçe](README.tr-TR.md), **Italiano**, [Bahasa Indonesia](README.id-ID.md), [Українська](README.uk-UA.md), [العربية](README.ar-AR.md), [Tiếng Việt](README.vi-VN.md), [Deutsch](README.de-DE.md), [Uzbek](README.uz-UZ.md), [עברית](README.he-IL.md)

La dimostrazione verrà registrata dopo la verifica sul dispositivo, senza spacciare simulazioni per prove reali.

<a id="features"></a>

## Funzioni

Tieni premuta un’app nello Spazio privato sbloccato e scegli **Aggiungi alla schermata Home**. Il modulo usa posizionamento, database, icone e lucchetto di Pixel Launcher. Numero di serie del profilo e componente di avvio identificano la destinazione e impediscono duplicati. Supporta spostamento, cartelle e rimozione.

Quando è bloccato, il collegamento è progettato per conservare la posizione e richiedere l’autenticazione di sistema. Se sbloccato, apre direttamente la copia privata. Ogni richiesta viene eseguita una volta e cancellata dopo annullamento, scadenza o distruzione del Launcher. Nessun ripiego sul profilo principale. Modifica solo elementi del modulo, senza widget o collegamenti interni. Nome e icona restano visibili anche con lo Spazio privato bloccato.

I collegamenti bloccati conservano colori e lucchetto nativi. La pagina informativa segue il tema chiaro o scuro del sistema con una palette fissa di nero, bianco e grigio.

<a id="compatibility"></a>

## Compatibilità

**Alfa sperimentale: verifica sul dispositivo incompleta.** Pixel 10a, Android 17 / API 37, `CP2A.260805.005`, Pixel Launcher 17 (`907`), Magisk e LSPosed 2.2.0 (7854). Serve l’esatta impronta APK del [registro](../TESTING.md). Altre versioni disabilitano l’adattatore e registrano il motivo. Una compilazione riuscita non dimostra compatibilità.

<a id="installation"></a>

## Installazione

Installa l’APK delle [versioni](https://github.com/asadman1523/pixel-private-space-shortcuts/releases) nel profilo principale. Abilita **Pixel Private Space Shortcuts** in LSPosed, seleziona solo `com.google.android.apps.nexuslauncher` e riavvia Launcher o dispositivo. Gli APK debug della CI sono di prova e possono avere firme diverse.

<a id="usage"></a>

## Utilizzo

Sblocca lo Spazio privato, tieni premuta l’app e aggiungila alla Home. Tocca l’icona per aprire la stessa copia privata e autenticati in Android se richiesto. Annullare scarta la richiesta. Tieni premuta l’icona per spostarla, inserirla in una cartella o rimuoverla. Un nuovo tentativo di aggiunta segnala che esiste già. L’app informativa del modulo può essere usata nello Spazio privato come prova senza dati sensibili.

<a id="build"></a>

## Compilazione

Usa JDK 17, SDK `platforms;android-37.0`, Build Tools `36.0.0` e il wrapper Gradle incluso. Esegui i comandi sotto (`gradlew.bat` su Windows). CI compila, verifica gli stati di sblocco, esegue Android lint e controlla tutti i README e collegamenti locali. Le quattro variabili `PPSS_*` di [BUILDING](../BUILDING.md) configurano la firma; conserva le chiavi fuori dal repository.

```sh
python3 -X utf8 tools/check_docs.py
./gradlew testDebugUnitTest lintDebug assembleDebug
```

<a id="disable"></a>

## Disabilitazione e rimozione

Rimuovi i collegamenti indesiderati dal Launcher, disabilita il modulo in LSPosed e riavvia Launcher; poi puoi disinstallare l’APK. Non cancellare i dati del Launcher. Gli elementi sono nativi, ma la gestione del blocco del modulo non sarà disponibile. La rimozione confermata di app o profili segue la pulizia nativa.

<a id="license"></a>

## Licenza

[Apache-2.0](../../LICENSE). Nessuna affiliazione con Google o LSPosed. Non distribuiamo APK Google, file decompilati, registri del dispositivo, credenziali o chiavi. Vedi [architettura](../ARCHITECTURE.md) e [verifiche](../TESTING.md).
