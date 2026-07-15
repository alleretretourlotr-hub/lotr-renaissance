package fr.alleretretour.lotr.recipe;

import com.google.gson.JsonObject;
import fr.alleretretour.lotr.init.LOTRRecipeTypes;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

/**
 * Recette shaped de table de faction. JSON identique au shaped vanilla
 * + un champ "faction" qui route la recette vers le bon type de table.
 */
public class LOTRRecipeFactionShaped extends ShapedRecipe {

    private final String faction;

    public LOTRRecipeFactionShaped(ShapedRecipe base, String faction) {
        super(base.getId(), base.getGroup(), base.getWidth(), base.getHeight(),
                base.getIngredients(), base.getResultItem());
        this.faction = faction;
    }

    public String getFaction() {
        return faction;
    }

    @Override
    public IRecipeType<?> getType() {
        return LOTRRecipeTypes.byFaction(faction);
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return LOTRRecipeTypes.FACTION_SHAPED.get();
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
            implements IRecipeSerializer<LOTRRecipeFactionShaped> {

        @Override
        public LOTRRecipeFactionShaped fromJson(ResourceLocation id, JsonObject json) {
            ShapedRecipe base = IRecipeSerializer.SHAPED_RECIPE.fromJson(id, json);
            String faction = JSONUtils.getAsString(json, "faction");
            return new LOTRRecipeFactionShaped(base, faction);
        }

        @Override
        public LOTRRecipeFactionShaped fromNetwork(ResourceLocation id, PacketBuffer buf) {
            String faction = buf.readUtf(64);
            ShapedRecipe base = IRecipeSerializer.SHAPED_RECIPE.fromNetwork(id, buf);
            return new LOTRRecipeFactionShaped(base, faction);
        }

        @Override
        public void toNetwork(PacketBuffer buf, LOTRRecipeFactionShaped recipe) {
            buf.writeUtf(recipe.faction, 64);
            IRecipeSerializer.SHAPED_RECIPE.toNetwork(buf, recipe);
        }
    }
}
