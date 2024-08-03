package noppe.minecraft.arena.helpers;

import noppe.minecraft.arena.mcarena.ArenaPlugin;
import org.bukkit.NamespacedKey;

public class Key {
    private static ArenaPlugin plugin;

    public static NamespacedKey name;

//    public static NamespacedKey origin, wrapper;
//    public static NamespacedKey startGame, removeGame, stopGame, soulShop, startWave, build, erase, staff;
//    public static NamespacedKey healthIncrease;


    public static void setArenaPlugin(ArenaPlugin plugin){
        Key.plugin = plugin;
        Key.initKeys();
    }

    private static void initKeys(){
        Key.name = new NamespacedKey(Key.plugin, "name");

//        Key.origin = new NamespacedKey(Key.plugin, "origin");
//        Key.wrapper = new NamespacedKey(Key.plugin, "wrapper");
//
//        Key.startGame = new NamespacedKey(Key.plugin, "startGame");
//        Key.removeGame = new NamespacedKey(Key.plugin, "removeGame");
//        Key.stopGame = new NamespacedKey(Key.plugin, "stopGame");
//        Key.soulShop = new NamespacedKey(Key.plugin, "soulShop");
//        Key.startWave = new NamespacedKey(Key.plugin, "startWave");
//        Key.build = new NamespacedKey(Key.plugin, "build");
//        Key.erase = new NamespacedKey(Key.plugin, "erase");
//        Key.staff = new NamespacedKey(Key.plugin, "staff");
//
//        Key.healthIncrease = new NamespacedKey(Key.plugin, "healthIncrease");
    }
}
