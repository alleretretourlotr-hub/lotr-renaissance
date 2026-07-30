#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""VF integrale des speechbanks des 6 factions du port initial."""
import os

OUT = "/home/claude/lot-vf/src/main/resources/assets/lotr/speech"

GONDOR_SOLDIER_F = [
 "Bienvenue au Gondor, #.",
 "Nous sommes des hommes de parole, nous autres du Gondor.",
 "Je rêve de revoir un Roi sur le trône du Gondor.",
 "Les temps sont sombres. Une grande puissance se lève à l'Est.",
 "Je suis un soldat du Gondor.",
 "Le Gondor n'est plus ce qu'il était, hélas.",
 "Dommage que vous n'ayez pas connu le Gondor en des jours plus heureux, #.",
 "Bien le bonjour, # ! Quelles nouvelles apportez-vous de par le vaste monde ?",
 "On raconte que les Rois reviendront un jour régner sur le Gondor. Mais ce ne sont que des contes... n'est-ce pas ?",
 "Un mal se réveille derrière les murailles du Mordor. Je crains qu'il ne soit notre perte.",
 "Sans les braves Hommes du Gondor, le monde aurait succombé à l'Ombre depuis bien longtemps.",
 "J'ai passé ma vie à m'entraîner à tuer de l'Orque. Mais que les Valar me gardent si un Troll vient frapper à la porte !",
 "Vous tombez un bon jour, # : les Orques n'ont pas pillé nos terres depuis plus d'une semaine.",
 "Je plains les rôdeurs de l'Ithilien, #. Ils tomberont les premiers.",
 "J'ai vu de mes yeux les ruines d'Osgiliath, jadis notre capitale. Cinq cents ans de deuil silencieux.",
 "Avez-vous vu l'Argonath, # ? Voilà un spectacle qui ne s'oublie pas !",
 "L'Arbre Blanc s'est desséché depuis longtemps, mais nos coeurs battent encore avec droiture.",
 "Si les feux d'alarme de la Tour Blanche s'allument un jour, nous saurons que notre heure est venue.",
 "Maudits soient les Suderons et leurs épées courbes !",
 "De sombres rumeurs viennent du Harad. On dit que les Suderons massent leurs forces au-delà du Harnen.",
]
GONDOR_SOLDIER_HIRED = [
 "Je me bats pour le Gondor, #.",
 "Que nos exploits rendent au Gondor sa gloire d'antan !",
 "Les ennemis du Gondor tomberont devant nous !",
 "Pour le Gondor !",
 "Nos ennemis ont raison de craindre la puissance du Gondor, # !",
 "Vous êtes un véritable ami du Gondor, #.",
 "Nous vous suivrons à travers la mort et la ruine !",
 "Fiers de combattre aux côtés d'un si grand guerrier !",
 "Pour le Gondor ! Pour tout ce qui est bon en ce monde !",
 "La vermine du Mordor va connaître notre courroux !",
]
GONDOR_SOLDIER_HOSTILE = [
 "Vous n'irez pas plus loin. Vous n'entrerez pas au Gondor !",
 "Fils du Gondor ! Tenez vos positions !",
 "Pour le Gondor !",
 "Vous êtes un ennemi du trône du Gondor, # !",
 "Des ennemis sont entrés au Gondor !",
 "Par l'Arbre Blanc, je vous verrai tomber !",
 "Votre mort sera prompte, # !",
 "Pas de pitié pour les ennemis du Gondor !",
 "Vous ne verrez pas la fin de ce jour, serviteur de l'Ombre !",
 "Hors de nos terres, immonde vermine du Mordor !",
 "Au nom des rois de jadis, je vous détruirai !",
]
GONDOR_CAPTAIN_F = [
 "Cherchez-vous à commander les armées du Gondor, # ?",
 "Les forces du Gondor peuvent être vôtres pour quelques pièces d'argent.",
 "Nos soldats doivent être payés, # !",
 "Apportez des pièces d'argent, et vous aurez des soldats.",
 "Les Hommes du Gondor vous respectent assez pour vous suivre au combat, #.",
 "Le peuple du Gondor a confiance en vous, #. Nos vaillants guerriers sont à vos ordres.",
 "Pour quelques dizaines de pièces, vous commanderez des soldats loyaux et valeureux.",
 "Si vous voulez mener mes troupes au combat, il faudra les payer !",
]
GONDOR_CAPTAIN_N = [
 "Seuls les plus grands amis du Gondor peuvent commander ses armées, #.",
 "Pour commander les forces du Gondor, prouvez d'abord que nous pouvons vous faire confiance.",
 "Êtes-vous ami ou ennemi, # ?",
 "On ne commande pas simplement les soldats du Gondor sans s'être d'abord fait un nom.",
 "Votre renom n'est pas encore assez grand pour mener nos soldats au combat, #.",
 "Seul un véritable ami du Gondor peut mener nos vaillants soldats à la bataille.",
 "Accomplissez de hauts faits pour le peuple du Gondor, et je vous confierai mes hommes.",
]
GONDOR_BS_F = [
 "Besoin d'équipement ? Apportez-moi des pièces d'argent.",
 "Bienvenue, # ! Que puis-je forger pour vous ?",
 "Les matériaux se font toujours rares, par les temps qui courent.",
 "Je vois que vous êtes un ami du Gondor, #.",
 "Je cherche toujours un apprenti, au fait...",
 "Je forge les meilleures armes de tout le Gondor !",
 "Bien le bonjour, # ! Un peu de bel équipement gondorien ?",
 "Sombres jours... Les matériaux sont chers et difficiles à trouver.",
]
GONDOR_BS_N = [
 "Il vous faudra gagner mon respect avant de commercer avec moi.",
 "Prouvez qu'on peut vous faire confiance, et je vous ouvrirai mon étal.",
 "Je ne sais trop quoi penser de vous, #.",
 "Êtes-vous un ami du Gondor, ou son ennemi ?",
 "Je ne commerce qu'avec les amis éprouvés du Gondor, #.",
 "Je ne traite pas avec le premier étranger venu...",
 "Prouvez votre loyauté au Gondor, et alors nous ferons affaire !",
]
GONDOR_BS_H = [
 "Je ne forgerai rien pour vous !",
 "Vous êtes un ennemi du Gondor, # !",
 "Que font les gens de votre espèce au Gondor ?",
 "Hors de ma vue, chien !",
 "Je refuse de commercer avec des gredins de votre acabit, #.",
 "Je ne forge pas pour les serviteurs du mal ! Disparaissez !",
 "Vermine du Mordor ! Comment osez-vous entrer au Gondor ?",
]
ROHAN_WARRIOR_F = [
 "Bienvenue, #. Qu'est-ce qui vous amène au pays de Rohan ?",
 "Quelles affaires vous amènent dans la Marche des Cavaliers, # ?",
 "N'oubliez pas de nourrir les chevaux !",
 "Ces terres sont troublées, #. Les Orques franchissent souvent nos frontières.",
 "Salut à vous, # ! Nous sommes les cavaliers du Rohan.",
 "Ce sont les terres de nos pères, et des pères de nos pères. Notre devoir est de les garder.",
 "Les chevaux du Rohan sont les plus beaux de toute la Terre du Milieu.",
 "Gare aux Uruk-hai. Ils viennent souvent en bandes, ne laissant que désolation derrière eux.",
 "Le loup qu'on entend est pire que l'Orque qu'on redoute.",
 "Avez-vous entendu les chants sur Helm Poing-de-Marteau ? On dit qu'on le trouva mort dans la neige, le sang des Dunlendings encore gelé sur ses mains.",
 "Les Hommes sauvages de Dun nous appellent Têtes-de-Paille. Eh bien, #, je préfère la paille sur la tête que la boue jusqu'aux genoux.",
 "Un bon cheval mérite du bon foin, #. Alors n'oubliez pas de les nourrir !",
 "Êtes-vous de mèche avec la Sorcière du Bois Doré ? On a toujours tissé des toiles de mensonges à Dwimordene.",
 "Nous avons une vieille querelle avec les Corsaires du Sud.",
 "Les jours sont sombres en Stoningland.",
]
ROHAN_WARRIOR_HIRED = [
 "Pour le Rohan ! Pour notre peuple !",
 "Que ce soit l'heure où nous tirons l'épée ensemble !",
 "Pour la Marche des Cavaliers !",
 "Nos ennemis seront réduits en poussière !",
 "Nous gagnerons bien de la gloire sur le champ de bataille, #.",
 "Les ennemis du Rohan trembleront sous nos sabots !",
 "Nous chevauchons ensemble. Pour la gloire. Pour le Rohan !",
 "Allons chasser de l'Orque !",
 "Pour le Roi du Rohan ! Pour Edoras !",
]
ROHAN_WARRIOR_HOSTILE = [
 "Les Rohirrim ne souffriront pas votre présence sur ces terres, # !",
 "Vous n'êtes pas le bienvenu dans la Marche, # !",
 "Vous auriez dû fuir ces terres depuis longtemps !",
 "Tremblez, # !",
 "Bien folle fut votre idée de vous aventurer sur nos terres !",
 "Pas de pitié pour les ennemis du Rohan !",
 "Vous connaîtrez un sort funeste, serviteur de l'Isengard !",
 "Partez, ou goûtez la pointe de nos lances !",
 "Vous paierez pour vos crimes, # !",
 "La traîtrise de l'Isengard n'est pas la bienvenue ici.",
]
ROHAN_AVENGE = [
 "Nous ne laissons pas vivre ceux qui mettent à mort les chevaux !",
 "Vos actes vous ont valu un ennemi redoutable, # !",
 "Ne croyez pas pouvoir traiter les montures du Rohan avec une telle cruauté !",
 "Quelle diablerie est-ce là ? Un tueur de chevaux ? Qu'on le mette à mort !",
 "Nul ami du Rohan ne commettrait un tel crime !",
 "Fuyez ces terres, #, ennemi des chevaux du Rohan !",
 "Vous avez tué un cheval du Rohan ? Votre vie est forfaite !",
 "Quelle folie vous a poussé à cette diablerie ?",
 "Vous êtes vraiment un serviteur du mal !",
 "Vous périrez pour ce crime !",
]
ROHAN_MARSHAL_F = [
 "Cherchez-vous à engager un guerrier de la Marche, # ?",
 "Nos guerriers sont les meilleurs cavaliers de toute la Terre du Milieu.",
 "Vous avez gagné le respect de notre peuple, #. Voulez-vous engager des guerriers ?",
 "Contre quelques pièces d'argent, mes Rohirrim vous suivront au combat avec joie.",
 "Nous sommes de nobles guerriers, mais même les nobles guerriers veulent leur solde.",
 "J'ai de vaillants hommes à engager. Avez-vous des pièces d'argent ?",
 "Vous êtes un véritable ami du Rohan. Mes hommes seront fiers de combattre à vos côtés.",
]
ROHAN_MARSHAL_N = [
 "Seuls les plus respectés de nos terres peuvent commander les cavaliers du Rohan, #.",
 "Pour vous attacher les Rohirrim, prouvez d'abord qu'on peut vous faire confiance.",
 "Vous, commander les Rohirrim ? Vous n'êtes pas un grand seigneur du Rohan, #.",
 "Tant que votre nom ne sera pas chanté aux côtés des héros d'antan, vous ne commanderez pas nos guerriers, #.",
 "Nous ne pouvons combattre aux côtés de quelqu'un d'aussi jeune en hauts faits que vous, #.",
 "Mes hommes ne suivront pas un étranger à la guerre !",
 "Faites d'abord vos preuves auprès de la Marche, et alors peut-être vous confierai-je mes hommes !",
]
ELF_F_COMMON = [
 "Le monde est bel et bien plein de périls.",
 "Quelles nouvelles apportez-vous, # ?",
 "Vers la mer, vers la mer !",
 "L'appel de la mer dans mon coeur se fait chaque jour plus fort.",
 "À l'ouest, loin à l'ouest, le soleil rond décline. Nos jours s'achèvent et nos années déclinent.",
 "Gilthoniel ! Ô Elbereth !",
 "Du Miruvor ! Il ravive le corps et purifie l'esprit.",
 "Êtes-vous un ami des Elfes, # ?",
 "Ici demeure encore la mémoire des Jours Anciens.",
 "Les jours se font froids et les années s'effacent.",
 "Bientôt... bientôt je quitterai ces terres, #.",
 "Vous ne pourrez vous dire véritable ami des Elfes avant d'avoir goûté au lembas, #.",
 "Une ombre s'amasse sur ces terres. Je le sens dans ma chair.",
 "J'ai vu passer plus d'années de soleil que n'en peuvent compter les Hommes mortels.",
 "Qu'une étoile brille sur l'heure de notre rencontre, #.",
 "Le temps de notre peuple touche à sa fin.",
 "Entendez-vous les mouettes blanches appeler ?",
 "Le Sans-Nom est de retour. Nous ne pourrons demeurer longtemps sur ces rivages.",
 "Nous devons partir vers l'Ouest, ou décliner en un peuple rustique des vallons et des grottes, pour lentement oublier et être oubliés.",
]
GALADHRIM_F = ELF_F_COMMON + [
 "Ô Lórien ! Trop longtemps ai-je demeuré sur ce Rivage-ci.",
 "Nul mal n'entrera en Lórien.",
 "Ce n'est pas en vain que tombent les feuilles de la Lórien.",
 "Ne craignez rien, #. Aucun mal ne peut entrer dans le Bois Doré : un pouvoir secret le protège.",
 "Bienvenue au royaume du Seigneur Celeborn, et de Galadriel, Dame de Lumière !",
 "Le monde a changé. Je le sens dans l'eau... Je le sens dans la terre... Je le hume dans l'air.",
]
RIVENDELL_F = ELF_F_COMMON + [
 "Bienvenue dans la maison du Seigneur Elrond, #.",
 "Nul mal n'entrera au royaume d'Imladris.",
 "Gil-galad était un roi des Elfes. De lui, tristement, chantent les harpes : le dernier dont le royaume fut beau et libre, entre les montagnes et la mer...",
 "Il est aisé de s'attarder chez nous, dans la Dernière Maison Simple à l'est de la Mer.",
]
ELF_HIRED = [
 "Nous autres Elfes comptons parmi les plus fins guerriers de toute la Terre du Milieu.",
 "Il y a bien longtemps que les races des Hommes et des Elfes n'ont combattu côte à côte, #.",
 "La vermine de Morgul périra par l'arc et par la lame !",
 "Vous avez notre allégeance, #.",
 "Que la grâce des Valar nous protège au combat, #.",
 "L'argent compte peu pour un Elfe, #, mais nous combattons pour une cause commune.",
 "Mon arc chantera avec votre épée, # !",
 "Purgeons ce monde de l'immonde vermine du Mordor !",
 "Les jours se font sombres et froids, mais la lumière des Elfes ne s'est pas encore éteinte !",
 "Le mal s'amasse de nouveau dans la forteresse de Dol Guldur. Il ne peut menacer nos terres plus longtemps.",
]
ELF_HOSTILE_G = [
 "Nul mal ne posera le pied dans le Bois Doré !",
 "Vous n'entrerez pas en Lórien, # !",
 "Vous respirez si fort que j'aurais pu vous abattre dans le noir.",
 "Vous n'êtes pas un ami des Elfes, # !",
 "L'Ennemi n'a aucune prise sur la Lórien !",
 "Votre ténèbre n'empoisonnera pas nos bois sacrés !",
 "À Udûn avec vous !",
 "Allez donc embrasser un Orque !",
 "Mort aux ennemis des Elfes !",
 "Retournez à l'ombre dont vous venez !",
 "Partez, #, ou affrontez la volée de nos flèches !",
]
ELF_HOSTILE_R = [s.replace("dans le Bois Doré", "en Imladris").replace("en Lórien", "à Fondcombe")
                 .replace("la Lórien", "Imladris").replace("nos bois sacrés", "la Dernière Maison Simple")
                 for s in ELF_HOSTILE_G] + [
 "Comment osez-vous souiller ce beau royaume de vos pieds immondes ?",
]
ELF_LORD_F = [
 "J'ai maints fins guerriers à engager, #.",
 "Avez-vous des pièces d'argent, # ?",
 "Si vous combattez pour une noble cause, alors mes forces vous soutiendront.",
 "Même les guerriers elfes demandent leur solde, #.",
 "Rares sont ceux qui, en Terre du Milieu, égalent un Elfe au combat.",
 "Vous cherchez une alliance avec les Elfes ? Voilà qui est rare, depuis le changement du monde.",
 "Que les Valar veillent sur vous dans la bataille.",
 "Que vos flèches volent loin et que votre épée frappe vite !",
 "Il y a fort longtemps qu'Elfe et Homme n'ont combattu ensemble.",
]
ELF_LORD_N = [
 "Pouvons-nous vous faire confiance, # ?",
 "Pour mener mes Elfes au combat, il vous faudra grandir en estime parmi notre peuple.",
 "Seuls les plus grands amis des Elfes peuvent combattre à nos côtés.",
 "Nul Elfe ne suivrait à la guerre quelqu'un d'aussi jeune en hauts faits que vous, #.",
 "Nous autres Elfes n'allons pas à la guerre à la légère, #.",
 "Vous, #, commander mes guerriers ? Par les Valar ! L'arrogance des Hommes n'a-t-elle donc pas de fin ?",
 "Prouvez votre allégeance aux Elfes, #, et nous combattrons avec vous.",
]
ORC_F = [
 "Immonde asticot ! Oh, c'est vous, #...",
 "Je veux de la chair d'Homme !",
 "Où est la chair d'Homme ?",
 "Vous ne ressemblez toujours pas à un Homme-Gobelin, # !",
 "Trois jours que je ne mange que du pain véreux ! Trois jours puants !",
 "À votre service, # !",
 "Bientôt, # ! Bientôt nous serons en guerre contre les sales fils des Hommes !",
 "Nous servons le même maître, #.",
 "Nous briserons leurs os et festoierons de leur chair !",
 "Mort aux royaumes des Hommes ! Mort aux sales Elfes !",
 "Nous déferlerons sur les terres des peuples libres comme un fléau ! Ne rien laisser en vie !",
 "Ces haillons puent !",
 "Ruine ! Ruine aux peuples libres !",
 "Ça sent quelque chose d'infect par ici... C'est vous, # ! Ouste !",
 "Vous n'êtes pas si mal, pour un Homme !",
]
ORC_N = [
 "Je sens de la chair d'Homme !",
 "Qu'est-ce que tu veux, asticot ?",
 "Suis tes ordres, vermine !",
 "Tu ne ressembles pas à un Homme-Gobelin !",
 "Reste pas planté là ! Fais quelque chose !",
 "J'ai pas confiance en toi, asticot !",
 "Tu sers qui, toi ?",
 "Circule, vermine !",
 "Tu cherches la bagarre ?",
 "Tu pues !",
 "Hors de ma vue, vermisseau !",
 "Retourne au travail, vermine !",
]
ORC_H = [
 "Je vais te saigner comme un porc !",
 "Profite de ton dernier souffle, vermine !",
 "Tu es bien frais, toi !",
 "Tuez la vermine !",
 "On dirait que la viande est de retour au menu, les gars !",
 "Tu pues la peur !",
 "Crève, vermine d'Homme !",
 "Ton sang sent le frais !",
 "Je vais t'embrocher comme un quartier de viande !",
 "Tu ferais mieux de courir, asticot !",
 "Ce soir, on dîne de chair d'Homme !",
 "Quand j'en aurai fini avec toi, il ne restera qu'un tas d'os !",
 "Tuez le tark !",
 "Sale tark ! Il a essayé de me planter !",
]
ORC_HIRED = [
 "Où est la chair d'Homme la plus proche ? Je meurs de faim, moi !",
 "Quels sont vos ordres, chef ?",
 "Vous êtes peut-être mon commandant, #, mais vous restez un sale asticot !",
 "Menez-nous à la guerre, # !",
 "L'air est mûr de la puanteur de la peur !",
 "Les peuples libres de la terre tomberont devant notre puissance !",
 "Mort ! Mort à tous ceux qui nous résistent !",
 "On va chasser de la chair d'Homme, # ?",
 "Ces sales tarks ne savent pas ce qui les attend !",
 "Semons la ruine dans le monde des Hommes !",
]
ORC_SKIRMISH = [
 "Tu cherches l'échauffourée ?",
 "Personne ne me touche, sale asticot !",
 "Je vais t'embrocher comme un quartier de viande !",
 "Tu veux commencer quelque chose, toi ?",
 "Je vais me faire des jarretières avec tes boyaux, vermine !",
 "Qu'est-ce que tu as à me regarder comme ça, vermine ?",
 "Je mettrai ta tête sur une pique, asticot !",
 "On dirait que c'est l'heure de l'échauffourée !",
 "Personne ne me cherche sans le regretter !",
 "Qui c'est que tu traites de sale asticot ?",
 "Tu veux te battre, sale bout de viande ?",
 "Tu crois pouvoir me battre, ordure ?",
 "Tu vas payer pour ça, sale vermisseau !",
 "Je vais t'écrabouiller comme le sale petit ver que tu es !",
]
ISEN_F = ORC_F + ["Sharkû nous mène à la victoire ! La Main Blanche écrasera tout !",
 "Les Uruk-hai sont la fierté de la Main Blanche. Nous, on est juste plus nombreux."]
ISEN_HIRED = ORC_HIRED + ["Pour la Main Blanche de Saroumane !"]
MORDOR_F = ORC_F + ["L'Oeil parle de vous en bien, #.",
 "Le Maître nous prépare à la guerre. Bientôt, le monde des Hommes tombera."]
MORDOR_HIRED = ORC_HIRED + ["Pour le Grand Oeil !"]

FILES = {
 "gondor/soldier/friendly": GONDOR_SOLDIER_F,
 "gondor/soldier/hired": GONDOR_SOLDIER_HIRED,
 "gondor/soldier/hostile": GONDOR_SOLDIER_HOSTILE,
 "gondor/captain/friendly": GONDOR_CAPTAIN_F,
 "gondor/captain/neutral": GONDOR_CAPTAIN_N,
 "gondor/blacksmith/friendly": GONDOR_BS_F,
 "gondor/blacksmith/neutral": GONDOR_BS_N,
 "gondor/blacksmith/hostile": GONDOR_BS_H,
 "rohan/warrior/friendly": ROHAN_WARRIOR_F,
 "rohan/warrior/hired": ROHAN_WARRIOR_HIRED,
 "rohan/warrior/hostile": ROHAN_WARRIOR_HOSTILE,
 "rohan/warrior/avengeHorse": ROHAN_AVENGE,
 "rohan/marshal/friendly": ROHAN_MARSHAL_F,
 "rohan/marshal/neutral": ROHAN_MARSHAL_N,
 "galadhrim/elf/friendly": GALADHRIM_F,
 "galadhrim/elf/hired": ELF_HIRED + ["Pour la Lothlórien !"],
 "galadhrim/elf/hostile": ELF_HOSTILE_G,
 "galadhrim/lord/friendly": ELF_LORD_F + ["Je vous accorde les plus fins guerriers de toute la Lórien !"],
 "galadhrim/lord/neutral": ELF_LORD_N,
 "rivendell/elf/friendly": RIVENDELL_F,
 "rivendell/elf/hired": ELF_HIRED + ["Pour Imladris !"],
 "rivendell/elf/hostile": ELF_HOSTILE_R,
 "rivendell/lord/friendly": ELF_LORD_F,
 "rivendell/lord/neutral": ELF_LORD_N,
 "mordor/orc/friendly": MORDOR_F,
 "mordor/orc/neutral": ORC_N + ["Tu sers le Grand Oeil, ou ces sales Gondoriens ?"],
 "mordor/orc/hostile": ORC_H + ["Nos armées ne t'ont pas encore chassé, vermine ?"],
 "mordor/orc/hired": MORDOR_HIRED,
 "mordor/orc/skirmish": ORC_SKIRMISH,
 "isengard/orc/friendly": ISEN_F,
 "isengard/orc/neutral": ORC_N,
 "isengard/orc/hostile": ORC_H,
 "isengard/orc/hired": ISEN_HIRED,
 "isengard/orc/skirmish": ORC_SKIRMISH,
}

n = 0
for path, lines in FILES.items():
    d = os.path.join(OUT, os.path.dirname(path))
    os.makedirs(d, exist_ok=True)
    open(os.path.join(OUT, path + ".txt"), "w", encoding="utf-8").write("\n".join(lines) + "\n")
    n += len(lines)
print(f"{len(FILES)} fichiers, {n} repliques VF")
