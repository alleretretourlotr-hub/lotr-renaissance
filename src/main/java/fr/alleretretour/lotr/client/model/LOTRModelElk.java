package fr.alleretretour.lotr.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

/** CONVERTI AUTOMATIQUEMENT de lotr.client.model.LOTRModelElk (1.7.10). */
public class LOTRModelElk<T extends fr.alleretretour.lotr.entity.animal.LOTREntityElk> extends EntityModel<T> {

    public final ModelRenderer body;
    public final ModelRenderer leg1;
    public final ModelRenderer leg2;
    public final ModelRenderer leg3;
    public final ModelRenderer leg4;
    public final ModelRenderer head;
    public final ModelRenderer nose;
    public final ModelRenderer antlersRight_1;
    public final ModelRenderer antlersRight_2;
    public final ModelRenderer antlersRight_3;
    public final ModelRenderer antlersRight_4;
    public final ModelRenderer antlersLeft_1;
    public final ModelRenderer antlersLeft_2;
    public final ModelRenderer antlersLeft_3;
    public final ModelRenderer antlersLeft_4;

    public LOTRModelElk() {
        this(0.0f);
    }

    public LOTRModelElk(float f) {
        texWidth = 128;
        texHeight = 64;
        body = new ModelRenderer(this, 0, 0);
        body.setPos(0.0f, 4.0f, 9.0f);
        body.addBox(-6.0f, -4.0f, -21.0f, 12, 11, 26, f);
        ModelRenderer tail = new ModelRenderer(this, 0, 54);
        tail.addBox(-1.0f, -5.0f, 2.0f, 2, 2, 8, f);
        tail.xRot = -1.0471975511965976f;
        body.addChild(tail);
        leg1 = new ModelRenderer(this, 42, 37);
        leg1.setPos(-4.0f, 3.0f, 8.0f);
        leg1.addBox(-5.5f, 0.0f, -3.0f, 7, 11, 8, f);
        leg1.texOffs(26, 37).addBox(-4.0f, 11.0f, -1.0f, 4, 10, 4, f);
        leg2 = new ModelRenderer(this, 42, 37);
        leg2.mirror = true;
        leg2.setPos(4.0f, 3.0f, 8.0f);
        leg2.addBox(-1.5f, 0.0f, -3.0f, 7, 11, 8, f);
        leg2.texOffs(26, 37).addBox(0.0f, 11.0f, -1.0f, 4, 10, 4, f);
        leg3 = new ModelRenderer(this, 0, 37);
        leg3.setPos(-4.0f, 4.0f, -6.0f);
        leg3.addBox(-4.5f, 0.0f, -3.0f, 6, 10, 7, f);
        leg3.texOffs(26, 37).addBox(-3.5f, 10.0f, -2.0f, 4, 10, 4, f);
        leg4 = new ModelRenderer(this, 0, 37);
        leg4.mirror = true;
        leg4.setPos(4.0f, 4.0f, -6.0f);
        leg4.addBox(-1.5f, 0.0f, -3.0f, 6, 10, 7, f);
        leg4.texOffs(26, 37).addBox(-0.5f, 10.0f, -2.0f, 4, 10, 4, f);
        head = new ModelRenderer(this, 50, 0);
        head.setPos(0.0f, 4.0f, -10.0f);
        head.addBox(-2.0f, -10.0f, -4.0f, 4, 12, 8, f);
        head.texOffs(74, 0).addBox(-3.0f, -16.0f, -8.0f, 6, 6, 13, f);
        head.texOffs(50, 20);
        head.addBox(-2.0f, -18.0f, 3.0f, 1, 2, 1, f);
        head.mirror = true;
        head.addBox(1.0f, -18.0f, 3.0f, 1, 2, 1, f);
        nose = new ModelRenderer(this, 56, 20);
        nose.addBox(-1.0f, -14.5f, -9.0f, 2, 2, 1, f);
        antlersRight_1 = new ModelRenderer(this, 0, 0);
        antlersRight_1.addBox(10.0f, -19.0f, 2.5f, 1, 12, 1, f);
        antlersRight_1.zRot = -1.1344640137963142f;
        antlersRight_2 = new ModelRenderer(this, 4, 0);
        antlersRight_2.addBox(-3.0f, -23.6f, 2.5f, 1, 8, 1, f);
        antlersRight_2.zRot = -0.2617993877991494f;
        antlersRight_3 = new ModelRenderer(this, 8, 0);
        antlersRight_3.addBox(-8.0f, -36.0f, 2.5f, 1, 16, 1, f);
        antlersRight_3.zRot = -0.2617993877991494f;
        antlersRight_4 = new ModelRenderer(this, 12, 0);
        antlersRight_4.addBox(7.5f, -35.0f, 2.5f, 1, 10, 1, f);
        antlersRight_4.zRot = -0.8726646259971648f;
        head.addChild(antlersRight_1);
        head.addChild(antlersRight_2);
        head.addChild(antlersRight_3);
        head.addChild(antlersRight_4);
        antlersLeft_1 = new ModelRenderer(this, 0, 0);
        antlersLeft_1.mirror = true;
        antlersLeft_1.addBox(-11.0f, -19.0f, 2.5f, 1, 12, 1, f);
        antlersLeft_1.zRot = 1.1344640137963142f;
        antlersLeft_2 = new ModelRenderer(this, 4, 0);
        antlersLeft_2.mirror = true;
        antlersLeft_2.addBox(2.0f, -23.6f, 2.5f, 1, 8, 1, f);
        antlersLeft_2.zRot = 0.2617993877991494f;
        antlersLeft_3 = new ModelRenderer(this, 8, 0);
        antlersLeft_3.mirror = true;
        antlersLeft_3.addBox(7.0f, -36.0f, 2.5f, 1, 16, 1, f);
        antlersLeft_3.zRot = 0.2617993877991494f;
        antlersLeft_4 = new ModelRenderer(this, 12, 0);
        antlersLeft_4.mirror = true;
        antlersLeft_4.addBox(-8.5f, -35.0f, 2.5f, 1, 10, 1, f);
        antlersLeft_4.zRot = 0.8726646259971648f;
        head.addChild(antlersLeft_1);
        head.addChild(antlersLeft_2);
        head.addChild(antlersLeft_3);
        head.addChild(antlersLeft_4);
    }

    @Override
    public void setupAnim(T entity, float f, float f1, float f2, float f3, float f4) {
        // PORT : getRearingAmount(renderTick) -> getStandAnimationScale(partialTicks)
        float partial = net.minecraft.client.Minecraft.getInstance().getFrameTime();
        float rearAmount = entity.getStandAnim(partial);
        float antiRearAmount = 1.0f - rearAmount;
        head.y = 4.0f;
        head.z = -10.0f;
        head.xRot = 0.3490658503988659f;
        head.yRot = 0.0f;
        head.y = rearAmount * -6.0f + antiRearAmount * head.y;
        head.z = rearAmount * -1.0f + antiRearAmount * head.z;
        head.xRot = (float) (head.xRot + Math.toRadians(f4));
        head.yRot = (float) (head.yRot + Math.toRadians(f3));
        head.xRot = antiRearAmount * head.xRot;
        head.yRot = antiRearAmount * head.yRot;
        if (f1 > 0.2f) {
        head.xRot += net.minecraft.util.math.MathHelper.cos(f * 0.3f) * 0.1f * f1;
        }
        nose.setPos(head.x, head.y, head.z);
        nose.xRot = head.xRot;
        nose.yRot = head.yRot;
        nose.zRot = head.zRot;
        body.xRot = 0.0f;
        body.xRot = rearAmount * -0.7853982f + antiRearAmount * body.xRot;
        float legRotation = net.minecraft.util.math.MathHelper.cos(f * 0.4f + 3.1415927f) * f1;
        float f17 = -1.0471976f;
        float f18 = 0.2617994f * rearAmount;
        float f19 = net.minecraft.util.math.MathHelper.cos(f2 * 0.4f + 3.1415927f);
        leg4.y = -2.0f * rearAmount + 4.0f * antiRearAmount;
        leg4.z = -2.0f * rearAmount + -6.0f * antiRearAmount;
        leg3.y = leg4.y;
        leg3.z = leg4.z;
        leg1.xRot = f18 + legRotation * antiRearAmount;
        leg2.xRot = f18 + -legRotation * antiRearAmount;
        leg3.xRot = (f17 - f19) * rearAmount + -legRotation * 0.8f * antiRearAmount;
        leg4.xRot = (f17 + f19) * rearAmount + legRotation * 0.8f * antiRearAmount;
    }

    @Override
    public void renderToBuffer(MatrixStack ms, IVertexBuilder buf, int light, int overlay,
                               float red, float green, float blue, float alpha) {
        body.render(ms, buf, light, overlay, red, green, blue, alpha);
        leg1.render(ms, buf, light, overlay, red, green, blue, alpha);
        leg2.render(ms, buf, light, overlay, red, green, blue, alpha);
        leg3.render(ms, buf, light, overlay, red, green, blue, alpha);
        leg4.render(ms, buf, light, overlay, red, green, blue, alpha);
        head.render(ms, buf, light, overlay, red, green, blue, alpha);
        nose.render(ms, buf, light, overlay, red, green, blue, alpha);
    }
}
