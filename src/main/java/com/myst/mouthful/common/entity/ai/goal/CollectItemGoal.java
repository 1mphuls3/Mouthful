package com.myst.mouthful.common.entity.ai.goal;

import com.myst.mouthful.common.entity.PackFrogEntity;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.List;

public class CollectItemGoal extends Goal {
    private final PackFrogEntity frog;
    private List<ItemEntity> items;
    private int timer;

    public CollectItemGoal(PackFrogEntity frog, double speedModifier) {
        this.frog = frog;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }
    @Override
    public boolean canUse() {
        items = frog.level().getEntitiesOfClass(ItemEntity.class, frog.getBoundingBox().inflate(8));

        return !items.isEmpty() && frog.inventory.canAddItem(items.getFirst().getItem());
    }

    @Override
    public boolean canContinueToUse() {
        items = frog.level().getEntitiesOfClass(ItemEntity.class, frog.getBoundingBox().inflate(8));

        return !items.isEmpty() && frog.inventory.canAddItem(items.getFirst().getItem());
    }

    @Override
    public void start() {
        frog.setTargetID(items.getFirst().getId());

        ItemEntity item = (ItemEntity) frog.level().getEntity(frog.getTargetID());

        if(item != null && !frog.level().isClientSide()) {
            frog.lookAt(EntityAnchorArgument.Anchor.FEET, item.position());
        }
        frog.setTongueState("EXTENDING");
    }

    @Override
    public void tick() {
        frog.setTargetID(items.getFirst().getId());

        if(frog.getTongueState().equals("IDLE")) {
            if(timer >= 20) {
                frog.setTongueState("EXTENDING");
                timer = 0;
            } else {
                timer++;
            }
        }
    }

    @Override
    public void stop() {
        frog.setTargetID(-1);
    }
}
