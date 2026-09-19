# Pixel Private Space Shortcuts

Des raccourcis natifs sur l’écran d’accueil pour les applications de l’Espace privé Pixel, avec LSPosed.

[![Build](https://img.shields.io/github/actions/workflow/status/asadman1523/pixel-private-space-shortcuts/build.yml?branch=main&style=flat&logo=githubactions&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/actions/workflows/build.yml)
[![Release](https://img.shields.io/github/v/release/asadman1523/pixel-private-space-shortcuts?include_prereleases&style=flat&logo=github&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/releases)
[![Downloads](https://img.shields.io/github/downloads/asadman1523/pixel-private-space-shortcuts/total?style=flat&logo=github&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/releases)
[![Android](https://img.shields.io/badge/Android-17%20%2F%20API%2037%20experimental-orange?style=flat&logo=android&logoColor=white)](#compatibility)
[![License](https://img.shields.io/badge/License-Apache--2.0-blue?style=flat&logo=apache&logoColor=white)](../../LICENSE)

Read this in other languages: [English](../../README.md), [简体中文](README.zh-CN.md), [繁體中文](README.zh-TW.md), [한국어](README.ko-KR.md), [日本語](README.ja-JP.md), [Polski](README.pl-PL.md), **Français**, [Español](README.es-ES.md), [Português](README.pt-BR.md), [Русский](README.ru-RU.md), [Türkçe](README.tr-TR.md), [Italiano](README.it-IT.md), [Bahasa Indonesia](README.id-ID.md), [Українська](README.uk-UA.md), [العربية](README.ar-AR.md), [Tiếng Việt](README.vi-VN.md), [Deutsch](README.de-DE.md), [Uzbek](README.uz-UZ.md), [עברית](README.he-IL.md)

La démonstration sera enregistrée après vérification sur appareil. Aucune simulation ne sera présentée comme un résultat réel.

<a id="features"></a>

## Fonctionnalités

Appuyez longuement sur une application de l’Espace privé déverrouillé et choisissez **Ajouter à l’écran d’accueil**, ou faites-la glisser directement vers l’écran d’accueil. Cela fonctionne également pour les applications privées affichées dans la ligne de suggestions en haut de Toutes les applications. Le module utilise le placement, la base de données, les icônes et le cadenas natifs de Pixel Launcher. Le numéro de série du profil et le composant de lancement identifient la cible et empêchent les doublons. Déplacement, dossiers et suppression sont pris en charge.

À l’état verrouillé, le raccourci est conçu pour conserver sa position et demander l’authentification système. Une fois déverrouillé, le bon profil s’ouvre directement. La demande n’est exécutée qu’une fois et disparaît après annulation, expiration ou destruction du Launcher. Aucun repli vers le profil principal. Seuls les éléments créés par le module sont modifiés, sans widgets ni raccourcis internes. Le nom et l’icône restent visibles même lorsque l’Espace privé est verrouillé.

Les raccourcis verrouillés gardent leurs couleurs et leur cadenas natif. La page d’information suit le thème clair ou sombre du système avec une palette fixe noire, blanche et grise.

<a id="compatibility"></a>

## Compatibilité

**Version alpha expérimentale : vérification sur appareil incomplète.** Cible : Pixel 10a, Android 17 / API 37, `CP2A.260805.005`, Pixel Launcher 17 (`907`), Magisk et LSPosed 2.2.0 (7854). L’empreinte APK doit correspondre exactement au [rapport](../TESTING.md). Sinon, l’adaptateur se désactive et consigne la raison. Une compilation réussie ne prouve pas la compatibilité.

<a id="installation"></a>

## Installation

Installez l’APK des [versions publiées](https://github.com/asadman1523/pixel-private-space-shortcuts/releases) dans le profil principal. Activez **Pixel Private Space Shortcuts** dans LSPosed, sélectionnez uniquement `com.google.android.apps.nexuslauncher`, puis redémarrez le Launcher ou le téléphone. Les APK debug de CI sont destinés aux tests et peuvent avoir une autre signature.

La page propose **Ouvrir LSPosed**. Le gestionnaire autonome s’ouvre directement ; le gestionnaire intégré demande une autorisation Magisk initiale. Cette permission sert uniquement à ce bouton, pas au lancement des applications privées.

<a id="usage"></a>

## Utilisation

Déverrouillez l’Espace privé, maintenez une application et ajoutez-la à l’accueil, ou faites-la glisser vers l’accueil. Les applications privées de la ligne de suggestions peuvent également être ajoutées de la même manière. Touchez son icône pour ouvrir la même instance privée ; authentifiez-vous dans Android si nécessaire. Annuler abandonne cette demande. Un appui long permet déplacement, dossier ou suppression. Un ajout répété signale le raccourci existant. L’application d’information du module peut servir de test sans données sensibles dans l’Espace privé.

<a id="build"></a>

## Compilation

Utilisez JDK 17, SDK `platforms;android-37.0`, Build Tools `36.0.0` et le wrapper Gradle fourni. Exécutez les commandes ci-dessous (`gradlew.bat` sous Windows). La CI compile, teste les états de déverrouillage, exécute Android lint et contrôle tous les README et liens locaux. Les quatre variables `PPSS_*` de [BUILDING](../BUILDING.md) configurent la signature ; gardez les clés hors du dépôt.

```sh
python3 -X utf8 tools/check_docs.py
./gradlew testDebugUnitTest lintDebug assembleDebug
```

<a id="disable"></a>

## Désactivation et suppression

Supprimez les raccourcis inutiles dans le Launcher, désactivez le module dans LSPosed, puis redémarrez le Launcher avant de désinstaller l’APK si souhaité. N’effacez pas les données du Launcher. Les éléments existants restent natifs, mais la gestion du verrouillage du module devient indisponible. La suppression confirmée d’une application ou d’un profil utilise le nettoyage natif.

<a id="license"></a>

## Licence

[Apache-2.0](../../LICENSE). Sans affiliation avec Google ou LSPosed. Aucun APK Google, fichier décompilé, journal d’appareil, identifiant secret ni clé de signature n’est distribué. Voir les [notes techniques](../ARCHITECTURE.md) et le [rapport de vérification](../TESTING.md).
