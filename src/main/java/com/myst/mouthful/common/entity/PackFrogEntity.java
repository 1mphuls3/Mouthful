package com.myst.mouthful.common.entity;

import com.myst.mouthful.common.menu.PackFrogMenu;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class PackFrogEntity extends TamableAnimal implements ContainerListener, HasCustomInventoryScreen {
    public final AnimationState openState = new AnimationState();
    public final AnimationState closeState = new AnimationState();

    static int INVENTORY_SIZE = 27;
    protected SimpleContainer inventory;
    private static final EntityDataAccessor<Boolean> MOUTH_OPEN =
            SynchedEntityData.defineId(PackFrogEntity.class, EntityDataSerializers.BOOLEAN);

    public PackFrogEntity(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        this.createInventory();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        goalSelector.addGoal(0, new PanicGoal(this, 0.18f));

        goalSelector.addGoal(1, new RandomLookAroundGoal(this));
        goalSelector.addGoal(2, new RandomStrollGoal(this, 0.15f, 60));

        goalSelector.addGoal(3, new TemptGoal(this, 0.15f, stack -> stack.is(Items.SLIME_BALL), false));
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (this.isAlive()) {
            ItemStack stack = player.getItemInHand(hand);
            if (stack.is(Items.NAME_TAG)) return InteractionResult.PASS;

            this.openCustomInventoryScreen(player);
            return InteractionResult.sidedSuccess(this.level().isClientSide());
        }
        return InteractionResult.PASS;
    }

    protected void createInventory() {
        SimpleContainer simpleContainer = this.inventory;
        this.inventory = new SimpleContainer(INVENTORY_SIZE);
        if (simpleContainer != null) {
            simpleContainer.removeListener(this);

            for (int i = 0; i < INVENTORY_SIZE; ++i) {
                ItemStack itemstack = simpleContainer.getItem(i);
                if (!itemstack.isEmpty()) {
                    this.inventory.setItem(i, itemstack.copy());
                }
            }
        }

        this.inventory.addListener(this);
    }

    @Override
    public void openCustomInventoryScreen(Player player) {
        if (!this.level().isClientSide) {
            player.openMenu(new SimpleMenuProvider((id, playerInventory, playerEntity) ->
                new PackFrogMenu(id, playerInventory, inventory, this), Component.translatable("entity.mouthful.packfrog")));
            setMouthOpen(!isMouthOpen());
        }
    }

    @Override
    public void remove(RemovalReason reason) {
        if (reason == RemovalReason.KILLED) {
            this.inventory.removeAllItems().forEach(this::spawnAtLocation);
            this.spawnAnim();
        }
        super.remove(reason);
    }

    @Override
    public void containerChanged(Container container) {

    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(Items.SLIME_BALL);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        ListTag tags = new ListTag();

        for (int i = 0; i < this.inventory.getContainerSize(); ++i) {
            ItemStack itemstack = this.inventory.getItem(i);
            if (!itemstack.isEmpty()) {
                CompoundTag compoundTag = new CompoundTag();
                compoundTag.putByte("Slot", (byte) i);
                tags.add(itemstack.save(this.level().registryAccess(), compoundTag));
            }
        }

        tag.put("Items", tags);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        ListTag tags = tag.getList("Items", 10);

        for (int i = 0; i < tags.size(); ++i) {
            CompoundTag compoundtag = tags.getCompound(i);
            int j = compoundtag.getByte("Slot") & 255;
            if (j < this.inventory.getContainerSize()) {
                this.inventory.setItem(j, ItemStack.parseOptional(this.level().registryAccess(), compoundtag));
            }
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return LivingEntity.createLivingAttributes().add(Attributes.FOLLOW_RANGE, 16.0F).add(Attributes.MAX_HEALTH, 10f);
    }

    private void setupAnimStates() {
        if(isMouthOpen() && !openState.isStarted()) {
            closeState.stop();
            openState.start(this.tickCount);
        }
        else if(!isMouthOpen() && !closeState.isStarted()){
            openState.stop();
            closeState.start(this.tickCount);
        }
    }

    @Override
    protected boolean isImmobile() {
        return super.isImmobile() || this.isMouthOpen();
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            setupAnimStates();
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);

        builder.define(MOUTH_OPEN, false);
    }

    public void setMouthOpen(boolean open) {
        this.entityData.set(MOUTH_OPEN, open);
    }

    public boolean isMouthOpen() {
        return this.entityData.get(MOUTH_OPEN);
    }
}
