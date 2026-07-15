package fr.alleretretour.lotr.client.model;

import fr.alleretretour.lotr.entity.npc.LOTREntityNPC;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ModelRenderer;

/**
 * PORT de lotr.client.model.LOTRModelOrc : biped + nez + oreilles pointues,
 * UVs et rotations exactes de l'original. Base PlayerModel (peaux 64x64).
 */
public class LOTRModelOrc<T extends LOTREntityNPC> extends PlayerModel<T> {

    public LOTRModelOrc(float scale) {
        super(scale, false);
        ModelRenderer nose = new ModelRenderer(this, 14, 17);
        nose.addBox(-0.5f, -4.0f, -4.8f, 1, 2, 1, scale);
        nose.setPos(0.0f, 0.0f, 0.0f);

        ModelRenderer earRight = new ModelRenderer(this, 0, 0);
        earRight.addBox(-3.5f, -5.5f, 2.0f, 1, 2, 3, scale);
        earRight.setPos(0.0f, 0.0f, 0.0f);
        earRight.xRot = 0.2617994f;
        earRight.yRot = -0.5235988f;
        earRight.zRot = -0.22689281f;

        ModelRenderer earLeft = new ModelRenderer(this, 24, 0);
        earLeft.addBox(2.5f, -5.5f, 2.0f, 1, 2, 3, scale);
        earLeft.setPos(0.0f, 0.0f, 0.0f);
        earLeft.xRot = 0.2617994f;
        earLeft.yRot = 0.5235988f;
        earLeft.zRot = 0.22689281f;

        head.addChild(nose);
        head.addChild(earRight);
        head.addChild(earLeft);
    }
}
