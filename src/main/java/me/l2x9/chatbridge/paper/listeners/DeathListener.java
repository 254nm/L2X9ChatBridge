package me.l2x9.chatbridge.paper.listeners;

import email.com.gmail.cosmoconsole.bukkit.deathmsg.DeathMessageBroadcastEvent;
import lombok.RequiredArgsConstructor;
import me.l2x9.chatbridge.L2X9ChatBridge;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@RequiredArgsConstructor
public class DeathListener implements Listener {
    private final L2X9ChatBridge plugin;
    @EventHandler
    public void onDie(DeathMessageBroadcastEvent event) {
        if (event.getWorld().equals(Bukkit.getWorlds().get(0))) { //DMP is weird
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle(String.format(":skull: %s", event.getMessage().toPlainText()));
            embedBuilder.setColor(0xaa0000);
            plugin.getBot().getBridgeChannel().sendMessageEmbeds(embedBuilder.build()).queue();
        }
    }
}
