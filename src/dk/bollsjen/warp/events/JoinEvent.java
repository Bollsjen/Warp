package dk.bollsjen.warp.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event){
        event.getPlayer().sendMessage("");
        event.getPlayer().sendMessage(ChatColor.YELLOW + "[Warp] /warpHelp for at få hjælp");
        event.getPlayer().sendMessage("");
    }

}
