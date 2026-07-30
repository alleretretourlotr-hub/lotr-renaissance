# Bibliotheque de conversion des peaux (extraite de fix_skins_weapons.py)
from PIL import Image

def convert_legacy_skin(img):
    out = img.copy()
    out.paste(Image.new("RGBA", (64, 32), (0, 0, 0, 0)), (0, 32))
    def cp(x, y, dx, dy, w, h):
        region = img.crop((x, y, x + w, y + h)).transpose(Image.FLIP_LEFT_RIGHT)
        out.paste(region, (x + dx, y + dy))
    cp(4, 16, 16, 32, 4, 4);  cp(8, 16, 16, 32, 4, 4)
    cp(0, 20, 24, 32, 4, 12); cp(4, 20, 16, 32, 4, 12)
    cp(8, 20, 8, 32, 4, 12);  cp(12, 20, 16, 32, 4, 12)
    cp(44, 16, -8, 32, 4, 4); cp(48, 16, -8, 32, 4, 4)
    cp(40, 20, 0, 32, 4, 12); cp(44, 20, -8, 32, 4, 12)
    cp(48, 20, -16, 32, 4, 12); cp(52, 20, -8, 32, 4, 12)
    return out

def limb_missing(img):
    for box in ((32, 48, 48, 64), (16, 48, 32, 64)):
        d = list(img.crop(box).getdata())
        if sum(1 for p in d if p[3] > 128) / len(d) < 0.3:
            return True
    return False
