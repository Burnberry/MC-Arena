package noppe.minecraft.arena.event.mappers;

import noppe.minecraft.arena.mcarena.ArenaPlugin;
import org.bukkit.event.Listener;

public class ArenaEventMapper implements Listener {
    ArenaPlugin arenaPlugin;

    public ArenaEventMapper(ArenaPlugin arenaPlugin){
        this.arenaPlugin = arenaPlugin;
    }
}
