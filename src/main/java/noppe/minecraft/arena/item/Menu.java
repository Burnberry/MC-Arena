package noppe.minecraft.arena.item;

import noppe.minecraft.arena.helpers.M;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Menu {
    public static ItemStack StartGame = Menu.createStartGame();
    public static ItemStack removeWave = Menu.createRemoveWave();
    public static ItemStack stopGame = Menu.createStopGame();


    static ItemStack createStartGame(){
        ItemStack itemStack = new ItemStack(Material.NETHER_STAR);
        M.setItemName(itemStack, "Start Game");
        M.setLore(itemStack, "StartGame");
        return itemStack;
    }

    static ItemStack createRemoveWave(){
        ItemStack itemStack = new ItemStack(Material.FIRE_CHARGE);
        M.setItemName(itemStack, "Remove Wave");
        M.setLore(itemStack, "removeWave");
        return itemStack;
    }

    static ItemStack createStopGame(){
        ItemStack itemStack = new ItemStack(Material.RED_CANDLE);
        M.setItemName(itemStack, "Stop Game");
        M.setLore(itemStack, "stopGame");
        return itemStack;
    }
}
