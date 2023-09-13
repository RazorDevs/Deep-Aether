package teamrazor.deepaether.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

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

        public Provider(SpriteSet p_105753_) {
            this.sprite = p_105753_;
        }

        public Particle createParticle(SimpleParticleType p_105764_, ClientLevel p_105765_, double p_105766_, double p_105767_, double p_105768_, double p_105769_, double p_105770_, double p_105771_) {
            PoisonBubbles poisonBubbles = new PoisonBubbles(p_105765_, p_105766_, p_105767_, p_105768_, p_105769_, p_105770_, p_105771_);
            poisonBubbles.setSpriteFromAge(this.sprite);
            return poisonBubbles;
        }
    }
}