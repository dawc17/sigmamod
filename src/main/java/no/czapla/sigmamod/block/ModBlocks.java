package no.czapla.sigmamod.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import no.czapla.sigmamod.SigmaMod;
import no.czapla.sigmamod.block.custom.LuckyBlock;
import no.czapla.sigmamod.block.custom.MagicBlock;
import no.czapla.sigmamod.item.ModItems;

import java.awt.*;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(SigmaMod.MOD_ID);

    public static final DeferredBlock<Block> LUCKY_BLOCK = registerBlock("lucky_block",
            () -> new LuckyBlock(BlockBehaviour.Properties.of().strength(2f).sound(SoundType.STONE)));

    public static final DeferredBlock<Block> BLUE_STEEL_ORE = registerBlock("blue_steel_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.of()
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> BLUE_STEEL_BLOCK = registerBlock("blue_steel_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));

    public static final DeferredBlock<Block> MAGIC_BLOCK = registerBlock("magic_block",
            () -> new MagicBlock(BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> BLUE_STEEL_STAINED_GLASS = registerBlock("blue_steel_stained_glass",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.GLASS).noLootTable()));

    public static final DeferredBlock<StairBlock> BLUE_STEEL_STAIRS = registerBlock("blue_steel_stairs",
            () -> new StairBlock(ModBlocks.BLUE_STEEL_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));

    public static final DeferredBlock<SlabBlock> BLUE_STEEL_SLAB = registerBlock("blue_steel_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));

    public static final DeferredBlock<PressurePlateBlock> BLUE_STEEL_PRESSURE_PLATE = registerBlock("blue_steel_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<ButtonBlock> BLUE_STEEL_BUTTON = registerBlock("blue_steel_button",
            () -> new ButtonBlock(BlockSetType.IRON, 20, BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().noCollission()));

    public static final DeferredBlock<FenceBlock> BLUE_STEEL_FENCE = registerBlock("blue_steel_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));

    public static final DeferredBlock<FenceGateBlock> BLUE_STEEL_FENCE_GATE = registerBlock("blue_steel_fence_gate",
            () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<WallBlock> BLUE_STEEL_WALL = registerBlock("blue_steel_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));

    public static final DeferredBlock<DoorBlock> BLUE_STEEL_DOOR = registerBlock("blue_steel_door",
            () -> new DoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().noOcclusion()));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
