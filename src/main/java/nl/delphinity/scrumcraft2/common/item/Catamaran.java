package nl.delphinity.scrumcraft2.common.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;

import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
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
            HitResult hitResult = player.pick(20.0D, 0.0F, true);
            Vec3 targetPos = hitResult.getLocation();
            for (int i = 0; i < 10; i++) {
                Boat boat = new Boat(
                        getRandomBoat(),
                        level,
                        () -> ModItems.CATAMARAN
                );
                boat.setPos(targetPos.x, targetPos.y, targetPos.z);
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
