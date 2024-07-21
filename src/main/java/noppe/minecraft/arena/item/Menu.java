package noppe.minecraft.arena.item;

import noppe.minecraft.arena.helpers.M;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Menu {
    public static ItemStack StartGame = Menu.createStartGame();
    public static ItemStack removeWave = Menu.createRemoveWave();
    public static ItemStack stopGame = Menu.createStopGame();

    public static ItemStack soulShop = Menu.createSoulShop();
    public static ItemStack startWave = Menu.createStartWave();


    static ItemStack createStartGame(){
        ItemStack itemStack = new ItemStack(Material.NETHER_STAR);
        M.setItemName(itemStack, "Start Game");
        M.setLore(itemStack, "StartGame");
        return itemStack;
    }

    static ItemStack createRemoveWave(){
        ItemStack itemStack = new ItemStack(Material.BLAZE_ROD);
        M.setItemName(itemStack, "Remove Wave");
        M.setLore(itemStack, "removeWave");
        return itemStack;
    }

    static ItemStack createStopGame(){
        ItemStack itemStack = new ItemStack(Material.BREEZE_ROD);
        M.setItemName(itemStack, "Stop Game");
        M.setLore(itemStack, "stopGame");
        return itemStack;
    }

    static ItemStack createSoulShop(){
        ItemStack itemStack = new ItemStack(Material.ECHO_SHARD);
        M.setItemName(itemStack, "Soul Shop");
        M.setLore(itemStack, "SoulShop");
        return itemStack;
    }

    static ItemStack createStartWave(){
        ItemStack itemStack = new ItemStack(Material.CLOCK);
        M.setItemName(itemStack, "Start Wave");
        M.setLore(itemStack, "startWave");
        return itemStack;
    }
}
