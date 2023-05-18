package de.greenman999.daycounter.commands;

import de.greenman999.daycounter.DayCounter;
import de.greenman999.daycounter.PlayerConfig;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.BooleanArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class DayCounterCommand {

    public static void register(DayCounter plugin) {
        new CommandAPICommand("daycounter")
                .withSubcommand(new CommandAPICommand("messages")
                        .withSubcommand(new CommandAPICommand("sendChat")
                                .withArguments(new BooleanArgument("value"))
                                .executesPlayer((player, args) -> {
                                    Object value = args.get("value");
                                    boolean bool = false;
                                    if(value instanceof Boolean) bool = (boolean) value;

                                    PlayerConfig playerConfig = plugin.playerConfigs.get(player.getUniqueId());
                                    playerConfig.setSendChat(bool);
                                    playerConfig.save();
                                    player.sendMessage(Component.text("Send Chat has been set to " + bool, NamedTextColor.GREEN));
                                })
                        )
                        .withSubcommand(new CommandAPICommand("sendActionbar")
                                .withArguments(new BooleanArgument("value"))
                                .executesPlayer((player, args) -> {
                                    Object value = args.get("value");
                                    boolean bool = false;
                                    if(value instanceof Boolean) bool = (boolean) value;

                                    PlayerConfig playerConfig = plugin.playerConfigs.get(player.getUniqueId());
                                    playerConfig.setSendActionbar(bool);
                                    playerConfig.save();
                                    player.sendMessage(Component.text("Send Actionbar has been set to " + bool, NamedTextColor.GREEN));
                                })
                        )
                        .withSubcommand(new CommandAPICommand("sendTitle")
                                .withArguments(new BooleanArgument("value"))
                                .executesPlayer((player, args) -> {
                                    Object value = args.get("value");
                                    boolean bool = false;
                                    if(value instanceof Boolean) bool = (boolean) value;

                                    PlayerConfig playerConfig = plugin.playerConfigs.get(player.getUniqueId());
                                    playerConfig.setSendTitle(bool);
                                    playerConfig.save();
                                    player.sendMessage(Component.text("Send Title has been set to " + bool, NamedTextColor.GREEN));
                                })
                        )
                        .withSubcommand(new CommandAPICommand("color")
                                .withArguments(new StringArgument("value").replaceSuggestions(ArgumentSuggestions.strings(NamedTextColor.NAMES.keys())))
                                .executesPlayer((player, args) -> {
                                    Object value = args.get("value");
                                    NamedTextColor color = NamedTextColor.GREEN;
                                    if(value instanceof String) color = NamedTextColor.NAMES.value((String) value);

                                    PlayerConfig playerConfig = plugin.playerConfigs.get(player.getUniqueId());
                                    playerConfig.setColor(color);
                                    playerConfig.save();
                                    player.sendMessage(Component.text("Color has been set to " + color, NamedTextColor.GREEN));
                                })
                        )
                )
                .withSubcommand(new CommandAPICommand("sounds")
                        .withSubcommand(new CommandAPICommand("playBell")
                                .withArguments(new BooleanArgument("value"))
                                .executesPlayer((player, args) -> {
                                    Object value = args.get("value");
                                    boolean bool = false;
                                    if(value instanceof Boolean) bool = (boolean) value;

                                    PlayerConfig playerConfig = plugin.playerConfigs.get(player.getUniqueId());
                                    playerConfig.setPlayBell(bool);
                                    playerConfig.save();
                                    player.sendMessage(Component.text("Play Bell has been set to " + bool, NamedTextColor.GREEN));
                                })
                        )
                )
                .withSubcommand(new CommandAPICommand("reload")
                        .withPermission("daycounter.command.reload")
                        .executesPlayer((player, args) -> {
                            for(PlayerConfig playerConfig : plugin.playerConfigs.values()) {
                                playerConfig.reload();
                            }
                            player.sendMessage(Component.text("The configuration has been reloaded!", NamedTextColor.GREEN));
                        })
                )
                .register();
    }

}
