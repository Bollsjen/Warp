package dk.bollsjen.warp.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dk.bollsjen.warp.customdatatypes.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;

public class DatabaseHandler {
    private static Connection Connection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3307/minecraftstuff", "root", "Nuster13");
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static WorldAndLocation GetWarp(String warp){
        Vector3 coords = null;
        World world = null;
        WorldAndLocation location = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = Connection().createStatement();
        }catch (Exception e) {
            System.out.println(ChatColor.RED + "STMT ERROR: " + e);
            return null;
        }

        try {
            rs = stmt.executeQuery("SELECT * FROM warp_destinations WHERE warp_name = '" + warp + "'");
        }catch (Exception e) {
            System.out.println(ChatColor.RED + "RS ERROR: " + e);
            return null;
        }
        try {
            coords = new Vector3();
            System.out.println("Getting coords");
            int rows = 0;
                while (rs.next()) {
                    rows ++;
                    coords.x = rs.getInt(2);
                    coords.y = rs.getInt(3);
                    coords.z = rs.getInt(4);
                    world = Bukkit.getWorld(rs.getString(6));
                }
                if(rows == 0)
                    location = null;
                else
                    location = new WorldAndLocation(world,coords);
        }catch (Exception e) {
            System.out.println(ChatColor.RED + "RESULT ERROR: " + e);
            return null;
        }
        try {
            Connection().close();
            return new WorldAndLocation(world, coords);
        }catch (Exception e){
            System.out.println(ChatColor.RED + "CLOSING ERROR: " + e);
            return null;
        }
    }

    public  static List<String> GetWarps(){
        Statement stmt = null;
        ResultSet rs = null;
        List<String> list = new ArrayList<String>();
        try {
            stmt = Connection().createStatement();
        }catch (Exception e) {
            System.out.println(ChatColor.RED + "STMT ERROR: " + e);
            return null;
        }

        try {
            rs = stmt.executeQuery("SELECT * FROM warp_destinations");
        }catch (Exception e) {
            System.out.println(ChatColor.RED + "RS ERROR: " + e);
            return null;
        }

        try {
            System.out.println("Getting coords");
            int rows = 0;
            while (rs.next()) {
                list.add(rs.getString(5));
            }
        }catch (Exception e) {
            System.out.println(ChatColor.RED + "RESULT ERROR: " + e);
            return null;
        }

        try {
            Connection().close();
            return list;
        }catch (Exception e){
            System.out.println(ChatColor.RED + "CLOSING ERROR: " + e);
            return null;
        }
    }

    public static String SetWarp(WorldAndLocation location, String warp_name){
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = Connection().createStatement();
        }catch (Exception e) {
            System.out.println(ChatColor.RED + "STMT ERROR: " + e);
            return "STMT ERROR: " + e;
        }

        try {
            WorldAndLocation warp = GetWarp(warp_name);
            if(warp.world == null) {
                int effectedRows = stmt.executeUpdate("INSERT INTO warp_destinations (x,y,z,warp_name, world) VALUES (" + location.coords.x + "," + location.coords.y + "," + location.coords.z + ", '" + warp_name + "', '"+ location.world.getName() +"')");
                System.out.println(effectedRows + " effected rows");
            }else{
                return "NAME ERROR: Navn findes allerede";
            }
        }catch (Exception e) {
            System.out.println(ChatColor.RED + "RS ERROR: " + e);
            return "INSERT ERROR: " + e;
        }

        return "SUCCESS";
    }

    public static String RemoveWarp(String warp_name){
        Statement stmt = null;
        try {
            stmt = Connection().createStatement();
        }catch (Exception e) {
            System.out.println(e);
            return "STMT ERROR: " + e;
        }
        try{
            int effectedRows = stmt.executeUpdate("DELETE FROM warp_destinations WHERE warp_name = '"+ warp_name +"'");
            System.out.println(effectedRows + " effected rows");
        }catch (Exception e){
            System.out.println(e);
            return "DELETE ERROR: " + e;
        }
        return "SUCCESS";
    }

    public static ResultSet GetData(){
        try{
            Statement stmt = Connection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM permissions");

            /*while(rs.next()){
                System.out.println(rs.getInt(1) + " " + rs.getString(2));
            }*/
            Connection().close();
            return rs;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static void DeleteData(){
        try{
            Statement stmt = Connection().createStatement();

            int effectedRows = stmt.executeUpdate("DELETE FROM permissions WHERE ID = 4");
            System.out.println(effectedRows + " effected rows");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void CreateData(){
        try{
            Statement stmt = Connection().createStatement();

            int effectedRows = stmt.executeUpdate("INSERT INTO permissions (perm_name) VALUES ('delete_warp'), ('create_warp')");
            System.out.println(effectedRows + " effected rows");
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
