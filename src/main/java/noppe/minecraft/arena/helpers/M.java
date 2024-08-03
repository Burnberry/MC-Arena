package noppe.minecraft.arena.helpers;

import noppe.minecraft.arena.entities.Ent;
import noppe.minecraft.arena.entities.Plyer;
import noppe.minecraft.arena.event.ArenaEventListener;
import noppe.minecraft.arena.mcarena.ArenaPlugin;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.List;

public class M {
    public static ArenaPlugin arenaPlugin;

    public static void setMetaData(Entity entity, String key, Object value){
        entity.setMetadata(key, new FixedMetadataValue(M.arenaPlugin, value));
    }
    public static void setMetaData(Block block, String key, Object value){
        block.setMetadata(key, new FixedMetadataValue(M.arenaPlugin, value));
    }

    public static Object getMetaData(Entity entity, String key){
        if (entity.getMetadata(key).isEmpty()){
            return null;
        }
        return entity.getMetadata(key).getFirst().value();
    }

    public static Object getMetaData(Block block, String key){
        if (block.getMetadata(key).isEmpty()){
            return null;
        }
        return block.getMetadata(key).getFirst().value();
    }

    public static void setItemNBTName(ItemStack itemstack, String name){
        ItemMeta itemMeta = itemstack.getItemMeta();
        itemMeta.getPersistentDataContainer().set(Key.name, PersistentDataType.STRING, name);
        itemstack.setItemMeta(itemMeta);
    }

    public static String getItemNBTName(ItemStack itemstack){
        if (!itemstack.getItemMeta().getPersistentDataContainer().has(Key.name, PersistentDataType.STRING)){
            return "empty";
        }
        return itemstack.getItemMeta().getPersistentDataContainer().get(Key.name, PersistentDataType.STRING);
    }

    public static void setItemNBTTag(ItemStack itemstack, NamespacedKey key){
        ItemMeta itemMeta = itemstack.getItemMeta();
        itemMeta.getPersistentDataContainer().set(key, PersistentDataType.BOOLEAN, true);
        itemstack.setItemMeta(itemMeta);
    }

    public static boolean getItemNBTTag(ItemStack itemstack, NamespacedKey key){
        return itemstack.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.BOOLEAN);
    }

    public static void setOrigin(Player player, ArenaEventListener origin) {
        M.setMetaData(player, "origin", origin);
    }

    public static Ent getWrapper(Entity entity){
        return (Ent) M.getMetaData(entity, "wrapper");
    }

    public static Ent getWrapper(Block block){
        return (Ent) M.getMetaData(block, "wrapper");
    }

    public static ArenaEventListener getOrigin(Entity entity){
        return (ArenaEventListener) M.getMetaData(entity, "origin");
    }

    public static Entity spawnEntity(Object origin, Location location, Class clazz){
        Entity entity = M.getWorld().spawn(location, clazz, false, null);
        M.setMetaData(entity, "origin", origin);
        return entity;
    }

    public static void setItemName(ItemStack itemStack, String name){
        ItemMeta meta = itemStack.getItemMeta();
        assert meta != null;
        meta.setDisplayName(name);
        itemStack.setItemMeta(meta);
    }

    public static void setLore(ItemStack item, String lore){
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setLore(Arrays.asList(lore));
//        itemMeta.setHideTooltip(true); // removes full tooltip
        item.setItemMeta(itemMeta);
    }

    public static String getLore(ItemStack item){
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null){
            return "empty";
        }
        List<String> lore = itemMeta.getLore();
        if (lore == null){
            return "empty";
        }
        if (itemMeta.getLore().isEmpty()){
            return "empty";
        }
        return itemMeta.getLore().getFirst();
    }

    public static Boolean matches(ItemStack item1, ItemStack item2){
        return (!M.getItemNBTName(item1).equals("empty") && M.getItemNBTName(item1).equals(M.getItemNBTName(item2)));
    }

    public static Plyer getPlayerFromDamageSource(Ent ent, EntityDeathEvent event){
        DamageSource damageSource = event.getDamageSource();
        if (damageSource != null){
            Entity entity = damageSource.getCausingEntity();
            if (entity != null){
                Ent entKiller = M.getWrapper(entity);
                if (entKiller.isPlayer()){
                    return (Plyer) entKiller;
                }
            }
        }
        return null;
    }

    public static void setInventory(Player player, Inventory inventory){
        player.getInventory().setContents(inventory.getContents());
    }

    public static void setInventory(Plyer plyer, Inventory inventory){
        M.setInventory(plyer.player, inventory);
    }

    public static World getWorld(){
        return M.arenaPlugin.getServer().getWorld("world");
    }

    public static int getTicks(){
        return M.arenaPlugin.arena.ticks;
    }

    public static void print(String message){
        M.arenaPlugin.print(message);
        System.out.println(message);
    }
}
