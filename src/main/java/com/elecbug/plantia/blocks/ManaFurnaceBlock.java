package com.elecbug.plantia.blocks;

import com.elecbug.plantia.entities.ManaFurnaceBlockEntity;
import com.elecbug.plantia.registries.ModRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class ManaFurnaceBlock extends BaseEntityBlock {
    public ManaFurnaceBlock() {
        super(Properties.of(Material.METAL)
        .strength(5.0f, 6.0f)
        .requiresCorrectToolForDrops()
        .noOcclusion());
    }

    @SuppressWarnings("null")
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ManaFurnaceBlockEntity(pos, state);
    }

    @SuppressWarnings("null")
    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof ManaFurnaceBlockEntity) {
                NetworkHooks.openGui((ServerPlayer) player, (ManaFurnaceBlockEntity) blockEntity, pos);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @SuppressWarnings("null")
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? null : createTickerHelper(type, ModRegistry.MANA_FURNACE_ENTITY.get(), ManaFurnaceBlockEntity::tick);
    }
}
