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
 * PORT de LOTREntityUrukHai : l'orque du Mordor.
 * 20 PV, arme aleatoire du pool original (cimeterre, dague empoisonnee,
 * hache de bataille, marteau, pioche orques). Ennemi mortel du Gondor :
 * les batailles PNJ contre PNJ commencent ici.
 */
public class LOTREntityUrukHai extends LOTREntityNPC {

    private static final String[] WEAPONS = {"uruk_scimitar", "uruk_battleaxe", "uruk_hammer", "uruk_pike"};

    public LOTREntityUrukHai(EntityType<? extends LOTREntityUrukHai> type, World world) {
        super(type, world);
    }

    @Override
    protected String getSpeechBank() {
        return "isengard/orc";
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.ISENGARD;
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return createNPCAttributes()
                .add(Attributes.MAX_HEALTH, 28.0)
                .add(Attributes.ATTACK_DAMAGE, 4.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3);
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
        setItemSlot(EquipmentSlotType.HEAD, new ItemStack(itemOf("uruk_helmet")));
        setItemSlot(EquipmentSlotType.CHEST, new ItemStack(itemOf("uruk_chestplate")));
        setItemSlot(EquipmentSlotType.LEGS, new ItemStack(itemOf("uruk_leggings")));
        setItemSlot(EquipmentSlotType.FEET, new ItemStack(itemOf("uruk_boots")));
        for (EquipmentSlotType slot : EquipmentSlotType.values()) {
            setDropChance(slot, 0.05f);
        }
        return super.finalizeSpawn(world, difficulty, reason, data, nbt);
    }
}
