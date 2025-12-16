package nl.delphinity.scrumcraft2.common.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class WeedDuckyEntity extends RubberDuckyEntity {
    public WeedDuckyEntity(Level level, double d, double e, double f, ItemStack itemStack) {
        super(level, d, e, f, itemStack);
    }

    public WeedDuckyEntity(EntityType<? extends RubberDuckyEntity> entityType, Level level) {
        super(entityType, level);
    }

    public WeedDuckyEntity(ServerLevel level, LivingEntity livingEntity, ItemStack stack) {
        super(level, livingEntity, stack);
    }

    @Override
    protected void onHit(HitResult hitResult) {
        if(hitResult instanceof EntityHitResult entityHitResult) {
            Entity hit = entityHitResult.getEntity();
            if (hit instanceof LivingEntity livingEntity) {
                MobEffectInstance effect = livingEntity.getEffect(MobEffects.NAUSEA);

                int amplifier = effect != null ? effect.getAmplifier()+1 : 1;

                livingEntity.addEffect(
                        new MobEffectInstance(MobEffects.NAUSEA,
                                600,
                                amplifier)
                );
            }
        }

        super.onHit(hitResult);
    }
}
