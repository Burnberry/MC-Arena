package noppe.minecraft.arena.mcarena.colosseum;

import noppe.minecraft.arena.builder.Bld;
import noppe.minecraft.arena.entities.Plyer;
import noppe.minecraft.arena.event.ArenaEventListener;
import noppe.minecraft.arena.event.events.EventPlayerInteract;
import noppe.minecraft.arena.helpers.M;
import noppe.minecraft.arena.item.Inv;
import noppe.minecraft.arena.item.Menu;
import noppe.minecraft.arena.location.Loc;
import noppe.minecraft.arena.mcarena.Arena;
import noppe.minecraft.arena.mcarena.Wave.Wave;
import noppe.minecraft.arena.mcarena.Wave.Wave1;
import noppe.minecraft.arena.mcarena.Wave.Wave2;
import noppe.minecraft.arena.mcarena.Wave.WaveBuilder;
import noppe.minecraft.arena.view.views.SoulShop;
import org.bukkit.GameMode;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Colosseum extends ArenaEventListener {
    public Arena arena;
    public List<Plyer> players = new ArrayList<>();
    int ticks = 0;
    int waves = 0;
    public Wave wave;
    BattleState battleState = BattleState.START;

    public Colosseum(Arena arena){
        this.arena=arena;
        Bld.colosseum(Loc.colosseum);
    }

    public void onRemove(){
        M.print("stopping game");
        if (this.wave != null) {
            this.wave.onRemove();
        }
        if (this.arena.colosseum == this){
            this.arena.colosseum = null;
        }
    }

    public void onTick(){
        this.ticks += 1;
        if (this.wave != null) {
            this.wave.onTick();
        }
//        for (Plyer plyer: this.players){
//            plyer.onTick();
//        }
    }

    public void onPlayerJoin(Plyer plyer){
        this.players.add(plyer);
        this.onPlayerTeleport(plyer);
        plyer.setRespawnLocation(Loc.colosseum);
        M.print("player joined colosseum");

        plyer.colosseum = this;
    }

    public void onPlayerTeleport(Plyer plyer){
        M.setOrigin(plyer.player, this);
        plyer.player.teleport(Loc.colosseum);
        M.setInventory(plyer, Inv.game);
        plyer.player.setGameMode(GameMode.ADVENTURE);
    }

    public void onPlayerLeave(Plyer plyer){
        this.players.remove(plyer);
    }

    public void startWave() {
        if (this.wave != null){
            return;
        }
        waves += 1;
        M.print("Starting Wave " + waves + "!");
        this.wave = WaveBuilder.getWave(this, waves);
    }

    public void onWaveEnd(){
        M.print("Wave " + waves + " Finished!");
//        for (Plyer plyer: this.arenaWave.players){
//            this.onPlayerTeleport(plyer);
//        }
        this.wave = null;
    }

    public void onPlayerInteract(PlayerInteractEvent event, EventPlayerInteract ev){
        if (!ev.rightClick){
            return;
        }

        if (M.matches(ev.item, Menu.removeWave) && this.wave != null && ev.plyer.useMenu()){
            this.wave.onRemove();
        }
        else if (M.matches(ev.item, Menu.stopGame) && ev.plyer.useMenu()){
            this.onRemove();
        }
        else if (M.matches(ev.item, Menu.startWave) && ev.plyer.useMenu()){
            this.startWave();
        }
        else if (M.matches(ev.item, Menu.soulShop) && ev.plyer.useMenu()){
            new SoulShop(ev.plyer);
        }
    }

    public boolean isArenaGame(){
        return true;
    }

}
