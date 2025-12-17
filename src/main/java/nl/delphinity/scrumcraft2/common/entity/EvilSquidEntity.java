package nl.delphinity.scrumcraft2.common.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.animal.squid.Squid;
import net.minecraft.world.entity.projectile.throwableitemprojectile.Snowball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import nl.delphinity.scrumcraft2.init.ModEntityTypes;
import nl.delphinity.scrumcraft2.init.ModItems;

public class EvilSquidEntity extends Squid implements RangedAttackMob {

    public EvilSquidEntity(EntityType<? extends Squid> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(0, new RangedAttackGoal(this, 1.0, 10, 16.0f));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }
    
    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        if (this.level().isClientSide()) return;

        RubberDuckyEntity rubberDucky = new RubberDuckyEntity(ModEntityTypes.RUBBER_DUCKY_ENTITY, this.level());
        rubberDucky.setOwner(this);
        rubberDucky.setItem(new ItemStack(ModItems.RUBBER_DUCKY));
        rubberDucky.setPos(this.getX(), this.getEyeY() - 0.1, this.getZ());

        double dx = target.getX() - this.getX();
        double dz = target.getZ() - this.getZ();
        double dy = target.getEyeY() - rubberDucky.getY();
        double d = Math.sqrt(dx * dx + dz * dz);

        rubberDucky.shoot(dx, dy + d * 0.2, dz, 1.6f, 0.0f);
        this.level().addFreshEntity(rubberDucky);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Squid.createAttributes()
                .add(Attributes.MAX_HEALTH, 30.0)
                .add(Attributes.MOVEMENT_SPEED, 1.0)
                .add(Attributes.FOLLOW_RANGE, 40.0)
                .add(Attributes.ATTACK_DAMAGE, 6.0);
    }



}

