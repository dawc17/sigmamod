package no.czapla.sigmamod.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import no.czapla.sigmamod.SigmaMod;
import no.czapla.sigmamod.item.custom.ChiselItem;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SigmaMod.MOD_ID);

    public static final DeferredItem<Item> BLUE_STEEL = ITEMS.register("blue_steel",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RAW_BLUE_STEEL = ITEMS.register("raw_blue_steel",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(100)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
