package noppe.minecraft.arena.entities;

import noppe.minecraft.arena.event.ArenaEventListener;
import noppe.minecraft.arena.helpers.M;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Plyer extends Ent{
    public Player player;
    public int souls;

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

        this.updateMaxHealth();
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
}
