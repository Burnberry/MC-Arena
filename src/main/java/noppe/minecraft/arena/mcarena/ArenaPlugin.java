package noppe.minecraft.arena.mcarena;

import noppe.minecraft.arena.event.ArenaEventHandler;
import noppe.minecraft.arena.helpers.M;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArenaPlugin extends JavaPlugin implements Listener {

    ArenaEventHandler arenaEventHandler;
    public Arena arena;

    @Override
    public void onEnable() {
        this.arenaEventHandler = new ArenaEventHandler(this);
        this.reloadPlugin();
        M.print("Restarting Plugin");
        this.getServer().getPluginManager().registerEvents(this, this);
    }


    public void reloadPlugin(){
        M.arenaPlugin = this;
        this.arena = new Arena(this);
        this.arena.load();
    }

    public void print(String message){
        this.getServer().broadcastMessage(message);
    }
}

