package nl.delphinity.scrumcraft2.common.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import nl.delphinity.scrumcraft2.init.ModEntityTypes;
import nl.delphinity.scrumcraft2.init.ModItems;
import nl.delphinity.scrumcraft2.init.ModSounds;
import org.jetbrains.annotations.NotNull;

public class ScrumBallEntity extends ThrowableItemProjectile {

    private double knockback;

    public ScrumBallEntity(EntityType<? extends ScrumBallEntity> entityType, Level level) {
        super(entityType, level);
    }

    public ScrumBallEntity(ServerLevel level, LivingEntity livingEntity, ItemStack stack) {
        super(ModEntityTypes.SCRUM_BALL_ENTITY, livingEntity, level, stack);
    }

    public ScrumBallEntity(ServerLevel level, LivingEntity livingEntity, ItemStack stack, double knockback) {
        super(ModEntityTypes.SCRUM_BALL_ENTITY, livingEntity, level, stack);
        setKnockback(knockback);
    }

    public ScrumBallEntity(Level level, double d, double e, double f, ItemStack itemStack) {
        super(ModEntityTypes.SCRUM_BALL_ENTITY, d, e, f, level, itemStack);
    }

    public void setKnockback(double knockback) {
        this.knockback = knockback;
    }

    @Override
    protected void onHitEntity(net.minecraft.world.phys.EntityHitResult hitResult) {
        super.onHitEntity(hitResult);

        var target = hitResult.getEntity();
        if (target instanceof LivingEntity living) {
            // knockback
            double strength = knockback;
            double x = this.getDeltaMovement().x;
            double y = this.getDeltaMovement().y;
            double z = this.getDeltaMovement().z;

            // Normalize horizontal direction
            double horizLen = Math.sqrt(x * x + z * z);
            if (horizLen > 0.0001D) {
                x /= horizLen;
                z /= horizLen;
            }

            // Apply knockback
            living.push(x * strength, y * strength, z * strength);
            living.hurtMarked = true;
        }

        Level level = level();
        level.playSound(null, this.getX(), this.getY(), this.getZ(),
                SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F, 1F);

        if (!level.isClientSide()) {
            this.kill((ServerLevel) level);
        }
    }

    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return ModItems.SCRUM_BALL;
    }
}
