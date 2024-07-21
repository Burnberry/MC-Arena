package noppe.minecraft.arena.event.events;

import noppe.minecraft.arena.helpers.M;
import org.bukkit.event.inventory.InventoryClickEvent;

public class EventTest extends ArenaEvent{
    public EventTest(InventoryClickEvent event) {
        super(event);
        M.print(event.getViewers().toString());
        M.print(event.getInventory().toString());
        event.setCancelled(true);
    }
}
