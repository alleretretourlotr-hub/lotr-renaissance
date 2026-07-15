package fr.alleretretour.lotr.client.render;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.util.ResourceLocation;

/**
 * Rendu generique type fleche pour les projectiles simples (carreaux, flechettes).
 * TippedArrowRenderer exige ArrowEntity precisement ; ArrowRenderer accepte
 * AbstractArrowEntity, dont heritent nos projectiles.
 * TODO : textures dediees (carreau, flechette) dans un prochain passage.
 */
public class LOTRArrowLikeRenderer<T extends AbstractArrowEntity> extends ArrowRenderer<T> {

    private static final ResourceLocation ARROW_TEXTURE =
            new ResourceLocation("textures/entity/projectiles/arrow.png");

    public LOTRArrowLikeRenderer(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return ARROW_TEXTURE;
    }
}
