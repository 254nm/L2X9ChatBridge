package me.l2x9.chatbridge;

import lombok.Getter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;

@Getter
public class Bot {
    private JDA jda;
    private TextChannel bridgeChannel;
    public Bot(String token, L2X9ChatBridge plugin) {
        try {
            long start = System.currentTimeMillis();
            JDABuilder builder = JDABuilder.createDefault(token);
            builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
            builder.setActivity(Activity.watching("L2X9 Chat"));
            jda = builder.build();
            jda.awaitReady();
            bridgeChannel = jda.getTextChannelById(plugin.getDiscordConfig().getString("BridgeChannelID"));
            Utils.log("&3Successfully logged into discord in&r&a %dms&r", (System.currentTimeMillis() - start));
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle(plugin.getDiscordConfig().getString("StartMessage"));
            embedBuilder.setColor(0x6eff3b);
            bridgeChannel.sendMessageEmbeds(embedBuilder.build()).queue();
        } catch (Throwable t) {
            Utils.log("&cFailed to log into discord due to &r&3 %s&r&c! Please see the stacktrace below for more info".trim(), t.getMessage().trim());
            t.printStackTrace();
           plugin.getServer().getPluginManager().disablePlugin(plugin, true);
        }
    }
    public void sendBridgeMessage(String message) {
        bridgeChannel.sendMessage(message).queue();
    }
}
