package com.hexagram2021.ipp.common.register;

import com.hexagram2021.ipp.common.crafting.MusicalInstrumentShadowRecipe;
import com.hexagram2021.ipp.common.crafting.serializers.MusicalInstrumentRecipeSerializer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import static com.hexagram2021.ipp.InstrumentPlusPlus.MODID;

public class IPPRecipeSerializers {

	public static final MusicalInstrumentRecipeSerializer<MusicalInstrumentShadowRecipe> MUSICAL_INSTRUMENT_SHADOW_SERIALIZER = Registry.register(
			Registry.RECIPE_SERIALIZER, new ResourceLocation(MODID, "trade_shadow"),
			new MusicalInstrumentRecipeSerializer<>(MusicalInstrumentShadowRecipe::new)
	);

	public static void init() {
	}
}
