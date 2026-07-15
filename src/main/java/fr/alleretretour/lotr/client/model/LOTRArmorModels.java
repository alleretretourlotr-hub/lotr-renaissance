package fr.alleretretour.lotr.client.model;

import fr.alleretretour.lotr.init.LOTRItemsCombat;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

/**
 * Map item -> modele 3D custom (port de lotr.client.model.LOTRArmorModels).
 * CLIENT UNIQUEMENT. Genere automatiquement, complete a la main pour les modeles animes.
 */
public class LOTRArmorModels {

    private static Map<Item, BipedModel<LivingEntity>> models;

    public static BipedModel<LivingEntity> get(Item item) {
        if (models == null) {
            models = new HashMap<>();
            models.put(LOTRItemsCombat.RHUN_GOLD_HELMET.get(), new LOTRModelEasterlingHelmet(1.0f, false));
            models.put(LOTRItemsCombat.RHUN_WARLORD_HELMET.get(), new LOTRModelEasterlingHelmet(1.0f, true));
            models.put(LOTRItemsCombat.GONDOR_HELMET.get(), new LOTRModelGondorHelmet(1.0f));
            models.put(LOTRItemsCombat.ELVEN_HELMET.get(), new LOTRModelGaladhrimHelmet(1.0f));
            models.put(LOTRItemsCombat.GONDOR_WINGED_HELMET.get(), new LOTRModelWingedHelmet(1.0f));
            models.put(LOTRItemsCombat.MORGUL_HELMET.get(), new LOTRModelMorgulHelmet(1.0f));
            models.put(LOTRItemsCombat.GEMSBOK_HELMET.get(), new LOTRModelGemsbokHelmet(1.0f));
            models.put(LOTRItemsCombat.HIGH_ELVEN_HELMET.get(), new LOTRModelHighElvenHelmet(1.0f));
            models.put(LOTRItemsCombat.BLACK_URUK_HELMET.get(), new LOTRModelBlackUrukHelmet(1.0f));
            models.put(LOTRItemsCombat.URUK_HELMET.get(), new LOTRModelUrukHelmet(1.0f));
            models.put(LOTRItemsCombat.NEAR_HARAD_WARLORD_HELMET.get(), new LOTRModelNearHaradWarlordHelmet(1.0f));
            models.put(LOTRItemsCombat.DOL_AMROTH_HELMET.get(), new LOTRModelSwanHelmet(1.0f));
            models.put(LOTRItemsCombat.DOL_AMROTH_CHESTPLATE.get(), new LOTRModelSwanChestplate(1.0f));
            models.put(LOTRItemsCombat.MOREDAIN_LION_HELMET.get(), new LOTRModelMoredainLionHelmet(1.0f));
            models.put(LOTRItemsCombat.GONDOLIN_HELMET.get(), new LOTRModelGondolinHelmet(1.0f));
            models.put(LOTRItemsCombat.ROHAN_MARSHAL_HELMET.get(), new LOTRModelRohanMarshalHelmet(1.0f));
            models.put(LOTRItemsCombat.TAUREDAIN_CHIEFTAIN_HELMET.get(), new LOTRModelTauredainChieftainHelmet(1.0f));
            models.put(LOTRItemsCombat.TAUREDAIN_GOLD_HELMET.get(), new LOTRModelTauredainGoldHelmet(1.0f));
            models.put(LOTRItemsCombat.GUNDABAD_URUK_HELMET.get(), new LOTRModelGundabadUrukHelmet(1.0f));
            models.put(LOTRItemsCombat.URUK_BERSERKER_HELMET.get(), new LOTRModelUrukHelmet(1.0f));
            models.put(LOTRItemsCombat.DORWINION_ELF_HELMET.get(), new LOTRModelDorwinionElfHelmet(1.0f));
            models.put(LOTRItemsCombat.ARNOR_HELMET.get(), new LOTRModelArnorHelmet(1.0f));
            models.put(LOTRItemsCombat.RIVENDELL_HELMET.get(), new LOTRModelHighElvenHelmet(1.0f));
            models.put(LOTRItemsCombat.GULF_HARAD_CHESTPLATE.get(), new LOTRModelGulfChestplate(1.0f));
            models.put(LOTRItemsCombat.UMBAR_HELMET.get(), new LOTRModelUmbarHelmet(1.0f));
            models.put(LOTRItemsCombat.HARNEDOR_HELMET.get(), new LOTRModelHarnedorHelmet(1.0f));
            models.put(LOTRItemsCombat.HARNEDOR_CHESTPLATE.get(), new LOTRModelHarnedorChestplate(1.0f));
            models.put(LOTRItemsCombat.BLACK_NUMENOREAN_HELMET.get(), new LOTRModelBlackNumenoreanHelmet(1.0f));
        }
        return models.get(item);
    }
}
