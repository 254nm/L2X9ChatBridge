package me.l2x9.chatbridge;

import lombok.Getter;
import me.l2x9.chatbridge.discord.command.CommandManager;
import me.l2x9.chatbridge.discord.listeners.MessageListener;
import me.l2x9.chatbridge.paper.listeners.AdvancementListener;
import me.l2x9.chatbridge.paper.listeners.ChatListener;
import me.l2x9.chatbridge.paper.listeners.DeathListener;
import me.l2x9.chatbridge.paper.listeners.JoinLeaveListener;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class L2X9ChatBridge extends JavaPlugin {

    private static L2X9ChatBridge instance;
    private ConfigurationSection discordConfig;
    private ConfigurationSection paperConfig;
    private Bot bot;

    public static L2X9ChatBridge getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        Configuration config = getConfig();
        discordConfig = config.getConfigurationSection("Discord");
        paperConfig = config.getConfigurationSection("Minecraft");
        getLogger().addHandler(new LoggerHandler());
        bot = new Bot(discordConfig.getString("Token"), this);
        bot.getJda().addEventListener(new CommandManager(), new MessageListener(this));
        bot.getJda().upsertCommand("playerlist", "List all the players on the server").queue();
        register(new ChatListener(this));
        register(new DeathListener(this));
        register(new JoinLeaveListener(this));
        register(new AdvancementListener(this));
    }
    private void register(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

    @Override
    public void onDisable() {
        getBot().getJda().shutdownNow();
    }
}
