package fr.alleretretour.lotr.item;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Port fidele de lotr.common.item.LOTRMaterial (1.7.10).
 * Chaque materiau expose un IItemTier (outils/armes) et un IArmorMaterial (armures).
 *
 * Valeurs transcrites 1:1 depuis le mod original :
 * uses, damage, protection (facteur 0-1), harvestLevel, speed, enchantability, manFlesh.
 *
 * protection par piece = round(base[piece] * facteur * 25)
 * avec base = casque 0.14, plastron 0.4, jambieres 0.32, bottes 0.14 (comme l'original).
 *
 * Les objets de reparation (repair items) seront cables en fin de Phase 1,
 * quand bronze / mithril / aciers de faction existeront. En attendant : fer.
 */
public class LOTRMaterial {

    private static final float[] PROTECTION_BASE = {0.14f, 0.4f, 0.32f, 0.14f}; // casque, plastron, jambieres, bottes
    private static final float MAX_PROTECTION = 25.0f;
    /** Durabilite armure par piece = ARMOR_DURABILITY[slot] * uses / DURABILITY_DIVISOR (ajustable en test). */
    private static final int[] ARMOR_DURABILITY = {13, 15, 16, 11}; // bottes, jambieres, plastron, casque (ordre 1.16.5)
    private static final int DURABILITY_DIVISOR = 20;

    public static final List<LOTRMaterial> ALL_MATERIALS = new ArrayList<>();

    public static final LOTRMaterial ANCIENT_HARAD = new LOTRMaterial("ancient_harad").uses(450).damage(2.5f).protection(0.6f).harvestLevel(2).speed(6.0f).enchantability(10);
    public static final LOTRMaterial ANGMAR = new LOTRMaterial("angmar").uses(350).damage(2.5f).protection(0.6f).harvestLevel(2).speed(6.0f).enchantability(8).manFlesh();
    public static final LOTRMaterial ARNOR = new LOTRMaterial("arnor").uses(450).damage(2.5f).protection(0.6f).harvestLevel(2).speed(6.0f).enchantability(10);
    public static final LOTRMaterial BARROW = new LOTRMaterial("barrow").uses(600).damage(3.0f).protection(0.6f).harvestLevel(2).speed(8.0f).enchantability(10);
    public static final LOTRMaterial BLACKROOT = new LOTRMaterial("blackroot").uses(400).damage(2.0f).protection(0.6f).harvestLevel(2).speed(6.0f).enchantability(10);
    public static final LOTRMaterial BLACK_NUMENOREAN = new LOTRMaterial("black_numenorean").uses(450).damage(2.5f).protection(0.6f).harvestLevel(2).speed(6.0f).enchantability(10);
    public static final LOTRMaterial BLACK_URUK = new LOTRMaterial("black_uruk").uses(550).damage(3.0f).protection(0.7f).harvestLevel(2).speed(6.0f).enchantability(6).manFlesh();
    public static final LOTRMaterial BLADORTHIN = new LOTRMaterial("bladorthin").uses(600).damage(3.0f).protection(0.6f).harvestLevel(2).speed(7.0f).enchantability(10);
    public static final LOTRMaterial BLUE_DWARVEN = new LOTRMaterial("blue_dwarven").uses(650).damage(3.0f).protection(0.7f).harvestLevel(3).speed(7.0f).enchantability(12);
    public static final LOTRMaterial BONE = new LOTRMaterial("bone").uses(150).damage(0.0f).protection(0.3f).harvestLevel(0).speed(0.0f).enchantability(10);
    public static final LOTRMaterial BRONZE = new LOTRMaterial("bronze").uses(230).damage(1.5f).protection(0.5f).harvestLevel(2).speed(5.0f).enchantability(10);
    public static final LOTRMaterial CORSAIR = new LOTRMaterial("corsair").uses(300).damage(2.5f).protection(0.5f).harvestLevel(2).speed(6.0f).enchantability(10);
    public static final LOTRMaterial COSMETIC = new LOTRMaterial("cosmetic").undamageable().uses(0).damage(0.0f).protection(0.0f).enchantability(0);
    public static final LOTRMaterial DALE = new LOTRMaterial("dale").uses(300).damage(2.5f).protection(0.6f).harvestLevel(2).speed(6.0f).enchantability(10);
    public static final LOTRMaterial DOL_AMROTH = new LOTRMaterial("dol_amroth").uses(500).damage(3.0f).protection(0.6f).harvestLevel(2).speed(6.0f).enchantability(10);
    public static final LOTRMaterial DOL_GULDUR = new LOTRMaterial("dol_guldur").uses(350).damage(2.5f).protection(0.6f).harvestLevel(2).speed(6.0f).enchantability(10).manFlesh();
    public static final LOTRMaterial DORWINION = new LOTRMaterial("dorwinion").uses(400).damage(2.5f).protection(0.5f).harvestLevel(2).speed(6.0f).enchantability(10);
    public static final LOTRMaterial DORWINION_ELF = new LOTRMaterial("dorwinion_elf").uses(500).damage(3.0f).protection(0.6f).harvestLevel(2).speed(7.0f).enchantability(15);
    public static final LOTRMaterial DUNLENDING = new LOTRMaterial("dunlending").uses(250).damage(2.0f).protection(0.5f).harvestLevel(2).speed(6.0f).enchantability(8);
    public static final LOTRMaterial DWARVEN = new LOTRMaterial("dwarven").uses(700).damage(3.0f).protection(0.7f).harvestLevel(3).speed(7.0f).enchantability(10);
    public static final LOTRMaterial FUR = new LOTRMaterial("fur").uses(180).damage(0.0f).protection(0.4f).harvestLevel(0).speed(0.0f).enchantability(8);
    public static final LOTRMaterial GALADHRIM = new LOTRMaterial("galadhrim").uses(600).damage(3.0f).protection(0.6f).harvestLevel(2).speed(7.0f).enchantability(15);
    public static final LOTRMaterial GALVORN = new LOTRMaterial("galvorn").uses(600).damage(3.0f).protection(0.6f).harvestLevel(2).speed(7.0f).enchantability(15);
    public static final LOTRMaterial GAMBESON = new LOTRMaterial("gambeson").uses(200).damage(0.0f).protection(0.4f).harvestLevel(0).speed(0.0f).enchantability(10);
    public static final LOTRMaterial GEMSBOK = new LOTRMaterial("gemsbok").uses(180).damage(0.0f).protection(0.4f).harvestLevel(0).speed(0.0f).enchantability(10);
    public static final LOTRMaterial GONDOLIN = new LOTRMaterial("gondolin").uses(1500).damage(5.0f).protection(0.7f).harvestLevel(2).speed(8.0f).enchantability(15);
    public static final LOTRMaterial GONDOR = new LOTRMaterial("gondor").uses(450).damage(2.5f).protection(0.6f).harvestLevel(2).speed(6.0f).enchantability(10);
    public static final LOTRMaterial GULF_HARAD = new LOTRMaterial("gulf_harad").uses(350).damage(2.5f).protection(0.5f).harvestLevel(2).speed(6.0f).enchantability(10);
    public static final LOTRMaterial GUNDABAD_URUK = new LOTRMaterial("gundabad_uruk").uses(500).damage(3.0f).protection(0.7f).harvestLevel(2).speed(6.0f).enchantability(5).manFlesh();
    public static final LOTRMaterial HALF_TROLL = new LOTRMaterial("half_troll").uses(300).damage(2.5f).protection(0.5f).harvestLevel(1).speed(5.0f).enchantability(5).manFlesh();
    public static final LOTRMaterial HARAD_NOMAD = new LOTRMaterial("harad_nomad").uses(200).damage(0.0f).protection(0.4f).harvestLevel(0).speed(0.0f).enchantability(8);
    public static final LOTRMaterial HARAD_ROBES = new LOTRMaterial("harad_robes").undamageable().uses(0).damage(0.0f).protection(0.0f).enchantability(0);
    public static final LOTRMaterial HARNEDOR = new LOTRMaterial("harnedor").uses(250).damage(2.0f).protection(0.5f).harvestLevel(2).speed(6.0f).enchantability(8);
    public static final LOTRMaterial HIGH_ELVEN = new LOTRMaterial("high_elven").uses(700).damage(3.0f).protection(0.6f).harvestLevel(2).speed(8.0f).enchantability(15);
    public static final LOTRMaterial HITHLAIN = new LOTRMaterial("hithlain").uses(300).damage(0.0f).protection(0.3f).harvestLevel(0).speed(0.0f).enchantability(15);
    public static final LOTRMaterial JACKET = new LOTRMaterial("jacket").uses(150).damage(0.0f).protection(0.4f).harvestLevel(0).speed(0.0f).enchantability(10);
    public static final LOTRMaterial KAFTAN = new LOTRMaterial("kaftan").undamageable().uses(0).damage(0.0f).protection(0.0f).enchantability(0);
    public static final LOTRMaterial LAMEDON = new LOTRMaterial("lamedon").uses(300).damage(2.0f).protection(0.5f).harvestLevel(2).speed(6.0f).enchantability(10);
    public static final LOTRMaterial LOSSARNACH = new LOTRMaterial("lossarnach").uses(300).damage(2.5f).protection(0.5f).harvestLevel(2).speed(6.0f).enchantability(10);
    public static final LOTRMaterial MALLORN = new LOTRMaterial("mallorn").uses(200).damage(1.5f).protection(0.0f).harvestLevel(1).speed(4.0f).enchantability(15);
    public static final LOTRMaterial MALLORN_MACE = new LOTRMaterial("mallorn_mace").uses(1500).damage(4.5f).protection(0.0f).harvestLevel(0).speed(0.0f).enchantability(15);
    public static final LOTRMaterial MITHRIL = new LOTRMaterial("mithril").uses(2400).damage(5.0f).protection(0.8f).harvestLevel(4).speed(9.0f).enchantability(8);
    public static final LOTRMaterial MORDOR = new LOTRMaterial("mordor").uses(400).damage(2.5f).protection(0.6f).harvestLevel(2).speed(6.0f).enchantability(7).manFlesh();
    public static final LOTRMaterial MOREDAIN = new LOTRMaterial("moredain").uses(250).damage(2.0f).protection(0.48f).harvestLevel(2).speed(6.0f).enchantability(10);
    public static final LOTRMaterial MOREDAIN_BRONZE = new LOTRMaterial("moredain_bronze").uses(230).damage(1.5f).protection(0.5f).harvestLevel(2).speed(5.0f).enchantability(10);
    public static final LOTRMaterial MOREDAIN_LION_ARMOR = new LOTRMaterial("moredain_lion_armor").uses(300).damage(0.0f).protection(0.4f).harvestLevel(0).speed(0.0f).enchantability(8);
    public static final LOTRMaterial MOREDAIN_SPEAR = new LOTRMaterial("moredain_spear").uses(250).damage(3.0f).protection(0.0f).harvestLevel(2).speed(6.0f).enchantability(10);
    public static final LOTRMaterial MOREDAIN_WOOD = new LOTRMaterial("moredain_wood").uses(250).damage(2.0f).protection(0.0f).harvestLevel(2).speed(6.0f).enchantability(10);
    public static final LOTRMaterial MORGUL = new LOTRMaterial("morgul").uses(450).damage(2.5f).protection(0.6f).harvestLevel(2).speed(6.0f).enchantability(10).manFlesh();
    public static final LOTRMaterial NEAR_HARAD = new LOTRMaterial("near_harad").uses(300).damage(2.5f).protection(0.5f).harvestLevel(2).speed(6.0f).enchantability(10);
    public static final LOTRMaterial PELARGIR = new LOTRMaterial("pelargir").uses(450).damage(2.5f).protection(0.6f).harvestLevel(2).speed(6.0f).enchantability(10);
    public static final LOTRMaterial PINNATH_GELIN = new LOTRMaterial("pinnath_gelin").uses(400).damage(2.0f).protection(0.6f).harvestLevel(2).speed(6.0f).enchantability(10);
    public static final LOTRMaterial RANGER = new LOTRMaterial("ranger").uses(350).damage(2.5f).protection(0.48f).harvestLevel(2).speed(6.0f).enchantability(12);
    public static final LOTRMaterial RANGER_ITHILIEN = new LOTRMaterial("ranger_ithilien").uses(350).damage(2.5f).protection(0.48f).harvestLevel(2).speed(6.0f).enchantability(12);
    public static final LOTRMaterial RHUN = new LOTRMaterial("rhun").uses(400).damage(2.5f).protection(0.5f).harvestLevel(2).speed(6.0f).enchantability(10);
    public static final LOTRMaterial RHUN_GOLD = new LOTRMaterial("rhun_gold").uses(450).damage(2.5f).protection(0.6f).harvestLevel(2).speed(6.0f).enchantability(10);
    public static final LOTRMaterial RIVENDELL = new LOTRMaterial("rivendell").uses(700).damage(3.0f).protection(0.6f).harvestLevel(2).speed(8.0f).enchantability(15);
    public static final LOTRMaterial ROHAN = new LOTRMaterial("rohan").uses(300).damage(2.5f).protection(0.5f).harvestLevel(2).speed(6.0f).enchantability(10);
    public static final LOTRMaterial ROHAN_MARSHAL = new LOTRMaterial("rohan_marshal").uses(400).damage(3.0f).protection(0.6f).harvestLevel(2).speed(6.0f).enchantability(10);
    public static final LOTRMaterial TAUREDAIN = new LOTRMaterial("tauredain").uses(300).damage(2.5f).protection(0.5f).harvestLevel(3).speed(8.0f).enchantability(10);
    public static final LOTRMaterial TAUREDAIN_GOLD = new LOTRMaterial("tauredain_gold").uses(400).damage(0.0f).protection(0.6f).harvestLevel(0).speed(0.0f).enchantability(10);
    public static final LOTRMaterial UMBAR = new LOTRMaterial("umbar").uses(450).damage(2.5f).protection(0.6f).harvestLevel(2).speed(6.0f).enchantability(10);
    public static final LOTRMaterial URUK = new LOTRMaterial("uruk").uses(550).damage(3.0f).protection(0.7f).harvestLevel(2).speed(6.0f).enchantability(5).manFlesh();
    public static final LOTRMaterial UTUMNO = new LOTRMaterial("utumno").uses(400).damage(3.5f).protection(0.7f).harvestLevel(2).speed(6.0f).enchantability(12).manFlesh();
    public static final LOTRMaterial WOOD_ELVEN = new LOTRMaterial("wood_elven").uses(500).damage(3.0f).protection(0.6f).harvestLevel(2).speed(9.0f).enchantability(15);
    /** Equivalents vanilla pour les armes du Legacy declarees avec Item.ToolMaterial. */
    public static final LOTRMaterial IRON = new LOTRMaterial("iron").uses(250).damage(2.0f).protection(0.6f).harvestLevel(2).speed(6.0f).enchantability(14);
    public static final LOTRMaterial STONE = new LOTRMaterial("stone").uses(131).damage(1.0f).protection(0.3f).harvestLevel(1).speed(4.0f).enchantability(5);
    public static final LOTRMaterial WOOD_ELVEN_SCOUT = new LOTRMaterial("wood_elven_scout").uses(300).damage(0.0f).protection(0.4f).harvestLevel(0).speed(0.0f).enchantability(15);

    private final String name;
    private boolean undamageable;
    private int uses;
    private float damage;
    private int[] protection = new int[4];
    private int harvestLevel;
    private float speed;
    private int enchantability;
    private boolean canHarvestManFlesh;
    private Supplier<Ingredient> repairIngredient = () -> Ingredient.of(Items.IRON_INGOT);
    private Supplier<Ingredient> armorRepairIngredient; // si null : identique aux outils

    private final LazyValue<IItemTier> itemTier = new LazyValue<>(this::createItemTier);
    private final LazyValue<IArmorMaterial> armorMaterial = new LazyValue<>(this::createArmorMaterial);

    private LOTRMaterial(String name) {
        this.name = name;
        ALL_MATERIALS.add(this);
    }

    // --- Builder (mêmes setters que l'original) ---

    private LOTRMaterial uses(int i) { this.uses = i; return this; }
    private LOTRMaterial damage(float f) { this.damage = f; return this; }
    private LOTRMaterial harvestLevel(int i) { this.harvestLevel = i; return this; }
    private LOTRMaterial speed(float f) { this.speed = f; return this; }
    private LOTRMaterial enchantability(int i) { this.enchantability = i; return this; }
    private LOTRMaterial manFlesh() { this.canHarvestManFlesh = true; return this; }
    private LOTRMaterial undamageable() { this.undamageable = true; return this; }

    private LOTRMaterial protection(float f) {
        // ordre original : casque, plastron, jambieres, bottes
        float[] base = PROTECTION_BASE;
        int helmet = Math.round(base[0] * f * MAX_PROTECTION);
        int chest = Math.round(base[1] * f * MAX_PROTECTION);
        int legs = Math.round(base[2] * f * MAX_PROTECTION);
        int boots = Math.round(base[3] * f * MAX_PROTECTION);
        // stockage en ordre 1.16.5 : bottes, jambieres, plastron, casque
        this.protection = new int[]{boots, legs, chest, helmet};
        return this;
    }

    /** Equivalent de setCraftingItem(item) : meme ingredient pour outils et armures. */
    public LOTRMaterial setRepairIngredient(Supplier<Ingredient> supplier) {
        this.repairIngredient = supplier;
        this.armorRepairIngredient = null;
        return this;
    }

    /** Equivalent de setCraftingItems(toolItem, armorItem) de l'original. */
    public LOTRMaterial setRepairIngredients(Supplier<Ingredient> tool, Supplier<Ingredient> armor) {
        this.repairIngredient = tool;
        this.armorRepairIngredient = armor;
        return this;
    }

    // --- Accesseurs ---

    public String getName() { return name; }
    public boolean canHarvestManFlesh() { return canHarvestManFlesh; }
    public boolean isDamageable() { return !undamageable; }
    public float getBaseDamage() { return damage; }
    public IItemTier toItemTier() { return itemTier.get(); }
    public IArmorMaterial toArmorMaterial() { return armorMaterial.get(); }

    // --- Fabrique IItemTier ---

    private IItemTier createItemTier() {
        return new IItemTier() {
            @Override public int getUses() { return uses; }
            @Override public float getSpeed() { return speed; }
            @Override public float getAttackDamageBonus() { return damage; }
            @Override public int getLevel() { return harvestLevel; }
            @Override public int getEnchantmentValue() { return enchantability; }
            @Override public Ingredient getRepairIngredient() { return repairIngredient.get(); }
        };
    }

    // --- Fabrique IArmorMaterial ---

    private IArmorMaterial createArmorMaterial() {
        return new IArmorMaterial() {
            @Override
            public int getDurabilityForSlot(EquipmentSlotType slot) {
                if (undamageable) {
                    return 0; // rendu indestructible via Properties dans LOTRItemArmor
                }
                return ARMOR_DURABILITY[slot.getIndex()] * Math.max(1, uses) / DURABILITY_DIVISOR;
            }

            @Override
            public int getDefenseForSlot(EquipmentSlotType slot) {
                return protection[slot.getIndex()];
            }

            @Override public int getEnchantmentValue() { return enchantability; }
            @Override public SoundEvent getEquipSound() { return SoundEvents.ARMOR_EQUIP_IRON; }
            @Override public Ingredient getRepairIngredient() {
                return (armorRepairIngredient != null ? armorRepairIngredient : repairIngredient).get();
            }
            @Override public String getName() { return "lotr:" + name; }
            @Override public float getToughness() { return 0.0f; }
            @Override public float getKnockbackResistance() { return 0.0f; }
        };
    }
}
