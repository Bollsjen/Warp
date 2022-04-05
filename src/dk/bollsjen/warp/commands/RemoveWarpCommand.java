package dk.bollsjen.warp.commands;

import dk.bollsjen.warp.database.DatabaseHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveWarpCommand  implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if(player.isOp()) {

                try {
                    if (args.length > 0) {
                        String status = DatabaseHandler.RemoveWarp(args[0]);
                        if (status == "SUCCESS") {
                            player.sendMessage("Warp " + args[0] + " blev slette med succes");
                        } else {
                            System.out.println(status);
                        }
                    } else {
                        return false;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    return false;
                }
            }else{
                player.sendMessage("Du skal være OP for at bruge denne command");
            }
        }
        return true;
    }
}
