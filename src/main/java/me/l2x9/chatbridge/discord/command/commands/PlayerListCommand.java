package me.l2x9.chatbridge.discord.command.commands;

import me.l2x9.chatbridge.discord.command.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.Bukkit;

public class PlayerListCommand extends Command {

    @Override
    public void execute(String[] args, Message message, User sender, TextChannel channel) {
        EmbedBuilder builder = new EmbedBuilder();
        StringBuilder sb = new StringBuilder();
        builder.setColor(0x00a86b);
        if (Bukkit.getOnlinePlayers().size() == 0) {
            builder.setDescription("There are no players currently online");
        } else  {
            Bukkit.getOnlinePlayers().forEach((p) -> sb.append(p.getName()).append("\n"));
            builder.setDescription(sb.substring(0, sb.length() -1));
        }

        message.replyEmbeds(builder.build()).queue();
    }

    @Override
    public String getUsage() {
        return "$playerlist";
    }

    @Override
    public String getDescription() {
        return "List all the players currently on the server";
    }
}
