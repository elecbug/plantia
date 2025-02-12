package com.elecbug.plantia;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.elecbug.plantia.registries.ModRegistry;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Setting up client configurations...");

        ItemBlockRenderTypes.setRenderLayer(ModRegistry.MAGIC_PLANT.get(), RenderType.cutout());
    }

    static {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientModEvents::clientSetup);
    }
}
