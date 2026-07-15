package fr.alleretretour.lotr.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * Port de lotr.client.model.LOTRModelEasterlingHelmet (casques du Rhun).
 * kineHorns : cornes de bovin du seigneur de guerre (texture 64x64).
 */
public class LOTRModelEasterlingHelmet extends BipedModel<LivingEntity> {

    public LOTRModelEasterlingHelmet(float f, boolean kineHorns) {
        super(f);
        texWidth = 64;
        texHeight = kineHorns ? 64 : 32;

        head = new ModelRenderer(this, 0, 0);
        head.setPos(0.0f, 0.0f, 0.0f);
        head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
        head.texOffs(0, 16).addBox(-5.5f, -8.5f - f, -5.5f, 11, 2, 11, 0.0f);
        head.texOffs(32, 8).addBox(-3.5f, -9.5f - f, -3.5f, 7, 1, 7, 0.0f);
        head.texOffs(50, 16).addBox(0.0f, -10.5f - f, -4.5f - f, 0, 3, 4, 0.0f);
        ModelRenderer horn = new ModelRenderer(this, 44, 16);
        horn.setPos(0.0f, 0.0f, 0.0f);
        horn.addBox(-0.5f, -14.0f - f, -2.0f - f, 1, 8, 2, 0.0f);
        horn.xRot = 0.34906584f;
        head.addChild(horn);
        head.texOffs(24, 0).addBox(-1.0f, -8.0f - f, 4.0f + f, 2, 4, 1, 0.0f);
        head.texOffs(32, 2).addBox(-6.0f, -12.0f - f, 5.0f + f, 12, 4, 0, 0.0f);
        ModelRenderer crest = new ModelRenderer(this, 32, 0);
        crest.setPos(0.0f, -12.0f - f, 5.0f + f);
        crest.addBox(-6.0f, -2.0f, 0.0f, 12, 2, 0, 0.0f);
        crest.xRot = 0.5235988f;
        head.addChild(crest);

        if (kineHorns) {
            ModelRenderer kineHornRight = new ModelRenderer(this, 0, 32);
            kineHornRight.setPos(-1.0f - f, -8.0f - f, 0.0f);
            kineHornRight.addBox(-7.0f, -1.5f, -1.5f, 7, 3, 3, 0.0f);
            ModelRenderer kineHornRight1 = new ModelRenderer(this, 0, 38);
            kineHornRight1.setPos(-7.0f, 0.0f, 0.0f);
            kineHornRight1.addBox(-5.0f, -1.0f, -1.0f, 6, 2, 2, 0.0f);
            ModelRenderer kineHornRight2 = new ModelRenderer(this, 0, 42);
            kineHornRight2.setPos(-5.0f, 0.0f, 0.0f);
            kineHornRight2.addBox(-3.0f, -0.5f, -0.5f, 4, 1, 1, 0.0f);
            ModelRenderer kineHornLeft = new ModelRenderer(this, 0, 32);
            kineHornLeft.mirror = true;
            kineHornLeft.setPos(1.0f + f, -8.0f - f, 0.0f);
            kineHornLeft.addBox(0.0f, -1.5f, -1.5f, 7, 3, 3, 0.0f);
            ModelRenderer kineHornLeft1 = new ModelRenderer(this, 0, 38);
            kineHornLeft1.mirror = true;
            kineHornLeft1.setPos(7.0f, 0.0f, 0.0f);
            kineHornLeft1.addBox(-1.0f, -1.0f, -1.0f, 6, 2, 2, 0.0f);
            ModelRenderer kineHornLeft2 = new ModelRenderer(this, 0, 42);
            kineHornLeft2.mirror = true;
            kineHornLeft2.setPos(5.0f, 0.0f, 0.0f);
            kineHornLeft2.addBox(-1.0f, -0.5f, -0.5f, 4, 1, 1, 0.0f);
            kineHornRight.zRot = 0.6981317f;
            kineHornLeft.zRot = -kineHornRight.zRot;
            kineHornRight1.zRot = -0.5235988f;
            kineHornLeft1.zRot = -kineHornRight1.zRot;
            kineHornRight2.zRot = -0.5235988f;
            kineHornLeft2.zRot = -kineHornRight2.zRot;
            head.addChild(kineHornRight);
            kineHornRight.addChild(kineHornRight1);
            kineHornRight1.addChild(kineHornRight2);
            head.addChild(kineHornLeft);
            kineHornLeft.addChild(kineHornLeft1);
            kineHornLeft1.addChild(kineHornLeft2);
        }

        hat = new ModelRenderer(this, 0, 0);
        body = new ModelRenderer(this, 0, 0);
        rightArm = new ModelRenderer(this, 0, 0);
        leftArm = new ModelRenderer(this, 0, 0);
        rightLeg = new ModelRenderer(this, 0, 0);
        leftLeg = new ModelRenderer(this, 0, 0);
    }
}
