package teamrazor.deepaether.client.model;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import teamrazor.deepaether.entity.living.boss.eots.EOTSController;
public class EOTSModel extends HierarchicalModel<EOTSController> {
        private final ModelPart bone;

        public EOTSModel(ModelPart root) {
            this.bone = root.getChild("bone");
        }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(EOTSController pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.root().xRot = - pAgeInTicks / 10;
        this.root().yRot = - pAgeInTicks / 10;
    }

    @Override
    public ModelPart root() {
        return bone;
    }
}

