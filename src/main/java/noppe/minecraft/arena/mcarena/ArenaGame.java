package noppe.minecraft.arena.mcarena;

import noppe.minecraft.arena.entities.Plyer;
import noppe.minecraft.arena.event.ArenaEventListener;
import noppe.minecraft.arena.event.events.EventPlayerInteract;
import noppe.minecraft.arena.helpers.M;
import noppe.minecraft.arena.item.Menu;
import noppe.minecraft.arena.location.Loc;
import org.bukkit.GameMode;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class ArenaGame extends ArenaEventListener {
    Arena arena;
    List<Plyer> players;
    int ticks;
    ArenaWave arenaWave;
    int souls;

    public ArenaGame(Arena arena){
        this.arena=arena;

        this.players = new ArrayList<>();
        this.ticks = 0;
        this.souls = 0;

        M.print("starting game");
    }

    public void onRemove(){
        M.print("stopping game");
        if (this.arenaWave != null) {
            this.arenaWave.onRemove();
        }
        if (this.arena.arenaGame == this){
            this.arena.arenaGame = null;
        }
    }

    public void onTick(){
        this.ticks += 1;
        if (this.arenaWave != null) {
            this.arenaWave.onTick();
        }
    }

    public void onPlayerJoin(Plyer plyer){
        this.players.add(plyer);
        this.onPlayerTeleport(plyer);
        M.print("player joined game");
    }

    public void onPlayerTeleport(Plyer plyer){
        plyer.player.teleport(Loc.arenaLobby);
        plyer.player.getInventory().clear();
        plyer.player.getInventory().setItem(5, Menu.startWave);
        plyer.player.setGameMode(GameMode.ADVENTURE);
    }

    public void onPlayerLeave(Plyer plyer){
        this.players.remove(plyer);
    }

    public void startWave(){
        M.print("starting wave");
        this.arenaWave = new ArenaWave(this);
    }

    public void onWaveEnd(){
        M.print("ending wave");
        for (Plyer plyer: this.arenaWave.players){
            this.onPlayerTeleport(plyer);
        }
        this.arenaWave = null;
    }

    public void onPlayerInteract(PlayerInteractEvent event, EventPlayerInteract ev){
        if (M.matches(ev.item, Menu.removeWave) && this.arenaWave != null){
            this.arenaWave.onRemove();
        }
        else if (M.matches(ev.item, Menu.stopGame)){
            this.onRemove();
        }
        else if (M.matches(ev.item, Menu.startWave)){
            this.startWave();
        }
    }

    public void collectSouls(int souls){
        this.souls += souls;
    }

    public boolean isArenaGame(){
        return true;
    }
}
