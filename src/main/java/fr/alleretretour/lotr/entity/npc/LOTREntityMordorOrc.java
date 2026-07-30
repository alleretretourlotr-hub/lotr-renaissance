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

/**
 * PORT de LOTREntityMordorOrc : l'orque du Mordor.
 * 20 PV, arme aleatoire du pool original (cimeterre, dague empoisonnee,
 * hache de bataille, marteau, pioche orques). Ennemi mortel du Gondor :
 * les batailles PNJ contre PNJ commencent ici.
 */
public class LOTREntityMordorOrc extends LOTREntityNPC {

    private static final String[] WEAPONS = {"orc_battleaxe", "orc_dagger", "orc_poisoned_dagger", "orc_scimitar", "orc_hammer", "orc_pickaxe"};

    public LOTREntityMordorOrc(EntityType<? extends LOTREntityMordorOrc> type, World world) {
        super(type, world);
    }

    @Override
    protected String getSpeechBank() {
        return "mordor/orc";
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.MORDOR;
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return createNPCAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.28);
    }

    private static net.minecraft.item.ItemStack stackOf(String id) {
        net.minecraft.item.Item item = net.minecraftforge.registries.ForgeRegistries.ITEMS
                .getValue(new net.minecraft.util.ResourceLocation(id));
        return item == null ? net.minecraft.item.ItemStack.EMPTY
                : new net.minecraft.item.ItemStack(item);
    }

    /** PORT de onSpawnWithEgg (Legacy LOTREntityMordorOrc) : pool d'armes + armure exacts. */
    @javax.annotation.Nullable
    @Override
    public net.minecraft.entity.ILivingEntityData finalizeSpawn(
            net.minecraft.world.IServerWorld world, net.minecraft.world.DifficultyInstance difficulty,
            net.minecraft.entity.SpawnReason reason,
            @javax.annotation.Nullable net.minecraft.entity.ILivingEntityData data,
            @javax.annotation.Nullable net.minecraft.nbt.CompoundNBT nbt) {
        String[] pool = {"lotr:orc_battleaxe", "lotr:orc_dagger", "lotr:orc_poisoned_dagger", "lotr:orc_scimitar", "lotr:orc_hammer", "lotr:orc_pickaxe", "lotr:orc_axe", "lotr:orc_polearm", "lotr:orc_spear"};
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.MAINHAND,
                stackOf(pool[getRandom().nextInt(pool.length)]));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.HEAD, stackOf("lotr:orc_helmet"));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.CHEST, stackOf("lotr:orc_chestplate"));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.LEGS, stackOf("lotr:orc_leggings"));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.FEET, stackOf("lotr:orc_boots"));
        for (net.minecraft.inventory.EquipmentSlotType slot
                : net.minecraft.inventory.EquipmentSlotType.values()) {
            setDropChance(slot, 0.05f);
        }
        return super.finalizeSpawn(world, difficulty, reason, data, nbt);
    }
}
