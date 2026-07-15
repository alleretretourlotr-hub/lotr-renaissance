package fr.alleretretour.lotr.recipe;

import com.google.gson.JsonObject;
import fr.alleretretour.lotr.init.LOTRRecipeTypes;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.ShapelessRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

/** Pendant shapeless des recettes de faction (champ "faction" en plus du vanilla). */
public class LOTRRecipeFactionShapeless extends ShapelessRecipe {

    private final String faction;

    public LOTRRecipeFactionShapeless(ShapelessRecipe base, String faction) {
        super(base.getId(), base.getGroup(), base.getResultItem(), base.getIngredients());
        this.faction = faction;
    }

    @Override
    public IRecipeType<?> getType() {
        return LOTRRecipeTypes.byFaction(faction);
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return LOTRRecipeTypes.FACTION_SHAPELESS.get();
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
            implements IRecipeSerializer<LOTRRecipeFactionShapeless> {

        @Override
        public LOTRRecipeFactionShapeless fromJson(ResourceLocation id, JsonObject json) {
            ShapelessRecipe base = IRecipeSerializer.SHAPELESS_RECIPE.fromJson(id, json);
            return new LOTRRecipeFactionShapeless(base, JSONUtils.getAsString(json, "faction"));
        }

        @Override
        public LOTRRecipeFactionShapeless fromNetwork(ResourceLocation id, PacketBuffer buf) {
            String faction = buf.readUtf(64);
            ShapelessRecipe base = IRecipeSerializer.SHAPELESS_RECIPE.fromNetwork(id, buf);
            return new LOTRRecipeFactionShapeless(base, faction);
        }

        @Override
        public void toNetwork(PacketBuffer buf, LOTRRecipeFactionShapeless recipe) {
            buf.writeUtf(recipe.faction, 64);
            IRecipeSerializer.SHAPELESS_RECIPE.toNetwork(buf, recipe);
        }
    }
}
