package com.elecbug.plantia.guis;

import com.elecbug.plantia.entities.ManaFurnaceBlockEntity;
import com.elecbug.plantia.registries.ModRegistry;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ManaFurnaceMenu extends AbstractContainerMenu {
    private final ManaFurnaceBlockEntity blockEntity;
    private final ContainerData data;

    public ManaFurnaceMenu(int id, Inventory playerInventory, ManaFurnaceBlockEntity entity, ContainerData data) {
        super(ModRegistry.MANA_FURNACE_MENU.get(), id);
        this.blockEntity = entity;
        this.data = data;

        ItemStackHandler itemHandler = entity.getItemHandler();

        this.addSlot(new SlotItemHandler(itemHandler, 0, 56, 17));
        this.addSlot(new SlotItemHandler(itemHandler, 1, 56, 53));

        addPlayerInventory(playerInventory);
        addDataSlots(data);
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int k = 0; k < 9; k++) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    @SuppressWarnings("null")
    @Override
    public boolean stillValid(Player player) {
        return blockEntity.stillValid(player);
    }

    @SuppressWarnings("null")
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack originalStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack stackInSlot = slot.getItem();
            originalStack = stackInSlot.copy();

            if (index < 2) {
                if (!this.moveItemStackTo(stackInSlot, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (stackInSlot.getItem() == ModRegistry.MANA_SHARD.get()) {
                    if (!this.moveItemStackTo(stackInSlot, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                }
                else if (net.minecraftforge.common.ForgeHooks.getBurnTime(stackInSlot, null) > 0) {
                    if (!this.moveItemStackTo(stackInSlot, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (stackInSlot.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            slot.onTake(player, stackInSlot);
        }

        return originalStack;
    }

    public ContainerData getContainerData() {
        return data;
    }

    public int getBurnTime() {
        return blockEntity.getBurnTime();
    }
}
