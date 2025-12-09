package nl.delphinity.scrumcraft2.common.item;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;
import nl.delphinity.scrumcraft2.common.entity.ScrumBallEntity;
import org.jetbrains.annotations.NotNull;

public class ScrumBall extends Item implements ProjectileItem {
    public ScrumBall(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack stack = player.getItemInHand(interactionHand);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F, 1F);

        if (!level.isClientSide()) {
            this.throwBall((ServerLevel) level, player, stack, 1F, 1F);
            System.out.println("Throwing ball");
        }

        player.awardStat(Stats.ITEM_USED.get(this));

        // Yarn had .decrementUnlessCreative(), we have an if statement
        if (!player.isCreative()) {
            stack.shrink(1);
        }
        return InteractionResult.CONSUME;
    }

    public void throwBall(ServerLevel level, Player player, ItemStack stack, float throwingPower, float divergence) {
        Projectile.spawnProjectileFromRotation(
                ScrumBallEntity::new,
                level,
                stack,
                player,
                0.0F,
                throwingPower,
                divergence
        );
    }

    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        ScrumBallEntity entity = new ScrumBallEntity(level, pos.x(), pos.y(), pos.z(), stack);
        entity.setItem(stack);
        entity.shoot(direction.getStepX(), direction.getStepY(), direction.getStepZ(), 1.5F, 1.0F);
        return entity;
    }

}