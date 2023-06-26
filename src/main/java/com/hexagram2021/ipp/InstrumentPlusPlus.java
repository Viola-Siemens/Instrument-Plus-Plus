package com.hexagram2021.ipp;

import com.hexagram2021.ipp.common.IPPContent;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.ModLoadingStage;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.Optional;
import java.util.function.Consumer;

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

        modBus.addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(IPPContent::init);
    }
}
