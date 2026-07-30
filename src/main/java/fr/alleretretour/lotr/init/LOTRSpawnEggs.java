package fr.alleretretour.lotr.init;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nullable;

/**
 * Oeufs d'apparition des PNJ - onglet creatif dedie (PORT de
 * LOTRCreativeTabs.tabSpawn), regroupes et ordonnes PAR FACTION.
 * Couleurs EXACTES du Legacy (LOTREntities.registerCreature).
 */
public class LOTRSpawnEggs {

    // ===== HOBBIT =====
    public static final RegistryObject<Item> HOBBIT_EGG =
            LOTRItems.ITEMS.register("hobbit_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.HOBBIT, 0xFF9F7F, 0x7A3A23));

    public static final RegistryObject<Item> HOBBIT_BARTENDER_EGG =
            LOTRItems.ITEMS.register("hobbit_bartender_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.HOBBIT_BARTENDER, 0xFF9F7F, 0x7A3A23));

    public static final RegistryObject<Item> HOBBIT_BOUNDER_EGG =
            LOTRItems.ITEMS.register("hobbit_bounder_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.HOBBIT_BOUNDER, 0xFF9F7F, 0x7A3A23));

    public static final RegistryObject<Item> HOBBIT_SHIRRIFF_EGG =
            LOTRItems.ITEMS.register("hobbit_shirriff_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.HOBBIT_SHIRRIFF, 0xFF9F7F, 0x7A3A23));

    // ===== BREE =====
    public static final RegistryObject<Item> BREE_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("bree_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.BREE_BANNER_BEARER, 0x8E7358, 0x382D25));

    public static final RegistryObject<Item> BREE_CAPTAIN_EGG =
            LOTRItems.ITEMS.register("bree_captain_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.BREE_CAPTAIN, 0x8E7358, 0x382D25));

    public static final RegistryObject<Item> BREE_GUARD_EGG =
            LOTRItems.ITEMS.register("bree_guard_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.BREE_GUARD, 0x8E7358, 0x382D25));

    public static final RegistryObject<Item> BREE_INNKEEPER_EGG =
            LOTRItems.ITEMS.register("bree_innkeeper_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.BREE_INNKEEPER, 0xD98366, 0x644D37));

    public static final RegistryObject<Item> BREE_MAN_EGG =
            LOTRItems.ITEMS.register("bree_man_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.BREE_MAN, 0xD98366, 0x644D37));

    // ===== RANGER_NORTH =====
    public static final RegistryObject<Item> RANGER_NORTH_EGG =
            LOTRItems.ITEMS.register("ranger_north_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.RANGER_NORTH, 0x444438, 0x23231C));

    public static final RegistryObject<Item> RANGER_NORTH_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("ranger_north_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.RANGER_NORTH_BANNER_BEARER, 0x444438, 0x23231C));

    public static final RegistryObject<Item> RANGER_NORTH_CAPTAIN_EGG =
            LOTRItems.ITEMS.register("ranger_north_captain_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.RANGER_NORTH_CAPTAIN, 0x444438, 0x23231C));

    // ===== BLUE_MOUNTAINS =====
    public static final RegistryObject<Item> BLUE_DWARF_AXE_THROWER_EGG =
            LOTRItems.ITEMS.register("blue_dwarf_axe_thrower_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.BLUE_DWARF_AXE_THROWER, 0x303E49, 0x5F7B8F));

    public static final RegistryObject<Item> BLUE_DWARF_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("blue_dwarf_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.BLUE_DWARF_BANNER_BEARER, 0x303E49, 0x5F7B8F));

    public static final RegistryObject<Item> BLUE_DWARF_COMMANDER_EGG =
            LOTRItems.ITEMS.register("blue_dwarf_commander_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.BLUE_DWARF_COMMANDER, 0x303E49, 0x5F7B8F));

    public static final RegistryObject<Item> BLUE_DWARF_WARRIOR_EGG =
            LOTRItems.ITEMS.register("blue_dwarf_warrior_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.BLUE_DWARF_WARRIOR, 0x303E49, 0x5F7B8F));

    public static final RegistryObject<Item> BLUE_MOUNTAINS_SMITH_EGG =
            LOTRItems.ITEMS.register("blue_mountains_smith_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.BLUE_MOUNTAINS_SMITH, 0xF9876D, 0xEA5620));

    // ===== HIGH_ELF =====
    public static final RegistryObject<Item> HIGH_ELF_EGG =
            LOTRItems.ITEMS.register("high_elf_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.HIGH_ELF, 0xFFC187, 0xEFE3AB));

    public static final RegistryObject<Item> HIGH_ELF_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("high_elf_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.HIGH_ELF_BANNER_BEARER, 0xE3E3E8, 0x6B6D9A));

    public static final RegistryObject<Item> HIGH_ELF_LORD_EGG =
            LOTRItems.ITEMS.register("high_elf_lord_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.HIGH_ELF_LORD, 0xE3E3E8, 0x6B6D9A));

    public static final RegistryObject<Item> HIGH_ELF_WARRIOR_EGG =
            LOTRItems.ITEMS.register("high_elf_warrior_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.HIGH_ELF_WARRIOR, 0xE3E3E8, 0x6B6D9A));

    public static final RegistryObject<Item> RIVENDELL_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("rivendell_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.RIVENDELL_BANNER_BEARER, 0xE0E4E6, 0xA39FB0));

    public static final RegistryObject<Item> RIVENDELL_ELF_EGG =
            LOTRItems.ITEMS.register("rivendell_elf_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.RIVENDELL_ELF, 0xFFC187, 0xEFE3AB));

    public static final RegistryObject<Item> RIVENDELL_LORD_EGG =
            LOTRItems.ITEMS.register("rivendell_lord_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.RIVENDELL_LORD, 0xE0E4E6, 0xA39FB0));

    public static final RegistryObject<Item> RIVENDELL_WARRIOR_EGG =
            LOTRItems.ITEMS.register("rivendell_warrior_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.RIVENDELL_WARRIOR, 0xE0E4E6, 0xA39FB0));

    // ===== GUNDABAD =====
    public static final RegistryObject<Item> GUNDABAD_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("gundabad_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GUNDABAD_BANNER_BEARER, 0x33271A, 0x827053));

    public static final RegistryObject<Item> GUNDABAD_ORC_EGG =
            LOTRItems.ITEMS.register("gundabad_orc_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GUNDABAD_ORC, 0x33271A, 0x827053));

    public static final RegistryObject<Item> GUNDABAD_ORC_ARCHER_EGG =
            LOTRItems.ITEMS.register("gundabad_orc_archer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GUNDABAD_ORC_ARCHER, 0x33271A, 0x827053));

    public static final RegistryObject<Item> GUNDABAD_ORC_MERCENARY_CAPTAIN_EGG =
            LOTRItems.ITEMS.register("gundabad_orc_mercenary_captain_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GUNDABAD_ORC_MERCENARY_CAPTAIN, 0x271D16, 0x616456));

    public static final RegistryObject<Item> GUNDABAD_URUK_EGG =
            LOTRItems.ITEMS.register("gundabad_uruk_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GUNDABAD_URUK, 0x271D16, 0x616456));

    public static final RegistryObject<Item> GUNDABAD_URUK_ARCHER_EGG =
            LOTRItems.ITEMS.register("gundabad_uruk_archer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GUNDABAD_URUK_ARCHER, 0x271D16, 0x616456));

    public static final RegistryObject<Item> GUNDABAD_WARG_EGG =
            LOTRItems.ITEMS.register("gundabad_warg_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GUNDABAD_WARG, 0x463329, 0x291D16));

    // ===== ANGMAR =====
    public static final RegistryObject<Item> ANGMAR_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("angmar_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.ANGMAR_BANNER_BEARER, 0x313529, 0x557749));

    public static final RegistryObject<Item> ANGMAR_HILLMAN_EGG =
            LOTRItems.ITEMS.register("angmar_hillman_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.ANGMAR_HILLMAN, 0xB47D6A, 0x2C1F18));

    public static final RegistryObject<Item> ANGMAR_HILLMAN_AXE_THROWER_EGG =
            LOTRItems.ITEMS.register("angmar_hillman_axe_thrower_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.ANGMAR_HILLMAN_AXE_THROWER, 0xB47D6A, 0x2C1F18));

    public static final RegistryObject<Item> ANGMAR_HILLMAN_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("angmar_hillman_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.ANGMAR_HILLMAN_BANNER_BEARER, 0xB47D6A, 0x2C1F18));

    public static final RegistryObject<Item> ANGMAR_HILLMAN_CHIEFTAIN_EGG =
            LOTRItems.ITEMS.register("angmar_hillman_chieftain_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.ANGMAR_HILLMAN_CHIEFTAIN, 0xB47D6A, 0x2C1F18));

    public static final RegistryObject<Item> ANGMAR_HILLMAN_WARRIOR_EGG =
            LOTRItems.ITEMS.register("angmar_hillman_warrior_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.ANGMAR_HILLMAN_WARRIOR, 0xB47D6A, 0x2C1F18));

    public static final RegistryObject<Item> ANGMAR_ORC_EGG =
            LOTRItems.ITEMS.register("angmar_orc_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.ANGMAR_ORC, 0x313529, 0x557749));

    public static final RegistryObject<Item> ANGMAR_ORC_ARCHER_EGG =
            LOTRItems.ITEMS.register("angmar_orc_archer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.ANGMAR_ORC_ARCHER, 0x313529, 0x557749));

    public static final RegistryObject<Item> ANGMAR_ORC_BOMBARDIER_EGG =
            LOTRItems.ITEMS.register("angmar_orc_bombardier_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.ANGMAR_ORC_BOMBARDIER, 0x313529, 0x557749));

    public static final RegistryObject<Item> ANGMAR_ORC_MERCENARY_CAPTAIN_EGG =
            LOTRItems.ITEMS.register("angmar_orc_mercenary_captain_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.ANGMAR_ORC_MERCENARY_CAPTAIN, 0x313529, 0x557749));

    public static final RegistryObject<Item> ANGMAR_ORC_TRADER_EGG =
            LOTRItems.ITEMS.register("angmar_orc_trader_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.ANGMAR_ORC_TRADER, 0x5B3D2C, 0xCCCCCC));

    public static final RegistryObject<Item> ANGMAR_WARG_EGG =
            LOTRItems.ITEMS.register("angmar_warg_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.ANGMAR_WARG, 0x463329, 0x291D16));

    public static final RegistryObject<Item> ANGMAR_WARG_BOMBARDIER_EGG =
            LOTRItems.ITEMS.register("angmar_warg_bombardier_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.ANGMAR_WARG_BOMBARDIER, 0x463329, 0x291D16));

    public static final RegistryObject<Item> MOUNTAIN_TROLL_EGG =
            LOTRItems.ITEMS.register("mountain_troll_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.MOUNTAIN_TROLL, 0x987359, 0x563D29));

    public static final RegistryObject<Item> TROLL_EGG =
            LOTRItems.ITEMS.register("troll_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.TROLL, 0xA58752, 0x49311E));

    // ===== WOOD_ELF =====
    public static final RegistryObject<Item> WOOD_ELF_EGG =
            LOTRItems.ITEMS.register("wood_elf_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.WOOD_ELF, 0x235121, 0xFFCE9E));

    public static final RegistryObject<Item> WOOD_ELF_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("wood_elf_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.WOOD_ELF_BANNER_BEARER, 0xBAA398, 0x595C2C));

    public static final RegistryObject<Item> WOOD_ELF_CAPTAIN_EGG =
            LOTRItems.ITEMS.register("wood_elf_captain_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.WOOD_ELF_CAPTAIN, 0xBAA398, 0x595C2C));

    public static final RegistryObject<Item> WOOD_ELF_SCOUT_EGG =
            LOTRItems.ITEMS.register("wood_elf_scout_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.WOOD_ELF_SCOUT, 0x05210C, 0x3B6033));

    public static final RegistryObject<Item> WOOD_ELF_WARRIOR_EGG =
            LOTRItems.ITEMS.register("wood_elf_warrior_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.WOOD_ELF_WARRIOR, 0xBAA398, 0x595C2C));

    // ===== DOL_GULDUR =====
    public static final RegistryObject<Item> DOL_GULDUR_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("dol_guldur_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DOL_GULDUR_BANNER_BEARER, 0x43454E, 0x1F2125));

    public static final RegistryObject<Item> DOL_GULDUR_ORC_EGG =
            LOTRItems.ITEMS.register("dol_guldur_orc_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DOL_GULDUR_ORC, 0x43454E, 0x1F2125));

    public static final RegistryObject<Item> DOL_GULDUR_ORC_ARCHER_EGG =
            LOTRItems.ITEMS.register("dol_guldur_orc_archer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DOL_GULDUR_ORC_ARCHER, 0x43454E, 0x1F2125));

    public static final RegistryObject<Item> DOL_GULDUR_ORC_CHIEFTAIN_EGG =
            LOTRItems.ITEMS.register("dol_guldur_orc_chieftain_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DOL_GULDUR_ORC_CHIEFTAIN, 0x43454E, 0x1F2125));

    public static final RegistryObject<Item> DOL_GULDUR_ORC_TRADER_EGG =
            LOTRItems.ITEMS.register("dol_guldur_orc_trader_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DOL_GULDUR_ORC_TRADER, 0x43454E, 0x1F2125));

    public static final RegistryObject<Item> MIRK_TROLL_EGG =
            LOTRItems.ITEMS.register("mirk_troll_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.MIRK_TROLL, 0x43454E, 0x1F2125));

    public static final RegistryObject<Item> MIRKWOOD_SPIDER_EGG =
            LOTRItems.ITEMS.register("mirkwood_spider_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.MIRKWOOD_SPIDER, 0x282521, 0x141110));

    // ===== DALE =====
    public static final RegistryObject<Item> DALE_ARCHER_EGG =
            LOTRItems.ITEMS.register("dale_archer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DALE_ARCHER, 0xB3B3B3, 0x07588B));

    public static final RegistryObject<Item> DALE_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("dale_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DALE_BANNER_BEARER, 0xB3B3B3, 0x07588B));

    public static final RegistryObject<Item> DALE_BLACKSMITH_EGG =
            LOTRItems.ITEMS.register("dale_blacksmith_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DALE_BLACKSMITH, 0xFFAC8B, 0x502411));

    public static final RegistryObject<Item> DALE_CAPTAIN_EGG =
            LOTRItems.ITEMS.register("dale_captain_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DALE_CAPTAIN, 0xB3B3B3, 0x07588B));

    public static final RegistryObject<Item> DALE_LEVYMAN_EGG =
            LOTRItems.ITEMS.register("dale_levyman_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DALE_LEVYMAN, 0x6B5548, 0x502411));

    public static final RegistryObject<Item> DALE_SOLDIER_EGG =
            LOTRItems.ITEMS.register("dale_soldier_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DALE_SOLDIER, 0xB3B3B3, 0x07588B));

    public static final RegistryObject<Item> ESGAROTH_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("esgaroth_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.ESGAROTH_BANNER_BEARER, 0xB3B3B3, 0x07588B));

    // ===== DURINS_FOLK =====
    public static final RegistryObject<Item> DWARF_EGG =
            LOTRItems.ITEMS.register("dwarf_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DWARF, 0xF9876D, 0xEA5620));

    public static final RegistryObject<Item> DWARF_AXE_THROWER_EGG =
            LOTRItems.ITEMS.register("dwarf_axe_thrower_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DWARF_AXE_THROWER, 0x22282A, 0x6C787A));

    public static final RegistryObject<Item> DWARF_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("dwarf_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DWARF_BANNER_BEARER, 0x22282A, 0x6C787A));

    public static final RegistryObject<Item> DWARF_COMMANDER_EGG =
            LOTRItems.ITEMS.register("dwarf_commander_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DWARF_COMMANDER, 0x22282A, 0x6C787A));

    public static final RegistryObject<Item> DWARF_SMITH_EGG =
            LOTRItems.ITEMS.register("dwarf_smith_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DWARF_SMITH, 0xF9876D, 0xEA5620));

    public static final RegistryObject<Item> DWARF_WARRIOR_EGG =
            LOTRItems.ITEMS.register("dwarf_warrior_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DWARF_WARRIOR, 0x22282A, 0x6C787A));

    // ===== LOTHLORIEN =====
    public static final RegistryObject<Item> GALADHRIM_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("galadhrim_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GALADHRIM_BANNER_BEARER, 0xC1BEBA, 0xEAB956));

    public static final RegistryObject<Item> GALADHRIM_ELF_EGG =
            LOTRItems.ITEMS.register("galadhrim_elf_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GALADHRIM_ELF, 0x8E7961, 0xF2EDAB));

    public static final RegistryObject<Item> GALADHRIM_LORD_EGG =
            LOTRItems.ITEMS.register("galadhrim_lord_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GALADHRIM_LORD, 0xC1BEBA, 0xEAB956));

    public static final RegistryObject<Item> GALADHRIM_WARDEN_EGG =
            LOTRItems.ITEMS.register("galadhrim_warden_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GALADHRIM_WARDEN, 0xA0A39D, 0x7A7C77));

    public static final RegistryObject<Item> GALADHRIM_WARRIOR_EGG =
            LOTRItems.ITEMS.register("galadhrim_warrior_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GALADHRIM_WARRIOR, 0xC1BEBA, 0xEAB956));

    // ===== DUNLAND =====
    public static final RegistryObject<Item> DUNLENDING_EGG =
            LOTRItems.ITEMS.register("dunlending_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DUNLENDING, 0xF29472, 0x38241A));

    public static final RegistryObject<Item> DUNLENDING_ARCHER_EGG =
            LOTRItems.ITEMS.register("dunlending_archer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DUNLENDING_ARCHER, 0x4F3C31, 0x8E7C77));

    public static final RegistryObject<Item> DUNLENDING_AXE_THROWER_EGG =
            LOTRItems.ITEMS.register("dunlending_axe_thrower_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DUNLENDING_AXE_THROWER, 0x4F3C31, 0x8E7C77));

    public static final RegistryObject<Item> DUNLENDING_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("dunlending_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DUNLENDING_BANNER_BEARER, 0x4F3C31, 0x8E7C77));

    public static final RegistryObject<Item> DUNLENDING_BERSERKER_EGG =
            LOTRItems.ITEMS.register("dunlending_berserker_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DUNLENDING_BERSERKER, 0x4F3C31, 0xF4E7C9));

    public static final RegistryObject<Item> DUNLENDING_WARLORD_EGG =
            LOTRItems.ITEMS.register("dunlending_warlord_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DUNLENDING_WARLORD, 0x4F3C31, 0x8E7C77));

    public static final RegistryObject<Item> DUNLENDING_WARRIOR_EGG =
            LOTRItems.ITEMS.register("dunlending_warrior_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DUNLENDING_WARRIOR, 0x4F3C31, 0x8E7C77));

    // ===== ISENGARD =====
    public static final RegistryObject<Item> ISENGARD_SNAGA_EGG =
            LOTRItems.ITEMS.register("isengard_snaga_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.ISENGARD_SNAGA, 0x42372C, 0x7F725D));

    public static final RegistryObject<Item> ISENGARD_SNAGA_ARCHER_EGG =
            LOTRItems.ITEMS.register("isengard_snaga_archer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.ISENGARD_SNAGA_ARCHER, 0x42372C, 0x7F725D));

    public static final RegistryObject<Item> URUK_HAI_EGG =
            LOTRItems.ITEMS.register("uruk_hai_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.URUK_HAI, 0x24261A, 0x58593F));

    public static final RegistryObject<Item> URUK_HAI_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("uruk_hai_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.URUK_HAI_BANNER_BEARER, 0x24261A, 0x58593F));

    public static final RegistryObject<Item> URUK_HAI_BERSERKER_EGG =
            LOTRItems.ITEMS.register("uruk_hai_berserker_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.URUK_HAI_BERSERKER, 0x24261A, 0xDBDBD6));

    public static final RegistryObject<Item> URUK_HAI_CROSSBOWER_EGG =
            LOTRItems.ITEMS.register("uruk_hai_crossbower_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.URUK_HAI_CROSSBOWER, 0x24261A, 0x58593F));

    public static final RegistryObject<Item> URUK_HAI_MERCENARY_CAPTAIN_EGG =
            LOTRItems.ITEMS.register("uruk_hai_mercenary_captain_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.URUK_HAI_MERCENARY_CAPTAIN, 0x24261A, 0x58593F));

    public static final RegistryObject<Item> URUK_HAI_SAPPER_EGG =
            LOTRItems.ITEMS.register("uruk_hai_sapper_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.URUK_HAI_SAPPER, 0x24261A, 0x58593F));

    public static final RegistryObject<Item> URUK_WARG_EGG =
            LOTRItems.ITEMS.register("uruk_warg_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.URUK_WARG, 0x463329, 0x291D16));

    public static final RegistryObject<Item> URUK_WARG_BOMBARDIER_EGG =
            LOTRItems.ITEMS.register("uruk_warg_bombardier_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.URUK_WARG_BOMBARDIER, 0x463329, 0x291D16));

    // ===== ROHAN =====
    public static final RegistryObject<Item> ROHAN_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("rohan_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.ROHAN_BANNER_BEARER, 0x544B48, 0xCEB390));

    public static final RegistryObject<Item> ROHAN_SHIELDMAIDEN_EGG =
            LOTRItems.ITEMS.register("rohan_shieldmaiden_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.ROHAN_SHIELDMAIDEN, 0x544B48, 0xCEB390));

    public static final RegistryObject<Item> ROHIRRIM_ARCHER_EGG =
            LOTRItems.ITEMS.register("rohirrim_archer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.ROHIRRIM_ARCHER, 0x544B48, 0xCEB390));

    public static final RegistryObject<Item> ROHIRRIM_MARSHAL_EGG =
            LOTRItems.ITEMS.register("rohirrim_marshal_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.ROHIRRIM_MARSHAL, 0x5E504C, 0xE2B678));

    public static final RegistryObject<Item> ROHIRRIM_WARRIOR_EGG =
            LOTRItems.ITEMS.register("rohirrim_warrior_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.ROHIRRIM_WARRIOR, 0x544B48, 0xCEB390));

    // ===== GONDOR =====
    public static final RegistryObject<Item> GONDOR_ARCHER_EGG =
            LOTRItems.ITEMS.register("gondor_archer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GONDOR_ARCHER, 0x514C4C, 0xE5DADA));

    public static final RegistryObject<Item> GONDOR_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("gondor_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GONDOR_BANNER_BEARER, 0x514C4C, 0xE5DADA));

    public static final RegistryObject<Item> GONDOR_BLACKSMITH_EGG =
            LOTRItems.ITEMS.register("gondor_blacksmith_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GONDOR_BLACKSMITH, 0xCEB8A5, 0x56403A));

    public static final RegistryObject<Item> GONDOR_LEVYMAN_EGG =
            LOTRItems.ITEMS.register("gondor_levyman_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GONDOR_LEVYMAN, 0xA4A3A2, 0x684634));

    public static final RegistryObject<Item> GONDOR_SOLDIER_EGG =
            LOTRItems.ITEMS.register("gondor_soldier_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GONDOR_SOLDIER, 0x514C4C, 0xE5DADA));

    public static final RegistryObject<Item> GONDOR_TOWER_GUARD_EGG =
            LOTRItems.ITEMS.register("gondor_tower_guard_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GONDOR_TOWER_GUARD, 0x514C4C, 0xE5DADA));

    public static final RegistryObject<Item> GONDORIAN_CAPTAIN_EGG =
            LOTRItems.ITEMS.register("gondorian_captain_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GONDORIAN_CAPTAIN, 0x514C4C, 0xE5DADA));

    public static final RegistryObject<Item> RANGER_ITHILIEN_EGG =
            LOTRItems.ITEMS.register("ranger_ithilien_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.RANGER_ITHILIEN, 0x3D4425, 0x15190D));

    public static final RegistryObject<Item> RANGER_ITHILIEN_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("ranger_ithilien_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.RANGER_ITHILIEN_BANNER_BEARER, 0x3D4425, 0x15190D));

    public static final RegistryObject<Item> RANGER_ITHILIEN_CAPTAIN_EGG =
            LOTRItems.ITEMS.register("ranger_ithilien_captain_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.RANGER_ITHILIEN_CAPTAIN, 0x3D4425, 0x15190D));

    // ===== MORDOR =====
    public static final RegistryObject<Item> MINAS_MORGUL_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("minas_morgul_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.MINAS_MORGUL_BANNER_BEARER, 0x332B22, 0x6B7567));

    public static final RegistryObject<Item> MORDOR_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("mordor_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.MORDOR_BANNER_BEARER, 0x332B22, 0x6B7567));

    public static final RegistryObject<Item> MORDOR_ORC_EGG =
            LOTRItems.ITEMS.register("mordor_orc_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.MORDOR_ORC, 0x332B22, 0x6B7567));

    public static final RegistryObject<Item> MORDOR_ORC_ARCHER_EGG =
            LOTRItems.ITEMS.register("mordor_orc_archer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.MORDOR_ORC_ARCHER, 0x332B22, 0x6B7567));

    public static final RegistryObject<Item> MORDOR_ORC_BOMBARDIER_EGG =
            LOTRItems.ITEMS.register("mordor_orc_bombardier_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.MORDOR_ORC_BOMBARDIER, 0x332B22, 0x6B7567));

    public static final RegistryObject<Item> MORDOR_ORC_MERCENARY_CAPTAIN_EGG =
            LOTRItems.ITEMS.register("mordor_orc_mercenary_captain_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.MORDOR_ORC_MERCENARY_CAPTAIN, 0x332B22, 0x6B7567));

    public static final RegistryObject<Item> MORDOR_WARG_EGG =
            LOTRItems.ITEMS.register("mordor_warg_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.MORDOR_WARG, 0x463329, 0x291D16));

    public static final RegistryObject<Item> MORDOR_WARG_BOMBARDIER_EGG =
            LOTRItems.ITEMS.register("mordor_warg_bombardier_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.MORDOR_WARG_BOMBARDIER, 0x463329, 0x291D16));

    public static final RegistryObject<Item> OLOG_HAI_EGG =
            LOTRItems.ITEMS.register("olog_hai_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.OLOG_HAI, 0x3F483C, 0x222322));

    // ===== DORWINION =====
    public static final RegistryObject<Item> DORWINION_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("dorwinion_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DORWINION_BANNER_BEARER, 0x896B4D, 0x5E4577));

    public static final RegistryObject<Item> DORWINION_CAPTAIN_EGG =
            LOTRItems.ITEMS.register("dorwinion_captain_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DORWINION_CAPTAIN, 0x896B4D, 0x5E4577));

    public static final RegistryObject<Item> DORWINION_CROSSBOWER_EGG =
            LOTRItems.ITEMS.register("dorwinion_crossbower_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DORWINION_CROSSBOWER, 0x896B4D, 0x5E4577));

    public static final RegistryObject<Item> DORWINION_ELF_ARCHER_EGG =
            LOTRItems.ITEMS.register("dorwinion_elf_archer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DORWINION_ELF_ARCHER, 0xCCC9C7, 0x804A90));

    public static final RegistryObject<Item> DORWINION_ELF_WARRIOR_EGG =
            LOTRItems.ITEMS.register("dorwinion_elf_warrior_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DORWINION_ELF_WARRIOR, 0xCCC9C7, 0x804A90));

    public static final RegistryObject<Item> DORWINION_GUARD_EGG =
            LOTRItems.ITEMS.register("dorwinion_guard_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.DORWINION_GUARD, 0x896B4D, 0x5E4577));

    // ===== RHUDEL =====
    public static final RegistryObject<Item> EASTERLING_ARCHER_EGG =
            LOTRItems.ITEMS.register("easterling_archer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.EASTERLING_ARCHER, 0x723B3B, 0xE8B978));

    public static final RegistryObject<Item> EASTERLING_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("easterling_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.EASTERLING_BANNER_BEARER, 0x723B3B, 0xE8B978));

    public static final RegistryObject<Item> EASTERLING_BLACKSMITH_EGG =
            LOTRItems.ITEMS.register("easterling_blacksmith_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.EASTERLING_BLACKSMITH, 0xF5915B, 0x5E3D32));

    public static final RegistryObject<Item> EASTERLING_FIRE_THROWER_EGG =
            LOTRItems.ITEMS.register("easterling_fire_thrower_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.EASTERLING_FIRE_THROWER, 0x723B3B, 0xE8B978));

    public static final RegistryObject<Item> EASTERLING_GOLD_WARRIOR_EGG =
            LOTRItems.ITEMS.register("easterling_gold_warrior_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.EASTERLING_GOLD_WARRIOR, 0xD9AD59, 0xB72D2D));

    public static final RegistryObject<Item> EASTERLING_LEVYMAN_EGG =
            LOTRItems.ITEMS.register("easterling_levyman_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.EASTERLING_LEVYMAN, 0xF5915B, 0x5E3D32));

    public static final RegistryObject<Item> EASTERLING_WARLORD_EGG =
            LOTRItems.ITEMS.register("easterling_warlord_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.EASTERLING_WARLORD, 0xD9AD59, 0xB72D2D));

    public static final RegistryObject<Item> EASTERLING_WARRIOR_EGG =
            LOTRItems.ITEMS.register("easterling_warrior_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.EASTERLING_WARRIOR, 0x723B3B, 0xE8B978));

    // ===== NEAR_HARAD =====
    public static final RegistryObject<Item> CORSAIR_EGG =
            LOTRItems.ITEMS.register("corsair_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.CORSAIR, 0x544235, 0xC38531));

    public static final RegistryObject<Item> CORSAIR_CAPTAIN_EGG =
            LOTRItems.ITEMS.register("corsair_captain_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.CORSAIR_CAPTAIN, 0x544235, 0xC38531));

    public static final RegistryObject<Item> HARNEDOR_ARCHER_EGG =
            LOTRItems.ITEMS.register("harnedor_archer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.HARNEDOR_ARCHER, 0x6B1111, 0xE2A146));

    public static final RegistryObject<Item> HARNEDOR_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("harnedor_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.HARNEDOR_BANNER_BEARER, 0x6B1111, 0xE2A146));

    public static final RegistryObject<Item> HARNEDOR_WARLORD_EGG =
            LOTRItems.ITEMS.register("harnedor_warlord_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.HARNEDOR_WARLORD, 0x6B1111, 0xE2A146));

    public static final RegistryObject<Item> HARNEDOR_WARRIOR_EGG =
            LOTRItems.ITEMS.register("harnedor_warrior_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.HARNEDOR_WARRIOR, 0x6B1111, 0xE2A146));

    public static final RegistryObject<Item> NEAR_HARAD_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("near_harad_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.NEAR_HARAD_BANNER_BEARER, 0x212121, 0xB51B1B));

    public static final RegistryObject<Item> NEAR_HARAD_MERCHANT_EGG =
            LOTRItems.ITEMS.register("near_harad_merchant_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.NEAR_HARAD_MERCHANT, 0xA47A5D, 0x2D2D2D));

    public static final RegistryObject<Item> NEAR_HARADRIM_ARCHER_EGG =
            LOTRItems.ITEMS.register("near_haradrim_archer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.NEAR_HARADRIM_ARCHER, 0x212121, 0xB51B1B));

    public static final RegistryObject<Item> NEAR_HARADRIM_WARLORD_EGG =
            LOTRItems.ITEMS.register("near_haradrim_warlord_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.NEAR_HARADRIM_WARLORD, 0x212121, 0xB51B1B));

    public static final RegistryObject<Item> NEAR_HARADRIM_WARRIOR_EGG =
            LOTRItems.ITEMS.register("near_haradrim_warrior_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.NEAR_HARADRIM_WARRIOR, 0x212121, 0xB51B1B));

    public static final RegistryObject<Item> SOUTHRON_CHAMPION_EGG =
            LOTRItems.ITEMS.register("southron_champion_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.SOUTHRON_CHAMPION, 0x212121, 0xB51B1B));

    public static final RegistryObject<Item> UMBAR_ARCHER_EGG =
            LOTRItems.ITEMS.register("umbar_archer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.UMBAR_ARCHER, 0x2D2D28, 0xCE9D54));

    public static final RegistryObject<Item> UMBAR_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("umbar_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.UMBAR_BANNER_BEARER, 0x2D2D28, 0xCE9D54));

    public static final RegistryObject<Item> UMBAR_CAPTAIN_EGG =
            LOTRItems.ITEMS.register("umbar_captain_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.UMBAR_CAPTAIN, 0x2D2D28, 0xCE9D54));

    public static final RegistryObject<Item> UMBAR_WARRIOR_EGG =
            LOTRItems.ITEMS.register("umbar_warrior_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.UMBAR_WARRIOR, 0x2D2D28, 0xCE9D54));

    // ===== MORWAITH =====
    public static final RegistryObject<Item> MOREDAIN_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("moredain_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.MOREDAIN_BANNER_BEARER, 0x894F29, 0x4D2B16));

    public static final RegistryObject<Item> MOREDAIN_CHIEFTAIN_EGG =
            LOTRItems.ITEMS.register("moredain_chieftain_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.MOREDAIN_CHIEFTAIN, 0xD2B16A, 0xAA6331));

    public static final RegistryObject<Item> MOREDAIN_HUNTSMAN_EGG =
            LOTRItems.ITEMS.register("moredain_huntsman_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.MOREDAIN_HUNTSMAN, 0x513A27, 0x211810));

    public static final RegistryObject<Item> MOREDAIN_TRADER_EGG =
            LOTRItems.ITEMS.register("moredain_trader_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.MOREDAIN_TRADER, 0x513A27, 0x211810));

    public static final RegistryObject<Item> MOREDAIN_WARRIOR_EGG =
            LOTRItems.ITEMS.register("moredain_warrior_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.MOREDAIN_WARRIOR, 0x894F29, 0x4D2B16));

    // ===== TAURETHRIM =====
    public static final RegistryObject<Item> TAUREDAIN_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("tauredain_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.TAUREDAIN_BANNER_BEARER, 0x563F2B, 0x8BDA4D));

    public static final RegistryObject<Item> TAUREDAIN_BLOWGUNNER_EGG =
            LOTRItems.ITEMS.register("tauredain_blowgunner_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.TAUREDAIN_BLOWGUNNER, 0x563F2B, 0x8BDA4D));

    public static final RegistryObject<Item> TAUREDAIN_CHIEFTAIN_EGG =
            LOTRItems.ITEMS.register("tauredain_chieftain_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.TAUREDAIN_CHIEFTAIN, 0x563F2B, 0x8BDA4D));

    public static final RegistryObject<Item> TAUREDAIN_WARRIOR_EGG =
            LOTRItems.ITEMS.register("tauredain_warrior_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.TAUREDAIN_WARRIOR, 0x563F2B, 0x8BDA4D));

    // ===== HALF_TROLL =====
    public static final RegistryObject<Item> HALF_TROLL_EGG =
            LOTRItems.ITEMS.register("half_troll_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.HALF_TROLL, 0x69563F, 0x37261C));

    public static final RegistryObject<Item> HALF_TROLL_BANNER_BEARER_EGG =
            LOTRItems.ITEMS.register("half_troll_banner_bearer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.HALF_TROLL_BANNER_BEARER, 0x8F7A7A, 0x5F4936));

    public static final RegistryObject<Item> HALF_TROLL_WARLORD_EGG =
            LOTRItems.ITEMS.register("half_troll_warlord_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.HALF_TROLL_WARLORD, 0x8F7A7A, 0x5F4936));

    public static final RegistryObject<Item> HALF_TROLL_WARRIOR_EGG =
            LOTRItems.ITEMS.register("half_troll_warrior_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.HALF_TROLL_WARRIOR, 0x8F7A7A, 0x5F4936));

    // ===== RUFFIAN =====
    public static final RegistryObject<Item> BREE_RUFFIAN_EGG =
            LOTRItems.ITEMS.register("bree_ruffian_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.BREE_RUFFIAN, 0xE08163, 0x4F362D));

    public static final RegistryObject<Item> RUFFIAN_BRUTE_EGG =
            LOTRItems.ITEMS.register("ruffian_brute_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.RUFFIAN_BRUTE, 0xE08163, 0x4F362D));

    public static final RegistryObject<Item> RUFFIAN_SPY_EGG =
            LOTRItems.ITEMS.register("ruffian_spy_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.RUFFIAN_SPY, 0xE08163, 0x4F362D));

    // ===== UNALIGNED =====
    public static final RegistryObject<Item> ELK_EGG =
            LOTRItems.ITEMS.register("elk_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.ELK, 0xEBE5D9, 0xB5A990));

    public static final RegistryObject<Item> RHINO_EGG =
            LOTRItems.ITEMS.register("rhino_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.RHINO, 0x5D5C51, 0xB9B79D));

    public static final RegistryObject<Item> WILD_BOAR_EGG =
            LOTRItems.ITEMS.register("wild_boar_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.WILD_BOAR, 0x65402A, 0x3E1802));

    public static final RegistryObject<Item> ZEBRA_EGG =
            LOTRItems.ITEMS.register("zebra_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.ZEBRA, 0xE4E4E4, 0x423934));

    private LOTRSpawnEggs() {
    }

    /** Declenche l'enregistrement des champs statiques. */
    public static void init() {
    }

    static class LOTRSpawnEgg extends SpawnEggItem {

        private final RegistryObject<? extends EntityType<?>> typeRO;

        @SuppressWarnings("ConstantConditions")
        LOTRSpawnEgg(RegistryObject<? extends EntityType<?>> type, int primary, int secondary) {
            super(null, primary, secondary,
                    new Item.Properties().tab(LOTRCreativeTabs.TAB_SPAWN));
            this.typeRO = type;
        }

        @Override
        public EntityType<?> getType(@Nullable CompoundNBT nbt) {
            if (nbt != null && nbt.contains("EntityTag", 10)) {
                EntityType<?> fromNbt = super.getType(nbt);
                if (fromNbt != null) {
                    return fromNbt;
                }
            }
            return typeRO.get();
        }
    }
}
