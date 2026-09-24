package nl.delphinity.scrumcraft2.common.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.golem.SnowGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
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
        Item ballItem = switch (this.level().getRandom().nextInt(3)) {
            case 0 -> ModItems.SCRUM_BALL;
            case 1 -> ModItems.ULTIMATE_SCRUM_BALL;
            default -> ModItems.SCRUM_MASTER_BALL;
        };

        EvilScrumball ball = new EvilScrumball(this.level(), this);
        ball.setItem(new ItemStack(ballItem));
        ball.setPos(this.getX(), this.getEyeY() - 0.1, this.getZ());

        double dx = target.getX() - this.getX();
        double dy = target.getEyeY() - ball.getY();
        double dz = target.getZ() - this.getZ();
        ball.shoot(dx, dy, dz, 1.6F, 12.0F);
        this.level().addFreshEntity(ball);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25, 1, 50.0F));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public boolean isAggressive() {
        return true;
    }
}
