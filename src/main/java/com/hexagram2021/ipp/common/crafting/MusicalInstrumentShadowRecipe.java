package com.hexagram2021.ipp.common.crafting;

import com.hexagram2021.ipp.common.crafting.cache.CachedRecipeList;
import com.hexagram2021.ipp.common.register.IPPRecipeSerializers;
import com.hexagram2021.ipp.common.register.IPPRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public record MusicalInstrumentShadowRecipe(ResourceLocation id, Ingredient bottom, NoteBlockInstrument instrument) implements Recipe<Container> {
	public static final CachedRecipeList<MusicalInstrumentShadowRecipe> recipeList = new CachedRecipeList<>(
			IPPRecipes.MUSICAL_INSTRUMENT_SHADOW_TYPE,
			MusicalInstrumentShadowRecipe.class
	);

	@Override
	public boolean canCraftInDimensions(int wid, int hgt) {
		return true;
	}

	@Override @NotNull
	public RecipeSerializer<?> getSerializer() {
		return IPPRecipeSerializers.MUSICAL_INSTRUMENT_SHADOW_SERIALIZER.get();
	}

	@Override @NotNull
	public NonNullList<Ingredient> getIngredients() {
		return NonNullList.of(this.bottom);
	}

	@SuppressWarnings("NullableProblems")
	@Override @Nullable
	public ItemStack assemble(@NotNull Container container) {
		return null;
	}

	@SuppressWarnings("NullableProblems")
	@Override @Nullable
	public ItemStack getResultItem() {
		return null;
	}

	@Override
	public boolean matches(Container container, @NotNull Level level) {
		return this.bottom.test(container.getItem(0));
	}

	@Override @NotNull
	public ResourceLocation getId() {
		return this.id;
	}

	@Override @NotNull
	public RecipeType<?> getType() {
		return IPPRecipes.MUSICAL_INSTRUMENT_SHADOW_TYPE.get();
	}
}
