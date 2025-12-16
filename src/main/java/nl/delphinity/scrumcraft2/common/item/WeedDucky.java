package nl.delphinity.scrumcraft2.common.item;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import nl.delphinity.scrumcraft2.common.entity.RubberDuckyEntity;
import nl.delphinity.scrumcraft2.common.entity.WeedDuckyEntity;
import org.jetbrains.annotations.NotNull;

public class WeedDucky extends RubberDucky {
    public WeedDucky(Properties properties) {
        super(properties);
    }

    @Override
    public void throwDuck(ServerLevel level, Player player, ItemStack stack, float throwingPower, float divergence) {
        Projectile.spawnProjectileFromRotation(
                WeedDuckyEntity::new,
                level,
                stack,
                player,
                0.0F,
                throwingPower,
                divergence
        );
    }

    @Override
    public @NotNull Projectile asProjectile(Level level, Position position, ItemStack itemStack, Direction direction) {
        return new WeedDuckyEntity(level, position.x(), position.y(), position.z(), itemStack);
    }
}
