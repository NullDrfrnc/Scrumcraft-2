package nl.delphinity.scrumcraft2.common.effect;

import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

// You can't walk or jump while this effect is active
public class FreezeEffect extends MobEffect {

    public FreezeEffect(MobEffectCategory category, int color, Identifier modifierId) {
        super(category, color);

        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, modifierId, -1.0F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(Attributes.JUMP_STRENGTH, modifierId, -1.0F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }
}
