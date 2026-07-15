package fr.alleretretour.lotr.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * TRANSPILE AUTOMATIQUEMENT depuis lotr.client.model.LOTRModelUrukHelmet (1.7.10).
 * Geometrie identique a l'original ; textures Legacy reutilisees telles quelles.
 */
public class LOTRModelUrukHelmet extends BipedModel<LivingEntity> {

    private ModelRenderer crest;
    private ModelRenderer jaw;

    public LOTRModelUrukHelmet(float f) {
        super(f);
        texWidth = 64;
        texHeight = 32;

        head = new ModelRenderer(this, 0, 0);
        head.setPos(0.0f, 0.0f, 0.0f);
        head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
        crest = new ModelRenderer(this, 0, 22);
        crest.addBox(-10.0f, -16.0f, -1.0f, 20, 10, 0, 0.0f);
        crest.xRot = -0.17453292519943295f;
        head.addChild(crest);
        jaw = new ModelRenderer(this, 0, 16);
        jaw.addBox(-6.0f, 2.0f, -4.0f, 12, 6, 0, 0.0f);
        jaw.xRot = -1.0471975511965976f;
        head.addChild(jaw);
        hat = new ModelRenderer(this, 0, 0);
        body = new ModelRenderer(this, 0, 0);
        rightArm = new ModelRenderer(this, 0, 0);
        leftArm = new ModelRenderer(this, 0, 0);
        rightLeg = new ModelRenderer(this, 0, 0);
        leftLeg = new ModelRenderer(this, 0, 0);
    }
}
