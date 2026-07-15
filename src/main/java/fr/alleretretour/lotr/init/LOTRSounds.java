package fr.alleretretour.lotr.init;

import fr.alleretretour.lotr.LOTRMod;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Registre differe des sons (cors, musiques, sons de NPC).
 */
public class LOTRSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, LOTRMod.MOD_ID);
}
