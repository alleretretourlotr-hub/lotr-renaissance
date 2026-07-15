package fr.alleretretour.lotr.client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

/**
 * Port de lotr.client.model.LOTRModelSwanChestplate (plastron cygne de Dol Amroth).
 * NOTE 1.16.5 : la couche d'armure vanilla n'appelle jamais setupAnim sur les
 * modeles custom (elle ne fait que copier les rotations des parties de base).
 * L'animation des ailes est donc appliquee via animateWings(), appele chaque
 * frame depuis LOTRItemArmor.getArmorModel().
 */
public class LOTRModelSwanChestplate extends BipedModel<LivingEntity>
        implements LOTRAnimatedArmorModel {

    private final ModelRenderer[] wingsRight;
    private final ModelRenderer[] wingsLeft;

    public LOTRModelSwanChestplate(float f) {
        super(f);
        texWidth = 64;
        texHeight = 32;

        body = new ModelRenderer(this, 0, 0);
        body.setPos(0.0f, 0.0f, 0.0f);
        body.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4, f);

        int wings = 12;
        wingsRight = new ModelRenderer[wings];
        for (int i = 0; i < wings; i++) {
            ModelRenderer wing = new ModelRenderer(this, 0, 16);
            wing.setPos(-2.0f, 0.0f, 0.0f);
            wing.addBox(-2.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f);
            wing.texOffs(6, 16).addBox(-2.0f, 1.0f, 0.5f, 2, 10, 0, 0.0f);
            wingsRight[i] = wing;
        }
        for (int i = 0; i < wings - 1; i++) {
            wingsRight[i].addChild(wingsRight[i + 1]);
        }
        wingsRight[0].setPos(-2.0f, 1.0f, 1.0f);
        body.addChild(wingsRight[0]);

        wingsLeft = new ModelRenderer[wings];
        for (int i = 0; i < wings; i++) {
            ModelRenderer wing = new ModelRenderer(this, 0, 16);
            wing.setPos(2.0f, 0.0f, 0.0f);
            wing.mirror = true;
            wing.addBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f);
            wing.texOffs(6, 16).addBox(0.0f, 1.0f, 0.5f, 2, 10, 0, 0.0f);
            wingsLeft[i] = wing;
        }
        for (int i = 0; i < wings - 1; i++) {
            wingsLeft[i].addChild(wingsLeft[i + 1]);
        }
        wingsLeft[0].setPos(2.0f, 1.0f, 1.0f);
        body.addChild(wingsLeft[0]);

        rightArm = new ModelRenderer(this, 24, 0);
        rightArm.setPos(-5.0f, 2.0f, 0.0f);
        rightArm.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4, f);
        leftArm = new ModelRenderer(this, 24, 0);
        leftArm.setPos(5.0f, 2.0f, 0.0f);
        leftArm.mirror = true;
        leftArm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4, f);

        head = new ModelRenderer(this, 0, 0);
        hat = new ModelRenderer(this, 0, 0);
        rightLeg = new ModelRenderer(this, 0, 0);
        leftLeg = new ModelRenderer(this, 0, 0);

        animate(null); // pose de repos par defaut
    }

    /**
     * Reproduction exacte de setRotationAngles() de l'original :
     * ondulation lente + battement lie au mouvement (du porteur ou de sa monture).
     */
    @Override
    public void animate(LivingEntity entity) {
        float partial = Minecraft.getInstance().getFrameTime();
        float motion = 0.0f;
        float motionPhase = 0.0f;
        float ageInTicks = 0.0f;

        if (entity != null) {
            ageInTicks = entity.tickCount + partial;
            motion = entity.animationSpeedOld
                    + (entity.animationSpeed - entity.animationSpeedOld) * partial;
            motionPhase = entity.animationPosition - entity.animationSpeed * (1.0f - partial);
            if (entity.getVehicle() instanceof LivingEntity) {
                LivingEntity mount = (LivingEntity) entity.getVehicle();
                motion = mount.animationSpeedOld
                        + (mount.animationSpeed - mount.animationSpeedOld) * partial;
                motionPhase = mount.animationPosition - mount.animationSpeed * (1.0f - partial);
                motion *= 1.5f;
                motionPhase *= 2.0f;
            }
        }

        float wingAngleBase = 0.17453292f;
        wingAngleBase += MathHelper.sin(ageInTicks * 0.02f) * 0.01f;
        wingAngleBase += MathHelper.sin(motionPhase * 0.2f) * 0.03f * motion;
        float wingYaw = 0.87266463f;
        wingYaw += MathHelper.sin(ageInTicks * 0.03f) * 0.05f;
        wingYaw += MathHelper.sin(motionPhase * 0.25f) * 0.12f * motion;

        for (int i = 0; i < wingsRight.length; i++) {
            float factor = i + 1;
            float wingAngle = wingAngleBase / (factor / 3.4f);
            wingsRight[i].zRot = wingAngle;
            wingsLeft[i].zRot = -wingAngle;
        }
        wingsRight[0].yRot = MathHelper.sin(wingYaw);
        wingsRight[0].xRot = MathHelper.cos(wingYaw);
        wingsLeft[0].yRot = MathHelper.sin(-wingYaw);
        wingsLeft[0].xRot = MathHelper.cos(-wingYaw);
    }
}
