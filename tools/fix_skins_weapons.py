#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""Refection v3 : peaux propres (27 dossiers) + tailles d'armes par categorie."""
import glob, json, os, re
from PIL import Image

LEG = "/home/claude/lotr-legacy/src/main/resources/assets/lotr/mob"
REN = "/home/claude/lotr-renaissance"
OUT = "/home/claude/megalot"
ENT = f"{OUT}/src/main/resources/assets/lotr/textures/entity"
MOD = f"{OUT}/src/main/resources/assets/lotr/models/item"

# ---------- 1. PEAUX ----------
def convert_legacy_skin(img):
    out = img.copy()
    # purge des zones de superposition 64x64 (veste, manches, pantalons) :
    # jamais lues par le rendu 1.7.10, elles contiennent des dechets qui
    # deviennent des vetements fantomes sur PlayerModel.
    out.paste(Image.new("RGBA", (64, 32), (0, 0, 0, 0)), (0, 32))
    def cp(x, y, dx, dy, w, h):
        region = img.crop((x, y, x + w, y + h)).transpose(Image.FLIP_LEFT_RIGHT)
        out.paste(region, (x + dx, y + dy))
    cp(4, 16, 16, 32, 4, 4);  cp(8, 16, 16, 32, 4, 4)      # jambe G dessus/dessous
    cp(0, 20, 24, 32, 4, 12); cp(4, 20, 16, 32, 4, 12)     # jambe G faces
    cp(8, 20, 8, 32, 4, 12);  cp(12, 20, 16, 32, 4, 12)
    cp(44, 16, -8, 32, 4, 4); cp(48, 16, -8, 32, 4, 4)     # bras G dessus/dessous
    cp(40, 20, 0, 32, 4, 12); cp(44, 20, -8, 32, 4, 12)    # bras G faces
    cp(48, 20, -16, 32, 4, 12); cp(52, 20, -8, 32, 4, 12)
    return out

def limb_missing(img):
    for box in ((32, 48, 48, 64), (16, 48, 32, 64)):
        d = list(img.crop(box).getdata())
        if sum(1 for p in d if p[3] > 128) / len(d) < 0.3:
            return True
    return False

FOLDERS = {  # destination -> source Legacy
    "gondor_male": "gondor/gondor_male", "rohan_male": "rohan/rohan_male",
    "shieldmaiden": "rohan/shieldmaiden", "galadhrim_male": "elf/galadhrim_male",
    "high_elf_male": "elf/highElf_male", "wood_elf_male": "elf/woodElf_male",
    "orc": "orc/orc", "uruk_hai": "orc/urukHai",
    "dwarf_male": "dwarf/dwarf_male", "blue_mountains_male": "dwarf/blueMountains_male",
    "hobbit_male": "hobbit/hobbit_male", "bree_male": "bree/bree_male",
    "ranger_male": "ranger/ranger_male", "dale_soldier": "dale/dale_soldier",
    "dale_male": "dale/dale_male", "dorwinion_male": "dorwinion/dorwinion_male",
    "dunlending_male": "dunland/dunlending_male", "dunland_berserker": "dunland/berserker",
    "hillman_male": "hillman/hillman_male", "near_harad_warrior": "nearHarad/warrior",
    "haradrim_male": "nearHarad/haradrim_male", "easterling_male": "rhun/easterling_male",
    "moredain_male": "moredain/moredain_male", "tauredain_male": "tauredain/tauredain_male",
    "half_troll": "halfTroll/halfTroll",
}
total = fixed = 0
for dest, src in FOLDERS.items():
    sp = f"{LEG}/{src}"
    files = sorted([f for f in os.listdir(sp) if re.fullmatch(r"\d+\.png", f)],
                   key=lambda f: int(f.split(".")[0]))
    dp = f"{ENT}/{dest}"
    os.makedirs(dp, exist_ok=True)
    for i, f in enumerate(files):
        img = Image.open(f"{sp}/{f}").convert("RGBA")
        if img.size != (64, 64):
            canvas = Image.new("RGBA", (64, 64), (0, 0, 0, 0))
            canvas.paste(img, (0, 0))
            img = canvas
        if limb_missing(img):
            img = convert_legacy_skin(img)
            fixed += 1
        img.save(f"{dp}/{i}.png")
        total += 1
print(f"peaux : {total} traitees, {fixed} converties/nettoyees, {len(FOLDERS)} dossiers")

# ---------- 2. TAILLES D'ARMES ----------
def handheld(ts, ty, fs):
    return {
        "thirdperson_righthand": {"rotation": [0, -90, 55], "translation": [0, ty, 0.5], "scale": [ts, ts, ts]},
        "thirdperson_lefthand": {"rotation": [0, 90, -55], "translation": [0, ty, 0.5], "scale": [ts, ts, ts]},
        "firstperson_righthand": {"rotation": [0, -90, 25], "translation": [1.13, 3.2, 1.13], "scale": [fs, fs, fs]},
        "firstperson_lefthand": {"rotation": [0, 90, -25], "translation": [1.13, 3.2, 1.13], "scale": [fs, fs, fs]},
    }
BOW = {
    "thirdperson_righthand": {"rotation": [-80, 260, -40], "translation": [-1, -2, 2.5], "scale": [0.9, 0.9, 0.9]},
    "thirdperson_lefthand": {"rotation": [-80, -280, 40], "translation": [-1, -2, 2.5], "scale": [0.9, 0.9, 0.9]},
    "firstperson_righthand": {"rotation": [0, -90, 25], "translation": [1.13, 3.2, 1.13], "scale": [0.68, 0.68, 0.68]},
    "firstperson_lefthand": {"rotation": [0, 90, -25], "translation": [1.13, 3.2, 1.13], "scale": [0.68, 0.68, 0.68]},
}
CATS = [
    (re.compile(r"_dagger$"), handheld(0.6, 3.0, 0.5), "dague"),
    (re.compile(r"_(spear|pike|polearm|lance|longspear|poleaxe|halberd|trident)$"),
     handheld(1.25, 4.5, 0.85), "hampe"),
    (re.compile(r"(?<!cross)_bow$|(?<!cross)bow$"), BOW, "arc"),
]
os.makedirs(MOD, exist_ok=True)
counts = {}
for path in glob.glob(f"{REN}/src/main/resources/assets/lotr/models/item/*.json"):
    name = os.path.basename(path)[:-5]
    if "pulling" in name or "crossbow" in name:
        continue
    for rx, display, label in CATS:
        if rx.search(name):
            data = json.load(open(path))
            data["display"] = display
            json.dump(data, open(f"{MOD}/{name}.json", "w"), indent=2)
            counts[label] = counts.get(label, 0) + 1
            break
print("modeles d'armes recalibres :", counts)
