package fr.alleretretour.lotr.init;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.fml.RegistryObject;

/**
 * PORT des listes d'apparition des PNJ (LOTRSpawnList + npcSpawnList de chaque
 * biome). Poids, taille de groupe minimale et maximale repris du Legacy :
 * poids final = poids de la liste dans le biome x poids de l'entree / 10.
 *
 * Seules les listes principales (newFactionList(100)) sont actives : les listes
 * alternatives dependent du systeme de CONQUETE du Legacy, qui n'est pas encore
 * porte ; elles sont signalees en commentaire, biome par biome.
 */
public final class LOTRBiomeSpawns {

    private LOTRBiomeSpawns() {
    }

    public static void apply(String name, MobSpawnInfo.Builder b) {
        // conquete (TODO) : ANGMAR_HILLMEN, ANGMAR_ORCS, ANGMAR_WARGS, DOL_GULDUR_ORCS, GALADHRIM_WARDENS, GALADHRIM_WARRIORS
        if (name.equals("anduin_hills")) {
            spawn(b, LOTREntities.GUNDABAD_ORC, 20, 4, 6); spawn(b, LOTREntities.GUNDABAD_ORC_ARCHER, 10, 4, 6); spawn(b, LOTREntities.GUNDABAD_WARG, 3, 4, 4); spawn(b, LOTREntities.GUNDABAD_URUK, 4, 2, 4); spawn(b, LOTREntities.GUNDABAD_URUK_ARCHER, 2, 2, 4);
        }
        // conquete (TODO) : RANGERS_NORTH, RIVENDELL_WARRIORS
        if (name.equals("angmar")) {
            spawn(b, LOTREntities.ANGMAR_ORC, 60, 4, 6); spawn(b, LOTREntities.ANGMAR_ORC_ARCHER, 30, 4, 6); spawn(b, LOTREntities.ANGMAR_ORC_BOMBARDIER, 5, 1, 2); spawn(b, LOTREntities.ANGMAR_WARG, 30, 4, 4); spawn(b, LOTREntities.TROLL, 30, 1, 3); spawn(b, LOTREntities.MOUNTAIN_TROLL, 20, 1, 3); spawn(b, LOTREntities.ANGMAR_HILLMAN, 20, 4, 6); spawn(b, LOTREntities.ANGMAR_HILLMAN_WARRIOR, 10, 4, 6); spawn(b, LOTREntities.ANGMAR_HILLMAN_AXE_THROWER, 10, 4, 6);
        }
        if (name.equals("blue_mountains")) {
            spawn(b, LOTREntities.BLUE_DWARF_WARRIOR, 100, 4, 4); spawn(b, LOTREntities.BLUE_DWARF_WARRIOR, 20, 4, 4); spawn(b, LOTREntities.BLUE_DWARF_AXE_THROWER, 10, 4, 4);
        }
        // conquete (TODO) : ANGMAR_ORCS, ANGMAR_WARGS, BLUE_DWARVES
        if (name.equals("blue_mountains_foothills")) {
            spawn(b, LOTREntities.GUNDABAD_ORC, 12, 4, 6); spawn(b, LOTREntities.GUNDABAD_ORC_ARCHER, 6, 4, 6); spawn(b, LOTREntities.GUNDABAD_WARG, 1, 4, 4); spawn(b, LOTREntities.GUNDABAD_URUK, 2, 2, 4); spawn(b, LOTREntities.GUNDABAD_URUK_ARCHER, 1, 2, 4);
        }
        // conquete (TODO) : ANGMAR_HILLMEN, ANGMAR_ORCS, ANGMAR_WARGS, GUNDABAD_ORCS, GUNDABAD_URUKS, GUNDABAD_WARGS
        if (name.equals("breeland")) {
            spawn(b, LOTREntities.BREE_MAN, 10, 4, 6); spawn(b, LOTREntities.HOBBIT, 3, 4, 6); spawn(b, LOTREntities.BREE_GUARD, 4, 2, 4);
        }
        // conquete (TODO) : BLACK_URUKS, DOL_GULDUR_ORCS, GALADHRIM, GALADHRIM_WARDENS, GALADHRIM_WARRIORS, MIRKWOOD_SPIDERS
        if (name.equals("celebrant")) {
            spawn(b, LOTREntities.GUNDABAD_ORC, 20, 4, 6); spawn(b, LOTREntities.GUNDABAD_ORC_ARCHER, 10, 4, 6); spawn(b, LOTREntities.GUNDABAD_WARG, 2, 4, 4); spawn(b, LOTREntities.GUNDABAD_URUK, 4, 2, 4); spawn(b, LOTREntities.GUNDABAD_URUK_ARCHER, 2, 2, 4);
        }
        if (name.equals("chetwood")) {
            spawn(b, LOTREntities.RUFFIAN_SPY, 10, 1, 4); spawn(b, LOTREntities.RUFFIAN_BRUTE, 5, 1, 4);
        }
        // conquete (TODO) : GONDOR_SOLDIERS, RANGERS_ITHILIEN
        if (name.equals("dagorlad")) {
            spawn(b, LOTREntities.MORDOR_ORC, 20, 4, 6); spawn(b, LOTREntities.MORDOR_ORC_ARCHER, 10, 4, 6); spawn(b, LOTREntities.MORDOR_ORC_BOMBARDIER, 1, 1, 2); spawn(b, LOTREntities.MORDOR_WARG, 2, 4, 4);
        }
        // conquete (TODO) : GALADHRIM_WARDENS, GALADHRIM_WARRIORS, WOOD_ELF_WARRIORS, WOOD_ELVES
        if (name.equals("dol_guldur")) {
            spawn(b, LOTREntities.MIRKWOOD_SPIDER, 20, 4, 6); spawn(b, LOTREntities.DOL_GULDUR_ORC, 60, 4, 6); spawn(b, LOTREntities.DOL_GULDUR_ORC_ARCHER, 30, 4, 6); spawn(b, LOTREntities.MIRK_TROLL, 5, 1, 3);
        }
        // conquete (TODO) : BLACK_URUKS, DOL_GULDUR_ORCS, EASTERLINGS, EASTERLING_GOLD_WARRIORS, EASTERLING_WARRIORS, GONDOR_MEN
        if (name.equals("dorwinion")) {
            spawn(b, LOTREntities.DORWINION_GUARD, 20, 1, 3); spawn(b, LOTREntities.DORWINION_CROSSBOWER, 10, 1, 3); spawn(b, LOTREntities.DORWINION_ELF_WARRIOR, 4, 1, 3); spawn(b, LOTREntities.DORWINION_ELF_ARCHER, 2, 1, 3);
        }
        // conquete (TODO) : GUNDABAD_ORCS, GUNDABAD_WARGS, ISENGARD_SNAGA, RANGERS_NORTH, ROHIRRIM_WARRIORS, RUFFIANS
        if (name.equals("dunland")) {
            spawn(b, LOTREntities.DUNLENDING, 9, 4, 6); spawn(b, LOTREntities.DUNLENDING_WARRIOR, 2, 4, 6); spawn(b, LOTREntities.DUNLENDING_ARCHER, 1, 4, 6); spawn(b, LOTREntities.DUNLENDING_AXE_THROWER, 1, 4, 6); spawn(b, LOTREntities.DUNLENDING_BERSERKER, 1, 1, 2);
        }
        // conquete (TODO) : GONDOR_SOLDIERS, RANGERS_ITHILIEN
        if (name.equals("emyn_muil")) {
            spawn(b, LOTREntities.MORDOR_ORC, 20, 4, 6); spawn(b, LOTREntities.MORDOR_ORC_ARCHER, 10, 4, 6); spawn(b, LOTREntities.MORDOR_WARG, 1, 4, 4);
        }
        // conquete (TODO) : GONDOR_SOLDIERS, GUNDABAD_ORCS, GUNDABAD_WARGS, ISENGARD_SNAGA, RANGERS_NORTH, ROHIRRIM_WARRIORS
        if (name.equals("enedwaith")) {
            spawn(b, LOTREntities.DUNLENDING, 3, 4, 6); spawn(b, LOTREntities.DUNLENDING_WARRIOR, 2, 4, 6); spawn(b, LOTREntities.DUNLENDING_ARCHER, 1, 4, 6); spawn(b, LOTREntities.DUNLENDING_AXE_THROWER, 1, 4, 6); spawn(b, LOTREntities.DUNLENDING_BERSERKER, 1, 1, 2); spawn(b, LOTREntities.DUNLENDING, 30, 4, 6); spawn(b, LOTREntities.DUNLENDING_WARRIOR, 20, 4, 6); spawn(b, LOTREntities.DUNLENDING_ARCHER, 10, 4, 6); spawn(b, LOTREntities.DUNLENDING_AXE_THROWER, 10, 4, 6); spawn(b, LOTREntities.DUNLENDING_BERSERKER, 5, 1, 2);
        }
        // conquete (TODO) : BLACK_URUKS, DOL_GULDUR_ORCS, EASTERLING_GOLD_WARRIORS, EASTERLING_WARRIORS, GUNDABAD_ORCS, GUNDABAD_URUKS
        if (name.equals("erebor")) {
            spawn(b, LOTREntities.DWARF, 1000, 4, 4); spawn(b, LOTREntities.DWARF_WARRIOR, 200, 4, 4); spawn(b, LOTREntities.DWARF_AXE_THROWER, 100, 4, 4);
        }
        // conquete (TODO) : ANGMAR_HILLMEN, ANGMAR_ORCS, ANGMAR_WARGS, RANGERS_NORTH, RIVENDELL_WARRIORS
        if (name.equals("eregion")) {
            spawn(b, LOTREntities.GUNDABAD_ORC, 20, 4, 6); spawn(b, LOTREntities.GUNDABAD_ORC_ARCHER, 10, 4, 6); spawn(b, LOTREntities.GUNDABAD_WARG, 3, 4, 4);
        }
        // conquete (TODO) : ANGMAR_HILLMEN, ANGMAR_ORCS, ANGMAR_WARGS, LINDON_WARRIORS, RANGERS_NORTH, RIVENDELL_WARRIORS
        if (name.equals("eriador")) {
            spawn(b, LOTREntities.GUNDABAD_ORC, 20, 4, 6); spawn(b, LOTREntities.GUNDABAD_ORC_ARCHER, 10, 4, 6); spawn(b, LOTREntities.GUNDABAD_WARG, 2, 4, 4); spawn(b, LOTREntities.GUNDABAD_URUK, 2, 2, 4); spawn(b, LOTREntities.GUNDABAD_URUK_ARCHER, 1, 2, 4);
        }
        // conquete (TODO) : BLACK_URUKS, DOL_GULDUR_ORCS, DUNLENDINGS, DUNLENDING_WARRIORS, ENTS, GUNDABAD_ORCS
        if (name.equals("fangorn_wasteland")) {
            spawn(b, LOTREntities.URUK_HAI, 16, 4, 6); spawn(b, LOTREntities.URUK_HAI_CROSSBOWER, 8, 4, 6); spawn(b, LOTREntities.URUK_HAI_SAPPER, 2, 1, 2); spawn(b, LOTREntities.URUK_HAI_BERSERKER, 4, 4, 6); spawn(b, LOTREntities.URUK_WARG, 1, 4, 4);
        }
        if (name.equals("far_harad_coast")) {
            spawn(b, LOTREntities.CORSAIR, 10, 2, 6);
        }
        if (name.equals("far_harad_volcano")) {
            spawn(b, LOTREntities.HALF_TROLL, 60, 2, 4); spawn(b, LOTREntities.HALF_TROLL_WARRIOR, 35, 2, 4);
        }
        // conquete (TODO) : ANGMAR_ORCS, ANGMAR_WARGS, BLUE_DWARVES, DOL_GULDUR_ORCS, DWARVES, MIRKWOOD_SPIDERS
        if (name.equals("grey_mountains")) {
            spawn(b, LOTREntities.GUNDABAD_ORC, 60, 4, 6); spawn(b, LOTREntities.GUNDABAD_ORC_ARCHER, 30, 4, 6); spawn(b, LOTREntities.GUNDABAD_WARG, 20, 4, 4); spawn(b, LOTREntities.GUNDABAD_URUK, 14, 2, 4); spawn(b, LOTREntities.GUNDABAD_URUK_ARCHER, 7, 2, 4);
        }
        if (name.equals("iron_hills")) {
            spawn(b, LOTREntities.DWARF, 100, 4, 4); spawn(b, LOTREntities.DWARF_WARRIOR, 20, 4, 4); spawn(b, LOTREntities.DWARF_AXE_THROWER, 10, 4, 4);
        }
        // conquete (TODO) : BLACK_URUKS, HALF_TROLLS, MORDOR_BOMBARDIERS, MORDOR_ORCS, MORDOR_WARGS, OLOG_HAI
        if (name.equals("lamedon_hills")) {
            spawn(b, LOTREntities.GONDOR_LEVYMAN, 3, 2, 4);
        }
        // conquete (TODO) : ANGMAR_ORCS, ANGMAR_WARGS, GUNDABAD_ORCS, GUNDABAD_URUKS, GUNDABAD_WARGS
        if (name.equals("lindon")) {
            spawn(b, LOTREntities.HIGH_ELF, 10, 4, 6); spawn(b, LOTREntities.HIGH_ELF_WARRIOR, 2, 4, 4);
        }
        // conquete (TODO) : ANGMAR_HILLMEN, ANGMAR_ORCS, ANGMAR_WARGS, RANGERS_NORTH, RIVENDELL_WARRIORS
        if (name.equals("lone_lands")) {
            spawn(b, LOTREntities.GUNDABAD_ORC, 20, 4, 6); spawn(b, LOTREntities.GUNDABAD_ORC_ARCHER, 10, 4, 6); spawn(b, LOTREntities.GUNDABAD_WARG, 2, 4, 4); spawn(b, LOTREntities.GUNDABAD_URUK, 2, 2, 4); spawn(b, LOTREntities.GUNDABAD_URUK_ARCHER, 1, 2, 4);
        }
        // conquete (TODO) : BLACK_URUKS, DOL_GULDUR_ORCS, GUNDABAD_ORCS, GUNDABAD_URUKS, GUNDABAD_WARGS, MIRKWOOD_SPIDERS
        if (name.equals("lothlorien")) {
            spawn(b, LOTREntities.GALADHRIM_ELF, 10, 4, 6); spawn(b, LOTREntities.GALADHRIM_WARRIOR, 2, 4, 4); spawn(b, LOTREntities.GALADHRIM_WARDEN, 1, 4, 4);
        }
        // conquete (TODO) : BLACK_URUKS, DOL_GULDUR_ORCS, GUNDABAD_ORCS, GUNDABAD_URUKS, GUNDABAD_WARGS, MIRKWOOD_SPIDERS
        if (name.equals("lothlorien_edge")) {
            spawn(b, LOTREntities.GALADHRIM_ELF, 5, 4, 6); spawn(b, LOTREntities.GALADHRIM_WARRIOR, 1, 4, 4); spawn(b, LOTREntities.GALADHRIM_WARDEN, 20, 4, 4);
        }
        // conquete (TODO) : ANGMAR_HILLMEN, ANGMAR_ORCS, ANGMAR_WARGS, RANGERS_NORTH
        if (name.equals("midgewater")) {
            spawn(b, LOTREntities.GUNDABAD_ORC, 20, 4, 6); spawn(b, LOTREntities.GUNDABAD_ORC_ARCHER, 10, 4, 6); spawn(b, LOTREntities.GUNDABAD_WARG, 2, 4, 4); spawn(b, LOTREntities.GUNDABAD_URUK, 2, 2, 4); spawn(b, LOTREntities.GUNDABAD_URUK_ARCHER, 1, 2, 4);
        }
        // conquete (TODO) : ANGMAR_ORCS, ANGMAR_WARGS, BLUE_DWARVES, DWARVES, ISENGARD_SNAGA, SNOW_TROLLS
        if (name.equals("misty_mountains")) {
            spawn(b, LOTREntities.GUNDABAD_ORC, 60, 4, 6); spawn(b, LOTREntities.GUNDABAD_ORC_ARCHER, 30, 4, 6); spawn(b, LOTREntities.GUNDABAD_WARG, 20, 4, 4); spawn(b, LOTREntities.GUNDABAD_URUK, 14, 2, 4); spawn(b, LOTREntities.GUNDABAD_URUK_ARCHER, 7, 2, 4);
        }
        // conquete (TODO) : GONDOR_SOLDIERS, RANGERS_ITHILIEN, ROHIRRIM_WARRIORS, WICKED_DWARVES
        if (name.equals("mordor")) {
            spawn(b, LOTREntities.MORDOR_ORC, 60, 4, 6); spawn(b, LOTREntities.MORDOR_ORC_ARCHER, 30, 4, 6); spawn(b, LOTREntities.MORDOR_ORC_BOMBARDIER, 5, 1, 2); spawn(b, LOTREntities.MORDOR_WARG, 30, 4, 4); spawn(b, LOTREntities.OLOG_HAI, 10, 1, 3);
        }
        // conquete (TODO) : GONDOR_SOLDIERS, RANGERS_ITHILIEN, ROHIRRIM_WARRIORS
        if (name.equals("morgul_vale")) {
            spawn(b, LOTREntities.MORDOR_ORC, 30, 4, 6); spawn(b, LOTREntities.MORDOR_ORC_ARCHER, 15, 4, 6); spawn(b, LOTREntities.MORDOR_WARG, 2, 4, 4); spawn(b, LOTREntities.OLOG_HAI, 2, 1, 3);
        }
        // conquete (TODO) : GONDOR_SOLDIERS, RANGERS_ITHILIEN
        if (name.equals("nan_ungol")) {
            spawn(b, LOTREntities.MORDOR_ORC, 60, 4, 6); spawn(b, LOTREntities.MORDOR_ORC_ARCHER, 30, 4, 6); spawn(b, LOTREntities.MORDOR_ORC_BOMBARDIER, 2, 1, 2);
        }
        // conquete (TODO) : GONDOR_SOLDIERS, RANGERS_ITHILIEN
        if (name.equals("nindalf")) {
            spawn(b, LOTREntities.MORDOR_ORC, 40, 4, 6); spawn(b, LOTREntities.MORDOR_ORC_ARCHER, 20, 4, 6); spawn(b, LOTREntities.MORDOR_WARG, 1, 4, 4);
        }
        // conquete (TODO) : GONDOR_SOLDIERS, RANGERS_ITHILIEN, ROHIRRIM_WARRIORS
        if (name.equals("nurn")) {
            spawn(b, LOTREntities.MORDOR_ORC, 60, 4, 6); spawn(b, LOTREntities.MORDOR_ORC_ARCHER, 30, 4, 6); spawn(b, LOTREntities.MORDOR_ORC_BOMBARDIER, 5, 1, 2); spawn(b, LOTREntities.MORDOR_WARG, 5, 4, 4); spawn(b, LOTREntities.OLOG_HAI, 2, 1, 3);
        }
        // conquete (TODO) : BLACK_URUKS, COAST_SOUTHRONS, CORSAIRS, EASTERLINGS, EASTERLING_GOLD_WARRIORS, EASTERLING_WARRIORS
        if (name.equals("pelennor")) {
            spawn(b, LOTREntities.GONDOR_LEVYMAN, 20, 2, 4); spawn(b, LOTREntities.GONDOR_SOLDIER, 10, 2, 8); spawn(b, LOTREntities.GONDOR_ARCHER, 5, 2, 8);
        }
        // conquete (TODO) : BLACK_URUKS, GULF_WARRIORS, MORDOR_ORCS, MORWAITH, MORWAITH_WARRIORS, OLOG_HAI
        if (name.equals("pertorogwaith")) {
            spawn(b, LOTREntities.HALF_TROLL, 60, 2, 4); spawn(b, LOTREntities.HALF_TROLL_WARRIOR, 35, 2, 4);
        }
        // conquete (TODO) : BLACK_URUKS, DALE_MEN, DALE_SOLDIERS, DORWINION_GUARDS, DORWINION_MEN, DWARVES
        if (name.equals("rhun_land")) {
            spawn(b, LOTREntities.EASTERLING_LEVYMAN, 40, 2, 4); spawn(b, LOTREntities.EASTERLING_WARRIOR, 20, 3, 6); spawn(b, LOTREntities.EASTERLING_ARCHER, 10, 3, 6); spawn(b, LOTREntities.EASTERLING_FIRE_THROWER, 4, 1, 3); spawn(b, LOTREntities.EASTERLING_GOLD_WARRIOR, 3, 2, 4); spawn(b, LOTREntities.EASTERLING_LEVYMAN, 20, 2, 4); spawn(b, LOTREntities.EASTERLING_WARRIOR, 10, 3, 6); spawn(b, LOTREntities.EASTERLING_ARCHER, 5, 3, 6); spawn(b, LOTREntities.EASTERLING_FIRE_THROWER, 2, 1, 3); spawn(b, LOTREntities.EASTERLING_GOLD_WARRIOR, 2, 2, 4); spawn(b, LOTREntities.EASTERLING_GOLD_WARRIOR, 5, 2, 4);
        }
        // conquete (TODO) : BLACK_URUKS, DALE_SOLDIERS, DORWINION_GUARDS, GONDOR_SOLDIERS, MORDOR_ORCS, MORDOR_WARGS
        if (name.equals("rhun_land_steppe")) {
            spawn(b, LOTREntities.EASTERLING_LEVYMAN, 20, 2, 4); spawn(b, LOTREntities.EASTERLING_WARRIOR, 10, 3, 6); spawn(b, LOTREntities.EASTERLING_ARCHER, 5, 3, 6); spawn(b, LOTREntities.EASTERLING_FIRE_THROWER, 2, 1, 3); spawn(b, LOTREntities.EASTERLING_LEVYMAN, 20, 2, 4); spawn(b, LOTREntities.EASTERLING_WARRIOR, 10, 3, 6); spawn(b, LOTREntities.EASTERLING_ARCHER, 5, 3, 6); spawn(b, LOTREntities.EASTERLING_FIRE_THROWER, 2, 1, 3); spawn(b, LOTREntities.EASTERLING_GOLD_WARRIOR, 2, 2, 4); spawn(b, LOTREntities.EASTERLING_GOLD_WARRIOR, 5, 2, 4);
        }
        // conquete (TODO) : DORWINION_ELF_WARRIORS, DORWINION_ELVES
        if (name.equals("rhun_red_forest")) {
            spawn(b, LOTREntities.EASTERLING_LEVYMAN, 20, 2, 4); spawn(b, LOTREntities.EASTERLING_WARRIOR, 10, 3, 6); spawn(b, LOTREntities.EASTERLING_ARCHER, 5, 3, 6); spawn(b, LOTREntities.EASTERLING_FIRE_THROWER, 2, 1, 3); spawn(b, LOTREntities.EASTERLING_LEVYMAN, 16, 2, 4); spawn(b, LOTREntities.EASTERLING_WARRIOR, 8, 3, 6); spawn(b, LOTREntities.EASTERLING_ARCHER, 4, 3, 6); spawn(b, LOTREntities.EASTERLING_FIRE_THROWER, 1, 1, 3); spawn(b, LOTREntities.EASTERLING_GOLD_WARRIOR, 1, 2, 4);
        }
        // conquete (TODO) : ANGMAR_ORCS, ANGMAR_WARGS, GUNDABAD_ORCS, GUNDABAD_URUKS, GUNDABAD_WARGS
        if (name.equals("rivendell")) {
            spawn(b, LOTREntities.RIVENDELL_ELF, 10, 4, 6); spawn(b, LOTREntities.RIVENDELL_WARRIOR, 2, 4, 4);
        }
        // conquete (TODO) : ANGMAR_ORCS, ANGMAR_WARGS, GUNDABAD_ORCS, GUNDABAD_URUKS, GUNDABAD_WARGS
        if (name.equals("rivendell_hills")) {
            spawn(b, LOTREntities.RIVENDELL_WARRIOR, 10, 4, 4);
        }
        // conquete (TODO) : DUNLENDINGS, DUNLENDING_WARRIORS, ENTS, HUORNS, ROHIRRIM_WARRIORS
        if (name.equals("rohan_uruk_highlands")) {
            spawn(b, LOTREntities.URUK_HAI, 20, 4, 6); spawn(b, LOTREntities.URUK_HAI_CROSSBOWER, 10, 4, 6); spawn(b, LOTREntities.URUK_HAI_SAPPER, 3, 1, 2); spawn(b, LOTREntities.URUK_HAI_BERSERKER, 5, 4, 6); spawn(b, LOTREntities.URUK_WARG, 4, 4, 4);
        }
        // conquete (TODO) : ANGMAR_ORCS, ANGMAR_WARGS, GUNDABAD_ORCS, GUNDABAD_WARGS, ISENGARD_SNAGA, RUFFIANS
        if (name.equals("shire")) {
            spawn(b, LOTREntities.HOBBIT, 40, 1, 4); spawn(b, LOTREntities.HOBBIT_BOUNDER, 1, 1, 3);
        }
        // conquete (TODO) : BLACK_URUKS, GULF_WARRIORS, HALF_TROLLS, MORDOR_ORCS, MORWAITH, MORWAITH_WARRIORS
        if (name.equals("tauredain_clearing")) {
            spawn(b, LOTREntities.TAUREDAIN_WARRIOR, 4, 4, 6); spawn(b, LOTREntities.TAUREDAIN_BLOWGUNNER, 8, 4, 6); spawn(b, LOTREntities.TAUREDAIN_WARRIOR, 10, 4, 6); spawn(b, LOTREntities.TAUREDAIN_BLOWGUNNER, 20, 4, 6);
        }
        // conquete (TODO) : BLACK_URUKS, DOL_AMROTH_SOLDIERS, GONDOR_SOLDIERS, MORDOR_ORCS, PELARGIR_SOLDIERS
        if (name.equals("tolfalas")) {
            spawn(b, LOTREntities.CORSAIR, 10, 2, 6); spawn(b, LOTREntities.CORSAIR, 10, 2, 6);
        }
        // conquete (TODO) : RANGERS_NORTH
        if (name.equals("tundra")) {
            spawn(b, LOTREntities.GUNDABAD_ORC, 20, 4, 6); spawn(b, LOTREntities.GUNDABAD_ORC_ARCHER, 10, 4, 6); spawn(b, LOTREntities.GUNDABAD_WARG, 5, 4, 4);
        }
        // conquete (TODO) : ANGMAR_ORCS, ANGMAR_WARGS, GUNDABAD_ORCS, GUNDABAD_WARGS, ISENGARD_SNAGA, URUK_HAI
        if (name.equals("white_downs")) {
            spawn(b, LOTREntities.HOBBIT, 40, 1, 4); spawn(b, LOTREntities.HOBBIT_BOUNDER, 1, 1, 3); spawn(b, LOTREntities.HOBBIT, 4, 1, 4); spawn(b, LOTREntities.HOBBIT_BOUNDER, 1, 1, 3);
        }
        // conquete (TODO) : DALE_MEN, DALE_SOLDIERS, DOL_GULDUR_ORCS, DWARVES, EASTERLINGS, EASTERLING_GOLD_WARRIORS
        if (name.equals("wilderland_north")) {
            spawn(b, LOTREntities.GUNDABAD_ORC, 20, 4, 6); spawn(b, LOTREntities.GUNDABAD_ORC_ARCHER, 10, 4, 6); spawn(b, LOTREntities.GUNDABAD_WARG, 2, 4, 4); spawn(b, LOTREntities.GUNDABAD_URUK, 4, 2, 4); spawn(b, LOTREntities.GUNDABAD_URUK_ARCHER, 2, 2, 4);
        }
        // conquete (TODO) : DOL_GULDUR_ORCS, GUNDABAD_ORCS, GUNDABAD_URUKS, GUNDABAD_WARGS, MIRKWOOD_SPIDERS, MIRK_TROLLS
        if (name.equals("woodland_realm")) {
            spawn(b, LOTREntities.WOOD_ELF, 10, 4, 6); spawn(b, LOTREntities.WOOD_ELF_SCOUT, 3, 4, 4); spawn(b, LOTREntities.WOOD_ELF_WARRIOR, 1, 4, 4);
        }
    }

    private static void spawn(MobSpawnInfo.Builder b,
                              RegistryObject<? extends EntityType<?>> type,
                              int weight, int min, int max) {
        b.addSpawn(EntityClassification.CREATURE,
                new MobSpawnInfo.Spawners(type.get(), weight, min, max));
    }
}
