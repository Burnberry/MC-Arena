package noppe.minecraft.arena.entities.monsters;

import noppe.minecraft.arena.entities.Enmy;
import noppe.minecraft.arena.event.ArenaEventListener;
import org.bukkit.Location;

public class MS {
    public enum Type {
        ZOMBIE,
        SKELETON
    }

    public static Enmy spawnMonster(ArenaEventListener origin, Location location, Type type){
        if (type == Type.ZOMBIE){
            return new ArenaZombie(origin, location);
        } else if (type == Type.SKELETON) {
            return new ArenaSkeleton(origin, location);
        }
        return null;
    }
}
