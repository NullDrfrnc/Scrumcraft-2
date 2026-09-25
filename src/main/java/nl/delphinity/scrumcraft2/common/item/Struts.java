package nl.delphinity.scrumcraft2.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import nl.delphinity.scrumcraft2.common.util.DataBreach;
import org.jetbrains.annotations.NotNull;

// @Deprecated pickaxe: crossed out name like in IntelliJ, breaks fast and sometimes leaks your inventory
public class Struts extends Item {
    private static final float LEAK_CHANCE = 0.05F;
    private static final int LEAKED_ITEMS = 3;

    public Struts(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull Component getName(ItemStack itemStack) {
        return super.getName(itemStack).copy().withStyle(ChatFormatting.STRIKETHROUGH);
    }

    @Override
    public boolean mineBlock(ItemStack itemStack, Level level, BlockState state, BlockPos pos, LivingEntity owner) {
        if (owner instanceof ServerPlayer player && level.getRandom().nextFloat() < LEAK_CHANCE) {
            DataBreach.leak(player, LEAKED_ITEMS);
        }
        return super.mineBlock(itemStack, level, state, pos, owner);
    }
}
