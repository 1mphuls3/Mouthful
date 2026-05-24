package com.myst.mouthful.client.entity.packfrog;

import com.mojang.blaze3d.vertex.PoseStack;
import com.myst.mouthful.Mouthful;
import com.myst.mouthful.common.entity.PackFrogEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PackFrogRenderer extends MobRenderer<PackFrogEntity, PackFrogModel<PackFrogEntity>> {
    public PackFrogRenderer(EntityRendererProvider.Context context) {
        super(context, new PackFrogModel<>(context.bakeLayer(PackFrogModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(PackFrogEntity packFrogEntity) {
        return ResourceLocation.fromNamespaceAndPath(Mouthful.MODID, "textures/entity/packfrog.png");
    }

    @Override
    public void render(PackFrogEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
