package no.czapla.sigmamod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LuckyBlock extends Block {
    private static final Random RANDOM = new Random();
    private static final List<Item> ALL_VANILLA_ITEMS = new ArrayList<>();

    static {
        BuiltInRegistries.ITEM.forEach(ALL_VANILLA_ITEMS::add);
    }

    public LuckyBlock(Properties properties) { super(properties); }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if (!level.isClientSide() && level instanceof ServerLevel) {
            spawnRandomLoot(level, pos);
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    private void spawnRandomLoot(Level level, BlockPos pos) {
        ItemStack loot = getRandomLoot();
        ItemEntity itemEntity = new ItemEntity(level,
                pos.getX() + 0.5,
                pos.getY() + 0.5,
                pos.getZ() + 0.5,
                loot);
        level.addFreshEntity(itemEntity);
    }

    private ItemStack getRandomLoot() {
        Item randomItem = ALL_VANILLA_ITEMS.get(RANDOM.nextInt(ALL_VANILLA_ITEMS.size()));

        if (randomItem.getMaxDamage() > 0) {
            return new ItemStack(randomItem, 1);
        }

        int count = RANDOM.nextInt(5) + 1;
        return new ItemStack(randomItem, count);
    }
}
