package com.github.wolfdev1.streamnotifier.events;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.events.ChannelGoLiveEvent;
import com.github.twitch4j.events.ChannelGoOfflineEvent;
import com.github.wolfdev1.streamnotifier.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;

public class GoOffline {

    Plugin plugin;

    public GoOffline(SimpleEventHandler eventHandler, Plugin plugin)
    {
        eventHandler.onEvent(ChannelGoOfflineEvent.class, this::onChannelGoOffline);
        this.plugin = plugin;
    }

    public void onChannelGoOffline(ChannelGoOfflineEvent event)
    {

        Configuration config = plugin.getConfig();

        if(config.getBoolean("events.end.notify"))
        {

            String msg = config.getString("events.end.message")
                    .replace("{prefix}", config.getString("prefix"))
                    .replace("{streamer}", event.getChannel().getName());

            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', msg));
        }
    }

}
