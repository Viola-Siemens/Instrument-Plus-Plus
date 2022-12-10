package com.hexagram2021.ipp.common.register;

import com.hexagram2021.ipp.common.crafting.MusicalInstrumentShadowRecipe;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.hexagram2021.ipp.InstrumentPlusPlus.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class IPPRecipes {
	public static RecipeType<MusicalInstrumentShadowRecipe> MUSICAL_INSTRUMENT_SHADOW_TYPE;

	@SubscribeEvent
	public static void registerRecipeTypes(RegistryEvent.Register<Block> event) {
		MUSICAL_INSTRUMENT_SHADOW_TYPE = register("instrument_shadow");
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
