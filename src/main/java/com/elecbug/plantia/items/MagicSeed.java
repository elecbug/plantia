package com.elecbug.plantia.items;

import com.elecbug.plantia.registries.ModRegistry;
import com.elecbug.plantia.tabs.ModCreativeTab;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public class MagicSeed extends BlockItem {
    public MagicSeed() {
        super(ModRegistry.MAGIC_PLANT.get(), new Item.Properties().tab(ModCreativeTab.PLANTIA_TAB));
    }

    @Override
    public String getDescriptionId() {
        return "item.plantia.magic_seed";
    }
}
