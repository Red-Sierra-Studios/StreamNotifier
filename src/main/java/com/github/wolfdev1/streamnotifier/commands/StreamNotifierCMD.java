package com.github.wolfdev1.streamnotifier.commands;

import com.github.wolfdev1.streamnotifier.Plugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.jetbrains.annotations.NotNull;

public class StreamNotifierCMD implements CommandExecutor {

    Plugin plugin;

    public StreamNotifierCMD(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String l, @NotNull String[] args)
    {
        Configuration c = plugin.getConfig();
        String prefix = c.getString("prefix");
        if(args.length < 3)
        {

            String msg = prefix + "&r&7 | PaperMC\n" +
                    "&8Created by:&7 Red Sierra Studios\n" +
                    "&8Code by:&7 wolfdev1\n" +
                    "\n&2022-2023";

            s.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
        }
        else
        {
            if(args[0].equals("streamers"))
            {
                if(args[1].equals("add"))
                {
                    if(c.getStringList("streamersToNotify").contains(args[2]))
                    {
                        s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                prefix + "&7 The streamer "+args[2]+" is already on the list"));
                    }
                    else
                    {
                        c.getStringList("streamersToNotify").add(args[2]);
                        s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                prefix + "&7 The streamer "+args[2]+" added to the list"));
                        plugin.reloadConfig();
                    }
                }
                else
                {
                    if(args[1].equals("remove"))
                    {
                        if(!c.getStringList("streamersToNotify").contains(args[2]))
                        {
                            s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                    prefix + "&7 You can't remove "+args[2]+" because he's not on the list"));
                        }
                        else
                        {
                            c.getStringList("streamersToNotify").remove(args[2]);
                            plugin.reloadConfig();
                            s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                    prefix + "&7 The streamer "+args[2]+" removed from the list"));

                        }
                    }
                    else {
                        s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                prefix + "&7 The option "+args[1]+" does not exist"));
                    }
                }
            }
        }

        return false;
    }
}
