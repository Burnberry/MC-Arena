package noppe.minecraft.arena.item;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class Inv {
    public static Inventory lobby = Inv.createLobby();
    public static Inventory game = Inv.createGame();
    public static Inventory defaultArenaKit = Inv.createDefaultArenaKit();

    static Inventory createLobby(){
        Inventory inventory = Bukkit.createInventory(null, InventoryType.PLAYER);
        inventory.setItem(0, Menu.StartGame);
        inventory.setItem(1, Menu.removeWave);
        inventory.setItem(2, Menu.stopGame);
        return inventory;
    }

    static Inventory createGame(){
        Inventory inventory = Bukkit.createInventory(null, InventoryType.PLAYER);
        inventory.setItem(5, Menu.startWave);
        return inventory;
    }

    static Inventory createDefaultArenaKit(){
        Inventory inventory = Bukkit.createInventory(null, InventoryType.PLAYER);
        inventory.setItem(0, Gear.knife);
        return inventory;
    }
}
