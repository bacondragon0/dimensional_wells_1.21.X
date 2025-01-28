package net.bacon.dimensional_wells.config;

import com.mojang.datafixers.util.Pair;
import net.bacon.dimensional_wells.DimensionalWells;


public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static boolean ITEM_DISINTEGRATION;
    public static double BUILDING_BLOCK_CONVERSION_CHANCE;
    // SCULK WELL
    public static double AMETHYST_SHARD_TO_ECHO_SHARD_CHANCE;
    public static double QUARTZ_TO_AMETHYST_SHARD_CHANCE;
    public static double MOSS_TO_SCULK_CHANCE;
    public static double DIRT_TO_MOSS_CHANCE;
    public static double SWEET_BERRY_TO_GLOW_BERRY_CHANCE;
    public static double VINES_TO_GLOW_LICHEN_CHANCE;
    public static double GLASS_TO_TINTED_GLASS_CHANCE;
    // NETHER WELL
    public static double AMETHYST_TO_QUARTZ_CHANCE;
    public static double REDSTONE_TO_GLOWSTONE_DUST_CHANCE;
    public static double OBSIDIAN_TO_CRYING_OBSIDIAN_CHANCE;
    public static double CRYING_OBSIDIAN_TO_OBSIDIAN_CHANCE;
    public static double FUNGUS_TO_MUSHROOM_CHANCE;
    public static double SAND_TO_SOUL_SAND_CHANCE;
    public static double SLIMEBALL_TO_MAGMA_CREAM_CHANCE;
    public static double GRAVEL_TO_MAGMA_BLOCK_CHANCE;
    public static double FLAME_TO_SOUL_FLAME_CHANCE;
    public static double ROSE_BUSH_TO_WITHER_ROSE_CHANCE;
    // END WELL
    public static double TORCH_TO_END_ROD_CHANCE;
    public static double APPLE_TO_CHORUS_FRUIT_CHANCE;
    public static double WATER_BOTTLE_TO_DRAGON_BREATH_CHANCE;
    public static double NAUTILUS_SHELL_TO_SHULKER_SHELL_CHANCE;

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(DimensionalWells.MOD_ID + "_config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("item.disintegration", true), "boolean # Enable/Disable item disintegration mechanic, if set to 'false' all items will convert 100% of the time");
        configs.addKeyValuePair(new Pair<>("building.block.convert", 1.0), "double, ex: 0.70 means 70% to convert item, 30% to disintegrate. # Chance to convert all building blocks, ex: stone -> blackstone");

        configs.addKeyValuePair(new Pair<>("amethyst_shard.to.echo_shard.convert", 0.7), "double # Sculk Well # If chance is set to 0.0, the conversion recipe will be automatically disabled");
        configs.addKeyValuePair(new Pair<>("quartz.to.amethyst_shard.convert", 0.7), "double # Sculk Well");
        configs.addKeyValuePair(new Pair<>("moss.to.sculk.convert", 0.8), "double # Sculk Well");
        configs.addKeyValuePair(new Pair<>("dirt.to.moss.convert", 0.3), "double # Sculk Well");
        configs.addKeyValuePair(new Pair<>("sweet_berry.to.glow_berry.convert", 0.75), "double # Sculk Well");
        configs.addKeyValuePair(new Pair<>("vines.to.glow_lichen.convert", 0.75), "double # Sculk Well");
        configs.addKeyValuePair(new Pair<>("glass.to.tinted_glass.convert", 0.25), "double # Sculk Well");

        configs.addKeyValuePair(new Pair<>("amethyst_shard.to.quartz.convert", 1), "double # Nether Well");
        configs.addKeyValuePair(new Pair<>("redstone.to.glowstone_dust.convert", 0.5), "double # Nether Well");
        configs.addKeyValuePair(new Pair<>("crying_obsidian.to.obsidian.convert", 1), "double # Nether Well");
        configs.addKeyValuePair(new Pair<>("obsidian.to.crying_obsidian.convert", 0.4), "double # Nether Well");
        configs.addKeyValuePair(new Pair<>("fungus.to.mushroom.convert", 0.7), "double # Nether Well # WORKS IN BOTH WAYS");
        configs.addKeyValuePair(new Pair<>("sand.to.soul_sand.convert", 0.5), "double # Nether Well");
        configs.addKeyValuePair(new Pair<>("slimeball.to.magma_cream.convert", 0.5), "double # Nether Well");
        configs.addKeyValuePair(new Pair<>("gravel.to.magma_block.convert", 0.4), "double # Nether Well");
        configs.addKeyValuePair(new Pair<>("flame.to.soul_flame.convert", 0.9), "double # Nether Well # ex: Torch -> Soul Torch");
        configs.addKeyValuePair(new Pair<>("rose_bush.to.wither_rose.convert", 0.1), "double # Nether Well");

        configs.addKeyValuePair(new Pair<>("torch.to.end_rod.convert", 0.3), "double # End Well");
        configs.addKeyValuePair(new Pair<>("apple.to.chorus_fruit.convert", 0.7), "double # End Well");
        configs.addKeyValuePair(new Pair<>("water_bottle.to.dragon_breath.convert", 1), "double # End Well");
        configs.addKeyValuePair(new Pair<>("nautilus_shell.to.shulker_shell.convert", 0.8), "double # End Well");

    }

    private static void assignConfigs() {
        ITEM_DISINTEGRATION = CONFIG.getOrDefault("item.disintegration", true);
        BUILDING_BLOCK_CONVERSION_CHANCE = CONFIG.getOrDefault("building.block.convert", 1.0);

        AMETHYST_SHARD_TO_ECHO_SHARD_CHANCE = CONFIG.getOrDefault("amethyst_shard.to.echo_shard.convert", 0.7);
        QUARTZ_TO_AMETHYST_SHARD_CHANCE = CONFIG.getOrDefault("quartz.to.amethyst_shard.convert", 0.7);
        MOSS_TO_SCULK_CHANCE = CONFIG.getOrDefault("moss.to.sculk.convert", 0.8);
        DIRT_TO_MOSS_CHANCE = CONFIG.getOrDefault("dirt.to.moss.convert", 0.3);
        SWEET_BERRY_TO_GLOW_BERRY_CHANCE = CONFIG.getOrDefault("sweet_berry.to.glow_berry.convert", 0.75);
        VINES_TO_GLOW_LICHEN_CHANCE = CONFIG.getOrDefault("vines.to.glow_lichen.convert", 0.75);
        GLASS_TO_TINTED_GLASS_CHANCE = CONFIG.getOrDefault("glass.to.tinted_glass.convert", 0.25);

        AMETHYST_TO_QUARTZ_CHANCE = CONFIG.getOrDefault("amethyst_shard.to.quartz.convert", 1);
        REDSTONE_TO_GLOWSTONE_DUST_CHANCE = CONFIG.getOrDefault("redstone.to.glowstone_dust.convert", 0.5);

        OBSIDIAN_TO_CRYING_OBSIDIAN_CHANCE = CONFIG.getOrDefault("obsidian.to.crying_obsidian.convert", 0.4);
        CRYING_OBSIDIAN_TO_OBSIDIAN_CHANCE = CONFIG.getOrDefault("crying_obsidian.to.obsidian.convert", 1);
        FUNGUS_TO_MUSHROOM_CHANCE = CONFIG.getOrDefault("fungus.to.mushroom.convert", 0.7);
        SAND_TO_SOUL_SAND_CHANCE = CONFIG.getOrDefault("sand.to.soul_sand.convert", 0.5);
        SLIMEBALL_TO_MAGMA_CREAM_CHANCE = CONFIG.getOrDefault("slimeball.to.magma_cream.convert", 0.5);
        GRAVEL_TO_MAGMA_BLOCK_CHANCE = CONFIG.getOrDefault("gravel.to.magma_block.convert", 0.4);
        FLAME_TO_SOUL_FLAME_CHANCE = CONFIG.getOrDefault("flame.to.soul_flame.convert", 0.9);
        ROSE_BUSH_TO_WITHER_ROSE_CHANCE = CONFIG.getOrDefault("rose_bush.to.wither_rose.convert", 0.1);

        TORCH_TO_END_ROD_CHANCE = CONFIG.getOrDefault("torch.to.end_rod.convert", 0.3);
        APPLE_TO_CHORUS_FRUIT_CHANCE = CONFIG.getOrDefault("apple.to.chorus_fruit.convert", 0.7);
        WATER_BOTTLE_TO_DRAGON_BREATH_CHANCE = CONFIG.getOrDefault("water_bottle.to.dragon_breath.convert", 1);
        NAUTILUS_SHELL_TO_SHULKER_SHELL_CHANCE = CONFIG.getOrDefault("nautilus_shell.to.shulker_shell.convert", 0.8);

        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}