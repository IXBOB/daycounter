package de.greenman999.daycounter;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class DayCounterListener implements Listener {

    private final DayCounter plugin;

    public DayCounterListener(DayCounter plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        plugin.playerConfigs.put(event.getPlayer().getUniqueId(), new PlayerConfig(event.getPlayer(), plugin));
        plugin.getMessages().sendTitle(event.getPlayer());
    }

}
