package noppe.minecraft.arena.event.mappers;

import noppe.minecraft.arena.mcarena.Arena;
import org.bukkit.event.Listener;

public class ArenaEventMapper implements Listener {
    Arena arena;

    public ArenaEventMapper(Arena arena){
        this.arena = arena;
    }
}
