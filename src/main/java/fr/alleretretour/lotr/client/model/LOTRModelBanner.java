package fr.alleretretour.lotr.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.RenderType;

/** PORT exact de lotr.client.model.LOTRModelBanner (boites et UV identiques). */
public class LOTRModelBanner extends Model {

    public final ModelRenderer stand;
    public final ModelRenderer post;
    public final ModelRenderer bannerFront;
    public final ModelRenderer bannerBack;

    public LOTRModelBanner() {
        super(RenderType::entityCutoutNoCull);
        texWidth = 64;
        texHeight = 64;
        stand = new ModelRenderer(this, 0, 0);
        stand.setPos(0.0f, 24.0f, 0.0f);
        stand.addBox(-6.0f, -2.0f, -6.0f, 12, 2, 12);
        post = new ModelRenderer(this, 0, 14);
        post.setPos(0.0f, 24.0f, 0.0f);
        post.addBox(-0.5f, -48.0f, -0.5f, 1, 47, 1);
        post.texOffs(4, 14).addBox(-8.0f, -43.0f, -1.5f, 16, 1, 3);
        bannerFront = new ModelRenderer(this, 0, 0);
        bannerFront.setPos(0.0f, -18.0f, 0.0f);
        bannerFront.addBox(-8.0f, 0.0f, -1.0f, 16, 32, 0);
        bannerBack = new ModelRenderer(this, 0, 0);
        bannerBack.setPos(0.0f, -18.0f, 0.0f);
        bannerBack.addBox(-8.0f, 0.0f, -1.0f, 16, 32, 0);
        bannerBack.yRot = 3.1415927f;
    }

    @Override
    public void renderToBuffer(MatrixStack ms, IVertexBuilder buf, int light, int overlay,
                               float r, float g, float b, float a) {
        post.render(ms, buf, light, overlay, r, g, b, a);
        bannerFront.render(ms, buf, light, overlay, r, g, b, a);
        bannerBack.render(ms, buf, light, overlay, r, g, b, a);
    }

    public void renderStand(MatrixStack ms, IVertexBuilder buf, int light, int overlay) {
        stand.render(ms, buf, light, overlay, 1.0f, 1.0f, 1.0f, 1.0f);
    }
}
