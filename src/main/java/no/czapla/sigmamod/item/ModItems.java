package no.czapla.sigmamod.item;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import no.czapla.sigmamod.SigmaMod;
import no.czapla.sigmamod.item.custom.ChiselItem;
import no.czapla.sigmamod.item.custom.MonsterCanItem;
import no.czapla.sigmamod.item.custom.FuelItem;
import no.czapla.sigmamod.item.custom.LuckyCoinItem;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SigmaMod.MOD_ID);

    public static final DeferredItem<Item> BLUE_STEEL = ITEMS.register("blue_steel",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RAW_BLUE_STEEL = ITEMS.register("raw_blue_steel",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(100)));

    public static final DeferredItem<Item> LUCKY_COIN = ITEMS.register("lucky_coin",
            () -> new LuckyCoinItem(new Item.Properties().stacksTo(64)){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    if (Screen.hasShiftDown()) {
                        tooltipComponents.add(Component.translatable("tooltip.czaplasigmamod.lucky_coin.shift_down"));
                    } else {
                        tooltipComponents.add(Component.translatable("tooltip.czaplasigmamod.lucky_coin"));
                    }
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<Item> OIL_CAN = ITEMS.register("oil_can",
            () -> new FuelItem(new Item.Properties(), 2500){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    if (Screen.hasShiftDown()) {
                        tooltipComponents.add(Component.translatable("tooltip.czaplasigmamod.oil_can.shift_down"));
                    } else {
                        tooltipComponents.add(Component.translatable("tooltip.czaplasigmamod.oil_can"));
                    }
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<ArmorItem> BLUE_STEEL_HELMET = ITEMS.register("blue_steel_helmet",
            () -> new ArmorItem(ModArmorMaterials.BLUE_STEEL_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(19))));

    public static final DeferredItem<ArmorItem> BLUE_STEEL_CHESTPLATE = ITEMS.register("blue_steel_chestplate",
            () -> new ArmorItem(ModArmorMaterials.BLUE_STEEL_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(19))));

    public static final DeferredItem<ArmorItem> BLUE_STEEL_LEGGINGS = ITEMS.register("blue_steel_leggings",
            () -> new ArmorItem(ModArmorMaterials.BLUE_STEEL_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(19))));

    public static final DeferredItem<ArmorItem> BLUE_STEEL_BOOTS = ITEMS.register("blue_steel_boots",
            () -> new ArmorItem(ModArmorMaterials.BLUE_STEEL_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(19))));

    public static final DeferredItem<Item> MONSTER_CAN = ITEMS.register("monster_can",
            () -> new MonsterCanItem(new Item.Properties().stacksTo(16).food(ModFoodProperties.MONSTER_CAN)){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    if (Screen.hasShiftDown()) {
                        tooltipComponents.add(Component.translatable("tooltip.czaplasigmamod.monster_can.shift_down"));
                    } else {
                        tooltipComponents.add(Component.translatable("tooltip.czaplasigmamod.monster_can"));
                    }
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<Item> WHITE_MONSTER_CAN = ITEMS.register("white_monster_can",
            () -> new MonsterCanItem(new Item.Properties().stacksTo(16).food(ModFoodProperties.WHITE_MONSTER_CAN)){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    if (Screen.hasShiftDown()) {
                        tooltipComponents.add(Component.translatable("tooltip.czaplasigmamod.white_monster_can.shift_down"));
                    } else {
                        tooltipComponents.add(Component.translatable("tooltip.czaplasigmamod.white_monster_can"));
                    }
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<Item> DURIAN = ITEMS.register("durian",
            () -> new Item(new Item.Properties().food(ModFoodProperties.DURIAN)){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    if (Screen.hasShiftDown()) {
                        tooltipComponents.add(Component.translatable("tooltip.czaplasigmamod.durian.shift_down"));
                    } else {
                        tooltipComponents.add(Component.translatable("tooltip.czaplasigmamod.durian"));
                    }
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
