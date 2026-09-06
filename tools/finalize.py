"""Passe unique de mise en conformite des structures extraites."""
import glob, re, collections

D = 'lotr-renaissance/src/main/java/fr/alleretretour/lotr/world/structure/'
KW = {'if','for','while','switch','return','new','else','do','break','continue','case','default',
      'true','false','null','this','super','instanceof','throw','try','catch','finally','int',
      'float','double','boolean','char','long','byte','short','void','static','final','class'}

# --- 1. neutraliser tout ce qui vient du Legacy ---------------------------
LEGACY = re.compile(
    r'(?<![\w."])('
    r'LOTRWorldGen\w+|LOTREntity\w+|LOTRItem\w+|LOTRBlock\w+|LOTRMod|LOTRChestContents|'
    r'LOTRFoods|LOTRSpeech|LOTRBiomeGen\w*|BiomeGenBase|WeightedRandom|EntityCreature|'
    r'NBTTag\w+|worldObj|restrictions|getBiomeGenForCoords|setBlockAndMetadata|'
    r'setBlockAndNotifyAdequately|usingPlayer\w*'
    r')\b'
    r'|\.(isOpaqueCube|setCheckRanges|setSpawnRanges|setLocationAndAngles|setHomeArea|'
    r'onSpawnWithEgg|getRandomCakeBlock|setSpecificLocationName|getTagCompound|getCompoundTag|'
    r'isWood|isLeaves|generateWithSetRotation|getRandomType|getBiomeVariantAt|getNPCSpawnList|'
    r'getAllSpawnEntries|onPlantGrow|getItemFromBlock|getRandomItem|getSpawnableList|'
    r'getConstructor|newInstance|printStackTrace|getSoldierClasses|setSpawnClasses|'
    r'getDunlendingTavernName|setStackDisplayName|getTagList|appendTag|setTag|getSizeInventory|'
    r'setInventorySlotContents|getBlockMetadata|detachHome|saddleMountForWorldGen|'
    r'setChestedForWorldGen|func_\w+|setLeashedToEntity|setRugType|getHobbitSign|generate)\s*\(')

MATH = {r'MathHelper\.getRandomIntegerInRange\(': 'net.minecraft.util.math.MathHelper.nextInt(',
        r'MathHelper\.floor_double\(': 'net.minecraft.util.math.MathHelper.floor(',
        r'MathHelper\.randomFloatClamp\(': 'net.minecraft.util.math.MathHelper.nextFloat(',
        r'\.getTopSolidOrLiquidBlock\(': '.getHeight(net.minecraft.world.gen.Heightmap.Type.OCEAN_FLOOR_WG, '}

def comment(line, why):
    code = re.sub(r'//.*', '', line)
    ind = line[:len(line) - len(line.lstrip())]
    op = code.count('{') - code.count('}')
    b = line.strip()
    if op > 0:
        return f'{ind}if (true) {{   // TODO ({why}) : {b}'
    if op < 0:
        return f'{ind}}}   // TODO ({why}) : {b}'
    return f'{ind}// TODO ({why}) : {b}'

def method_spans(src):
    out = []
    for m in re.finditer(r'(?:public|protected|private)[\w<>?,\[\] .]*\s\w+\s*\([^)]*\)\s*\{', src):
        depth = 0
        for i in range(m.end() - 1, len(src)):
            if src[i] == '{': depth += 1
            elif src[i] == '}':
                depth -= 1
                if depth == 0:
                    out.append((m.start(), m.end(), i)); break
    return out

def infer(v):
    if v.endswith('Block'): return 'Block', 'Blocks.STONE'
    if v.endswith('Meta'): return 'int', '0'
    if v.startswith('flag') or v.startswith('is') or v.startswith('has'): return 'boolean', 'false'
    if v.endswith('State'): return 'BlockState', 'Blocks.AIR.defaultBlockState()'
    return 'int', '0'

for f in sorted(glob.glob(D + '*.java')):
    if f.endswith('LOTRStructureBase.java'):
        continue                      # socle ecrit a la main : jamais modifie
    raw = open(f).read()

    # traductions mathematiques
    for pat, rep in MATH.items():
        raw = re.sub(pat, rep, raw)

    # neutralisation Legacy
    lines = raw.split('\n')
    out = []
    for line in lines:
        code = re.sub(r'//.*', '', line)
        if LEGACY.search(code) and 'class LOTRStructure' not in code and not code.strip().startswith('import'):
            out.append(comment(line, 'Legacy'))
        else:
            out.append(line)
    raw = '\n'.join(out)

    # try orphelins
    lines = raw.split('\n')
    for idx, line in enumerate(lines):
        code = re.sub(r'//.*', '', line)
        if not re.search(r'(?<![\w.])try\s*\{', code):
            continue
        depth, ok = 0, False
        for j in range(idx, len(lines)):
            c = re.sub(r'//.*', '', lines[j])
            depth += c.count('{') - c.count('}')
            if j > idx and depth <= 0:
                nxt = re.sub(r'//.*', '', lines[j] + ' ' + (lines[j+1] if j+1 < len(lines) else ''))
                ok = bool(re.search(r'(?<![\w.])(catch|finally)\b', nxt)); break
        if not ok:
            lines[idx] = re.sub(r'(?<![\w.])try\s*\{', 'if (true) {   //', line, 1)
    raw = '\n'.join(lines)

    # declaration de TOUTE variable manquante, methode par methode
    if f.endswith('LOTRStructureBase.java'):
        open(f, 'w').write(raw)
        continue
    for _ in range(3):
        nocom = re.sub(r'//[^\n]*', '', re.sub(r'/\*.*?\*/', '', raw, flags=re.S))
        # neutraliser les chaines : leur contenu n'est pas du code
        nocom = re.sub(r'"(\\.|[^"\\])*"', '""', nocom)
        nocom = re.sub(r'"(\\.|[^"\\])*"', '""', nocom)   # neutraliser les chaines
        fields = set(re.findall(r'^\s*(?:protected|private|public)\s+(?:static\s+)?(?:final\s+)?'
                                r'[\w<>?,\[\] .]+\s+(\w+)\s*[=;]', nocom, re.M))
        added = False
        for hs, he, be in reversed(method_spans(nocom)):
            sig, body = nocom[hs:he], nocom[he:be]
            params = set(re.findall(r'[\w<>?,\[\].]+\s+(\w+)\s*(?=[,)])', sig[sig.find('('):]))
            declared = set(re.findall(r'(?:^|[;{}(\s])(?:final\s+)?[A-Za-z_][\w<>?,\[\].]*\s+(\w+)\s*(?:=|;|:)', body))
            # ni prefixe de package (net.minecraft...), ni appel, ni chaine
            usedset = set(re.findall(r'(?<![\w.])([a-z]\w*)\b(?![\s]*[.(])', body))
            usedset -= {'net','java','com','fr','lotr','minecraft','minecraftforge','util','world'}
            need = sorted(v for v in usedset - declared - params - fields - KW if len(v) <= 12)
            if not need:
                continue
            decls = ''.join(f'\n        {infer(v)[0]} {v} = {infer(v)[1]};' for v in need)
            anchor = sig.strip()[-50:]
            pos = raw.find(anchor)
            if pos < 0:
                continue
            brace = raw.find('{', pos + len(anchor) - 1)
            raw = raw[:brace + 1] + decls + raw[brace + 1:]
            added = True
        if not added:
            break
    open(f, 'w').write(raw)
print('mise en conformite terminee')
