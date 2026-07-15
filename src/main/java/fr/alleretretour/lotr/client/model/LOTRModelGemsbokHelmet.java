package fr.alleretretour.lotr.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * TRANSPILE AUTOMATIQUEMENT depuis lotr.client.model.LOTRModelGemsbokHelmet (1.7.10).
 * Geometrie identique a l'original ; textures Legacy reutilisees telles quelles.
 */
public class LOTRModelGemsbokHelmet extends BipedModel<LivingEntity> {

    private ModelRenderer hornRight;
    private ModelRenderer hornLeft;

    public LOTRModelGemsbokHelmet(float f) {
        super(f);
        texWidth = 64;
        texHeight = 32;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
        head.setPos(0.0f, 0.0f, 0.0f);
        hornRight = new ModelRenderer(this, 32, 0);
        hornRight.addBox(-4.9f, -7.0f, 7.5f, 1, 1, 13);
        hornLeft = new ModelRenderer(this, 32, 0);
        hornLeft.mirror = true;
        hornLeft.addBox(3.9f, -7.0f, 7.5f, 1, 1, 13);
        hornRight.xRot = hornLeft.xRot = 0.3490658503988659f;
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
