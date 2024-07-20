package noppe.minecraft.arena.mcarena;

import noppe.minecraft.arena.event.ArenaEventListener;
import noppe.minecraft.arena.event.events.EventEntityDeath;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ArenaGame extends ArenaEventListener {
    Arena arena;
    Location arenaLobby;
    Location arenaLocation;
    List<Player> players;
    ItemStack startWaveItem;
    int ticks;
    ArenaWave arenaWave;
    int souls;

    public ArenaGame(Arena arena){
        this.arena=arena;

        this.arenaLocation = new Location(this.arena.getServer().getWorld("world"), 0.5, 95, 20.5);
        this.startWaveItem = new ItemStack(Material.FEATHER);
        this.arena.setItemName(this.startWaveItem, "Start Wave");

        this.players = new ArrayList<>();
        this.ticks = 0;
        this.souls = 0;

        this.print("starting game");
    }

    public void onRemove(){
        this.print("stopping game");
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

    public void onPlayerJoin(Player player){
        this.players.add(player);
        this.onPlayerTeleport(player);
        print("player joined game");
    }

    public void onPlayerTeleport(Player player){
        player.teleport(this.arenaLocation);
        player.getInventory().clear();
        player.getInventory().setItem(5, this.startWaveItem);
        player.setGameMode(GameMode.ADVENTURE);
    }

    public void onPlayerLeave(Player player){
        this.players.remove(player);
    }

    public void startWave(){
        print("starting wave");
        this.arenaWave = new ArenaWave(this);
    }

    public void onWaveEnd(){
        this.print("ending wave");
        for (Player player: this.arenaWave.players){
            this.onPlayerTeleport(player);
        }
        this.arenaWave = null;
    }

    public void onMenuPress(PlayerInteractEvent event){
        if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.FIRE_CHARGE) && this.arenaWave != null){
            this.arenaWave.onRemove();
        }
        else if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.RED_CANDLE)) {
            this.onRemove();
        }
        else if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.FEATHER)) {
            this.startWave();
        }
    }

    public void collectSouls(int souls){
        this.souls += souls;
    }

    public void print(String message){
        this.arena.print(message);
    }
}
