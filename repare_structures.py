#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
REPARE_STRUCTURES - corrige d'un seul coup les erreurs de compilation des
structures traduites automatiquement.

UTILISATION (depuis C:\\Dev\\LOTR\\lotr-renaissance) :
    python repare_structures.py

Il modifie les fichiers de src\\main\\java\\fr\\alleretretour\\lotr\\world\\structure\\
sauf LOTRStructureBase.java, qui est ecrit a la main et jamais touche.

CE QU'IL CORRIGE :
  1. "missing return statement"  -> ajoute le return manquant en fin de methode
  2. "already defined"           -> supprime les declarations en double
  3. "cannot find symbol variable" -> declare la variable manquante
  4. symboles du Legacy actifs   -> neutralise la ligne en commentaire TODO
  5. "try without catch"         -> transforme le try en bloc simple
  6. declarations parasites      -> supprime int net = 0, int lotr = 0...
  7. accolades desequilibrees    -> signale (correction manuelle)

Une SAUVEGARDE .bak est creee la premiere fois pour chaque fichier modifie.
"""
import collections
import glob
import os
import re
import shutil
import sys

D = os.path.join('src', 'main', 'java', 'fr', 'alleretretour', 'lotr', 'world', 'structure')
BASE_NAME = 'LOTRStructureBase.java'

KW = {'if', 'for', 'while', 'switch', 'return', 'new', 'else', 'do', 'break', 'continue',
      'case', 'default', 'true', 'false', 'null', 'this', 'super', 'instanceof', 'throw',
      'try', 'catch', 'finally', 'int', 'float', 'double', 'boolean', 'char', 'long',
      'byte', 'short', 'void', 'static', 'final', 'class', 'import', 'package', 'public',
      'protected', 'private', 'abstract', 'extends', 'implements'}

KNOWN_PARAMS = {'pos', 'random', 'world', 'origin', 'base', 'state', 'rotation',
                'generator', 'config', 'codec', 'manager', 'entity', 'player', 'stack',
                'facing', 'axis', 'dir', 'te', 'block', 'chest', 'table', 'name', 'type'}

PARASITES = {'net', 'lotr', 'minecraft', 'chests', 'java', 'util', 'com', 'fr',
             'alleretretour', 'world', 'forge', 'mojang'}

LEGACY = re.compile(
    r'(?<![\w."])(LOTRMod|LOTRWorldGen\w+|LOTREntity\w+|LOTRItem\w+|LOTRBlock\w+|'
    r'LOTRChestContents|LOTRFoods|LOTRSpeech|LOTRBiomeGen\w*|BiomeGenBase|WeightedRandom|'
    r'EntityCreature|NBTTag\w+|worldObj|restrictions|getBiomeGenForCoords|'
    r'setBlockAndMetadata|setBlockAndNotifyAdequately|usingPlayer\w*)\b'
    r'|\.(isOpaqueCube|setCheckRanges|setSpawnRanges|setLocationAndAngles|setHomeArea|'
    r'onSpawnWithEgg|getTagCompound|getCompoundTag|isWood|isLeaves|generateWithSetRotation|'
    r'getRandomType|getBiomeVariantAt|getNPCSpawnList|getAllSpawnEntries|onPlantGrow|'
    r'getItemFromBlock|getRandomItem|getSpawnableList|getConstructor|newInstance|'
    r'printStackTrace|setStackDisplayName|getTagList|appendTag|setTag|getSizeInventory|'
    r'setInventorySlotContents|getBlockMetadata|detachHome|saddleMountForWorldGen|'
    r'setChestedForWorldGen|func_\w+|setLeashedToEntity|setRugType|getHobbitSign)\s*\(')

stats = collections.Counter()


def mask(src):
    """Remplace commentaires et chaines par des espaces, EN GARDANT les positions."""
    out = list(src)
    i, n = 0, len(src)
    while i < n:
        if src.startswith('//', i):
            j = src.find('\n', i)
            j = n if j < 0 else j
            for k in range(i, j):
                out[k] = ' '
            i = j
        elif src.startswith('/*', i):
            j = src.find('*/', i + 2)
            j = n if j < 0 else j + 2
            for k in range(i, j):
                out[k] = ' '
            i = j
        elif src[i] == '"':
            j = i + 1
            while j < n and src[j] != '"':
                j += 2 if src[j] == '\\' else 1
            for k in range(i, min(j + 1, n)):
                out[k] = ' '
            i = j + 1
        else:
            i += 1
    return ''.join(out)


def nocom(src):
    """Retire commentaires et contenu des chaines (pour analyser du VRAI code)."""
    src = re.sub(r'/\*.*?\*/', '', src, flags=re.S)
    src = re.sub(r'//[^\n]*', '', src)
    return re.sub(r'"(\\.|[^"\\])*"', '""', src)


def comment_line(line, why):
    """Commente une ligne SANS casser l'equilibre des accolades."""
    code = re.sub(r'//.*', '', line)
    indent = line[:len(line) - len(line.lstrip())]
    delta = code.count('{') - code.count('}')
    body = line.strip()
    if delta > 0:
        return '%sif (true) {   // TODO (%s) : %s' % (indent, why, body)
    if delta < 0:
        return '%s}   // TODO (%s) : %s' % (indent, why, body)
    return '%s// TODO (%s) : %s' % (indent, why, body)


def method_spans(src):
    """(debut_signature, fin_signature, index_accolade_fermante) de chaque methode."""
    out = []
    pattern = re.compile(r'(?:public|protected|private)[\w<>?,\[\] .]*\s(\w+)\s*\([^)]*\)\s*\{')
    for m in pattern.finditer(src):
        depth = 0
        for i in range(m.end() - 1, len(src)):
            if src[i] == '{':
                depth += 1
            elif src[i] == '}':
                depth -= 1
                if depth == 0:
                    out.append((m.start(), m.end(), i, m.group(0)))
                    break
    return out


def infer_type(v):
    if v.endswith('Block'):
        return 'Block', 'Blocks.STONE'
    if v.endswith('State'):
        return 'BlockState', 'Blocks.AIR.defaultBlockState()'
    if v.endswith('Meta'):
        return 'int', '0'
    if v.startswith(('flag', 'is', 'has', 'can')):
        return 'boolean', 'false'
    return 'int', '0'


def fix_file(path):
    raw = open(path, encoding='utf-8').read()
    original = raw

    # ---- 1. neutraliser les symboles du Legacy ----
    lines = raw.split('\n')
    out = []
    for line in lines:
        code = re.sub(r'//.*', '', line)
        if LEGACY.search(code) and 'class LOTRStructure' not in code \
                and not code.strip().startswith('import'):
            out.append(comment_line(line, 'Legacy'))
            stats['symboles Legacy neutralises'] += 1
        else:
            out.append(line)
    raw = '\n'.join(out)

    # ---- 2. try sans catch ----
    lines = raw.split('\n')
    for idx, line in enumerate(lines):
        code = re.sub(r'//.*', '', line)
        if not re.search(r'(?<![\w.])try\s*\{', code):
            continue
        depth, has = 0, False
        for j in range(idx, len(lines)):
            c = re.sub(r'//.*', '', lines[j])
            depth += c.count('{') - c.count('}')
            if j > idx and depth <= 0:
                nxt = re.sub(r'//.*', '', lines[j] + ' ' + (lines[j + 1] if j + 1 < len(lines) else ''))
                has = bool(re.search(r'(?<![\w.])(catch|finally)\b', nxt))
                break
        if not has:
            lines[idx] = re.sub(r'(?<![\w.])try\s*\{', 'if (true) {   //', line, count=1)
            stats['try sans catch corriges'] += 1
    raw = '\n'.join(lines)

    # ---- 3. declarations parasites et doublons ----
    lines = raw.split('\n')
    out, seen = [], set()
    for line in lines:
        code = re.sub(r'//.*', '', line)
        m = re.match(r'\s+(?:int|Block|boolean|BlockState)\s+(\w+)\s*=', code)
        if m:
            if m.group(1) in PARASITES:
                stats['declarations parasites supprimees'] += 1
                continue
            if m.group(1) in seen:
                out.append(re.sub(r'(\s+)(?:int|Block|boolean|BlockState)\s+(\w+\s*=)',
                                  r'\1\2', line, count=1))
                stats['doublons de declaration corriges'] += 1
                continue
            seen.add(m.group(1))
        out.append(line)
    raw = '\n'.join(out)

    # ---- 3b. methodes declarees deux fois ----
    for _ in range(6):
        masked = mask(raw)
        seen_sig, dup = set(), None
        for hs, he, be, sig in method_spans(masked):
            m = re.search(r'(\w+)\s*\(([^)]*)\)', sig)
            if not m:
                continue
            key = (m.group(1), tuple(re.sub(r'\s+', ' ', t).strip().split(' ')[0]
                                     for t in m.group(2).split(',') if t.strip()))
            if key in seen_sig:
                dup = (hs, be)
                break
            seen_sig.add(key)
        if dup is None:
            break
        start, end = dup
        # remonter jusqu'au debut de la ligne, descendre apres l'accolade fermante
        line_start = raw.rfind('\n', 0, start) + 1
        line_end = raw.find('\n', end)
        line_end = len(raw) if line_end < 0 else line_end + 1
        raw = raw[:line_start] + raw[line_end:]
        stats['methodes en double supprimees'] += 1

    # ---- 4. variables manquantes (plusieurs passes) ----
    for _ in range(4):
        src = nocom(raw)
        fields = set(re.findall(r'^\s*(?:protected|private|public)\s+(?:static\s+)?(?:final\s+)?'
                                r'[\w<>?,\[\] .]+\s+(\w+)\s*[=;]', src, re.M))
        added = False
        for hs, he, be, sig in reversed(method_spans(src)):
            body = src[he:be]
            params = set(re.findall(r'[\w<>?,\[\].]+\s+(\w+)\s*(?=[,)])', sig[sig.find('('):]))
            declared = set(re.findall(r'(?:^|[;{}(\s])(?:final\s+)?[A-Za-z_][\w<>?,\[\].]*\s+'
                                      r'(\w+)\s*(?:=|;|:)', body))
            used = set(re.findall(r'(?<![\w.])([a-z]\w*)\b(?![\s]*[.(])', body))
            need = sorted(v for v in used - declared - params - fields - KW
                          if len(v) <= 12 and v not in PARASITES
                          and v not in KNOWN_PARAMS)
            if not need:
                continue
            decls = ''.join('\n        %s %s = %s;' % (infer_type(v)[0], v, infer_type(v)[1])
                            for v in need)
            anchor = sig.strip()[-45:]
            pos = raw.find(anchor)
            if pos < 0:
                continue
            brace = mask(raw).find('{', pos + len(anchor) - 1)
            raw = raw[:brace + 1] + decls + raw[brace + 1:]
            stats['variables declarees'] += len(need)
            added = True
        if not added:
            break

    # ---- 5. return manquant ----
    src = nocom(raw)
    for hs, he, be, sig in reversed(method_spans(src)):
        rtype = re.search(r'(?:public|protected|private)\s+(?:static\s+)?([\w<>?,\[\] .]+?)\s+\w+\s*\(',
                          sig)
        if not rtype:
            continue
        t = rtype.group(1).strip().split()[-1]
        if t in ('void', 'LOTRStructureBase') or t.startswith('LOTRStructure'):
            continue
        body = src[he:be]
        # un return non commente en fin de methode ?
        last = body.rstrip()
        if re.search(r'(?<![\w.])return\b[^;]*;\s*$', last):
            continue
        value = {'boolean': 'true', 'int': '0', 'float': '0.0f', 'double': '0.0',
                 'BlockState': 'Blocks.AIR.defaultBlockState()'}.get(t, 'null')
        # inserer avant l'accolade fermante, dans le fichier BRUT
        anchor = sig.strip()[-45:]
        pos = raw.find(anchor)
        if pos < 0:
            continue
        masked = mask(raw)
        depth, close = 0, -1
        for i in range(masked.find('{', pos + len(anchor) - 1), len(masked)):
            if masked[i] == '{':
                depth += 1
            elif masked[i] == '}':
                depth -= 1
                if depth == 0:
                    close = i
                    break
        if close < 0:
            continue
        raw = raw[:close] + '        return %s;   // ajoute : return manquant\n    ' % value \
              + raw[close:]
        stats['return ajoutes'] += 1

    if raw != original:
        if not os.path.exists(path + '.bak'):
            shutil.copy(path, path + '.bak')
        open(path, 'w', encoding='utf-8').write(raw)
        return True
    return False


def main():
    if not os.path.isdir(D):
        print('Dossier introuvable :', D)
        print('Lance ce script depuis la RACINE du projet (la ou se trouve build.gradle).')
        return 1
    files = [f for f in sorted(glob.glob(os.path.join(D, '*.java')))
             if os.path.basename(f) != BASE_NAME]
    print('%d fichiers a traiter (LOTRStructureBase.java exclu)\n' % len(files))
    changed = sum(1 for f in files if fix_file(f))

    print('%d fichiers modifies\n' % changed)
    for k, v in sorted(stats.items()):
        print('   %-38s %d' % (k, v))

    # controle final
    print('\n--- controle final ---')
    bad = []
    for f in sorted(glob.glob(os.path.join(D, '*.java'))):
        s = nocom(open(f, encoding='utf-8').read())
        if s.count('{') != s.count('}'):
            bad.append(os.path.basename(f) + ' (accolades)')
        if s.count('(') != s.count(')'):
            bad.append(os.path.basename(f) + ' (parentheses)')
    print('  ' + ('\n  '.join(bad) if bad else 'aucun desequilibre detecte'))
    print('\nSauvegardes : fichiers .bak dans le meme dossier.')
    return 0


if __name__ == '__main__':
    sys.exit(main())
