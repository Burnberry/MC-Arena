package noppe.minecraft.arena.item;

import noppe.minecraft.arena.entities.Plyer;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class InvView {
    public static Inventory getSoulShop(Plyer plyer) {
        Inventory inventory = Bukkit.createInventory(null, InventoryType.HOPPER, "Soul Shop");
        inventory.setItem(0, Upgr.getHealthIncrease(plyer));
        inventory.setItem(4, Upgr.getSouls(plyer));
        return inventory;
    }
}
