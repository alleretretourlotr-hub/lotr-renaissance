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
}
