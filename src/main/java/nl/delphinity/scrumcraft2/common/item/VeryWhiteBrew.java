package nl.delphinity.scrumcraft2.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class VeryWhiteBrew extends Item {

    public static final ResourceKey<Level> AGARTHA_LEVEL_KEY = ResourceKey.create(
            Registries.DIMENSION,
            Identifier.fromNamespaceAndPath("scrumcraft2", "agartha_dim")
    );

    public VeryWhiteBrew(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        ServerLevel serverLevel = (ServerLevel) level;

        ResourceKey<Level> destinationKey = serverLevel.dimension() == AGARTHA_LEVEL_KEY ?
                Level.OVERWORLD :
                AGARTHA_LEVEL_KEY;

        ServerLevel destinationLevel = serverLevel.getServer().getLevel(destinationKey);
        if (destinationLevel == null) {
            return InteractionResult.FAIL;
        }

        BlockPos spawnPos = new BlockPos(0, 100, 0);
        // Create destination position as Vec3
        Vec3 destPos = new Vec3(spawnPos.getX() + 0.5, spawnPos.getY() + 1, spawnPos.getZ() + 0.5);

        TeleportTransition transition = new TeleportTransition(
                destinationLevel,
                destPos,
                Vec3.ZERO,
                player.getYRot(),
                player.getXRot(),
                TeleportTransition.DO_NOTHING
        );

        player.teleport(transition);

        return InteractionResult.SUCCESS;
    }
}
