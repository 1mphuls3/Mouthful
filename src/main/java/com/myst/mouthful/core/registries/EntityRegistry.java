package com.myst.mouthful.core.registries;

import com.myst.mouthful.Mouthful;
import com.myst.mouthful.common.entity.PackFrogEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, Mouthful.MODID);

    public static final Supplier<EntityType<PackFrogEntity>> PACKFROG = ENTITIES.register("packfrog",
            () -> EntityType.Builder.of(PackFrogEntity::new, MobCategory.CREATURE).build("packfrog"));
}
