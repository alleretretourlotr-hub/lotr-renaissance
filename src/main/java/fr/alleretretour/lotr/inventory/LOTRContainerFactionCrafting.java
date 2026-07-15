package fr.alleretretour.lotr.inventory;

import fr.alleretretour.lotr.init.LOTRContainers;
import fr.alleretretour.lotr.init.LOTRRecipeTypes;
import fr.alleretretour.lotr.recipe.LOTRRecipeFactionShaped;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.CraftingResultSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;

import java.util.Optional;

/**
 * Container des tables de faction : copie du WorkbenchContainer vanilla,
 * mais la resolution passe par le type de recette de la faction.
 */
public class LOTRContainerFactionCrafting extends Container {

    private final CraftingInventory craftSlots = new CraftingInventory(this, 3, 3);
    private final CraftResultInventory resultSlots = new CraftResultInventory();
    private final IWorldPosCallable access;
    private final PlayerEntity player;
    private final String faction;
    private final Block tableBlock;

    public LOTRContainerFactionCrafting(int id, PlayerInventory inv) {
        this(id, inv, IWorldPosCallable.NULL, "none", null);
    }

    public LOTRContainerFactionCrafting(int id, PlayerInventory inv, IWorldPosCallable access,
                                        String faction, Block tableBlock) {
        super(LOTRContainers.FACTION_CRAFTING.get(), id);
        this.access = access;
        this.player = inv.player;
        this.faction = faction;
        this.tableBlock = tableBlock;

        addSlot(new CraftingResultSlot(player, craftSlots, resultSlots, 0, 124, 35));
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                addSlot(new Slot(craftSlots, col + row * 3, 30 + col * 18, 17 + row * 18));
            }
        }
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(inv, col, 8 + col * 18, 142));
        }
    }

    private void refreshResult(World world) {
        if (world.isClientSide) {
            return;
        }
        ItemStack result = ItemStack.EMPTY;
        IRecipeType<LOTRRecipeFactionShaped> type = LOTRRecipeTypes.byFaction(faction);
        Optional<LOTRRecipeFactionShaped> match =
                world.getServer().getRecipeManager().getRecipeFor(type, craftSlots, world);
        if (match.isPresent()) {
            result = match.get().assemble(craftSlots);
        }
        resultSlots.setItem(0, result);
        if (player instanceof ServerPlayerEntity) {
            ((ServerPlayerEntity) player).connection.send(
                    new net.minecraft.network.play.server.SSetSlotPacket(containerId, 0, result));
        }
    }

    @Override
    public void slotsChanged(net.minecraft.inventory.IInventory inventory) {
        access.execute((world, pos) -> refreshResult(world));
    }

    @Override
    public void removed(PlayerEntity player) {
        super.removed(player);
        access.execute((world, pos) -> clearContainer(player, world, craftSlots));
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        if (tableBlock == null) {
            return true;
        }
        return access.evaluate((world, pos) ->
                world.getBlockState(pos).getBlock() == tableBlock
                        && player.distanceToSqr(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) <= 64.0,
                true);
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int index) {
        ItemStack copy = ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            copy = stack.copy();
            if (index == 0) {
                access.execute((world, pos) -> stack.getItem().onCraftedBy(stack, world, player));
                if (!moveItemStackTo(stack, 10, 46, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, copy);
            } else if (index >= 10 && index < 46) {
                if (!moveItemStackTo(stack, 1, 10, false)
                        && !moveItemStackTo(stack, index < 37 ? 37 : 10, index < 37 ? 46 : 37, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!moveItemStackTo(stack, 10, 46, false)) {
                return ItemStack.EMPTY;
            }
            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            }
            slot.onTake(player, stack);
            if (index == 0) {
                player.drop(stack, false);
            }
        }
        return copy;
    }
}
