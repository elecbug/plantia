package com.elecbug.plantia.registries;

import com.elecbug.plantia.ModMain;
import com.elecbug.plantia.blocks.MagicPlant;
import com.elecbug.plantia.blocks.ManaOreBlock;
import com.elecbug.plantia.items.MagicEssence;
import com.elecbug.plantia.items.MagicSeed;
import com.elecbug.plantia.items.ManaOre;
import com.elecbug.plantia.items.ManaShard;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ModMain.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModMain.MOD_ID);

    public static final RegistryObject<Block> MAGIC_PLANT = BLOCKS.register("magic_plant", MagicPlant::new);
    public static final RegistryObject<Block> MANA_ORE_BLOCK = BLOCKS.register("mana_ore", ManaOreBlock::new);

    public static final RegistryObject<Item> MAGIC_SEED = ITEMS.register("magic_seed", MagicSeed::new);
    public static final RegistryObject<Item> MAGIC_ESSENCE = ITEMS.register("magic_essence", MagicEssence::new);
    public static final RegistryObject<Item> MANA_ORE = ITEMS.register("mana_ore", ManaOre::new );
    public static final RegistryObject<Item> MANA_SHARD = ITEMS.register("mana_shard", ManaShard::new);

    static {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}