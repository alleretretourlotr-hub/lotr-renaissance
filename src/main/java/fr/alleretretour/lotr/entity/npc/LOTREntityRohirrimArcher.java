package fr.alleretretour.lotr.entity.npc;

import fr.alleretretour.lotr.fac.LOTRFaction;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/** GENERE - PNJ ROHAN. */
public class LOTREntityRohirrimArcher extends LOTREntityNPC {

    public LOTREntityRohirrimArcher(EntityType<? extends LOTREntityRohirrimArcher> type, World world) {
        super(type, world);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.ROHAN;
    }

    @Override
    protected boolean isRangedNPC() {
        return true;
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return createNPCAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 2.5);
    }

    private static Item itemOf(String id) {
        return net.minecraftforge.registries.ForgeRegistries.ITEMS.getValue(
                new ResourceLocation("lotr", id));
    }

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficulty,
                                           SpawnReason reason, @Nullable ILivingEntityData data,
                                           @Nullable CompoundNBT nbt) {
        setItemSlot(EquipmentSlotType.FEET, new ItemStack(itemOf("rohan_boots")));
        setItemSlot(EquipmentSlotType.LEGS, new ItemStack(itemOf("rohan_leggings")));
        setItemSlot(EquipmentSlotType.CHEST, new ItemStack(itemOf("rohan_chestplate")));
        setItemSlot(EquipmentSlotType.HEAD, new ItemStack(itemOf("rohan_helmet")));
        setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(itemOf("rohan_bow")));
        for (EquipmentSlotType slot : EquipmentSlotType.values()) {
            setDropChance(slot, 0.05f);
        }
        return super.finalizeSpawn(world, difficulty, reason, data, nbt);
    }
}
