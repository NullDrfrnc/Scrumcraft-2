package nl.delphinity.scrumcraft2.common.effect;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import nl.delphinity.scrumcraft2.init.ModItems;

// The sandwich only gets loaded when this runs out, and only when you still have a Hibernate session open
public class LazyLoadingEffect extends DelayedEffect {

    public LazyLoadingEffect() {
        super(MobEffectCategory.NEUTRAL, 0xD9A066);
    }

    @Override
    protected void onExpire(ServerLevel level, LivingEntity entity, int amplifier) {
        if (entity instanceof Player player && hasSession(player)) {
            entity.addEffect(new MobEffectInstance(MobEffects.SATURATION, 1, 9));
            entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1));
            return;
        }

        entity.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 200));
        entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 200, 2));
        if (entity instanceof Player player) {
            player.sendSystemMessage(Component.translatable("message.scrumcraft2.lazy_initialization_exception").withStyle(ChatFormatting.RED));
        }
    }

    private static boolean hasSession(Player player) {
        return player.getInventory().contains(stack -> stack.is(ModItems.HIBERNATE_SESSION));
    }
}
