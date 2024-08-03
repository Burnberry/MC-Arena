package noppe.minecraft.arena.event.events;

import noppe.minecraft.arena.entities.Ent;
import noppe.minecraft.arena.entities.Plyer;
import noppe.minecraft.arena.helpers.M;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageEvent;

public class EventEntityDamage extends ArenaEvent {
    public Ent ent;
    public Plyer plyerCause;

    public EventEntityDamage(EntityDamageEvent event) {
        super(event);
        this.ent = M.getWrapper(event.getEntity());
        if (event.getDamageSource().getCausingEntity() != null){
            this.plyerCause = (Plyer)M.getWrapper(event.getDamageSource().getCausingEntity());
        }
    }

    public boolean hasPlayerCause(){
        return this.plyerCause != null;
    }
}
