package nl.delphinity.scrumcraft2.common.item;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import nl.delphinity.scrumcraft2.common.entity.ScrumBallEntity;

public class ScrumBall extends ThrowableItem {
    private final double knockback;

    public ScrumBall(Properties properties, double knockback) {
        super(properties, SoundEvents.EGG_THROW);
        this.knockback = knockback;
    }

    @Override
    protected void throwItem(ServerLevel level, Player player, ItemStack stack) {
        ScrumBallEntity entity = new ScrumBallEntity(level, player, stack, knockback);
        entity.setItem(stack);
        entity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1F, 1F);
        level.addFreshEntity(entity);
    }

    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        ScrumBallEntity entity = new ScrumBallEntity(level, pos.x(), pos.y(), pos.z(), stack);
        entity.setItem(stack);
        entity.shoot(direction.getStepX(), direction.getStepY(), direction.getStepZ(), 1.5F, 1.0F);
        return entity;
    }
}
