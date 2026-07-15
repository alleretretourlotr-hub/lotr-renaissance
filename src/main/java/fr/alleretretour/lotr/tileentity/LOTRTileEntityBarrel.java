package fr.alleretretour.lotr.tileentity;

import fr.alleretretour.lotr.init.LOTRItemsDrinks;
import fr.alleretretour.lotr.init.LOTRTileEntities;
import fr.alleretretour.lotr.item.LOTRItemMug;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

/**
 * Port de lotr.common.tileentity.LOTRTileEntityBarrel.
 * Slots : 0-5 ingredients, 6-8 seaux d'eau.
 * 6 ingredients + 3 seaux -> 16 portions ; la boisson continue de fermenter :
 * light -> moderate -> strong -> potent (fidele a la mecanique originale).
 * Service : clic droit sur le tonneau avec un recipient vide.
 */
public class LOTRTileEntityBarrel extends LockableLootTileEntity implements ITickableTileEntity {

    public static final int BREW_TICKS = 2400;             // 2 min de brassage
    public static final int FERMENT_STEP_TICKS = 20 * 60 * 5; // +1 puissance / 5 min
    public static final int CAPACITY = 16;
    public static final int NUM_SLOTS = 10;
    public static final int SLOT_VESSEL = 9;

    /** Sync GUI : 0=progression, 1=portions, 2=puissance. */
    public final net.minecraft.util.IIntArray dataAccess = new net.minecraft.util.IIntArray() {
        @Override
        public int get(int i) {
            switch (i) {
                case 0: return brewingTime;
                case 1: return servings;
                case 2: return strength;
                case 3: return brewedDrink.isEmpty() ? 0 : net.minecraft.item.Item.getId(
                        net.minecraftforge.registries.ForgeRegistries.ITEMS.getValue(
                                new net.minecraft.util.ResourceLocation(brewedDrink)));
                default: return 0;
            }
        }

        @Override
        public void set(int i, int v) {
            switch (i) {
                case 0: brewingTime = v; break;
                case 1: servings = v; break;
                case 2: strength = v; break;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };

    private NonNullList<ItemStack> items = NonNullList.withSize(NUM_SLOTS, ItemStack.EMPTY);
    private int brewingTime;
    private String brewedDrink = "";
    private int servings;
    private int strength = 1; // demarre en "light", comme l'original
    private int fermentTime;
    private boolean brewRequested;

    /** Declenche par le bouton du GUI : demarre le brassage si la cuve est prete. */
    public void requestBrewing() {
        brewRequested = true;
    }

    /** Recettes chargees par LOTRBrewingRecipesData (genere du Legacy). */
    public static final List<BrewEntry> RECIPES = new ArrayList<>();

    public static class BrewEntry {
        public final String result;
        public final String[] ingredients; // ids "mod:item" ou tags "#mod:tag"

        public BrewEntry(String result, String... ingredients) {
            this.result = result;
            this.ingredients = ingredients;
        }
    }

    public LOTRTileEntityBarrel() {
        super(LOTRTileEntities.BARREL.get());
    }

    @Override
    public void tick() {
        if (level == null || level.isClientSide) {
            return;
        }
        if (hasDrink()) {
            fermentTime++;
            if (strength < 4 && fermentTime >= FERMENT_STEP_TICKS) {
                fermentTime = 0;
                strength++;
                setChanged();
            }
            return;
        }
        String match = matchRecipe();
        boolean canBrew = match != null && waterBuckets() >= 3;
        if (canBrew && brewingTime == 0 && !brewRequested) {
            // en attente du bouton "Brasser"
        } else if (canBrew) {
            brewRequested = false;
            brewingTime++;
            if (brewingTime >= BREW_TICKS) {
                for (int i = 0; i < 6; i++) {
                    items.get(i).shrink(1);
                }
                for (int i = 6; i < 9; i++) {
                    items.set(i, new ItemStack(Items.BUCKET));
                }
                brewedDrink = match;
                servings = CAPACITY;
                strength = 1;
                fermentTime = 0;
                brewingTime = 0;
                setChanged();
            }
        } else {
            brewRequested = false;
            if (brewingTime != 0) {
                brewingTime = 0;
                setChanged();
            }
        }
        if (!level.isClientSide) {
            fillVesselSlot();
        }
    }

    private int waterBuckets() {
        int n = 0;
        for (int i = 6; i < 9; i++) {
            if (items.get(i).getItem() == Items.WATER_BUCKET) {
                n++;
            }
        }
        return n;
    }

    private String matchRecipe() {
        List<Item> present = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            if (items.get(i).isEmpty()) {
                return null;
            }
            present.add(items.get(i).getItem());
        }
        outer:
        for (BrewEntry e : RECIPES) {
            List<Item> pool = new ArrayList<>(present);
            for (String ing : e.ingredients) {
                Item found = null;
                for (Item it : pool) {
                    if (matches(it, ing)) {
                        found = it;
                        break;
                    }
                }
                if (found == null) {
                    continue outer;
                }
                pool.remove(found);
            }
            return e.result;
        }
        return null;
    }

    private boolean matches(Item item, String spec) {
        if (spec.startsWith("#")) {
            net.minecraft.tags.ITag<Item> tag = net.minecraft.tags.ItemTags.getAllTags()
                    .getTag(new ResourceLocation(spec.substring(1)));
            return tag != null && tag.contains(item);
        }
        return ForgeRegistries.ITEMS.getKey(item).toString().equals(spec);
    }

    /** Remplit automatiquement un recipient vide pose dans le slot de service. */
    private void fillVesselSlot() {
        ItemStack v = items.get(SLOT_VESSEL);
        if (hasDrink() && !v.isEmpty() && v.getCount() == 1
                && v.getItem() instanceof fr.alleretretour.lotr.item.LOTRItemVessel) {
            ItemStack served = serve(v);
            if (!served.isEmpty()) {
                items.set(SLOT_VESSEL, served);
                setChanged();
            }
        }
    }

    public boolean hasDrink() {
        return servings > 0 && !brewedDrink.isEmpty();
    }

    public int getBrewProgress() {
        return brewingTime;
    }

    public int getServings() {
        return servings;
    }

    public int getStrength() {
        return strength;
    }

    public String getBrewedDrink() {
        return brewedDrink;
    }

    /** Sert une portion dans le recipient donne ; retourne la chope pleine ou EMPTY. */
    public ItemStack serve(ItemStack vessel) {
        if (!hasDrink()) {
            return ItemStack.EMPTY;
        }
        Item drink = ForgeRegistries.ITEMS.getValue(new ResourceLocation(brewedDrink));
        if (drink == null) {
            return ItemStack.EMPTY;
        }
        ItemStack filled = new ItemStack(drink);
        LOTRItemMug.setStrength(filled, strength);
        if (vessel.getItem() instanceof fr.alleretretour.lotr.item.LOTRItemVessel) {
            fr.alleretretour.lotr.item.LOTRVessel v = vesselOf(vessel.getItem());
            LOTRItemMug.setVessel(filled, v);
        }
        servings--;
        if (servings <= 0) {
            brewedDrink = "";
            strength = 1;
            fermentTime = 0;
        }
        setChanged();
        return filled;
    }

    private fr.alleretretour.lotr.item.LOTRVessel vesselOf(Item item) {
        ResourceLocation id = ForgeRegistries.ITEMS.getKey(item);
        String p = id.getPath();
        if (p.contains("ceramic")) return fr.alleretretour.lotr.item.LOTRVessel.MUG_CLAY;
        if (p.contains("goblet_gold")) return fr.alleretretour.lotr.item.LOTRVessel.GOBLET_GOLD;
        if (p.contains("goblet_silver")) return fr.alleretretour.lotr.item.LOTRVessel.GOBLET_SILVER;
        if (p.contains("goblet_copper")) return fr.alleretretour.lotr.item.LOTRVessel.GOBLET_COPPER;
        if (p.contains("goblet_wood")) return fr.alleretretour.lotr.item.LOTRVessel.GOBLET_WOOD;
        if (p.contains("skull")) return fr.alleretretour.lotr.item.LOTRVessel.SKULL;
        if (p.contains("wine_glass")) return fr.alleretretour.lotr.item.LOTRVessel.GLASS;
        if (p.contains("waterskin")) return fr.alleretretour.lotr.item.LOTRVessel.SKIN;
        if (p.contains("horn_gold")) return fr.alleretretour.lotr.item.LOTRVessel.HORN_GOLD;
        if (p.contains("horn")) return fr.alleretretour.lotr.item.LOTRVessel.HORN;
        return fr.alleretretour.lotr.item.LOTRVessel.MUG;
    }

    // --- inventaire / NBT ---

    @Override
    public int getContainerSize() {
        return 9;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> list) {
        this.items = list;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("block.lotr.barrel");
    }

    @Override
    protected net.minecraft.inventory.container.Container createMenu(int id, net.minecraft.entity.player.PlayerInventory inv) {
        return new fr.alleretretour.lotr.inventory.LOTRContainerBarrel(id, inv, this, dataAccess);
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        super.save(nbt);
        net.minecraft.inventory.ItemStackHelper.saveAllItems(nbt, items);
        nbt.putInt("BrewTime", brewingTime);
        nbt.putString("Drink", brewedDrink);
        nbt.putInt("Servings", servings);
        nbt.putInt("Strength", strength);
        nbt.putInt("Ferment", fermentTime);
        return nbt;
    }

    @Override
    public void load(net.minecraft.block.BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
        items = NonNullList.withSize(NUM_SLOTS, ItemStack.EMPTY);
        net.minecraft.inventory.ItemStackHelper.loadAllItems(nbt, items);
        brewingTime = nbt.getInt("BrewTime");
        brewedDrink = nbt.getString("Drink");
        servings = nbt.getInt("Servings");
        strength = Math.max(1, nbt.getInt("Strength"));
        fermentTime = nbt.getInt("Ferment");
    }
}
