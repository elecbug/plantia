package com.elecbug.plantia;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;

@Mod("plantia")
public class ModMain {
    public static final String MOD_ID = "plantia";
    private static final Logger LOGGER = LogManager.getLogger();

    public ModMain() {
        LOGGER.info("Hello, Minecraft! And Plantia!");
    }
}
