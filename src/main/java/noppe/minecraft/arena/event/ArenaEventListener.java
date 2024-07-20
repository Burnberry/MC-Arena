package noppe.minecraft.arena.event;

import noppe.minecraft.arena.event.events.EventEntityDeath;
import noppe.minecraft.arena.event.events.EventEntityRemove;
import noppe.minecraft.arena.event.events.EventPlayerInteract;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityRemoveEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ArenaEventListener {
    public void onEntityDeath(EntityDeathEvent event, EventEntityDeath ev){}
    public void onEntityRemove(EntityRemoveEvent event, EventEntityRemove ev){}
    public void onPlayerInteract(PlayerInteractEvent event, EventPlayerInteract ev){}

}
