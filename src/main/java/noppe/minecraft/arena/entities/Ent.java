package noppe.minecraft.arena.entities;

import noppe.minecraft.arena.event.ArenaEventListener;
import noppe.minecraft.arena.helpers.M;
import org.bukkit.entity.Entity;

public class Ent {
    public String type;
    public Entity entity;
    public ArenaEventListener origin;

    Ent(ArenaEventListener origin, Entity entity){
        this.origin = origin;
        this.entity = entity;
        M.setMetaData(entity, "wrapper", this);
    }

    public void remove(){
        this.entity.remove();
    }

    public Boolean isEnemy(){
        return false;
    }

    public Boolean isPlayer(){
        return false;
    }
}
