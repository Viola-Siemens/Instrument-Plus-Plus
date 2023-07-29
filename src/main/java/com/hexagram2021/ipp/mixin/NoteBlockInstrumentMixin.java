package com.hexagram2021.ipp.mixin;

import com.hexagram2021.ipp.common.register.IPPSoundEvents;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
		$VALUES = Arrays.copyOf($VALUES, ordinal + 20);

		BASSOON = $VALUES[ordinal] =
				create("BASSOON", ordinal, IPPSoundEvents.NOTE_BLOCK_BASSOON);
		CLARINET = $VALUES[ordinal + 1] =
				create("CLARINET", ordinal + 1, IPPSoundEvents.NOTE_BLOCK_CLARINET);
		CRASH = $VALUES[ordinal + 2] =
				create("CRASH", ordinal + 2, IPPSoundEvents.NOTE_BLOCK_CRASH);
		CYMBAL = $VALUES[ordinal + 3] =
				create("CYMBAL", ordinal + 3, IPPSoundEvents.NOTE_BLOCK_CYMBAL);
		ELECTRIC_CLEAN = $VALUES[ordinal + 4] =
				create("ELECTRIC_CLEAN", ordinal + 4, IPPSoundEvents.NOTE_BLOCK_ELECTRIC_CLEAN);
		ELECTRIC_OVERDRIVEN = $VALUES[ordinal + 5] =
				create("ELECTRIC_OVERDRIVEN", ordinal + 5, IPPSoundEvents.NOTE_BLOCK_ELECTRIC_OVERDRIVEN);
		ELECTRIC_PIANO = $VALUES[ordinal + 6] =
				create("ELECTRIC_PIANO", ordinal + 6, IPPSoundEvents.NOTE_BLOCK_ELECTRIC_PIANO);
		ERHU = $VALUES[ordinal + 7] =
				create("ERHU", ordinal + 7, IPPSoundEvents.NOTE_BLOCK_ERHU);
		FRENCH_HORN = $VALUES[ordinal + 8] =
				create("FRENCH_HORN", ordinal + 8, IPPSoundEvents.NOTE_BLOCK_FRENCH_HORN);
		GUQIN = $VALUES[ordinal + 9] =
				create("GUQIN", ordinal + 9, IPPSoundEvents.NOTE_BLOCK_GUQIN);
		KONGHOU = $VALUES[ordinal + 10] =
				create("KONGHOU", ordinal + 10, IPPSoundEvents.NOTE_BLOCK_KONGHOU);
		PAD = $VALUES[ordinal + 11] =
				create("PAD", ordinal + 11, IPPSoundEvents.NOTE_BLOCK_PAD);
		SUONA = $VALUES[ordinal + 12] =
				create("SUONA", ordinal + 12, IPPSoundEvents.NOTE_BLOCK_SUONA);
		TIMPANI = $VALUES[ordinal + 13] =
				create("TIMPANI", ordinal + 13, IPPSoundEvents.NOTE_BLOCK_TIMPANI);
		TROMBONE = $VALUES[ordinal + 14] =
				create("TROMBONE", ordinal + 14, IPPSoundEvents.NOTE_BLOCK_TROMBONE);
		TRUMPET = $VALUES[ordinal + 15] =
				create("TRUMPET", ordinal + 15, IPPSoundEvents.NOTE_BLOCK_TRUMPET);
		TUBA = $VALUES[ordinal + 16] =
				create("TUBA", ordinal + 16, IPPSoundEvents.NOTE_BLOCK_TUBA);
		VIOLA = $VALUES[ordinal + 17] =
				create("VIOLA", ordinal + 17, IPPSoundEvents.NOTE_BLOCK_VIOLA);
		VIOLIN = $VALUES[ordinal + 18] =
				create("VIOLIN", ordinal + 18, IPPSoundEvents.NOTE_BLOCK_VIOLIN);
		YANGQIN = $VALUES[ordinal + 19] =
				create("YANGQIN", ordinal + 19, IPPSoundEvents.NOTE_BLOCK_YANGQIN);
	}
}
