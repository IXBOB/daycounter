package de.greenman999.daycounter;

import de.greenman999.daycounter.commands.DayCounterCommand;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class DayCounter extends JavaPlugin {

    PluginManager pluginManager = Bukkit.getPluginManager();

    private final Messages messages = new Messages(this);
    public final HashMap<UUID, PlayerConfig> playerConfigs = new HashMap<>();

    @Override
    public void onEnable() {
        registerAllCommands();
        pluginManager.registerEvents(new DayCounterListener(this), this);

        AtomicBoolean beforeDoDaylightCycle = new AtomicBoolean(Boolean.TRUE.equals(Bukkit.getWorlds().getFirst().getGameRuleValue(GameRule.DO_DAYLIGHT_CYCLE)));

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            if(getDayTicks() == 1005) {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    messages.sendTitle(player);
                    messages.sendChat(player);
                    if(playerConfigs.get(player.getUniqueId()).shouldPlayBell()) player.playSound(player.getLocation(), Sound.BLOCK_BELL_USE, 1F, 0.7F);
                });
            }
            for(Player player : Bukkit.getOnlinePlayers()) {
                messages.sendActionbar(player);
            }
            if(beforeDoDaylightCycle.get() == Bukkit.getOnlinePlayers().isEmpty()) {
                setDaylightCycle(!Bukkit.getOnlinePlayers().isEmpty());
                beforeDoDaylightCycle.set(!Bukkit.getOnlinePlayers().isEmpty());
            }
        }, 0, 1);

        getLogger().info("Plugin successfully enabled and loaded!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin successfully disabled and unloaded!");
    }

    private void registerAllCommands() {
        DayCounterCommand.register(this);
    }

    public void setDaylightCycle(boolean value) {
        Bukkit.getWorlds().forEach(world -> world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, value));
    }

    public int getDayCount() {
        return ((int) Bukkit.getWorlds().getFirst().getFullTime()) / 24000;
    }

    public int getDayTicks() {
        return ((int) Bukkit.getWorlds().getFirst().getFullTime()) % 24000;
    }

    public Messages getMessages() {
        return messages;
    }
}
