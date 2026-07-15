package fr.alleretretour.lotr.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * TRANSPILE AUTOMATIQUEMENT depuis lotr.client.model.LOTRModelUmbarHelmet (1.7.10).
 * Geometrie identique a l'original ; textures Legacy reutilisees telles quelles.
 */
public class LOTRModelUmbarHelmet extends BipedModel<LivingEntity> {

    public LOTRModelUmbarHelmet(float f) {
        super(f);
        texWidth = 64;
        texHeight = 32;

        head = new ModelRenderer(this, 0, 0);
        head.setPos(0.0f, 0.0f, 0.0f);
        head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
        hat = new ModelRenderer(this, 32, 0);
        hat.setPos(0.0f, 0.0f, 0.0f);
        hat.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, f + 0.5f);
        head.texOffs(0, 0);
        head.addBox(-0.5f, -11.0f - f, -3.0f, 1, 3, 1, 0.0f);
        head.addBox(-0.5f, -10.0f - f, 2.0f, 1, 2, 1, 0.0f);
        head.texOffs(0, 16).addBox(0.0f, -13.0f - f, -6.0f, 0, 4, 12, 0.0f);
        body = new ModelRenderer(this, 0, 0);
        rightArm = new ModelRenderer(this, 0, 0);
        leftArm = new ModelRenderer(this, 0, 0);
        rightLeg = new ModelRenderer(this, 0, 0);
        leftLeg = new ModelRenderer(this, 0, 0);
    }
}
