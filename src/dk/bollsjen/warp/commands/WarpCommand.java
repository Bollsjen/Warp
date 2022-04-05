package dk.bollsjen.warp.commands;

import dk.bollsjen.warp.customdatatypes.Vector3;
import dk.bollsjen.warp.customdatatypes.WorldAndLocation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dk.bollsjen.warp.database.DatabaseHandler;

import java.security.Permission;

public class WarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            try{
                if(args.length == 0){
                    player.sendMessage("Warp name needed");
                    return false;
                }
                WorldAndLocation warp = DatabaseHandler.GetWarp(args[0]);
                Vector3 coords = warp.coords;
                World world = warp.world;
                if(world != null){
                    player.sendMessage("X " + coords.x + ", Y " + coords.y + ", Z " + coords.z);
                    player.teleport(new Location(world, coords.x,coords.y,coords.z));
                }else{
                    player.sendMessage("Ingen warp med dette navn "+ args[0] +" var fundet :(");
                    return false;
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }

        return true;
    }

}
