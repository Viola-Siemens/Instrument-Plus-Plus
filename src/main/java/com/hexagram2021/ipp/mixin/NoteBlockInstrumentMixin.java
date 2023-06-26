package com.hexagram2021.ipp.mixin;

import com.hexagram2021.ipp.common.register.IPPBlockTags;
import com.hexagram2021.ipp.common.register.IPPSoundEvents;
import net.minecraft.core.Holder;
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
import java.util.Locale;

import static com.hexagram2021.ipp.common.register.IPPInstruments.*;

@Mixin(NoteBlockInstrument.class)
public class NoteBlockInstrumentMixin {
	@SuppressWarnings("unused")
	NoteBlockInstrumentMixin(String enumName, int ord, String name, Holder<SoundEvent> sound, NoteBlockInstrument.Type type) {
		throw new UnsupportedOperationException("Replaced by Mixin");
	}

	@Shadow @Mutable @Final
	private static NoteBlockInstrument[] $VALUES;

	private static NoteBlockInstrument create(String enumName, int ord, SoundEvent soundEvent) {
		return (NoteBlockInstrument)(Object)new NoteBlockInstrumentMixin(
				enumName, ord, enumName.toLowerCase(Locale.ROOT), Holder.direct(soundEvent), NoteBlockInstrument.Type.BASE_BLOCK
		);
	}

	@Inject(method = "<clinit>", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/block/state/properties/NoteBlockInstrument;$VALUES:[Lnet/minecraft/world/level/block/state/properties/NoteBlockInstrument;", shift = At.Shift.AFTER))
	private static void ipp_injectEnum(CallbackInfo ci) {
		int ordinal = $VALUES.length;
		$VALUES = Arrays.copyOf($VALUES, ordinal + 16);

		BASSOON = $VALUES[ordinal] =
				create("BASSOON", ordinal, IPPSoundEvents.NOTE_BLOCK_BASSOON);
		CLARINET = $VALUES[ordinal + 1] =
				create("CLARINET", ordinal + 1, IPPSoundEvents.NOTE_BLOCK_CLARINET);
		CYMBAL = $VALUES[ordinal + 2] =
				create("CYMBAL", ordinal + 2, IPPSoundEvents.NOTE_BLOCK_CYMBAL);
		ELECTRIC_CLEAN = $VALUES[ordinal + 3] =
				create("ELECTRIC_CLEAN", ordinal + 3, IPPSoundEvents.NOTE_BLOCK_ELECTRIC_CLEAN);
		ELECTRIC_OVERDRIVEN = $VALUES[ordinal + 4] =
				create("ELECTRIC_OVERDRIVEN", ordinal + 4, IPPSoundEvents.NOTE_BLOCK_ELECTRIC_OVERDRIVEN);
		ERHU = $VALUES[ordinal + 5] =
				create("ERHU", ordinal + 5, IPPSoundEvents.NOTE_BLOCK_ERHU);
		FRENCH_HORN = $VALUES[ordinal + 6] =
				create("FRENCH_HORN", ordinal + 6, IPPSoundEvents.NOTE_BLOCK_FRENCH_HORN);
		GUQIN = $VALUES[ordinal + 7] =
				create("GUQIN", ordinal + 7, IPPSoundEvents.NOTE_BLOCK_GUQIN);
		KONGHOU = $VALUES[ordinal + 8] =
				create("KONGHOU", ordinal + 8, IPPSoundEvents.NOTE_BLOCK_KONGHOU);
		SUONA = $VALUES[ordinal + 9] =
				create("SUONA", ordinal + 9, IPPSoundEvents.NOTE_BLOCK_SUONA);
		TIMPANI = $VALUES[ordinal + 10] =
				create("TIMPANI", ordinal + 10, IPPSoundEvents.NOTE_BLOCK_TIMPANI);
		TRUMPET = $VALUES[ordinal + 11] =
				create("TRUMPET", ordinal + 11, IPPSoundEvents.NOTE_BLOCK_TRUMPET);
		TUBA = $VALUES[ordinal + 12] =
				create("TUBA", ordinal + 12, IPPSoundEvents.NOTE_BLOCK_TUBA);
		VIOLA = $VALUES[ordinal + 13] =
				create("VIOLA", ordinal + 13, IPPSoundEvents.NOTE_BLOCK_VIOLA);
		VIOLIN = $VALUES[ordinal + 14] =
				create("VIOLIN", ordinal + 14, IPPSoundEvents.NOTE_BLOCK_VIOLIN);
		YANGQIN = $VALUES[ordinal + 15] =
				create("YANGQIN", ordinal + 15, IPPSoundEvents.NOTE_BLOCK_YANGQIN);
	}

	@Inject(method = "byStateBelow", at = @At(value = "HEAD"), cancellable = true)
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
		} else if(blockState.is(IPPBlockTags.NETHER_BRICKS)) {
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
