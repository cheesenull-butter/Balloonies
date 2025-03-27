package cheesenull.balloonies.sound;

import cheesenull.balloonies.Balloonies;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class BallooniesSounds {

    public static final SoundEvent BALOONIE_POP = registerSoundEvent("entity.balloonie.balloonie_pop");
    public static final SoundEvent LOBOTOMY = registerSoundEvent("entity.balloonie.lobotomy");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(Balloonies.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        Balloonies.LOGGER.info("Registering Mod Sounds for " + Balloonies.MOD_ID);
    }

}
