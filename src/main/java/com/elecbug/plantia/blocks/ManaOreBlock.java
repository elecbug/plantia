package com.elecbug.plantia.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class ManaOreBlock extends Block {
    public ManaOreBlock() {
        super(BlockBehaviour.Properties.of(Material.STONE)
            .strength(3.0f, 3.0f)
            .requiresCorrectToolForDrops());
    }
}
