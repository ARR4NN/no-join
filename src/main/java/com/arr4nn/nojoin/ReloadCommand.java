package com.arr4nn.nojoin;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class ReloadCommand extends Command {

    NoJoin plugin;

    public ReloadCommand(String name, NoJoin plugin) {
        super("nojoinreload");
        this.plugin = plugin;
    }

    public void execute(CommandSender sender, String[] args) {
        plugin.getConfig().reload(plugin);
        sender.sendMessage("Reloading plugin!");
    }
}
