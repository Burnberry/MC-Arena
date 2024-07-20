package noppe.minecraft.arena.location;

import noppe.minecraft.arena.mcarena.Arena;
import org.bukkit.Location;
import org.bukkit.World;

public class Loc {
    static Arena arena;
    static World world;

    public static Location spawn;
    public static Location arenaLobby;
    public static Location waveArena;


    public static void setArena(Arena arena){
        Loc.arena = arena;
        Loc.world = arena.arenaPlugin.getServer().getWorld("world");

        Loc.spawn = new Location(Loc.world, 0.5, 100, 0.5);
        Loc.arenaLobby = new Location(Loc.world, 0.5, 95, 20.5);
        Loc.waveArena = new Location(Loc.world, 20.5, 100, 0.5);
    }
}
