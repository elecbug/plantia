package com.elecbug.plantia.items;

import com.elecbug.plantia.tabs.ModCreativeTab;

import net.minecraft.world.item.Item;

public class ManaShard extends Item {
    public ManaShard() {
        super(new Item.Properties().tab(ModCreativeTab.PLANTIA_TAB));
    }
}