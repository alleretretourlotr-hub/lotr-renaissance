package fr.alleretretour.lotr.inventory;

import fr.alleretretour.lotr.init.LOTRContainers;
import fr.alleretretour.lotr.item.LOTRItemVessel;
import fr.alleretretour.lotr.tileentity.LOTRTileEntityBarrel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;

/**
 * Container du tonneau, aux positions exactes du Legacy (GUI 210x221) :
 * grille 3x3 en (14,34) - lignes 1-2 ingredients, ligne 3 seaux d'eau -
 * slot de service (chope vide -> remplie) en (108,52).
 */
public class LOTRContainerBarrel extends Container {

    private final IInventory barrel;
    private final IIntArray data;

    public LOTRContainerBarrel(int id, PlayerInventory playerInv) {
        this(id, playerInv, new Inventory(LOTRTileEntityBarrel.NUM_SLOTS), new IntArray(4));
    }

    public LOTRContainerBarrel(int id, PlayerInventory playerInv, IInventory barrel, IIntArray data) {
        super(LOTRContainers.BARREL.get(), id);
        this.barrel = barrel;
        this.data = data;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                final boolean water = row == 2;
                addSlot(new Slot(barrel, col + row * 3, 14 + col * 18, 34 + row * 18) {
                    @Override
                    public boolean mayPlace(ItemStack stack) {
                        return water
                                ? stack.getItem() == Items.WATER_BUCKET
                                : !(stack.getItem() instanceof LOTRItemVessel);
                    }
                });
            }
        }
        // slot de service : recipient vide -> rempli automatiquement par le tonneau
        addSlot(new Slot(barrel, LOTRTileEntityBarrel.SLOT_VESSEL, 108, 52) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() instanceof LOTRItemVessel;
            }

            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(playerInv, col + row * 9 + 9, 25 + col * 18, 139 + row * 18));
            }
        }
        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(playerInv, col, 25 + col * 18, 197));
        }
        addDataSlots(data);
    }

    public int getBrewingTime() {
        return data.get(0);
    }

    public int getServings() {
        return data.get(1);
    }

    public int getStrength() {
        return data.get(2);
    }

    public int getDrinkItemId() {
        return data.get(3);
    }

    @Override
    public boolean clickMenuButton(PlayerEntity player, int id) {
        if (id == 0 && barrel instanceof LOTRTileEntityBarrel) {
            ((LOTRTileEntityBarrel) barrel).requestBrewing();
            return true;
        }
        return false;
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return barrel.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int index) {
        ItemStack copy = ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            copy = stack.copy();
            if (index < LOTRTileEntityBarrel.NUM_SLOTS) {
                if (!moveItemStackTo(stack, LOTRTileEntityBarrel.NUM_SLOTS, slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (stack.getItem() == Items.WATER_BUCKET) {
                if (!moveItemStackTo(stack, 6, 9, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (stack.getItem() instanceof LOTRItemVessel) {
                if (!moveItemStackTo(stack, 9, 10, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!moveItemStackTo(stack, 0, 6, false)) {
                return ItemStack.EMPTY;
            }
            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            }
            slot.onTake(player, stack);
        }
        return copy;
    }
}
