#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""Regenere les rosters des 9 recruteurs du mega-lot 2 avec TOUTES les unites
desormais portees (troupes du lot 3 + porte-bannieres)."""
import re, sys
sys.path.insert(0, "/home/claude")
import usine2 as U2

NEW = {
 "AngmarHillman": "angmar_hillman", "AngmarHillmanAxeThrower": "angmar_hillman_axe_thrower",
 "DunlendingArcher": "dunlending_archer", "DunlendingAxeThrower": "dunlending_axe_thrower",
 "EasterlingLevyman": "easterling_levyman", "SouthronChampion": "southron_champion",
 "HarnedorWarrior": "harnedor_warrior", "HarnedorArcher": "harnedor_archer",
 "AngmarHillmanBannerBearer": "angmar_hillman_banner_bearer",
 "DolGuldurBannerBearer": "dol_guldur_banner_bearer",
 "DunlendingBannerBearer": "dunlending_banner_bearer",
 "EasterlingBannerBearer": "easterling_banner_bearer",
 "HalfTrollBannerBearer": "half_troll_banner_bearer",
 "HarnedorBannerBearer": "harnedor_banner_bearer",
 "NearHaradBannerBearer": "near_harad_banner_bearer",
 "MoredainBannerBearer": "moredain_banner_bearer",
 "TauredainBannerBearer": "tauredain_banner_bearer",
}
U2.KNOWN.update(NEW)
U2.MOUNTS.update({"LOTREntityAngmarWarg": "warg", "LOTREntityRhino": "rhino"})

TABLES = [
 ("ANGMAR_HILLMAN_CHIEFTAIN", "angmarHillmanChieftain"),
 ("DOL_GULDUR_CAPTAIN", "dolGuldurChieftain"),
 ("DUNLENDING_WARLORD", "dunlendingWarlord"),
 ("EASTERLING_WARLORD", "easterlingWarlord"),
 ("HALF_TROLL_WARLORD", "halfTrollWarlord"),
 ("HARNEDOR_WARLORD", "harnedorWarlord"),
 ("NEAR_HARADRIM_WARLORD", "nearHaradrimWarlord"),
 ("MOREDAIN_CHIEFTAIN", "moredainChieftain"),
 ("TAUREDAIN_CHIEFTAIN", "tauredainChieftain"),
]

path_in = "/home/claude/lot-creatures/src/main/java/fr/alleretretour/lotr/hire/LOTRHireRosters.java"
path_out = "/home/claude/lot-bannieres3/src/main/java/fr/alleretretour/lotr/hire/LOTRHireRosters.java"
ro = open(path_in, encoding="utf-8").read()

for table, method in TABLES:
    base, entries, skipped = U2.extract_unit_table(table)
    lines = []
    for reg, cost, align, pledge, mount in entries:
        args = f"LOTREntities.{reg.upper()}, {cost}, {align}"
        if pledge:
            args += f", {pledge}"
        e = f"                new Entry({args})"
        if mount:
            e += f'.mounted("{mount}")'
        lines.append(e)
    todo = f"    // TODO (non portes) : {', '.join(skipped)}\n" if skipped else ""
    body = (f"{todo}    public static List<Entry> {method}() {{\n"
            f"        return new ArrayList<>(Arrays.asList(\n"
            + ",\n".join(lines) + "));\n    }\n")
    # remplace la methode existante (avec son eventuel TODO precedent)
    pat = re.compile(r"(?:    // TODO \(non portes\)[^\n]*\n)?    public static List<Entry> "
                     + method + r"\(\) \{.*?\n    \}\n", re.S)
    assert pat.search(ro), method
    ro = pat.sub(body, ro, count=1)
    print(f"  {method}: {len(entries)} unites | ignorees: {skipped}")

open(path_out, "w", encoding="utf-8").write(ro)
