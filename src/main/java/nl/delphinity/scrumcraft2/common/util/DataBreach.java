package nl.delphinity.scrumcraft2.common.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Util;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;

import java.util.stream.IntStream;

// The Struts vulnerability that caused the Equifax breach, leaks random items out of your inventory
public class DataBreach {

    public static void leak(ServerPlayer player, int amount) {
        Inventory inventory = player.getInventory();
        IntStream filledSlots = IntStream.range(0, inventory.getNonEquipmentItems().size())
                .filter(slot -> !inventory.getItem(slot).isEmpty());

        Util.toShuffledList(filledSlots, player.getRandom()).intStream().limit(amount).forEach(slot -> {
            ItemEntity drop = player.createItemStackToDrop(inventory.removeItemNoUpdate(slot), true, false);
            if (drop != null) {
                player.level().addFreshEntity(drop);
            }
        });

        Titles.show(player,
                Component.literal("CVE-2017-5638").withStyle(ChatFormatting.RED),
                Component.translatable("title.scrumcraft2.data_breach")
        );
    }
}
