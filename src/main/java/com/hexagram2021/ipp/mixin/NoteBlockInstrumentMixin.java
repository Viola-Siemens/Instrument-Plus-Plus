package com.hexagram2021.ipp.mixin;

import com.hexagram2021.ipp.common.register.IPPBlockTags;
import com.hexagram2021.ipp.common.register.IPPSoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;

import static com.hexagram2021.ipp.common.register.IPPInstruments.*;

@Mixin(NoteBlockInstrument.class)
public class NoteBlockInstrumentMixin {
	@SuppressWarnings("unused")
	NoteBlockInstrumentMixin(String enumName, int ord, String name, SoundEvent sound) {
		throw new UnsupportedOperationException("Replaced by Mixin");
	}

	@Shadow @Mutable @Final
	private static NoteBlockInstrument[] $VALUES;

	@Inject(method = "<clinit>", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/block/state/properties/NoteBlockInstrument;$VALUES:[Lnet/minecraft/world/level/block/state/properties/NoteBlockInstrument;", shift = At.Shift.AFTER))
	private static void ipp_injectEnum(CallbackInfo ci) {
		int ordinal = $VALUES.length;
		$VALUES = Arrays.copyOf($VALUES, ordinal + 16);

		BASSOON = $VALUES[ordinal] = (NoteBlockInstrument)(Object)new NoteBlockInstrumentMixin("BASSOON", ordinal, "bassoon", IPPSoundEvents.NOTE_BLOCK_BASSOON);
		CLARINET = $VALUES[ordinal + 1] = (NoteBlockInstrument)(Object)new NoteBlockInstrumentMixin("CLARINET", ordinal + 1, "clarinet", IPPSoundEvents.NOTE_BLOCK_CLARINET);
		CYMBAL = $VALUES[ordinal + 2] = (NoteBlockInstrument)(Object)new NoteBlockInstrumentMixin("CYMBAL", ordinal + 2, "cymbal", IPPSoundEvents.NOTE_BLOCK_CYMBAL);
		ELECTRIC_CLEAN = $VALUES[ordinal + 3] = (NoteBlockInstrument)(Object)new NoteBlockInstrumentMixin("ELECTRIC_CLEAN", ordinal + 3, "electric_clean", IPPSoundEvents.NOTE_BLOCK_ELECTRIC_CLEAN);
		ELECTRIC_OVERDRIVEN = $VALUES[ordinal + 4] = (NoteBlockInstrument)(Object)new NoteBlockInstrumentMixin("ELECTRIC_OVERDRIVEN", ordinal + 4, "electric_overdriven", IPPSoundEvents.NOTE_BLOCK_ELECTRIC_OVERDRIVEN);
		ERHU = $VALUES[ordinal + 5] = (NoteBlockInstrument)(Object)new NoteBlockInstrumentMixin("ERHU", ordinal + 5, "erhu", IPPSoundEvents.NOTE_BLOCK_ERHU);
		FRENCH_HORN = $VALUES[ordinal + 6] = (NoteBlockInstrument)(Object)new NoteBlockInstrumentMixin("FRENCH_HORN", ordinal + 6, "french_horn", IPPSoundEvents.NOTE_BLOCK_FRENCH_HORN);
		GUQIN = $VALUES[ordinal + 7] = (NoteBlockInstrument)(Object)new NoteBlockInstrumentMixin("GUQIN", ordinal + 7, "guqin", IPPSoundEvents.NOTE_BLOCK_GUQIN);
		KONGHOU = $VALUES[ordinal + 8] = (NoteBlockInstrument)(Object)new NoteBlockInstrumentMixin("KONGHOU", ordinal + 8, "konghou", IPPSoundEvents.NOTE_BLOCK_KONGHOU);
		SUONA = $VALUES[ordinal + 9] = (NoteBlockInstrument)(Object)new NoteBlockInstrumentMixin("SUONA", ordinal + 9, "suona", IPPSoundEvents.NOTE_BLOCK_SUONA);
		TIMPANI = $VALUES[ordinal + 10] = (NoteBlockInstrument)(Object)new NoteBlockInstrumentMixin("TIMPANI", ordinal + 10, "timpani", IPPSoundEvents.NOTE_BLOCK_TIMPANI);
		TRUMPET = $VALUES[ordinal + 11] = (NoteBlockInstrument)(Object)new NoteBlockInstrumentMixin("TRUMPET", ordinal + 11, "trumpet", IPPSoundEvents.NOTE_BLOCK_TRUMPET);
		TUBA = $VALUES[ordinal + 12] = (NoteBlockInstrument)(Object)new NoteBlockInstrumentMixin("TUBA", ordinal + 12, "tuba", IPPSoundEvents.NOTE_BLOCK_TUBA);
		VIOLA = $VALUES[ordinal + 13] = (NoteBlockInstrument)(Object)new NoteBlockInstrumentMixin("VIOLA", ordinal + 13, "viola", IPPSoundEvents.NOTE_BLOCK_VIOLA);
		VIOLIN = $VALUES[ordinal + 14] = (NoteBlockInstrument)(Object)new NoteBlockInstrumentMixin("VIOLIN", ordinal + 14, "violin", IPPSoundEvents.NOTE_BLOCK_VIOLIN);
		YANGQIN = $VALUES[ordinal + 15] = (NoteBlockInstrument)(Object)new NoteBlockInstrumentMixin("YANGQIN", ordinal + 15, "yangqin", IPPSoundEvents.NOTE_BLOCK_YANGQIN);
	}

	@Inject(method = "byState", at = @At(value = "HEAD"), cancellable = true)
	private static void addIPPInstruments(BlockState blockState, CallbackInfoReturnable<NoteBlockInstrument> cir) {
		if(blockState.is(Blocks.BASALT) ||
				blockState.is(Blocks.POLISHED_BASALT) ||
				blockState.is(Blocks.SMOOTH_BASALT)) {
			cir.setReturnValue(BASSOON);
			cir.cancel();
		} else if(blockState.is(Blocks.BLACKSTONE) ||
				blockState.is(Blocks.CHISELED_POLISHED_BLACKSTONE) ||
				blockState.is(Blocks.GILDED_BLACKSTONE) ||
				blockState.is(Blocks.POLISHED_BLACKSTONE) ||
				blockState.is(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS) ||
				blockState.is(Blocks.POLISHED_BLACKSTONE_BRICKS)) {
			cir.setReturnValue(CLARINET);
			cir.cancel();
		} else if(blockState.is(Blocks.CHAIN) ||
				blockState.is(Blocks.IRON_BARS)) {
			cir.setReturnValue(CYMBAL);
			cir.cancel();
		} else if(blockState.is(Blocks.SNOW) ||
				blockState.is(Blocks.SNOW_BLOCK) ||
				blockState.is(Blocks.POWDER_SNOW)) {
			cir.setReturnValue(ELECTRIC_CLEAN);
			cir.cancel();
		} else if(blockState.is(Blocks.NETHERRACK)) {
			cir.setReturnValue(ELECTRIC_OVERDRIVEN);
			cir.cancel();
		} else if(blockState.is(BlockTags.CORAL_BLOCKS) || blockState.is(IPPBlockTags.DEAD_CORAL_BLOCKS)) {
			cir.setReturnValue(ERHU);
			cir.cancel();
		} else if(blockState.is(IPPBlockTags.COPPER_BLOCKS)) {
			cir.setReturnValue(FRENCH_HORN);
			cir.cancel();
		} else if(blockState.is(Blocks.MOSS_BLOCK)) {
			cir.setReturnValue(GUQIN);
			cir.cancel();
		} else if(blockState.is(Blocks.END_STONE) || blockState.is(Blocks.END_STONE_BRICKS)) {
			cir.setReturnValue(KONGHOU);
			cir.cancel();
		} else if(blockState.is(Blocks.DIRT)) {
			cir.setReturnValue(SUONA);
			cir.cancel();
		} else if(blockState.is(Blocks.NETHER_BRICKS) ||
				blockState.is(Blocks.CHISELED_NETHER_BRICKS) ||
				blockState.is(Blocks.CRACKED_NETHER_BRICKS) ||
				blockState.is(Blocks.RED_NETHER_BRICKS)) {
			cir.setReturnValue(TIMPANI);
			cir.cancel();
		} else if(blockState.is(IPPBlockTags.CUT_COPPER_BLOCKS)) {
			cir.setReturnValue(TRUMPET);
			cir.cancel();
		} else if(blockState.is(Blocks.GRAVEL)) {
			cir.setReturnValue(TUBA);
			cir.cancel();
		} else if(blockState.is(IPPBlockTags.GLAZED_TERRACOTTA)) {
			cir.setReturnValue(VIOLA);
			cir.cancel();
		} else if(blockState.is(BlockTags.TERRACOTTA)) {
			cir.setReturnValue(VIOLIN);
			cir.cancel();
		} else if(blockState.is(Blocks.AMETHYST_BLOCK) || blockState.is(Blocks.BUDDING_AMETHYST)) {
			cir.setReturnValue(YANGQIN);
			cir.cancel();
		}
	}
}
