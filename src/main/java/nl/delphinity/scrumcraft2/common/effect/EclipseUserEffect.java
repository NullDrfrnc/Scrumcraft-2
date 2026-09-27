package nl.delphinity.scrumcraft2.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import static nl.delphinity.scrumcraft2.Scrumcraft2.identifierOf;

public class EclipseUserEffect extends MobEffect {
    public EclipseUserEffect() {
        super(MobEffectCategory.HARMFUL, 0x000000);

        // -0.2 scale (1.0)
        this.addAttributeModifier(
                Attributes.SCALE,
                identifierOf("size_modifier"),
                -0.2F ,
                AttributeModifier.Operation.ADD_VALUE
        );

        // -35% movement speed
        this.addAttributeModifier(
                Attributes.MOVEMENT_SPEED, identifierOf("speed_modifier"), -0.35F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return false;
    }
}

