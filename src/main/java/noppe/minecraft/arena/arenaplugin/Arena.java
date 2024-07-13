package noppe.minecraft.arena.arenaplugin;

import net.kyori.adventure.text.Component;
//import noppe.minecraft.arena.arenaplugin.ArenaGame;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
    int sched;
    int ticks = 0;

//    ArenaGame arenaGame;

    @Override
    public void onEnable() {
        this.broadcast("Arena Plugin Enable");

        this.spawnLocation = new Location(this.getServer().getWorld("world"), 0.5, 100, 0.5);
        this.arenaLocation = new Location(this.getServer().getWorld("world"), 0.5, 105, 20);
        this.spawnMenuItem = new ItemStack(Material.NETHER_STAR);
        this.setItemName(this.spawnMenuItem, "Start");
        if(!this.getServer().getScheduler().isCurrentlyRunning(sched)) {
            sched = this.getServer().getScheduler().scheduleSyncRepeatingTask(this, this::onTick, 1, 1);
        }
    }

    public void onTick(){
        ticks += 1;
//        if (this.arenaGame != null){
//            this.arenaGame.onTick();
//        }
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
    }

    @EventHandler
    public void onMenuPress(PlayerInteractEvent event){
        if (Objects.requireNonNull(event.getHand()).name().equals("HAND") && event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.NETHER_STAR)){
            this.broadcast(event.getPlayer().name().append(Component.text( " Is Starting A Game!")));
            Player player = event.getPlayer();

            player.teleport(this.arenaLocation);
            player.getInventory().clear();
            player.setGameMode(GameMode.ADVENTURE);

//            this.arenaGame = new ArenaGame(this);
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
