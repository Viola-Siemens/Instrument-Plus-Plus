package com.hexagram2021.ipp.common.compat.jei;

import com.hexagram2021.ipp.InstrumentPlusPlus;
import com.hexagram2021.ipp.common.crafting.MusicalInstrumentShadowRecipe;
import com.hexagram2021.ipp.common.crafting.cache.CachedRecipeList;
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

import java.util.ArrayList;
import java.util.List;

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
		registration.addRecipes(JeiHelper.INSTRUMENTS, getRecipes(MusicalInstrumentShadowRecipe.recipeList));
	}

	@SuppressWarnings("SameParameterValue")
	private <T extends Recipe<?>> List<T> getRecipes(CachedRecipeList<T> cachedList) {
		return new ArrayList<>(cachedList.getRecipes(Minecraft.getInstance().level));
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(Items.NOTE_BLOCK), JeiHelper.INSTRUMENTS);
	}
}
