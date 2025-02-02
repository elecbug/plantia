package com.elecbug.plantia.registries;

import com.elecbug.plantia.ModMain;
import com.elecbug.plantia.blocks.MagicPlant;
import com.elecbug.plantia.blocks.ManaFurnaceBlock;
import com.elecbug.plantia.blocks.ManaOreBlock;
import com.elecbug.plantia.entities.ManaFurnaceBlockEntity;
import com.elecbug.plantia.guis.ManaFurnaceMenu;
import com.elecbug.plantia.guis.ManaFurnaceScreen;
import com.elecbug.plantia.items.MagicEssence;
import com.elecbug.plantia.items.MagicSeed;
import com.elecbug.plantia.items.ManaFurnace;
import com.elecbug.plantia.items.ManaOre;
import com.elecbug.plantia.items.ManaShard;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ModMain.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModMain.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, ModMain.MOD_ID);
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, ModMain.MOD_ID);

    public static final RegistryObject<Block> MAGIC_PLANT = BLOCKS.register("magic_plant", MagicPlant::new);
    public static final RegistryObject<Block> MANA_ORE_BLOCK = BLOCKS.register("mana_ore", ManaOreBlock::new);
    public static final RegistryObject<Block> MANA_FURNACE_BLOCK = BLOCKS.register("mana_furnace", ManaFurnaceBlock::new);

    public static final RegistryObject<Item> MAGIC_SEED = ITEMS.register("magic_seed", MagicSeed::new);
    public static final RegistryObject<Item> MAGIC_ESSENCE = ITEMS.register("magic_essence", MagicEssence::new);
    public static final RegistryObject<Item> MANA_ORE = ITEMS.register("mana_ore", ManaOre::new );
    public static final RegistryObject<Item> MANA_SHARD = ITEMS.register("mana_shard", ManaShard::new);
    public static final RegistryObject<Item> MANA_FURNACE_ITEM = ITEMS.register("mana_furnace", ManaFurnace::new);

    @SuppressWarnings("null")
    public static final RegistryObject<BlockEntityType<ManaFurnaceBlockEntity>> MANA_FURNACE_ENTITY =
        BLOCK_ENTITIES.register("mana_furnace", () ->
            BlockEntityType.Builder.of(ManaFurnaceBlockEntity::new, MANA_FURNACE_BLOCK.get()).build(null));

    public static final RegistryObject<MenuType<ManaFurnaceMenu>> MANA_FURNACE_MENU =
        MENUS.register("mana_furnace",
            () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockEntity blockEntity = inv.player.level.getBlockEntity(data.readBlockPos());
                if (blockEntity instanceof ManaFurnaceBlockEntity) {
                    return new ManaFurnaceMenu(windowId, inv, (ManaFurnaceBlockEntity) blockEntity, ((ManaFurnaceBlockEntity) blockEntity).getContainerData());
                }
                throw new IllegalStateException("Invalid block entity at " + data.readBlockPos());
            }));
            
    static {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCK_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        MENUS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public class ModScreens {
        @SubscribeEvent
        public static void registerScreens(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                MenuScreens.register(ModRegistry.MANA_FURNACE_MENU.get(), ManaFurnaceScreen::new);
            });
        }
    }
}