package com.elecbug.plantia.entities;

import com.elecbug.plantia.guis.ManaFurnaceMenu;
import com.elecbug.plantia.registries.ModRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.ItemStackHandler;

public class ManaFurnaceBlockEntity extends BlockEntity implements MenuProvider {
    private static final int SLOT_INPUT = 0;
    private static final int SLOT_FUEL = 1;

    private static final int MAX_MANA = 12800;
    private int manaStored = 0;
    private int burnTime = 0;
    private int manaConversionCooldown = 20;

    private final ContainerData containerData = new SimpleContainerData(1) {
        @Override
        public int get(int index) {
            return index == 0 ? manaStored : MAX_MANA;
        }

        @Override
        public void set(int index, int value) {
            manaStored = value;
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    private final ItemStackHandler itemHandler = new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @SuppressWarnings("null")
        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            if (slot == SLOT_INPUT) {
                return stack.getItem() == ModRegistry.MANA_SHARD.get();
            } else if (slot == SLOT_FUEL) {
                return ForgeHooks.getBurnTime(stack, null) > 0;
            }

            return super.isItemValid(slot, stack);
        }
    };

    public ManaFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistry.MANA_FURNACE_ENTITY.get(), pos, state);
    }

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }

    public ContainerData getContainerData() {
        return containerData;
    }

    @Override
    public Component getDisplayName() {
        return new TranslatableComponent("container.mana_furnace");
    }

    @SuppressWarnings("null")
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
        return new ManaFurnaceMenu(id, playerInventory, this, containerData);
    }

    public int getBurnTime() {
        return burnTime;
    }

    public void addMana(int amount) {
        this.manaStored = Math.min(manaStored + amount, MAX_MANA);
        setChanged();
    }

    public void consumeMana(int amount) {
        this.manaStored = Math.max(manaStored - amount, 0);
        setChanged();
    }

    public boolean stillValid(Player player) {
        return player.distanceToSqr(this.worldPosition.getX() + 0.5, this.worldPosition.getY() + 0.5, this.worldPosition.getZ() + 0.5) <= 64;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, ManaFurnaceBlockEntity entity) {
        if (level.isClientSide) {
            return;
        }
    
        ItemStack inputStack = entity.itemHandler.getStackInSlot(SLOT_INPUT);
        ItemStack fuelStack = entity.itemHandler.getStackInSlot(SLOT_FUEL);
    
        if (entity.burnTime <= 0 && !fuelStack.isEmpty() && !inputStack.isEmpty()) {
            int fuelBurnTime = ForgeHooks.getBurnTime(fuelStack, null);
            
            if (fuelBurnTime > 0) {
                entity.burnTime += fuelBurnTime;
                fuelStack.shrink(1);
                entity.setChanged();
            }
        }
    
        if (entity.burnTime > 0 && !inputStack.isEmpty() && entity.manaStored < MAX_MANA) {
            entity.manaConversionCooldown--;
    
            if (entity.manaConversionCooldown <= 0) {
                entity.addMana(100);
                inputStack.shrink(1);
                entity.manaConversionCooldown = 200;
            }
    
            entity.burnTime--;
            entity.setChanged();
        }
    
        if (entity.burnTime <= 0) {
            entity.burnTime = 0;
        }
    }
}
