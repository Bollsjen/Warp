package dk.bollsjen.warp.customdatatypes;

import org.bukkit.World;

public class WorldAndLocation {
    public World world;
    public Vector3 coords;

    public WorldAndLocation(){}

    public WorldAndLocation(World world, Vector3 coords){
        this.world = world;
        this.coords = coords;
    }
}
