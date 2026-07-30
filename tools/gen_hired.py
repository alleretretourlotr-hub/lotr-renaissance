#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""Banques 'hired' VF des factions ajoutees depuis le lot speechbanks."""
import os

OUT = "/home/claude/lot12/src/main/resources/assets/lotr/speech"

HIRED = {
 "dwarf/warrior": [
  "Baruk Khazad ! Les haches des Nains sont avec vous, # !",
  "Menez-nous au combat, et nous ferons trembler la pierre.",
  "Un Nain paye n'est jamais un Nain qui rechigne.",
  "Nos haches sont a votre service, #.",
  "Que les ennemis du Peuple de Durin s'ecartent de notre route !",
  "Pour Erebor ! Pour la Montagne Solitaire !",
  "Vous verrez ce que vaut un Nain au combat, #.",
  "Combattre coude a coude, voila qui vaut tout l'or d'un tresor.",
 ],
 "blue_mountains/warrior": [
  "Les Montagnes Bleues repondent a votre appel, #.",
  "Nos haches sont affutees, menez-nous a l'ennemi !",
  "Pour les Ered Luin ! Pour les salles de nos peres !",
  "Un contrat est un contrat : nous nous battrons jusqu'au bout.",
  "Les forges bleues ont trempe cette lame. Elle ne rompra pas.",
  "Nous suivrons votre banniere, #.",
 ],
 "hobbit/civilian": [
  "Je... je vais essayer d'etre courageux, #.",
  "Ma mere m'avait pourtant dit de rester au Comte !",
  "On rentre pour le the, dites ? Enfin, apres la bagarre.",
  "Ne vous fiez pas a ma taille, je vise tres bien !",
  "Pour le Comte ! Et pour un bon repas au retour !",
  "Je n'aurais jamais cru vivre une aventure pareille.",
 ],
 "bree/civilian": [
  "Les gens de Bree savent tenir un baton, #.",
  "Je vous suis, mais ne me demandez pas d'aimer ca.",
  "Nous defendrons la route et le village, comme toujours.",
  "Un peu d'argent, un peu de courage, et nous voila en route.",
  "Bree n'oublie pas ses amis, #.",
 ],
 "ranger/warrior": [
  "Nous marcherons dans l'ombre pour que d'autres vivent dans la lumiere.",
  "Mon arc est a vous, #.",
  "Les Rodeurs ne demandent ni gloire ni remerciements.",
  "Menez, je vous couvre depuis les fourres.",
  "Peu nous voient passer. Nos ennemis, jamais.",
  "Le Nord sera garde, quoi qu'il en coute.",
 ],
 "dale/warrior": [
  "Les hommes de Dale n'ont pas peur, #.",
  "Nous nous souvenons du Dragon. Rien ne nous effraie plus.",
  "Pour Dale ! Pour Esgaroth !",
  "Nos arcs sont a votre service.",
  "Menez-nous, et la cite chantera nos noms.",
 ],
 "dorwinion/warrior": [
  "Le vin coulera mieux apres la victoire, #.",
  "Dorwinion vous accorde ses lances.",
  "Nous protegeons les vignes et ceux qui les aiment.",
  "Menez-nous, mais promettez-nous une bonne coupe au retour !",
  "Nos ennemis gouteront autre chose que du vin.",
 ],
 "rhun/warrior": [
  "Les chars du Rhun roulent avec vous, #.",
  "Nous servons qui paie et qui commande bien.",
  "L'Orient n'oublie ni ses amis ni ses dettes.",
  "Nos lames sont a vous jusqu'au dernier combat.",
  "Pour les seigneurs de guerre du Rhun !",
 ],
 "near_harad/warrior": [
  "Le desert nous a endurcis, #. L'ennemi ne tiendra pas.",
  "Nos cimeterres sont a votre service.",
  "Que le soleil brule nos ennemis et epargne nos amis.",
  "Menez-nous, seigneur, et le sable boira leur sang.",
  "Le Sud se souviendra de cette bataille.",
 ],
 "moredain/warrior": [
  "La chasse commence, #.",
  "Nos lances connaissent la chair et l'os.",
  "Les Moredain marchent avec vous.",
  "Nous suivrons votre piste comme celle du lion.",
  "Le tambour du village battra pour cette victoire.",
 ],
 "tauredain/warrior": [
  "La foret nous protege, #. Elle protegera aussi nos allies.",
  "Nos fleches sont enduites. Un souffle suffit.",
  "Les Taurethrim combattent a vos cotes.",
  "Menez-nous sous le couvert des arbres.",
  "Les dieux de la jungle veillent sur cette bataille.",
 ],
 "dunland/warrior": [
  "Les Hommes des collines n'oublient jamais une dette, #.",
  "Nous nous battons pour la terre qu'on nous a prise.",
  "Menez-nous contre les Tetes-de-Paille !",
  "Ma hache est a vous, etranger.",
  "Dun se leve, et sa colere est longue.",
 ],
 "half_troll/warrior": [
  "Nous ecraserons vos ennemis, #.",
  "Chair fraiche pour les Semi-trolls !",
  "Grand. Fort. A votre service.",
  "Nos massues parlent plus fort que les mots.",
  "Menez. Nous frappons.",
 ],
 "angmar/orc": [
  "Le Roi-Sorcier a des serviteurs partout, asticot. Aujourd'hui, c'est vous le chef.",
  "Angmar se leve encore ! Menez-nous a la tuerie !",
  "Le froid du Nord ne vaut rien face a notre haine.",
  "Vous payez bien, tark. On vous suit.",
  "Que les os craquent et que le sang gele !",
 ],
 "gundabad/orc": [
  "Gundabad ! Gundabad ! Les montagnes sont a nous !",
  "On vous suit, mais gardez la viande a portee, chef.",
  "Nos galeries s'ouvriront sous leurs pieds !",
  "Ecrasez les Nains ! Ecrasez-les tous !",
  "Vos ordres, chef ? Vite, j'ai faim.",
 ],
 "dol_guldur/orc": [
  "Les ombres de la Foret Noire marchent avec vous.",
  "Le Necromancien sait ce que nous faisons ici, tark.",
  "Nos toiles attendent leur pature.",
  "Menez-nous sous les arbres morts !",
  "Que la peur les prenne avant nos lames !",
 ],
}

n = 0
for path, lines in HIRED.items():
    d = os.path.join(OUT, path)
    os.makedirs(d, exist_ok=True)
    open(os.path.join(d, "hired.txt"), "w", encoding="utf-8").write("\n".join(lines) + "\n")
    n += len(lines)
print(f"{len(HIRED)} banques hired, {n} repliques")
