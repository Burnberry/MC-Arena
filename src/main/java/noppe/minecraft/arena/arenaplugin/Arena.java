package noppe.minecraft.arena.arenaplugin;

import com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Arena extends JavaPlugin implements Listener {

    Location spawnLocation;
    Location arenaLocation;
    ItemStack spawnMenuItem;
    ItemStack removeWaveMenuItem;
    ItemStack stopGameMenuItem;
    int ticks;
    ArenaGame arenaGame;

    @Override
    public void onEnable() {
        this.broadcast("Arena Plugin Enable");
        getServer().getPluginManager().registerEvents(this, this);

        this.spawnLocation = new Location(this.getServer().getWorld("world"), 0.5, 100, 0.5);
        this.arenaLocation = new Location(this.getServer().getWorld("world"), 0.5, 95, 20);

        this.spawnMenuItem = new ItemStack(Material.NETHER_STAR);
        this.setItemName(this.spawnMenuItem, "Start");
        this.removeWaveMenuItem = new ItemStack(Material.FIRE_CHARGE);
        this.setItemName(this.removeWaveMenuItem, "Remove Wave");
        this.stopGameMenuItem = new ItemStack(Material.RED_CANDLE);
        this.setItemName(this.stopGameMenuItem, "Stop Game");

        this.ticks = 0;
        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, this::onTick, 1, 1);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        this.broadcast(player.name().append(Component.text(" joined!")));
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
            this.broadcast(event.getPlayer().name().append(Component.text( " Is Starting A Game!")));
            Player player = event.getPlayer();

            player.teleport(this.arenaLocation);
//            player.getInventory().clear();
//            player.setGameMode(GameMode.ADVENTURE);
            this.arenaGame = new ArenaGame(this);
        }

        if (Objects.requireNonNull(event.getHand()).name().equals("HAND") && this.arenaGame != null){
            this.arenaGame.onMenuPress(event);
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
        if (this.arenaGame != null) {
            this.arenaGame.onEntityDeath(event);
        }
    }

    @EventHandler
    public void onEntityRemoved(EntityRemoveFromWorldEvent event){
        if (this.arenaGame != null) {
            this.arenaGame.onEntityRemoved(event);
        }
    }

    public void broadcast(String message){
        this.getServer().broadcast(Component.text(message));
    }

    public void broadcast(Component message){
        this.getServer().broadcast(message);
    }

    public void setItemName(ItemStack item, String name){
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(name));
        item.setItemMeta(meta);
    }
}
