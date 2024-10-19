package io.github.razordevs.deep_aether.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PoisonBubbles extends TextureSheetParticle {

    protected PoisonBubbles(ClientLevel clientLevel, double v, double v1, double v2, double v3, double v4, double v5) {
        super(clientLevel, v, v1, v2);
        this.gravity = -0.1F;
        this.friction = 0.85F;
        this.setSize(0.02F, 0.02F);
        this.quadSize *= this.random.nextFloat() * 0.6F + 0.2F;
        this.xd = v3 * (double)0.2F + (Math.random() * 2.0D - 1.0D) * (double)0.02F;
        this.yd = v4 * (double)0.2F + (Math.random() * 2.0D - 1.0D) * (double)0.02F;
        this.zd = v5 * (double)0.2F + (Math.random() * 2.0D - 1.0D) * (double)0.02F;
        this.lifetime = (int)(10.0D / (Math.random() * 0.8D + 0.2D));
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }


    public void tick() {
        this.setSpriteFromAge(Provider.sprite);
        super.tick();
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        protected static SpriteSet sprite = null;

        public Provider(SpriteSet spriteSet) {
            this.sprite = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel clientLevel, double v, double v1, double v2, double v3, double v4, double v5) {
            PoisonBubbles poisonBubbles = new PoisonBubbles(clientLevel, v, v1, v2, v3, v4, v5);
            poisonBubbles.setSpriteFromAge(this.sprite);
            return poisonBubbles;
        }
    }
}