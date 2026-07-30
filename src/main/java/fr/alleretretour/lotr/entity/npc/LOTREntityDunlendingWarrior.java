package fr.alleretretour.lotr.entity.npc;

import fr.alleretretour.lotr.fac.LOTRFaction;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/** GENERE (mega-lot Phase 4) - port de lotr.common.entity.npc.LOTREntityDunlendingWarrior */
public class LOTREntityDunlendingWarrior extends LOTREntityNPC {

    public LOTREntityDunlendingWarrior(EntityType<? extends LOTREntityDunlendingWarrior> type, World world) {
        super(type, world);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.DUNLAND;
    }

    @Override
    protected String getSpeechBank() {
        return "dunland/warrior";
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return createNPCAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.ATTACK_DAMAGE, 2.0);
    }

    private static net.minecraft.item.ItemStack stackOf(String id) {
        net.minecraft.item.Item item = net.minecraftforge.registries.ForgeRegistries.ITEMS
                .getValue(new net.minecraft.util.ResourceLocation(id));
        return item == null ? net.minecraft.item.ItemStack.EMPTY
                : new net.minecraft.item.ItemStack(item);
    }

    /** PORT de onSpawnWithEgg (Legacy LOTREntityDunlendingWarrior) : pool d'armes + armure exacts. */
    @javax.annotation.Nullable
    @Override
    public net.minecraft.entity.ILivingEntityData finalizeSpawn(
            net.minecraft.world.IServerWorld world, net.minecraft.world.DifficultyInstance difficulty,
            net.minecraft.entity.SpawnReason reason,
            @javax.annotation.Nullable net.minecraft.entity.ILivingEntityData data,
            @javax.annotation.Nullable net.minecraft.nbt.CompoundNBT nbt) {
        String[] pool = {"minecraft:iron_sword", "lotr:bronze_sword", "lotr:bronze_dagger", "lotr:bronze_battleaxe", "lotr:iron_spear", "lotr:bronze_spear"};
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.MAINHAND,
                stackOf(pool[getRandom().nextInt(pool.length)]));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.HEAD, stackOf("lotr:dunlending_helmet"));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.CHEST, stackOf("lotr:dunlending_chestplate"));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.LEGS, stackOf("lotr:dunlending_leggings"));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.FEET, stackOf("lotr:dunlending_boots"));
        for (net.minecraft.inventory.EquipmentSlotType slot
                : net.minecraft.inventory.EquipmentSlotType.values()) {
            setDropChance(slot, 0.05f);
        }
        return super.finalizeSpawn(world, difficulty, reason, data, nbt);
    }
}
