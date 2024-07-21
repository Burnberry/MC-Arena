package noppe.minecraft.arena.event;

import noppe.minecraft.arena.event.mappers.*;
import noppe.minecraft.arena.mcarena.ArenaPlugin;

import java.util.ArrayList;
import java.util.List;

public class ArenaEventHandler {
    ArenaPlugin arena;
    List<ArenaEventMapper> arenaEventMappers;

    public ArenaEventHandler(ArenaPlugin arena){
        this.arena = arena;
        this.arenaEventMappers = new ArrayList<>();

//        this.arenaEventMappers.add(new MapperOnTest(this.arena));
        this.arenaEventMappers.add(new DisableEvents(this.arena));

        // player events
        this.arenaEventMappers.add(new MapperOnPlayerJoin(this.arena));
        this.arenaEventMappers.add(new MapperOnPlayerInteract(this.arena));

        // inventory events
        this.arenaEventMappers.add(new MapperOnInventoryClick(this.arena));

        // entity events
        this.arenaEventMappers.add(new MapperOnEntityDeath(this.arena));
        this.arenaEventMappers.add(new MapperOnEntityRemove(this.arena));

        this.registerEvents();
    }

    public void registerEvents(){
        for (ArenaEventMapper mapper: this.arenaEventMappers){
            this.arena.getServer().getPluginManager().registerEvents(mapper, this.arena);
        }
    }
}
