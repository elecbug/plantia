package com.elecbug.plantia.items;

import com.elecbug.plantia.registries.ModRegistry;
import com.elecbug.plantia.tabs.ModCreativeTab;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public class ManaFurnace extends BlockItem {
    public ManaFurnace() {
        super(ModRegistry.MANA_FURNACE_BLOCK.get(), new Item.Properties().tab(ModCreativeTab.PLANTIA_TAB));
    }
}
