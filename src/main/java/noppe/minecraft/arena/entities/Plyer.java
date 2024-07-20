package noppe.minecraft.arena.entities;

import noppe.minecraft.arena.event.ArenaEventListener;
import org.bukkit.entity.Player;

public class Plyer extends Ent{
    public Player player;

    Plyer(ArenaEventListener origin, Player player) {
        super(origin, player);
        this.type = "player";
        this.player = player;
    }

    public Boolean isPlayer(){
        return true;
    }
}
