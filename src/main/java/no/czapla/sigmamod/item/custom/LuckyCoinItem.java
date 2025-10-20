package no.czapla.sigmamod.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;
import no.czapla.sigmamod.network.LuckyCoinResultPacket;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class LuckyCoinItem extends Item {
    public LuckyCoinItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            // Server side - pick random item and send to client for animation
            List<ResourceLocation> items = new ArrayList<>(BuiltInRegistries.ITEM.keySet());
            Random random = new Random();

            ResourceLocation chosen = items.get(random.nextInt(items.size()));
            Item chosenItem = BuiltInRegistries.ITEM.get(chosen);

            // Send packet to client to start animation with the chosen item
            if (player instanceof ServerPlayer serverPlayer) {
                PacketDistributor.sendToPlayer(serverPlayer, new LuckyCoinResultPacket(chosen));
            }

            // Calculate animation duration (same as client)
            int animationTicks = 40;
            int totalDelay = IntStream.range(0, animationTicks).map(i -> 50 + i * 3).sum();

            // Schedule item giving after animation
            level.getServer().execute(() -> {
                try {
                    Thread.sleep(totalDelay);

                    // Give the item to the player
                    ItemStack reward = new ItemStack(chosenItem);
                    if (!player.addItem(reward)) {
                        player.drop(reward, false);
                    }

                    // Send message to player
                    player.displayClientMessage(Component.literal("You got: " + chosenItem.getDescription().getString() + "!"), false);

                    // Consume the lucky coin
                    itemStack.shrink(1);
                } catch (InterruptedException ignored) {}
            });
        }

        return InteractionResultHolder.success(itemStack);
    }

    @OnlyIn(Dist.CLIENT)
    public static void startClientRouletteAnimation(ResourceLocation finalItemId) {
        List<ResourceLocation> items = new ArrayList<>(BuiltInRegistries.ITEM.keySet());
        Random random = new Random();

        new Thread(() -> {
            try {
                int totalTicks = 40;
                for (int i = 0; i < totalTicks; i++) {
                    ResourceLocation displayItem;

                    // On the last tick, show the final item
                    if (i == totalTicks - 1) {
                        displayItem = finalItemId;
                    } else {
                        displayItem = items.get(random.nextInt(items.size()));
                    }

                    String name = BuiltInRegistries.ITEM.get(displayItem).getDescription().getString();

                    showCenteredText(Component.literal("🎲 " + name + " 🎲"));

                    Thread.sleep(50 + i * 3);
                }

            } catch (InterruptedException ignored) {}
        }).start();
    }

    @OnlyIn(Dist.CLIENT)
    private static void showCenteredText(Component text) {
        Minecraft mc = Minecraft.getInstance();
        mc.execute(() -> {
            if (mc.gui != null) {
                mc.gui.setOverlayMessage(text, false);
            }
        });
    }
}
