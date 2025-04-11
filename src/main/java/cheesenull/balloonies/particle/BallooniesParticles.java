package cheesenull.balloonies.particle;

import cheesenull.balloonies.Balloonies;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BallooniesParticles {

    public static final SimpleParticleType CONFETTI_BLACK =
            registerParticle("black_confetti_particle", FabricParticleTypes.simple());
    public static final SimpleParticleType CONFETTI_BLUE =
            registerParticle("blue_confetti_particle", FabricParticleTypes.simple());
    public static final SimpleParticleType CONFETTI_ORANGE =
            registerParticle("orange_confetti_particle", FabricParticleTypes.simple());
    public static final SimpleParticleType CONFETTI_RED =
            registerParticle("red_confetti_particle", FabricParticleTypes.simple());

    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(Balloonies.MOD_ID, name), particleType);
    }

    public static void registerParticles() {
        Balloonies.LOGGER.info("Registering Particles for " + Balloonies.MOD_ID);
    }

}
