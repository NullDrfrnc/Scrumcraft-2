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
import nl.delphinity.scrumcraft2.common.util.DataBreach;
import nl.delphinity.scrumcraft2.common.util.Titles;
import nl.delphinity.scrumcraft2.init.ModBlocks;
import nl.delphinity.scrumcraft2.init.ModDamageTypes;
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
            ScrumBlock::christmasTree,
            ScrumBlock::nullPointer,
            ScrumBlock::stackOverflow,
            ScrumBlock::strutsVulnerability,
            ScrumBlock::tomcat,
            ScrumBlock::nPlusOneQuery
    );

    private static final int STACK_OVERFLOW_START = 8;
    private static final int STACK_OVERFLOW_HEIGHT = 16;
    private static final int LEAKED_ITEMS = 5;

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

    private static void nullPointer(ServerLevel level, BlockPos pos, ServerPlayer player) {
        Titles.show(player, Component.literal("NullPointerException").withStyle(ChatFormatting.RED));
        player.hurtServer(level, player.damageSources().source(ModDamageTypes.NULL_POINTER), 8.0F);
    }

    // A tower of sand falls on your head, only in empty spots so it doesn't break anything
    private static void stackOverflow(ServerLevel level, BlockPos pos, ServerPlayer player) {
        Titles.show(player, Component.literal("StackOverflowError").withStyle(ChatFormatting.RED));
        BlockPos bottom = player.blockPosition().above(STACK_OVERFLOW_START);
        for (int i = 0; i < STACK_OVERFLOW_HEIGHT; i++) {
            BlockPos stackPos = bottom.above(i);
            if (level.isEmptyBlock(stackPos)) {
                FallingBlockEntity.fall(level, stackPos, Blocks.SAND.defaultBlockState());
            }
        }
    }

    private static void strutsVulnerability(ServerLevel level, BlockPos pos, ServerPlayer player) {
        DataBreach.leak(player, LEAKED_ITEMS);
    }

    // Catalina is the real name of Tomcat's servlet container
    private static void tomcat(ServerLevel level, BlockPos pos, ServerPlayer player) {
        Titles.show(player, Component.translatable("title.scrumcraft2.server_startup").withStyle(ChatFormatting.GOLD));
        spawnNamed(level, ModEntityTypes.TOMCAT, pos, "Catalina");
    }

    private static void nPlusOneQuery(ServerLevel level, BlockPos pos, ServerPlayer player) {
        spawnNamed(level, ModEntityTypes.N_PLUS_ONE_QUERY, pos, "SELECT * FROM scrum");
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
