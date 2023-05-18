package de.greenman999.daycounter;

import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PlayerConfig {

    private final PersistentDataContainer data;
    private final DayCounter plugin;

    private NamedTextColor color;
    private boolean sendTitle;
    private boolean sendActionbar;
    private boolean sendChat;
    private boolean playBell;

    public PlayerConfig(Player player, DayCounter plugin) {
        this.plugin = plugin;
        this.data = player.getPersistentDataContainer();

        this.color = NamedTextColor.GREEN;
        this.sendTitle = true;
        this.sendActionbar = false;
        this.sendChat = false;
        this.playBell = false;
        initializeData();
        readData();
    }

    private void initializeData() {
        if(!data.has(new NamespacedKey(plugin, "color"), PersistentDataType.STRING)) {
            save();
        }
    }

    private void readData() {
        color = NamedTextColor.NAMES.value(data.get(new NamespacedKey(plugin, "color"), PersistentDataType.STRING));
        sendTitle = data.get(new NamespacedKey(plugin, "sendTitle"), PersistentDataType.INTEGER) == 1;
        sendActionbar = data.get(new NamespacedKey(plugin, "sendActionbar"), PersistentDataType.INTEGER) == 1;
        sendChat = data.get(new NamespacedKey(plugin, "sendChat"), PersistentDataType.INTEGER) == 1;
        playBell = data.get(new NamespacedKey(plugin, "playBell"), PersistentDataType.INTEGER) == 1;
    }

    public void save() {
        data.set(new NamespacedKey(plugin, "color"), PersistentDataType.STRING, NamedTextColor.NAMES.key(color));
        data.set(new NamespacedKey(plugin, "sendTitle"), PersistentDataType.INTEGER, sendTitle ? 1 : 0);
        data.set(new NamespacedKey(plugin, "sendActionbar"), PersistentDataType.INTEGER, sendActionbar ? 1 : 0);
        data.set(new NamespacedKey(plugin, "sendChat"), PersistentDataType.INTEGER, sendChat ? 1 : 0);
        data.set(new NamespacedKey(plugin, "playBell"), PersistentDataType.INTEGER, playBell ? 1 : 0);
    }

    public void reload() {
        readData();
    }

    public NamedTextColor getColor() {
        return color;
    }

    public void setColor(NamedTextColor color) {
        this.color = color;
    }

    public boolean shouldSendTitle() {
        return sendTitle;
    }

    public void setSendTitle(boolean sendTitle) {
        this.sendTitle = sendTitle;
    }

    public boolean shouldSendActionbar() {
        return sendActionbar;
    }

    public void setSendActionbar(boolean sendActionbar) {
        this.sendActionbar = sendActionbar;
    }

    public boolean shouldSendChat() {
        return sendChat;
    }

    public void setSendChat(boolean sendChat) {
        this.sendChat = sendChat;
    }

    public boolean shouldPlayBell() {
        return playBell;
    }

    public void setPlayBell(boolean playBell) {
        this.playBell = playBell;
    }
}
