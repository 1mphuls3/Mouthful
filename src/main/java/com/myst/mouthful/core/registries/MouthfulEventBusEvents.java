package com.myst.mouthful.core.registries;

import com.myst.mouthful.Mouthful;
import com.myst.mouthful.client.entity.packfrog.PackFrogModel;
import com.myst.mouthful.common.entity.PackFrogEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = Mouthful.MODID)
public class MouthfulEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PackFrogModel.LAYER_LOCATION, PackFrogModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityRegistry.PACKFROG.get(), PackFrogEntity.createAttributes().build());
    }
}
