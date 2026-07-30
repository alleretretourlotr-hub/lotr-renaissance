package fr.alleretretour.lotr.entity.npc;

import fr.alleretretour.lotr.fac.LOTRFaction;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/** GENERE (mega-lot Phase 4) - port de lotr.common.entity.npc.LOTREntityDolGuldurOrcArcher */
public class LOTREntityDolGuldurOrcArcher extends LOTREntityNPC {

    public LOTREntityDolGuldurOrcArcher(EntityType<? extends LOTREntityDolGuldurOrcArcher> type, World world) {
        super(type, world);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.DOL_GULDUR;
    }

    @Override
    protected boolean isRangedNPC() {
        return true;
    }

    @Override
    protected String getSpeechBank() {
        return "dol_guldur/orc";
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

    /** PORT de onSpawnWithEgg (Legacy LOTREntityDolGuldurOrcArcher) : pool d'armes + armure exacts. */
    @javax.annotation.Nullable
    @Override
    public net.minecraft.entity.ILivingEntityData finalizeSpawn(
            net.minecraft.world.IServerWorld world, net.minecraft.world.DifficultyInstance difficulty,
            net.minecraft.entity.SpawnReason reason,
            @javax.annotation.Nullable net.minecraft.entity.ILivingEntityData data,
            @javax.annotation.Nullable net.minecraft.nbt.CompoundNBT nbt) {
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.MAINHAND, stackOf("lotr:orc_bow"));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.HEAD, stackOf("lotr:dol_guldur_helmet"));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.CHEST, stackOf("lotr:dol_guldur_chestplate"));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.LEGS, stackOf("lotr:dol_guldur_leggings"));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.FEET, stackOf("lotr:dol_guldur_boots"));
        for (net.minecraft.inventory.EquipmentSlotType slot
                : net.minecraft.inventory.EquipmentSlotType.values()) {
            setDropChance(slot, 0.05f);
        }
        return super.finalizeSpawn(world, difficulty, reason, data, nbt);
    }
}
