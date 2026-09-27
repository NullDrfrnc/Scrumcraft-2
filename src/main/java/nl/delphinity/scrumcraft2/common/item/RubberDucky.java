package nl.delphinity.scrumcraft2.common.item;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import nl.delphinity.scrumcraft2.common.entity.RubberDuckyEntity;
import nl.delphinity.scrumcraft2.init.ModSounds;
import org.jetbrains.annotations.NotNull;

public class RubberDucky extends ThrowableItem {
    public RubberDucky(Properties properties) {
        super(properties, ModSounds.RUBBER_DUCKY_THROW);
    }

    @Override
    protected void throwItem(ServerLevel level, Player player, ItemStack stack) {
        Projectile.spawnProjectileFromRotation(RubberDuckyEntity::new, level, stack, player, 0.0F, 1F, 1F);
    }

    @Override
    public @NotNull Projectile asProjectile(Level level, Position position, ItemStack itemStack, Direction direction) {
        return new RubberDuckyEntity(level, position.x(), position.y(), position.z(), itemStack);
    }
}
