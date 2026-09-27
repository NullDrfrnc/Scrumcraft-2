package nl.delphinity.scrumcraft2.common.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

// Spawns a bunch of vehicles where the player is looking
public abstract class VehicleSpawnerItem extends Item {
    private static final int AMOUNT = 10;

    public VehicleSpawnerItem(Properties properties) {
        super(properties);
    }

    protected abstract Entity createVehicle(Level level);

    @Override
    public @NotNull InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        if (!level.isClientSide()) {
            Vec3 targetPos = player.pick(20.0D, 0.0F, true).getLocation();
            for (int i = 0; i < AMOUNT; i++) {
                Entity vehicle = createVehicle(level);
                vehicle.setPos(targetPos);
                level.addFreshEntity(vehicle);
            }
            player.getItemInHand(interactionHand).consume(1, player);
        }
        return InteractionResult.SUCCESS;
    }
}
