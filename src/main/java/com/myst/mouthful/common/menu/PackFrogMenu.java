package com.myst.mouthful.common.menu;

import com.myst.mouthful.common.entity.PackFrogEntity;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;

public class PackFrogMenu extends ChestMenu {
    private final PackFrogEntity frog;

    public PackFrogMenu(int id, Inventory inventory, Container container, PackFrogEntity frog) {
        super(MenuType.GENERIC_9x3, id, inventory, container, 3);
        this.frog = frog;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        frog.setMouthOpen(false);
    }
}
