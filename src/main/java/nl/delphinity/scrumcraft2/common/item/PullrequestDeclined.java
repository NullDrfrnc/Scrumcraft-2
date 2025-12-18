package nl.delphinity.scrumcraft2.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.zombie.ZombieVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class PullrequestDeclined extends Item {

    public PullrequestDeclined(Properties properties) {
        super(properties);
    }

    public static final ResourceKey<Level> AGARTHA_LEVEL_KEY = ResourceKey.create(
            Registries.DIMENSION,
            Identifier.fromNamespaceAndPath("scrumcraft2", "agartha_dim")
    );

    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, @NotNull Player player, @NotNull LivingEntity target, @NotNull InteractionHand hand) {

        if (!player.level().isClientSide()) {
            if (target instanceof Entity entity) {
                BlockPos spawnPos = new BlockPos(0, 100, 0);
                ServerLevel serverLevel = (ServerLevel) player.level();

                ResourceKey<Level> destinationKey = serverLevel.dimension() == AGARTHA_LEVEL_KEY ?
                        Level.OVERWORLD :
                        AGARTHA_LEVEL_KEY;
                Vec3 destPos = new Vec3(spawnPos.getX() + 0.5, spawnPos.getY() + 1, spawnPos.getZ() + 0.5);
                ServerLevel destinationLevel = serverLevel.getServer().getLevel(destinationKey);
                if (destinationLevel == null) {
                    return InteractionResult.FAIL;
                }
                TeleportTransition transition = new TeleportTransition(
                        destinationLevel,
                        destPos,
                        Vec3.ZERO,
                        player.getYRot(),
                        player.getXRot(),
                        TeleportTransition.DO_NOTHING
                );

                entity.teleport(transition);
            }

            return InteractionResult.PASS;
        }
        return InteractionResult.SUCCESS;
    }

}
