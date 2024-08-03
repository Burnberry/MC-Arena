package noppe.minecraft.arena.item;

import noppe.minecraft.arena.helpers.M;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Gear {
    public static ItemStack knife = Gear.createKnife();
    public static ItemStack bow = Gear.createBow();

    static ItemStack createKnife(){
        ItemStack itemStack = new ItemStack(Material.DIAMOND_SWORD);
        M.setItemName(itemStack, "Knife");
        return itemStack;
    }

    static ItemStack createBow(){
        ItemStack itemStack = new ItemStack(Material.BOW);
        M.setItemName(itemStack, "Bow");
        return itemStack;
    }
}
