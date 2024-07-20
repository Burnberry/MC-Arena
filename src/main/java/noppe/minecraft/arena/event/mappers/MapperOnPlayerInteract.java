package noppe.minecraft.arena.event.mappers;

import noppe.minecraft.arena.entities.Ent;
import noppe.minecraft.arena.event.ArenaEventListener;
import noppe.minecraft.arena.event.events.EventPlayerInteract;
import noppe.minecraft.arena.helpers.M;
import noppe.minecraft.arena.mcarena.Arena;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class MapperOnPlayerInteract extends ArenaEventMapper implements Listener {
    public MapperOnPlayerInteract(Arena arena) {
        super(arena);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Ent ent = M.getWrapper(event.getPlayer());
        ArenaEventListener listener = M.getOrigin(event.getPlayer());
        M.print(M.getLore((new EventPlayerInteract(event)).item));
        if (listener == null || ent == null){
            return;
        }

        listener.onPlayerInteract(event, new EventPlayerInteract(event));
    }
}
