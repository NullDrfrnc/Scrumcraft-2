package nl.delphinity.scrumcraft2.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class ChristmasTreeBlock extends RandomEventBlock {

    public ChristmasTreeBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void triggerEvent(ServerLevel level, BlockPos pos, ServerPlayer player) {
        // TODO: no event yet, it just doesn't drop anything
    }
}
