package com.hexagram2021.ipp;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hexagram2021.ipp.common.IPPContent;
import com.hexagram2021.ipp.common.crafting.MusicalInstrumentShadowRecipe;
import com.hexagram2021.ipp.common.register.IPPRecipes;
import com.hexagram2021.ipp.mixin.RecipeManagerAccess;
import com.mojang.logging.LogUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.ModLoadingStage;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@Mod(InstrumentPlusPlus.MODID)
public class InstrumentPlusPlus {
    public static final String MODID = "ipp";

    public static final Logger LOGGER = LogUtils.getLogger();

    public InstrumentPlusPlus() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.addListener(this::serverStarted);
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

    @SuppressWarnings("deprecation")
    public void serverStarted(ServerStartedEvent event) {
        ServerLevel world = event.getServer().getLevel(Level.OVERWORLD);
        assert world != null;
        RecipeManagerAccess recipeManagerAccess = (RecipeManagerAccess)(world.getRecipeManager());
        Map<RecipeType<?>, Map<ResourceLocation, Recipe<?>>> recipes = Maps.newHashMap(recipeManagerAccess.ipp_getRecipes());
        recipes.compute(IPPRecipes.MUSICAL_INSTRUMENT_SHADOW_TYPE, (key, map) -> {
            Map<ResourceLocation, Recipe<?>> shadows = Maps.newHashMap();
            if(map != null) {
                shadows.putAll(map);
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
        recipeManagerAccess.ipp_setRecipes(recipes);
    }
}
