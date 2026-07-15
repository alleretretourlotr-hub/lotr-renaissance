package fr.alleretretour.lotr.item;

import fr.alleretretour.lotr.LOTRMod;
import fr.alleretretour.lotr.client.model.LOTRArmorModels;
import fr.alleretretour.lotr.client.model.LOTRAnimatedArmorModel;
import fr.alleretretour.lotr.init.LOTRCreativeTabs;
import fr.alleretretour.lotr.init.LOTRItems;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Locale;

/**
 * Port de lotr.common.item.LOTRItemArmor - convention extraName de l'original.
 * Texture portee : <materiau>_<suffixe>.png, suffixe = extraName snake_case,
 * sinon "2" (jambieres) ou "1" (le reste).
 * Les modeles animes sont mis a jour ici, car la couche d'armure 1.16.5
 * n'appelle jamais setupAnim sur les modeles custom.
 */
public class LOTRItemArmor extends ArmorItem {

    private final String materialName;
    private final String extraName;

    public LOTRItemArmor(LOTRMaterial material, EquipmentSlotType slot) {
        this(material, slot, "");
    }

    public LOTRItemArmor(LOTRMaterial material, EquipmentSlotType slot, String extraName) {
        super(material.toArmorMaterial(), slot,
                new Properties().tab(LOTRCreativeTabs.TAB_COMBAT));
        this.materialName = material.getName();
        this.extraName = toSnakeCase(extraName);
    }

    /** wingedHelmet -> winged_helmet ; dolAmrothLegs -> dol_amroth_legs */
    private static String toSnakeCase(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        return s.replaceAll("(?<=[a-z0-9])(?=[A-Z])", "_").toLowerCase(Locale.ROOT);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        String suffix;
        if (!extraName.isEmpty()) {
            suffix = extraName;
        } else {
            suffix = slot == EquipmentSlotType.LEGS ? "2" : "1";
        }
        return LOTRMod.MOD_ID + ":textures/armor/" + materialName + "_" + suffix + ".png";
    }

    @Nullable
    @Override
    @OnlyIn(Dist.CLIENT)
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlotType slot, A defaultModel) {
        BipedModel<LivingEntity> custom = LOTRArmorModels.get(stack.getItem());
        if (custom instanceof LOTRAnimatedArmorModel) {
            ((LOTRAnimatedArmorModel) custom).animate(entity);
        }
        @SuppressWarnings("unchecked")
        A model = (A) custom;
        return model; // null -> modele vanilla par defaut
    }
}
