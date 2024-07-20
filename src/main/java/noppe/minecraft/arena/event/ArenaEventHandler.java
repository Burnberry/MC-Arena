package noppe.minecraft.arena.event;

import noppe.minecraft.arena.event.mappers.ArenaEventMapper;
import noppe.minecraft.arena.event.mappers.MapperOnEntityDeath;
import noppe.minecraft.arena.event.mappers.MapperOnEntityRemove;
import noppe.minecraft.arena.event.mappers.MapperOnPlayerInteract;
import noppe.minecraft.arena.mcarena.Arena;

import java.util.ArrayList;
import java.util.List;

public class ArenaEventHandler {
    Arena arena;
    List<ArenaEventMapper> arenaEventMappers;

    public ArenaEventHandler(Arena arena){
        this.arena = arena;
        this.arenaEventMappers = new ArrayList<>();

        this.arenaEventMappers.add(new MapperOnEntityDeath(this.arena));
        this.arenaEventMappers.add(new MapperOnEntityRemove(this.arena));
        this.arenaEventMappers.add(new MapperOnPlayerInteract(this.arena));
    }

    public void registerEvents(){
        this.arena.print("Registering events");
        for (ArenaEventMapper mapper: this.arenaEventMappers){
            this.arena.print("Registering custom event");
            this.arena.getServer().getPluginManager().registerEvents(mapper, this.arena);
        }
    }
}
