package no.czapla.sigmamod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import no.czapla.sigmamod.SigmaMod;
import no.czapla.sigmamod.block.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SigmaMod.MOD_ID);

    public static final Supplier<CreativeModeTab> BLUE_STEEL_ITEMS_TAB = CREATIVE_MODE_TAB.register("blue_steel_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BLUE_STEEL.get()))
                    .title(Component.translatable("creativetab.czaplasigmamod.blue_steel_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.BLUE_STEEL);
                        output.accept(ModItems.RAW_BLUE_STEEL);
                        output.accept(ModItems.CHISEL);
                    }).build());

    public static final Supplier<CreativeModeTab> BLUE_STEEL_BLOCKS_TAB = CREATIVE_MODE_TAB.register("blue_steel_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.BLUE_STEEL_BLOCK.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(SigmaMod.MOD_ID, "blue_steel_items_tab"))
                    .title(Component.translatable("creativetab.czaplasigmamod.blue_steel_blocks"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.BLUE_STEEL_BLOCK);
                        output.accept(ModBlocks.BLUE_STEEL_ORE);
                        output.accept(ModBlocks.MAGIC_BLOCK);
                    }).build());

    public static final Supplier<CreativeModeTab> SIGMA_FOOD_TAB = CREATIVE_MODE_TAB.register("sigma_food_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DURIAN.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(SigmaMod.MOD_ID, "blue_steel_blocks_tab"))
                    .title(Component.translatable("creativetab.czaplasigmamod.sigma_food"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.DURIAN);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
