package fr.alleretretour.lotr.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * TRANSPILE AUTOMATIQUEMENT depuis lotr.client.model.LOTRModelArnorHelmet (1.7.10).
 * Geometrie identique a l'original ; textures Legacy reutilisees telles quelles.
 */
public class LOTRModelArnorHelmet extends BipedModel<LivingEntity> {

    public LOTRModelArnorHelmet(float f) {
        super(f);
        texWidth = 64;
        texHeight = 32;

        head = new ModelRenderer(this, 0, 0);
        head.setPos(0.0f, 0.0f, 0.0f);
        head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
        head.texOffs(32, 0).addBox(-4.5f - f, -13.0f - f, -1.0f, 1, 8, 1, 0.0f);
        head.texOffs(36, 0).addBox(-4.5f - f, -12.0f - f, 0.0f, 1, 7, 1, 0.0f);
        head.texOffs(40, 0).addBox(-4.5f - f, -11.0f - f, 1.0f, 1, 5, 1, 0.0f);
        head.mirror = true;
        head.texOffs(32, 0).addBox(3.5f + f, -13.0f - f, -1.0f, 1, 8, 1, 0.0f);
        head.texOffs(36, 0).addBox(3.5f + f, -12.0f - f, 0.0f, 1, 7, 1, 0.0f);
        head.texOffs(40, 0).addBox(3.5f + f, -11.0f - f, 1.0f, 1, 5, 1, 0.0f);
        hat = new ModelRenderer(this, 0, 0);
        body = new ModelRenderer(this, 0, 0);
        rightArm = new ModelRenderer(this, 0, 0);
        leftArm = new ModelRenderer(this, 0, 0);
        rightLeg = new ModelRenderer(this, 0, 0);
        leftLeg = new ModelRenderer(this, 0, 0);
    }
}
