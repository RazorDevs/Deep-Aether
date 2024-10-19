package io.github.razordevs.deepaether.deepaether.custom;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.AABB;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class EOTSExplosionParticle extends SimpleAnimatedParticle {
    public static final AABB INFINITE = new AABB(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    float yDegrees = 0;
    SingleQuadParticle.FacingCameraMode NO_CAMERA_ROTATION_DOWNWARDS_ROTATING = (quaternionf, camera, partialTicks) -> {
        yDegrees+= partialTicks;
        yDegrees = Mth.wrapDegrees(yDegrees);

        float xw =Mth.lerp(Mth.abs(yDegrees)/180.0F, 0.0F, 0.707F);
        float yz = 0.707F-xw;

        if(camera.getPosition().y < this.getPos().y) {
            if(yDegrees < 0) {
                quaternionf.set(-xw, -yz, -yz, xw);
            }
            else quaternionf.set(-xw, yz, yz, xw);
        }
        else {
            if(yDegrees < 0) {
                quaternionf.set(xw, yz, -yz, xw);
            }
            else quaternionf.set(xw, -yz, yz, xw);
        }
    };





    protected EOTSExplosionParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed, SpriteSet pSprites) {
        super(pLevel, pX, pY+0.1, pZ, pSprites, 0.0F);
        this.xd = pXSpeed;
        this.yd = pYSpeed;
        this.zd = pZSpeed;
        this.lifetime = 50;
        this.setFadeColor(15916745);
        this.setSpriteFromAge(pSprites);
        this.setSize(0.1F, 0.1F);
    }

    @Override
    public void render(VertexConsumer pBuffer, Camera pRenderInfo, float pPartialTicks) {
        super.render(pBuffer, pRenderInfo, pPartialTicks);
        this.quadSize += (pPartialTicks * pPartialTicks * 0.5F);
    }

    @Override
    public SingleQuadParticle.FacingCameraMode getFacingCameraMode() {
        return NO_CAMERA_ROTATION_DOWNWARDS_ROTATING;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            return new EOTSExplosionParticle(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed, this.sprites);
        }
    }


    @Override
    public AABB getBoundingBox() {
        return INFINITE;
    }

    @Override
    public boolean shouldCull() {
        return false;
    }
}
