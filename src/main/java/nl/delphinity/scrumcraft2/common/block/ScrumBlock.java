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

public class ScrumBlock extends Block {

    public ScrumBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        // Generate a random number between 0.0 and 1.0
        // Adjust "0.5f" to change the chance (e.g., 0.1f = 10% chance for event, 90% for items)
        if (level.random.nextFloat() < 0.5f) {

            //This is where I made it so it calls the event trigger thingy
            if (level instanceof ServerLevel serverLevel) {
                triggerEvent(serverLevel, pos);
            }
            player.awardStat(Stats.BLOCK_MINED.get(this));
        } else {
            //This just drops the loot table
            super.playerDestroy(level, player, pos, state, blockEntity, tool);
        }
    }

    private void triggerEvent(ServerLevel level, BlockPos pos) {
        if (level.random.nextBoolean()) {
            // Event A: Lightning
            LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level, EntitySpawnReason.SPAWNER);
            if (lightning != null) {
                lightning.setPos(Vec3.atBottomCenterOf(pos));
                level.addFreshEntity(lightning);
            }
            BlockPos anvilPos = pos.above(10);
            FallingBlockEntity.fall(level, anvilPos, Blocks.ANVIL.defaultBlockState());
        } else {
            // Event B: Mob Spawn
            Zombie zombie = EntityType.ZOMBIE.create(level, EntitySpawnReason.SPAWNER);
            if (zombie != null) {
                zombie.setPos(Vec3.atBottomCenterOf(pos));
                zombie.setCustomName(Component.translatable("Agarthian"));
                zombie.setItemInHand(InteractionHand.MAIN_HAND, ModItems.AGARTHA_POTION.getDefaultInstance());
                zombie.setItemInHand(InteractionHand.OFF_HAND, ModItems.AGARTHA_POTION.getDefaultInstance());
                zombie.setDropChance(EquipmentSlot.MAINHAND, 1.0f);
                zombie.setCanPickUpLoot(true);
                zombie.setGlowingTag(true);
                zombie.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.GOLDEN_HELMET));
                zombie.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Items.GOLDEN_CHESTPLATE));
                zombie.setItemSlot(EquipmentSlot.LEGS, new ItemStack(Items.GOLDEN_LEGGINGS));
                zombie.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.GOLDEN_BOOTS));
                level.addFreshEntity(zombie);
            }
        }
    }
}