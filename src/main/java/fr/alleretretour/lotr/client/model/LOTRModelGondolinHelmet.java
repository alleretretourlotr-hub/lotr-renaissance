package fr.alleretretour.lotr.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * TRANSPILE AUTOMATIQUEMENT depuis lotr.client.model.LOTRModelGondolinHelmet (1.7.10).
 * Geometrie identique a l'original ; textures Legacy reutilisees telles quelles.
 */
public class LOTRModelGondolinHelmet extends BipedModel<LivingEntity> {

    public LOTRModelGondolinHelmet(float f) {
        super(f);
        texWidth = 64;
        texHeight = 32;

        head = new ModelRenderer(this, 0, 0);
        head.setPos(0.0f, 0.0f, 0.0f);
        head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
        head.texOffs(46, 0).addBox(-0.5f, -14.0f - f, -4.5f, 1, 6, 1, 0.0f);
        head.texOffs(50, 0).addBox(-0.5f, -12.0f - f, -0.5f, 1, 4, 1, 0.0f);
        head.texOffs(54, 0).addBox(-0.5f, -10.0f - f, 3.5f, 1, 2, 1, 0.0f);
        head.texOffs(32, -7).addBox(0.0f, -13.5f - f, -3.5f, 0, 6, 7, 0.0f);
        hat = new ModelRenderer(this, 0, 0);
        body = new ModelRenderer(this, 0, 0);
        rightArm = new ModelRenderer(this, 0, 0);
        leftArm = new ModelRenderer(this, 0, 0);
        rightLeg = new ModelRenderer(this, 0, 0);
        leftLeg = new ModelRenderer(this, 0, 0);
    }
}
