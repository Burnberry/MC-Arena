package noppe.minecraft.arena.event.mappers;

import noppe.minecraft.arena.mcarena.ArenaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityAirChangeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class DisableEvents extends ArenaEventMapper implements Listener {
    public DisableEvents(ArenaPlugin arenaPlugin) {
        super(arenaPlugin);
    }

    @EventHandler
    public void disableFood(FoodLevelChangeEvent event){
        event.setCancelled(true);
    }

//    @EventHandler
//    public void disableAir(EntityAirChangeEvent event){
//        event.setCancelled(true);
//    }
}
