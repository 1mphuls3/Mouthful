package com.myst.mouthful;

import com.myst.mouthful.client.entity.packfrog.PackFrogRenderer;
import com.myst.mouthful.client.entity.pancaketoad.PancakeToadRenderer;
import com.myst.mouthful.core.registries.EntityRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = Mouthful.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = Mouthful.MODID, value = Dist.CLIENT)
public class MouthfulClient {
    public MouthfulClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(EntityRegistry.PACK_FROG.get(), PackFrogRenderer::new);
        EntityRenderers.register(EntityRegistry.PANCAKE_TOAD.get(), PancakeToadRenderer::new);
    }
}
