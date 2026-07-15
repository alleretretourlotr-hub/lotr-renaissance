package fr.alleretretour.lotr.client.model;

import net.minecraft.entity.LivingEntity;

/**
 * Modeles d'armure animes : la couche d'armure 1.16.5 n'appelant jamais
 * setupAnim, l'animation est poussee chaque frame par LOTRItemArmor.getArmorModel.
 */
public interface LOTRAnimatedArmorModel {

    void animate(LivingEntity entity);
}
