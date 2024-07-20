package noppe.minecraft.arena.event.mappers;

import noppe.minecraft.arena.event.events.EventPlayerJoin;
import noppe.minecraft.arena.mcarena.ArenaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class MapperOnPlayerJoin extends ArenaEventMapper implements Listener {
    public MapperOnPlayerJoin(ArenaPlugin arena) {
        super(arena);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        this.arenaPlugin.arena.onPlayerJoin(event, new EventPlayerJoin(event));
    }
}
