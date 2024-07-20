package noppe.minecraft.arena.entities;

import noppe.minecraft.arena.event.ArenaEventListener;
import org.bukkit.entity.Enemy;

public class Enmy extends Ent{
    public Enemy enemy;
    public int souls;

    public Enmy(ArenaEventListener origin, Enemy enemy) {
        super(origin, enemy);
        this.type = "enemy";
        this.enemy = enemy;
    }

    public Boolean isEnemy(){
        return true;
    }
}
