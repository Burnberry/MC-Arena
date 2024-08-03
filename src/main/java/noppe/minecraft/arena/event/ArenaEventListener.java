package noppe.minecraft.arena.event;

import noppe.minecraft.arena.event.events.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityRemoveEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ArenaEventListener {
    public void onEntityDeath(EntityDeathEvent event, EventEntityDeath ev){}
    public void onEntityRemove(EntityRemoveEvent event, EventEntityRemove ev){}
    public void onEntityDamage(EntityDamageEvent event, EventEntityDamage ev){}
    public void onPlayerInteract(PlayerInteractEvent event, EventPlayerInteract ev){}
    public void onInventoryClick(InventoryClickEvent event, EventInventoryClick ev){}

    public boolean isArena(){
        return false;
    }

    public boolean isArenaGame(){
        return false;
    }

    public boolean isArenaWave(){
        return false;
    }
}
