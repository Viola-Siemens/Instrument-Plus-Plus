package com.hexagram2021.ipp.common.register;

import com.hexagram2021.ipp.common.crafting.MusicalInstrumentShadowRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.hexagram2021.ipp.InstrumentPlusPlus.MODID;

public class IPPRecipes {
	private static final DeferredRegister<RecipeType<?>> REGISTER = DeferredRegister.create(Registries.RECIPE_TYPE, MODID);
	public static RegistryObject<RecipeType<MusicalInstrumentShadowRecipe>> MUSICAL_INSTRUMENT_SHADOW_TYPE = register("instrument_shadow");

	public static void init(IEventBus bus) {
		REGISTER.register(bus);
	}

	@SuppressWarnings("SameParameterValue")
	private static <T extends Recipe<?>> RegistryObject<RecipeType<T>> register(String name) {
		return REGISTER.register(name, () -> new RecipeType<>() {
			@Override
			public String toString() {
				return new ResourceLocation(MODID, name).toString();
			}
		});
	}
}
