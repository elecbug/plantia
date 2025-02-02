package com.elecbug.plantia;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("plantia")
public class ModMain {
    public static final String MOD_ID = "plantia";
    private static final Logger LOGGER = LogManager.getLogger();

    public ModMain() {
        LOGGER.info("Initializing Plantia Mod");

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Setting up common configurations...");
    }
}