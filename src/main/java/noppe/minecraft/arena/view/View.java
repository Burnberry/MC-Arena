package noppe.minecraft.arena.view;

import noppe.minecraft.arena.entities.Plyer;
import noppe.minecraft.arena.event.ArenaEventListener;
import noppe.minecraft.arena.event.events.EventInventoryClick;
import noppe.minecraft.arena.helpers.M;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import java.util.List;

public abstract class View extends ArenaEventListener {
    public Plyer plyer;
    public Inventory inventory;
    public InventoryView inventoryView;
    public List<MenuElement> menuElements;

    public View(Plyer plyer){
        this.plyer = plyer;
        this.plyer.view = this;
    }

    public abstract void reload();

    public void close(){
        this.plyer.player.closeInventory();
        if (this == this.plyer.view){
            this.plyer.view = null;
        }
    }

    public void switchInventory(Inventory inventory, List<MenuElement> menuElements){
        this.inventory = inventory;
        this.inventoryView = this.plyer.player.openInventory(inventory);
        this.menuElements = menuElements;
    }

    public void onInventoryClick(InventoryClickEvent event, EventInventoryClick ev){
        if (this.menuElements == null){
            return;
        }
        for (MenuElement menuElement: this.menuElements){
            if (M.matches(menuElement.itemStack, ev.itemClicked)){
                menuElement.onClick(this);
                return;
            }
        }
    }

}
