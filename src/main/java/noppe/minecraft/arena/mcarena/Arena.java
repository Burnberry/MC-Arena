package noppe.minecraft.arena.mcarena;

import noppe.minecraft.arena.builder.Bld;
import noppe.minecraft.arena.entities.Plyer;
import noppe.minecraft.arena.event.ArenaEventListener;
import noppe.minecraft.arena.event.events.EventInventoryClick;
import noppe.minecraft.arena.event.events.EventPlayerInteract;
import noppe.minecraft.arena.event.events.EventPlayerJoin;
import noppe.minecraft.arena.helpers.M;
import noppe.minecraft.arena.item.Inv;
import noppe.minecraft.arena.item.Menu;
import noppe.minecraft.arena.location.Loc;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

public class Arena extends ArenaEventListener {
    public ArenaPlugin arenaPlugin;
    public ArenaGame arenaGame;
    public int ticks;
    public List<Plyer> players;

    public Arena(ArenaPlugin arenaPlugin){
        this.arenaPlugin = arenaPlugin;
        this.arenaPlugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.arenaPlugin, this::onTick, 1, 1);
    }

    public void load(){
        Loc.setArena(this);

        Bld.boxHome(Loc.spawn, Material.STONE_BRICKS, Material.BLACK_STAINED_GLASS, Material.AIR, 5, 5, 3);

        this.ticks = 0;
        this.players = new ArrayList<>();
        for (Player player: this.arenaPlugin.getServer().getOnlinePlayers()){
            this.onNewPlayer(player);
        }
    }

    public void onTick(){
        this.ticks += 1;
        if (this.arenaGame != null){
            this.arenaGame.onTick();
        }
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event, EventPlayerInteract ev){
        if (!ev.rightClick){
            return;
        }
        if (M.matches(ev.item, Menu.StartGame) && ev.plyer.useMenu()){
            if (this.arenaGame == null){
                this.arenaGame = new ArenaGame(this);
            }
            this.arenaGame.onPlayerJoin(ev.plyer);
        } else if (M.matches(ev.item, Menu.build) && ev.plyer.useMenu()) {
            Bld.test(ev.plyer.player.getLocation().clone().add(0, -1, 0), Material.STONE);
        } else if (M.matches(ev.item, Menu.erase) && ev.plyer.useMenu()) {
            Bld.test(ev.plyer.player.getLocation().clone().add(0, -1, 0), Material.AIR);
        }

        if (this.arenaGame != null){
            this.arenaGame.onPlayerInteract(event, ev);
        }
    }

    public void onPlayerJoin(PlayerJoinEvent event, EventPlayerJoin ev){
        Player player = event.getPlayer();
        M.print(player.getName()+" joined!");
        this.onNewPlayer(player);
    }

    public void onNewPlayer(Player player){
        this.players.add(new Plyer(this, player));

        player.teleport(Loc.spawn);

        if (player.getName().equals("Noppe")) {
            player.setGameMode(GameMode.CREATIVE);
        }
        else {
            player.setGameMode(GameMode.ADVENTURE);
        }

        M.setInventory(player, Inv.lobby);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event, EventInventoryClick ev){
        if (ev.itemClicked == null || this.arenaGame == null){
            return;
        } else {
            this.arenaGame.onInventoryClick(event, ev);
        }
    }

    public boolean isArena(){
        return true;
    }
}
