package dk.bollsjen.warp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpHelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            player.sendMessage("");
            player.sendMessage(ChatColor.YELLOW + "-------Warp Hjælp-------");
            player.sendMessage("");

            player.sendMessage(ChatColor.YELLOW + "/warpHelp for hjælp med warp commands");
            player.sendMessage(ChatColor.YELLOW + "/warpList for at liste all warp destinationer");
            player.sendMessage(ChatColor.YELLOW + "/warp <name> for warp/teleporterer til destination");
            player.sendMessage(ChatColor.YELLOW + "/setWarp <name> hvis du har tilladelse kan du sætte warp destination ved din position med navn");
            player.sendMessage("");
            player.sendMessage(ChatColor.YELLOW + "/removeWarp <name> hvis du har tilladelse kan du slette warp destinationer med navn");
        }
        return true;
    }
}
