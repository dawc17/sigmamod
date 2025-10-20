package no.czapla.sigmamod.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import no.czapla.sigmamod.SigmaMod;
import no.czapla.sigmamod.item.custom.LuckyCoinItem;

public record LuckyCoinResultPacket(ResourceLocation itemId) implements CustomPacketPayload {

    public static final Type<LuckyCoinResultPacket> TYPE =
        new Type<>(ResourceLocation.fromNamespaceAndPath(SigmaMod.MOD_ID, "lucky_coin_result"));

    public static final StreamCodec<FriendlyByteBuf, LuckyCoinResultPacket> STREAM_CODEC =
        StreamCodec.composite(
            ResourceLocation.STREAM_CODEC,
            LuckyCoinResultPacket::itemId,
            LuckyCoinResultPacket::new
        );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(IPayloadContext context) {
        context.enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player != null) {
                LuckyCoinItem.startClientRouletteAnimation(itemId);
            }
        });
    }
}

