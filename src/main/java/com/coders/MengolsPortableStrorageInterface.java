package com.coders;

import com.coders.items.MPSIItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MengolsPortableStrorageInterface implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("mpsi");

	public static String MODID = "mpsi";


	@Override
	public void onInitialize() {


		MPSIItems.registerModItems();

		LOGGER.info(MODID);
	}
}