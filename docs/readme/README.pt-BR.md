# Pixel Private Space Shortcuts

Atalhos nativos na tela inicial para apps do Espaço privado do Pixel, com LSPosed.

[![Build](https://img.shields.io/github/actions/workflow/status/asadman1523/pixel-private-space-shortcuts/build.yml?branch=main&style=flat&logo=githubactions&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/actions/workflows/build.yml)
[![Release](https://img.shields.io/github/v/release/asadman1523/pixel-private-space-shortcuts?include_prereleases&style=flat&logo=github&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/releases)
[![Downloads](https://img.shields.io/github/downloads/asadman1523/pixel-private-space-shortcuts/total?style=flat&logo=github&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/releases)
[![Android](https://img.shields.io/badge/Android-17%20%2F%20API%2037%20experimental-orange?style=flat&logo=android&logoColor=white)](#compatibility)
[![License](https://img.shields.io/badge/License-Apache--2.0-blue?style=flat&logo=apache&logoColor=white)](../../LICENSE)

Read this in other languages: [English](../../README.md), [简体中文](README.zh-CN.md), [繁體中文](README.zh-TW.md), [한국어](README.ko-KR.md), [日本語](README.ja-JP.md), [Polski](README.pl-PL.md), [Français](README.fr-FR.md), [Español](README.es-ES.md), **Português**, [Русский](README.ru-RU.md), [Türkçe](README.tr-TR.md), [Italiano](README.it-IT.md), [Bahasa Indonesia](README.id-ID.md), [Українська](README.uk-UA.md), [العربية](README.ar-AR.md), [Tiếng Việt](README.vi-VN.md), [Deutsch](README.de-DE.md), [Uzbek](README.uz-UZ.md), [עברית](README.he-IL.md)

A demonstração será gravada após a verificação no aparelho. Simulações não serão apresentadas como testes reais.

<a id="features"></a>

## Recursos

Pressione um app no Espaço privado desbloqueado e selecione **Adicionar à tela inicial**. O módulo reutiliza a posição, o banco de dados, os ícones e o cadeado do Pixel Launcher. O número de série do perfil e o componente de inicialização identificam o destino, evitando duplicatas. É possível mover, organizar em pastas e remover.

Bloqueado, o atalho foi projetado para manter sua posição e solicitar autenticação do sistema; desbloqueado, abre a mesma cópia privada diretamente. Cada pedido é executado uma vez e descartado ao cancelar, expirar ou encerrar o Launcher. Nunca usa a cópia principal como alternativa. Afeta apenas itens do módulo, sem widgets ou atalhos internos. Nome e ícone ficam visíveis mesmo com o Espaço privado bloqueado.

Os atalhos bloqueados mantêm as cores e o cadeado nativo. A página informativa acompanha o tema claro ou escuro do sistema com paleta fixa de preto, branco e cinza.

<a id="compatibility"></a>

## Compatibilidade

**Alfa experimental; verificação no aparelho incompleta.** Pixel 10a, Android 17 / API 37, `CP2A.260805.005`, Pixel Launcher 17 (`907`), Magisk e LSPosed 2.2.0 (7854). Exige a impressão digital APK exata do [registro](../TESTING.md). Outras versões desativam o adaptador e registram o motivo. Compilar não comprova compatibilidade.

<a id="installation"></a>

## Instalação

Instale o APK de [Releases](https://github.com/asadman1523/pixel-private-space-shortcuts/releases) no perfil principal. Ative **Pixel Private Space Shortcuts** no LSPosed, selecione somente `com.google.android.apps.nexuslauncher` e reinicie o Launcher ou o aparelho. APKs debug da CI são testes e podem ter outra assinatura.

<a id="usage"></a>

## Uso

Desbloqueie o Espaço privado, pressione um app e adicione à tela inicial. Toque no ícone para abrir a mesma cópia privada e autentique-se pelo Android quando necessário. Cancelar descarta o pedido. Pressione o ícone para mover, guardar em pasta ou remover. Adicionar novamente avisa que já existe. O app informativo do módulo pode ser instalado no Espaço privado como teste sem dados sensíveis.

<a id="build"></a>

## Compilação

Use JDK 17, SDK `platforms;android-37.0`, Build Tools `36.0.0` e o wrapper Gradle incluído. Execute abaixo (`gradlew.bat` no Windows). A CI compila, testa os estados de desbloqueio, executa Android lint e verifica todos os README e links locais. As quatro variáveis `PPSS_*` de [BUILDING](../BUILDING.md) configuram a assinatura; mantenha as chaves fora do repositório.

```sh
python3 -X utf8 tools/check_docs.py
./gradlew testDebugUnitTest lintDebug assembleDebug
```

<a id="disable"></a>

## Desativação e remoção

Remova atalhos indesejados pelo Launcher, desative o módulo no LSPosed e reinicie o Launcher; depois desinstale o APK se quiser. Não apague os dados do Launcher. Os itens existentes são nativos, mas o tratamento de bloqueio do módulo fica indisponível. Exclusão confirmada de app ou perfil usa a limpeza nativa.

<a id="license"></a>

## Licença

[Apache-2.0](../../LICENSE). Sem vínculo com Google ou LSPosed. Não distribuímos APKs do Google, arquivos descompilados, registros do aparelho, credenciais ou chaves. Veja a [arquitetura](../ARCHITECTURE.md) e os [testes](../TESTING.md).
