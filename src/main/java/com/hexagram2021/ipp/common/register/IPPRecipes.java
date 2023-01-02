package com.hexagram2021.ipp.common.register;

import com.hexagram2021.ipp.common.crafting.MusicalInstrumentShadowRecipe;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

import static com.hexagram2021.ipp.InstrumentPlusPlus.MODID;

public class IPPRecipes {
	public static RecipeType<MusicalInstrumentShadowRecipe> MUSICAL_INSTRUMENT_SHADOW_TYPE = register("instrument_shadow");

	public static void init() {
	}

	@SuppressWarnings("SameParameterValue")
	private static <T extends Recipe<?>> RecipeType<T> register(String path) {
		ResourceLocation name = new ResourceLocation(MODID, path);
		return Registry.register(Registry.RECIPE_TYPE, name, new RecipeType<T>() {
			@Override
			public String toString() {
				return name.toString();
			}
		});
	}
}
