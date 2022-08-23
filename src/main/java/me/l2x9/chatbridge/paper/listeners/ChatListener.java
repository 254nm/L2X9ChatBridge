package me.l2x9.chatbridge.paper.listeners;

import lombok.RequiredArgsConstructor;
import me.l2x9.chatbridge.L2X9ChatBridge;
import me.l2x9.core.event.CheckedChatEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@RequiredArgsConstructor
public class ChatListener implements Listener {
    private final L2X9ChatBridge plugin;
    @EventHandler
    public void onChat(CheckedChatEvent event) { //This event is from the core plugin and is produced by its chat listener
        plugin.getBot().sendBridgeMessage(ChatColor.stripColor(event.getMessage()));
    }
}
