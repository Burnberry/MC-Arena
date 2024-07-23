package noppe.minecraft.arena.view.menuelements;

import noppe.minecraft.arena.entities.Plyer;
import noppe.minecraft.arena.item.Upgr;
import noppe.minecraft.arena.view.MenuElement;
import noppe.minecraft.arena.view.View;
import org.bukkit.inventory.ItemStack;

public class HealthUpgrade extends MenuElement {

    public HealthUpgrade(Plyer plyer) {
        super(Upgr.getHealthIncrease(plyer));
    }

    @Override
    public void onClick(View view) {
        view.plyer.buyHealthIncrease();
        view.reload();
    }
}
