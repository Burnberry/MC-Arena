package noppe.minecraft.arena.arenaplugin;

import com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.*;

public class ArenaWave {
    Arena arena;
    ArenaGame arenaGame;
    int ticks;
    List<Entity> monsters;

    ArenaWave(ArenaGame arenaGame){
        this.arenaGame=arenaGame;
        this.arena = arenaGame.arena;
        this.ticks = 0;
        this.monsters = new ArrayList<>();
    }

    public void onRemove(){
        this.broadcast("removing wave");
        for (int i = this.monsters.size()-1; i >= 0; i--) {
            this.monsters.get(i).remove();
        }
    }

    public void onTick(){
        this.ticks += 1;
        if (this.ticks%100 == 20){
            this.broadcast("Spawning Enemy");
            this.spawnMonster();
        }
    }

    public void onMonsterDeath(EntityDeathEvent event){
        Entity monster = event.getEntity();
        if (this.monsters.contains(monster)){
            this.broadcast(monster.getName() + " killed + 1 gem");
        }
    }

    public void onMonsterRemoved(EntityRemoveFromWorldEvent event){
        Entity monster = event.getEntity();
        this.removeMonster(monster);
    }

    private void removeMonster(Entity monster){
        if (this.monsters.contains(monster)){
            this.monsters.remove(monster);
            this.broadcast(monster.getName() + " removed");
        }
    }

    public void spawnMonster(){
        Location location = this.getMonsterSpawnLocation();
        Zombie zombie = (Zombie) location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
        this.monsters.add(zombie);
    }

    public Location getMonsterSpawnLocation(){
        return this.arena.arenaLocation.clone().add(3, 0, 3);
    }

    public void broadcast(String message){
        this.arenaGame.broadcast(Component.text(message));
    }

    public void broadcast(Component message){
        this.arenaGame.broadcast(message);
    }
}
