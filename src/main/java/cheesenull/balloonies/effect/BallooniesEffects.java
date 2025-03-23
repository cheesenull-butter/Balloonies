package cheesenull.balloonies.effect;

import cheesenull.balloonies.Balloonies;
import cheesenull.balloonies.effect.custom.DistortionEffect;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class BallooniesEffects {

    public static final RegistryEntry<StatusEffect> DISTORTION = registerStatusEffect("distortion",
            new DistortionEffect(StatusEffectCategory.NEUTRAL, 0x36ebab));


    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Balloonies.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        Balloonies.LOGGER.info("Registering Mod Effects for " + Balloonies.MOD_ID);
    }

}
