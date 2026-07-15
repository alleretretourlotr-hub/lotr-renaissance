package fr.alleretretour.lotr.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * TRANSPILE AUTOMATIQUEMENT depuis lotr.client.model.LOTRModelGaladhrimHelmet (1.7.10).
 * Geometrie identique a l'original ; textures Legacy reutilisees telles quelles.
 */
public class LOTRModelGaladhrimHelmet extends BipedModel<LivingEntity> {

    public LOTRModelGaladhrimHelmet(float f) {
        super(f);
        texWidth = 64;
        texHeight = 32;

        head = new ModelRenderer(this, 0, 0);
        head.setPos(0.0f, 0.0f, 0.0f);
        head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
        ModelRenderer horn = new ModelRenderer(this, 32, 0);
        horn.addBox(-0.5f, -9.0f - f, 2.0f - f, 1, 3, 3, 0.0f);
        horn.texOffs(32, 6).addBox(-0.5f, -10.0f - f, 3.5f - f, 1, 1, 3, 0.0f);
        horn.texOffs(32, 10).addBox(-0.5f, -11.0f - f, 5.5f - f, 1, 1, 4, 0.0f);
        horn.xRot = 0.7853981633974483f;
        head.addChild(horn);
        hat = new ModelRenderer(this, 0, 0);
        body = new ModelRenderer(this, 0, 0);
        rightArm = new ModelRenderer(this, 0, 0);
        leftArm = new ModelRenderer(this, 0, 0);
        rightLeg = new ModelRenderer(this, 0, 0);
        leftLeg = new ModelRenderer(this, 0, 0);
    }
}
