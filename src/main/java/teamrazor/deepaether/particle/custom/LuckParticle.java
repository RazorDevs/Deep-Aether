package teamrazor.deepaether.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;

import org.jetbrains.annotations.NotNull;

public class LuckParticle extends SimpleAnimatedParticle {
    LuckParticle(ClientLevel level, double x, double y, double z, double xd, double yd, double zd, SpriteSet spriteSet) {
        super(level, x, y, z, spriteSet, 0.0125F);
        this.xd = xd;
        this.yd = yd;
        this.zd = zd;
        this.quadSize *= 0.75F;
        this.lifetime = 50 + this.random.nextInt(25);
        this.setFadeColor(15916745);
        this.setSpriteFromAge(spriteSet);
    }

    @Override
    public void move(double x, double y, double z) {
        this.setBoundingBox(this.getBoundingBox().move(x, y, z));
        this.setLocationFromBoundingbox();
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(@NotNull SimpleParticleType type, ClientLevel level, double x, double y, double z, double xd, double yd, double zd) {
            return new LuckParticle(level, x, y, z, xd, yd, zd, this.sprites);
        }
    }
}
