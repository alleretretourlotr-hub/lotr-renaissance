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
public class LOTREntityRohirrimMarshal extends LOTREntityNPC {

    public LOTREntityRohirrimMarshal(EntityType<? extends LOTREntityRohirrimMarshal> type, World world) {
        super(type, world);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.ROHAN;
    }

    @Override
    protected String getSpeechBank() {
        return "rohan/marshal";
    }

    @Override
    protected net.minecraft.util.ActionResultType mobInteract(
            net.minecraft.entity.player.PlayerEntity player, net.minecraft.util.Hand hand) {
        // clic simple = embauche si alignement >= base Legacy ; sinon discours
        if (hand == net.minecraft.util.Hand.MAIN_HAND && !player.isShiftKeyDown()) {
            float alignment = fr.alleretretour.lotr.fac.LOTRPlayerDataProvider
                    .get(player).getAlignment(getFaction());
            if (alignment < fr.alleretretour.lotr.hire.LOTRHireRosters.ROHAN_MARSHAL_BASE) {
                return super.mobInteract(player, hand); // discours contextuel
            }
            if (!level.isClientSide
                    && player instanceof net.minecraft.entity.player.ServerPlayerEntity) {
                final LOTREntityRohirrimMarshal self = this;
                player.openMenu(new net.minecraft.inventory.container.INamedContainerProvider() {
                    @Override
                    public net.minecraft.util.text.ITextComponent getDisplayName() {
                        return getName();
                    }

                    @Override
                    public net.minecraft.inventory.container.Container createMenu(
                            int id, net.minecraft.entity.player.PlayerInventory inv,
                            net.minecraft.entity.player.PlayerEntity p) {
                        return new fr.alleretretour.lotr.inventory.LOTRContainerHire(id, inv,
                                fr.alleretretour.lotr.hire.LOTRHireRosters.rohirrimMarshal(), self);
                    }
                });
            }
            return net.minecraft.util.ActionResultType.CONSUME;
        }
        return super.mobInteract(player, hand);
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return createNPCAttributes()
                .add(Attributes.MAX_HEALTH, 25.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 2.0);
    }

    private static net.minecraft.item.ItemStack stackOf(String id) {
        net.minecraft.item.Item item = net.minecraftforge.registries.ForgeRegistries.ITEMS
                .getValue(new net.minecraft.util.ResourceLocation(id));
        return item == null ? net.minecraft.item.ItemStack.EMPTY
                : new net.minecraft.item.ItemStack(item);
    }

    /** PORT de onSpawnWithEgg (Legacy LOTREntityRohirrimMarshal) : pool d'armes + armure exacts. */
    @javax.annotation.Nullable
    @Override
    public net.minecraft.entity.ILivingEntityData finalizeSpawn(
            net.minecraft.world.IServerWorld world, net.minecraft.world.DifficultyInstance difficulty,
            net.minecraft.entity.SpawnReason reason,
            @javax.annotation.Nullable net.minecraft.entity.ILivingEntityData data,
            @javax.annotation.Nullable net.minecraft.nbt.CompoundNBT nbt) {
        String[] pool = {"lotr:rohan_sword"};
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.MAINHAND,
                stackOf(pool[getRandom().nextInt(pool.length)]));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.HEAD, stackOf("lotr:rohan_marshal_helmet"));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.CHEST, stackOf("lotr:rohan_marshal_chestplate"));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.LEGS, stackOf("lotr:rohan_marshal_leggings"));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.FEET, stackOf("lotr:rohan_marshal_boots"));
        for (net.minecraft.inventory.EquipmentSlotType slot
                : net.minecraft.inventory.EquipmentSlotType.values()) {
            setDropChance(slot, 0.05f);
        }
        return super.finalizeSpawn(world, difficulty, reason, data, nbt);
    }
}
