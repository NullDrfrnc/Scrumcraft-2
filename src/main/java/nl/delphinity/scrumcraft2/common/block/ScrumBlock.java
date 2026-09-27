package nl.delphinity.scrumcraft2.common.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
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
import nl.delphinity.scrumcraft2.common.entity.EvilSnowGolemEntity;
import nl.delphinity.scrumcraft2.common.entity.EvilSquidEntity;
import nl.delphinity.scrumcraft2.init.ModBlocks;
import nl.delphinity.scrumcraft2.init.ModEntityTypes;
import nl.delphinity.scrumcraft2.init.ModItems;
import nl.delphinity.scrumcraft2.init.ModSounds;

public class ScrumBlock extends RandomEventBlock {

    public ScrumBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void triggerEvent(ServerLevel level, BlockPos pos, ServerPlayer player) {
        int event = 6;//level.getRandom().nextIntBetweenInclusive(1, 5);
        switch (event) {
            case 1 -> lightning(level, pos);
            case 2 -> agarthian(level, pos);
            case 3 -> sixSeven(level, player);
            case 4 -> evilSnowGolem(level, pos);
            case 5 -> evilSquid(level, pos);
            case 6 -> level.setBlockAndUpdate(pos.above(), ModBlocks.CHRISTMASTREE.defaultBlockState());
        }
    }

    private void lightning(ServerLevel level, BlockPos pos) {
        LightningBolt lightning = create(level, EntityTypes.LIGHTNING_BOLT, pos);
        if (lightning != null) {
            level.addFreshEntity(lightning);
        }
        FallingBlockEntity.fall(level, pos.above(10), Blocks.ANVIL.defaultBlockState());
    }

    private void agarthian(ServerLevel level, BlockPos pos) {
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
    private void sixSeven(ServerLevel level, ServerPlayer player) {
        player.connection.send(new ClientboundSetTitleTextPacket(Component.literal("67").withStyle(ChatFormatting.RED)));
        level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.SICK_SEVEN, SoundSource.MASTER, 1F, 1F);
    }

    private void evilSnowGolem(ServerLevel level, BlockPos pos) {
        EvilSnowGolemEntity snowGolem = create(level, ModEntityTypes.EVIL_SNOW_GOLEM, pos);
        if (snowGolem != null) {
            snowGolem.setCustomName(Component.translatable("Frostussy"));
            level.addFreshEntity(snowGolem);
        }
    }

    private void evilSquid(ServerLevel level, BlockPos pos) {
        level.setBlockAndUpdate(pos, Blocks.WATER.defaultBlockState());
        EvilSquidEntity squid = create(level, ModEntityTypes.EVIL_SQUID, pos);
        if (squid != null) {
            squid.setCustomName(Component.translatable("Octopussy"));
            level.addFreshEntity(squid);
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
