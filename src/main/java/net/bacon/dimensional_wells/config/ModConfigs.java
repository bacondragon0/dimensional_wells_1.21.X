package net.bacon.dimensional_wells.config;

import com.mojang.datafixers.util.Pair;
import net.bacon.dimensional_wells.DimensionalWells;


public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static boolean ITEM_DISINTEGRATION;
    public static double BUILDING_BLOCK_CONVERSION_CHANCE;
    public static double AMETHYST_SHARD_TO_ECHO_SHARD_CHANCE;
    public static double QUARTZ_TO_AMETHYST_SHARD_CHANCE;



    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(DimensionalWells.MOD_ID + "_config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("item.disintegration", true), "boolean # Enable/Disable item disintegration mechanic, if set to 'false' all items will convert 100% of the time");
        configs.addKeyValuePair(new Pair<>("building.block.convert", 1.0), "double, ex: 0.70 means 70% to convert item, 30% to disintegrate. # Chance to convert all building blocks, ex: stone -> blackstone");
        configs.addKeyValuePair(new Pair<>("amethyst_shard.to.echo_shard.convert", 0.7), "double # Sculk Well");
        configs.addKeyValuePair(new Pair<>("quartz.to.amethyst_shard.convert", 0.7), "double # Sculk Well");
    }

    private static void assignConfigs() {
        ITEM_DISINTEGRATION = CONFIG.getOrDefault("item.disintegration", true);
        BUILDING_BLOCK_CONVERSION_CHANCE = CONFIG.getOrDefault("building.block.convert", 1.0);
        AMETHYST_SHARD_TO_ECHO_SHARD_CHANCE = CONFIG.getOrDefault("amethyst_shard.to.echo_shard.convert", 0.7);
        QUARTZ_TO_AMETHYST_SHARD_CHANCE = CONFIG.getOrDefault("quartz.to.amethyst_shard.convert", 0.7);

        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}