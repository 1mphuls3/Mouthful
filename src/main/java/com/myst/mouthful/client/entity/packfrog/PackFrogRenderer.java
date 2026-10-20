package com.myst.mouthful.client.entity.packfrog;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.myst.mouthful.Mouthful;
import com.myst.mouthful.common.entity.PackFrogEntity;
import com.myst.mouthful.core.helper.Easing;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.Objects;

public class PackFrogRenderer extends MobRenderer<PackFrogEntity, PackFrogModel<PackFrogEntity>> {
    private Vec3 targetPosO = Vec3.ZERO;

    public PackFrogRenderer(EntityRendererProvider.Context context) {
        super(context, new PackFrogModel<>(context.bakeLayer(PackFrogModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(PackFrogEntity packFrogEntity) {
        return ResourceLocation.fromNamespaceAndPath(Mouthful.MODID, "textures/entity/pack_frog.png");
    }

    @Override
    public void render(PackFrogEntity frog, float yaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
        super.render(frog, yaw, partialTicks, stack, buffer, packedLight);

        VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutout(ResourceLocation.fromNamespaceAndPath(Mouthful.MODID, "textures/white.png")));
        Matrix4f matrix = stack.last().pose();

        ItemEntity target = (ItemEntity) frog.level().getEntity(frog.getTargetID());

        // When item is removed, retain its position
        targetPosO = target == null ? targetPosO : target.position();

        Vec3 mouthPos = new Vec3(0, 0.6, 0);
        Vec3 targetPos = targetPosO.add(0, 0.5, 0).subtract(frog.position());
        Vec3 tongueEnd = mouthPos.lerp(targetPos, Easing.easeInExpo(frog.getTongueDelta() / PackFrogEntity.MAX_TONGUE_TICKS));

        Vec3 dir = tongueEnd.subtract(mouthPos);
        Vec3 flatDir = new Vec3(dir.x, 0f, dir.z);

        float width = 0.2f;
        Vec3 perpendicular = flatDir.cross(new Vec3(0, 1, 0)).normalize().scale(width/2f);

        Vec3 v1 = mouthPos.subtract(perpendicular);
        Vec3 v2 = mouthPos.add(perpendicular);
        Vec3 v3 = tongueEnd.add(perpendicular);
        Vec3 v4 = tongueEnd.subtract(perpendicular);

        stack.pushPose();

        // Top face
        consumer.addVertex(matrix, (float) v1.x, (float) v1.y, (float) v1.z).setColor(0xa12a2a)
                .setUv(0.0f, 0.0f).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(0, 1, 0);
        consumer.addVertex(matrix, (float) v2.x, (float) v2.y, (float) v2.z).setColor(0xa12a2a)
                .setUv(1.0f, 0.0f).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(0, 1, 0);
        consumer.addVertex(matrix, (float) v3.x, (float) v3.y, (float) v3.z).setColor(0xe55e5e)
                .setUv(1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(0, 1, 0);
        consumer.addVertex(matrix, (float) v4.x, (float) v4.y, (float) v4.z).setColor(0xe55e5e)
                .setUv(0.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(0, 1, 0);

        // Bottom face
        consumer.addVertex(matrix, (float) v4.x, (float) v4.y, (float) v4.z).setColor(0xe55e5e)
                .setUv(0.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(0, -1, 0);
        consumer.addVertex(matrix, (float) v3.x, (float) v3.y, (float) v3.z).setColor(0xe55e5e)
                .setUv(1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(0, -1, 0);
        consumer.addVertex(matrix, (float) v2.x, (float) v2.y, (float) v2.z).setColor(0xa12a2a)
                .setUv(1.0f, 0.0f).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(0, -1, 0);
        consumer.addVertex(matrix, (float) v1.x, (float) v1.y, (float) v1.z).setColor(0xa12a2a)
                .setUv(0.0f, 0.0f).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(0, -1, 0);

        stack.popPose();
    }
}
