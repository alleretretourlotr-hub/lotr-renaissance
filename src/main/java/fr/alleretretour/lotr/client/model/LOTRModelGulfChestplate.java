package fr.alleretretour.lotr.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * TRANSPILE AUTOMATIQUEMENT depuis lotr.client.model.LOTRModelGulfChestplate (1.7.10).
 * Geometrie identique a l'original ; textures Legacy reutilisees telles quelles.
 */
public class LOTRModelGulfChestplate extends BipedModel<LivingEntity> {

    public LOTRModelGulfChestplate(float f) {
        super(f);
        texWidth = 64;
        texHeight = 32;

        body = new ModelRenderer(this, 16, 16);
        body.setPos(0.0f, 0.0f, 0.0f);
        body.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4, f);
        body.texOffs(16, 0);
        body.addBox(-4.0f, 0.0f, -3.0f - f, 8, 3, 1, 0.0f);
        ModelRenderer chestHorn1 = new ModelRenderer(this, 0, 0);
        chestHorn1.setPos(-2.5f - f, 2.5f, -3.0f - f);
        chestHorn1.addBox(-0.5f, 0.0f, -0.5f, 1, 2, 1, 0.0f);
        chestHorn1.xRot = -0.4363323129985824f;
        chestHorn1.zRot = 0.4363323129985824f;
        body.addChild(chestHorn1);
        ModelRenderer chestHorn2 = new ModelRenderer(this, 0, 0);
        chestHorn2.setPos(0.0f, 3.0f, -3.0f - f);
        chestHorn2.addBox(-0.5f, 0.0f, -0.5f, 1, 2, 1, 0.0f);
        chestHorn2.xRot = -0.4363323129985824f;
        chestHorn2.zRot = 0.0f;
        body.addChild(chestHorn2);
        ModelRenderer chestHorn3 = new ModelRenderer(this, 0, 0);
        chestHorn3.setPos(2.5f + f, 2.5f, -3.0f - f);
        chestHorn3.addBox(-0.5f, 0.0f, -0.5f, 1, 2, 1, 0.0f);
        chestHorn3.xRot = -0.4363323129985824f;
        chestHorn3.zRot = -0.4363323129985824f;
        body.addChild(chestHorn3);
        rightArm = new ModelRenderer(this, 40, 16);
        rightArm.setPos(-5.0f, 2.0f, 0.0f);
        rightArm.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4, f);
        rightArm.texOffs(40, 0);
        rightArm.addBox(-4.0f, -2.0f - f, -2.5f, 5, 1, 5, 0.0f);
        ModelRenderer rightHorn1 = new ModelRenderer(this, 4, 0);
        rightHorn1.setPos(-2.5f, -2.0f - f, 0.0f);
        rightHorn1.addBox(-0.5f, -2.0f, -0.5f, 1, 2, 1, 0.0f);
        rightHorn1.zRot = -0.17453292519943295f;
        rightArm.addChild(rightHorn1);
        ModelRenderer rightHorn2 = new ModelRenderer(this, 8, 0);
        rightHorn2.setPos(-0.5f, -2.0f - f, 0.0f);
        rightHorn2.addBox(-0.5f, -3.0f, -0.5f, 1, 3, 1, 0.0f);
        rightHorn2.zRot = -0.17453292519943295f;
        rightArm.addChild(rightHorn2);
        leftArm = new ModelRenderer(this, 40, 16);
        leftArm.setPos(5.0f, 2.0f, 0.0f);
        leftArm.mirror = true;
        leftArm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4, f);
        leftArm.texOffs(40, 0);
        leftArm.addBox(-1.0f, -2.0f - f, -2.5f, 5, 1, 5, 0.0f);
        ModelRenderer leftHorn1 = new ModelRenderer(this, 4, 0);
        leftHorn1.setPos(2.5f, -2.0f - f, 0.0f);
        leftHorn1.mirror = true;
        leftHorn1.addBox(-0.5f, -2.0f, -0.5f, 1, 2, 1, 0.0f);
        leftHorn1.zRot = 0.17453292519943295f;
        leftArm.addChild(leftHorn1);
        ModelRenderer leftHorn2 = new ModelRenderer(this, 8, 0);
        leftHorn2.setPos(0.5f, -2.0f - f, 0.0f);
        leftHorn2.mirror = true;
        leftHorn2.addBox(-0.5f, -3.0f, -0.5f, 1, 3, 1, 0.0f);
        leftHorn2.zRot = 0.17453292519943295f;
        leftArm.addChild(leftHorn2);
        head = new ModelRenderer(this, 0, 0);
        hat = new ModelRenderer(this, 0, 0);
        rightLeg = new ModelRenderer(this, 0, 0);
        leftLeg = new ModelRenderer(this, 0, 0);
    }
}
