package com.hexagram2021.ipp.common.register;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.RegisterEvent;

import java.util.HashMap;
import java.util.Map;

import static com.hexagram2021.ipp.InstrumentPlusPlus.MODID;

public class IPPSoundEvents {
	static final Map<ResourceLocation, SoundEvent> registeredEvents = new HashMap<>();

	public static final SoundEvent NOTE_BLOCK_BASSOON = registerSound("block.note_block.bassoon");
	public static final SoundEvent NOTE_BLOCK_CLARINET = registerSound("block.note_block.clarinet");
	public static final SoundEvent NOTE_BLOCK_CYMBAL = registerSound("block.note_block.cymbal");
	public static final SoundEvent NOTE_BLOCK_ELECTRIC_CLEAN = registerSound("block.note_block.electric_clean");
	public static final SoundEvent NOTE_BLOCK_ELECTRIC_OVERDRIVEN = registerSound("block.note_block.electric_overdriven");
	public static final SoundEvent NOTE_BLOCK_ERHU = registerSound("block.note_block.erhu");
	public static final SoundEvent NOTE_BLOCK_FRENCH_HORN = registerSound("block.note_block.french_horn");
	public static final SoundEvent NOTE_BLOCK_GUQIN = registerSound("block.note_block.guqin");
	public static final SoundEvent NOTE_BLOCK_KONGHOU = registerSound("block.note_block.konghou");
	public static final SoundEvent NOTE_BLOCK_SUONA = registerSound("block.note_block.suona");
	public static final SoundEvent NOTE_BLOCK_TIMPANI = registerSound("block.note_block.timpani");
	public static final SoundEvent NOTE_BLOCK_TRUMPET = registerSound("block.note_block.trumpet");
	public static final SoundEvent NOTE_BLOCK_TUBA = registerSound("block.note_block.tuba");
	public static final SoundEvent NOTE_BLOCK_VIOLA = registerSound("block.note_block.viola");
	public static final SoundEvent NOTE_BLOCK_VIOLIN = registerSound("block.note_block.violin");
	public static final SoundEvent NOTE_BLOCK_YANGQIN = registerSound("block.note_block.yangqin");

	private static SoundEvent registerSound(String name) {
		ResourceLocation location = new ResourceLocation(MODID, name);
		SoundEvent event = new SoundEvent(location);
		registeredEvents.put(location, event);
		return event;
	}

	public static void init(RegisterEvent event) {
		event.register(Registry.SOUND_EVENT_REGISTRY, helper -> registeredEvents.forEach(helper::register));
	}
}
