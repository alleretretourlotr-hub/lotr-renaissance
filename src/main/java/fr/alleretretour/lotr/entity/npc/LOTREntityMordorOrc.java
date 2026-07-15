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
                .add(Attributes.ATTACK_DAMAGE, 2.5)
                .add(Attributes.MOVEMENT_SPEED, 0.28);
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
        setItemSlot(EquipmentSlotType.MAINHAND,
                new ItemStack(itemOf(WEAPONS[random.nextInt(WEAPONS.length)])));
        for (EquipmentSlotType slot : EquipmentSlotType.values()) {
            setDropChance(slot, 0.05f);
        }
        return super.finalizeSpawn(world, difficulty, reason, data, nbt);
    }
}
