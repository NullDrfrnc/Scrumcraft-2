package nl.delphinity.scrumcraft2.common.util;

import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.server.level.ServerPlayer;

// Shows big text in the middle of the player's screen
public class Titles {

    public static void show(ServerPlayer player, Component title) {
        player.connection.send(new ClientboundSetTitleTextPacket(title));
    }

    public static void show(ServerPlayer player, Component title, Component subtitle) {
        player.connection.send(new ClientboundSetSubtitleTextPacket(subtitle));
        show(player, title);
    }
}
