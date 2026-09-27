package nl.delphinity.scrumcraft2.common.util;

import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;
import nl.delphinity.scrumcraft2.init.ModDimensions;

public class AgarthaTeleporter {
    private static final Vec3 SPAWN_POS = new Vec3(0.5, 101, 0.5);

    // Sends the entity to Agartha, or back to the overworld when it's already in Agartha
    public static boolean teleport(ServerLevel level, Entity entity, float yRot, float xRot) {
        ResourceKey<Level> destinationKey = level.dimension() == ModDimensions.AGARTHA ? Level.OVERWORLD : ModDimensions.AGARTHA;
        ServerLevel destination = level.getServer().getLevel(destinationKey);
        if (destination == null) {
            return false;
        }

        entity.teleport(new TeleportTransition(destination, SPAWN_POS, Vec3.ZERO, yRot, xRot, TeleportTransition.DO_NOTHING));
        return true;
    }
}
