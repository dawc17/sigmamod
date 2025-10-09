package no.czapla.sigmamod.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import no.czapla.sigmamod.SigmaMod;
import no.czapla.sigmamod.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, SigmaMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.BLUE_STEEL_BLOCK);
        blockWithItem(ModBlocks.MAGIC_BLOCK);
        blockWithItem(ModBlocks.BLUE_STEEL_ORE);

        stairsBlock(ModBlocks.BLUE_STEEL_STAIRS.get(), blockTexture(ModBlocks.BLUE_STEEL_BLOCK.get()));
        slabBlock(ModBlocks.BLUE_STEEL_SLAB.get(), blockTexture(ModBlocks.BLUE_STEEL_BLOCK.get()), blockTexture(ModBlocks.BLUE_STEEL_BLOCK.get()));
        buttonBlock(ModBlocks.BLUE_STEEL_BUTTON.get(), blockTexture(ModBlocks.BLUE_STEEL_BLOCK.get()));
        pressurePlateBlock(ModBlocks.BLUE_STEEL_PRESSURE_PLATE.get(), blockTexture(ModBlocks.BLUE_STEEL_BLOCK.get()));
        fenceBlock(ModBlocks.BLUE_STEEL_FENCE.get(), blockTexture(ModBlocks.BLUE_STEEL_BLOCK.get()));
        fenceGateBlock(ModBlocks.BLUE_STEEL_FENCE_GATE.get(), blockTexture(ModBlocks.BLUE_STEEL_BLOCK.get()));
        wallBlock(ModBlocks.BLUE_STEEL_WALL.get(), blockTexture(ModBlocks.BLUE_STEEL_BLOCK.get()));
        doorBlockWithRenderType(ModBlocks.BLUE_STEEL_DOOR.get(), modLoc("block/blue_steel_door_bottom"), modLoc("block/blue_steel_door_top"), "cutout");
        blockItem(ModBlocks.BLUE_STEEL_STAIRS);
        blockItem(ModBlocks.BLUE_STEEL_SLAB);
        blockItem(ModBlocks.BLUE_STEEL_PRESSURE_PLATE);
        blockItem(ModBlocks.BLUE_STEEL_FENCE_GATE);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("czaplasigmamod:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("czaplasigmamod:block/" + deferredBlock.getId().getPath() + appendix));
    }
}
