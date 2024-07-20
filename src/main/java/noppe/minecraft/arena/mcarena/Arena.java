package noppe.minecraft.arena.mcarena;

import noppe.minecraft.arena.event.ArenaEventHandler;
import noppe.minecraft.arena.event.events.EventEntityDeath;
import noppe.minecraft.arena.helpers.M;
import noppe.minecraft.arena.item.Menu;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityRemoveEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Arena extends JavaPlugin implements Listener {

    ArenaEventHandler arenaEventHandler;

    Location spawnLocation;
    ItemStack spawnMenuItem;
    ItemStack removeWaveMenuItem;
    ItemStack stopGameMenuItem;
    int ticks;
    ArenaGame arenaGame;

    @Override
    public void onEnable() {
        this.arenaEventHandler = new ArenaEventHandler(this);
        this.reloadPlugin();
        this.arenaEventHandler.registerEvents();
        this.getServer().getPluginManager().registerEvents(this, this);
    }


    public void reloadPlugin(){
        M.arena = this;

        this.spawnLocation = new Location(this.getServer().getWorld("world"), 0.5, 100, 0.5);

        this.spawnMenuItem = Menu.StartGame;
        this.removeWaveMenuItem = Menu.removeWave;
        this.stopGameMenuItem = Menu.stopGame;

        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, this::onTick, 1, 1);
        this.ticks = 0;

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        this.print(player.getName()+" joined!");
        player.teleport(this.spawnLocation);

        if (player.getName().equals("Noppe")) {
            player.setGameMode(GameMode.CREATIVE);
        }
        else {
            player.setGameMode(GameMode.ADVENTURE);
        }

        player.getInventory().clear();
        player.getInventory().setItem(0, this.spawnMenuItem);
        player.getInventory().setItem(1, this.removeWaveMenuItem);
        player.getInventory().setItem(2, this.stopGameMenuItem);
    }

    public void onTick(){
        this.ticks += 1;
        if (this.arenaGame != null){
            this.arenaGame.onTick();
        }
//        this.broadcast(Integer.toString(this.ticks));
    }

    @EventHandler
    public void onMenuPress(PlayerInteractEvent event){
        if (Objects.requireNonNull(event.getHand()).name().equals("HAND") && event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.NETHER_STAR)){
            if (this.arenaGame == null){
                this.arenaGame = new ArenaGame(this);
            }
            this.arenaGame.onPlayerJoin(event.getPlayer());
        }

        if (Objects.requireNonNull(event.getHand()).name().equals("HAND") && this.arenaGame != null){
            this.arenaGame.onMenuPress(event);
        }
    }

    public void print(String message){
        this.getServer().broadcastMessage(message);
        System.out.println(message);
    }

    public void setItemName(ItemStack item, String name){
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(name);
        item.setItemMeta(meta);
    }
}

