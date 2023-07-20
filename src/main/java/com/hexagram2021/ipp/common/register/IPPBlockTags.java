package com.hexagram2021.ipp.common.register;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import static com.hexagram2021.ipp.InstrumentPlusPlus.MODID;

public class IPPBlockTags {
	public static final TagKey<Block> BASALTS = create("basalts");
	public static final TagKey<Block> BLACKSTONES = create("blackstones");
	public static final TagKey<Block> SNOWS = create("snows");
	public static final TagKey<Block> END_STONES = create("end_stones");
	public static final TagKey<Block> COPPER_BLOCKS = create("copper_blocks");
	public static final TagKey<Block> CUT_COPPER_BLOCKS = create("cut_copper_blocks");
	public static final TagKey<Block> DEAD_CORAL_BLOCKS = create("dead_coral_blocks");
	public static final TagKey<Block> GLAZED_TERRACOTTA = create("glazed_terracotta");
	public static final TagKey<Block> NETHER_BRICKS = create("nether_bricks");
	public static final TagKey<Block> AMETHYST_BLOCKS = create("amethyst_blocks");

	private static TagKey<Block> create(String name) {
		return TagKey.create(Registries.BLOCK, new ResourceLocation(MODID, name));
	}
	
	public static void init() {
	
	}
}
