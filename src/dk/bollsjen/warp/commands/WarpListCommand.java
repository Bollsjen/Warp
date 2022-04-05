package dk.bollsjen.warp.commands;

import dk.bollsjen.warp.database.DatabaseHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WarpListCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            
            List<String> list = DatabaseHandler.GetWarps();
            player.sendMessage("");
            player.sendMessage(ChatColor.YELLOW + "-------Warp Liste-------");
            player.sendMessage("");
            for (String name: list) {
                player.sendMessage(ChatColor.YELLOW + "/warp " + name);
            }
        }
        return true;
    }
}
