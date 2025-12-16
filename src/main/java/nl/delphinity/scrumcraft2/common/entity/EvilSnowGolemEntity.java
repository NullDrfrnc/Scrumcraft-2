package nl.delphinity.scrumcraft2.common.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.golem.SnowGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.throwableitemprojectile.Snowball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import nl.delphinity.scrumcraft2.common.item.ScrumBall;
import nl.delphinity.scrumcraft2.init.ModEntityTypes;
import nl.delphinity.scrumcraft2.init.ModItems;

public class EvilSnowGolemEntity extends SnowGolem {

    public EvilSnowGolemEntity(EntityType<? extends SnowGolem> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return SnowGolem.createAttributes();
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        double dx = target.getX() - this.getX();
        double dz = target.getZ() - this.getZ();

        int choice = this.level().random.nextInt(3);

        if (choice == 0) {
            EvilScrumball scrumBall = new EvilScrumball(ModEntityTypes.SCRUM_BALL_ENTITY, this.level());
            scrumBall.setOwner(this);
            scrumBall.setItem(new ItemStack(ModItems.SCRUM_BALL));
            scrumBall.setPos(this.getX(), this.getEyeY() - 0.1, this.getZ());
            double dy = target.getEyeY() - scrumBall.getY();
            scrumBall.shoot(dx, dy, dz, 1.6F, 12.0F);
            this.level().addFreshEntity(scrumBall);
            return;
        }

        if (choice == 1) {
            EvilScrumball ultimateScrumball = new EvilScrumball(ModEntityTypes.SCRUM_BALL_ENTITY, this.level());
            ultimateScrumball.setOwner(this);
            ultimateScrumball.setItem(new ItemStack(ModItems.ULTIMATE_SCRUM_BALL));
            ultimateScrumball.setPos(this.getX(), this.getEyeY() - 0.1, this.getZ());
            double dy = target.getEyeY() - ultimateScrumball.getY();
            ultimateScrumball.shoot(dx, dy, dz, 1.6F, 12.0F);
            this.level().addFreshEntity(ultimateScrumball);
            return;
        }

        EvilScrumball scrumMasterball = new EvilScrumball(ModEntityTypes.SCRUM_BALL_ENTITY, this.level());
        scrumMasterball.setOwner(this);
        scrumMasterball.setItem(new ItemStack(ModItems.SCRUM_MASTER_BALL));
        scrumMasterball.setPos(this.getX(), this.getEyeY() - 0.1, this.getZ());
        double dy = target.getEyeY() - scrumMasterball.getY();
        scrumMasterball.shoot(dx, dy, dz, 1.6F, 12.0F);
        this.level().addFreshEntity(scrumMasterball);
    }




    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1,
                new RangedAttackGoal(
                        this,
                        1.25,
                        1,
                        50.0F
                )
        );

        this.targetSelector.addGoal(1,
                new NearestAttackableTargetGoal<>(
                        this,
                        Player.class,
                        true
                )
        );
    }

    @Override
    public boolean isAggressive() {
        return true;
    }
}
