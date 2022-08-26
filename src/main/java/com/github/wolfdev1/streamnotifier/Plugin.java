package com.github.wolfdev1.streamnotifier;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.wolfdev1.streamnotifier.commands.StreamNotifierCMD;
import com.github.wolfdev1.streamnotifier.events.GoLive;
import com.github.wolfdev1.streamnotifier.events.GoOffline;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin extends JavaPlugin {

    @Override
    public void onEnable()
    {

        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Configuration c = getConfig();

        getCommand("streamnotifier").setExecutor(new StreamNotifierCMD(this));

        try
        {

            TwitchClient twitchClient = TwitchClientBuilder.builder()
                    .withDefaultAuthToken(new OAuth2Credential("twitch", c.getString("twitchOAuth2Token")))
                    .withEnableHelix(true)
                    .build();

            SimpleEventHandler eventHandler = twitchClient.getEventManager().getEventHandler(SimpleEventHandler.class);
            new GoLive(eventHandler, this);
            new GoOffline(eventHandler, this);

            this.getLogger().info("Twitch module loaded correctly.");
            this.getLogger().info("Verifying that the list of Streamers is valid . . .");


            for (int i=0; i < c.getStringList("streamersToNotify").size(); i++)
            {
                String s = c.getStringList("streamersToNotify").get(i).toString();
                twitchClient.getClientHelper().enableStreamEventListener(s);
                getLogger().info("Listening " + s + " Twitch Channel ");
            }

        }
        catch (Exception ex)
        {
            this.getLogger().info("Twitch module did not load correctly, make sure you have set a valid token in your config.yml file.");
            this.getLogger().info("Shutting down . . .");
        }
    }

    @Override
    public void onDisable()
    {
        this.getLogger().info("StreamNotifier has been disabled");
    }
}
