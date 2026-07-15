package fr.alleretretour.lotr.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * TRANSPILE AUTOMATIQUEMENT depuis lotr.client.model.LOTRModelGundabadUrukHelmet (1.7.10).
 * Geometrie identique a l'original ; textures Legacy reutilisees telles quelles.
 */
public class LOTRModelGundabadUrukHelmet extends BipedModel<LivingEntity> {

    public LOTRModelGundabadUrukHelmet(float f) {
        super(f);
        texWidth = 64;
        texHeight = 32;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
        head.setPos(0.0f, 0.0f, 0.0f);
        ModelRenderer hornRight = new ModelRenderer(this, 32, 0);
        hornRight.setPos(-f, -f, -f);
        hornRight.addBox(-7.0f, -12.0f, 0.5f, 3, 8, 0, 0.0f);
        hornRight.zRot = 0.10471975511965978f;
        ModelRenderer hornLeft = new ModelRenderer(this, 32, 0);
        hornLeft.setPos(f, -f, -f);
        hornLeft.mirror = true;
        hornLeft.addBox(4.0f, -12.0f, 0.5f, 3, 8, 0, 0.0f);
        hornLeft.zRot = -0.10471975511965978f;
        head.addChild(hornRight);
        head.addChild(hornLeft);
        hat = new ModelRenderer(this, 0, 0);
        body = new ModelRenderer(this, 0, 0);
        rightArm = new ModelRenderer(this, 0, 0);
        leftArm = new ModelRenderer(this, 0, 0);
        rightLeg = new ModelRenderer(this, 0, 0);
        leftLeg = new ModelRenderer(this, 0, 0);
    }
}
