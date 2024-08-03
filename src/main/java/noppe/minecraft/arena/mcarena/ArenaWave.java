package noppe.minecraft.arena.mcarena;

import noppe.minecraft.arena.builder.Bld;
import noppe.minecraft.arena.entities.Enmy;
import noppe.minecraft.arena.entities.Plyer;
import noppe.minecraft.arena.entities.monsters.ArenaSkeleton;
import noppe.minecraft.arena.entities.monsters.ArenaZombie;
import noppe.minecraft.arena.event.ArenaEventListener;
import noppe.minecraft.arena.event.events.EventEntityDeath;
import noppe.minecraft.arena.event.events.EventEntityRemove;
import noppe.minecraft.arena.helpers.M;
import noppe.minecraft.arena.location.Loc;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityRemoveEvent;

import java.util.ArrayList;
import java.util.List;

public class ArenaWave extends ArenaEventListener {
    Arena arena;
    ArenaGame arenaGame;

    int ticks;
    List<Plyer> players;
    List<Enmy> monsters;
    int rounds;
    int roundSize;

    ArenaWave(ArenaGame arenaGame){
        this.arenaGame = arenaGame;
        this.arena = arenaGame.arena;
        Bld.boxHome(Loc.waveArena, Material.GILDED_BLACKSTONE, Material.BLACK_STAINED_GLASS, Material.TINTED_GLASS, 8, 8, 3);


        this.ticks = 0;
        this.monsters = new ArrayList<>();
        this.players = this.arenaGame.players;
        for (Plyer plyer: this.players){
            plyer.player.teleport(Loc.waveArena);
//            M.setInventory(plyer, Inv.defaultArenaKit);
            plyer.player.setGameMode(GameMode.ADVENTURE);
        }
        this.rounds = 2;
        this.roundSize = 1;
    }

    public void onRemove(){
        for (int i = this.monsters.size()-1; i >= 0; i--) {
            this.monsters.get(i).remove();
        }
    }

    public void onTick(){
        this.ticks += 1;
        for (Enmy enmy: this.monsters){
            enmy.onTick();
        }
        if (this.monsters.isEmpty()){
            if (this.rounds > 0){
                this.startRound();
            }
            else {
                this.arenaGame.onWaveEnd();
            }
        }
    }

    private void startRound(){
        this.rounds -= 1;
        for (int i = 0; i < this.roundSize; i++){
            this.spawnMonster();
        }
    }

    private void spawnMonster(){
        Location location = this.getMonsterSpawnLocation();
        Enmy enmy = new ArenaSkeleton(this, location);
        this.monsters.add(enmy);
    }

    private Location getMonsterSpawnLocation(){
        return Loc.waveArena.clone().add(3, 0, 3);
    }

    @Override
    public void onEntityDeath(EntityDeathEvent event, EventEntityDeath ev) {
        if (ev.ent.isEnemy()){
            Enmy enemy = (Enmy) ev.ent;
            if (this.monsters.contains(enemy)){
                int souls = enemy.souls;
                this.arenaGame.collectSouls(souls);
                Plyer killer = ev.plyerKiller;
                if (killer == null) {
                    killer = this.getDefaultKiller();
                }
                if (killer != null){
                    killer.souls += souls;
                    M.print(killer.getName() + " killed " + enemy.enemy.getName() + " + " + souls +  " souls | total: " + killer.souls);
                }
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

    public Plyer getDefaultKiller(){
        if (!this.players.isEmpty()){
            return this.players.getFirst();
        }
        return null;
    }

    public boolean isArenaWave(){
        return true;
    }
}
