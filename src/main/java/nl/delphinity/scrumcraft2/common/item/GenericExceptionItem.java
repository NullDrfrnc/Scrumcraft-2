package nl.delphinity.scrumcraft2.common.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import nl.delphinity.scrumcraft2.common.entity.ThrownExceptionEntity;

// throw new Exception(); blows up when nobody catches it
public class GenericExceptionItem extends ExceptionItem {
    private static final float EXPLOSION_POWER = 2.0F;

    public GenericExceptionItem(Properties properties) {
        super(properties);
    }

    @Override
    public void onUncaught(ServerLevel level, ThrownExceptionEntity exception, HitResult hitResult) {
        level.explode(exception, exception.getX(), exception.getY(), exception.getZ(), EXPLOSION_POWER, Level.ExplosionInteraction.MOB);
    }
}
