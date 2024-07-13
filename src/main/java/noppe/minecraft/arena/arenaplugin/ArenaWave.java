package noppe.minecraft.arena.arenaplugin;

import net.kyori.adventure.text.Component;

public class ArenaWave {
    ArenaGame arenaGame;
    int ticks;

    ArenaWave(ArenaGame arenaGame){
        this.arenaGame=arenaGame;
        this.ticks = 0;
    }

    public void onTick(){
        this.ticks += 1;
        if (this.ticks%100 == 0){
            this.broadcast("Spawning Enemy");
        }
    }

    public void broadcast(String message){
        this.arenaGame.broadcast(Component.text(message));
    }

    public void broadcast(Component message){
        this.arenaGame.broadcast(message);
    }
}
