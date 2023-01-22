package com.hexagram2021.ipp.common;

import com.hexagram2021.ipp.common.register.IPPRecipeSerializers;
import com.hexagram2021.ipp.common.register.IPPRecipes;
import com.hexagram2021.ipp.common.register.IPPSoundEvents;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

import java.util.function.Consumer;

import static com.hexagram2021.ipp.InstrumentPlusPlus.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class IPPContent {
	public static void modConstruction(IEventBus bus, Consumer<Runnable> runLater) {
		IPPRecipes.init(bus);
		IPPRecipeSerializers.init(bus);
	}

	public static void init() {
	}

	@SubscribeEvent
	public static void onRegister(RegisterEvent event) {
		IPPSoundEvents.init(event);
	}
}
