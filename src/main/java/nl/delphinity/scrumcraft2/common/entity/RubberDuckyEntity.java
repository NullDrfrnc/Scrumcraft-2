package nl.delphinity.scrumcraft2.common.entity;

import com.llamalad7.mixinextras.expression.impl.ast.expressions.ThisExpression;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import nl.delphinity.scrumcraft2.init.ModEntityTypes;
import nl.delphinity.scrumcraft2.init.ModItems;
import nl.delphinity.scrumcraft2.init.ModSounds;
import org.jetbrains.annotations.NotNull;

public class RubberDuckyEntity extends ThrowableItemProjectile {

    public RubberDuckyEntity(Level level, double d, double e, double f, ItemStack itemStack) {
        super(ModEntityTypes.RUBBER_DUCKY_ENTITY, d, e, f, level, itemStack);
    }

    public RubberDuckyEntity(EntityType<? extends RubberDuckyEntity> entityType, Level level) {
        super(entityType, level);
    }

    public RubberDuckyEntity(ServerLevel level, LivingEntity livingEntity, ItemStack stack) {
        super(ModEntityTypes.RUBBER_DUCKY_ENTITY, livingEntity, level, stack);
    }
    
    @Override
    protected void onHit(HitResult hitResult) {
        Level level = level();

        level.playSound(null, this.getX(), this.getY(), this.getZ(), ModSounds.RUBBER_DUCKY_SQUEAK, SoundSource.PLAYERS, 0.5F, 1F);

        if (!level.isClientSide()) {
            this.kill((ServerLevel) level);
        }
    }
    
    @Override
    protected @NotNull Item getDefaultItem() {
        return ModItems.RUBBER_DUCKY;
    }
}
