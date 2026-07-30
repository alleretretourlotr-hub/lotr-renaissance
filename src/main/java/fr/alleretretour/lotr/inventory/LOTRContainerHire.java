package fr.alleretretour.lotr.inventory;

import fr.alleretretour.lotr.entity.npc.LOTREntityNPC;
import fr.alleretretour.lotr.fac.LOTRPlayerDataProvider;
import fr.alleretretour.lotr.fac.LOTRUnitHiring;
import fr.alleretretour.lotr.hire.LOTRHireRosters;
import fr.alleretretour.lotr.init.LOTRContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.registry.Registry;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

/**
 * PORT de l'ecran d'embauche du Legacy : liste des unites synchronisee par
 * dataSlots. Le COUT est calcule par joueur avec la formule exacte du Legacy
 * (double si non-jure, remise selon le surplus d'alignement) et rafraichi
 * apres chaque embauche. Les exigences de serment sont synchronisees.
 */
public class LOTRContainerHire extends Container {

    public static final int MAX_ROWS = 9;

    private final List<LOTRHireRosters.Entry> roster;
    private final PlayerInventory playerInv;
    @Nullable
    private final LOTREntityNPC recruiter;

    private final IntReferenceHolder count = IntReferenceHolder.standalone();
    private final IntReferenceHolder purse = IntReferenceHolder.standalone();
    private final IntReferenceHolder alignment = IntReferenceHolder.standalone();
    private final IntReferenceHolder[] typeIds = new IntReferenceHolder[MAX_ROWS];
    private final IntReferenceHolder[] costs = new IntReferenceHolder[MAX_ROWS];
    private final IntReferenceHolder[] minAligns = new IntReferenceHolder[MAX_ROWS];
    private final IntReferenceHolder[] pledgeTypes = new IntReferenceHolder[MAX_ROWS];
    private final IntReferenceHolder[] pledgeOks = new IntReferenceHolder[MAX_ROWS];
    private final IntReferenceHolder[] mounted = new IntReferenceHolder[MAX_ROWS];

    /** Constructeur CLIENT (les donnees arrivent par la synchro). */
    public LOTRContainerHire(int id, PlayerInventory playerInv) {
        this(id, playerInv, Collections.emptyList(), null);
    }

    /** Constructeur SERVEUR. */
    public LOTRContainerHire(int id, PlayerInventory playerInv,
                             List<LOTRHireRosters.Entry> roster, @Nullable LOTREntityNPC recruiter) {
        super(LOTRContainers.HIRE.get(), id);
        this.playerInv = playerInv;
        this.roster = roster;
        this.recruiter = recruiter;

        for (int i = 0; i < MAX_ROWS; i++) {
            typeIds[i] = addDataSlot(IntReferenceHolder.standalone());
            costs[i] = addDataSlot(IntReferenceHolder.standalone());
            minAligns[i] = addDataSlot(IntReferenceHolder.standalone());
            pledgeTypes[i] = addDataSlot(IntReferenceHolder.standalone());
            pledgeOks[i] = addDataSlot(IntReferenceHolder.standalone());
            mounted[i] = addDataSlot(IntReferenceHolder.standalone());
            typeIds[i].set(-1);
        }
        count.set(Math.min(roster.size(), MAX_ROWS));
        addDataSlot(count);
        addDataSlot(purse);
        addDataSlot(alignment);
        refreshData();
    }

    private void refreshData() {
        if (recruiter == null) {
            return;
        }
        int total = 0;
        for (ItemStack s : playerInv.items) {
            if (s.getItem() == fr.alleretretour.lotr.init.LOTRItems.SILVER_COIN.get()) {
                total += s.getCount();
            }
        }
        purse.set(total);
        alignment.set((int) LOTRPlayerDataProvider.get(playerInv.player)
                .getAlignment(recruiter.getFaction()));
        fr.alleretretour.lotr.fac.LOTRFaction pledged =
                LOTRPlayerDataProvider.get(playerInv.player).getPledgeFaction();
        for (int i = 0; i < Math.min(roster.size(), MAX_ROWS); i++) {
            LOTRHireRosters.Entry e = roster.get(i);
            typeIds[i].set(Registry.ENTITY_TYPE.getId(e.type.get()));
            costs[i].set(LOTRUnitHiring.getCost(e, playerInv.player, recruiter.getFaction()));
            minAligns[i].set(e.minAlignment);
            pledgeTypes[i].set(e.pledgeType.ordinal());
            pledgeOks[i].set(e.pledgeType.isMet(pledged, recruiter.getFaction()) ? 1 : 0);
            mounted[i].set(e.mount != null ? 1 : 0);
        }
    }

    public int rowCount() {
        return count.get();
    }

    public int typeId(int i) {
        return typeIds[i].get();
    }

    public int cost(int i) {
        return costs[i].get();
    }

    public int minAlignment(int i) {
        return minAligns[i].get();
    }

    public LOTRHireRosters.PledgeType pledgeType(int i) {
        int ord = pledgeTypes[i].get();
        LOTRHireRosters.PledgeType[] values = LOTRHireRosters.PledgeType.values();
        return ord >= 0 && ord < values.length ? values[ord] : LOTRHireRosters.PledgeType.NONE;
    }

    /** true si l'unite est fournie avec sa monture (PORT des entrees Xxx_Horse). */
    public boolean isMounted(int i) {
        return mounted[i].get() != 0;
    }

    public boolean pledgeOk(int i) {
        return pledgeOks[i].get() != 0;
    }

    public int getPurse() {
        return purse.get();
    }

    public int getAlignment() {
        return alignment.get();
    }

    @Override
    public boolean clickMenuButton(PlayerEntity player, int idx) {
        if (recruiter != null && idx >= 0 && idx < roster.size()
                && player instanceof ServerPlayerEntity) {
            LOTRUnitHiring.tryHire(recruiter, (ServerPlayerEntity) player, roster.get(idx));
            refreshData();
            broadcastChanges();
            return true;
        }
        return false;
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return recruiter == null
                || (recruiter.isAlive() && recruiter.distanceToSqr(player) < 64.0);
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int index) {
        return ItemStack.EMPTY;
    }
}
