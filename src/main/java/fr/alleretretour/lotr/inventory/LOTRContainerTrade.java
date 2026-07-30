package fr.alleretretour.lotr.inventory;

import fr.alleretretour.lotr.init.LOTRContainers;
import fr.alleretretour.lotr.init.LOTRItems;
import fr.alleretretour.lotr.trade.LOTRTradeTables;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * PORT de LOTRContainerTrade (Legacy) : 9 slots d'ACHAT (haut), 9 slots de
 * VENTE (bas), une rangee d'OFFRE de vente, puis l'inventaire joueur.
 * Cliquer un slot d'achat paie en pieces ; deposer un item vendable dans
 * l'offre puis cliquer le slot de vente correspondant rapporte des pieces.
 */
public class LOTRContainerTrade extends Container {

    public final IInventory buyInv = new Inventory(9);
    public final IInventory sellInv = new Inventory(9);
    public final IInventory sellOffer = new Inventory(9);
    private final List<LOTRTradeTables.Entry> buy;
    private final List<LOTRTradeTables.Entry> sell;
    private final PlayerInventory playerInv;
    // CORRECTIF : couts et bourse synchronises vers le client (dataSlots),
    // les listes buy/sell etant vides cote client.
    private final net.minecraft.util.IntReferenceHolder[] buyCosts =
            new net.minecraft.util.IntReferenceHolder[9];
    private final net.minecraft.util.IntReferenceHolder[] sellCosts =
            new net.minecraft.util.IntReferenceHolder[9];
    private final net.minecraft.util.IntReferenceHolder purseSync =
            net.minecraft.util.IntReferenceHolder.standalone();

    public LOTRContainerTrade(int id, PlayerInventory playerInv) {
        this(id, playerInv, java.util.Collections.emptyList(), java.util.Collections.emptyList());
    }

    public LOTRContainerTrade(int id, PlayerInventory playerInv,
                              List<LOTRTradeTables.Entry> buy, List<LOTRTradeTables.Entry> sell) {
        super(LOTRContainers.TRADE.get(), id);
        this.playerInv = playerInv;
        this.buy = buy;
        this.sell = sell;

        for (int i = 0; i < 9; i++) {
            buyInv.setItem(i, i < buy.size() ? buy.get(i).stack.copy() : ItemStack.EMPTY);
            sellInv.setItem(i, i < sell.size() ? sell.get(i).stack.copy() : ItemStack.EMPTY);
            buyCosts[i] = addDataSlot(net.minecraft.util.IntReferenceHolder.standalone());
            sellCosts[i] = addDataSlot(net.minecraft.util.IntReferenceHolder.standalone());
            buyCosts[i].set(i < buy.size() ? buy.get(i).cost : 0);
            sellCosts[i].set(i < sell.size() ? sell.get(i).cost : 0);
        }
        addDataSlot(purseSync);
        purseSync.set(countCoins());
        // slots d'achat (lecture seule, clic = acheter) - y=40
        for (int i = 0; i < 9; i++) {
            final int idx = i;
            addSlot(new Slot(buyInv, i, 8 + i * 18, 40) {
                @Override
                public boolean mayPickup(PlayerEntity p) {
                    return false;
                }

                @Override
                public boolean mayPlace(ItemStack s) {
                    return false;
                }
            });
        }
        // slots de vente (affichage de ce que le marchand rachete) - y=92
        for (int i = 0; i < 9; i++) {
            addSlot(new Slot(sellInv, i, 8 + i * 18, 92) {
                @Override
                public boolean mayPickup(PlayerEntity p) {
                    return false;
                }

                @Override
                public boolean mayPlace(ItemStack s) {
                    return false;
                }
            });
        }
        // rangee d'offre : le joueur y depose ce qu'il veut vendre - y=141
        for (int i = 0; i < 9; i++) {
            addSlot(new Slot(sellOffer, i, 8 + i * 18, 141));
        }
        // inventaire joueur - y=188
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 188 + row * 18));
            }
        }
        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(playerInv, col, 8 + col * 18, 246));
        }
    }

    private int countCoins() {
        int total = 0;
        for (ItemStack s : playerInv.items) {
            if (s.getItem() == LOTRItems.SILVER_COIN.get()) {
                total += s.getCount();
            }
        }
        return total;
    }

    public int getPurse() {
        return purseSync.get();
    }

    public int buyCount() {
        return 9;
    }

    public int buyCost(int i) {
        return buyCosts[i].get();
    }

    public int sellCount() {
        return 9;
    }

    public int sellCost(int i) {
        return sellCosts[i].get();
    }

    private void removeCoins(int amount) {
        for (ItemStack s : playerInv.items) {
            if (amount <= 0) break;
            if (s.getItem() == LOTRItems.SILVER_COIN.get()) {
                int take = Math.min(amount, s.getCount());
                s.shrink(take);
                amount -= take;
            }
        }
    }

    private void giveCoins(int amount) {
        while (amount > 0) {
            int n = Math.min(amount, 64);
            if (!playerInv.add(new ItemStack(LOTRItems.SILVER_COIN.get(), n))) {
                playerInv.player.drop(new ItemStack(LOTRItems.SILVER_COIN.get(), n), false);
            }
            amount -= n;
        }
    }

    @Override
    public ItemStack clicked(int slotId, int dragType, ClickType type, PlayerEntity player) {
        // clic sur un slot d'ACHAT (0..8) : acheter
        if (slotId >= 0 && slotId < 9 && type == ClickType.PICKUP) {
            int idx = slotId;
            if (idx < buy.size()) {
                LOTRTradeTables.Entry e = buy.get(idx);
                if (countCoins() >= e.cost) {
                    ItemStack result = e.stack.copy();
                    if (player.inventory.add(result)) {
                        removeCoins(e.cost);
                        purseSync.set(countCoins());
                        broadcastChanges();
                    }
                }
            }
            return ItemStack.EMPTY;
        }
        // clic sur un slot de VENTE (9..17) : vendre l'item correspondant depuis l'offre
        if (slotId >= 9 && slotId < 18 && type == ClickType.PICKUP) {
            int idx = slotId - 9;
            if (idx < sell.size()) {
                LOTRTradeTables.Entry e = sell.get(idx);
                // chercher l'item dans la rangee d'offre
                for (int i = 0; i < sellOffer.getContainerSize(); i++) {
                    ItemStack offered = sellOffer.getItem(i);
                    if (ItemStack.isSame(offered, e.stack) && offered.getCount() >= e.stack.getCount()) {
                        offered.shrink(e.stack.getCount());
                        giveCoins(e.cost);
                        purseSync.set(countCoins());
                        broadcastChanges();
                        break;
                    }
                }
            }
            return ItemStack.EMPTY;
        }
        return super.clicked(slotId, dragType, type, player);
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return true;
    }

    @Override
    public void removed(PlayerEntity player) {
        super.removed(player);
        // rendre les items deposes dans l'offre
        if (!player.level.isClientSide) {
            for (int i = 0; i < sellOffer.getContainerSize(); i++) {
                ItemStack s = sellOffer.getItem(i);
                if (!s.isEmpty()) {
                    player.drop(s, false);
                }
            }
        }
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int index) {
        Slot slot = slots.get(index);
        if (slot == null || !slot.hasItem()) {
            return ItemStack.EMPTY;
        }
        ItemStack stack = slot.getItem();
        ItemStack copy = stack.copy();
        int invStart = 27;
        int invEnd = slots.size();
        if (index >= invStart) {
            // depuis l'inventaire -> vers la rangee d'offre
            if (!moveItemStackTo(stack, 18, 27, false)) {
                return ItemStack.EMPTY;
            }
        } else if (index >= 18 && index < 27) {
            // depuis l'offre -> vers l'inventaire
            if (!moveItemStackTo(stack, invStart, invEnd, false)) {
                return ItemStack.EMPTY;
            }
        }
        if (stack.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }
        return copy;
    }
}
