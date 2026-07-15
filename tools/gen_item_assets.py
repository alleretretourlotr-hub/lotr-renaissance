#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Generateur d'assets pour le portage LOTR 1.16.5 - Aller & Retour.

Pour chaque item du MANIFEST :
  1. Copie la texture depuis le repo Legacy en la renommant en snake_case
     (+ le .mcmeta d'animation s'il existe, ex. mithril)
  2. Genere le JSON de modele d'item (handheld pour armes, generated pour le reste)
  3. Ajoute les entrees de traduction en_us.json et fr_fr.json

Usage (depuis la racine du projet lotr-renaissance) :
  python tools/gen_item_assets.py C:\\Dev\\LOTR\\The-Lord-of-the-Rings

Relançable sans risque : ecrase les modeles, fusionne les langues.
"""

import json
import re
import shutil
import sys
from pathlib import Path

# item_id : (texture legacy, type de modele, nom EN, nom FR)
MANIFEST = {
    # --- Lot 1 : Gondor ---
    "gondor_sword": ("swordGondor.png", "handheld", "Gondorian Sword", "Epee du Gondor"),
    "gondor_dagger": ("daggerGondor.png", "handheld", "Gondorian Dagger", "Dague du Gondor"),
    "gondor_dagger_poisoned": ("daggerGondorPoisoned.png", "handheld", "Poisoned Gondorian Dagger", "Dague empoisonnee du Gondor"),
    "gondor_helmet": ("helmetGondor.png", "generated", "Gondorian Helmet", "Casque du Gondor"),
    "gondor_chestplate": ("bodyGondor.png", "generated", "Gondorian Chestplate", "Plastron du Gondor"),
    "gondor_leggings": ("legsGondor.png", "generated", "Gondorian Leggings", "Jambieres du Gondor"),
    "gondor_boots": ("bootsGondor.png", "generated", "Gondorian Boots", "Bottes du Gondor"),
    # --- Lot 2 : materiaux ---
    "bronze": ("bronze.png", "generated", "Bronze Ingot", "Lingot de bronze"),
    "mithril": ("mithril.png", "generated", "Mithril Ingot", "Lingot de mithril"),
    "mithril_mail": ("mithrilMail.png", "generated", "Mithril Mail", "Cotte de mithril"),
    "orc_steel": ("orcSteel.png", "generated", "Orc Steel Ingot", "Lingot d'acier orque"),
    "uruk_steel": ("urukSteel.png", "generated", "Uruk Steel Ingot", "Lingot d'acier uruk"),
    "morgul_steel": ("morgulSteel.png", "generated", "Morgul Steel Ingot", "Lingot d'acier de Morgul"),
    "black_uruk_steel": ("blackUrukSteel.png", "generated", "Black Uruk Steel Ingot", "Lingot d'acier uruk noir"),
    "elf_steel": ("elfSteel.png", "generated", "Elven Steel Ingot", "Lingot d'acier elfique"),
    "dwarf_steel": ("dwarfSteel.png", "generated", "Dwarven Steel Ingot", "Lingot d'acier nain"),
    "blue_dwarf_steel": ("blueDwarfSteel.png", "generated", "Blue Dwarven Steel Ingot", "Lingot d'acier des Nains bleus"),
    "galvorn": ("galvorn.png", "generated", "Galvorn Ingot", "Lingot de galvorn"),
    "gilded_iron": ("gildedIron.png", "generated", "Gilded Iron Ingot", "Lingot de fer dore"),
    "hithlain": ("hithlain.png", "generated", "Hithlain", "Hithlain"),
    "fur": ("wargFur.png", "generated", "Warg Fur", "Fourrure de Warg"),
    "gemsbok_hide": ("gemsbokHide.png", "generated", "Gemsbok Hide", "Peau de gemsbok"),
    "gemsbok_horn": ("gemsbokHorn.png", "generated", "Gemsbok Horn", "Corne de gemsbok"),
    "rhino_horn": ("rhinoHorn.png", "generated", "Rhino Horn", "Corne de rhinoceros"),
    "lion_fur": ("lionFur.png", "generated", "Lion Fur", "Fourrure de lion"),
    "obsidian_shard": ("obsidianShard.png", "generated", "Obsidian Shard", "Eclat d'obsidienne"),
}

# Manifest genere automatiquement (lot 3+) : fusionne avec le MANIFEST ci-dessus
MANIFEST_JSON = Path(__file__).resolve().parent / "items_manifest.json"

TAB_NAME_EN = "The Lord of the Rings"
TAB_NAME_FR = "Le Seigneur des Anneaux"


def main():
    if len(sys.argv) < 2:
        print("Usage : python tools/gen_item_assets.py <chemin du repo Legacy>")
        sys.exit(1)

    legacy = Path(sys.argv[1])
    project = Path(__file__).resolve().parent.parent
    assets = project / "src" / "main" / "resources" / "assets" / "lotr"

    for mf in (MANIFEST_JSON, MANIFEST_JSON.parent / "ranged_manifest.json",
               MANIFEST_JSON.parent / "foodtools_manifest.json",
               MANIFEST_JSON.parent / "drinks_manifest.json",
               MANIFEST_JSON.parent / "minerals_manifest.json",
               MANIFEST_JSON.parent / "flora_manifest.json"):
        if mf.exists():
            generated = json.loads(mf.read_text(encoding="utf-8"))
            for k, v in generated.items():
                MANIFEST[k] = tuple(v)

    legacy_items = legacy / "src" / "main" / "resources" / "assets" / "lotr" / "textures" / "items"
    legacy_armor = legacy / "src" / "main" / "resources" / "assets" / "lotr" / "armor"

    tex_items = assets / "textures" / "item"
    tex_armor = assets / "textures" / "armor"
    models = assets / "models" / "item"
    lang = assets / "lang"
    for d in (tex_items, tex_armor, models, lang):
        d.mkdir(parents=True, exist_ok=True)

    ok, missing = 0, []

    for item_id, (legacy_tex, model_type, name_en, name_fr) in MANIFEST.items():
        if model_type == "precomposed":
            # texture deja livree precomposee dans le projet : modele + lang seulement
            model = {"parent": "item/generated", "textures": {"layer0": "lotr:item/" + item_id}}
            (models / (item_id + ".json")).write_text(
                json.dumps(model, indent=2) + "\n", encoding="utf-8")
            ok += 1
            continue
        src = legacy_items / legacy_tex
        if src.exists():
            shutil.copy2(src, tex_items / (item_id + ".png"))
            ok += 1
            # animation (ex. mithril.png.mcmeta) -> <item_id>.png.mcmeta
            mcmeta = legacy_items / (legacy_tex + ".mcmeta")
            if mcmeta.exists():
                shutil.copy2(mcmeta, tex_items / (item_id + ".png.mcmeta"))
        else:
            missing.append(legacy_tex)

        if model_type == "bow_static":
            base = {
                "parent": "minecraft:item/bow",
                "textures": {"layer0": "lotr:item/" + item_id},
            }
            (models / (item_id + ".json")).write_text(
                json.dumps(base, indent=2) + "\n", encoding="utf-8")
        elif model_type == "bow":
            base = {
                "parent": "minecraft:item/bow",
                "textures": {"layer0": "lotr:item/" + item_id},
                "overrides": [
                    {"predicate": {"pulling": 1}, "model": "lotr:item/" + item_id + "_pulling_0"},
                    {"predicate": {"pulling": 1, "pull": 0.65}, "model": "lotr:item/" + item_id + "_pulling_1"},
                    {"predicate": {"pulling": 1, "pull": 0.9}, "model": "lotr:item/" + item_id + "_pulling_2"},
                ],
            }
            (models / (item_id + ".json")).write_text(
                json.dumps(base, indent=2) + "\n", encoding="utf-8")
            for i in range(3):
                pull_model = {
                    "parent": "lotr:item/" + item_id,
                    "textures": {"layer0": "lotr:item/" + item_id + "_pulling_" + str(i)},
                }
                (models / (item_id + "_pulling_" + str(i) + ".json")).write_text(
                    json.dumps(pull_model, indent=2) + "\n", encoding="utf-8")
                pull_src = legacy_items / (legacy_tex[:-4] + "_pull_" + str(i) + ".png")
                if pull_src.exists():
                    shutil.copy2(pull_src, tex_items / (item_id + "_pulling_" + str(i) + ".png"))
                else:
                    missing.append(pull_src.name)
        else:
            model = {
                "parent": "item/" + model_type,
                "textures": {"layer0": "lotr:item/" + item_id},
            }
            (models / (item_id + ".json")).write_text(
                json.dumps(model, indent=2) + "\n", encoding="utf-8")

    # copie integrale des textures d'armure portee du Legacy,
    # renommees en snake_case (contrainte 1.16.5 : chemins en minuscules)
    armor_count = 0
    for src in legacy_armor.glob("*.png"):
        dest_name = re.sub(r"(?<=[a-z0-9])(?=[A-Z])", "_", src.stem).lower() + ".png"
        shutil.copy2(src, tex_armor / dest_name)
        armor_count += 1
    print("Textures armure portee : {}".format(armor_count))

    # --- blocs (Phase 2) : copie des textures referencees par les modeles + langues ---
    blocks_lang = Path(__file__).resolve().parent / "blocks_lang.json"
    tex_blocks = assets / "textures" / "block"
    legacy_blocks = legacy / "src" / "main" / "resources" / "assets" / "lotr" / "textures" / "blocks"
    models_block = assets / "models" / "block"
    if models_block.exists():
        tex_blocks.mkdir(parents=True, exist_ok=True)
        needed = set()
        for mf2 in models_block.glob("*.json"):
            data = json.loads(mf2.read_text(encoding="utf-8"))
            for tex_ref in data.get("textures", {}).values():
                if tex_ref.startswith("lotr:block/"):
                    needed.add(tex_ref.split("/", 1)[1])
        # index legacy : nom snake -> fichier reel (souvent camelCase)
        legacy_index = {}
        for f in legacy_blocks.glob("*.png"):
            snake = re.sub(r"(?<=[a-z0-9])(?=[A-Z])", "_", f.stem).lower()
            legacy_index.setdefault(snake, f)
        copied_blocks = 0
        for t in sorted(needed):
            srcp = legacy_index.get(t)
            if srcp is not None:
                shutil.copy2(srcp, tex_blocks / (t + ".png"))
                # animation (.mcmeta) : indispensable pour mithril, edhelvir...
                mcmeta = srcp.with_name(srcp.name + ".mcmeta")
                if mcmeta.exists():
                    shutil.copy2(mcmeta, tex_blocks / (t + ".png.mcmeta"))
                copied_blocks += 1
            else:
                missing.append("block/" + t)
        print("Textures de blocs : {}".format(copied_blocks))
    # portes : textures d'items (plates) depuis items/ du Legacy
    door_map = Path(__file__).resolve().parent / "door_item_textures.json"
    if door_map.exists():
        dm = json.loads(door_map.read_text(encoding="utf-8"))
        door_count = 0
        for item_id, legacy_name in dm.items():
            srcp = legacy_items / (legacy_name + ".png")
            if srcp.exists():
                shutil.copy2(srcp, tex_items / (item_id + ".png"))
                door_count += 1
            else:
                missing.append("door/" + legacy_name)
        print("Textures de portes : {}".format(door_count))
    for lang_file in Path(__file__).resolve().parent.glob("*_lang.json"):
        dl = json.loads(lang_file.read_text(encoding="utf-8"))
        merge_lang(lang / "en_us.json", {}, dl.get("en", {}))
        merge_lang(lang / "fr_fr.json", {}, dl.get("fr", {}))


    merge_lang(lang / "en_us.json",
               {"itemGroup.lotr": TAB_NAME_EN},
               {("item.lotr." + i): en for i, (_, _, en, _) in MANIFEST.items()})
    merge_lang(lang / "fr_fr.json",
               {"itemGroup.lotr": TAB_NAME_FR},
               {("item.lotr." + i): fr for i, (_, _, _, fr) in MANIFEST.items()})

    print("Textures copiees : {}".format(ok))
    print("Modeles generes  : {}".format(len(MANIFEST)))
    if missing:
        print("ATTENTION, textures introuvables : {}".format(", ".join(missing)))


def merge_lang(path, extra, entries):
    data = {}
    if path.exists():
        data = json.loads(path.read_text(encoding="utf-8"))
    data.update(extra)
    data.update(entries)
    path.write_text(json.dumps(data, indent=2, ensure_ascii=False, sort_keys=True) + "\n",
                    encoding="utf-8")


if __name__ == "__main__":
    main()
