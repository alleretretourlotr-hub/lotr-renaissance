package fr.alleretretour.lotr.init;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nullable;

/**
 * Oeufs d'apparition des PNJ (onglet Divers).
 * SpawnEggItem vanilla exige le type au construct ; on passe null et on
 * resout via RegistryObject dans getType (pattern standard 1.16.5).
 */
public class LOTRSpawnEggs {

    public static final RegistryObject<Item> GONDOR_SOLDIER_EGG =
            LOTRItems.ITEMS.register("gondor_soldier_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GONDOR_SOLDIER,
                            0xF8F8F0, 0x1F2D45));

    public static final RegistryObject<Item> GONDOR_ARCHER_EGG =
            LOTRItems.ITEMS.register("gondor_archer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GONDOR_ARCHER, 0x514C4C, 0xE5DADA));

    public static final RegistryObject<Item> GONDOR_TOWER_GUARD_EGG =
            LOTRItems.ITEMS.register("gondor_tower_guard_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GONDOR_TOWER_GUARD, 0x514C4C, 0xE5DADA));

    public static final RegistryObject<Item> GONDORIAN_CAPTAIN_EGG =
            LOTRItems.ITEMS.register("gondorian_captain_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GONDORIAN_CAPTAIN, 0x514C4C, 0xE5DADA));

    public static final RegistryObject<Item> GONDOR_LEVYMAN_EGG =
            LOTRItems.ITEMS.register("gondor_levyman_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GONDOR_LEVYMAN, 0xA4A3A2, 0x684634));

    public static final RegistryObject<Item> MORDOR_ORC_EGG =
            LOTRItems.ITEMS.register("mordor_orc_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.MORDOR_ORC, 0x332B22, 0x6B7567));

    public static final RegistryObject<Item> MORDOR_ORC_ARCHER_EGG =
            LOTRItems.ITEMS.register("mordor_orc_archer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.MORDOR_ORC_ARCHER, 0x332B22, 0x6B7567));

    public static final RegistryObject<Item> URUK_HAI_EGG =
            LOTRItems.ITEMS.register("uruk_hai_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.URUK_HAI, 0x24261A, 0x58593F));

    public static final RegistryObject<Item> GONDOR_BLACKSMITH_EGG =
            LOTRItems.ITEMS.register("gondor_blacksmith_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GONDOR_BLACKSMITH, 0xCEB8A5, 0x56403A));

    public static final RegistryObject<Item> ROHIRRIM_WARRIOR_EGG =
            LOTRItems.ITEMS.register("rohirrim_warrior_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.ROHIRRIM_WARRIOR, 0x888888, 0x333333));

    public static final RegistryObject<Item> ROHIRRIM_ARCHER_EGG =
            LOTRItems.ITEMS.register("rohirrim_archer_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.ROHIRRIM_ARCHER, 0x888888, 0x333333));

    public static final RegistryObject<Item> ROHIRRIM_MARSHAL_EGG =
            LOTRItems.ITEMS.register("rohirrim_marshal_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.ROHIRRIM_MARSHAL, 0x888888, 0x333333));

    public static final RegistryObject<Item> ROHAN_SHIELDMAIDEN_EGG =
            LOTRItems.ITEMS.register("rohan_shieldmaiden_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.ROHAN_SHIELDMAIDEN, 0x888888, 0x333333));

    public static final RegistryObject<Item> GALADHRIM_WARRIOR_EGG =
            LOTRItems.ITEMS.register("galadhrim_warrior_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GALADHRIM_WARRIOR, 0xC1BEBA, 0xEAB956));

    public static final RegistryObject<Item> GALADHRIM_WARDEN_EGG =
            LOTRItems.ITEMS.register("galadhrim_warden_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GALADHRIM_WARDEN, 0xA0A39D, 0x7A7C77));

    public static final RegistryObject<Item> GALADHRIM_LORD_EGG =
            LOTRItems.ITEMS.register("galadhrim_lord_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.GALADHRIM_LORD, 0xC1BEBA, 0xEAB956));

    public static final RegistryObject<Item> HIGH_ELF_WARRIOR_EGG =
            LOTRItems.ITEMS.register("high_elf_warrior_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.HIGH_ELF_WARRIOR, 0xE3E3E8, 0x6B6D9A));

    public static final RegistryObject<Item> HIGH_ELF_LORD_EGG =
            LOTRItems.ITEMS.register("high_elf_lord_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.HIGH_ELF_LORD, 0xE3E3E8, 0x6B6D9A));

    public static final RegistryObject<Item> WOOD_ELF_WARRIOR_EGG =
            LOTRItems.ITEMS.register("wood_elf_warrior_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.WOOD_ELF_WARRIOR, 0xBAA398, 0x595C2C));

    public static final RegistryObject<Item> WOOD_ELF_SCOUT_EGG =
            LOTRItems.ITEMS.register("wood_elf_scout_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.WOOD_ELF_SCOUT, 0x05210C, 0x3B6033));

    public static final RegistryObject<Item> WOOD_ELF_CAPTAIN_EGG =
            LOTRItems.ITEMS.register("wood_elf_captain_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.WOOD_ELF_CAPTAIN, 0xBAA398, 0x595C2C));

    public static final RegistryObject<Item> RIVENDELL_WARRIOR_EGG =
            LOTRItems.ITEMS.register("rivendell_warrior_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.RIVENDELL_WARRIOR, 0xE0E4E6, 0xA39FB0));

    public static final RegistryObject<Item> RIVENDELL_LORD_EGG =
            LOTRItems.ITEMS.register("rivendell_lord_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.RIVENDELL_LORD, 0xE0E4E6, 0xA39FB0));

    public static void init() {
    }

    static class LOTRSpawnEgg extends SpawnEggItem {

        private final RegistryObject<? extends EntityType<?>> typeRO;

        @SuppressWarnings("ConstantConditions")
        LOTRSpawnEgg(RegistryObject<? extends EntityType<?>> type, int primary, int secondary) {
            super(null, primary, secondary,
                    new Item.Properties().tab(LOTRCreativeTabs.TAB_MISC));
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
