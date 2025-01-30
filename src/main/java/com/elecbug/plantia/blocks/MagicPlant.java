package com.elecbug.plantia.blocks;

import com.elecbug.plantia.registries.ModRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MagicPlant extends CropBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;

    public MagicPlant() {
        super(BlockBehaviour.Properties.copy(net.minecraft.world.level.block.Blocks.WHEAT)
            .randomTicks()
            .noOcclusion()
            .sound(SoundType.CROP));
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModRegistry.MAGIC_SEED.get();
    }

    @SuppressWarnings("null")
    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @SuppressWarnings("null")
    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }
}

