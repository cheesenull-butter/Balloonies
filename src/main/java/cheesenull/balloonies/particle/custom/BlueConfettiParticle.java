package cheesenull.balloonies.particle.custom;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class BlueConfettiParticle extends SpriteBillboardParticle {

    public BlueConfettiParticle(ClientWorld clientWorld, double x, double y, double z,
                                SpriteProvider spriteProvider, double xSpeed, double ySpeed, double zSpeed) {

        super(clientWorld, x, y, z, xSpeed, ySpeed, zSpeed);

        this.velocityMultiplier = 0.6F;
        this.gravityStrength = 0.2F;
        this.x = x;
        this.y = y;
        this.z = z;
        this.scale(6.0F);
        this.maxAge = 300;
        this.setSpriteForAge(spriteProvider);

        this.red = 1.0F;
        this.green = 1.0F;
        this.blue = 1.0F;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    public static class Factory implements ParticleFactory<SimpleParticleType> {

        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z,
                                       double velocityX, double velocityY, double velocityZ) {
            return new BlueConfettiParticle(world, x, y, z, this.spriteProvider, velocityX, velocityY, velocityZ);
        }

    }

}
