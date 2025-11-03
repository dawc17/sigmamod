package no.czapla.sigmamod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties DURIAN = new FoodProperties.Builder().nutrition(1).saturationModifier(0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 100), 0.35f).build();

    public static final FoodProperties MONSTER_CAN = new FoodProperties.Builder()
            .effect(()-> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 2), 1.0f).alwaysEdible().build();

    public static final FoodProperties WHITE_MONSTER_CAN = new FoodProperties.Builder()
            .effect(()-> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300, 2), 1.0f).alwaysEdible().build();

    public static final FoodProperties MANGO_MONSTER_CAN = new FoodProperties.Builder()
            .effect(()-> new MobEffectInstance(MobEffects.JUMP, 300, 2), 1.0f).alwaysEdible().build();
}
