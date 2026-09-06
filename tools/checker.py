"""Verificateur : declarations de variables par METHODE (portee correcte)."""
import glob, re, sys

D = 'lotr-renaissance/src/main/java/fr/alleretretour/lotr/world/structure/'
KEYWORDS = {'if','for','while','switch','return','new','else','do','break','continue',
            'case','default','true','false','null','this','super','int','float','double',
            'boolean','Block','BlockState','void','static','protected','public','private'}

def strip_comments(s):
    s = re.sub(r'/\*.*?\*/', '', s, flags=re.S)
    return re.sub(r'//[^\n]*', '', s)

def methods(src):
    """Renvoie (debut_corps, fin_corps) de chaque methode."""
    out = []
    for m in re.finditer(r'(?:public|protected|private)\s+[\w<>?,\[\] .]+\s+\w+\s*\([^)]*\)\s*\{', src):
        depth, start = 0, m.end() - 1
        for i in range(start, len(src)):
            if src[i] == '{': depth += 1
            elif src[i] == '}':
                depth -= 1
                if depth == 0:
                    out.append((m.start(), start + 1, i))
                    break
    return out

def check(path, fix=False):
    raw = open(path).read()
    src = strip_comments(raw)
    fields = set(re.findall(r'^\s*(?:protected|private|public)\s+(?:static\s+)?(?:final\s+)?'
                            r'[\w<>?,\[\] .]+\s+(\w+)\s*[=;]', src, re.M))
    problems = []
    for head, bs, be in methods(src):
        body = src[bs:be]
        sig = src[head:bs]
        params = set(re.findall(r'(\w+)\s*(?:,|\))', sig.split('(', 1)[1] if '(' in sig else ''))
        declared = set(re.findall(r'(?:^|[;{(\s])(?:final\s+)?[\w<>?,\[\].]+\s+(\w+)\s*(?:=|;|:)', body))
        assigned = set(re.findall(r'(?:^|[;{}\s])(\w+)\s*=(?!=)', body))
        used = set(re.findall(r'(?<![\w.])([a-z]\w*)(?=\s*[;=,)\.\]])', body))
        known = fields | params | declared | KEYWORDS
        missing = sorted((assigned | used) - known)
        missing = [v for v in missing if not v[0].isupper() and len(v) > 1
                   and (v.endswith('Block') or v.endswith('Meta') or v == 'block')]
        if missing:
            problems.append((path.split('/')[-1], sorted(set(missing))))
    return problems

allp = []
for f in sorted(glob.glob(D + '*.java')):
    allp += check(f)
print(f'{len(allp)} methodes avec variables non declarees')
for name, vars_ in allp[:15]:
    print(f'  {name}: {vars_}')
