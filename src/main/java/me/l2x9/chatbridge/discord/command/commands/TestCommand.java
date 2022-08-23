package me.l2x9.chatbridge.discord.command.commands;

import me.l2x9.chatbridge.discord.command.Command;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.Arrays;

public class TestCommand extends Command {
    @Override
    public void execute(String[] args, Message message, User sender, TextChannel channel) {
        message.reply(String.format("Got arguments: %s", Arrays.toString(args))).queue();
    }

    @Override
    public String getUsage() {
        return "$test <Any args>";
    }

    @Override
    public String getDescription() {
        return "Its a test command bruh";
    }
}
