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

/** GENERE - milicien - arme aleatoire, gambison */
public class LOTREntityGondorLevyman extends LOTREntityNPC {

    public LOTREntityGondorLevyman(EntityType<? extends LOTREntityGondorLevyman> type, World world) {
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
                .add(Attributes.MAX_HEALTH, 22)
                .add(Attributes.ATTACK_DAMAGE, 3.0);
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
        String[] weapons = {"gondor_sword", "gondor_hammer", "gondor_pike", "bronze_sword", "bronze_axe", "bronze_battleaxe"};
        setItemSlot(EquipmentSlotType.MAINHAND,
                new ItemStack(itemOf(weapons[random.nextInt(weapons.length)])));
        setItemSlot(EquipmentSlotType.HEAD, new ItemStack(itemOf("gondor_helmet")));
        setItemSlot(EquipmentSlotType.CHEST, new ItemStack(itemOf("gondor_gambeson_chestplate")));
        for (EquipmentSlotType slot : EquipmentSlotType.values()) {
            setDropChance(slot, 0.05f);
        }
        return super.finalizeSpawn(world, difficulty, reason, data, nbt);
    }
}
