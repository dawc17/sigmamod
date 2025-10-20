package no.czapla.sigmamod.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

import java.util.List;
import java.util.Random;

public class LuckyCoinItem extends Item {
    private static final String MOD_ID = "czaplasigmamod";

    public LuckyCoinItem(Properties properties) {
        super(properties);
    }

    private static List<ResourceLocation> getModItems() {
        return BuiltInRegistries.ITEM.keySet().stream()
                .filter(rl -> rl.getNamespace().equals(MOD_ID))
                .toList();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            // Server side - pick random item and give it instantly
            List<ResourceLocation> items = getModItems();

            // Safety check - if no custom items exist, fail
            if (items.isEmpty()) {
                return InteractionResultHolder.fail(itemStack);
            }

            Random random = new Random();

            ResourceLocation chosen = items.get(random.nextInt(items.size()));
            Item chosenItem = BuiltInRegistries.ITEM.get(chosen);

            // Give the item to the player
            ItemStack reward = new ItemStack(chosenItem);
            if (!player.addItem(reward)) {
                player.drop(reward, false);
            }

            // Play sound effect
            level.playSound(null, player.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1.0f, 1.0f);

            // Send message to player
            player.displayClientMessage(Component.literal("You got: " + chosenItem.getDescription().getString() + "!"), false);

            // Consume the lucky coin
            itemStack.shrink(1);
        }

        return InteractionResultHolder.success(itemStack);
    }
}
