package no.czapla.sigmamod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import no.czapla.sigmamod.SigmaMod;
import no.czapla.sigmamod.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, SigmaMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.BLUE_STEEL_BLOCK.get())
                .add(ModBlocks.BLUE_STEEL_ORE.get())
                .add(ModBlocks.MAGIC_BLOCK.get())
                .add(ModBlocks.BLUE_STEEL_STAINED_GLASS.get())
                .add(ModBlocks.LUCKY_BLOCK.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.BLUE_STEEL_BLOCK.get())
                .add(ModBlocks.BLUE_STEEL_ORE.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.MAGIC_BLOCK.get());

        tag(BlockTags.FENCES).add(ModBlocks.BLUE_STEEL_FENCE.get());
        tag(BlockTags.FENCE_GATES).add(ModBlocks.BLUE_STEEL_FENCE_GATE.get());
        tag(BlockTags.WALLS).add(ModBlocks.BLUE_STEEL_WALL.get());
    }
}
