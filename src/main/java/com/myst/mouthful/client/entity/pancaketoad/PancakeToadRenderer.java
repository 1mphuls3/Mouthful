package com.myst.mouthful.client.entity.pancaketoad;

import com.mojang.blaze3d.vertex.PoseStack;
import com.myst.mouthful.Mouthful;
import com.myst.mouthful.common.entity.PancakeToadEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PancakeToadRenderer extends MobRenderer<PancakeToadEntity, PancakeToadModel<PancakeToadEntity>> {
    public PancakeToadRenderer(EntityRendererProvider.Context context) {
        super(context, new PancakeToadModel<>(context.bakeLayer(PancakeToadModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(PancakeToadEntity packFrogEntity) {
        return ResourceLocation.fromNamespaceAndPath(Mouthful.MODID, "textures/entity/pancake_toad.png");
    }

    @Override
    public void render(PancakeToadEntity toad, float yaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
        super.render(toad, yaw, partialTicks, stack, buffer, packedLight);
    }
}