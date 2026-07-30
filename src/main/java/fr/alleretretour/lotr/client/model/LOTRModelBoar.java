package fr.alleretretour.lotr.client.model;

import fr.alleretretour.lotr.entity.animal.LOTREntityWildBoar;
import net.minecraft.client.renderer.entity.model.PigModel;
import net.minecraft.client.renderer.model.ModelRenderer;

/** PORT exact de lotr.client.model.LOTRModelBoar : cochon + groin et defenses. */
public class LOTRModelBoar<T extends LOTREntityWildBoar> extends PigModel<T> {

    public LOTRModelBoar() {
        this(0.0f);
    }

    public LOTRModelBoar(float f) {
        super(f);
        head.texOffs(24, 0).addBox(-3.0f, 0.0f, -10.0f, 6, 4, 2, f);
        head.texOffs(40, 0).addBox(-5.0f, -5.0f, -6.0f, 1, 2, 2, f);
        head.mirror = true;
        head.texOffs(40, 0).addBox(4.0f, -5.0f, -6.0f, 1, 2, 2, f);
        ModelRenderer tusks = new ModelRenderer(this, 0, 0);
        tusks.addBox(-4.0f, 2.0f, -11.0f, 1, 1, 2, f);
        tusks.texOffs(1, 1).addBox(-4.0f, 1.0f, -11.5f, 1, 1, 1, f);
        tusks.mirror = true;
        tusks.texOffs(0, 0).addBox(3.0f, 2.0f, -11.0f, 1, 1, 2, f);
        tusks.texOffs(1, 1).addBox(3.0f, 1.0f, -11.5f, 1, 1, 1, f);
        head.addChild(tusks);
    }
}
