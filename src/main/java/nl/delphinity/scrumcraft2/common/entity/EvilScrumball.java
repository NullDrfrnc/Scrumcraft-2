package nl.delphinity.scrumcraft2.common.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import nl.delphinity.scrumcraft2.init.ModEntityTypes;
import nl.delphinity.scrumcraft2.init.ModItems;

public class EvilScrumball extends ThrowableItemProjectile {

    public EvilScrumball(EntityType<? extends ThrowableItemProjectile> type, Level level) {
        super(type, level);
    }

    public EvilScrumball(Level level, LivingEntity owner) {
        this(ModEntityTypes.SCRUM_BALL_ENTITY, level);
        this.setOwner(owner);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.SCRUM_BALL;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        if (this.level().isClientSide()) return;
        if (!(result.getEntity() instanceof LivingEntity living)) return;

        Entity owner = this.getOwner();
        living.hurt(this.damageSources().thrown(this, owner), 5.0F);
    }
}
