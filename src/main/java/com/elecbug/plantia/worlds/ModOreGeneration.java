package com.elecbug.plantia.worlds;

import java.util.List;
import java.util.function.Supplier;

import com.elecbug.plantia.ModMain;
import com.elecbug.plantia.registries.ModRegistry;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModOreGeneration {
    public static final Supplier<Holder<ConfiguredFeature<?, ?>>> MANA_ORE_CONFIGURED_FEATURE =
        () -> Holder.direct(new ConfiguredFeature<>(
            Feature.ORE,
            new OreConfiguration(
                List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModRegistry.MANA_ORE_BLOCK.get().defaultBlockState())), 9
            )
        ));

    public static final Supplier<Holder<PlacedFeature>> MANA_ORE_PLACED_FEATURE =
        () -> Holder.direct(new PlacedFeature(
            MANA_ORE_CONFIGURED_FEATURE.get(),
            List.of(
                CountPlacement.of(10),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(128)),
                BiomeFilter.biome()
            )
        ));

    @SubscribeEvent
    public static void addOresToBiomes(BiomeLoadingEvent event) {
        event.getGeneration().addFeature(
            GenerationStep.Decoration.UNDERGROUND_ORES,
            MANA_ORE_PLACED_FEATURE.get()
        );
    }
}
