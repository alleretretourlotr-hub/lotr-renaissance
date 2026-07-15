package fr.alleretretour.lotr.init;

import fr.alleretretour.lotr.LOTRMod;
import fr.alleretretour.lotr.recipe.LOTRRecipeFactionShaped;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

/**
 * Types de recettes des tables de faction : un IRecipeType par faction,
 * un seul serialiseur JSON (lotr:faction_shaped).
 */
public class LOTRRecipeTypes {

    public static final DeferredRegister<IRecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, LOTRMod.MOD_ID);

    public static final RegistryObject<IRecipeSerializer<?>> FACTION_SHAPED =
            SERIALIZERS.register("faction_shaped", LOTRRecipeFactionShaped.Serializer::new);

    public static final RegistryObject<IRecipeSerializer<?>> FACTION_SHAPELESS =
            SERIALIZERS.register("faction_shapeless",
                    fr.alleretretour.lotr.recipe.LOTRRecipeFactionShapeless.Serializer::new);

    private static final Map<String, IRecipeType<LOTRRecipeFactionShaped>> TYPES = new HashMap<>();

    /** Cree (ou retrouve) le type de recette d'une faction. Thread-safe via commonSetup. */
    public static synchronized IRecipeType<LOTRRecipeFactionShaped> byFaction(String faction) {
        return TYPES.computeIfAbsent(faction, f -> {
            ResourceLocation id = new ResourceLocation(LOTRMod.MOD_ID, "crafting_" + f);
            IRecipeType<LOTRRecipeFactionShaped> type = new IRecipeType<LOTRRecipeFactionShaped>() {
                @Override
                public String toString() {
                    return id.toString();
                }
            };
            return Registry.register(Registry.RECIPE_TYPE, id, type);
        });
    }
}
