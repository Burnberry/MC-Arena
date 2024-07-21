package noppe.minecraft.arena.entities;

import noppe.minecraft.arena.event.ArenaEventListener;
import noppe.minecraft.arena.helpers.M;
import noppe.minecraft.arena.location.Loc;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Plyer extends Ent{
    public Player player;
    public int souls;
    private final int menuUseCooldown = 10;
    public int lastMenuUseTime = -menuUseCooldown;

    // upgrades
    int healthIncreaseLevel;

    public Plyer(ArenaEventListener origin, Player player) {
        super(origin, player);
        M.setOrigin(player, origin);
        this.type = "player";
        this.player = player;
        this.souls = 0;

        // upgrades
        this.healthIncreaseLevel = 0;

        this.fullHeal();
        this.setRespawnLocation(Loc.spawn);
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
}
