package fr.alleretretour.lotr.entity.npc;

import fr.alleretretour.lotr.fac.LOTRFaction;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/** GENERE (mega-lot Phase 4) - port de lotr.common.entity.npc.LOTREntityRangerNorthCaptain */
public class LOTREntityRangerNorthCaptain extends LOTREntityNPC {

    public LOTREntityRangerNorthCaptain(EntityType<? extends LOTREntityRangerNorthCaptain> type, World world) {
        super(type, world);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.RANGER_NORTH;
    }

    @Override
    protected String getSpeechBank() {
        return "ranger/warrior";
    }

    @Override
    protected net.minecraft.util.ActionResultType mobInteract(
            net.minecraft.entity.player.PlayerEntity player, net.minecraft.util.Hand hand) {
        // clic simple = embauche si alignement >= base Legacy ; sinon discours
        if (hand == net.minecraft.util.Hand.MAIN_HAND && !player.isShiftKeyDown()) {
            float alignment = fr.alleretretour.lotr.fac.LOTRPlayerDataProvider
                    .get(player).getAlignment(getFaction());
            if (alignment < fr.alleretretour.lotr.hire.LOTRHireRosters.RANGER_CAPTAIN_BASE) {
                return super.mobInteract(player, hand); // discours contextuel
            }
            if (!level.isClientSide
                    && player instanceof net.minecraft.entity.player.ServerPlayerEntity) {
                final LOTREntityRangerNorthCaptain self = this;
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
                                fr.alleretretour.lotr.hire.LOTRHireRosters.rangerCaptain(), self);
                    }
                });
            }
            return net.minecraft.util.ActionResultType.CONSUME;
        }
        return super.mobInteract(player, hand);
    }

    @Override
    protected boolean isRangedNPC() {
        return true;
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return createNPCAttributes()
                .add(Attributes.MAX_HEALTH, 25.0)
                .add(Attributes.ATTACK_DAMAGE, 2.0);
    }

    private static net.minecraft.item.ItemStack stackOf(String id) {
        net.minecraft.item.Item item = net.minecraftforge.registries.ForgeRegistries.ITEMS
                .getValue(new net.minecraft.util.ResourceLocation(id));
        return item == null ? net.minecraft.item.ItemStack.EMPTY
                : new net.minecraft.item.ItemStack(item);
    }

    /** PORT de onSpawnWithEgg (Legacy LOTREntityRangerNorthCaptain) : pool d'armes + armure exacts. */
    @javax.annotation.Nullable
    @Override
    public net.minecraft.entity.ILivingEntityData finalizeSpawn(
            net.minecraft.world.IServerWorld world, net.minecraft.world.DifficultyInstance difficulty,
            net.minecraft.entity.SpawnReason reason,
            @javax.annotation.Nullable net.minecraft.entity.ILivingEntityData data,
            @javax.annotation.Nullable net.minecraft.nbt.CompoundNBT nbt) {
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.MAINHAND, stackOf("lotr:ranger_bow"));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.HEAD, stackOf("lotr:ranger_helmet"));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.CHEST, stackOf("lotr:ranger_chestplate"));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.LEGS, stackOf("lotr:ranger_leggings"));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.FEET, stackOf("lotr:ranger_boots"));
        for (net.minecraft.inventory.EquipmentSlotType slot
                : net.minecraft.inventory.EquipmentSlotType.values()) {
            setDropChance(slot, 0.05f);
        }
        return super.finalizeSpawn(world, difficulty, reason, data, nbt);
    }
}
