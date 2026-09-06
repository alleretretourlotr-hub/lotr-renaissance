"""Audit complet des structures : tout ce qui est verifiable sans compilateur."""
import glob, re

D = 'lotr-renaissance/src/main/java/fr/alleretretour/lotr/world/structure/'
BASE = D + 'LOTRStructureBase.java'

def nocom(s):
    return re.sub(r'//[^\n]*', '', re.sub(r'/\*.*?\*/', '', s, flags=re.S))

base_src = nocom(open(BASE).read())
base_methods = set(re.findall(r'(?:protected|public|private)[\w<>?,\[\] .]*\s(\w+)\s*\(', base_src))
base_fields = set(re.findall(r'(?:protected|private|public)\s+(?:static\s+)?(?:final\s+)?'
                             r'[\w<>?,\[\] .]+\s+(\w+)\s*[=;]', base_src))

issues = {}
def add(f, msg):
    issues.setdefault(f.split('/')[-1], []).append(msg)

files = sorted(glob.glob(D + '*.java'))
classes = {re.search(r'class (LOTRStructure\w+)', open(f).read()).group(1) for f in files}

for f in files:
    raw = open(f).read()
    src = nocom(raw)
    # 1) accolades / parentheses
    if src.count('{') != src.count('}'):
        add(f, 'accolades desequilibrees')
    if src.count('(') != src.count(')'):
        add(f, 'parentheses desequilibrees')
    # 2) classe parente presente
    m = re.search(r'class \w+ extends (\w+)', src)
    if m and m.group(1) not in classes and m.group(1) != 'Feature':
        add(f, f'parent absent : {m.group(1)}')
    # 2b) try sans catch (un catch a pu etre commente)
    for m in re.finditer(r'(?<![\w.])try\s*\{', src):
        tail = src[m.end():m.end() + 4000]
        depth = 1
        for i, ch in enumerate(tail):
            if ch == '{': depth += 1
            elif ch == '}':
                depth -= 1
                if depth == 0:
                    if not re.match(r'\s*(catch|finally)\b', tail[i + 1:i + 40]):
                        add(f, 'try sans catch')
                    break

    # 3) symboles Legacy actifs
    for sym in re.findall(r'(?<![\w."])(LOTRMod|LOTRBiomeGen\w*|LOTRItem\w*|LOTRBlock\w*|'
                          r'LOTRChestContents|LOTRFoods|setBlockAndMetadata|setBlockAndNotify\w*|'
                          r'getBiomeGenForCoords|restrictions|worldObj)\b', src):
        add(f, f'symbole Legacy actif : {sym}')
    # 4) methodes du socle appelees mais inexistantes
    called = set(re.findall(r'(?<![\w.])([a-z]\w*)\s*\(', src))
    local = set(re.findall(r'(?:protected|public|private)[\w<>?,\[\] .]*\s(\w+)\s*\(', src))
    unknown = called - base_methods - local - {
        'if','for','while','switch','return','new','super','this','equals','valueOf','abs',
        'min','max','signum','round','floor','sqrt','nextInt','nextBoolean','nextFloat',
        'nextDouble','getX','getY','getZ','offset','above','below','west','east','north',
        'south','getBlockState','setBlock','isEmptyBlock','getHeight','getBlock','getMaterial',
        'isSolidRender','isAir','defaultBlockState','setValue','hasProperty','getClockWise',
        'from2DDataValue','getStepX','getStepZ','addFreshEntity','create','moveTo','finalizeSpawn',
        'getCurrentDifficultyAt','blockPosition','isReplaceable','canSustainPlant','getValue',
        'setPersistenceRequired','restrictTo','getLevel','toRadians','sin','cos','getEntity',
        'spawnAtLocation','remove','isPickable','getRandom','random','getDescription','name',
        'ordinal','values','contains','add','size','get','put','computeIfAbsent','getOrDefault',
        'toLowerCase','substring','indexOf','startsWith','endsWith','length','isEmpty','copy',
        'shrink','setItem','getItem','playSound','addParticle','broadcastEntityEvent','ceil',
        'getSeconds','setSecondsOnFire','hurt','addEffect','distanceToSqr','isSolid','getId',
        'nextLong','getBlockEntity','setLootTable','getPropertyForFace','asItem','getFrameTime',
        'clamp','signum2','isSpectator','isCreative','getUUID','equalsIgnoreCase',
    }
    for u in sorted(unknown):
        add(f, f'methode inconnue : {u}()')

print(f'{len(files)} fichiers audites, {len(issues)} avec anomalie')
for name, msgs in list(issues.items())[:12]:
    uniq = sorted(set(msgs))
    print(f'  {name}: {uniq[:3]}')
