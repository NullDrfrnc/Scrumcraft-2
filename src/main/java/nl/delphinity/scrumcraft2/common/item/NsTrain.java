package nl.delphinity.scrumcraft2.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.minecart.Minecart;
import net.minecraft.world.entity.vehicle.minecart.MinecartSpawner;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import nl.delphinity.scrumcraft2.init.ModEntityTypes;
import nl.delphinity.scrumcraft2.init.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class NsTrain extends Item {
    private static final Random RANDOM = new Random();

    public NsTrain(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        if (!level.isClientSide()) {
            HitResult hitResult = player.pick(20.0D, 0.0F, true);
            Vec3 targetPos = hitResult.getLocation();
            for (int i = 0; i < 10; i++) {
                if (!level.isClientSide()) {
                    Minecart cart = new Minecart(
                            getRandomCart(),
                            level
                    );
                    cart.setPos(targetPos.x, targetPos.y, targetPos.z);
                    level.addFreshEntity(cart);
                }

            }
            if (!player.isCreative()) {
                player.getItemInHand(interactionHand).shrink(1);
            }
        }
        return InteractionResult.SUCCESS;
    }

    private EntityType<? extends Minecart> getRandomCart() {
        EntityType<? extends Minecart>[] types = new EntityType[]{
                EntityType.MINECART,
                EntityType.TNT_MINECART,
                EntityType.HOPPER_MINECART,
                EntityType.CHEST_MINECART,
                EntityType.FURNACE_MINECART,
                EntityType.COMMAND_BLOCK_MINECART,
                EntityType.SPAWNER_MINECART
        };

        return types[RANDOM.nextInt(types.length)];
    }
}

