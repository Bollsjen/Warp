package dk.bollsjen.warp.commands;

import dk.bollsjen.warp.customdatatypes.Vector3;
import dk.bollsjen.warp.customdatatypes.WorldAndLocation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dk.bollsjen.warp.database.DatabaseHandler;

public class SetWarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;

            if (player.isOp()) {

                try {
                    Vector3 coords = new Vector3(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
                    if (args.length == 0) {
                        player.sendMessage("Behøver et warp navn");
                        return false;
                    }

                    String status = DatabaseHandler.SetWarp(new WorldAndLocation(player.getWorld(), coords), args[0]);
                    if (status == "SUCCESS") {
                        player.sendMessage("Warp " + args[0] + " er sat");
                    } else {
                        player.sendMessage(status);
                        return false;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }else{
                player.sendMessage("Du skal være OP for at bruge denne command");
            }
        }

        return true;
    }

}
