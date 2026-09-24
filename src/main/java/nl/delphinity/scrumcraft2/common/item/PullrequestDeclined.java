package nl.delphinity.scrumcraft2.common.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import nl.delphinity.scrumcraft2.common.util.AgarthaTeleporter;
import org.jetbrains.annotations.NotNull;

public class PullrequestDeclined extends Item {

    public PullrequestDeclined(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, @NotNull Player player, @NotNull LivingEntity target, @NotNull InteractionHand hand) {
        if (player.level().isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        boolean teleported = AgarthaTeleporter.teleport((ServerLevel) player.level(), target, player.getYRot(), player.getXRot());
        return teleported ? InteractionResult.PASS : InteractionResult.FAIL;
    }
}
