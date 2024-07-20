package noppe.minecraft.arena.helpers;

import noppe.minecraft.arena.entities.Ent;
import noppe.minecraft.arena.event.ArenaEventListener;
import noppe.minecraft.arena.mcarena.Arena;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class M {
    public static Arena arena;

    public static void setMetaData(Entity entity, String key, Object value){
        entity.setMetadata(key, new FixedMetadataValue(M.arena, value));
    }

    public static Object getMetaData(Entity entity, String key){
        if (entity.getMetadata(key).isEmpty()){
            return null;
        }
        return entity.getMetadata(key).getFirst().value();
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
        item.setItemMeta(itemMeta);
    }

    public static String getLore(ItemStack item){
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
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

    public static void print(String message){
        M.arena.print(message);
    }
}
