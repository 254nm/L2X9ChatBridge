package me.l2x9.chatbridge;

import org.bukkit.ChatColor;

import java.util.logging.Level;

public class Utils {

    public static void log(String format, Object... args) {
        StackTraceElement element = Thread.currentThread().getStackTrace()[2];
        String message = String.format(format, args);
        message = translateChars(message);
        L2X9ChatBridge.getInstance().getLogger().log(Level.INFO, String.format("%s%c%s", message, Character.MIN_VALUE, element.getClassName()));
    }
    public static String translateChars(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
