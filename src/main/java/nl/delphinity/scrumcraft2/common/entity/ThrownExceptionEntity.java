package nl.delphinity.scrumcraft2.common.entity;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import nl.delphinity.scrumcraft2.common.item.ExceptionItem;
import nl.delphinity.scrumcraft2.init.ModEntityTypes;
import nl.delphinity.scrumcraft2.init.ModItems;
import org.jetbrains.annotations.NotNull;

// Projectile for every ExceptionItem, the item decides what happens when it isn't caught
public class ThrownExceptionEntity extends ThrowableItemProjectile {

    public ThrownExceptionEntity(EntityType<? extends ThrownExceptionEntity> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownExceptionEntity(ServerLevel level, LivingEntity owner, ItemStack stack) {
        super(ModEntityTypes.THROWN_EXCEPTION, owner, level, stack);
    }

    public ThrownExceptionEntity(Level level, double x, double y, double z, ItemStack stack) {
        super(ModEntityTypes.THROWN_EXCEPTION, x, y, z, level, stack);
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!(level() instanceof ServerLevel level)) {
            return;
        }

        if (isCaught(hitResult)) {
            level.playSound(null, getX(), getY(), getZ(), SoundEvents.SHIELD_BLOCK, SoundSource.PLAYERS, 1F, 1F);
            if (getOwner() instanceof Player player) {
                player.sendOverlayMessage(Component.translatable("message.scrumcraft2.exception_caught").withStyle(ChatFormatting.GRAY));
            }
        } else if (getItem().getItem() instanceof ExceptionItem exception) {
            exception.onUncaught(level, this, hitResult);
        }

        discard();
    }

    // A raised shield works like a try/catch block
    private static boolean isCaught(HitResult hitResult) {
        return hitResult instanceof EntityHitResult entityHit
                && entityHit.getEntity() instanceof LivingEntity target
                && target.isBlocking();
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return ModItems.EXCEPTION;
    }
}
