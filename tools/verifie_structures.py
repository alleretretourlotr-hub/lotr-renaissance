#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""VERIFIE_STRUCTURES : detecte, sur TOUT le dossier world/structure, les
erreurs de compilation courantes SANS avoir besoin de lancer Gradle.

A lancer depuis la racine du projet :
    python verifie_structures.py

Detecte :
  1. doublons de signature de methode (une methode declaree deux fois) ;
  2. accolades / parentheses desequilibrees ;
  3. try sans catch ni finally ;
  4. classe parente absente du dossier ;
  5. symboles du Legacy encore actifs (LOTRMod, LOTREntity, worldObj...) ;
  6. methodes appelees sur le socle mais absentes de LOTRStructureBase ;
  7. variables utilisees sans declaration, methode par methode ;
  8. declarations parasites (int net = 0, int lotr = 0...).

N'ecrit rien : il se contente de lister ce qui doit etre corrige.
"""
import collections
import glob
import os
import re
import sys

D = os.path.join('src', 'main', 'java', 'fr', 'alleretretour', 'lotr', 'world', 'structure')
BASE = os.path.join(D, 'LOTRStructureBase.java')

KW = {'if', 'for', 'while', 'switch', 'return', 'new', 'else', 'do', 'break', 'continue',
      'case', 'default', 'true', 'false', 'null', 'this', 'super', 'instanceof', 'throw',
      'try', 'catch', 'finally', 'int', 'float', 'double', 'boolean', 'char', 'long',
      'byte', 'short', 'void', 'static', 'final', 'class', 'import', 'package'}

PARASITES = {'net', 'lotr', 'minecraft', 'chests', 'java', 'util', 'com', 'fr',
             'alleretretour', 'world', 'forge', 'mojang', 'block', 'entity'}

LEGACY = re.compile(r'(?<![\w."])(LOTRMod|LOTRWorldGen\w+|LOTREntity\w+|LOTRItem\w+|'
                    r'LOTRBlock\w+|LOTRChestContents|LOTRFoods|LOTRBiomeGen\w*|BiomeGenBase|'
                    r'WeightedRandom|EntityCreature|NBTTag\w+|worldObj|restrictions|'
                    r'getBiomeGenForCoords|setBlockAndMetadata|setBlockAndNotifyAdequately)\b')


def nocom(src):
    src = re.sub(r'/\*.*?\*/', '', src, flags=re.S)
    src = re.sub(r'//[^\n]*', '', src)
    return re.sub(r'"(\\.|[^"\\])*"', '""', src)


def signatures(src):
    out = []
    for m in re.finditer(r'(?:protected|public|private)[\w<>?,\[\] .]*?\s(\w+)\s*\(([^)]*)\)\s*\{',
                         src):
        types = tuple(re.sub(r'\s+', ' ', t).strip().split(' ')[0]
                      for t in m.group(2).split(',') if t.strip())
        out.append((m.group(1), types))
    return out


def method_spans(src):
    out = []
    for m in re.finditer(r'(?:public|protected|private)[\w<>?,\[\] .]*\s\w+\s*\([^)]*\)\s*\{', src):
        depth = 0
        for i in range(m.end() - 1, len(src)):
            if src[i] == '{':
                depth += 1
            elif src[i] == '}':
                depth -= 1
                if depth == 0:
                    out.append((m.start(), m.end(), i))
                    break
    return out


def main():
    if not os.path.isdir(D):
        print('Dossier introuvable :', D)
        print('Lance ce script depuis la RACINE du projet (la ou se trouve build.gradle).')
        return 1

    files = sorted(glob.glob(os.path.join(D, '*.java')))
    base_src = nocom(open(BASE, encoding='utf-8').read()) if os.path.exists(BASE) else ''
    base_methods = {n for n, _ in signatures(base_src)}
    classes = set()
    for f in files:
        m = re.search(r'class (\w+)', open(f, encoding='utf-8').read())
        if m:
            classes.add(m.group(1))

    problems = collections.OrderedDict()

    def add(f, msg):
        problems.setdefault(os.path.basename(f), []).append(msg)

    for f in files:
        raw = open(f, encoding='utf-8').read()
        src = nocom(raw)

        # 1. doublons de signature
        counts = collections.Counter(signatures(src))
        for (name, _), n in counts.items():
            if n > 1:
                add(f, f'methode declaree {n} fois : {name}()')

        # 2. equilibre
        if src.count('{') != src.count('}'):
            add(f, 'accolades desequilibrees')
        if src.count('(') != src.count(')'):
            add(f, 'parentheses desequilibrees')

        # 3. try sans catch
        for m in re.finditer(r'(?<![\w.])try\s*\{', src):
            tail = src[m.end():]
            depth = 1
            for i, ch in enumerate(tail):
                if ch == '{':
                    depth += 1
                elif ch == '}':
                    depth -= 1
                    if depth == 0:
                        if not re.match(r'\s*(catch|finally)\b', tail[i + 1:i + 40]):
                            add(f, 'try sans catch ni finally')
                        break

        # 4. classe parente
        m = re.search(r'class \w+ extends (\w+)', src)
        if m and m.group(1) not in classes and m.group(1) != 'Feature':
            add(f, f'classe parente absente : {m.group(1)}')

        # 5. symboles Legacy
        for sym in set(LEGACY.findall(src)):
            add(f, f'symbole Legacy actif : {sym}')

        # 6. methodes du socle
        local = {n for n, _ in signatures(src)}
        called = set(re.findall(r'(?<![\w.])([a-z]\w*)\s*\(', src))
        for c in sorted(called - local - KW):
            if c in ('rotate', 'modBlock', 'modBlockOnly', 'setBlockRotated', 'setGrassToDirt',
                     'setLootTable', 'withMeta', 'stairFacing', 'isOpaqueLocal', 'isSurfaceLocal',
                     'getTopBlockLocal', 'placeRandomBrick', 'placeRandomStairs') \
                    and c not in base_methods:
                add(f, f'methode absente du socle : {c}()')

        # 7 & 8. variables
        fields = set(re.findall(r'^\s*(?:protected|private|public)\s+(?:static\s+)?(?:final\s+)?'
                                r'[\w<>?,\[\] .]+\s+(\w+)\s*[=;]', src, re.M))
        for hs, he, be in method_spans(src):
            sig, body = src[hs:he], src[he:be]
            params = set(re.findall(r'[\w<>?,\[\].]+\s+(\w+)\s*(?=[,)])', sig[sig.find('('):]))
            declared = set(re.findall(r'(?:^|[;{}(\s])(?:final\s+)?[A-Za-z_][\w<>?,\[\].]*\s+'
                                      r'(\w+)\s*(?:=|;|:)', body))
            used = set(re.findall(r'(?<![\w.])([a-z]\w*)\b(?![\s]*[.(])', body))
            for v in sorted(used - declared - params - fields - KW):
                if len(v) <= 12:
                    add(f, f'variable non declaree : {v}')
        for m in re.finditer(r'^\s+(?:int|Block|boolean|BlockState)\s+(\w+)\s*=', src, re.M):
            if m.group(1) in PARASITES:
                add(f, f'declaration parasite : {m.group(1)}')

    print(f'{len(files)} fichiers analyses, {len(problems)} avec anomalie\n')
    for name, msgs in problems.items():
        uniq = sorted(set(msgs))
        print(f'  {name}')
        for u in uniq[:6]:
            print(f'      - {u}')
        if len(uniq) > 6:
            print(f'      ... et {len(uniq) - 6} autres')
    if not problems:
        print('AUCUNE ANOMALIE DETECTEE.')
    return 0


if __name__ == '__main__':
    sys.exit(main())
