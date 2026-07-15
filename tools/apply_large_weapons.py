#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""PORT du systeme LOTRRenderLargeItem du Legacy :
les armes ayant une texture dans textures/items/large/ (32x32, rendu x2)
ou large2/ (48x48, rendu x3) utilisent cette texture haute resolution
et des transformations d'affichage agrandies en main (1re et 3e personne).
L'inventaire et le sol restent a taille normale, comme l'original.

Usage : python tools\\apply_large_weapons.py C:\\Dev\\LOTR\\The-Lord-of-the-Rings
A relancer apres chaque gen_item_assets.py (idempotent)."""
import json, os, re, shutil, sys, glob

LEGACY = sys.argv[1] if len(sys.argv) > 1 else r"C:\Dev\LOTR\The-Lord-of-the-Rings"
LEGACY_ITEMS = os.path.join(LEGACY, "src", "main", "resources", "assets", "lotr", "textures", "items")
ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
ASSETS = os.path.join(ROOT, "src", "main", "resources", "assets", "lotr")
MODELS = os.path.join(ASSETS, "models", "item")
TEXTURES = os.path.join(ASSETS, "textures", "item")

FOLDERS = {"large": 2.0, "large2": 3.0}

def snake(s):
    return re.sub(r"(?<=[a-z0-9])(?=[A-Z])", "_", s).lower()

def handheld_display(f):
    ts = round(0.85 * f, 3)
    fs = min(round(0.68 * f, 3), 1.6)
    return {
        "thirdperson_righthand": {"rotation": [0, -90, 55], "translation": [0, 4.0, 0.5], "scale": [ts, ts, ts]},
        "thirdperson_lefthand": {"rotation": [0, 90, -55], "translation": [0, 4.0, 0.5], "scale": [ts, ts, ts]},
        "firstperson_righthand": {"rotation": [0, -90, 25], "translation": [1.13, 3.2, 1.13], "scale": [fs, fs, fs]},
        "firstperson_lefthand": {"rotation": [0, 90, -25], "translation": [1.13, 3.2, 1.13], "scale": [fs, fs, fs]},
    }

def bow_display(f):
    ts = round(0.9 * f, 3)
    fs = min(round(0.68 * f, 3), 1.5)
    return {
        "thirdperson_righthand": {"rotation": [-80, 260, -40], "translation": [-1, -2, 2.5], "scale": [ts, ts, ts]},
        "thirdperson_lefthand": {"rotation": [-80, -280, 40], "translation": [-1, -2, 2.5], "scale": [ts, ts, ts]},
        "firstperson_righthand": {"rotation": [0, -90, 25], "translation": [1.13, 3.2, 1.13], "scale": [fs, fs, fs]},
        "firstperson_lefthand": {"rotation": [0, 90, -25], "translation": [1.13, 3.2, 1.13], "scale": [fs, fs, fs]},
    }

# manifests : legacy field -> notre id + type de modele
entries = {}
for mf in glob.glob(os.path.join(ROOT, "tools", "*_manifest.json")):
    try:
        for iid, e in json.load(open(mf)).items():
            entries[e[0][:-4]] = (iid, e[1])
    except Exception:
        pass

os.makedirs(os.path.join(TEXTURES, "large"), exist_ok=True)
patched, missing_model = 0, []
for folder, factor in FOLDERS.items():
    src_dir = os.path.join(LEGACY_ITEMS, folder)
    if not os.path.isdir(src_dir):
        continue
    for png in os.listdir(src_dir):
        if not png.endswith(".png"):
            continue
        base = png[:-4]
        # etats de tension d'arc : gondorBow_pull_0
        pull = re.match(r"(.+)_pull_(\d)$", base)
        legacy_field = pull.group(1) if pull else base
        if legacy_field not in entries:
            continue
        iid, mtype = entries[legacy_field]
        # copier la texture large
        tex_name = snake(base if not pull else pull.group(1)) + ("_pulling_" + pull.group(2) if pull else "")
        shutil.copy(os.path.join(src_dir, png), os.path.join(TEXTURES, "large", tex_name + ".png"))
        if pull:
            model_files = [os.path.join(MODELS, iid + "_pulling_" + pull.group(2) + ".json")]
        else:
            model_files = [os.path.join(MODELS, iid + ".json")]
        for mpath in model_files:
            if not os.path.exists(mpath):
                missing_model.append(os.path.basename(mpath))
                continue
            data = json.load(open(mpath))
            data.setdefault("textures", {})["layer0"] = "lotr:item/large/" + tex_name
            if not pull:
                data["display"] = bow_display(factor) if mtype in ("bow", "bow_static") \
                    else handheld_display(factor)
            json.dump(data, open(mpath, "w"), indent=2)
            patched += 1

print("modeles agrandis:", patched, "| modeles introuvables:", len(missing_model))
if missing_model[:5]:
    print("  ex:", missing_model[:5])
