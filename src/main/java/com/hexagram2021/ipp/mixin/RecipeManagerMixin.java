package com.hexagram2021.ipp.mixin;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.hexagram2021.ipp.common.crafting.MusicalInstrumentShadowRecipe;
import com.hexagram2021.ipp.common.register.IPPRecipes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

import static com.hexagram2021.ipp.InstrumentPlusPlus.MODID;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {
	@Shadow private Map<RecipeType<?>, Map<ResourceLocation, Recipe<?>>> recipes;

	@Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V", at = @At(value = "RETURN"))
	protected void addShadowRecipes(Map<ResourceLocation, JsonElement> map, ResourceManager resourceManager, ProfilerFiller profilerFiller, CallbackInfo ci) {
		Map<RecipeType<?>, Map<ResourceLocation, Recipe<?>>> editedRecipes = Maps.newHashMap(this.recipes);
		editedRecipes.compute(IPPRecipes.MUSICAL_INSTRUMENT_SHADOW_TYPE, (key, customMap) -> {
			Map<ResourceLocation, Recipe<?>> shadows = Maps.newHashMap();
			if(customMap != null) {
				shadows.putAll(customMap);
			}

			Map<NoteBlockInstrument, List<ItemStack>> bottoms = Maps.newHashMap();

			Registry.BLOCK.forEach(block -> {
				ItemStack itemStack = new ItemStack(block.asItem());
				if(!itemStack.isEmpty()) {
					NoteBlockInstrument instrument = NoteBlockInstrument.byState(block.defaultBlockState());
					bottoms.computeIfAbsent(instrument, ignored -> Lists.newArrayList()).add(itemStack);
				}
			});

			bottoms.forEach((instrument, blocks) -> {
				Ingredient bottom = Ingredient.of(blocks.stream());
				ResourceLocation id = new ResourceLocation(MODID, "instrument/" + instrument.getSerializedName());
				shadows.put(id, new MusicalInstrumentShadowRecipe(id, bottom, instrument));
			});

			return shadows;
		});
		this.recipes = ImmutableMap.copyOf(editedRecipes);
	}
}
