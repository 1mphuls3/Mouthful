package com.myst.mouthful.core.registries;

import com.myst.mouthful.Mouthful;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Mouthful.MODID);

    public static final Supplier<Item> PACK_FROG_EGG = ITEMS.register("pack_frog_egg",
            () -> new DeferredSpawnEggItem(EntityRegistry.PACK_FROG, 0xc48c4d, 0xffd987, new Item.Properties()));
    public static final Supplier<Item> PANCAKE_TOAD_EGG = ITEMS.register("pancake_toad_egg",
            () -> new DeferredSpawnEggItem(EntityRegistry.PANCAKE_TOAD, 0xc48c4d, 0xffd987, new Item.Properties()));
}
