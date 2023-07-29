package com.hexagram2021.ipp;

import com.google.common.collect.Maps;
import com.hexagram2021.ipp.common.IPPContent;
import com.hexagram2021.ipp.common.register.IPPBlockTags;
import com.mojang.logging.LogUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.ModLoadingStage;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import java.util.IdentityHashMap;
import java.util.Optional;
import java.util.function.Consumer;

import static com.hexagram2021.ipp.common.register.IPPInstruments.*;

@Mod(InstrumentPlusPlus.MODID)
public class InstrumentPlusPlus {
    public static final String MODID = "ipp";

    public static final Logger LOGGER = LogUtils.getLogger();

    public InstrumentPlusPlus() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        DeferredWorkQueue queue = DeferredWorkQueue.lookup(Optional.of(ModLoadingStage.CONSTRUCT)).orElseThrow();
        Consumer<Runnable> runLater = job -> queue.enqueueWork(
                ModLoadingContext.get().getActiveContainer(), job
        );
        IPPContent.modConstruction(modBus, runLater);
    
        MinecraftForge.EVENT_BUS.addListener(this::onTagsUpdated);

        MinecraftForge.EVENT_BUS.register(this);
    }
    
    private final IdentityHashMap<Block, NoteBlockInstrument> backups = Maps.newIdentityHashMap();
    
    @SuppressWarnings("deprecation")
    public void onTagsUpdated(TagsUpdatedEvent event) {
        ForgeRegistries.BLOCKS.forEach(block -> {
            if(block.builtInRegistryHolder().is(IPPBlockTags.BASALTS)) {
                setInstrument(block, BASSOON);
            } else if(block.builtInRegistryHolder().is(IPPBlockTags.BLACKSTONES)) {
                setInstrument(block, CLARINET);
            } else if(block.equals(Blocks.IRON_TRAPDOOR)) {
                setInstrument(block, CRASH);
            } else if(block.equals(Blocks.CHAIN) || block.equals(Blocks.IRON_BARS)) {
                setInstrument(block, CYMBAL);
            } else if(block.builtInRegistryHolder().is(IPPBlockTags.SNOWS)) {
                setInstrument(block, ELECTRIC_CLEAN);
            } else if(block.equals(Blocks.NETHERRACK)) {
                setInstrument(block, ELECTRIC_OVERDRIVEN);
            } else if(block.builtInRegistryHolder().is(IPPBlockTags.QUARTZ_BLOCKS)) {
                setInstrument(block, ELECTRIC_PIANO);
            } else if(block.builtInRegistryHolder().is(BlockTags.CORAL_BLOCKS) ||
                    block.builtInRegistryHolder().is(IPPBlockTags.DEAD_CORAL_BLOCKS)) {
                setInstrument(block, ERHU);
            } else if(block.builtInRegistryHolder().is(IPPBlockTags.COPPER_BLOCKS)) {
                setInstrument(block, FRENCH_HORN);
            } else if(block.equals(Blocks.MOSS_BLOCK)) {
                setInstrument(block, GUQIN);
            } else if(block.builtInRegistryHolder().is(IPPBlockTags.END_STONES)) {
                setInstrument(block, KONGHOU);
            } else if(block.equals(Blocks.LAPIS_BLOCK)) {
                setInstrument(block, PAD);
            } else if(block.equals(Blocks.DIRT)) {
                setInstrument(block, SUONA);
            } else if(block.builtInRegistryHolder().is(IPPBlockTags.NETHER_BRICKS)) {
                setInstrument(block, TIMPANI);
            } else if(block.equals(Blocks.DRIED_KELP_BLOCK)) {
                setInstrument(block, TROMBONE);
            } else if(block.builtInRegistryHolder().is(IPPBlockTags.CUT_COPPER_BLOCKS)) {
                setInstrument(block, TRUMPET);
            } else if(block.equals(Blocks.GRAVEL)) {
                setInstrument(block, TUBA);
            } else if(block.builtInRegistryHolder().is(IPPBlockTags.GLAZED_TERRACOTTA)) {
                setInstrument(block, VIOLA);
            } else if(block.builtInRegistryHolder().is(BlockTags.TERRACOTTA)) {
                setInstrument(block, VIOLIN);
            } else if(block.builtInRegistryHolder().is(IPPBlockTags.AMETHYST_BLOCKS)) {
                setInstrument(block, YANGQIN);
            } else if(this.backups.containsKey(block)) {
                setInstrument(block, this.backups.get(block));
                this.backups.remove(block);
            }
        });
    }
    
    private void setInstrument(Block block, NoteBlockInstrument instrument) {
        if(!this.backups.containsKey(block)) {
            this.backups.put(block, block.defaultBlockState().instrument());
        }
        block.getStateDefinition().getPossibleStates().forEach(blockState -> blockState.instrument = instrument);
    }
}
