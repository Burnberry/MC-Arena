package noppe.minecraft.arena.item;

import noppe.minecraft.arena.helpers.Key;
import noppe.minecraft.arena.helpers.M;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Menu {
    public static ItemStack StartGame = Menu.createStartGame();
    public static ItemStack removeWave = Menu.createRemoveWave();
    public static ItemStack stopGame = Menu.createStopGame();

    public static ItemStack soulShop = Menu.createSoulShop();
    public static ItemStack startWave = Menu.createStartWave();

    public static ItemStack build = Menu.createBuild();
    public static ItemStack erase = Menu.createErase();

    public static ItemStack staff = Menu.createStaff();


    static ItemStack createStartGame(){
        ItemStack itemStack = new ItemStack(Material.NETHER_STAR);
        M.setItemName(itemStack, "Start Game");
        M.setItemNBTName(itemStack, "startGame");
        return itemStack;
    }

    static ItemStack createRemoveWave(){
        ItemStack itemStack = new ItemStack(Material.BLAZE_ROD);
        M.setItemName(itemStack, "Remove Wave");
        M.setItemNBTName(itemStack, "removeGame");
        return itemStack;
    }

    static ItemStack createStopGame(){
        ItemStack itemStack = new ItemStack(Material.BREEZE_ROD);
        M.setItemName(itemStack, "Stop Game");
        M.setItemNBTName(itemStack, "stopGame");
        return itemStack;
    }

    static ItemStack createSoulShop(){
        ItemStack itemStack = new ItemStack(Material.ECHO_SHARD);
        M.setItemName(itemStack, "Soul Shop");
        M.setItemNBTName(itemStack, "soulShop");
        return itemStack;
    }

    static ItemStack createStartWave(){
        ItemStack itemStack = new ItemStack(Material.CLOCK);
        M.setItemName(itemStack, "Start Wave");
        M.setItemNBTName(itemStack, "startWave");
        return itemStack;
    }

    static ItemStack createBuild(){
        ItemStack itemStack = new ItemStack(Material.BRICK);
        M.setItemName(itemStack, "Build");
        M.setItemNBTName(itemStack, "build");
        return itemStack;
    }

    static ItemStack createErase(){
        ItemStack itemStack = new ItemStack(Material.STONE_SHOVEL);
        M.setItemName(itemStack, "Erase");
        M.setItemNBTName(itemStack, "erase");
        return itemStack;
    }

    static ItemStack createStaff(){
        ItemStack itemStack = new ItemStack(Material.MACE);
        M.setItemName(itemStack, "Staff");
        M.setItemNBTName(itemStack, "staff");
        return itemStack;
    }
}
