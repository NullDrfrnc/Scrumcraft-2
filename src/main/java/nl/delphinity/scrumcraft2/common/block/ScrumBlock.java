package nl.delphinity.scrumcraft2.common.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Util;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import nl.delphinity.scrumcraft2.common.util.Titles;
import nl.delphinity.scrumcraft2.init.ModBlocks;
import nl.delphinity.scrumcraft2.init.ModEntityTypes;
import nl.delphinity.scrumcraft2.init.ModItems;
import nl.delphinity.scrumcraft2.init.ModSounds;

import java.util.List;

public class ScrumBlock extends RandomEventBlock {

    @FunctionalInterface
    private interface Event {
        void trigger(ServerLevel level, BlockPos pos, ServerPlayer player);
    }

    // Every event has the same chance, just add a new one to the list
    private static final List<Event> EVENTS = List.of(
            ScrumBlock::lightning,
            ScrumBlock::agarthian,
            ScrumBlock::sixSeven,
            ScrumBlock::evilSnowGolem,
            ScrumBlock::evilSquid,
            ScrumBlock::christmasTree
    );

    public ScrumBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void triggerEvent(ServerLevel level, BlockPos pos, ServerPlayer player) {
        Util.getRandom(EVENTS, level.getRandom()).trigger(level, pos, player);
    }

    private static void lightning(ServerLevel level, BlockPos pos, ServerPlayer player) {
        LightningBolt lightning = create(level, EntityTypes.LIGHTNING_BOLT, pos);
        if (lightning != null) {
            level.addFreshEntity(lightning);
        }
        FallingBlockEntity.fall(level, pos.above(10), Blocks.ANVIL.defaultBlockState());
    }

    private static void agarthian(ServerLevel level, BlockPos pos, ServerPlayer player) {
        Zombie zombie = create(level, EntityTypes.ZOMBIE, pos);
        if (zombie == null) {
            return;
        }
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

    // 67...
    private static void sixSeven(ServerLevel level, BlockPos pos, ServerPlayer player) {
        Titles.show(player, Component.literal("67").withStyle(ChatFormatting.RED));
        level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.SICK_SEVEN, SoundSource.MASTER, 1F, 1F);
    }

    private static void evilSnowGolem(ServerLevel level, BlockPos pos, ServerPlayer player) {
        spawnNamed(level, ModEntityTypes.EVIL_SNOW_GOLEM, pos, "Frostussy");
    }

    private static void evilSquid(ServerLevel level, BlockPos pos, ServerPlayer player) {
        level.setBlockAndUpdate(pos, Blocks.WATER.defaultBlockState());
        spawnNamed(level, ModEntityTypes.EVIL_SQUID, pos, "Octopussy");
    }

    private static void christmasTree(ServerLevel level, BlockPos pos, ServerPlayer player) {
        level.setBlockAndUpdate(pos.above(), ModBlocks.CHRISTMASTREE.defaultBlockState());
    }

    private static void spawnNamed(ServerLevel level, EntityType<?> type, BlockPos pos, String name) {
        Entity entity = create(level, type, pos);
        if (entity != null) {
            entity.setCustomName(Component.translatable(name));
            level.addFreshEntity(entity);
        }
    }

    private static <T extends Entity> T create(ServerLevel level, EntityType<T> type, BlockPos pos) {
        T entity = type.create(level, EntitySpawnReason.SPAWNER);
        if (entity != null) {
            entity.setPos(Vec3.atBottomCenterOf(pos));
        }
        return entity;
    }
}
