package fr.alleretretour.lotr.client.model;

/**
 * Port de lotr.client.model.LOTRModelWingedHelmet (casque aile du Gondor).
 * Herite du casque gondorien et ajoute les deux ailes laterales.
 */
public class LOTRModelWingedHelmet extends LOTRModelGondorHelmet {

    public LOTRModelWingedHelmet(float f) {
        super(f);
        head.texOffs(32, 8).addBox(-6.0f - f, -4.0f, -0.5f, 2, 2, 1, 0.0f);
        head.texOffs(38, 8).addBox(-7.0f - f, -13.0f, -0.5f, 3, 9, 1, 0.0f);
        head.texOffs(46, 8).addBox(-5.5f - f, -17.0f, -0.5f, 2, 4, 1, 0.0f);
        head.mirror = true;
        head.texOffs(32, 8).addBox(4.0f + f, -4.0f, -0.5f, 2, 2, 1, 0.0f);
        head.texOffs(38, 8).addBox(4.0f + f, -13.0f, -0.5f, 3, 9, 1, 0.0f);
        head.texOffs(46, 8).addBox(3.5f + f, -17.0f, -0.5f, 2, 4, 1, 0.0f);
    }
}
