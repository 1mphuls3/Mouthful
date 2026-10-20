package com.myst.mouthful.core.registries;

import com.myst.mouthful.Mouthful;
import com.myst.mouthful.common.entity.PackFrogEntity;
import com.myst.mouthful.common.entity.PancakeToadEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, Mouthful.MODID);

    public static final Supplier<EntityType<PackFrogEntity>> PACK_FROG = ENTITIES.register("pack_frog",
            () -> EntityType.Builder.of(PackFrogEntity::new, MobCategory.CREATURE).sized(0.8f, 0.8f).build("pack_frog"));
    public static final Supplier<EntityType<PancakeToadEntity>> PANCAKE_TOAD = ENTITIES.register("pancake_toad",
            () -> EntityType.Builder.of(PancakeToadEntity::new, MobCategory.CREATURE).sized(1.8f, 0.4f).build("pancake_toad"));
}
