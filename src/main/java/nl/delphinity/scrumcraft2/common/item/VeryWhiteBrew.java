package nl.delphinity.scrumcraft2.common.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import nl.delphinity.scrumcraft2.common.util.AgarthaTeleporter;
import org.jetbrains.annotations.NotNull;

public class VeryWhiteBrew extends Item {

    public VeryWhiteBrew(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        boolean teleported = AgarthaTeleporter.teleport((ServerLevel) level, player, player.getYRot(), player.getXRot());
        return teleported ? InteractionResult.SUCCESS : InteractionResult.FAIL;
    }
}
