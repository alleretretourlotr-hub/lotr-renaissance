package fr.alleretretour.lotr.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

/** CONVERTI AUTOMATIQUEMENT de lotr.client.model.LOTRModelTroll (1.7.10). */
public class LOTRModelTroll<T extends fr.alleretretour.lotr.entity.npc.LOTREntityTroll> extends EntityModel<T> {

    public final ModelRenderer head;
    public final ModelRenderer headHurt;
    public final ModelRenderer body;
    public final ModelRenderer rightArm;
    public final ModelRenderer leftArm;
    public final ModelRenderer rightLeg;
    public final ModelRenderer leftLeg;
    public final ModelRenderer woodenClub;
    public final ModelRenderer woodenClubSpikes;
    public final ModelRenderer warhammer;
    public final ModelRenderer battleaxe;

    public LOTRModelTroll() {
        this(0.0f);
    }

    public LOTRModelTroll(float f) {
        texWidth = 128;
        texHeight = 128;
        head = new ModelRenderer(this, 0, 0);
        head.addBox(-6.0f, -6.0f, -12.0f, 12, 12, 12, f);
        head.setPos(0.0f, -27.0f, -6.0f);
        head.texOffs(40, 0).addBox(6.0f, -2.0f, -8.0f, 1, 4, 3, f);
        head.mirror = true;
        head.texOffs(40, 0).addBox(-7.0f, -2.0f, -8.0f, 1, 4, 3, f);
        head.mirror = false;
        head.texOffs(0, 0).addBox(-1.0f, -1.0f, -14.0f, 2, 3, 2, f);
        headHurt = new ModelRenderer(this, 48, 44);
        headHurt.addBox(-6.0f, -6.0f, -12.0f, 12, 12, 12, f);
        headHurt.setPos(0.0f, -27.0f, -6.0f);
        headHurt.texOffs(40, 0).addBox(6.0f, -2.0f, -8.0f, 1, 4, 3, f);
        headHurt.mirror = true;
        headHurt.texOffs(40, 0).addBox(-7.0f, -2.0f, -8.0f, 1, 4, 3, f);
        headHurt.mirror = false;
        headHurt.texOffs(0, 0).addBox(-1.0f, -1.0f, -14.0f, 2, 3, 2, f);
        body = new ModelRenderer(this, 48, 0);
        body.addBox(-12.0f, -28.0f, -8.0f, 24, 28, 16, f);
        body.setPos(0.0f, 0.0f, 0.0f);
        rightArm = new ModelRenderer(this, 0, 24);
        rightArm.mirror = true;
        rightArm.addBox(-12.0f, -3.0f, -6.0f, 12, 12, 12, f);
        rightArm.setPos(-12.0f, -23.0f, 0.0f);
        rightArm.texOffs(0, 48).addBox(-11.0f, 9.0f, -5.0f, 10, 20, 10, f);
        leftArm = new ModelRenderer(this, 0, 24);
        leftArm.addBox(0.0f, -3.0f, -6.0f, 12, 12, 12, f);
        leftArm.setPos(12.0f, -23.0f, 0.0f);
        leftArm.texOffs(0, 48).addBox(1.0f, 9.0f, -5.0f, 10, 20, 10, f);
        rightLeg = new ModelRenderer(this, 0, 78);
        rightLeg.mirror = true;
        rightLeg.addBox(-6.0f, 0.0f, -6.0f, 11, 12, 12, f);
        rightLeg.setPos(-6.0f, 0.0f, 0.0f);
        rightLeg.texOffs(0, 102).addBox(-5.5f, 12.0f, -5.0f, 10, 12, 10);
        leftLeg = new ModelRenderer(this, 0, 78);
        leftLeg.addBox(-5.0f, 0.0f, -6.0f, 11, 12, 12, f);
        leftLeg.setPos(6.0f, 0.0f, 0.0f);
        leftLeg.texOffs(0, 102).addBox(-4.5f, 12.0f, -5.0f, 10, 12, 10);
        woodenClub = new ModelRenderer(this, 0, 0);
        woodenClub.addBox(-9.0f, 5.0f, 21.0f, 6, 24, 6, f);
        woodenClub.setPos(-12.0f, -23.0f, 0.0f);
        woodenClubSpikes = new ModelRenderer(this, 24, 0);
        woodenClubSpikes.addBox(-12.0f, 25.0f, 23.5f, 12, 1, 1, f);
        woodenClubSpikes.addBox(-12.0f, 20.0f, 23.5f, 12, 1, 1, f);
        woodenClubSpikes.addBox(-12.0f, 15.0f, 23.5f, 12, 1, 1, f);
        woodenClubSpikes.texOffs(24, 2);
        woodenClubSpikes.addBox(-6.5f, 25.0f, 18.0f, 1, 1, 12, f);
        woodenClubSpikes.addBox(-6.5f, 20.0f, 18.0f, 1, 1, 12, f);
        woodenClubSpikes.addBox(-6.5f, 15.0f, 18.0f, 1, 1, 12, f);
        woodenClubSpikes.setPos(-12.0f, -23.0f, 0.0f);
        warhammer = new ModelRenderer(this, 52, 29);
        warhammer.setPos(-12.0f, -23.0f, 0.0f);
        warhammer.addBox(-7.5f, 5.0f, 22.5f, 3, 20, 3, f);
        warhammer.texOffs(0, 32).addBox(-12.0f, 25.0f, 14.0f, 12, 12, 20, f);
        battleaxe = new ModelRenderer(this, 64, 0);
        battleaxe.setPos(-12.0f, -23.0f, 0.0f);
        battleaxe.addBox(-7.0f, -40.0f, 22.5f, 2, 80, 2, f);
        battleaxe.texOffs(72, 0);
        battleaxe.addBox(-6.0f, 20.0f, 24.0f, 0, 24, 16, f);
    }

    @Override
    public void setupAnim(T entity, float f, float f1, float f2, float f3, float f4) {
        float f6;
        head.x = 0.0f;
        head.y = -27.0f;
        head.zRot = 0.0f;
        body.x = 0.0f;
        body.y = 0.0f;
        body.zRot = 0.0f;
        rightArm.x = -12.0f;
        rightArm.y = -23.0f;
        leftArm.x = 12.0f;
        leftArm.y = -23.0f;
        head.yRot = f3 / 57.295776f;
        head.xRot = f4 / 57.295776f;
        if (((fr.alleretretour.lotr.entity.npc.LOTREntityTroll) entity).sniffTime > 0) {
        f6 = (((fr.alleretretour.lotr.entity.npc.LOTREntityTroll) entity).sniffTime - (f2 - entity.tickCount)) / 8.0f;
        head.yRot = net.minecraft.util.math.MathHelper.sin(f6 * 6.2831855f) * 0.5f;
        }
        rightArm.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662f + 3.1415927f) * 2.0f * f1 * 0.5f;
        leftArm.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662f) * 2.0f * f1 * 0.5f;
        if (true) {
        rightArm.xRot = rightArm.xRot * 0.5f - 0.31415927f;
        }
        rightArm.zRot = 0.0f;
        leftArm.zRot = 0.0f;
        if (attackTime > -9990.0f) {
        f6 = attackTime;
        body.yRot = net.minecraft.util.math.MathHelper.sin(net.minecraft.util.math.MathHelper.sqrt(f6) * 3.1415927f * 2.0f) * 0.2f;
        rightArm.z = net.minecraft.util.math.MathHelper.sin(body.yRot) * 5.0f;
        rightArm.x = -net.minecraft.util.math.MathHelper.cos(body.yRot) * 12.0f;
        leftArm.z = -net.minecraft.util.math.MathHelper.sin(body.yRot) * 5.0f;
        leftArm.x = net.minecraft.util.math.MathHelper.cos(body.yRot) * 12.0f;
        leftArm.xRot += body.yRot;
        f6 = 1.0f - attackTime;
        f6 *= f6;
        f6 *= f6;
        f6 = 1.0f - f6;
        float f7 = net.minecraft.util.math.MathHelper.sin(f6 * 3.1415927f);
        float f8 = net.minecraft.util.math.MathHelper.sin(attackTime * 3.1415927f) * -(head.xRot - 0.7f) * 0.75f;
        rightArm.xRot = (float) (rightArm.xRot - (f7 * 1.2 + f8));
        rightArm.zRot = net.minecraft.util.math.MathHelper.sin(attackTime * 3.1415927f) * -0.4f;
        }
        rightLeg.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662f) * 1.4f * f1;
        leftLeg.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662f + 3.1415927f) * 1.4f * f1;
        rightLeg.yRot = 0.0f;
        leftLeg.yRot = 0.0f;
        rightArm.yRot = 0.0f;
        leftArm.yRot = 0.0f;
        rightArm.zRot += net.minecraft.util.math.MathHelper.cos(f2 * 0.09f) * 0.05f + 0.05f;
        leftArm.zRot -= net.minecraft.util.math.MathHelper.cos(f2 * 0.09f) * 0.05f + 0.05f;
        rightArm.xRot += net.minecraft.util.math.MathHelper.sin(f2 * 0.067f) * 0.05f;
        leftArm.xRot -= net.minecraft.util.math.MathHelper.sin(f2 * 0.067f) * 0.05f;
        boolean throwing = false;
        if (false) {
        throwing = true;
        }
        if (throwing) {
        rightArm.xRot -= 0.5f;
        rightArm.zRot -= 0.4f;
        leftArm.xRot = rightArm.xRot;
        leftArm.yRot = -rightArm.yRot;
        leftArm.zRot = -rightArm.zRot;
        }
        if (entity instanceof net.minecraft.entity.LivingEntity) {
        float f62 = net.minecraft.util.math.MathHelper.sin(f * 0.2f) * 0.3f * f1;
        head.x += net.minecraft.util.math.MathHelper.sin(f62) * 27.0f;
        head.y += 27.0f - net.minecraft.util.math.MathHelper.cos(f62) * 27.0f;
        head.zRot += f62;
        body.zRot += f62;
        float armRotationOffsetX = net.minecraft.util.math.MathHelper.sin(f62) * 23.0f + net.minecraft.util.math.MathHelper.cos(f62) * 12.0f - 12.0f;
        float armRotationOffsetY = net.minecraft.util.math.MathHelper.cos(f62) * -23.0f + net.minecraft.util.math.MathHelper.sin(f62) * 12.0f + 23.0f;
        rightArm.x += armRotationOffsetX;
        rightArm.y -= armRotationOffsetY;
        rightArm.zRot += f62;
        leftArm.x += armRotationOffsetX;
        leftArm.y += armRotationOffsetY;
        leftArm.zRot += f62;
        }
        headHurt.x = head.x;
        headHurt.y = head.y;
        headHurt.z = head.z;
        headHurt.xRot = head.xRot;
        headHurt.yRot = head.yRot;
        headHurt.zRot = head.zRot;
    }

    @Override
    public void renderToBuffer(MatrixStack ms, IVertexBuilder buf, int light, int overlay,
                               float red, float green, float blue, float alpha) {
        head.render(ms, buf, light, overlay, red, green, blue, alpha);
        headHurt.render(ms, buf, light, overlay, red, green, blue, alpha);
        body.render(ms, buf, light, overlay, red, green, blue, alpha);
        rightArm.render(ms, buf, light, overlay, red, green, blue, alpha);
        leftArm.render(ms, buf, light, overlay, red, green, blue, alpha);
        rightLeg.render(ms, buf, light, overlay, red, green, blue, alpha);
        leftLeg.render(ms, buf, light, overlay, red, green, blue, alpha);
        woodenClub.render(ms, buf, light, overlay, red, green, blue, alpha);
        woodenClubSpikes.render(ms, buf, light, overlay, red, green, blue, alpha);
        warhammer.render(ms, buf, light, overlay, red, green, blue, alpha);
        battleaxe.render(ms, buf, light, overlay, red, green, blue, alpha);
    }
}
