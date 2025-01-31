package com.elecbug.plantia.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ManaOreBlock extends Block {
    public ManaOreBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.IRON_ORE));
    }
}
