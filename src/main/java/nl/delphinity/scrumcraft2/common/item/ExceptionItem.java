package nl.delphinity.scrumcraft2.common.item;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import nl.delphinity.scrumcraft2.common.entity.ThrownExceptionEntity;
import org.jetbrains.annotations.NotNull;

// An exception you can literally throw, a shield catches it like a try/catch block
public abstract class ExceptionItem extends ThrowableItem {

    public ExceptionItem(Properties properties) {
        super(properties, SoundEvents.SNOWBALL_THROW);
    }

    // Only gets called on the server when the exception hits something that didn't catch it
    public abstract void onUncaught(ServerLevel level, ThrownExceptionEntity exception, HitResult hitResult);

    @Override
    protected void throwItem(ServerLevel level, Player player, ItemStack stack) {
        Projectile.spawnProjectileFromRotation(ThrownExceptionEntity::new, level, stack, player, 0.0F, 1.5F, 1F);
    }

    @Override
    public @NotNull Projectile asProjectile(Level level, Position position, ItemStack itemStack, Direction direction) {
        return new ThrownExceptionEntity(level, position.x(), position.y(), position.z(), itemStack);
    }
}
