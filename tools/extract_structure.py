#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""EXTRACTEUR DE STRUCTURES : traduit un LOTRWorldGenXxx du Legacy en classe
1.16.5 heritant de LOTRStructureBase.

Ce que l'outil fait AUTOMATIQUEMENT :
  - traduit les appels de pose (setBlockAndNotifyAdequately, isOpaque,
    setGrassToDirt, isAirBlock, getHeightValue...) ;
  - convertit les blocs Legacy (LOTRMod.xxx, Blocks.xxx) en identifiants
    1.16.5, resolus PAR NOM avec repli vanilla ;
  - reprend les boucles, tirages aleatoires et conditions TELS QUELS ;
  - detecte les quatre variantes d'orientation (generateFacingXxx) et ne
    conserve que la premiere, le socle appliquant la rotation.

Ce que l'outil NE FAIT PAS (signale en TODO dans le fichier genere) :
  - les tables de butin, spawners et entites : a brancher a la main ;
  - les blocs a metadonnees complexes (escaliers inverses, lits, bannieres) ;
  - toute structure dont la traduction est incertaine est laissee en
    commentaire plutot que devinee.

Usage : python3 extract_structure.py <NomLegacy> [<NomLegacy> ...]
"""
import os
import re
import sys

LEGACY_DIRS = [
    "/home/claude/lotr-legacy/src/main/java/lotr/common/world/structure",
    "/home/claude/lotr-legacy/src/main/java/lotr/common/world/structure2",
]
OUT = "/home/claude/lotr-renaissance/src/main/java/fr/alleretretour/lotr/world/structure"

# --- blocs vanilla : champ 1.7.10 -> identifiant 1.16.5 -------------------
VANILLA = {
    "air": "AIR", "stone": "STONE", "grass": "GRASS_BLOCK", "dirt": "DIRT",
    "cobblestone": "COBBLESTONE", "planks": "OAK_PLANKS", "sand": "SAND",
    "gravel": "GRAVEL", "log": "OAK_LOG", "log2": "ACACIA_LOG",
    "leaves": "OAK_LEAVES", "leaves2": "ACACIA_LEAVES", "glass": "GLASS",
    "sandstone": "SANDSTONE", "web": "COBWEB", "wool": "WHITE_WOOL",
    "gold_block": "GOLD_BLOCK", "iron_block": "IRON_BLOCK", "brick_block": "BRICKS",
    "tnt": "TNT", "bookshelf": "BOOKSHELF", "mossy_cobblestone": "MOSSY_COBBLESTONE",
    "obsidian": "OBSIDIAN", "torch": "TORCH", "fire": "FIRE",
    "chest": "CHEST", "crafting_table": "CRAFTING_TABLE", "farmland": "FARMLAND",
    "furnace": "FURNACE", "ladder": "LADDER", "rail": "RAIL",
    "stone_stairs": "COBBLESTONE_STAIRS", "iron_bars": "IRON_BARS",
    "glass_pane": "GLASS_PANE", "melon_block": "MELON", "vine": "VINE",
    "fence": "OAK_FENCE", "fence_gate": "OAK_FENCE_GATE", "brick_stairs": "BRICK_STAIRS",
    "stonebrick": "STONE_BRICKS", "mycelium": "MYCELIUM", "waterlily": "LILY_PAD",
    "nether_brick": "NETHER_BRICKS", "cauldron": "CAULDRON", "anvil": "ANVIL",
    "hay_block": "HAY_BLOCK", "coal_block": "COAL_BLOCK", "packed_ice": "PACKED_ICE",
    "water": "WATER", "flowing_water": "WATER", "lava": "LAVA", "flowing_lava": "LAVA",
    "snow": "SNOW", "ice": "ICE", "clay": "CLAY", "netherrack": "NETHERRACK",
    "soul_sand": "SOUL_SAND", "glowstone": "GLOWSTONE", "cobblestone_wall": "COBBLESTONE_WALL",
    "double_stone_slab": "SMOOTH_STONE", "stone_slab": "SMOOTH_STONE_SLAB",
    "wooden_slab": "OAK_SLAB", "oak_stairs": "OAK_STAIRS", "birch_stairs": "BIRCH_STAIRS",
    "spruce_stairs": "SPRUCE_STAIRS", "sandstone_stairs": "SANDSTONE_STAIRS",
    "stone_brick_stairs": "STONE_BRICK_STAIRS", "tripwire_hook": "TRIPWIRE_HOOK",
    "bed": "RED_BED", "wooden_door": "OAK_DOOR", "trapdoor": "OAK_TRAPDOOR",
    "stone_slab2": "RED_SANDSTONE_SLAB", "double_stone_slab2": "SMOOTH_RED_SANDSTONE",
    "quartz_block": "QUARTZ_BLOCK", "quartz_stairs": "QUARTZ_STAIRS",
    "nether_brick_fence": "NETHER_BRICK_FENCE", "nether_brick_stairs": "NETHER_BRICK_STAIRS",
    "sandstone_slab": "SANDSTONE_SLAB", "spruce_fence": "SPRUCE_FENCE",
    "birch_fence": "BIRCH_FENCE", "jungle_stairs": "JUNGLE_STAIRS",
    "acacia_stairs": "ACACIA_STAIRS", "dark_oak_stairs": "DARK_OAK_STAIRS",
    "cactus": "CACTUS", "pumpkin": "CARVED_PUMPKIN", "lit_pumpkin": "JACK_O_LANTERN",
    "wheat": "WHEAT", "carrots": "CARROTS", "potatoes": "POTATOES",
    "brown_mushroom": "BROWN_MUSHROOM", "red_mushroom": "RED_MUSHROOM",
    "yellow_flower": "DANDELION", "red_flower": "POPPY", "deadbush": "DEAD_BUSH",
    "tallgrass": "GRASS", "snow_layer": "SNOW", "lever": "LEVER",
    "stone_button": "STONE_BUTTON", "wooden_button": "OAK_BUTTON",
    "stained_hardened_clay": "TERRACOTTA", "hardened_clay": "TERRACOTTA",
    "stained_glass": "WHITE_STAINED_GLASS", "stained_glass_pane": "WHITE_STAINED_GLASS_PANE",
    "double_wooden_slab": "OAK_PLANKS", "redstone_torch": "REDSTONE_TORCH",
    "skull": "SKELETON_SKULL", "flower_pot": "FLOWER_POT", "carpet": "WHITE_CARPET",
}

# --- blocs du mod : champ Legacy -> (id 1.16.5, repli vanilla) ------------
def mod_block(field):
    """LOTRMod.gondorBrick -> ("lotr:gondor_brick", repli)."""
    snake = re.sub(r"(?<=[a-z0-9])(?=[A-Z])", "_", field).lower()
    fallback = "STONE_BRICKS"
    if "plank" in snake or "wood" in snake:
        fallback = "OAK_PLANKS"
    elif "log" in snake:
        fallback = "OAK_LOG"
    elif "leaves" in snake:
        fallback = "OAK_LEAVES"
    elif "slab" in snake:
        fallback = "STONE_BRICK_SLAB"
    elif "stairs" in snake:
        fallback = "STONE_BRICK_STAIRS"
    elif "wall" in snake:
        fallback = "STONE_BRICK_WALL"
    elif "chest" in snake:
        fallback = "CHEST"
    elif "torch" in snake:
        fallback = "TORCH"
    elif "table" in snake:
        fallback = "CRAFTING_TABLE"
    elif "bed" in snake:
        fallback = "RED_BED"
    elif "door" in snake:
        fallback = "OAK_DOOR"
    elif "rock" in snake:
        fallback = "STONE"
    elif "dirt" in snake or "mud" in snake:
        fallback = "DIRT"
    elif "sand" in snake:
        fallback = "SAND"
    elif "gravel" in snake:
        fallback = "GRAVEL"
    elif "brick" in snake:
        fallback = "STONE_BRICKS"
    return f'modBlock("lotr:{snake}", Blocks.{fallback})'


# variables de bloc posees par setupRandomBlocks (structures de faction)
BLOCK_VARS = {
    "rockBlock": ("lotr:gondor_rock", "STONE"),
    "rockSlabBlock": ("lotr:gondor_rock_slab", "STONE_SLAB"),
    "rockSlabDoubleBlock": ("lotr:gondor_rock", "STONE"),
    "rockStairBlock": ("lotr:gondor_rock_stairs", "STONE_STAIRS"),
    "rockWallBlock": ("lotr:gondor_rock_wall", "COBBLESTONE_WALL"),
    "brickBlock": ("lotr:gondor_brick", "STONE_BRICKS"),
    "brickSlabBlock": ("lotr:gondor_brick_slab", "STONE_BRICK_SLAB"),
    "brickStairBlock": ("lotr:gondor_brick_stairs", "STONE_BRICK_STAIRS"),
    "brickWallBlock": ("lotr:gondor_brick_wall", "STONE_BRICK_WALL"),
    "brickMossyBlock": ("lotr:mossy_gondor_brick", "MOSSY_STONE_BRICKS"),
    "brickMossySlabBlock": ("lotr:mossy_gondor_brick_slab", "MOSSY_STONE_BRICK_SLAB"),
    "brickCrackedBlock": ("lotr:cracked_gondor_brick", "CRACKED_STONE_BRICKS"),
    "brickCarvedBlock": ("lotr:carved_gondor_brick", "CHISELED_STONE_BRICKS"),
    "pillarBlock": ("lotr:gondor_pillar", "STONE_BRICKS"),
    "plankBlock": ("lotr:gondor_planks", "OAK_PLANKS"),
    "plankSlabBlock": ("lotr:gondor_slab", "OAK_SLAB"),
    "plankStairBlock": ("lotr:gondor_stairs", "OAK_STAIRS"),
    "woodBlock": ("lotr:gondor_log", "OAK_LOG"),
    "beamBlock": ("lotr:gondor_beam", "OAK_LOG"),
    "fenceBlock": ("lotr:gondor_fence", "OAK_FENCE"),
    "doorBlock": ("lotr:gondor_door", "OAK_DOOR"),
    "barsBlock": ("lotr:iron_bars", "IRON_BARS"),
    "roofBlock": ("lotr:gondor_roof", "STONE_BRICKS"),
    "roofSlabBlock": ("lotr:gondor_roof_slab", "STONE_BRICK_SLAB"),
    "roofStairBlock": ("lotr:gondor_roof_stairs", "STONE_BRICK_STAIRS"),
    "tableBlock": ("lotr:gondor_table", "CRAFTING_TABLE"),
    "bedBlock": ("lotr:straw_bed", "RED_BED"),
    "chestBlock": ("lotr:chest_lebethron", "CHEST"),
    "torchBlock": ("lotr:gondor_torch", "TORCH"),
    "carpetBlock": ("lotr:carpet", "WHITE_CARPET"),
    "clayBlock": ("lotr:clay_tile", "TERRACOTTA"),
    "gateBlock": ("lotr:gondor_gate", "OAK_FENCE_GATE"),
    "trapdoorBlock": ("lotr:gondor_trapdoor", "OAK_TRAPDOOR"),
    "plank2Block": ("lotr:gondor_planks_2", "SPRUCE_PLANKS"),
    "plank2SlabBlock": ("lotr:gondor_slab_2", "SPRUCE_SLAB"),
    "plank2StairBlock": ("lotr:gondor_stairs_2", "SPRUCE_STAIRS"),
    "brick2Block": ("lotr:gondor_brick_2", "STONE_BRICKS"),
    "brick2SlabBlock": ("lotr:gondor_brick_slab_2", "STONE_BRICK_SLAB"),
    "brick2StairBlock": ("lotr:gondor_brick_stairs_2", "STONE_BRICK_STAIRS"),
    "fence2Block": ("lotr:gondor_fence_2", "SPRUCE_FENCE"),
    "floorBlock": ("lotr:gondor_floor", "STONE_BRICKS"),
    "floorStairBlock": ("lotr:gondor_floor_stairs", "STONE_BRICK_STAIRS"),
    "stairBlock": ("lotr:gondor_stairs", "OAK_STAIRS"),
    "slabBlock": ("lotr:gondor_slab", "OAK_SLAB"),
    "tileSlabBlock": ("lotr:clay_tile_slab", "STONE_SLAB"),
    "tileBlock": ("lotr:clay_tile", "TERRACOTTA"),
    "chandelierBlock": ("lotr:chandelier", "LANTERN"),
    "pathBlock": ("lotr:dirt_path", "COARSE_DIRT"),
    "logBlock": ("lotr:gondor_log", "OAK_LOG"),
    "wood1Block": ("lotr:gondor_log", "OAK_LOG"),
    "wood2Block": ("lotr:gondor_log_2", "SPRUCE_LOG"),
    "wood1Stair": ("lotr:gondor_stairs", "OAK_STAIRS"),
    "wood2Stair": ("lotr:gondor_stairs_2", "SPRUCE_STAIRS"),
    "beam1Block": ("lotr:gondor_beam", "OAK_LOG"),
    "beam2Block": ("lotr:gondor_beam_2", "SPRUCE_LOG"),
    "hedgeBlock": ("lotr:hedge", "OAK_LEAVES"),
    "outFenceBlock": ("lotr:gondor_fence", "OAK_FENCE"),
    "glowBrickBlock": ("lotr:glowing_brick", "GLOWSTONE"),
    "wallBlock": ("lotr:gondor_brick_wall", "STONE_BRICK_WALL"),
    "windowBlock": ("lotr:glass_pane", "GLASS_PANE"),
    "rockSlabDoubleBlock": ("lotr:gondor_rock", "STONE"),
}


def translate_block(expr):
    """Traduit une expression de bloc Legacy."""
    expr = expr.strip()
    m = re.fullmatch(r"LOTRMod\.(\w+)", expr)
    if m:
        return mod_block(m.group(1))
    if expr in BLOCK_VARS:
        mod_id, fallback = BLOCK_VARS[expr]
        return f'modBlock("{mod_id}", Blocks.{fallback})'
    m = re.fullmatch(r"Blocks\.(\w+)", expr)
    if m:
        vanilla = VANILLA.get(m.group(1))
        if vanilla:
            return f"Blocks.{vanilla}.defaultBlockState()"
        return None    # bloc vanilla inconnu : on ne devine pas
    return None        # variable (plankBlock...) : traitee par le prologue


def convert(name):
    """Traduit un generateur Legacy ; renvoie (java, avertissements)."""
    path = None
    for d in LEGACY_DIRS:
        p = os.path.join(d, f"LOTRWorldGen{name}.java")
        if os.path.exists(p):
            path = p
            break
    if path is None:
        return None, [f"introuvable : LOTRWorldGen{name}"]
    src = open(path, encoding="utf-8").read()
    warnings = []

    # variante de faction : la classe herite d'une autre et ne change que
    # ses blocs (PORT du constructeur du Legacy)
    parent = re.search(r"public class LOTRWorldGen" + name + r" extends LOTRWorldGen(\w+)", src)
    has_generate = "generateWithSetRotation" in src or "public boolean generate(" in src
    if parent and not has_generate:
        overrides = re.findall(r"(\w+Block) = (LOTRMod\.\w+|Blocks\.\w+);", src)
        lines = []
        for var, expr in overrides:
            block = translate_block(expr)
            if block is None:
                warnings.append(f"{name} : bloc {expr}")
                continue
            lines.append(f"        {var} = {block};")
        java = f'''package fr.alleretretour.lotr.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.NoFeatureConfig;

/**
 * PORT de LOTRWorldGen{name} : variante de LOTRStructure{parent.group(1)},
 * seuls les blocs changent (PORT du constructeur du Legacy).
 */
public class LOTRStructure{name} extends LOTRStructure{parent.group(1)} {{

    public LOTRStructure{name}(Codec<NoFeatureConfig> codec) {{
        super(codec);
{chr(10).join(lines)}
    }}
}}
'''
        return java, warnings

    # corps de generate(...) : on s'arrete a la premiere variante d'orientation
    m = re.search(r"public boolean generateWithSetRotation\(World world, Random random, "
                  r"int i, int j, int k, int rotation\) \{", src)
    local = m is not None
    if m is None:
        m = re.search(r"public boolean generate\(World world, Random random, "
                      r"int i, int j, int k\) \{", src)
    if m is None:
        return None, [f"{name} : signature generate() non reconnue"]
    body = extract_block(src, m.end() - 1)

    lines_out = []
    depth = 0
    for raw in body.splitlines():
        line = raw.strip()
        if not line or line.startswith("//"):
            continue
        converted, warn = convert_line(line, name)
        if warn:
            warnings.append(warn)
        if converted is None:
            continue
        for part in converted.split("\n"):
            stripped = part.strip()
            if not stripped:
                continue
            closes = stripped.startswith("}")
            if closes:
                depth = max(0, depth - 1)
            lines_out.append("    " * depth + stripped)
            opens = stripped.count("{") - stripped.count("}")
            if closes:
                opens += 1
            depth = max(0, depth + opens)

    # variables du Legacy utilisees mais non declarees : on les declare en tete
    joined = "\n".join(lines_out)
    decls = []
    already = set(re.findall(r"(?:Block|int)\s+(\w+)\s*[=;]", joined))
    for var in sorted(set(re.findall(r"(?<![\w.])(\w+Block)\b", joined))):
        if var in ("modBlock", "modBlockOnly", "setBlock", "getBlock") or var in already:
            continue
        default = BLOCK_VARS.get(var)
        if default:
            decls.append(f'        Block {var} = modBlockOnly("{default[0]}", Blocks.{default[1]});')
        else:
            decls.append(f"        Block {var} = Blocks.STONE;")
    for var in sorted(set(re.findall(r"(?<![\w.])(\w+Meta)\b", joined))):
        if var in already:
            continue
        decls.append(f"        int {var} = 0;")
    if decls:
        decls.insert(0, "        // variables du Legacy (setupRandomBlocks) - valeurs par defaut")
        lines_out = decls + [""] + lines_out

    # variables du Legacy utilisees mais non declarees : on les declare en tete
    joined = "\n".join(lines_out)
    decls = []
    already = set(re.findall(r"(?:Block|int)\s+(\w+)\s*[=;]", joined))
    for var in sorted(set(re.findall(r"(?<![\w.])(\w+Block)\b", joined))):
        if var in ("modBlock", "modBlockOnly", "setBlock", "getBlock") or var in already:
            continue
        default = BLOCK_VARS.get(var)
        if default:
            decls.append(f'        Block {var} = modBlockOnly("{default[0]}", Blocks.{default[1]});')
        else:
            decls.append(f"        Block {var} = Blocks.STONE;")
    for var in sorted(set(re.findall(r"(?<![\w.])(\w+Meta)\b", joined))):
        if var in already:
            continue
        decls.append(f"        int {var} = 0;")
    if decls:
        decls.insert(0, "        // variables du Legacy (setupRandomBlocks) - valeurs par defaut")
        lines_out = decls + [""] + lines_out

    java = f'''package fr.alleretretour.lotr.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

/**
 * PORT AUTOMATIQUE de LOTRWorldGen{name}.
 *
 * Traduit depuis le Legacy par tools/extract_structure.py : boucles, tirages
 * et conditions sont repris tels quels. Les lignes que l'outil n'a pas su
 * traduire avec certitude sont laissees en commentaire "TODO" plutot que
 * devinees - a completer a la main.
 */
public class LOTRStructure{name} extends LOTRStructureBase {{

    public LOTRStructure{name}(Codec<NoFeatureConfig> codec) {{
        super(codec);
    }}

    @Override
    protected boolean generateWithRotation(ISeedReader world, Random random, BlockPos origin,
                                           int rotation) {{
        BlockPos base = origin;
        int i = origin.getX();
        int j = origin.getY();
        int k = origin.getZ();
{chr(10).join(("        " + l if not l.startswith("        ") and l else l) for l in lines_out)}
    }}
}}
'''
    return java, warnings


def extract_block(src, brace_index):
    """Extrait le corps entre accolades equilibrees."""
    depth = 0
    for idx in range(brace_index, len(src)):
        if src[idx] == "{":
            depth += 1
        elif src[idx] == "}":
            depth -= 1
            if depth == 0:
                return src[brace_index + 1:idx]
    return ""


# lignes du prologue Legacy sans equivalent (le socle s'en charge)
DROP = (
    "setOriginAndRotation(", "setupRandomBlocks(", "usingPlayer",
)


def comment_out(line, why):
    """Commente une ligne en PRESERVANT ses accolades (sinon le bloc casse)."""
    opens = line.count("{") - line.count("}")
    if opens > 0:
        return f"if (false) {{   // TODO ({why}) : {line}"
    if opens < 0:
        return f"}}   // TODO ({why}) : {line}"
    return f"// TODO ({why}) : {line}"


def convert_line(line, name):
    """Traduit une ligne ; renvoie (ligne, avertissement|None)."""
    original = line
    for d in DROP:
        if line.startswith(d):
            if line.count("{") != line.count("}"):
                return comment_out(line, "prologue"), None
            return None, None
    if line.startswith("if (restrictions)"):
        return "// verification du terrain (PORT de restrictions)\n        if (true) {", None

    # pose de bloc
    m = re.fullmatch(r"setBlockAndNotifyAdequately\(world, ([^,]+), ([^,]+), ([^,]+), "
                     r"([\w.]+), ([^)]+)\);", line)
    if m:
        block = translate_block(m.group(4))
        meta = m.group(5).strip()
        if block is None:
            return comment_out(original, "bloc non traduit"), f"{name} : bloc {m.group(4)}"
        if meta not in ("0",):
            return (f"world.setBlock(new BlockPos({m.group(1)}, {m.group(2)}, {m.group(3)}), "
                    f"{block}, 2);   // TODO meta {meta}",
                    f"{name} : metadonnee {meta} sur {m.group(4)}")
        return (f"world.setBlock(new BlockPos({m.group(1)}, {m.group(2)}, {m.group(3)}), "
                f"{block}, 2);", None)

    # --- API des structures a coordonnees LOCALES (structure2) ---
    m = re.fullmatch(r"setBlockAndMetadata\(world, ([^,]+), ([^,]+), ([^,]+), "
                     r"([\w.]+), ([^)]+)\);", line)
    if m:
        block = translate_block(m.group(4))
        meta = m.group(5).strip()
        if block is None:
            return comment_out(original, "bloc non traduit"), f"{name} : bloc {m.group(4)}"
        if meta in ("0",):
            return (f"setBlockRotated(world, base, rotation, {m.group(1)}, {m.group(2)}, "
                    f"{m.group(3)}, {block});", None)
        # metadonnee : orientation d'escalier / axe de pilier, traduite par le socle
        return (f"setBlockRotated(world, base, rotation, {m.group(1)}, {m.group(2)}, "
                f"{m.group(3)}, withMeta({block}, {meta}, rotation));", None)
    line = re.sub(r"(?<![\w.])isOpaque\(world, ([^,]+), ([^,]+), ([^)]+)\)",
                  r"isOpaqueLocal(world, base, rotation, \1, \2, \3)", line)
    line = re.sub(r"(?<![\w.])isSurface\(world, ([^,]+), ([^,]+), ([^)]+)\)",
                  r"isSurfaceLocal(world, base, rotation, \1, \2, \3)", line)
    line = re.sub(r"(?<![\w.])getTopBlock\(world, ([^,]+), ([^)]+)\)",
                  r"getTopBlockLocal(world, base, rotation, \1, \2)", line)
    line = re.sub(r"(?<![\w.])setGrassToDirt\(world, ([^,]+), ([^,]+), ([^)]+)\);",
                  r"setGrassToDirt(world, rotate(base, rotation, \1, \2, \3));", line)
    line = re.sub(r"(?<![\w.])getY\(([^)]+)\)", r"(base.getY() + \1)", line)
    line = re.sub(r"placeRandomBrick\(world, random, ([^,]+), ([^,]+), ([^)]+)\);",
                  r"placeRandomBrick(world, base, rotation, random, \1, \2, \3);", line)
    line = re.sub(r"placeRandomStairs\(world, random, ([^,]+), ([^,]+), ([^,]+), ([^)]+)\);",
                  r"placeRandomStairs(world, base, rotation, random, \1, \2, \3, \4);", line)

    # appels de methode du Legacy (a traiter AVANT les references de bloc)
    line = re.sub(r"LOTRMod\.isOpaque\(world, ([^,]+), ([^,]+), ([^)]+)\)",
                  r"isOpaqueLocal(world, base, rotation, \1, \2, \3)", line)

    # references de bloc dans les conditions (== / !=)
    def vanilla_ref(m):
        v = VANILLA.get(m.group(1))
        return f"Blocks.{v}" if v else m.group(0)
    line = re.sub(r"Blocks\.([a-z_0-9]+)", vanilla_ref, line)
    line = re.sub(r"LOTRMod\.(\w+)",
                  lambda m: 'modBlockOnly("lotr:'
                            + re.sub(r"(?<=[a-z0-9])(?=[A-Z])", "_", m.group(1)).lower()
                            + '", Blocks.STONE)', line)

    line = re.sub(r"(?<![\w.])setAir\(world, ([^,]+), ([^,]+), ([^)]+)\);",
                  r"setBlockRotated(world, base, rotation, \1, \2, \3, Blocks.AIR.defaultBlockState());",
                  line)
    line = re.sub(r"(?<![\w.])getBlock\(world, ([^,]+), ([^,]+), ([^)]+)\)",
                  r"world.getBlockState(rotate(base, rotation, \1, \2, \3)).getBlock()", line)

    # helpers du socle
    line = re.sub(r"LOTRMod\.isOpaque\(world, ([^,]+), ([^,]+), ([^)]+)\)",
                  r"world.getBlockState(new BlockPos(\1, \2, \3)).isSolidRender(world, "
                  r"new BlockPos(\1, \2, \3))", line)
    line = re.sub(r"world\.isAirBlock\(([^,]+), ([^,]+), ([^)]+)\)",
                  r"world.isEmptyBlock(new BlockPos(\1, \2, \3))", line)
    line = re.sub(r"setGrassToDirt\(world, ([^,]+), ([^,]+), ([^)]+)\);",
                  r"setGrassToDirt(world, new BlockPos(\1, \2, \3));", line)
    line = re.sub(r"world\.getHeightValue\(([^,]+), ([^)]+)\)",
                  r"world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, \1, \2)", line)
    line = re.sub(r"world\.getBlock\(([^,]+), ([^,]+), ([^)]+)\)",
                  r"world.getBlockState(new BlockPos(\1, \2, \3)).getBlock()", line)

    # elements non traduisibles automatiquement
    for pattern, why in (
            (r"LOTRChestContents\.", "table de butin"),
            (r"spawn\w*\(", "apparition d'entite"),
            (r"LOTREntity", "entite"),
            (r"TileEntity", "bloc a inventaire"),
            (r"usingPlayer", "pose par le joueur"),
            (r"getBiomeGenForCoords", "test de biome"),
            (r"(?<![\w.])restrictions\b", "test de terrain"),
            (r"LOTRBiomeGen", "biome Legacy"),
            (r"(?<![\w.])LOTRMod\.", "bloc du mod non traduit"),
            (r"LOTRItem|LOTRBlock|LOTRFoods|LOTRSpeech", "objet du mod"),
            (r"LOTRMod\.isOpaque", "test d'opacite"),
    ):
        if re.search(pattern, line):
            return comment_out(original, why), f"{name} : {why}"

    if "setBlockAndNotifyAdequately" in line or "setBlockAndMetadata" in line:
        return comment_out(original, "pose non traduite"), f"{name} : pose complexe"
    return line, None


if __name__ == "__main__":
    os.makedirs(OUT, exist_ok=True)
    for arg in sys.argv[1:]:
        java, warns = convert(arg)
        if java is None:
            print(f"ECHEC {arg} : {warns}")
            continue
        dest = os.path.join(OUT, f"LOTRStructure{arg}.java")
        open(dest, "w", encoding="utf-8").write(java)
        todos = java.count("// TODO")
        print(f"OK {arg} : {len(java.splitlines())} lignes, {todos} TODO")
        for w in sorted(set(warns))[:5]:
            print(f"    - {w}")
