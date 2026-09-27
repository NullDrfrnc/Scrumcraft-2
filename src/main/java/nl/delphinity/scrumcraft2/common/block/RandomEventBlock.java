package nl.delphinity.scrumcraft2.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

// Block that has a chance to trigger an event instead of dropping its loot
public abstract class RandomEventBlock extends Block {

    public RandomEventBlock(Properties properties) {
        super(properties);
    }

    protected abstract void triggerEvent(ServerLevel level, BlockPos pos, ServerPlayer player);

    @Override
    public void playerDestroy(ServerLevel level, ServerPlayer player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        // Adjust "0.5f" to change the chance (e.g., 0.1f = 10% chance for event, 90% for items)
        if (level.getRandom().nextFloat() < 0.5f) {
            triggerEvent(level, pos, player);
            player.awardStat(Stats.BLOCK_MINED.get(this));
        } else {
            //This just drops the loot table
            super.playerDestroy(level, player, pos, state, blockEntity, tool);
        }
    }
}
