package nl.delphinity.scrumcraft2.common.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import nl.delphinity.scrumcraft2.init.ModEntityTypes;

// The Hibernate N+1 problem: every hit fires one more query
public class NPlusOneQueryEntity extends Silverfish {
    private static final int MAX_QUERIES = 16;
    private static final double QUERY_RANGE = 16.0;

    public NPlusOneQueryEntity(EntityType<? extends Silverfish> type, Level level) {
        super(type, level);
    }

    // Same as a silverfish, but without hiding in stone (that would turn it back into a normal silverfish)
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0, false));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        boolean hurt = super.hurtServer(level, source, damage);
        if (hurt && source.getEntity() != null && countQueries(level) < MAX_QUERIES) {
            ModEntityTypes.N_PLUS_ONE_QUERY.spawn(level, blockPosition(), EntitySpawnReason.REINFORCEMENT);
        }
        return hurt;
    }

    private int countQueries(ServerLevel level) {
        return level.getEntitiesOfClass(NPlusOneQueryEntity.class, getBoundingBox().inflate(QUERY_RANGE)).size();
    }
}
