package com.myst.mouthful.client.entity.pancaketoad;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.myst.mouthful.Mouthful;
import com.myst.mouthful.client.entity.packfrog.PackFrogAnimations;
import com.myst.mouthful.common.entity.PackFrogEntity;
import com.myst.mouthful.common.entity.PancakeToadEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class PancakeToadModel<T extends PancakeToadEntity> extends HierarchicalModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Mouthful.MODID, "pancake_toad"), "main");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart fr_foot;
	private final ModelPart fl_foot;
	private final ModelPart bl_foot;
	private final ModelPart br_foot;

	public PancakeToadModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.fr_foot = this.body.getChild("fr_foot");
		this.fl_foot = this.body.getChild("fl_foot");
		this.bl_foot = this.body.getChild("bl_foot");
		this.br_foot = this.body.getChild("br_foot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 23.0F, 1.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, -6.0F, -17.0F, 32.0F, 6.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition fr_foot = body.addOrReplaceChild("fr_foot", CubeListBuilder.create().texOffs(81, 75).addBox(4.0F, -6.0F, -7.0F, 2.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(43, 89).addBox(1.0F, 0.0F, -9.0F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(12.0F, 1.0F, -9.0F));

		PartDefinition fl_foot = body.addOrReplaceChild("fl_foot", CubeListBuilder.create().texOffs(81, 75).mirror().addBox(-2.0F, -6.0F, -6.0F, 2.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(43, 89).mirror().addBox(-2.0F, 0.0F, -8.0F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-16.0F, 1.0F, -10.0F));

		PartDefinition bl_foot = body.addOrReplaceChild("bl_foot", CubeListBuilder.create().texOffs(81, 65).addBox(4.0F, -5.0F, -8.0F, 3.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(54, 90).addBox(1.0F, 0.0F, -9.0F, 5.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-22.0F, 1.0F, 20.0F));

		PartDefinition br_foot = body.addOrReplaceChild("br_foot", CubeListBuilder.create().texOffs(81, 65).mirror().addBox(3.0F, -5.0F, -7.0F, 3.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(54, 90).mirror().addBox(3.0F, 0.0F, -8.0F, 5.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(12.0F, 1.0F, 19.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

    @Override
    public void setupAnim(PancakeToadEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int red, int green, int blue) {
        body.render(stack, consumer, red, green, blue);
    }

    @Override
    public ModelPart root() {
        return body;
    }
}