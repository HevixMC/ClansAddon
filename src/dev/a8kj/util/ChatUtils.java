package dev.a8kj.util;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class ChatUtils {

    private ChatUtils() {
        throw new IllegalStateException("Utility class");
    }

    // Bl 8ods malan ab9tal !

    public static void sendClickableLink(Player player, String text, String url) {
        TextComponent message = new TextComponent(text);
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
        player.spigot().sendMessage(message);
    }

    public static void sendClickableCommand(Player player, String text, String command) {
        TextComponent message = new TextComponent(text);
        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        player.spigot().sendMessage(message);
    }

    public static void sendHoverText(Player player, String text, String hoverText) {
        TextComponent message = new TextComponent(text);
        message.setHoverEvent(
                new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[] { new TextComponent(hoverText) }));
        player.spigot().sendMessage(message);
    }

    public static void sendClickableSuggestion(Player player, String text, String suggestion) {
        TextComponent message = new TextComponent(text);
        message.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, suggestion));
        player.spigot().sendMessage(message);
    }

    public static void sendClickableLinkWithHover(Player player, String text, String url, String hoverText) {
        TextComponent message = new TextComponent(text);
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
        message.setHoverEvent(
                new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[] { new TextComponent(hoverText) }));
        player.spigot().sendMessage(message);
    }

    public static void sendClickableCommandWithHover(Player player, String text, String command, String hoverText) {
        TextComponent message = new TextComponent(text);
        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        message.setHoverEvent(
                new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[] { new TextComponent(hoverText) }));
        player.spigot().sendMessage(message);
    }

    public static void sendClickableSuggestionWithHover(Player player, String text, String suggestion,
            String hoverText) {
        TextComponent message = new TextComponent(text);
        message.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, suggestion));
        message.setHoverEvent(
                new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[] { new TextComponent(hoverText) }));
        player.spigot().sendMessage(message);
    }

    public static void sendColoredMessage(Player player, String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
