package fr.alleretretour.lotr.init;

import fr.alleretretour.lotr.LOTRMod;
import fr.alleretretour.lotr.entity.projectile.LOTREntityCrossbowBolt;
import fr.alleretretour.lotr.entity.projectile.LOTREntityDart;
import fr.alleretretour.lotr.entity.projectile.LOTREntityThrownWeapon;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Registre differe des entites.
 * Lot 4 : projectiles. Phase 4 : les 444 NPC viendront ici.
 */
public class LOTREntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, LOTRMod.MOD_ID);

    public static final RegistryObject<EntityType<LOTREntityThrownWeapon>> THROWN_WEAPON =
            ENTITIES.register("thrown_weapon", () -> EntityType.Builder
                    .<LOTREntityThrownWeapon>of(LOTREntityThrownWeapon::new, EntityClassification.MISC)
                    .sized(0.5f, 0.5f)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build("thrown_weapon"));

    public static final RegistryObject<EntityType<LOTREntityCrossbowBolt>> CROSSBOW_BOLT =
            ENTITIES.register("crossbow_bolt", () -> EntityType.Builder
                    .<LOTREntityCrossbowBolt>of(LOTREntityCrossbowBolt::new, EntityClassification.MISC)
                    .sized(0.5f, 0.5f)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build("crossbow_bolt"));

    public static final RegistryObject<EntityType<LOTREntityDart>> DART =
            ENTITIES.register("dart", () -> EntityType.Builder
                    .<LOTREntityDart>of(LOTREntityDart::new, EntityClassification.MISC)
                    .sized(0.4f, 0.4f)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build("dart"));

    // ===== Phase 4 : PNJ =====
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityGondorSoldier>>
            GONDOR_SOLDIER = ENTITIES.register("gondor_soldier",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityGondorSoldier::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("gondor_soldier"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityGondorArcher>>
            GONDOR_ARCHER = ENTITIES.register("gondor_archer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityGondorArcher::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("gondor_archer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityGondorTowerGuard>>
            GONDOR_TOWER_GUARD = ENTITIES.register("gondor_tower_guard",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityGondorTowerGuard::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("gondor_tower_guard"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityGondorianCaptain>>
            GONDORIAN_CAPTAIN = ENTITIES.register("gondorian_captain",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityGondorianCaptain::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("gondorian_captain"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityGondorLevyman>>
            GONDOR_LEVYMAN = ENTITIES.register("gondor_levyman",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityGondorLevyman::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("gondor_levyman"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityMordorOrc>>
            MORDOR_ORC = ENTITIES.register("mordor_orc",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityMordorOrc::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.55f, 1.7f).build("mordor_orc"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityMordorOrcArcher>>
            MORDOR_ORC_ARCHER = ENTITIES.register("mordor_orc_archer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityMordorOrcArcher::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.55f, 1.7f).build("mordor_orc_archer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityUrukHai>>
            URUK_HAI = ENTITIES.register("uruk_hai",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityUrukHai::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.85f).build("uruk_hai"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityGondorBlacksmith>>
            GONDOR_BLACKSMITH = ENTITIES.register("gondor_blacksmith",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityGondorBlacksmith::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("gondor_blacksmith"));

    // ===== Rohan =====
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityRohirrimWarrior>>
            ROHIRRIM_WARRIOR = ENTITIES.register("rohirrim_warrior",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityRohirrimWarrior::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("rohirrim_warrior"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityRohirrimArcher>>
            ROHIRRIM_ARCHER = ENTITIES.register("rohirrim_archer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityRohirrimArcher::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("rohirrim_archer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityRohirrimMarshal>>
            ROHIRRIM_MARSHAL = ENTITIES.register("rohirrim_marshal",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityRohirrimMarshal::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("rohirrim_marshal"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityRohanShieldmaiden>>
            ROHAN_SHIELDMAIDEN = ENTITIES.register("rohan_shieldmaiden",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityRohanShieldmaiden::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("rohan_shieldmaiden"));

    // ===== Elfes =====
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityGaladhrimWarrior>>
            GALADHRIM_WARRIOR = ENTITIES.register("galadhrim_warrior",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityGaladhrimWarrior::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("galadhrim_warrior"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityGaladhrimWarden>>
            GALADHRIM_WARDEN = ENTITIES.register("galadhrim_warden",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityGaladhrimWarden::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("galadhrim_warden"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityGaladhrimLord>>
            GALADHRIM_LORD = ENTITIES.register("galadhrim_lord",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityGaladhrimLord::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("galadhrim_lord"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityHighElfWarrior>>
            HIGH_ELF_WARRIOR = ENTITIES.register("high_elf_warrior",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityHighElfWarrior::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("high_elf_warrior"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityHighElfLord>>
            HIGH_ELF_LORD = ENTITIES.register("high_elf_lord",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityHighElfLord::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("high_elf_lord"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityWoodElfWarrior>>
            WOOD_ELF_WARRIOR = ENTITIES.register("wood_elf_warrior",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityWoodElfWarrior::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("wood_elf_warrior"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityWoodElfScout>>
            WOOD_ELF_SCOUT = ENTITIES.register("wood_elf_scout",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityWoodElfScout::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("wood_elf_scout"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityWoodElfCaptain>>
            WOOD_ELF_CAPTAIN = ENTITIES.register("wood_elf_captain",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityWoodElfCaptain::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("wood_elf_captain"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityRivendellWarrior>>
            RIVENDELL_WARRIOR = ENTITIES.register("rivendell_warrior",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityRivendellWarrior::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("rivendell_warrior"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityRivendellLord>>
            RIVENDELL_LORD = ENTITIES.register("rivendell_lord",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityRivendellLord::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("rivendell_lord"));

    // ===== MEGA-LOT Phase 4 =====

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDwarf>>
            DWARF = ENTITIES.register("dwarf",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDwarf::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.55f, 1.45f).build("dwarf"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDwarfWarrior>>
            DWARF_WARRIOR = ENTITIES.register("dwarf_warrior",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDwarfWarrior::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.55f, 1.45f).build("dwarf_warrior"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDwarfCommander>>
            DWARF_COMMANDER = ENTITIES.register("dwarf_commander",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDwarfCommander::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.55f, 1.45f).build("dwarf_commander"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityBlueDwarfWarrior>>
            BLUE_DWARF_WARRIOR = ENTITIES.register("blue_dwarf_warrior",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityBlueDwarfWarrior::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.55f, 1.45f).build("blue_dwarf_warrior"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityHobbit>>
            HOBBIT = ENTITIES.register("hobbit",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityHobbit::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.5f, 1.2f).build("hobbit"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityHobbitBounder>>
            HOBBIT_BOUNDER = ENTITIES.register("hobbit_bounder",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityHobbitBounder::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.5f, 1.2f).build("hobbit_bounder"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityBreeMan>>
            BREE_MAN = ENTITIES.register("bree_man",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityBreeMan::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("bree_man"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityBreeGuard>>
            BREE_GUARD = ENTITIES.register("bree_guard",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityBreeGuard::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("bree_guard"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityRangerNorth>>
            RANGER_NORTH = ENTITIES.register("ranger_north",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityRangerNorth::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("ranger_north"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityRangerNorthCaptain>>
            RANGER_NORTH_CAPTAIN = ENTITIES.register("ranger_north_captain",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityRangerNorthCaptain::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("ranger_north_captain"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDaleSoldier>>
            DALE_SOLDIER = ENTITIES.register("dale_soldier",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDaleSoldier::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("dale_soldier"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDaleArcher>>
            DALE_ARCHER = ENTITIES.register("dale_archer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDaleArcher::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("dale_archer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDaleLevyman>>
            DALE_LEVYMAN = ENTITIES.register("dale_levyman",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDaleLevyman::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("dale_levyman"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDorwinionGuard>>
            DORWINION_GUARD = ENTITIES.register("dorwinion_guard",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDorwinionGuard::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("dorwinion_guard"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDorwinionElfWarrior>>
            DORWINION_ELF_WARRIOR = ENTITIES.register("dorwinion_elf_warrior",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDorwinionElfWarrior::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("dorwinion_elf_warrior"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDorwinionElfArcher>>
            DORWINION_ELF_ARCHER = ENTITIES.register("dorwinion_elf_archer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDorwinionElfArcher::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("dorwinion_elf_archer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityAngmarOrc>>
            ANGMAR_ORC = ENTITIES.register("angmar_orc",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityAngmarOrc::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.55f, 1.7f).build("angmar_orc"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityAngmarOrcArcher>>
            ANGMAR_ORC_ARCHER = ENTITIES.register("angmar_orc_archer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityAngmarOrcArcher::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.55f, 1.7f).build("angmar_orc_archer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityAngmarHillmanWarrior>>
            ANGMAR_HILLMAN_WARRIOR = ENTITIES.register("angmar_hillman_warrior",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityAngmarHillmanWarrior::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("angmar_hillman_warrior"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityGundabadOrc>>
            GUNDABAD_ORC = ENTITIES.register("gundabad_orc",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityGundabadOrc::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.55f, 1.7f).build("gundabad_orc"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityGundabadOrcArcher>>
            GUNDABAD_ORC_ARCHER = ENTITIES.register("gundabad_orc_archer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityGundabadOrcArcher::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.55f, 1.7f).build("gundabad_orc_archer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityGundabadUruk>>
            GUNDABAD_URUK = ENTITIES.register("gundabad_uruk",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityGundabadUruk::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.85f).build("gundabad_uruk"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDolGuldurOrc>>
            DOL_GULDUR_ORC = ENTITIES.register("dol_guldur_orc",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDolGuldurOrc::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.55f, 1.7f).build("dol_guldur_orc"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDolGuldurOrcArcher>>
            DOL_GULDUR_ORC_ARCHER = ENTITIES.register("dol_guldur_orc_archer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDolGuldurOrcArcher::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.55f, 1.7f).build("dol_guldur_orc_archer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDunlending>>
            DUNLENDING = ENTITIES.register("dunlending",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDunlending::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("dunlending"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDunlendingWarrior>>
            DUNLENDING_WARRIOR = ENTITIES.register("dunlending_warrior",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDunlendingWarrior::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("dunlending_warrior"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDunlendingBerserker>>
            DUNLENDING_BERSERKER = ENTITIES.register("dunlending_berserker",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDunlendingBerserker::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("dunlending_berserker"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityNearHaradrimWarrior>>
            NEAR_HARADRIM_WARRIOR = ENTITIES.register("near_haradrim_warrior",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityNearHaradrimWarrior::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("near_haradrim_warrior"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityNearHaradrimArcher>>
            NEAR_HARADRIM_ARCHER = ENTITIES.register("near_haradrim_archer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityNearHaradrimArcher::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("near_haradrim_archer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityCorsair>>
            CORSAIR = ENTITIES.register("corsair",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityCorsair::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("corsair"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityUmbarWarrior>>
            UMBAR_WARRIOR = ENTITIES.register("umbar_warrior",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityUmbarWarrior::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("umbar_warrior"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityUmbarArcher>>
            UMBAR_ARCHER = ENTITIES.register("umbar_archer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityUmbarArcher::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("umbar_archer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityEasterlingWarrior>>
            EASTERLING_WARRIOR = ENTITIES.register("easterling_warrior",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityEasterlingWarrior::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("easterling_warrior"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityEasterlingArcher>>
            EASTERLING_ARCHER = ENTITIES.register("easterling_archer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityEasterlingArcher::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("easterling_archer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityEasterlingGoldWarrior>>
            EASTERLING_GOLD_WARRIOR = ENTITIES.register("easterling_gold_warrior",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityEasterlingGoldWarrior::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("easterling_gold_warrior"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityMoredainWarrior>>
            MOREDAIN_WARRIOR = ENTITIES.register("moredain_warrior",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityMoredainWarrior::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("moredain_warrior"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityMoredainHuntsman>>
            MOREDAIN_HUNTSMAN = ENTITIES.register("moredain_huntsman",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityMoredainHuntsman::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("moredain_huntsman"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityTauredainWarrior>>
            TAUREDAIN_WARRIOR = ENTITIES.register("tauredain_warrior",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityTauredainWarrior::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("tauredain_warrior"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityTauredainBlowgunner>>
            TAUREDAIN_BLOWGUNNER = ENTITIES.register("tauredain_blowgunner",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityTauredainBlowgunner::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("tauredain_blowgunner"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityHalfTroll>>
            HALF_TROLL = ENTITIES.register("half_troll",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityHalfTroll::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.7f, 2.0f).build("half_troll"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityHalfTrollWarrior>>
            HALF_TROLL_WARRIOR = ENTITIES.register("half_troll_warrior",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityHalfTrollWarrior::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.7f, 2.0f).build("half_troll_warrior"));

    // ===== LOT ARMES EXOTIQUES =====

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDwarfAxeThrower>>
            DWARF_AXE_THROWER = ENTITIES.register("dwarf_axe_thrower",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDwarfAxeThrower::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.55f, 1.45f).build("dwarf_axe_thrower"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityBlueDwarfAxeThrower>>
            BLUE_DWARF_AXE_THROWER = ENTITIES.register("blue_dwarf_axe_thrower",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityBlueDwarfAxeThrower::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.55f, 1.45f).build("blue_dwarf_axe_thrower"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDorwinionCrossbower>>
            DORWINION_CROSSBOWER = ENTITIES.register("dorwinion_crossbower",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDorwinionCrossbower::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("dorwinion_crossbower"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityUrukHaiCrossbower>>
            URUK_HAI_CROSSBOWER = ENTITIES.register("uruk_hai_crossbower",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityUrukHaiCrossbower::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.85f).build("uruk_hai_crossbower"));

    // ===== LOT MARCHANDS =====

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDwarfSmith>>
            DWARF_SMITH = ENTITIES.register("dwarf_smith",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDwarfSmith::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.55f, 1.45f).build("dwarf_smith"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityBlueMountainsSmith>>
            BLUE_MOUNTAINS_SMITH = ENTITIES.register("blue_mountains_smith",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityBlueMountainsSmith::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.55f, 1.45f).build("blue_mountains_smith"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityHobbitBartender>>
            HOBBIT_BARTENDER = ENTITIES.register("hobbit_bartender",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityHobbitBartender::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.5f, 1.2f).build("hobbit_bartender"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityBreeInnkeeper>>
            BREE_INNKEEPER = ENTITIES.register("bree_innkeeper",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityBreeInnkeeper::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("bree_innkeeper"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDaleBlacksmith>>
            DALE_BLACKSMITH = ENTITIES.register("dale_blacksmith",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDaleBlacksmith::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("dale_blacksmith"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityEasterlingBlacksmith>>
            EASTERLING_BLACKSMITH = ENTITIES.register("easterling_blacksmith",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityEasterlingBlacksmith::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("easterling_blacksmith"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityNearHaradMerchant>>
            NEAR_HARAD_MERCHANT = ENTITIES.register("near_harad_merchant",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityNearHaradMerchant::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("near_harad_merchant"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityMoredainTrader>>
            MOREDAIN_TRADER = ENTITIES.register("moredain_trader",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityMoredainTrader::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("moredain_trader"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityAngmarOrcTrader>>
            ANGMAR_ORC_TRADER = ENTITIES.register("angmar_orc_trader",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityAngmarOrcTrader::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.55f, 1.7f).build("angmar_orc_trader"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDolGuldurOrcTrader>>
            DOL_GULDUR_ORC_TRADER = ENTITIES.register("dol_guldur_orc_trader",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDolGuldurOrcTrader::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.55f, 1.7f).build("dol_guldur_orc_trader"));

    // ===== LOT PORTE-BANNIERES =====

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityGondorBannerBearer>>
            GONDOR_BANNER_BEARER = ENTITIES.register("gondor_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityGondorBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("gondor_banner_bearer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityRohanBannerBearer>>
            ROHAN_BANNER_BEARER = ENTITIES.register("rohan_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityRohanBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("rohan_banner_bearer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDwarfBannerBearer>>
            DWARF_BANNER_BEARER = ENTITIES.register("dwarf_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDwarfBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.55f, 1.45f).build("dwarf_banner_bearer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityBlueDwarfBannerBearer>>
            BLUE_DWARF_BANNER_BEARER = ENTITIES.register("blue_dwarf_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityBlueDwarfBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.55f, 1.45f).build("blue_dwarf_banner_bearer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityHighElfBannerBearer>>
            HIGH_ELF_BANNER_BEARER = ENTITIES.register("high_elf_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityHighElfBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("high_elf_banner_bearer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityRivendellBannerBearer>>
            RIVENDELL_BANNER_BEARER = ENTITIES.register("rivendell_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityRivendellBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("rivendell_banner_bearer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityGaladhrimBannerBearer>>
            GALADHRIM_BANNER_BEARER = ENTITIES.register("galadhrim_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityGaladhrimBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("galadhrim_banner_bearer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityRangerNorthBannerBearer>>
            RANGER_NORTH_BANNER_BEARER = ENTITIES.register("ranger_north_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityRangerNorthBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("ranger_north_banner_bearer"));

    // ===== BANNIERE PLANTEE =====
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.item.LOTREntityBannerPlaced>>
            BANNER_PLACED = ENTITIES.register("banner_placed",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.item.LOTREntityBannerPlaced::new,
                    net.minecraft.entity.EntityClassification.MISC)
                    .sized(0.9f, 3.0f)
                    .clientTrackingRange(10)
                    .build("banner_placed"));
    // ===== LOT CIVILS ELFES =====
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityGaladhrimElf>>
            GALADHRIM_ELF = ENTITIES.register("galadhrim_elf",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityGaladhrimElf::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("galadhrim_elf"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityHighElf>>
            HIGH_ELF = ENTITIES.register("high_elf",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityHighElf::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("high_elf"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityRivendellElf>>
            RIVENDELL_ELF = ENTITIES.register("rivendell_elf",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityRivendellElf::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("rivendell_elf"));

    // ===== MONTURES =====
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.animal.LOTREntityHorse>>
            HORSE = ENTITIES.register("horse",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.animal.LOTREntityHorse::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(1.3964844f, 1.6f).build("horse"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.animal.LOTREntityWildBoar>>
            WILD_BOAR = ENTITIES.register("wild_boar",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.animal.LOTREntityWildBoar::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.9f, 0.9f).build("wild_boar"));
    // ===== MEGA-LOT 2 =====
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityHobbitShirriff>>
            HOBBIT_SHIRRIFF = ENTITIES.register("hobbit_shirriff",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityHobbitShirriff::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.5f, 1.2f).build("hobbit_shirriff"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityBreeRuffian>>
            BREE_RUFFIAN = ENTITIES.register("bree_ruffian",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityBreeRuffian::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("bree_ruffian"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityRuffianBrute>>
            RUFFIAN_BRUTE = ENTITIES.register("ruffian_brute",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityRuffianBrute::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("ruffian_brute"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityRuffianSpy>>
            RUFFIAN_SPY = ENTITIES.register("ruffian_spy",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityRuffianSpy::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("ruffian_spy"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityRangerIthilien>>
            RANGER_ITHILIEN = ENTITIES.register("ranger_ithilien",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityRangerIthilien::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("ranger_ithilien"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityRangerIthilienCaptain>>
            RANGER_ITHILIEN_CAPTAIN = ENTITIES.register("ranger_ithilien_captain",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityRangerIthilienCaptain::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("ranger_ithilien_captain"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityHarnedorWarrior>>
            HARNEDOR_WARRIOR = ENTITIES.register("harnedor_warrior",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityHarnedorWarrior::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("harnedor_warrior"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityHarnedorArcher>>
            HARNEDOR_ARCHER = ENTITIES.register("harnedor_archer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityHarnedorArcher::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("harnedor_archer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityAngmarHillmanChieftain>>
            ANGMAR_HILLMAN_CHIEFTAIN = ENTITIES.register("angmar_hillman_chieftain",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityAngmarHillmanChieftain::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("angmar_hillman_chieftain"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDolGuldurOrcChieftain>>
            DOL_GULDUR_ORC_CHIEFTAIN = ENTITIES.register("dol_guldur_orc_chieftain",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDolGuldurOrcChieftain::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("dol_guldur_orc_chieftain"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDunlendingWarlord>>
            DUNLENDING_WARLORD = ENTITIES.register("dunlending_warlord",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDunlendingWarlord::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("dunlending_warlord"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityEasterlingWarlord>>
            EASTERLING_WARLORD = ENTITIES.register("easterling_warlord",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityEasterlingWarlord::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("easterling_warlord"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityHalfTrollWarlord>>
            HALF_TROLL_WARLORD = ENTITIES.register("half_troll_warlord",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityHalfTrollWarlord::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.7f, 2.1f).build("half_troll_warlord"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityHarnedorWarlord>>
            HARNEDOR_WARLORD = ENTITIES.register("harnedor_warlord",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityHarnedorWarlord::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("harnedor_warlord"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityNearHaradrimWarlord>>
            NEAR_HARADRIM_WARLORD = ENTITIES.register("near_haradrim_warlord",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityNearHaradrimWarlord::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("near_haradrim_warlord"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityMoredainChieftain>>
            MOREDAIN_CHIEFTAIN = ENTITIES.register("moredain_chieftain",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityMoredainChieftain::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("moredain_chieftain"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityTauredainChieftain>>
            TAUREDAIN_CHIEFTAIN = ENTITIES.register("tauredain_chieftain",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityTauredainChieftain::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("tauredain_chieftain"));

    // ===== CREATURES / MONTURES (lot creatures) =====

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.animal.LOTREntityRhino>>
            RHINO = ENTITIES.register("rhino",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.animal.LOTREntityRhino::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(1.6f, 1.8f).build("rhino"));
    // ===== LOT TROUPES 3 =====
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityAngmarHillman>>
            ANGMAR_HILLMAN = ENTITIES.register("angmar_hillman",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityAngmarHillman::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("angmar_hillman"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityAngmarHillmanAxeThrower>>
            ANGMAR_HILLMAN_AXE_THROWER = ENTITIES.register("angmar_hillman_axe_thrower",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityAngmarHillmanAxeThrower::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("angmar_hillman_axe_thrower"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDunlendingArcher>>
            DUNLENDING_ARCHER = ENTITIES.register("dunlending_archer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDunlendingArcher::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("dunlending_archer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDunlendingAxeThrower>>
            DUNLENDING_AXE_THROWER = ENTITIES.register("dunlending_axe_thrower",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDunlendingAxeThrower::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("dunlending_axe_thrower"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityEasterlingLevyman>>
            EASTERLING_LEVYMAN = ENTITIES.register("easterling_levyman",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityEasterlingLevyman::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("easterling_levyman"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntitySouthronChampion>>
            SOUTHRON_CHAMPION = ENTITIES.register("southron_champion",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntitySouthronChampion::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("southron_champion"));

    // ===== PORTE-BANNIERES (mega-lot 2) =====
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityAngmarHillmanBannerBearer>>
            ANGMAR_HILLMAN_BANNER_BEARER = ENTITIES.register("angmar_hillman_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityAngmarHillmanBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("angmar_hillman_banner_bearer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDolGuldurBannerBearer>>
            DOL_GULDUR_BANNER_BEARER = ENTITIES.register("dol_guldur_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDolGuldurBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.55f, 1.7f).build("dol_guldur_banner_bearer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDunlendingBannerBearer>>
            DUNLENDING_BANNER_BEARER = ENTITIES.register("dunlending_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDunlendingBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("dunlending_banner_bearer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityEasterlingBannerBearer>>
            EASTERLING_BANNER_BEARER = ENTITIES.register("easterling_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityEasterlingBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("easterling_banner_bearer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityHalfTrollBannerBearer>>
            HALF_TROLL_BANNER_BEARER = ENTITIES.register("half_troll_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityHalfTrollBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.7f, 2.0f).build("half_troll_banner_bearer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityHarnedorBannerBearer>>
            HARNEDOR_BANNER_BEARER = ENTITIES.register("harnedor_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityHarnedorBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("harnedor_banner_bearer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityNearHaradBannerBearer>>
            NEAR_HARAD_BANNER_BEARER = ENTITIES.register("near_harad_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityNearHaradBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("near_harad_banner_bearer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityMoredainBannerBearer>>
            MOREDAIN_BANNER_BEARER = ENTITIES.register("moredain_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityMoredainBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("moredain_banner_bearer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityTauredainBannerBearer>>
            TAUREDAIN_BANNER_BEARER = ENTITIES.register("tauredain_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityTauredainBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("tauredain_banner_bearer"));

    // ===== CREATURES 2 =====
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityMirkwoodSpider>>
            MIRKWOOD_SPIDER = ENTITIES.register("mirkwood_spider",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityMirkwoodSpider::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(1.4f, 0.9f).build("mirkwood_spider"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.animal.LOTREntityZebra>>
            ZEBRA = ENTITIES.register("zebra",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.animal.LOTREntityZebra::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(1.3964844f, 1.6f).build("zebra"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityMirkTroll>>
            MIRK_TROLL = ENTITIES.register("mirk_troll",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityMirkTroll::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(1.4f, 3.0f).build("mirk_troll"));

    // ===== LOT POT DE FEU =====
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityEasterlingFireThrower>>
            EASTERLING_FIRE_THROWER = ENTITIES.register("easterling_fire_thrower",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityEasterlingFireThrower::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("easterling_fire_thrower"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.projectile.LOTREntityFirePot>>
            FIRE_POT = ENTITIES.register("fire_pot",
            () -> EntityType.Builder.<fr.alleretretour.lotr.entity.projectile.LOTREntityFirePot>of(
                    fr.alleretretour.lotr.entity.projectile.LOTREntityFirePot::new,
                    net.minecraft.entity.EntityClassification.MISC)
                    .sized(0.25f, 0.25f).clientTrackingRange(4).updateInterval(10)
                    .build("fire_pot"));
    // ===== LOT 7 RECRUTEURS =====

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDaleCaptain>>
            DALE_CAPTAIN = ENTITIES.register("dale_captain",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDaleCaptain::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("dale_captain"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityBlueDwarfCommander>>
            BLUE_DWARF_COMMANDER = ENTITIES.register("blue_dwarf_commander",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityBlueDwarfCommander::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.55f, 1.45f).build("blue_dwarf_commander"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDorwinionCaptain>>
            DORWINION_CAPTAIN = ENTITIES.register("dorwinion_captain",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDorwinionCaptain::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("dorwinion_captain"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityUmbarCaptain>>
            UMBAR_CAPTAIN = ENTITIES.register("umbar_captain",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityUmbarCaptain::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("umbar_captain"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityCorsairCaptain>>
            CORSAIR_CAPTAIN = ENTITIES.register("corsair_captain",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityCorsairCaptain::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("corsair_captain"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityBreeCaptain>>
            BREE_CAPTAIN = ENTITIES.register("bree_captain",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityBreeCaptain::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("bree_captain"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityMordorOrcMercenaryCaptain>>
            MORDOR_ORC_MERCENARY_CAPTAIN = ENTITIES.register("mordor_orc_mercenary_captain",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityMordorOrcMercenaryCaptain::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("mordor_orc_mercenary_captain"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityUrukHaiMercenaryCaptain>>
            URUK_HAI_MERCENARY_CAPTAIN = ENTITIES.register("uruk_hai_mercenary_captain",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityUrukHaiMercenaryCaptain::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("uruk_hai_mercenary_captain"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityGundabadOrcMercenaryCaptain>>
            GUNDABAD_ORC_MERCENARY_CAPTAIN = ENTITIES.register("gundabad_orc_mercenary_captain",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityGundabadOrcMercenaryCaptain::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("gundabad_orc_mercenary_captain"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityAngmarOrcMercenaryCaptain>>
            ANGMAR_ORC_MERCENARY_CAPTAIN = ENTITIES.register("angmar_orc_mercenary_captain",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityAngmarOrcMercenaryCaptain::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("angmar_orc_mercenary_captain"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.animal.LOTREntityElk>>
            ELK = ENTITIES.register("elk",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.animal.LOTREntityElk::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(1.4f, 2.0f).build("elk"));
    // ===== LOT 8 OMBRE =====
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityIsengardSnaga>>
            ISENGARD_SNAGA = ENTITIES.register("isengard_snaga",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityIsengardSnaga::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("isengard_snaga"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityIsengardSnagaArcher>>
            ISENGARD_SNAGA_ARCHER = ENTITIES.register("isengard_snaga_archer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityIsengardSnagaArcher::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("isengard_snaga_archer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityUrukHaiSapper>>
            URUK_HAI_SAPPER = ENTITIES.register("uruk_hai_sapper",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityUrukHaiSapper::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("uruk_hai_sapper"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityUrukHaiBerserker>>
            URUK_HAI_BERSERKER = ENTITIES.register("uruk_hai_berserker",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityUrukHaiBerserker::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("uruk_hai_berserker"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityGundabadUrukArcher>>
            GUNDABAD_URUK_ARCHER = ENTITIES.register("gundabad_uruk_archer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityGundabadUrukArcher::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("gundabad_uruk_archer"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityMordorOrcBombardier>>
            MORDOR_ORC_BOMBARDIER = ENTITIES.register("mordor_orc_bombardier",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityMordorOrcBombardier::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("mordor_orc_bombardier"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityAngmarOrcBombardier>>
            ANGMAR_ORC_BOMBARDIER = ENTITIES.register("angmar_orc_bombardier",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityAngmarOrcBombardier::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("angmar_orc_bombardier"));

    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityWoodElf>>
            WOOD_ELF = ENTITIES.register("wood_elf",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityWoodElf::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("wood_elf"));

    // ===== WARGS DE FACTION =====
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityMordorWarg>>
            MORDOR_WARG = ENTITIES.register("mordor_warg",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityMordorWarg::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(1.4f, 1.6f).build("mordor_warg"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityUrukWarg>>
            URUK_WARG = ENTITIES.register("uruk_warg",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityUrukWarg::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(1.4f, 1.6f).build("uruk_warg"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityGundabadWarg>>
            GUNDABAD_WARG = ENTITIES.register("gundabad_warg",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityGundabadWarg::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(1.4f, 1.6f).build("gundabad_warg"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityAngmarWarg>>
            ANGMAR_WARG = ENTITIES.register("angmar_warg",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityAngmarWarg::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(1.4f, 1.6f).build("angmar_warg"));

    // ===== PORTE-BANNIERES (lot 9) =====
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityMordorBannerBearer>>
            MORDOR_BANNER_BEARER = ENTITIES.register("mordor_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityMordorBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.55f, 1.7f).build("mordor_banner_bearer"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityMinasMorgulBannerBearer>>
            MINAS_MORGUL_BANNER_BEARER = ENTITIES.register("minas_morgul_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityMinasMorgulBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.55f, 1.7f).build("minas_morgul_banner_bearer"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityUrukHaiBannerBearer>>
            URUK_HAI_BANNER_BEARER = ENTITIES.register("uruk_hai_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityUrukHaiBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.85f).build("uruk_hai_banner_bearer"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityGundabadBannerBearer>>
            GUNDABAD_BANNER_BEARER = ENTITIES.register("gundabad_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityGundabadBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.55f, 1.7f).build("gundabad_banner_bearer"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityAngmarBannerBearer>>
            ANGMAR_BANNER_BEARER = ENTITIES.register("angmar_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityAngmarBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.55f, 1.7f).build("angmar_banner_bearer"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityWoodElfBannerBearer>>
            WOOD_ELF_BANNER_BEARER = ENTITIES.register("wood_elf_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityWoodElfBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("wood_elf_banner_bearer"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDaleBannerBearer>>
            DALE_BANNER_BEARER = ENTITIES.register("dale_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDaleBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("dale_banner_bearer"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityEsgarothBannerBearer>>
            ESGAROTH_BANNER_BEARER = ENTITIES.register("esgaroth_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityEsgarothBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("esgaroth_banner_bearer"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityUmbarBannerBearer>>
            UMBAR_BANNER_BEARER = ENTITIES.register("umbar_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityUmbarBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("umbar_banner_bearer"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityBreeBannerBearer>>
            BREE_BANNER_BEARER = ENTITIES.register("bree_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityBreeBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("bree_banner_bearer"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityDorwinionBannerBearer>>
            DORWINION_BANNER_BEARER = ENTITIES.register("dorwinion_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityDorwinionBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("dorwinion_banner_bearer"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityRangerIthilienBannerBearer>>
            RANGER_ITHILIEN_BANNER_BEARER = ENTITIES.register("ranger_ithilien_banner_bearer",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityRangerIthilienBannerBearer::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(0.6f, 1.8f).build("ranger_ithilien_banner_bearer"));

    // ===== TROLLS ET BOMBARDIERS =====
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityTroll>>
            TROLL = ENTITIES.register("troll",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityTroll::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(1.4f, 3.0f).build("troll"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityMountainTroll>>
            MOUNTAIN_TROLL = ENTITIES.register("mountain_troll",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityMountainTroll::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(1.4f, 3.2f).build("mountain_troll"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityOlogHai>>
            OLOG_HAI = ENTITIES.register("olog_hai",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityOlogHai::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(1.4f, 3.2f).build("olog_hai"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityMordorWargBombardier>>
            MORDOR_WARG_BOMBARDIER = ENTITIES.register("mordor_warg_bombardier",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityMordorWargBombardier::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(1.4f, 1.6f).build("mordor_warg_bombardier"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityUrukWargBombardier>>
            URUK_WARG_BOMBARDIER = ENTITIES.register("uruk_warg_bombardier",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityUrukWargBombardier::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(1.4f, 1.6f).build("uruk_warg_bombardier"));
    public static final RegistryObject<EntityType<fr.alleretretour.lotr.entity.npc.LOTREntityAngmarWargBombardier>>
            ANGMAR_WARG_BOMBARDIER = ENTITIES.register("angmar_warg_bombardier",
            () -> EntityType.Builder.of(
                    fr.alleretretour.lotr.entity.npc.LOTREntityAngmarWargBombardier::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized(1.4f, 1.6f).build("angmar_warg_bombardier"));
}
