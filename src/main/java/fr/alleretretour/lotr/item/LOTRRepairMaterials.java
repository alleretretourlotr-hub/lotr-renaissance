package fr.alleretretour.lotr.item;

import fr.alleretretour.lotr.init.LOTRItems;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

/**
 * Transcription de LOTRMaterial.setCraftingItems() du mod original (1.7.10).
 * Cable les objets de reparation (enclume) de chaque materiau.
 * A appeler UNE fois depuis commonSetup (les Ingredient sont resolus paresseusement,
 * donc aucun probleme d'ordre d'enregistrement).
 *
 * TODO Phase 2 : HARAD_NOMAD -> roseaux seches (bloc, pas encore porte). Fer en attendant.
 */
public final class LOTRRepairMaterials {

    private LOTRRepairMaterials() {
    }

    public static void wireUp() {
        LOTRMaterial.BRONZE.setRepairIngredient(() -> Ingredient.of(LOTRItems.BRONZE.get()));
        LOTRMaterial.MITHRIL.setRepairIngredients(
                () -> Ingredient.of(LOTRItems.MITHRIL.get()),
                () -> Ingredient.of(LOTRItems.MITHRIL_MAIL.get()));
        LOTRMaterial.FUR.setRepairIngredient(() -> Ingredient.of(LOTRItems.FUR.get()));
        LOTRMaterial.GEMSBOK.setRepairIngredient(() -> Ingredient.of(LOTRItems.GEMSBOK_HIDE.get()));
        LOTRMaterial.GAMBESON.setRepairIngredient(() -> Ingredient.of(Blocks.WHITE_WOOL));
        LOTRMaterial.JACKET.setRepairIngredient(() -> Ingredient.of(Items.LEATHER));
        LOTRMaterial.GONDOR.setRepairIngredient(() -> Ingredient.of(Items.IRON_INGOT));
        LOTRMaterial.DOL_AMROTH.setRepairIngredient(() -> Ingredient.of(Items.IRON_INGOT));
        LOTRMaterial.ROHAN.setRepairIngredient(() -> Ingredient.of(Items.IRON_INGOT));
        LOTRMaterial.ROHAN_MARSHAL.setRepairIngredient(() -> Ingredient.of(Items.IRON_INGOT));
        LOTRMaterial.RANGER.setRepairIngredients(
                () -> Ingredient.of(Items.IRON_INGOT),
                () -> Ingredient.of(Items.LEATHER));
        LOTRMaterial.RANGER_ITHILIEN.setRepairIngredients(
                () -> Ingredient.of(Items.IRON_INGOT),
                () -> Ingredient.of(Items.LEATHER));
        LOTRMaterial.DUNLENDING.setRepairIngredient(() -> Ingredient.of(Items.IRON_INGOT));
        LOTRMaterial.NEAR_HARAD.setRepairIngredient(() -> Ingredient.of(LOTRItems.BRONZE.get()));
        LOTRMaterial.HARNEDOR.setRepairIngredient(() -> Ingredient.of(LOTRItems.BRONZE.get()));
        LOTRMaterial.UMBAR.setRepairIngredient(() -> Ingredient.of(Items.IRON_INGOT));
        LOTRMaterial.CORSAIR.setRepairIngredients(
                () -> Ingredient.of(Items.IRON_INGOT),
                () -> Ingredient.of(LOTRItems.BRONZE.get()));
        LOTRMaterial.GULF_HARAD.setRepairIngredient(() -> Ingredient.of(LOTRItems.BRONZE.get()));
        LOTRMaterial.HARAD_NOMAD.setRepairIngredient(() -> Ingredient.of(Items.IRON_INGOT)); // TODO Phase 2 : dried reeds
        LOTRMaterial.ANCIENT_HARAD.setRepairIngredient(() -> Ingredient.of(Items.IRON_INGOT));
        LOTRMaterial.MOREDAIN.setRepairIngredients(
                () -> Ingredient.of(LOTRItems.RHINO_HORN.get()),
                () -> Ingredient.of(LOTRItems.GEMSBOK_HIDE.get()));
        LOTRMaterial.MOREDAIN_SPEAR.setRepairIngredient(() -> Ingredient.of(LOTRItems.GEMSBOK_HORN.get()));
        LOTRMaterial.MOREDAIN_LION_ARMOR.setRepairIngredient(() -> Ingredient.of(LOTRItems.LION_FUR.get()));
        LOTRMaterial.MOREDAIN_BRONZE.setRepairIngredient(() -> Ingredient.of(LOTRItems.BRONZE.get()));
        LOTRMaterial.TAUREDAIN.setRepairIngredients(
                () -> Ingredient.of(LOTRItems.OBSIDIAN_SHARD.get()),
                () -> Ingredient.of(LOTRItems.BRONZE.get()));
        LOTRMaterial.TAUREDAIN_GOLD.setRepairIngredient(() -> Ingredient.of(Items.GOLD_INGOT));
        LOTRMaterial.BARROW.setRepairIngredient(() -> Ingredient.of(Items.IRON_INGOT));
        LOTRMaterial.DALE.setRepairIngredient(() -> Ingredient.of(Items.IRON_INGOT));
        LOTRMaterial.DORWINION.setRepairIngredient(() -> Ingredient.of(Items.IRON_INGOT));
        LOTRMaterial.LOSSARNACH.setRepairIngredient(() -> Ingredient.of(Items.IRON_INGOT));
        LOTRMaterial.PELARGIR.setRepairIngredient(() -> Ingredient.of(Items.IRON_INGOT));
        LOTRMaterial.PINNATH_GELIN.setRepairIngredient(() -> Ingredient.of(Items.IRON_INGOT));
        LOTRMaterial.BLACKROOT.setRepairIngredient(() -> Ingredient.of(Items.IRON_INGOT));
        LOTRMaterial.LAMEDON.setRepairIngredient(() -> Ingredient.of(Items.IRON_INGOT));
        LOTRMaterial.ARNOR.setRepairIngredient(() -> Ingredient.of(Items.IRON_INGOT));
        LOTRMaterial.RHUN.setRepairIngredient(() -> Ingredient.of(Items.IRON_INGOT));
        LOTRMaterial.RHUN_GOLD.setRepairIngredient(() -> Ingredient.of(LOTRItems.GILDED_IRON.get()));
        LOTRMaterial.BLACK_NUMENOREAN.setRepairIngredient(() -> Ingredient.of(Items.IRON_INGOT));
        LOTRMaterial.GALADHRIM.setRepairIngredient(() -> Ingredient.of(LOTRItems.ELF_STEEL.get()));
        LOTRMaterial.GALVORN.setRepairIngredient(() -> Ingredient.of(LOTRItems.GALVORN.get()));
        LOTRMaterial.WOOD_ELVEN_SCOUT.setRepairIngredients(
                () -> Ingredient.of(LOTRItems.ELF_STEEL.get()),
                () -> Ingredient.of(Items.LEATHER));
        LOTRMaterial.WOOD_ELVEN.setRepairIngredient(() -> Ingredient.of(LOTRItems.ELF_STEEL.get()));
        LOTRMaterial.HIGH_ELVEN.setRepairIngredient(() -> Ingredient.of(LOTRItems.ELF_STEEL.get()));
        LOTRMaterial.GONDOLIN.setRepairIngredient(() -> Ingredient.of(LOTRItems.ELF_STEEL.get()));
        LOTRMaterial.HITHLAIN.setRepairIngredient(() -> Ingredient.of(LOTRItems.HITHLAIN.get()));
        LOTRMaterial.DORWINION_ELF.setRepairIngredient(() -> Ingredient.of(LOTRItems.ELF_STEEL.get()));
        LOTRMaterial.RIVENDELL.setRepairIngredient(() -> Ingredient.of(LOTRItems.ELF_STEEL.get()));
        LOTRMaterial.DWARVEN.setRepairIngredient(() -> Ingredient.of(LOTRItems.DWARF_STEEL.get()));
        LOTRMaterial.BLUE_DWARVEN.setRepairIngredient(() -> Ingredient.of(LOTRItems.BLUE_DWARF_STEEL.get()));
        LOTRMaterial.BLADORTHIN.setRepairIngredient(() -> Ingredient.of(LOTRItems.DWARF_STEEL.get()));
        LOTRMaterial.MORDOR.setRepairIngredient(() -> Ingredient.of(LOTRItems.ORC_STEEL.get()));
        LOTRMaterial.URUK.setRepairIngredient(() -> Ingredient.of(LOTRItems.URUK_STEEL.get()));
        LOTRMaterial.MORGUL.setRepairIngredient(() -> Ingredient.of(LOTRItems.MORGUL_STEEL.get()));
        LOTRMaterial.GUNDABAD_URUK.setRepairIngredient(() -> Ingredient.of(LOTRItems.URUK_STEEL.get()));
        LOTRMaterial.ANGMAR.setRepairIngredient(() -> Ingredient.of(LOTRItems.ORC_STEEL.get()));
        LOTRMaterial.DOL_GULDUR.setRepairIngredient(() -> Ingredient.of(LOTRItems.ORC_STEEL.get()));
        LOTRMaterial.BLACK_URUK.setRepairIngredient(() -> Ingredient.of(LOTRItems.BLACK_URUK_STEEL.get()));
        LOTRMaterial.UTUMNO.setRepairIngredient(() -> Ingredient.of(LOTRItems.ORC_STEEL.get()));
        LOTRMaterial.HALF_TROLL.setRepairIngredients(
                () -> Ingredient.of(Items.FLINT),
                () -> Ingredient.of(LOTRItems.GEMSBOK_HIDE.get()));
    }
}
