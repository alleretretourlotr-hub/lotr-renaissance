package fr.alleretretour.lotr.entity.npc;

import fr.alleretretour.lotr.fac.LOTRFaction;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.World;

/** GENERE PAR L'USINE (mega-lot 2) - port de lotr.common.entity.npc.LOTREntityHarnedorWarrior */
public class LOTREntityHarnedorWarrior extends LOTREntityNPC {

    public LOTREntityHarnedorWarrior(EntityType<? extends LOTREntityHarnedorWarrior> type, World world) {
        super(type, world);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.NEAR_HARAD;
    }

    @Override
    protected String getSpeechBank() {
        return "near_harad/warrior";
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

    /** PORT de onSpawnWithEgg (Legacy LOTREntityHarnedorWarrior). */
    @javax.annotation.Nullable
    @Override
    public net.minecraft.entity.ILivingEntityData finalizeSpawn(
            net.minecraft.world.IServerWorld world, net.minecraft.world.DifficultyInstance difficulty,
            net.minecraft.entity.SpawnReason reason,
            @javax.annotation.Nullable net.minecraft.entity.ILivingEntityData data,
            @javax.annotation.Nullable net.minecraft.nbt.CompoundNBT nbt) {
        String[] pool = {"lotr:harad_spear"};
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.MAINHAND,
                stackOf(pool[getRandom().nextInt(pool.length)]));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.HEAD, stackOf("lotr:harnedor_helmet"));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.CHEST, stackOf("lotr:harnedor_chestplate"));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.LEGS, stackOf("lotr:harnedor_leggings"));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.FEET, stackOf("lotr:harnedor_boots"));
        for (net.minecraft.inventory.EquipmentSlotType slot
                : net.minecraft.inventory.EquipmentSlotType.values()) {
            setDropChance(slot, 0.05f);
        }
        return super.finalizeSpawn(world, difficulty, reason, data, nbt);
    }
}
