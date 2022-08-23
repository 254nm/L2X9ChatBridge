package me.l2x9.chatbridge.discord.command.commands;

import lombok.RequiredArgsConstructor;
import me.l2x9.chatbridge.discord.command.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;

import java.util.Map;

@RequiredArgsConstructor
public class HelpCommand extends Command {
    private final Map<String, Command> commands;

    @Override
    public void execute(String[] args, Message message, User sender, TextChannel channel) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("L2X9ChatBot Help");
        embed.setColor(0x5c73);
        embed.setImage("https://cdn.discordapp.com/attachments/1009472028518133870/1011755287113367714/server-icon-old.png");
        embed.setFooter(String.format("Command ran by %s", sender.getAsMention()));
        int index = 0;
        for (Map.Entry<String, Command> entry : commands.entrySet()) {
            Command command = entry.getValue();
            String name = entry.getKey();
            MessageEmbed.Field field = new MessageEmbed.Field(name, String.format("Usage: %s\nDescription: %s", command.getUsage(), command.getDescription()), (index % 2 == 0));
            embed.addField(field);
            index++;
        }
        message.replyEmbeds(embed.build()).queue();
    }

    @Override
    public String getUsage() {
        return "$help";
    }

    @Override
    public String getDescription() {
        return "Show a list of commands and how to use them";
    }
}
