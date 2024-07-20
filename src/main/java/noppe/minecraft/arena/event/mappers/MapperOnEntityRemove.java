package noppe.minecraft.arena.event.mappers;

import noppe.minecraft.arena.entities.Ent;
import noppe.minecraft.arena.event.ArenaEventListener;
import noppe.minecraft.arena.event.events.EventEntityRemove;
import noppe.minecraft.arena.helpers.M;
import noppe.minecraft.arena.mcarena.ArenaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRemoveEvent;

public class MapperOnEntityRemove extends ArenaEventMapper implements Listener {
    public MapperOnEntityRemove(ArenaPlugin arena) {
        super(arena);
    }

    @EventHandler
    public void onEntityRemove(EntityRemoveEvent event){
        Ent ent = M.getWrapper(event.getEntity());
        ArenaEventListener listener = M.getOrigin(event.getEntity());

        if (listener == null || ent == null){
            return;
        }

        listener.onEntityRemove(event, new EventEntityRemove(event));
    }
}
