package com.hexagram2021.ipp.common.compat.jei;

import com.hexagram2021.ipp.common.crafting.MusicalInstrumentShadowRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static com.hexagram2021.ipp.InstrumentPlusPlus.MODID;

public class MusicalInstrumentCategory implements IRecipeCategory<MusicalInstrumentShadowRecipe> {
	public static final int width = 96;
	public static final int height = 32;
	public static final ResourceLocation UID = new ResourceLocation(MODID, "musical_instrument");
	public static final ResourceLocation TEXTURE = new ResourceLocation(MODID, "textures/gui/musical_instrument.png");

	private final IDrawable background;
	private final IDrawable icon;

	public MusicalInstrumentCategory(IGuiHelper guiHelper) {
		this.background = guiHelper.createDrawable(TEXTURE, 0, 0, width, height);
		this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Items.NOTE_BLOCK));
	}

	@Override
	public Component getTitle() {
		return Component.translatable("jei.ipp.musical_instrument");
	}

	@Override
	public IDrawable getBackground() {
		return this.background;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public RecipeType<MusicalInstrumentShadowRecipe> getRecipeType() {
		return JeiHelper.INSTRUMENTS;
	}

	@Override
	public void draw(MusicalInstrumentShadowRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics transform, double mouseX, double mouseY) {
		Component timbre;
		if(recipe.instrument().isTunable()) {
			timbre = Component.translatable("gui.ipp.jei.instrument.%s".formatted(recipe.instrument().getSerializedName()));
		} else {
			timbre = Component.translatable("gui.ipp.jei.mob.%s".formatted(recipe.instrument().getSerializedName()));
		}
		Component tip = Component.translatable("gui.ipp.jei.instrument.tip", timbre);
		Minecraft minecraft = Minecraft.getInstance();
		int stringWidth = minecraft.font.width(tip);
		transform.drawString(minecraft.font, tip, this.background.getWidth() - stringWidth, 12, 0xFF808080);
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, MusicalInstrumentShadowRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 3, 8).addIngredients(recipe.bottom());
	}
}
