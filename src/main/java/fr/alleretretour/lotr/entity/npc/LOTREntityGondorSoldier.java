package fr.alleretretour.lotr.entity.npc;

import fr.alleretretour.lotr.fac.LOTRFaction;
import fr.alleretretour.lotr.init.LOTRItemsCombat;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * PNJ PILOTE de la Phase 4 : le soldat du Gondor.
 * Equipement complet en acier gondorien (nos items de la Phase 1 !),
 * 25 PV et 3.5 de degats comme l'original.
 */
public class LOTREntityGondorSoldier extends LOTREntityNPC {

    public LOTREntityGondorSoldier(EntityType<? extends LOTREntityGondorSoldier> type, World world) {
        super(type, world);
    }

    @Override
    protected String getSpeechBank() {
        return "gondor/soldier";
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.GONDOR;
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return createNPCAttributes()
                .add(Attributes.MAX_HEALTH, 25.0)
                .add(Attributes.ATTACK_DAMAGE, 3.5);
    }

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficulty,
                                           SpawnReason reason, @Nullable ILivingEntityData data,
                                           @Nullable CompoundNBT nbt) {
        setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(LOTRItemsCombat.GONDOR_SWORD.get()));
        setItemSlot(EquipmentSlotType.HEAD, new ItemStack(LOTRItemsCombat.GONDOR_HELMET.get()));
        setItemSlot(EquipmentSlotType.CHEST, new ItemStack(LOTRItemsCombat.GONDOR_CHESTPLATE.get()));
        setItemSlot(EquipmentSlotType.LEGS, new ItemStack(LOTRItemsCombat.GONDOR_LEGGINGS.get()));
        setItemSlot(EquipmentSlotType.FEET, new ItemStack(LOTRItemsCombat.GONDOR_BOOTS.get()));
        for (EquipmentSlotType slot : EquipmentSlotType.values()) {
            setDropChance(slot, 0.05f);
        }
        return super.finalizeSpawn(world, difficulty, reason, data, nbt);
    }
}
