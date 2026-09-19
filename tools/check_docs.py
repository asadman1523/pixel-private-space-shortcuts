"""Check complete language navigation, documentation structure and local links."""
from pathlib import Path
import re
import os
from urllib.parse import unquote, urlsplit

ROOT = Path(__file__).resolve().parents[1]
LANGUAGES = {
    'en': 'English', 'zh-CN': '简体中文', 'zh-TW': '繁體中文',
    'ko-KR': '한국어', 'ja-JP': '日本語', 'pl-PL': 'Polski',
    'fr-FR': 'Français', 'es-ES': 'Español', 'pt-BR': 'Português',
    'ru-RU': 'Русский', 'tr-TR': 'Türkçe', 'it-IT': 'Italiano',
    'id-ID': 'Bahasa Indonesia', 'uk-UA': 'Українська', 'ar-AR': 'العربية',
    'vi-VN': 'Tiếng Việt', 'de-DE': 'Deutsch', 'uz-UZ': 'Uzbek', 'he-IL': 'עברית',
}

def filename(code):
    return 'README.md' if code == 'en' else f'docs/readme/README.{code}.md'

def anchors(text):
    result = set(re.findall(r'<a id="([^"]+)"', text))
    for title in re.findall(r'^#{1,6}\s+(.+)$', text, re.MULTILINE):
        result.add(re.sub(r'[^\w\- ]', '', title.lower()).replace(' ', '-'))
    return result

for code, name in LANGUAGES.items():
    path = ROOT / filename(code)
    content = path.read_text(encoding='utf-8')
    assert len(content) > 1500, f'Incomplete translation: {path.name}'
    nav = next(line for line in content.splitlines() if line.startswith('Read this in other languages:'))
    for other, label in LANGUAGES.items():
        relative = Path(os.path.relpath(ROOT / filename(other), path.parent)).as_posix()
        expected = f'**{label}**' if other == code else f'[{label}]({relative})'
        assert expected in nav, f'{path.name}: missing language {other}'
    sections = ['features', 'compatibility', 'installation', 'usage', 'build', 'disable', 'license']
    assert all(section in anchors(content) for section in sections), path.name
    assert content.index('# Pixel Private Space Shortcuts') < content.index('[![Build]') < content.index(nav) < content.index('<a id="features">'), path.name
    assert 'branch=main&style=for-the-badge' in content, path.name
    assert '/actions/workflows/build.yml' in content, path.name
    assert 'build-passing' not in content.lower(), path.name

for path in list(ROOT.glob('README*.md')) + list((ROOT / 'docs').rglob('*.md')):
    content = path.read_text(encoding='utf-8')
    for href in re.findall(r'\]\(([^\s)]+)\)', content):
        parsed = urlsplit(href)
        if parsed.scheme:
            continue
        target = (path.parent / unquote(parsed.path)).resolve() if parsed.path else path
        assert target.is_relative_to(ROOT), f'{path.name}: link outside repository: {href}'
        assert target.exists(), f'{path.name}: broken link: {href}'
        if parsed.fragment and target.suffix == '.md':
            assert unquote(parsed.fragment) in anchors(target.read_text(encoding='utf-8')), f'{path.name}: missing anchor: {href}'
print('Checked 19 complete READMEs, language navigation, badges and all local documentation links.')
