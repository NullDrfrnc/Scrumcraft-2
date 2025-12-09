package nl.delphinity.scrumcraft2.common.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import nl.delphinity.scrumcraft2.common.entity.RubberDuckyEntity;
import nl.delphinity.scrumcraft2.init.ModSounds;
import org.jetbrains.annotations.NotNull;

public class RubberDucky extends Item {
    public RubberDucky(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack stack = player.getItemInHand(interactionHand);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.RUBBER_DUCKY_THROW, SoundSource.PLAYERS, 0.5F, 1F);

        // If this is running on the server, throw a ducky (CLIENT WILL CRASH IF IT THROWS A DUCK, THANKS MINECRAFT)
        if (!level.isClientSide()) {
            this.throwDuck((ServerLevel) level, player, stack, 1F, 1F);
        }

        player.awardStat(Stats.ITEM_USED.get(this));

        // Yarn had .decrementUnlessCreative(), we have an if statement
        if (!player.isCreative()) {
            stack.shrink(1);
        }
        return InteractionResult.CONSUME;
    }

    public void throwDuck(ServerLevel level, Player player, ItemStack stack, float throwingPower, float divergence) {
        Projectile.spawnProjectileFromRotation(
                RubberDuckyEntity::new,
                level,
                stack,
                player,
                0.0F,
                throwingPower,
                divergence
        );
    }
}
