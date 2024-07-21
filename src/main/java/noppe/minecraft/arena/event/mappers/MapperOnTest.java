package noppe.minecraft.arena.event.mappers;

import noppe.minecraft.arena.event.events.EventTest;
import noppe.minecraft.arena.mcarena.ArenaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MapperOnTest extends ArenaEventMapper {
    public MapperOnTest(ArenaPlugin arenaPlugin) {
        super(arenaPlugin);
    }

    @EventHandler
    public void onTest(InventoryClickEvent event){
        EventTest ev = new EventTest(event);
    }
}
