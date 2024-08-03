package noppe.minecraft.arena.event.mappers;

import noppe.minecraft.arena.entities.Ent;
import noppe.minecraft.arena.event.ArenaEventListener;
import noppe.minecraft.arena.event.events.EventEntityDamage;
import noppe.minecraft.arena.event.events.EventEntityDeath;
import noppe.minecraft.arena.helpers.M;
import noppe.minecraft.arena.mcarena.ArenaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class MapperOnEntityDamage extends ArenaEventMapper implements Listener {
    public MapperOnEntityDamage(ArenaPlugin arenaPlugin) {
        super(arenaPlugin);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){
        EventEntityDamage ev = new EventEntityDamage(event);
        Ent ent = M.getWrapper(event.getEntity());
        ArenaEventListener listener = M.getOrigin(event.getEntity());

        if (listener == null || ent == null){
            return;
        }

        listener.onEntityDamage(event, ev);
        ent.onEntityDamage(event, ev);
    }
}
