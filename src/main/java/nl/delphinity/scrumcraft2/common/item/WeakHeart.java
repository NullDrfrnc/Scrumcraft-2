package nl.delphinity.scrumcraft2.common.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.zombie.ZombieVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import nl.delphinity.scrumcraft2.init.ModDamageTypes;
import nl.delphinity.scrumcraft2.mixin.ZombieVillagerInvoker;
import org.jetbrains.annotations.NotNull;

public class WeakHeart extends Item {

    public WeakHeart(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, @NotNull Player player, @NotNull LivingEntity target, @NotNull InteractionHand hand) {
        if (!(player.level() instanceof ServerLevel level)) {
            return InteractionResult.SUCCESS;
        }

        // Used on anything else than zomber vilger bc yaya -> Damage Player
        if (!(target instanceof ZombieVillager zombieVillager)) {
            damagePlayer(level, player, stack);
            return InteractionResult.SUCCESS;
        }

        if (zombieVillager.isConverting()) {
            return InteractionResult.PASS;
        }

        ((ZombieVillagerInvoker) zombieVillager).invokeStartConverting(player.getUUID(), 100);
        level.levelEvent(null, 1027, zombieVillager.blockPosition(), 0);
        stack.shrink(1);
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if (level instanceof ServerLevel serverLevel) {
            damagePlayer(serverLevel, player, player.getItemInHand(hand));
        }
        return InteractionResult.SUCCESS;
    }

    private void damagePlayer(ServerLevel level, Player player, ItemStack stack) {
        // "1 damage" = 0.5 hearts. So 6 damage = 3 hearts :DDDDDDDDDDDDDDDDDD (I'm going insamne)
        player.hurtServer(level, player.damageSources().source(ModDamageTypes.WEAK_HEART_OUCHIE), 6.0f);
        stack.consume(1, player);
    }
}
