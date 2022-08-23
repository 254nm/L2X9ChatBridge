package me.l2x9.chatbridge.discord.command;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public abstract class Command {
    public abstract void execute(String[] args, Message message, User sender, TextChannel channel);

    public abstract String getUsage();

    public abstract String getDescription();
}
