package me.l2x9.chatbridge.discord.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class PlayerListCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getName().equals("playerlist")) return;
        long time = System.currentTimeMillis();
        event.reply("bruh!").flatMap(v -> event.getHook().editOriginalFormat("Ping to discord: %d ms", System.currentTimeMillis() - time)).queue(); // Queue both reply and edit
    }
}
