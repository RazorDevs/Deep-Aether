package io.github.razordevs.deepaether.deepaether.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class EOTSPreFightParticle extends TextureSheetParticle {
    private final SpriteSet animatedSprite;
    private float count;

    public EOTSPreFightParticle(ClientLevel level, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, SpriteSet sprite) {
        super(level, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);
        this.animatedSprite = sprite;
        this.hasPhysics = false;
        this.quadSize = this.random.nextFloat() * this.random.nextFloat() * 0.5F;
        this.lifetime = 80 + this.random.nextInt(50);
        this.xd = xSpeed;
        this.yd = ySpeed;
        this.zd = zSpeed;
        this.count = 2.5f;
    }

    public void tick() {
        if (this.age++ >= this.lifetime) {
            this.remove();
        }
        this.setAlpha(RandomSource.create().nextFloat() + 0.3f);
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        this.setSpriteFromAge(this.animatedSprite);
        this.oRoll = this.roll;
        this.roll += 0.1F;

        this.xd = Math.sin(this.age/0.003f) / count;
        this.zd = Math.cos(this.age/0.003f) / count;

        count -= 0.015F;

        this.yd += 0.004;
        this.move(xd, yd, zd);
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public record Provider(SpriteSet spriteSet) implements ParticleProvider<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            EOTSPreFightParticle particle = new EOTSPreFightParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet());
            particle.pickSprite(this.spriteSet());
            return particle;
        }

        public SpriteSet spriteSet() {
            return this.spriteSet;
        }
    }

}
