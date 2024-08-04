package noppe.minecraft.arena.entities;

import noppe.minecraft.arena.event.ArenaEventListener;
import noppe.minecraft.arena.event.events.EventInventoryClick;
import noppe.minecraft.arena.helpers.M;
import noppe.minecraft.arena.item.Menu;
import noppe.minecraft.arena.location.Loc;
import noppe.minecraft.arena.view.View;
import noppe.minecraft.arena.view.views.SoulShop;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Objects;

public class Plyer extends Ent{
    public Player player;
    public int souls = 0;
    public double maxMana = 3;
    public double mana = maxMana;
    public int airManaDisplay = 0;
    private final int menuUseCooldown = 10;
    public int lastMenuUseTime = -menuUseCooldown;
    public View view;

    // upgrades
    int healthIncreaseLevel;

    public Plyer(ArenaEventListener origin, Player player) {
        super(origin, player);
        M.setOrigin(player, origin);
        this.type = "player";
        this.player = player;
        this.souls = 20;

        // upgrades
        this.healthIncreaseLevel = 0;

        this.fullHeal();
        this.updateAirManaDisplay();
        this.setRespawnLocation(Loc.spawn);

        M.print(""+player.getMaximumAir());
        player.setMaximumAir(300);
    }

    public void onTick(){
        player.setRemainingAir(airManaDisplay);
//        player.setRemainingAir((int)(mana*3) - 25);
    }

    public boolean useMenu(){
        if (this.lastMenuUseTime + this.menuUseCooldown <= M.getTicks()){
            this.lastMenuUseTime = M.getTicks();
            return true;
        }
        M.print("Wait before using a menu again");
        return false;
    }

    public void fullHeal(){
        this.updateMaxHealth();
        this.player.setHealth(this.getMaxHealth());
        this.player.setFoodLevel(20);
        this.player.setSaturation(20);
    }

    public void heal(double health){
        health += this.player.getHealth();
        health = Math.min(health, this.getMaxHealth());
        this.player.setHealth(health);
    }

    public void addMana(double addedMana){
        mana += addedMana;
        mana = Math.min(Math.max(mana, 0.0), maxMana);
        this.updateAirManaDisplay();
    }

    public void updateAirManaDisplay(){
        airManaDisplay = (int)(mana)*30 - 25;
    }

    public Boolean isPlayer(){
        return true;
    }

    public String getName(){
        return this.player.getName();
    }

    void spend(int souls){
        this.souls -= souls;
    }

    void updateMaxHealth(){
        Objects.requireNonNull(this.player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(this.healthIncreaseLevel*2+20);
    }

    public double getMaxHealth(){
        return Objects.requireNonNull(this.player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue();
    }

    public int getHealthIncreaseCost(){
        return 5*(this.healthIncreaseLevel+1);
    }

    public void buyHealthIncrease(){
        if (this.souls >= this.getHealthIncreaseCost()){
            this.spend(this.getHealthIncreaseCost());
            this.upgradeHealthIncrease();
        } else {
            this.player.sendMessage("You lack souls...");
        }
    }

    void upgradeHealthIncrease(){
        this.healthIncreaseLevel += 1;
        this.updateMaxHealth();
    }

    public void setRespawnLocation(Location location){
        this.player.setRespawnLocation(location);
    }

    public boolean canMoveItems(){
        return (this.view == null);
    }

    public void onInventoryClick(InventoryClickEvent event, EventInventoryClick ev){
        if (this.view != null){
            M.print(this.view.toString());
            this.view.onInventoryClick(event, ev);
        }
        if (M.matches(ev.itemClicked, Menu.soulShop)){
            this.view = new SoulShop(this);
            M.print(this.view.toString());
        }
    }
}
