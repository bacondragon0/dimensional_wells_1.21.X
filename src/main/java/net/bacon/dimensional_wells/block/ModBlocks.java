package net.bacon.dimensional_wells.block;

import net.bacon.dimensional_wells.DimensionalWells;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block SCULK_WELL_BLOCK = registerBlock("sculk_well_block",
            new Block(AbstractBlock.Settings.create().strength(4f).requiresTool().sounds(BlockSoundGroup.SCULK_SHRIEKER)));


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return  Registry.register(Registries.BLOCK, Identifier.of(DimensionalWells.MOD_ID), block);
    }


    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(DimensionalWells.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }


    public static void  registerModBlocks() {
        DimensionalWells.LOGGER.info("Registering mod blocks for " + DimensionalWells.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries ->
                entries.add(ModBlocks.SCULK_WELL_BLOCK));
    }
}
