package fr.alleretretour.lotr.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * Port de lotr.client.model.LOTRModelSwanHelmet (casque cygne de Dol Amroth).
 * Casque + deux ailes fixes orientees vers l'arriere.
 */
public class LOTRModelSwanHelmet extends BipedModel<LivingEntity> {

    private final ModelRenderer wingRight;
    private final ModelRenderer wingLeft;

    public LOTRModelSwanHelmet(float f) {
        super(f);
        texWidth = 64;
        texHeight = 32;

        head = new ModelRenderer(this, 0, 0);
        head.setPos(0.0f, 0.0f, 0.0f);
        head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
        head.texOffs(32, 0).addBox(-0.5f, -9.0f, -3.5f, 1, 1, 7, f);

        wingRight = new ModelRenderer(this, 0, 16);
        wingRight.addBox(-4.0f - f, -6.0f, 1.0f + f, 1, 1, 9, 0.0f);
        wingRight.texOffs(20, 16).addBox(-3.5f - f, -5.0f, 1.9f + f, 0, 6, 8, 0.0f);
        wingLeft = new ModelRenderer(this, 0, 16);
        wingLeft.mirror = true;
        wingLeft.addBox(3.0f + f, -6.0f, 1.0f + f, 1, 1, 9, 0.0f);
        wingLeft.texOffs(20, 16).addBox(3.5f + f, -5.0f, 1.9f + f, 0, 6, 8, 0.0f);
        head.addChild(wingRight);
        head.addChild(wingLeft);

        hat = new ModelRenderer(this, 0, 0);
        body = new ModelRenderer(this, 0, 0);
        rightArm = new ModelRenderer(this, 0, 0);
        leftArm = new ModelRenderer(this, 0, 0);
        rightLeg = new ModelRenderer(this, 0, 0);
        leftLeg = new ModelRenderer(this, 0, 0);
    }

    @Override
    public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float wingYaw = -0.4363323f;
        float wingPitch = 0.34906585f;
        wingRight.yRot = wingYaw;
        wingLeft.yRot = -wingYaw;
        wingRight.xRot = wingPitch;
        wingLeft.xRot = wingPitch;
    }
}
