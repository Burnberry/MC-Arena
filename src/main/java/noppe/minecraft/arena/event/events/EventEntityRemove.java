package noppe.minecraft.arena.event.events;

import noppe.minecraft.arena.entities.Ent;
import noppe.minecraft.arena.helpers.M;
import org.bukkit.event.entity.EntityRemoveEvent;

public class EventEntityRemove extends ArenaEvent{
    public Ent ent;

    public EventEntityRemove(EntityRemoveEvent event) {
        super(event);
        this.ent = M.getWrapper(event.getEntity());
    }
}
