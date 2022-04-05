package dk.bollsjen.warp;

import dk.bollsjen.warp.commands.*;
import dk.bollsjen.warp.events.JoinEvent;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Warp extends JavaPlugin {

    @Override
    public void onEnable(){
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Warp] Plugin enabled");
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        this.getCommand("warp").setExecutor(new WarpCommand());
        this.getCommand("setWarp").setExecutor(new SetWarpCommand());
        this.getCommand("removeWarp").setExecutor(new RemoveWarpCommand());
        this.getCommand("warpList").setExecutor(new WarpListCommand());
        this.getCommand("warpHelp").setExecutor(new WarpHelpCommand());
    }

    @Override
    public void onDisable(){
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Warp] Plugin disabled");
    }

}
