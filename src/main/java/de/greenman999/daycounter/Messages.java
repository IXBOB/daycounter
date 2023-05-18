package de.greenman999.daycounter;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;

public class Messages {

    private final DayCounter plugin;

    public Messages(DayCounter plugin) {
        this.plugin = plugin;
    }

    public void sendTitle(Player player) {
        plugin.playerConfigs.get(player.getUniqueId()).reload();
        if(!plugin.playerConfigs.get(player.getUniqueId()).shouldSendTitle()) return;
        player.showTitle(Title.title(buildDayComponent(player), Component.empty()));
    }

    public void sendActionbar(Player player) {
        plugin.playerConfigs.get(player.getUniqueId()).reload();
        if(!plugin.playerConfigs.get(player.getUniqueId()).shouldSendActionbar()) return;
        player.sendActionBar(buildDayComponent(player));
    }

    public void sendChat(Player player) {
        plugin.playerConfigs.get(player.getUniqueId()).reload();
        if(!plugin.playerConfigs.get(player.getUniqueId()).shouldSendChat()) return;
        player.sendMessage(Component.text("The sun is rising: ", NamedTextColor.GREEN).append(buildDayComponent(player)));
    }

    public Component buildDayComponent(Player player) {
        return MiniMessage.miniMessage().deserialize("<" + NamedTextColor.NAMES.key(plugin.playerConfigs.get(player.getUniqueId()).getColor()) + ">Day " + plugin.getDayCount());
    }

}
