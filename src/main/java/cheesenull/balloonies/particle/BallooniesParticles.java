package cheesenull.balloonies.particle;

import cheesenull.balloonies.Balloonies;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BallooniesParticles {

    public static final SimpleParticleType LAG =
            registerParticle("lag_particle", FabricParticleTypes.simple());

    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(Balloonies.MOD_ID, name), particleType);
    }

    public static void registerParticles() {
        Balloonies.LOGGER.info("Registering Particles for " + Balloonies.MOD_ID);
    }

}
