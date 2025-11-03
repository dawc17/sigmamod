package no.czapla.sigmamod.item.custom;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredItem;
import no.czapla.sigmamod.block.ModBlocks;
import no.czapla.sigmamod.item.ModItems;

import java.util.List;
import java.util.Random;

public class LuckyCoinItem extends Item {
    private static final Random RANDOM = new Random();
    private static final List<DeferredItem<Item>> MONSTER_CANS = List.of(
            ModItems.MONSTER_CAN,
            ModItems.WHITE_MONSTER_CAN
            , ModItems.MANGO_MONSTER_CAN
    );

    public LuckyCoinItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        // Check if the clicked block is a vending machine
        if (clickedBlock == ModBlocks.VENDING_MACHINE.get()) {
            if (!level.isClientSide()) {
                Player player = context.getPlayer();
                ItemStack itemStack = context.getItemInHand();

                // Randomly select a monster can
                DeferredItem<Item> randomMonsterCan = MONSTER_CANS.get(RANDOM.nextInt(MONSTER_CANS.size()));
                ItemStack monsterCan = new ItemStack(randomMonsterCan.get());

                if (!player.addItem(monsterCan)) {
                    player.drop(monsterCan, false);
                }

                // Play sound effect
                level.playSound(null, context.getClickedPos(), SoundEvents.IRON_TRAPDOOR_OPEN, SoundSource.BLOCKS, 1.0f, 1.0f);

                // Consume the lucky coin
                itemStack.consume(1, player);
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}
