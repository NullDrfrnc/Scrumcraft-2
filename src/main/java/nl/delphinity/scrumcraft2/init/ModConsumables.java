package nl.delphinity.scrumcraft2.init;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.enchantment.effects.ExplodeEffect;

import java.util.List;

public class ModConsumables {
    public static void init() {

    }

    public static final Consumable AYRAN = defaultDrink()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.BAD_OMEN, 500, 5),
                                    new MobEffectInstance(MobEffects.NAUSEA, 500, 5),
                                    new MobEffectInstance(MobEffects.MINING_FATIGUE, 500, 5),
                                    new MobEffectInstance(MobEffects.HUNGER, 500, 5),
                                    new MobEffectInstance(MobEffects.WEAKNESS, 500, 5),
                                    new MobEffectInstance(MobEffects.WITHER, 500, 5),
                                    new MobEffectInstance(MobEffects.SLOWNESS, 500, 5),
                                    new MobEffectInstance(MobEffects.DARKNESS, 500, 5)
                            )
                    )
            )
            .build();

    public static final Consumable POTION_OF_TERRORISM = defaultDrink()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.INFESTED, 500, 9),
                                    new MobEffectInstance(MobEffects.WEAVING, 500, 9),
                                    new MobEffectInstance(MobEffects.WIND_CHARGED, 500, 9),
                                    new MobEffectInstance(MobEffects.OOZING, 500, 9),
                                    new MobEffectInstance(MobEffects.INSTANT_DAMAGE, 500, 9)
                            )
                    )
            )
            .build();

    public static final Consumable AGARTHA_POTION = defaultDrink()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.JUMP_BOOST, 500, 9),
                                    new MobEffectInstance(MobEffects.SPEED, 500, 9),
                                    new MobEffectInstance(MobEffects.ABSORPTION, 500, 9),
                                    new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 500, 9),
                                    new MobEffectInstance(MobEffects.INSTANT_HEALTH, 500, 9),
                                    new MobEffectInstance(MobEffects.REGENERATION, 500, 9),
                                    new MobEffectInstance(MobEffects.RESISTANCE, 500, 9),
                                    new MobEffectInstance(MobEffects.STRENGTH, 500, 9),
                                    new MobEffectInstance(MobEffects.HASTE, 500, 9),
                                    new MobEffectInstance(MobEffects.NIGHT_VISION, 500, 9),
                                    new MobEffectInstance(MobEffects.GLOWING, 500, 9),
                                    new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 500, 9),
                                    new MobEffectInstance(MobEffects.SLOWNESS, 500, 0)
                            )
                    )
            )
            .build();

    public static final Consumable GOLDEN_FISH = defaultFood()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.CONDUIT_POWER, 1000, 4),
                                    new MobEffectInstance(MobEffects.WATER_BREATHING, 1000, 4),
                                    new MobEffectInstance(MobEffects.BREATH_OF_THE_NAUTILUS, 1000, 4),
                                    new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 1000, 4)
                            )
                    )
            )
            .build();
    public static final Consumable BOWL_OF_CODE = defaultFood()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(ModEffects.ECLIPSE_USER, 500, 0)
                            )
                    )
            )
            .build();


    public static final Consumable WORSTE_BOLUS = defaultFood()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.LEVITATION, 100, 18),
                                    new MobEffectInstance(MobEffects.SPEED, 100, 9),
                                    new MobEffectInstance(MobEffects.GLOWING, 200, 9)
                            )
                    )
            )
            .build();

    public static Consumable.Builder defaultFood() {
        return Consumable.builder().consumeSeconds(1.6F).animation(ItemUseAnimation.EAT).sound(SoundEvents.GENERIC_EAT).hasConsumeParticles(true);
    }

    public static Consumable.Builder defaultDrink() {
        return Consumable.builder().consumeSeconds(1.6F).animation(ItemUseAnimation.DRINK).sound(SoundEvents.GENERIC_DRINK).hasConsumeParticles(false);
    }
}
