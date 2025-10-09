package no.czapla.sigmamod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import no.czapla.sigmamod.SigmaMod;
import no.czapla.sigmamod.block.ModBlocks;
import no.czapla.sigmamod.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SigmaMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.BLUE_STEEL.get());
        basicItem(ModItems.RAW_BLUE_STEEL.get());
        basicItem(ModItems.CHISEL.get());
        basicItem(ModItems.DURIAN.get());
        basicItem(ModItems.OIL_CAN.get());

        buttonItem(ModBlocks.BLUE_STEEL_BUTTON, ModBlocks.BLUE_STEEL_BLOCK);
        fenceItem(ModBlocks.BLUE_STEEL_FENCE, ModBlocks.BLUE_STEEL_BLOCK);
        wallItem(ModBlocks.BLUE_STEEL_WALL, ModBlocks.BLUE_STEEL_BLOCK);

        basicItem(ModBlocks.BLUE_STEEL_DOOR.asItem());


    }

    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(SigmaMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(SigmaMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(SigmaMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }
}
