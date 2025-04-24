package cheesenull.balloonies.sound;

import cheesenull.balloonies.Balloonies;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class BallooniesSounds {

    public static final SoundEvent ENTITY_BALLOONIE_POP = registerSoundEvent("entity.balloonie.pop");
    public static final SoundEvent ENTITY_BALLOONIE_LOBOTOMY = registerSoundEvent("entity.balloonie.lobotomy");

    public static final SoundEvent ITEM_HARPOON_HIT = registerSoundEvent("item.harpoon.hit");
    public static final SoundEvent ITEM_HARPOON_HIT_GROUND = registerSoundEvent("item.harpoon.hit_ground");
    public static final SoundEvent ITEM_HARPOON_RETURN = registerSoundEvent("item.harpoon.return");
    public static final SoundEvent ITEM_HARPOON_THROW = registerSoundEvent("item.harpoon.throw");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(Balloonies.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        Balloonies.LOGGER.info("Registering Mod Sounds for " + Balloonies.MOD_ID);
    }

}
