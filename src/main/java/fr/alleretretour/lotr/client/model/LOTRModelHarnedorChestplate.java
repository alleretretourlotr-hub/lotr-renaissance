package fr.alleretretour.lotr.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * TRANSPILE AUTOMATIQUEMENT depuis lotr.client.model.LOTRModelHarnedorChestplate (1.7.10).
 * Geometrie identique a l'original ; textures Legacy reutilisees telles quelles.
 */
public class LOTRModelHarnedorChestplate extends BipedModel<LivingEntity> {

    public LOTRModelHarnedorChestplate(float f) {
        super(f);
        texWidth = 64;
        texHeight = 32;

        body = new ModelRenderer(this, 16, 16);
        body.setPos(0.0f, 0.0f, 0.0f);
        body.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4, f);
        rightArm = new ModelRenderer(this, 40, 16);
        rightArm.setPos(-5.0f, 2.0f, 0.0f);
        rightArm.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4, f);
        rightArm.texOffs(46, 0);
        rightArm.addBox(-4.0f - f, -3.0f - f, -2.0f, 5, 1, 4, 0.0f);
        ModelRenderer rightBarbs1 = new ModelRenderer(this, 29, 0);
        rightBarbs1.setPos(-1.5f, -2.5f - f, -2.0f);
        rightBarbs1.addBox(-2.5f, 0.0f, -2.0f, 5, 0, 2, 0.0f);
        rightBarbs1.xRot = 0.5235987755982988f;
        rightArm.addChild(rightBarbs1);
        ModelRenderer rightBarbs2 = new ModelRenderer(this, 29, 3);
        rightBarbs2.setPos(-1.5f, -2.5f - f, 2.0f);
        rightBarbs2.addBox(-2.5f, 0.0f, 0.0f, 5, 0, 2, 0.0f);
        rightBarbs2.xRot = -0.5235987755982988f;
        rightArm.addChild(rightBarbs2);
        leftArm = new ModelRenderer(this, 40, 16);
        leftArm.setPos(5.0f, 2.0f, 0.0f);
        leftArm.mirror = true;
        leftArm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4, f);
        leftArm.texOffs(46, 0);
        leftArm.addBox(-1.0f + f, -3.0f - f, -2.0f, 5, 1, 4, 0.0f);
        ModelRenderer leftBarbs1 = new ModelRenderer(this, 29, 0);
        leftBarbs1.setPos(1.5f, -2.5f - f, -2.0f);
        leftBarbs1.mirror = true;
        leftBarbs1.addBox(-2.5f, 0.0f, -2.0f, 5, 0, 2, 0.0f);
        leftBarbs1.xRot = 0.5235987755982988f;
        leftArm.addChild(leftBarbs1);
        ModelRenderer leftBarbs2 = new ModelRenderer(this, 29, 3);
        leftBarbs2.setPos(1.5f, -2.5f - f, 2.0f);
        leftBarbs2.mirror = true;
        leftBarbs2.addBox(-2.5f, 0.0f, 0.0f, 5, 0, 2, 0.0f);
        leftBarbs2.xRot = -0.5235987755982988f;
        leftArm.addChild(leftBarbs2);
        head = new ModelRenderer(this, 0, 0);
        hat = new ModelRenderer(this, 0, 0);
        rightLeg = new ModelRenderer(this, 0, 0);
        leftLeg = new ModelRenderer(this, 0, 0);
    }
}
