package noppe.minecraft.arena.view.views;

import noppe.minecraft.arena.entities.Plyer;
import noppe.minecraft.arena.view.InvViews;
import noppe.minecraft.arena.view.View;
import noppe.minecraft.arena.view.menuelements.HealthUpgrade;

import java.util.Arrays;

public class SoulShop extends View {
    public SoulShop(Plyer plyer) {
        super(plyer);
        this.reload();
    }

    @Override
    public void reload() {
        this.switchInventory(InvViews.getSoulShop(this.plyer), Arrays.asList(new HealthUpgrade(this.plyer)));
    }
}
