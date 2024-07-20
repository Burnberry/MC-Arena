package noppe.minecraft.arena.event.mappers;

import noppe.minecraft.arena.entities.Ent;
import noppe.minecraft.arena.event.ArenaEventListener;
import noppe.minecraft.arena.event.events.EventEntityDeath;
import noppe.minecraft.arena.helpers.M;
import noppe.minecraft.arena.mcarena.Arena;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class MapperOnEntityDeath extends ArenaEventMapper implements Listener {

    public MapperOnEntityDeath(Arena arena) {
        super(arena);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
        Ent ent = M.getWrapper(event.getEntity());
        ArenaEventListener listener = M.getOrigin(event.getEntity());

        if (listener == null || ent == null){
            return;
        }

        listener.onEntityDeath(event, new EventEntityDeath(event));
    }
}

