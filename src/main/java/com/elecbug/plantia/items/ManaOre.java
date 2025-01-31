package com.elecbug.plantia.items;

import com.elecbug.plantia.registries.ModRegistry;
import com.elecbug.plantia.tabs.ModCreativeTab;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public class ManaOre extends BlockItem {
    public ManaOre() {
        super(ModRegistry.MANA_ORE_BLOCK.get(), new Item.Properties().tab(ModCreativeTab.PLANTIA_TAB));
    }
}
