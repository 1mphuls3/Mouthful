package com.myst.mouthful.core.registries;

import com.myst.mouthful.Mouthful;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Mouthful.MODID);

    public static final Supplier<Item> PACKFROG_EGG = ITEMS.register("packfrog_egg",
            () -> new DeferredSpawnEggItem(EntityRegistry.PACKFROG, 0xc48c4d, 0xffd987, new Item.Properties()));
}
