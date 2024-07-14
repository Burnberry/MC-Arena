package noppe.minecraft.arena.arenaplugin;

import com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;

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

    public void onRemove(){
        this.broadcast("stopping game");
        if (this.arenaWave != null) {
            this.arenaWave.onRemove();
        }
        if (this.arena.arenaGame == this){
            this.arena.arenaGame = null;
        }
    }

    public void onTick(){
        this.ticks += 1;
        this.arenaWave.onTick();
    }

    public void onMenuPress(PlayerInteractEvent event){
        if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.FIRE_CHARGE) && this.arenaWave != null){
            this.arenaWave.onRemove();
        } else if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.RED_CANDLE)) {
            this.onRemove();
        }
    }

    public void onEntityDeath(EntityDeathEvent event){
        if (this.arenaWave != null) {
            this.arenaWave.onMonsterDeath(event);
        }
    }

    public void onEntityRemoved(EntityRemoveFromWorldEvent event){
        if (this.arenaWave != null) {
            this.arenaWave.onMonsterRemoved(event);
        }
    }

    public void broadcast(String message){
        this.arena.getServer().broadcast(Component.text(message));
    }

    public void broadcast(Component message){
        this.arena.getServer().broadcast(message);
    }
}
