package me.l2x9.chatbridge.discord.command;

import me.l2x9.chatbridge.discord.command.commands.HelpCommand;
import me.l2x9.chatbridge.discord.command.commands.PlayerListCommand;
import me.l2x9.chatbridge.discord.command.commands.TestCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandManager extends ListenerAdapter {
    private final Map<String, Command> commands;
    private final HelpCommand helpCommand;

    public CommandManager() {
        commands = new HashMap<>();
        helpCommand = new HelpCommand(commands);
        commands.put("help", helpCommand);
        commands.put("test", new TestCommand());
        commands.put("playerlist", new PlayerListCommand());
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        Message message = event.getMessage();
        if (event.getAuthor().isBot()) return;
        if (!message.getContentRaw().startsWith("$")) return;
        String[] raw = event.getMessage().getContentRaw().split(" ");
        Command command = commands.getOrDefault(raw[0].toLowerCase().replace("$",""), helpCommand);
        command.execute(Arrays.copyOfRange(raw, 1, raw.length), event.getMessage(), event.getAuthor(), (TextChannel) event.getChannel());
    }
}
