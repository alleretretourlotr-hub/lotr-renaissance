package fr.alleretretour.lotr.client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

/**
 * Port de lotr.client.model.LOTRModelRohanMarshalHelmet.
 * Criniere de cheval en 3 pans, eventail statique + ondulation liee au mouvement.
 */
public class LOTRModelRohanMarshalHelmet extends BipedModel<LivingEntity>
        implements LOTRAnimatedArmorModel {

    private final ModelRenderer[] manes;

    public LOTRModelRohanMarshalHelmet(float f) {
        super(f);
        texWidth = 64;
        texHeight = 32;

        head = new ModelRenderer(this, 0, 0);
        head.setPos(0.0f, 0.0f, 0.0f);
        head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
        head.texOffs(0, 16).addBox(-1.0f, -11.5f - f, -4.5f - f, 2, 7, 6, 0.0f);
        manes = new ModelRenderer[3];
        for (int i = 0; i < manes.length; i++) {
            ModelRenderer mane = new ModelRenderer(this, 32, 0);
            mane.setPos(0.0f, -f, f);
            mane.addBox(0.0f, -11.0f, -1.0f, 0, 14, 12, 0.0f);
            manes[i] = mane;
            head.addChild(mane);
        }

        hat = new ModelRenderer(this, 0, 0);
        body = new ModelRenderer(this, 0, 0);
        rightArm = new ModelRenderer(this, 0, 0);
        leftArm = new ModelRenderer(this, 0, 0);
        rightLeg = new ModelRenderer(this, 0, 0);
        leftLeg = new ModelRenderer(this, 0, 0);

        animate(null);
    }

    @Override
    public void animate(LivingEntity entity) {
        float limbSwing = 0.0f;
        float limbSwingAmount = 0.0f;
        if (entity != null) {
            float partial = Minecraft.getInstance().getFrameTime();
            limbSwingAmount = entity.animationSpeedOld
                    + (entity.animationSpeed - entity.animationSpeedOld) * partial;
            limbSwing = entity.animationPosition - entity.animationSpeed * (1.0f - partial);
        }
        float mid = manes.length / 2.0f - 0.5f;
        for (int i = 0; i < manes.length; i++) {
            ModelRenderer mane = manes[i];
            mane.xRot = (mid - Math.abs(i - mid)) / mid * 0.22f;
            mane.yRot = (i - mid) / mid * 0.17f;
            mane.xRot += MathHelper.sin(limbSwing * 0.4f) * limbSwingAmount * 0.2f;
        }
    }
}
