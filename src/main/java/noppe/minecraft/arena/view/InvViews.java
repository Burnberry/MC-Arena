package noppe.minecraft.arena.view;

import noppe.minecraft.arena.entities.Plyer;
import noppe.minecraft.arena.item.Upgr;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class InvViews {
    public static Inventory getSoulShop(Plyer plyer) {
        Inventory inventory = Bukkit.createInventory(null, InventoryType.HOPPER, "Soul Shop");
        inventory.setItem(0, Upgr.getHealthIncrease(plyer));
        inventory.setItem(4, Upgr.getSouls(plyer));
        return inventory;
    }
}
