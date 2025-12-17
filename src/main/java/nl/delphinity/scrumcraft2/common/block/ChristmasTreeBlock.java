package nl.delphinity.scrumcraft2.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import nl.delphinity.scrumcraft2.init.ModItems;
import org.jetbrains.annotations.Nullable;

public class ChristmasTreeBlock extends Block {

    public ChristmasTreeBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        // Generate a random number between 0.0 and 1.0
        // Adjust "0.5f" to change the chance (e.g., 0.1f = 10% chance for event, 90% for items)
        if (level.random.nextFloat() < 0.5f) {

            //This is where I made it so it calls the event trigger thingy
            if (level instanceof ServerLevel serverLevel) {
            }
            player.awardStat(Stats.BLOCK_MINED.get(this));
        } else {
            //This just drops the loot table
            super.playerDestroy(level, player, pos, state, blockEntity, tool);
        }
    }


}