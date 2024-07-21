package noppe.minecraft.arena.helpers;

import noppe.minecraft.arena.entities.Ent;
import noppe.minecraft.arena.entities.Plyer;
import noppe.minecraft.arena.event.ArenaEventListener;
import noppe.minecraft.arena.mcarena.ArenaPlugin;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class M {
    public static ArenaPlugin arenaPlugin;

    public static void setMetaData(Entity entity, String key, Object value){
        entity.setMetadata(key, new FixedMetadataValue(M.arenaPlugin, value));
    }

    public static Object getMetaData(Entity entity, String key){
        if (entity.getMetadata(key).isEmpty()){
            return null;
        }
        return entity.getMetadata(key).getFirst().value();
    }

    public static void setOrigin(Player player, ArenaEventListener origin) {
        M.setMetaData(player, "origin", origin);
    }

    public static Ent getWrapper(Entity entity){
        return (Ent) M.getMetaData(entity, "wrapper");
    }

    public static ArenaEventListener getOrigin(Entity entity){
        return (ArenaEventListener) M.getMetaData(entity, "origin");
    }

    public static Entity spawnEntity(Object origin, Location location, EntityType entityType){
        Entity entity = Objects.requireNonNull(location.getWorld()).spawnEntity(location, entityType);
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
            return "Empty";
        }
        List<String> lore = itemMeta.getLore();
        if (lore == null){
            return "Empty";
        }
        if (itemMeta.getLore().isEmpty()){
            return "Empty";
        }
        return itemMeta.getLore().getFirst();
    }

    public static Boolean matches(ItemStack item1, ItemStack item2){
        return (!M.getLore(item1).equals("Empty") && M.getLore(item1).equals(M.getLore(item2)));
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

    public static void print(String message){
        M.arenaPlugin.print(message);
        System.out.println(message);
    }
}
