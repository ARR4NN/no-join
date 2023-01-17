package com.arr4nn.nojoin;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.io.File;

public final class NoJoin extends Plugin implements Listener {
    private FileStorage config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        config = new FileStorage("config.yml", new File(getDataFolder().getPath()));
        config.saveDefaults(this);
        getProxy().getPluginManager().registerListener(this,this);
        getProxy().getInstance().getPluginManager().registerCommand(this, new ReloadCommand("nojoinreload", this));
        getLogger().info("Plugin started and is watching joins!");
    }

    @EventHandler
    public void onLogin(LoginEvent event) {
        if(config.read().getBoolean("kick")){
            event.getConnection().disconnect(TextComponent.fromLegacyText(config.read().getString("kick-message")));
            getLogger().info("User with uuid: "+event.getConnection().getUniqueId()+" tried to connect.");
            event.setCancelled(true);
        }
    }

    public FileStorage getConfig() {
        return config;
    }
}
