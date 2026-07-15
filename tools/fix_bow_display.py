#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""Ajoute les transformations d'affichage vanilla (tenue en main) aux modeles
d'arcs generes. Idempotent - a relancer apres chaque gen_item_assets.py.
Usage : python tools\\fix_bow_display.py"""
import json, glob, os

DISPLAY = {
    "thirdperson_righthand": {"rotation": [-80, 260, -40], "translation": [-1, -2, 2.5], "scale": [0.9, 0.9, 0.9]},
    "thirdperson_lefthand": {"rotation": [-80, -280, 40], "translation": [-1, -2, 2.5], "scale": [0.9, 0.9, 0.9]},
    "firstperson_righthand": {"rotation": [0, -90, 25], "translation": [1.13, 3.2, 1.13], "scale": [0.68, 0.68, 0.68]},
    "firstperson_lefthand": {"rotation": [0, 90, -25], "translation": [1.13, 3.2, 1.13], "scale": [0.68, 0.68, 0.68]},
}

root = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))),
                    "src", "main", "resources", "assets", "lotr", "models", "item")
manifest = json.load(open(os.path.join(os.path.dirname(os.path.abspath(__file__)), "ranged_manifest.json")))
fixed = 0
for iid, entry in manifest.items():
    if entry[1] not in ("bow", "bow_static"):
        continue
    for path in glob.glob(os.path.join(root, iid + "*.json")):
        data = json.load(open(path))
        if "display" not in data:
            data["display"] = DISPLAY
            json.dump(data, open(path, "w"), indent=2)
            fixed += 1
print("modeles d'arcs corriges:", fixed)
