package nl.delphinity.scrumcraft2.common.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.zombie.ZombieVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import nl.delphinity.scrumcraft2.init.ModDamageTypes;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.UUID;

public class WeakHeart extends Item {
    private static Method startConvertingMethod;
    static {
        try {
            // "startConverting" is the Mojank mapping name, thank yer
            startConvertingMethod = ZombieVillager.class.getDeclaredMethod("startConverting", UUID.class, int.class);
            startConvertingMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Failed to find startConverting method in ZombieVillager", e);
        }
    }

    public WeakHeart(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, @NotNull Player player, @NotNull LivingEntity target, @NotNull InteractionHand hand) {
        if (!player.level().isClientSide()) {
            if (target instanceof ZombieVillager zombieVillager) {
                // Check if already converting
                if (!zombieVillager.isConverting()) {
                    try {
                        startConvertingMethod.invoke(zombieVillager, player.getUUID(), 100);
                        player.level().levelEvent(null, 1027, zombieVillager.blockPosition(), 0);

                        stack.shrink(1);
                        return InteractionResult.SUCCESS;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return InteractionResult.FAIL;
                    }
                }
                return InteractionResult.PASS;
            } else {
                damagePlayer(player, stack, player.level());
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide()) {
            // Used on anything else than zomber vilger bc yaya -> Damage Player
            damagePlayer(player, stack, level);
        }
        return InteractionResult.SUCCESS;
    }

    private void damagePlayer(Player player, ItemStack stack, Level world) {
        // "1 damage" = 0.5 hearts. So 6 damage = 3 hearts :DDDDDDDDDDDDDDDDDD (I'm going insamne)
        DamageSource damageSource = new DamageSource(
                world.registryAccess()
                        .lookupOrThrow(Registries.DAMAGE_TYPE)
                        .getOrThrow(ModDamageTypes.WEAK_HEART_OUCHIE)
        );
        player.hurtServer((ServerLevel) world, damageSource, 6.0f);
        stack.shrink(1);
    }
}
