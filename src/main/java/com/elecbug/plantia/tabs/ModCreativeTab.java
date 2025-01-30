package com.elecbug.plantia.tabs;

import com.elecbug.plantia.registries.ModRegistry;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTab {
    public static final CreativeModeTab PLANTIA_TAB = new CreativeModeTab("plantia") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModRegistry.MAGIC_SEED.get());
        }
    };
}