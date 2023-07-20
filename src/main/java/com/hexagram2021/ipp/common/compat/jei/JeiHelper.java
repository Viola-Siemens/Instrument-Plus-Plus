package com.hexagram2021.ipp.common.compat.jei;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hexagram2021.ipp.InstrumentPlusPlus;
import com.hexagram2021.ipp.common.crafting.MusicalInstrumentShadowRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;

import static com.hexagram2021.ipp.InstrumentPlusPlus.MODID;

@JeiPlugin
public class JeiHelper implements IModPlugin {
	public static final RecipeType<MusicalInstrumentShadowRecipe> INSTRUMENTS = new RecipeType<>(MusicalInstrumentCategory.UID,  MusicalInstrumentShadowRecipe.class);

	private static final ResourceLocation UID = new ResourceLocation(MODID, "main");

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
	}

	@Override
	public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) { }

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		InstrumentPlusPlus.LOGGER.info("Adding EC recipes to JEI!!");
		registration.addRecipes(JeiHelper.INSTRUMENTS, getRecipes());
	}

	private List<MusicalInstrumentShadowRecipe> getRecipes() {
		List<MusicalInstrumentShadowRecipe> shadows = Lists.newArrayList();

		Map<NoteBlockInstrument, List<ItemStack>> bottoms = Maps.newHashMap();

		ForgeRegistries.BLOCKS.forEach(block -> {
			ItemStack itemStack = new ItemStack(block.asItem());
			if(!itemStack.isEmpty()) {
				NoteBlockInstrument instrument = block.defaultBlockState().instrument();
				bottoms.computeIfAbsent(instrument, ignored -> Lists.newArrayList()).add(itemStack);
			}
		});

		bottoms.forEach((instrument, blocks) -> {
			Ingredient bottom = Ingredient.of(blocks.stream());
			ResourceLocation id = new ResourceLocation(MODID, "instrument/" + instrument.getSerializedName());
			shadows.add(new MusicalInstrumentShadowRecipe(id, bottom, instrument));
		});

		return shadows;
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(Items.NOTE_BLOCK), JeiHelper.INSTRUMENTS);
	}
}
