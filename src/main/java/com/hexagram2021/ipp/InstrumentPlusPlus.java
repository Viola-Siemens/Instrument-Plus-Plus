package com.hexagram2021.ipp;

import com.hexagram2021.ipp.common.IPPContent;
import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;

public class InstrumentPlusPlus implements ModInitializer {
	public static final String MODID = "ipp";
	public static final String NAME = "Instrument ++";

	public static final Logger LOGGER = LogUtils.getLogger();

	@Override
	public void onInitialize() {
		IPPContent.init();
	}
}
