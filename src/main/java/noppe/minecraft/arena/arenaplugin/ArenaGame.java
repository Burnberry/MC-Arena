package noppe.minecraft.arena.arenaplugin;

import net.kyori.adventure.text.Component;

public class ArenaGame {
    Arena arena;
    int ticks;
    ArenaWave arenaWave;

    public ArenaGame(Arena arena){
        this.arena=arena;
        this.ticks = 0;
        this.arenaWave = new ArenaWave(this);

        this.broadcast("starting game");
    }

    public void onTick(){
        this.ticks += 1;
        this.arenaWave.onTick();
    }

    public void broadcast(String message){
        this.arena.getServer().broadcast(Component.text(message));
    }

    public void broadcast(Component message){
        this.arena.getServer().broadcast(message);
    }
}
