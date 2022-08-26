package com.github.wolfdev1.streamnotifier.events;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.events.ChannelGoLiveEvent;
import com.github.wolfdev1.streamnotifier.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;

import java.awt.*;

public class GoLive {

    Plugin plugin;

    public GoLive(SimpleEventHandler eventHandler, Plugin plugin)
    {
        eventHandler.onEvent(ChannelGoLiveEvent.class, this::onChannelGoLive);
        this.plugin = plugin;
    }

    public void onChannelGoLive(ChannelGoLiveEvent event)
    {

        Configuration config = plugin.getConfig();

        if(config.getBoolean("events.start.notify"))
        {

            String msg = config.getString("events.start.message")
                    .replace("{prefix}", config.getString("prefix"))
                    .replace("{streamer}", event.getChannel().getName());

            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', msg));
        }
    }

}
