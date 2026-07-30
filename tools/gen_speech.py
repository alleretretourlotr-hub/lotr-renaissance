#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""Lot speechbanks FR : 16 banques + cablage getSpeechBank."""
import os, re

OUT = "/home/claude/lot-speech"
SP = f"{OUT}/src/main/resources/assets/lotr/speech"
NPC_SRC = ["/home/claude/megalot/src/main/java/fr/alleretretour/lotr/entity/npc",
           "/home/claude/lot-ranged/src/main/java/fr/alleretretour/lotr/entity/npc"]
NPC_OUT = f"{OUT}/src/main/java/fr/alleretretour/lotr/entity/npc"

B = {
"dwarf/warrior": {
 "friendly": ["Baruk Khazâd ! Les haches des Nains sont avec vous, #.",
  "Bienvenue, ami. Peu d'étrangers gagnent la confiance du Peuple de Durin.",
  "L'or et les gemmes d'Erebor n'ont pas d'égal, croyez-m'en.",
  "Un Nain n'oublie jamais une dette... ni un affront.",
  "Que votre barbe pousse toujours plus longue, #.",
  "Les salles de nos pères résonnaient de chants et de marteaux. Elles résonneront encore."],
 "neutral": ["Que voulez-vous ? J'ai de l'ouvrage qui m'attend.",
  "On ne creuse pas la pierre avec des paroles.",
  "Passez votre chemin, étranger, et il ne vous sera fait aucun mal.",
  "Je ne vous connais pas. Et un Nain se méfie de ce qu'il ne connaît pas.",
  "Nos portes ne s'ouvrent pas au premier venu."],
 "hostile": ["Baruk Khazâd ! Khazâd ai-mênu !",
  "Vous ne toucherez pas à l'or de mon peuple !",
  "Ma hache a fendu plus dur que votre crâne, #.",
  "Les Nains n'oublient rien ! Et surtout pas vous !",
  "Venez donc goûter l'acier des forges d'Erebor !"]},
"blue_mountains/warrior": {
 "friendly": ["Bienvenue, voyageur. Les Montagnes Bleues accueillent les amis des Nains.",
  "Nos forges chantent depuis l'aube du monde, bien avant Erebor.",
  "Le clan de Thorin lui-même a vécu ici, savez-vous ?",
  "Que la pierre vous soit douce, #.",
  "Peu de voyageurs poussent jusqu'aux Ered Luin. Vous avez du cran."],
 "neutral": ["Les cols sont dangereux par ce temps. Restez sur le sentier.",
  "Nous commerçons avec ceux qui montrent patte blanche. Rien de plus.",
  "Un étranger dans nos montagnes... Voilà qui est rare.",
  "Parlez vite, la forge n'attend pas."],
 "hostile": ["Vous n'auriez jamais dû gravir ces montagnes, #.",
  "Les Ered Luin seront votre tombeau !",
  "Ma hache réclame son dû !",
  "On ne pille pas les Montagnes Bleues impunément !"]},
"hobbit/civilian": {
 "friendly": ["Belle journée, n'est-ce pas ? Parfaite pour un second petit-déjeuner.",
  "Bienvenue dans la Comté, #. Vous restez pour le dîner ?",
  "Ma vieille Gaffer disait toujours : méfie-toi des ennuis plus grands que toi.",
  "Vous n'auriez pas vu mes champignons ? Je les avais posés juste là...",
  "Un peu d'herbe à pipe du Quartier Sud ? C'est la meilleure, foi de Hobbit.",
  "Les aventures, très peu pour moi. Mais racontez toujours, ça m'intrigue."],
 "neutral": ["Un Grand ! Enfin, je veux dire... bonjour, monsieur le Grand.",
  "On ne voit pas souvent des gens de votre espèce par ici.",
  "Restez sur la route, voulez-vous ? Mes choux en ont assez vu.",
  "Pas d'ennuis, hein ? On n'aime pas beaucoup les ennuis, par chez nous."],
 "hostile": ["À l'aide ! Aux voleurs ! Au feu ! Des brigands !",
  "Restez loin de moi, espèce de grand malotru !",
  "Les Frontaliers ! Appelez les Frontaliers !",
  "Vous êtes pire que les loups de l'Hiver Terrible !"]},
"bree/civilian": {
 "friendly": ["Bienvenue à Bree, l'étranger. Le Poney Fringant a toujours une chambre.",
  "Étrange temps, étranges voyageurs... mais vous m'avez l'air convenable, #.",
  "Grands et Petits vivent côte à côte ici, et personne ne s'en plaint.",
  "Une chope de bière et les nouvelles de la route, voilà ce qui fait une bonne soirée.",
  "Vous venez de loin ? On entend de drôles d'histoires, ces temps-ci."],
 "neutral": ["Passez votre chemin, on ne cherche pas d'histoires.",
  "Les gens de Bree se mêlent de leurs affaires. Faites-en autant.",
  "Encore un vagabond du Sud... Les routes en sont pleines.",
  "Je vous ai à l'oeil, l'étranger."],
 "hostile": ["Au garde ! Il y a un brigand dans nos murs !",
  "On sait s'occuper des malandrins, à Bree !",
  "Vous finirez au poste, #, croyez-moi !",
  "Dehors ! Bree n'a que faire des gens de votre espèce !"]},
"ranger/warrior": {
 "friendly": ["Peu de gens saluent un Rôdeur. Vous avez le regard juste, #.",
  "Nous veillons sur ces terres depuis la chute d'Arnor. Sans remerciements, sans repos.",
  "Le sang de Númenor coule encore dans nos veines, si mince soit-il.",
  "Les routes sont plus sombres qu'elles n'en ont l'air. Restez sur vos gardes.",
  "Un jour, le Roi reviendra. Ce jour-là, notre longue veille prendra fin."],
 "neutral": ["Passez votre chemin et oubliez m'avoir vu.",
  "Je ne suis qu'une ombre sur la route. Rien de plus.",
  "Ces terres gardent des tombes plus anciennes que vos ancêtres.",
  "Le Nord n'est sûr pour personne. Pas même pour vous."],
 "hostile": ["Vous avez trahi la mémoire d'Arnor. Vous le paierez.",
  "Ma flèche vous trouvera avant que vous ne me voyiez, #.",
  "Les héritiers de Númenor ne pardonnent pas aux serviteurs de l'Ombre.",
  "C'est ici que finit votre route."]},
"dale/warrior": {
 "friendly": ["Bienvenue à Dale, cité des cloches et des marchés, #.",
  "Depuis que Bard a terrassé le dragon, notre cité renaît de ses cendres.",
  "Nos archers sont les héritiers de la Flèche Noire. Ne l'oubliez jamais.",
  "Le commerce avec Erebor fait notre fortune, et notre amitié fait notre force.",
  "Goûtez donc nos jouets et nos vins ! Rien de tel de l'autre côté des Monts."],
 "neutral": ["Le marché est par là, étranger. Les ennuis sont dehors.",
  "Dale accueille tout le monde, mais surveille chacun.",
  "Payez vos taxes et personne ne vous cherchera querelle.",
  "Le Roi sous la Montagne a l'oeil sur nous. Et nous sur vous."],
 "hostile": ["Aux armes ! Dale ne tombera pas deux fois !",
  "Ma flèche est plus noire que votre coeur, #.",
  "Vous brûlerez comme Smaug a brûlé !",
  "Pour Bard ! Pour Dale !"]},
"dorwinion/warrior": {
 "friendly": ["Bienvenue au Dorwinion, pays des vignes et du soleil, #.",
  "Nos vins voyagent jusqu'à la table du Roi Thranduil lui-même.",
  "Hommes et Elfes cultivent ces terres ensemble. C'est notre fierté.",
  "Restez donc pour les vendanges, on manque toujours de bras.",
  "La Mer de Rhûn scintille, le vin coule : que demander de plus ?"],
 "neutral": ["Les vignes ne se gardent pas toutes seules, faites vite.",
  "Acheteur ou curieux ? Les deux paient, ici.",
  "Le Dorwinion est en paix. Tâchez qu'il le reste.",
  "L'Est n'envoie pas que du bon vent, ces temps-ci."],
 "hostile": ["Vous ne piétinerez pas nos vignes, brigand !",
  "Mon carreau est plus vif que votre lame, #.",
  "Le Dorwinion défend ce qui lui appartient !",
  "Vous finirez engraisser nos ceps !"]},
"angmar/orc": {
 "friendly": ["Le Roi-Sorcier voit d'un bon oeil tes carnages, #.",
  "Angmar se relève ! Le Nord tremblera encore.",
  "Toi, t'es de la bonne graine de tueur. Ça me plaît.",
  "Les os d'Arnor blanchissent sous nos pieds. Bientôt, ceux de leurs héritiers.",
  "Sers bien Angmar, et Angmar te le rendra... peut-être."],
 "neutral": ["Qu'est-ce que tu veux, la chair fraîche ?",
  "Passe ton chemin avant que je change d'humeur.",
  "T'es ni ami, ni viande. Pour l'instant.",
  "Le Carn Dûm n'aime pas les curieux."],
 "hostile": ["De la viande fraîche pour les loups d'Angmar !",
  "Le Roi-Sorcier réclame ta carcasse, # !",
  "On va te faire hurler comme hurlaient les Dúnedain !",
  "Crève, vermine du Sud !"]},
"gundabad/orc": {
 "friendly": ["Gundabad reconnaît les siens. T'as du sang sur les mains, ça se voit.",
  "Le Mont est à nous ! Qu'ils viennent le reprendre, les barbus !",
  "Toi et moi, on pourrait piller du Nain ensemble, #.",
  "Plus tu tues, plus on t'aime. C'est simple, chez nous.",
  "Les tunnels de Gundabad n'ont pas de fin. Comme notre faim."],
 "neutral": ["Grrr... T'approche pas trop de mes affaires.",
  "T'as de la chance que j'aie déjà mangé.",
  "Un pas de plus et on verra qui rigole.",
  "Qu'est-ce tu regardes, toi ?"],
 "hostile": ["De la chair à trancher ! Sonnez les tambours !",
  "Gundabad aura ta peau, # !",
  "Les Nains d'abord, toi ensuite ! Non... toi d'abord !",
  "On va voir de quelle couleur tu saignes !"]},
"dol_guldur/orc": {
 "friendly": ["L'ombre de Dol Guldur s'étend, et toi avec elle, #.",
  "Le Nécromancien récompense ceux qui servent bien. Continue.",
  "La forêt pourrit, les Elfes reculent. Bientôt tout sera à nous.",
  "T'entends les arbres crever ? Douce musique.",
  "Sers l'Ombre de la colline, et l'Ombre te gardera."],
 "neutral": ["La forêt a des yeux, étranger. Les nôtres.",
  "Passe, mais l'Ombre n'oublie pas les visages.",
  "T'es encore vivant ? Étonnant, par ici.",
  "Vermenouze n'est pas un endroit pour les tiens."],
 "hostile": ["L'Ombre de la colline te réclame, # !",
  "Les araignées mangeront ce qu'on laissera de toi !",
  "Crève, comme crèvent les arbres de cette forêt !",
  "Pour le Nécromancien !"]},
"dunland/warrior": {
 "friendly": ["Les Hommes Sauvages saluent un vrai guerrier, #.",
  "Les Forgoil nous ont volé nos terres. Un jour, on reprendra tout.",
  "Cinq cents ans de haine, ça se transmet de père en fils.",
  "T'es pas un homme de paille, toi. T'es des nôtres.",
  "Le Pays de Dun est rude, mais il est à nous."],
 "neutral": ["Qu'est-ce qu'un étranger vient chercher dans nos collines ?",
  "On n'aime pas les visages nouveaux, par ici.",
  "Passe ton chemin avant la tombée de la nuit.",
  "T'es pas un Forgoil, au moins ? T'as pas intérêt."],
 "hostile": ["Mort aux Têtes-de-Paille et à leurs amis !",
  "Le sang appelle le sang, # !",
  "Nos ancêtres réclament vengeance ! Tu paieras pour eux !",
  "Les collines de Dun boiront ton sang !"]},
"near_harad/warrior": {
 "friendly": ["Que les étoiles du Sud veillent sur toi, #.",
  "Le Harad est vaste, brûlant et fier. Comme ses fils.",
  "Nos caravanes traversent des déserts que tu ne peux imaginer.",
  "Un ami du Harad ne marche jamais seul.",
  "Le serpent sur nos bannières ne dort jamais. Nous non plus."],
 "neutral": ["Le soleil du Harad ne pardonne pas aux imprudents.",
  "Tes pièces d'abord, tes questions ensuite.",
  "Le désert engloutit ceux qui s'égarent. Souviens-t'en.",
  "Que veux-tu, homme du Nord ?"],
 "hostile": ["Le Serpent frappe plus vite que ton ombre, # !",
  "Ton sang arrosera le sable du Harad !",
  "Les fils du désert ne connaissent pas la pitié !",
  "Meurs, chien du Nord !"]},
"rhun/warrior": {
 "friendly": ["Rhûn te salue, guerrier. Nos chars écrasent nos ennemis communs.",
  "L'or de l'Est brille plus fort que le soleil du Couchant, #.",
  "Les Balchoth, les Gens-des-Chariots... tous nos pères furent conquérants.",
  "Bois le kumis avec nous, et parlons de guerre.",
  "La Mer de Rhûn n'a qu'un maître : nous."],
 "neutral": ["L'Ouest envoie ses espions... ou ses marchands ? Difficile à dire.",
  "Parle, mais choisis tes mots comme on choisit ses flèches.",
  "Les steppes n'appartiennent à personne. Sauf à nous.",
  "Qu'est-ce qui t'amène si loin du Couchant ?"],
 "hostile": ["Les chars de Rhûn te piétineront, # !",
  "L'Est se souvient de chaque affront !",
  "Ta tête ornera ma selle !",
  "Pour le Grand Oeil et pour Rhûn !"]},
"moredain/warrior": {
 "friendly": ["Le peuple du lion salue ta force, #.",
  "Les savanes du Harad Lointain forgent des guerriers, pas des pleureuses.",
  "Tue un lion à la lance, et tu seras un homme parmi nous.",
  "Nos huttes sont ouvertes à ceux qui ont prouvé leur valeur.",
  "Le tambour parle ce soir. Il dit du bien de toi."],
 "neutral": ["Un étranger... Les lions aussi observent avant de juger.",
  "La savane est à nous. Marche avec respect.",
  "Que cherches-tu si loin de tes terres pâles ?",
  "Le soleil tape fort. Les esprits aussi."],
 "hostile": ["Le lion a faim, et te voilà, # !",
  "Ta peau ornera ma hutte !",
  "Les Moredain ne reculent jamais !",
  "Cours ! La chasse n'en sera que meilleure !"]},
"tauredain/warrior": {
 "friendly": ["Le peuple de la jungle t'accueille, #. C'est un honneur rare.",
  "Nos pyramides se dressaient avant que tes ancêtres ne sachent bâtir.",
  "La jungle nourrit ceux qu'elle accepte, et dévore les autres.",
  "Les esprits des arbres murmurent ton nom avec bienveillance.",
  "Peu d'étrangers voient nos cités d'or et vivent pour le raconter."],
 "neutral": ["La jungle a mille yeux, étranger. Tous te regardent.",
  "Ne touche à rien. Tout ici appartient aux esprits.",
  "Ta peau pâle brille trop fort sous notre soleil.",
  "Repars avant que la nuit tombe. La jungle chasse la nuit."],
 "hostile": ["Le poison de la jungle coule déjà vers toi, # !",
  "Les esprits réclament ton sang !",
  "Ta tête séchera sur nos pyramides !",
  "La jungle t'engloutira, profanateur !"]},
"half_troll/warrior": {
 "friendly": ["Toi cogner fort. Moi respecter ça, #.",
  "Perak-trolls pas aimer grand monde. Toi, ça va.",
  "Le sang du Nord et du troll coule en nous. Double force !",
  "Viens boire. Boisson forte comme nous.",
  "Toi ami. Ennemis de toi, ennemis de moi. Simple."],
 "neutral": ["Toi petit. Toi fragile. Toi prudent, hein ?",
  "Pas toucher mes affaires. Compris ?",
  "Grr... Toi vouloir quoi ?",
  "Moi pas d'humeur. Revenir plus tard. Ou jamais."],
 "hostile": ["Moi écraser toi, # !",
  "Petit homme, gros massacre !",
  "Toi crier bientôt. Moi rire déjà !",
  "CHAIR ! OS ! MIETTES !"]},
}

# reg -> banque
WIRE = {
 "dwarf": "dwarf/warrior", "dwarf_warrior": "dwarf/warrior", "dwarf_commander": "dwarf/warrior",
 "dwarf_axe_thrower": "dwarf/warrior",
 "blue_dwarf_warrior": "blue_mountains/warrior", "blue_dwarf_axe_thrower": "blue_mountains/warrior",
 "hobbit": "hobbit/civilian", "hobbit_bounder": "hobbit/civilian",
 "bree_man": "bree/civilian", "bree_guard": "bree/civilian",
 "ranger_north": "ranger/warrior", "ranger_north_captain": "ranger/warrior",
 "dale_soldier": "dale/warrior", "dale_archer": "dale/warrior", "dale_levyman": "dale/warrior",
 "dorwinion_guard": "dorwinion/warrior", "dorwinion_elf_warrior": "dorwinion/warrior",
 "dorwinion_elf_archer": "dorwinion/warrior", "dorwinion_crossbower": "dorwinion/warrior",
 "angmar_orc": "angmar/orc", "angmar_orc_archer": "angmar/orc", "angmar_hillman_warrior": "angmar/orc",
 "gundabad_orc": "gundabad/orc", "gundabad_orc_archer": "gundabad/orc", "gundabad_uruk": "gundabad/orc",
 "dol_guldur_orc": "dol_guldur/orc", "dol_guldur_orc_archer": "dol_guldur/orc",
 "dunlending": "dunland/warrior", "dunlending_warrior": "dunland/warrior",
 "dunlending_berserker": "dunland/warrior",
 "near_haradrim_warrior": "near_harad/warrior", "near_haradrim_archer": "near_harad/warrior",
 "corsair": "near_harad/warrior", "umbar_warrior": "near_harad/warrior", "umbar_archer": "near_harad/warrior",
 "easterling_warrior": "rhun/warrior", "easterling_archer": "rhun/warrior",
 "easterling_gold_warrior": "rhun/warrior",
 "moredain_warrior": "moredain/warrior", "moredain_huntsman": "moredain/warrior",
 "tauredain_warrior": "tauredain/warrior", "tauredain_blowgunner": "tauredain/warrior",
 "half_troll": "half_troll/warrior", "half_troll_warrior": "half_troll/warrior",
}

# 1) fichiers de repliques
count = 0
for bank, moods in B.items():
    d = f"{SP}/{bank}"
    os.makedirs(d, exist_ok=True)
    for mood, lines in moods.items():
        open(f"{d}/{mood}.txt", "w", encoding="utf-8").write("\n".join(lines) + "\n")
        count += len(lines)
print(f"{len(B)} banques, {count} repliques ecrites")

# 2) cablage getSpeechBank dans les classes
os.makedirs(NPC_OUT, exist_ok=True)
def camel(reg):
    return "".join(w.capitalize() for w in reg.split("_"))
wired = 0
for reg, bank in WIRE.items():
    cls = camel(reg)
    path = None
    for base in NPC_SRC:
        p = f"{base}/LOTREntity{cls}.java"
        if os.path.exists(p):
            path = p
            break
    assert path, f"classe introuvable pour {reg}"
    src = open(path, encoding="utf-8").read()
    if "getSpeechBank" in src:
        continue
    anchor = "    public static AttributeModifierMap.MutableAttribute createAttributes() {"
    assert anchor in src, reg
    src = src.replace(anchor,
        f'    @Override\n    protected String getSpeechBank() {{\n        return "{bank}";\n    }}\n\n' + anchor, 1)
    open(f"{NPC_OUT}/LOTREntity{cls}.java", "w", encoding="utf-8").write(src)
    wired += 1
print(f"{wired} classes cablees")
