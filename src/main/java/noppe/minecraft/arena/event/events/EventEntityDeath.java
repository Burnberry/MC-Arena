package noppe.minecraft.arena.event.events;

import noppe.minecraft.arena.entities.Ent;
import noppe.minecraft.arena.entities.Plyer;
import noppe.minecraft.arena.helpers.M;
import org.bukkit.event.entity.EntityDeathEvent;

public class EventEntityDeath extends ArenaEvent{
    public Ent ent;
    public Plyer plyerKiller;

    public EventEntityDeath(EntityDeathEvent event) {
        super(event);
        this.ent = M.getWrapper(event.getEntity());
        this.plyerKiller = M.getPlayerFromDamageSource(ent, event);
    }

    public boolean killedByPlayer(){
        return this.plyerKiller != null;
    }
}
