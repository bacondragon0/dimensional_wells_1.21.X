package net.bacon.dimensional_wells;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DimensionalWells implements ModInitializer {
	public static final String MOD_ID = "dimensional_wells";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("Hello world!");
	}
}