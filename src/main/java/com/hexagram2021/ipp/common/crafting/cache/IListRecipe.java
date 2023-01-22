package com.hexagram2021.ipp.common.crafting.cache;

import net.minecraft.world.item.crafting.Recipe;

import java.util.List;

public interface IListRecipe {
	List<? extends Recipe<?>> getSubRecipes();
}