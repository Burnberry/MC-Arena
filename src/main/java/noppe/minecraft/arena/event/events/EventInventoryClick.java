package noppe.minecraft.arena.event.events;

import noppe.minecraft.arena.entities.Plyer;
import noppe.minecraft.arena.helpers.M;
import org.bukkit.GameMode;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class EventInventoryClick extends ArenaEvent{
    public Plyer plyer;
    public ItemStack itemClicked;

    public EventInventoryClick(InventoryClickEvent event) {
        super(event);
        this.plyer = (Plyer) M.getWrapper(event.getWhoClicked());
        this.itemClicked = event.getCurrentItem();
        event.setCancelled(!this.plyer.player.getGameMode().equals(GameMode.CREATIVE));
    }
}
