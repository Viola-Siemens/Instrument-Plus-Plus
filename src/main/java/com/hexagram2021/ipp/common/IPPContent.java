package com.hexagram2021.ipp.common;

import com.hexagram2021.ipp.common.register.IPPRecipeSerializers;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.function.Consumer;

public class IPPContent {
	public static void modConstruction(IEventBus bus, Consumer<Runnable> runLater) {
		IPPRecipeSerializers.init(bus);
	}

	public static void init() {
	}
}
