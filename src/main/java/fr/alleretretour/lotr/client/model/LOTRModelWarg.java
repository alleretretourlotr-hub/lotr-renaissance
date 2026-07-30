package fr.alleretretour.lotr.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

/** CONVERTI AUTOMATIQUEMENT de lotr.client.model.LOTRModelWarg (1.7.10). */
public class LOTRModelWarg<T extends fr.alleretretour.lotr.entity.npc.LOTREntityWarg> extends EntityModel<T> {

    public final ModelRenderer body;
    public final ModelRenderer tail;
    public final ModelRenderer head;
    public final ModelRenderer leg1;
    public final ModelRenderer leg2;
    public final ModelRenderer leg3;
    public final ModelRenderer leg4;

    public LOTRModelWarg() {
        this(0.0f);
    }

    public LOTRModelWarg(float f) {
        texWidth = 128;
        texHeight = 64;
        body = new ModelRenderer(this, 0, 0).setTexSize(128, 64);
        body.addBox(-8.0f, -2.0f, -14.0f, 16, 14, 14, f);
        body.setPos(0.0f, 2.0f, 1.0f);
        body.texOffs(0, 28).addBox(-6.5f, 0.0f, 0.0f, 13, 11, 18, f);
        tail = new ModelRenderer(this, 98, 55).setTexSize(128, 64);
        tail.addBox(-1.0f, -1.0f, 0.0f, 2, 1, 8, f);
        tail.setPos(0.0f, 4.0f, 18.0f);
        head = new ModelRenderer(this, 92, 0).setTexSize(128, 64);
        head.addBox(-5.0f, -5.0f, -8.0f, 10, 10, 8, f);
        head.setPos(0.0f, 8.0f, -13.0f);
        head.texOffs(108, 18).addBox(-3.0f, -1.0f, -12.0f, 6, 5, 4, f);
        head.texOffs(102, 18).addBox(-4.0f, -7.8f, -3.0f, 2, 3, 1, f);
        head.texOffs(102, 18).addBox(2.0f, -7.8f, -3.0f, 2, 3, 1, f);
        leg1 = new ModelRenderer(this, 62, 0).setTexSize(128, 64);
        leg1.mirror = true;
        leg1.addBox(-6.0f, -1.0f, -2.5f, 6, 9, 8, f);
        leg1.setPos(-4.0f, 6.0f, 12.0f);
        leg1.texOffs(66, 17).addBox(-5.5f, 8.0f, -1.0f, 5, 10, 5, f);
        leg2 = new ModelRenderer(this, 62, 0).setTexSize(128, 64);
        leg2.addBox(0.0f, -1.0f, -2.5f, 6, 9, 8, f);
        leg2.setPos(4.0f, 6.0f, 12.0f);
        leg2.texOffs(66, 17).addBox(0.5f, 8.0f, -1.0f, 5, 10, 5, f);
        leg3 = new ModelRenderer(this, 62, 0).setTexSize(128, 64);
        leg3.mirror = true;
        leg3.addBox(-6.0f, -1.0f, -2.5f, 6, 9, 8, f);
        leg3.setPos(-6.0f, 5.0f, -8.0f);
        leg3.texOffs(66, 17).addBox(-5.5f, 8.0f, -1.0f, 5, 11, 5, f);
        leg4 = new ModelRenderer(this, 62, 0).setTexSize(128, 64);
        leg4.addBox(0.0f, -1.0f, -2.5f, 6, 9, 8, f);
        leg4.setPos(6.0f, 5.0f, -8.0f);
        leg4.texOffs(66, 17).addBox(0.5f, 8.0f, -1.0f, 5, 11, 5, f);
    }

    @Override
    public void setupAnim(T entity, float f, float f1, float f2, float f3, float f4) {
        head.xRot = f4 / 57.295776f;
        head.yRot = f3 / 57.295776f;
        leg1.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662f) * 0.9f * f1;
        leg2.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662f + 3.1415927f) * 0.9f * f1;
        leg3.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662f + 3.1415927f) * 0.9f * f1;
        leg4.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662f) * 0.9f * f1;
        tail.xRot = ((fr.alleretretour.lotr.entity.npc.LOTREntityWarg) entity).getTailRotation();
    }

    @Override
    public void renderToBuffer(MatrixStack ms, IVertexBuilder buf, int light, int overlay,
                               float red, float green, float blue, float alpha) {
        body.render(ms, buf, light, overlay, red, green, blue, alpha);
        tail.render(ms, buf, light, overlay, red, green, blue, alpha);
        head.render(ms, buf, light, overlay, red, green, blue, alpha);
        leg1.render(ms, buf, light, overlay, red, green, blue, alpha);
        leg2.render(ms, buf, light, overlay, red, green, blue, alpha);
        leg3.render(ms, buf, light, overlay, red, green, blue, alpha);
        leg4.render(ms, buf, light, overlay, red, green, blue, alpha);
    }
}
