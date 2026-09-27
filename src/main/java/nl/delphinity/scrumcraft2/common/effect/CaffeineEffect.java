package nl.delphinity.scrumcraft2.common.effect;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import nl.delphinity.scrumcraft2.init.ModEffects;

// When the Java wears off the garbage collector kicks in and freezes you (stop-the-world pause)
public class CaffeineEffect extends DelayedEffect {
    private static final int GC_PAUSE_TICKS = 60;

    public CaffeineEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x6F4E37);
    }

    @Override
    protected void onExpire(ServerLevel level, LivingEntity entity, int amplifier) {
        entity.addEffect(new MobEffectInstance(ModEffects.STOP_THE_WORLD, GC_PAUSE_TICKS));
        if (entity instanceof Player player) {
            player.sendOverlayMessage(Component.translatable("message.scrumcraft2.garbage_collector").withStyle(ChatFormatting.GRAY));
        }
    }
}
