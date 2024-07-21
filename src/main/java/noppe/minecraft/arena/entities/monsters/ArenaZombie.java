package noppe.minecraft.arena.entities.monsters;

import noppe.minecraft.arena.entities.Enmy;
import noppe.minecraft.arena.event.ArenaEventListener;
import noppe.minecraft.arena.helpers.M;
import org.bukkit.Location;
import org.bukkit.entity.Zombie;

public class ArenaZombie extends Enmy {
    public ArenaZombie(ArenaEventListener origin, Location location){
        super(origin, (Zombie) M.spawnEntity(origin, location, Zombie.class));
        this.souls = 1;
    }
}
