package fr.alleretretour.lotr.item;

import net.minecraft.item.HorseArmorItem;
import net.minecraft.util.ResourceLocation;

/**
 * PORT de lotr.common.item.LOTRItemMountArmor : armure de monture.
 * damageReduceAmount = protection du plastron + des jambieres du materiau
 * (formule exacte du Legacy), texture armor/mount/<monture>_<materiau>.png.
 * Herite de HorseArmorItem : les chevaux (et zebres) l'acceptent nativement ;
 * les autres montures passent par LOTRMountArmored.
 */
public class LOTRItemMountArmor extends HorseArmorItem {

    /** PORT de LOTRItemMountArmor.Mount. */
    public enum Mount {
        HORSE, BOAR, WARG, RHINO, ELK, CAMEL, GIRAFFE
    }

    private final Mount mountType;
    private final ResourceLocation armorTexture;

    public LOTRItemMountArmor(Mount mount, int protection, String textureName,
                              Properties properties) {
        super(protection, new ResourceLocation("lotr", "textures/armor/mount/"
                + textureName + ".png"), properties);
        this.mountType = mount;
        this.armorTexture = new ResourceLocation("lotr", "textures/armor/mount/"
                + textureName + ".png");
    }

    public Mount getMountType() {
        return mountType;
    }

    public ResourceLocation getArmorTexture() {
        return armorTexture;
    }

    public boolean fits(Mount mount) {
        return mountType == mount;
    }
}
