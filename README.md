# Stream Notifier
A simple tool to notify when a twitch streamer go live.
#
**⚔️ How it works**

Simply add a Twitch OAuth2 Token (which you can get [here](https://dev.twitch.tv/console)) in the config.yml in the `twitchOAuth2Token` section

- Optionally you can modify the following sections according to your objective

```yaml
twitchOAuth2Token: "YOUR OAUTH2 TOKEN HERE!"
prefix: '&x&9&3&2&c&f&b&l&oS&x&a&0&2&a&f&b&l&ot&x&a&c&2&9&f&c&l&or&x&b&9&2&7&f&c&l&oe&x&c&5&2&6&f&d&l&oa&x&d&2&2&4&f&d&l&om&r&8 »'
streamersToNotify:
  - "TwitchChannelNameHere"
  - "OtherChannelNameHere"
events:
  start:
    notify: true
    message: '{prefix} &6{streamer} &7has started streaming live on twitch!'
  end:
    notify: true
    message: '{prefix} &6{streamer} &7has stopped broadcasting on Twitch.'

```