package com.hexagram2021.ipp.common.register;

import com.hexagram2021.ipp.common.crafting.MusicalInstrumentShadowRecipe;
import com.hexagram2021.ipp.common.crafting.serializers.MusicalInstrumentRecipeSerializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.hexagram2021.ipp.InstrumentPlusPlus.MODID;

public class IPPRecipeSerializers {
	public static final DeferredRegister<RecipeSerializer<?>> REGISTER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MODID);

	public static final RegistryObject<MusicalInstrumentRecipeSerializer<MusicalInstrumentShadowRecipe>> MUSICAL_INSTRUMENT_SHADOW_SERIALIZER = REGISTER.register(
			"instrument_shadow", () -> new MusicalInstrumentRecipeSerializer<>(MusicalInstrumentShadowRecipe::new)
	);

	public static void init(IEventBus bus) {
		REGISTER.register(bus);
	}
}
