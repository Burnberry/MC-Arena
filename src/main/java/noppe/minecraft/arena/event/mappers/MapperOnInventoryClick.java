package noppe.minecraft.arena.event.mappers;

import noppe.minecraft.arena.event.events.EventInventoryClick;
import noppe.minecraft.arena.mcarena.ArenaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MapperOnInventoryClick extends ArenaEventMapper implements Listener {
    public MapperOnInventoryClick(ArenaPlugin arenaPlugin) {
        super(arenaPlugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        this.arenaPlugin.arena.onInventoryClick(event, new EventInventoryClick(event));
    }
}
