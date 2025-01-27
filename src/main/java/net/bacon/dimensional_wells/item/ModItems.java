package net.bacon.dimensional_wells.item;

import net.bacon.dimensional_wells.DimensionalWells;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(DimensionalWells.MOD_ID, name), item);
    }

    public static void registerModItems() {
        DimensionalWells.LOGGER.info("Registering mod items for " + DimensionalWells.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
        });

    }
}
