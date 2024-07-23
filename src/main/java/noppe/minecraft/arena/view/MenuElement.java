package noppe.minecraft.arena.view;

import org.bukkit.inventory.ItemStack;

public abstract class MenuElement {
    public ItemStack itemStack;

    public MenuElement(ItemStack itemStack){
        this.itemStack = itemStack;
    }

    abstract public void onClick(View view);
}
