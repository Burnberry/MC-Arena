package noppe.minecraft.arena.mcarena;

import noppe.minecraft.arena.entities.Enmy;
import noppe.minecraft.arena.entities.monsters.ArenaZombie;
import noppe.minecraft.arena.event.ArenaEventListener;
import noppe.minecraft.arena.event.events.EventEntityDeath;
import noppe.minecraft.arena.event.events.EventEntityRemove;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityRemoveEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ArenaWave extends ArenaEventListener {
    Arena arena;
    ArenaGame arenaGame;

    Location waveLocation;
    ItemStack weaponItem;
    int ticks;
    List<Player> players;
    List<Enmy> monsters;
    int rounds;
    int roundSize;

    ArenaWave(ArenaGame arenaGame){
        this.arenaGame=arenaGame;
        this.arena = arenaGame.arena;

        this.waveLocation = new Location(this.arena.getServer().getWorld("world"), 20.5, 100, 0.5);
        this.weaponItem = new ItemStack(Material.DIAMOND_SWORD);
        this.arena.setItemName(this.weaponItem, "Knife");

        this.ticks = 0;
        this.monsters = new ArrayList<>();
        this.players = this.arenaGame.players;
        for (Player player: this.players){
            player.teleport(this.waveLocation);
            player.getInventory().clear();
            player.getInventory().setItem(0, this.weaponItem);
            player.setGameMode(GameMode.ADVENTURE);
        }
        this.rounds = 2;
        this.roundSize = 3;
    }

    public void onRemove(){
        for (int i = this.monsters.size()-1; i >= 0; i--) {
            this.monsters.get(i).remove();
        }
    }

    public void onTick(){
        this.ticks += 1;
        if (this.monsters.isEmpty()){
            if (this.rounds > 0){
                this.startRound();
            }
            else {
                this.arenaGame.onWaveEnd();
            }
        }
//        if (this.ticks%100 == 20){
//            this.print("Spawning Enemy");
//            this.spawnMonster();
//        }
    }


    public void onMonsterRemoved(EntityRemoveEvent event){
        Entity monster = event.getEntity();
        this.removeMonster(monster);
    }

    private void removeMonster(Entity monster){
        this.monsters.remove((Enmy) monster);
    }

    private void startRound(){
        this.rounds -= 1;
        for (int i = 0; i < this.roundSize; i++){
            this.spawnMonster();
        }
    }

    private void spawnMonster(){
        Location location = this.getMonsterSpawnLocation();
        ArenaZombie zombie = new ArenaZombie(this, location);
        this.monsters.add(zombie);
    }

    private Location getMonsterSpawnLocation(){
        return this.waveLocation.clone().add(3, 0, 3);
    }

    public void print(String message){
        this.arenaGame.print(message);
    }

    @Override
    public void onEntityDeath(EntityDeathEvent event, EventEntityDeath ev) {
        if (ev.ent.isEnemy()){
            Enmy enemy = (Enmy) ev.ent;
            if (this.monsters.contains(enemy)){
                int souls = enemy.souls;
                this.arenaGame.collectSouls(souls);
                this.print(" killed + " + souls +  " souls | total: " + this.arenaGame.souls);
            }
        }
    }

    @Override
    public void onEntityRemove(EntityRemoveEvent event, EventEntityRemove ev){
        if (ev.ent.isEnemy()){
            Enmy enmy = (Enmy) ev.ent;
            this.monsters.remove(enmy);
        }
    }
}
