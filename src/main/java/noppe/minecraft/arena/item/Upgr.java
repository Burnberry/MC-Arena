package noppe.minecraft.arena.item;

import noppe.minecraft.arena.entities.Plyer;
import noppe.minecraft.arena.helpers.M;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Upgr {

    public static ItemStack getHealthIncrease(Plyer plyer) {
        ItemStack itemStack = new ItemStack(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE);
        M.setItemName(itemStack, "Increase Health: " + plyer.getHealthIncreaseCost() + " Souls");
        M.setLore(itemStack, "healthIncrease");
        return itemStack;
    }

    public static ItemStack getSouls(Plyer plyer){
        ItemStack itemStack = new ItemStack(Material.GHAST_TEAR);
        M.setItemName(itemStack, plyer.souls + " Souls");
        return itemStack;
    }
}
