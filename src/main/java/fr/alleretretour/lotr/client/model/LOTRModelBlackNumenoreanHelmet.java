package fr.alleretretour.lotr.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * TRANSPILE AUTOMATIQUEMENT depuis lotr.client.model.LOTRModelBlackNumenoreanHelmet (1.7.10).
 * Geometrie identique a l'original ; textures Legacy reutilisees telles quelles.
 */
public class LOTRModelBlackNumenoreanHelmet extends BipedModel<LivingEntity> {

    public LOTRModelBlackNumenoreanHelmet(float f) {
        super(f);
        texWidth = 64;
        texHeight = 32;

        head = new ModelRenderer(this, 0, 0);
        head.setPos(0.0f, 0.0f, 0.0f);
        head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
        ModelRenderer wingLeft = new ModelRenderer(this, 33, 0);
        wingLeft.setPos(-4.0f - f, -8.0f - f, 0.0f);
        wingLeft.addBox(-6.0f, -6.0f, 0.0f, 6, 16, 0, 0.0f);
        wingLeft.yRot = 0.4363323129985824f;
        head.addChild(wingLeft);
        ModelRenderer wingRight = new ModelRenderer(this, 33, 0);
        wingRight.mirror = true;
        wingRight.setPos(4.0f + f, -8.0f - f, 0.0f);
        wingRight.addBox(0.0f, -6.0f, 0.0f, 6, 16, 0, 0.0f);
        wingRight.yRot = -0.4363323129985824f;
        head.addChild(wingRight);
        hat = new ModelRenderer(this, 0, 0);
        body = new ModelRenderer(this, 0, 0);
        rightArm = new ModelRenderer(this, 0, 0);
        leftArm = new ModelRenderer(this, 0, 0);
        rightLeg = new ModelRenderer(this, 0, 0);
        leftLeg = new ModelRenderer(this, 0, 0);
    }
}
