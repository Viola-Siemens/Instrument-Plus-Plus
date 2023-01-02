package com.hexagram2021.ipp.common;

import com.hexagram2021.ipp.common.register.IPPRecipeSerializers;
import com.hexagram2021.ipp.common.register.IPPRecipes;
import com.hexagram2021.ipp.common.register.IPPSoundEvents;

public class IPPContent {
	public static void init() {
		IPPRecipes.init();
		IPPRecipeSerializers.init();
		IPPSoundEvents.init();
	}
}
