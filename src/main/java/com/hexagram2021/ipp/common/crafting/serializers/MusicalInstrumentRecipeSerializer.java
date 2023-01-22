package com.hexagram2021.ipp.common.crafting.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hexagram2021.ipp.common.crafting.MusicalInstrumentShadowRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class MusicalInstrumentRecipeSerializer<T extends MusicalInstrumentShadowRecipe> extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<T> {
	private final MusicalInstrumentRecipeSerializer.Creator<T> factory;

	public MusicalInstrumentRecipeSerializer(MusicalInstrumentRecipeSerializer.Creator<T> creator) {
		this.factory = creator;
	}

	@Deprecated
	@Override @NotNull
	public T fromJson(@NotNull ResourceLocation id, @NotNull JsonObject json) {
		JsonElement jsonelement =
				GsonHelper.isArrayNode(json, "ingredient") ?
						GsonHelper.getAsJsonArray(json, "ingredient") :
						GsonHelper.getAsJsonObject(json, "ingredient");
		Ingredient ingredient = Ingredient.fromJson(jsonelement);
		String instrument = GsonHelper.getAsString(json, "instrument");

		return this.factory.create(id, ingredient, Arrays.stream(NoteBlockInstrument.values()).filter(ni -> ni.getSerializedName().equals(instrument)).findAny().orElseThrow());
	}

	@Override @Nullable
	public T fromNetwork(@NotNull ResourceLocation id, @NotNull FriendlyByteBuf buf) {
		Ingredient ingredient = Ingredient.fromNetwork(buf);
		String instrument = buf.readUtf();

		return this.factory.create(id, ingredient, Arrays.stream(NoteBlockInstrument.values()).filter(ni -> ni.getSerializedName().equals(instrument)).findAny().orElseThrow());
	}

	@Override
	public void toNetwork(@NotNull FriendlyByteBuf buf, @NotNull T recipe) {
		recipe.bottom().toNetwork(buf);
		buf.writeUtf(recipe.instrument().getSerializedName());
	}


	public interface Creator<T extends MusicalInstrumentShadowRecipe> {
		T create(ResourceLocation id, Ingredient bottom, NoteBlockInstrument instrument);
	}
}
