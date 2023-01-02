package com.hexagram2021.ipp.common.compat.jei;

import com.hexagram2021.ipp.InstrumentPlusPlus;
import com.hexagram2021.ipp.common.crafting.MusicalInstrumentShadowRecipe;
import com.hexagram2021.ipp.common.register.IPPRecipes;
import com.hexagram2021.ipp.mixin.RecipeManagerAccess;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.hexagram2021.ipp.InstrumentPlusPlus.MODID;

@JeiPlugin
public class JeiHelper implements IModPlugin {
	public static final RecipeType<MusicalInstrumentShadowRecipe> INSTRUMENTS = new RecipeType<>(MusicalInstrumentCategory.UID,  MusicalInstrumentShadowRecipe.class);

	private static final ResourceLocation UID = new ResourceLocation(MODID, "main");
	public static IDrawableStatic slotDrawable;

	@Override
	public ResourceLocation getPluginUid() {
		return UID;
	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistration subtypeRegistry) { }

	@Override
	public void registerIngredients(IModIngredientRegistration registry) { }

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		//Recipes
		IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
		registry.addRecipeCategories(
				new MusicalInstrumentCategory(guiHelper)
		);

		slotDrawable = guiHelper.getSlotDrawable();
	}

	@Override
	public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) { }

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		InstrumentPlusPlus.LOGGER.info("Adding EC recipes to JEI!!");
		registration.addRecipes(JeiHelper.INSTRUMENTS, getRecipes(IPPRecipes.MUSICAL_INSTRUMENT_SHADOW_TYPE));
	}

	@SuppressWarnings({"SameParameterValue", "unchecked", "ConstantConditions"})
	private <T extends Recipe<?>> List<T> getRecipes(net.minecraft.world.item.crafting.RecipeType<T> type) {
		Map<ResourceLocation, Recipe<?>> map = ((RecipeManagerAccess)(Minecraft.getInstance().getConnection().getRecipeManager())).ipp_getRecipes().get(type);
		return map.values().stream().map(r -> (T)r).collect(Collectors.toList());
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(Items.NOTE_BLOCK), JeiHelper.INSTRUMENTS);
	}
}
