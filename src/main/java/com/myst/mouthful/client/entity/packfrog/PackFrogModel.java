package com.myst.mouthful.client.entity.packfrog;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.myst.mouthful.Mouthful;
import com.myst.mouthful.common.entity.PackFrogEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class PackFrogModel<T extends PackFrogEntity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Mouthful.MODID, "packfrog"), "main");
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart bottom;
    private final ModelPart top;
    private final ModelPart fr_foot;
    private final ModelPart fl_foot;
    private final ModelPart bl_foot;
    private final ModelPart br_foot;

    public PackFrogModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = this.body.getChild("head");
        this.bottom = this.head.getChild("bottom");
        this.top = this.head.getChild("top");
        this.fr_foot = this.body.getChild("fr_foot");
        this.fl_foot = this.body.getChild("fl_foot");
        this.bl_foot = this.body.getChild("bl_foot");
        this.br_foot = this.body.getChild("br_foot");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 23.0F, 1.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -7.0F, -1.0F));

        PartDefinition bottom = head.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 32).addBox(-6.0F, -1.0F, -8.0F, 12.0F, 8.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(27, 25).addBox(-2.0F, -1.1F, -8.1F, 6.0F, 0.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = bottom.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(34, 39).addBox(-3.0F, 0.0F, -5.0F, 6.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.9F, -8.1F, 1.5708F, 0.0F, 0.0F));

        PartDefinition top = head.addOrReplaceChild("top", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -3.0F, -11.0F, 12.0F, 3.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 23).addBox(2.0F, -6.0F, -10.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 23).mirror().addBox(-6.0F, -6.0F, -10.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -1.0F, 5.0F));

        PartDefinition fr_foot = body.addOrReplaceChild("fr_foot", CubeListBuilder.create().texOffs(49, 11).addBox(-0.25F, -2.0F, -3.0F, 2.0F, 7.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(11, 25).addBox(-3.25F, 5.0F, -5.0F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(6.25F, -4.0F, -3.0F));

        PartDefinition fl_foot = body.addOrReplaceChild("fl_foot", CubeListBuilder.create().texOffs(49, 11).mirror().addBox(-1.75F, -2.0F, -3.0F, 2.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(11, 25).mirror().addBox(-1.75F, 5.0F, -5.0F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-6.25F, -4.0F, -3.0F));

        PartDefinition bl_foot = body.addOrReplaceChild("bl_foot", CubeListBuilder.create().texOffs(49, 1).addBox(-2.0F, -1.0F, 0.0F, 3.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(23, 27).addBox(-5.0F, 4.0F, -1.0F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -3.0F, 2.0F));

        PartDefinition br_foot = body.addOrReplaceChild("br_foot", CubeListBuilder.create().texOffs(23, 27).mirror().addBox(-1.0F, 4.0F, -1.0F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(49, 1).mirror().addBox(-1.0F, -1.0F, 0.0F, 3.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(6.0F, -3.0F, 2.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }



    @Override
    public void setupAnim(PackFrogEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animateWalk(PackFrogAnimations.ANIM_PACKFROG_WALK, limbSwing, limbSwingAmount, 8f, 25f);
        this.animate(entity.openState, PackFrogAnimations.ANIM_PACKFROG_OPEN, ageInTicks);
        this.animate(entity.closeState, PackFrogAnimations.ANIM_PACKFROG_CLOSE, ageInTicks);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int red, int green, int blue) {
        body.render(poseStack, vertexConsumer, red, green, blue);

    }

    @Override
    public ModelPart root() {
        return body;
    }
}
