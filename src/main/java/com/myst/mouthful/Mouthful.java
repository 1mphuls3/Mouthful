package com.myst.mouthful;

import com.myst.mouthful.core.registries.BlockRegistry;
import com.myst.mouthful.core.registries.EntityRegistry;
import com.myst.mouthful.core.registries.ItemRegistry;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(Mouthful.MODID)
public class Mouthful {
    public static final String MODID = "mouthful";

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MOUTHFUL_TAB = CREATIVE_MODE_TABS.register("mouthful",
            () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.mouthful"))
            .icon(Items.VERDANT_FROGLIGHT::getDefaultInstance)
            .displayItems((params, output) -> {
                ItemRegistry.ITEMS.getEntries().stream().map(DeferredHolder::get).forEach(output::accept);
            }).build());

    public Mouthful(IEventBus eventBus, ModContainer container) {
        eventBus.addListener(this::commonSetup);

        BlockRegistry.BLOCKS.register(eventBus);
        ItemRegistry.ITEMS.register(eventBus);
        EntityRegistry.ENTITIES.register(eventBus);
        CREATIVE_MODE_TABS.register(eventBus);

        NeoForge.EVENT_BUS.register(this);
        container.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
