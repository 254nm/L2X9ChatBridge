package me.l2x9.chatbridge.discord.listeners;

import lombok.RequiredArgsConstructor;
import me.l2x9.chatbridge.L2X9ChatBridge;
import me.l2x9.chatbridge.Utils;
import me.l2x9.core.L2X9RebootCore;
import me.l2x9.core.chat.ChatInfo;
import me.l2x9.core.chat.ChatManager;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

@RequiredArgsConstructor
public class MessageListener extends ListenerAdapter {
    private final L2X9ChatBridge plugin;
    private final ChatManager chatManager = (ChatManager) L2X9RebootCore.getInstance().getManagers().stream().filter(m -> m instanceof ChatManager).findAny().get();
    private final Pattern usernameRegex = Pattern.compile("^\\w{3,16}$>");

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        Message message = event.getMessage();
        if (event.getAuthor().isBot()) return;
        if (message.getContentRaw().startsWith("$")) return;
        if (event.getChannel().equals(plugin.getBot().getBridgeChannel())) {
            StringBuilder msg = new StringBuilder();
            msg.append(ChatColor.stripColor(message.getContentRaw()));
            message.getAttachments().forEach(a -> msg.append(formatAttachment(event.getMessage().getContentRaw(), a)));
            String userName = event.getAuthor().getAsTag();
            String toMc = (message.getReferencedMessage() != null) ? String.format(Utils.translateChars("&7<&r&b%s&r&7> (&r&d%s&r&7)&r&3 %s&r"), userName, parseReply(message), msg) : String.format(Utils.translateChars("&7<&r&b%s&r&7>&r&3 %s&r"), userName, msg);
            Bukkit.getOnlinePlayers().forEach(p -> {
                ChatInfo ci = chatManager.getInfo(p);
                if (!ci.isToggledChat()) p.sendMessage(toMc);
            });
            Utils.log(toMc);
            message.addReaction(Emoji.fromUnicode("\u2705")).queue();
        }
    }

    private String parseReply(Message message) {
        Message ref = message.getReferencedMessage();
        if (ref.getAuthor().equals(plugin.getBot().getJda().getSelfUser()) && ref.getEmbeds().size() == 0) {
            return ref.getContentRaw().trim().split(" ")[0].trim().replace("<", "").replace(">", "");
        } else return ref.getAuthor().getAsTag();
    }

    private String formatAttachment(String message, Message.Attachment attachment) {
        if (message.endsWith(" ")) return attachment.getUrl();
        return " ".concat(attachment.getUrl()).concat(" ");
    }
}
