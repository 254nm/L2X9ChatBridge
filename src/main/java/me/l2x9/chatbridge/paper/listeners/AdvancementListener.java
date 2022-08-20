package me.l2x9.chatbridge.paper.listeners;

import lombok.RequiredArgsConstructor;
import me.l2x9.chatbridge.L2X9ChatBridge;
import net.dv8tion.jda.api.EmbedBuilder;
import net.minecraft.server.v1_12_R1.Advancement;
import org.bukkit.craftbukkit.v1_12_R1.advancement.CraftAdvancement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

@RequiredArgsConstructor
public class AdvancementListener implements Listener {
    private final L2X9ChatBridge plugin;
    @EventHandler
    public void onAdvancement(PlayerAdvancementDoneEvent event) {
        Advancement advancement = ((CraftAdvancement)event.getAdvancement()).getHandle();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setDescription(String.format(":partying_face: **%s** made the advancement %s", event.getPlayer().getName(), advancement.c().a().toPlainText()));
        embedBuilder.setColor(0x1E244B);
        plugin.getBot().getBridgeChannel().sendMessageEmbeds(embedBuilder.build()).queue();
    }
}
