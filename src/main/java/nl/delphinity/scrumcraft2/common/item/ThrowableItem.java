package nl.delphinity.scrumcraft2.common.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public abstract class ThrowableItem extends Item implements ProjectileItem {
    private final SoundEvent throwSound;

    public ThrowableItem(Properties properties, SoundEvent throwSound) {
        super(properties);
        this.throwSound = throwSound;
    }

    // Only gets called on the server (CLIENT WILL CRASH IF IT THROWS A DUCK, THANKS MINECRAFT)
    protected abstract void throwItem(ServerLevel level, Player player, ItemStack stack);

    @Override
    public @NotNull InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack stack = player.getItemInHand(interactionHand);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), throwSound, SoundSource.PLAYERS, 0.5F, 1F);

        if (level instanceof ServerLevel serverLevel) {
            throwItem(serverLevel, player, stack);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        stack.consume(1, player);
        return InteractionResult.CONSUME;
    }
}
