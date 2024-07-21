package noppe.minecraft.arena.event.events;

import noppe.minecraft.arena.entities.Plyer;
import noppe.minecraft.arena.helpers.M;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class EventPlayerInteract extends ArenaEvent{
    public ItemStack item;
    public Plyer plyer;
    public boolean rightClick = false;
    public boolean leftClick  = false;

    public EventPlayerInteract(PlayerInteractEvent event) {
        super(event);
        this.plyer = (Plyer) M.getWrapper(event.getPlayer());
        if(Objects.requireNonNull(event.getHand()).name().equals("HAND")){
            this.item = event.getPlayer().getInventory().getItemInMainHand();
        }
        if(Objects.requireNonNull(event.getHand()).name().equals("OFF_HAND")){
            this.item = event.getPlayer().getInventory().getItemInOffHand();
        }

        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK){
            this.leftClick = true;
        }
        else if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
            this.rightClick = true;
        }
    }
}
