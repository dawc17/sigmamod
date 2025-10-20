package no.czapla.sigmamod.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import no.czapla.sigmamod.SigmaMod;

@EventBusSubscriber(modid = SigmaMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModMessages {
    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");

        registrar.playToClient(
            LuckyCoinResultPacket.TYPE,
            LuckyCoinResultPacket.STREAM_CODEC,
            LuckyCoinResultPacket::handle
        );
    }
}

