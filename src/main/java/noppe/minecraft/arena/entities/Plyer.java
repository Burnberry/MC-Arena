package noppe.minecraft.arena.entities;

import noppe.minecraft.arena.event.ArenaEventListener;
import noppe.minecraft.arena.helpers.M;
import org.bukkit.entity.Player;

public class Plyer extends Ent{
    public Player player;
    public int souls;

    public Plyer(ArenaEventListener origin, Player player) {
        super(origin, player);
        M.setOrigin(player, origin);
        this.type = "player";
        this.player = player;
        this.souls = 0;
    }

    public Boolean isPlayer(){
        return true;
    }

    public String getName(){
        return this.player.getName();
    }
}
