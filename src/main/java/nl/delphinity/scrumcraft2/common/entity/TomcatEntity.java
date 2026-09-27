package nl.delphinity.scrumcraft2.common.entity;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.feline.Cat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import nl.delphinity.scrumcraft2.init.ModItems;

import java.util.List;

// Apache Tomcat: throws HTTP errors when you hit it and deploys a WAR file into an actual war
public class TomcatEntity extends Cat {
    private static final float NOT_FOUND_CHANCE = 0.25F;
    private static final int NOT_FOUND_TICKS = 100;
    private static final List<EntityType<? extends Mob>> WAR = List.of(
            EntityTypes.PILLAGER,
            EntityTypes.PILLAGER,
            EntityTypes.VINDICATOR
    );

    public TomcatEntity(EntityType<? extends Cat> type, Level level) {
        super(type, level);
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        boolean hurt = super.hurtServer(level, source, damage);
        if (hurt && isAlive() && source.getEntity() instanceof Player player) {
            // 404: can't find the cat anymore
            boolean notFound = random.nextFloat() < NOT_FOUND_CHANCE;
            if (notFound) {
                addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, NOT_FOUND_TICKS));
            }
            String message = notFound ? "message.scrumcraft2.http_404" : "message.scrumcraft2.http_500";
            player.sendOverlayMessage(Component.translatable(message).withStyle(ChatFormatting.RED));
        }
        return hurt;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (!itemStack.is(ModItems.WAR_FILE)) {
            return super.mobInteract(player, hand);
        }

        if (level() instanceof ServerLevel level) {
            WAR.forEach(type -> type.spawn(level, blockPosition(), EntitySpawnReason.EVENT));
            player.sendOverlayMessage(Component.translatable("message.scrumcraft2.war_deployed").withStyle(ChatFormatting.GOLD));
            itemStack.consume(1, player);
        }
        return InteractionResult.SUCCESS;
    }
}
