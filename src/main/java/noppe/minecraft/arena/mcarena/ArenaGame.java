package noppe.minecraft.arena.mcarena;

import noppe.minecraft.arena.builder.Bld;
import noppe.minecraft.arena.entities.Plyer;
import noppe.minecraft.arena.event.ArenaEventListener;
import noppe.minecraft.arena.event.events.EventInventoryClick;
import noppe.minecraft.arena.event.events.EventPlayerInteract;
import noppe.minecraft.arena.helpers.M;
import noppe.minecraft.arena.item.Inv;
import noppe.minecraft.arena.item.InvView;
import noppe.minecraft.arena.item.Menu;
import noppe.minecraft.arena.item.Upgr;
import noppe.minecraft.arena.location.Loc;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
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
        Bld.boxHome(Loc.arenaLobby, Material.CHISELED_DEEPSLATE, Material.BLACK_STAINED_GLASS, Material.AIR, 5, 5, 3);


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
        plyer.setRespawnLocation(Loc.arenaLobby);
        M.print("player joined game");
    }

    public void onPlayerTeleport(Plyer plyer){
        M.setOrigin(plyer.player, this);
        plyer.player.teleport(Loc.arenaLobby);
        M.setInventory(plyer, Inv.game);
        plyer.player.setGameMode(GameMode.ADVENTURE);
    }

    public void onPlayerLeave(Plyer plyer){
        this.players.remove(plyer);
    }

    public void startWave(){
        if (this.arenaWave != null){
            return;
        }
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
        if (!ev.rightClick){
            return;
        }

        if (M.matches(ev.item, Menu.removeWave) && this.arenaWave != null && ev.plyer.useMenu()){
            this.arenaWave.onRemove();
        }
        else if (M.matches(ev.item, Menu.stopGame) && ev.plyer.useMenu()){
            this.onRemove();
        }
        else if (M.matches(ev.item, Menu.startWave) && ev.plyer.useMenu()){
            this.startWave();
        }
        else if (M.matches(ev.item, Menu.soulShop) && ev.plyer.useMenu()){
            ev.plyer.player.openInventory(InvView.getSoulShop(ev.plyer));
        }
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event, EventInventoryClick ev){
        if (ev.itemClicked == null){
            return;
        } else if (M.matches(ev.itemClicked, Upgr.getHealthIncrease(ev.plyer))) {
            ev.plyer.buyHealthIncrease();
        }
    }


    public void collectSouls(int souls){
        this.souls += souls;
    }

    public boolean isArenaGame(){
        return true;
    }
}
