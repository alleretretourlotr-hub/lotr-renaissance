#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""CONVERTISSEUR DE MODELES : lotr.client.model.LOTRModelXxx (1.7.10, ModelBase)
-> fr.alleretretour.lotr.client.model.LOTRModelXxx (1.16.5, EntityModel).

Ne traite QUE les modeles Java du Legacy. Les modeles Blockbench/GeckoLib du
projet ne sont jamais lus ni ecrits par cet outil.

Correspondances appliquees :
  setTextureSize(w,h)   -> setTexSize(w,h)
  setTextureOffset(u,v) -> texOffs(u,v)
  setRotationPoint(...) -> setPos(...)
  rotateAngleX/Y/Z      -> xRot/yRot/zRot
  render(f5)            -> render(ms, buf, light, overlay)
  setRotationAngles     -> setupAnim(entity, f, f1, f2, f3, f4)
"""
import os, re, sys

LEG = "/home/claude/lotr-legacy/src/main/java/lotr/client/model"

DECL = re.compile(r"public ModelRenderer (\w+)\s*(?:=\s*(new ModelRenderer\(this[^;]*))?;")
CTOR_SIG = re.compile(r"public LOTRModel\w+\(float \w+\)\s*\{")


def body_of(src, start_idx):
    j = src.index("{", start_idx)
    depth = 0
    for k in range(j, len(src)):
        if src[k] == "{":
            depth += 1
        elif src[k] == "}":
            depth -= 1
            if depth == 0:
                return src[j + 1:k]
    raise AssertionError("bloc non ferme")


def convert_expr(line):
    """Traduit une ligne de corps de constructeur ou d'animation."""
    line = line.replace(".setTextureSize(", ".setTexSize(")
    line = line.replace(".setTextureOffset(", ".texOffs(")
    line = line.replace(".setRotationPoint(", ".setPos(")
    line = line.replace(".rotateAngleX", ".xRot")
    line = line.replace(".rotateAngleY", ".yRot")
    line = line.replace(".rotateAngleZ", ".zRot")
    line = re.sub(r"\btextureWidth\b", "texWidth", line)
    line = re.sub(r"\btextureHeight\b", "texHeight", line)
    line = re.sub(r"\bshowModel\b", "visible", line)
    line = re.sub(r"\bisChild\b", "young", line)
    line = re.sub(r"\bticksExisted\b", "tickCount", line)
    line = re.sub(r"\bonGround\b", "attackTime", line)          # ModelBase.onGround
    line = re.sub(r"MathHelper\.sqrt_float\b", "MathHelper.sqrt", line)
    line = re.sub(r"\bEntityLivingBase\b", "net.minecraft.entity.LivingEntity", line)
    line = re.sub(r"\bisRiding\b", "riding", line)
    line = re.sub(r"\.getRearingAmount\(", ".getStandAnim(", line)   # AbstractHorse 1.16.5
    line = re.sub(r"\.getGrassEatingAmount\(", ".getEatAnim(", line)
    line = re.sub(r"\.getMouthOpennessAngle\(", ".getMouthAnim(", line)
    line = re.sub(r"\brotationPointX\b", "x", line)
    line = re.sub(r"\brotationPointY\b", "y", line)
    line = re.sub(r"\brotationPointZ\b", "z", line)
    line = line.replace("MathHelper.", "net.minecraft.util.math.MathHelper.")
    line = line.replace("net.minecraft.util.math.net.minecraft.util.math.", "net.minecraft.util.math.")
    return line


def convert(name, entity_class=None, texture_size=None):
    """name : 'Warg' pour LOTRModelWarg. entity_class : classe 1.16.5 castee
    dans les animations (ou None -> parametre generique Entity)."""
    src = open(f"{LEG}/LOTRModel{name}.java", encoding="utf-8").read()

    # 1. champs
    fields, inits = [], {}
    for m in DECL.finditer(src):
        part, init = m.group(1), m.group(2)
        fields.append(part)
        if init:
            inits[part] = init
    # setTexSize global : premier setTextureSize rencontre
    ts = texture_size
    if ts is None:
        m = re.search(r"setTextureSize\((\d+),\s*(\d+)\)", src)
        if m:
            ts = (int(m.group(1)), int(m.group(2)))
        else:
            # certains modeles affectent directement les champs
            mw = re.search(r"textureWidth\s*=\s*(\d+)", src)
            mh = re.search(r"textureHeight\s*=\s*(\d+)", src)
            ts = (int(mw.group(1)) if mw else 64, int(mh.group(1)) if mh else 32)

    # 2. corps du constructeur
    m = CTOR_SIG.search(src)
    assert m, name
    ctor = body_of(src, m.start())
    ctor_lines = []
    for part, init in inits.items():
        ctor_lines.append(f"        {part} = {convert_expr(init)};")
    for raw in ctor.splitlines():
        line = raw.strip()
        if not line or line.startswith("//"):
            continue
        if re.match(r"texture(Width|Height)\s*=", line):
            continue  # deja emis en tete (texWidth/texHeight)
        ctor_lines.append("        " + convert_expr(line))

    # 3. parties racines (jamais enfants)
    children = set(re.findall(r"\w+\.addChild\((\w+)\)", src))
    roots = [f for f in fields if f not in children]

    # 4. animations
    anim_lines = []
    m = re.search(r"public void setRotationAngles\([^)]*\)\s*\{", src)
    if m:
        for raw in body_of(src, m.start()).splitlines():
            line = raw.strip()
            if not line or line.startswith("//"):
                continue
            line = convert_expr(line)
            if entity_class:
                line = re.sub(r"\(\(LOTREntity\w+\) entity\)",
                              f"(({entity_class}) entity)", line)
            elif "LOTREntity" in line:
                line = "// TODO (entite non portee) : " + line
            anim_lines.append("        " + line)

    generic = entity_class or "net.minecraft.entity.Entity"
    renders = "\n".join(
        f"        {r}.render(ms, buf, light, overlay, red, green, blue, alpha);"
        for r in roots)
    fields_java = "\n".join(f"    public final ModelRenderer {f};" for f in fields)
    return f'''package fr.alleretretour.lotr.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

/** CONVERTI AUTOMATIQUEMENT de lotr.client.model.LOTRModel{name} (1.7.10). */
public class LOTRModel{name}<T extends {generic}> extends EntityModel<T> {{

{fields_java}

    public LOTRModel{name}() {{
        this(0.0f);
    }}

    public LOTRModel{name}(float f) {{
        texWidth = {ts[0]};
        texHeight = {ts[1]};
{chr(10).join(ctor_lines)}
    }}

    @Override
    public void setupAnim(T entity, float f, float f1, float f2, float f3, float f4) {{
{chr(10).join(anim_lines) if anim_lines else "        // aucune animation dans le modele Legacy"}
    }}

    @Override
    public void renderToBuffer(MatrixStack ms, IVertexBuilder buf, int light, int overlay,
                               float red, float green, float blue, float alpha) {{
{renders}
    }}
}}
'''


if __name__ == "__main__":
    out = sys.argv[1]
    os.makedirs(out, exist_ok=True)
    for spec in sys.argv[2:]:
        parts = spec.split(":")
        name = parts[0]
        ec = parts[1] if len(parts) > 1 and parts[1] else None
        java = convert(name, ec)
        open(f"{out}/LOTRModel{name}.java", "w", encoding="utf-8").write(java)
        print(f"  LOTRModel{name} converti ({java.count('addBox')} boites, "
              f"{java.count('.render(ms')} racines)")
