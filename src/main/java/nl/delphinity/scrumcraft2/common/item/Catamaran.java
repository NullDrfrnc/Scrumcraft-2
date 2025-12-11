package nl.delphinity.scrumcraft2.common.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;

import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import nl.delphinity.scrumcraft2.init.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Catamaran extends Item {
    private static final Random RANDOM = new Random();

    public Catamaran(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        if (!level.isClientSide()) {
            double baseX = player.getX();
            double baseY = player.getY();
            double baseZ = player.getZ();

            double dx = -Math.sin(Math.toRadians(player.getYRot()));
            double dz =  Math.cos(Math.toRadians(player.getYRot()));

            for (int i = 0; i < 10; i++) {
                Boat boat = new Boat(
                        getRandomBoat(),
                        level,
                        () -> ModItems.CATAMARAN
                );
                boat.setPos(
                        baseX + dx,
                        baseY,
                        baseZ + dz
                );

                level.addFreshEntity(boat);
            }

            if (!player.isCreative()) {
                player.getItemInHand(interactionHand).shrink(1);
            }
        }

        return InteractionResult.SUCCESS;
    }

    private EntityType<? extends Boat> getRandomBoat() {
        EntityType<? extends Boat>[] types = new EntityType[]{
                EntityType.OAK_BOAT,
                EntityType.SPRUCE_BOAT,
                EntityType.BIRCH_BOAT,
                EntityType.JUNGLE_BOAT,
                EntityType.ACACIA_BOAT,
                EntityType.DARK_OAK_BOAT,
                EntityType.MANGROVE_BOAT,
                EntityType.CHERRY_BOAT,
                EntityType.PALE_OAK_BOAT
        };

        return types[RANDOM.nextInt(types.length)];
    }
}
