package nl.delphinity.scrumcraft2.common.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

// Does nothing while it's active, only does something when it runs out
public abstract class DelayedEffect extends MobEffect {

    protected DelayedEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    protected abstract void onExpire(ServerLevel level, LivingEntity entity, int amplifier);

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplifier) {
        // tickCount is the remaining duration, so 1 means this is the last tick
        return tickCount == 1;
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
        onExpire(level, entity, amplifier);
        return true;
    }
}
