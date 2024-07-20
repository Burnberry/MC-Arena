package noppe.minecraft.arena.item;

import noppe.minecraft.arena.helpers.M;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Gear {
    public static ItemStack knife = Gear.createKnife();

    static ItemStack createKnife(){
        ItemStack itemStack = new ItemStack(Material.DIAMOND_SWORD);
        M.setItemName(itemStack, "Knife");
        M.setLore(itemStack, "Knife");
        return itemStack;
    }
}
