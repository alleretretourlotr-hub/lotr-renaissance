package fr.alleretretour.lotr.entity.npc;

import fr.alleretretour.lotr.fac.LOTRFaction;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/** GENERE (mega-lot Phase 4) - port de lotr.common.entity.npc.LOTREntityDwarfCommander */
public class LOTREntityDwarfCommander extends LOTREntityNPC {

    public LOTREntityDwarfCommander(EntityType<? extends LOTREntityDwarfCommander> type, World world) {
        super(type, world);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.DURINS_FOLK;
    }

    @Override
    protected String getSpeechBank() {
        return "dwarf/warrior";
    }

    @Override
    protected net.minecraft.util.ActionResultType mobInteract(
            net.minecraft.entity.player.PlayerEntity player, net.minecraft.util.Hand hand) {
        // clic simple = embauche si alignement >= base Legacy ; sinon discours
        if (hand == net.minecraft.util.Hand.MAIN_HAND && !player.isShiftKeyDown()) {
            float alignment = fr.alleretretour.lotr.fac.LOTRPlayerDataProvider
                    .get(player).getAlignment(getFaction());
            if (alignment < fr.alleretretour.lotr.hire.LOTRHireRosters.DWARF_COMMANDER_BASE) {
                return super.mobInteract(player, hand); // discours contextuel
            }
            if (!level.isClientSide
                    && player instanceof net.minecraft.entity.player.ServerPlayerEntity) {
                final LOTREntityDwarfCommander self = this;
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
                                fr.alleretretour.lotr.hire.LOTRHireRosters.dwarfCommander(), self);
                    }
                });
            }
            return net.minecraft.util.ActionResultType.CONSUME;
        }
        return super.mobInteract(player, hand);
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return createNPCAttributes()
                .add(Attributes.MAX_HEALTH, 30.0)
                .add(Attributes.ATTACK_DAMAGE, 2.0);
    }

    private static net.minecraft.item.ItemStack stackOf(String id) {
        net.minecraft.item.Item item = net.minecraftforge.registries.ForgeRegistries.ITEMS
                .getValue(new net.minecraft.util.ResourceLocation(id));
        return item == null ? net.minecraft.item.ItemStack.EMPTY
                : new net.minecraft.item.ItemStack(item);
    }

    /** PORT de onSpawnWithEgg (Legacy LOTREntityDwarfCommander) : pool d'armes + armure exacts. */
    @javax.annotation.Nullable
    @Override
    public net.minecraft.entity.ILivingEntityData finalizeSpawn(
            net.minecraft.world.IServerWorld world, net.minecraft.world.DifficultyInstance difficulty,
            net.minecraft.entity.SpawnReason reason,
            @javax.annotation.Nullable net.minecraft.entity.ILivingEntityData data,
            @javax.annotation.Nullable net.minecraft.nbt.CompoundNBT nbt) {
        String[] pool = {"lotr:dwarven_hammer"};
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.MAINHAND,
                stackOf(pool[getRandom().nextInt(pool.length)]));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.HEAD, stackOf("lotr:dwarven_helmet"));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.CHEST, stackOf("lotr:dwarven_gold_chestplate"));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.LEGS, stackOf("lotr:dwarven_gold_leggings"));
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.FEET, stackOf("lotr:dwarven_gold_boots"));
        for (net.minecraft.inventory.EquipmentSlotType slot
                : net.minecraft.inventory.EquipmentSlotType.values()) {
            setDropChance(slot, 0.05f);
        }
        return super.finalizeSpawn(world, difficulty, reason, data, nbt);
    }
}
