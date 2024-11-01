package noppe.minecraft.arena.item;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class Inv {
    public static Inventory lobby = Inv.createLobby();
    public static Inventory game = Inv.createGame();

    static Inventory createLobby(){
        Inventory inventory = Bukkit.createInventory(null, InventoryType.PLAYER, "Inventory");
        inventory.setItem(0, Menu.StartGame);
        inventory.setItem(2, Menu.removeWave);
        inventory.setItem(3, Menu.stopGame);
        inventory.setItem(5, Menu.build);
        inventory.setItem(6, Menu.erase);
        inventory.setItem(8, Menu.staff);
        return inventory;
    }

    static Inventory createGame(){
        Inventory inventory = Bukkit.createInventory(null, InventoryType.PLAYER, "Inventory");
        inventory.setItem(0, Gear.knife);
        inventory.setItem(1, Menu.staff);
        inventory.setItem(4, Menu.soulShop);
        inventory.setItem(8, Menu.startWave);
        return inventory;
    }
}
