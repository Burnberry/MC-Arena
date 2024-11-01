package noppe.minecraft.arena.spellcasting.playerSpells;

import noppe.minecraft.arena.entities.Enmy;
import noppe.minecraft.arena.entities.Plyer;
import noppe.minecraft.arena.helpers.M;
import noppe.minecraft.arena.spellcasting.PlayerSpell;
import noppe.minecraft.arena.spellcasting.Spell;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Monster;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class ChainLightning extends PlayerSpell {
    List<Enmy> enemies;
    List<Enmy> hit = new ArrayList<>();
    Location location;
    int counter = 0;
    int delay = 3;
    double damage = 10;
    double jumpDistance = 6;

    public ChainLightning(Spell spell, Plyer plyer) {
        super(spell, plyer);
        location = plyer.player.getEyeLocation();
        M.print(plyer.origin.toString());
    }

    @Override
    public void onTick() {
        if (counter <= 0){
            if (!zap()){
                onSpellEnd();
                return;
            };
            counter = delay;
        }
        counter -= 1;
    }

    boolean zap(){
        double distance = jumpDistance;
        Enmy target = null;

        for (Enmy monster: plyer.getEnemies()){
            if (!hit.contains(monster) && distance > monster.enemy.getEyeLocation().distance(location)){
                distance = monster.enemy.getEyeLocation().distance(location);
                target = monster;
            }
        }

        if (target != null){
            hit.add(target);
            drawZap(target);
            target.enemy.damage(damage);
            target.enemy.setFireTicks(target.enemy.getFireTicks()+10);
            M.print("ZAP");
            return true;
        }

        return false;
    }

    void drawZap(Enmy target){
        Location locationTarget = target.enemy.getEyeLocation();

        int jumps = (int)(location.distance(locationTarget)/0.5);
        jumps = Math.max(1, jumps);
        Vector jump = locationTarget.clone().toVector().subtract(location.toVector()).multiply(1.0/jumps);

        for (double i=0; i<=jumps; i+=1){
            this.plyer.player.spawnParticle(Particle.ELECTRIC_SPARK, location, 0, 0, 0, 0, 0);
            location.add(jump);
        }

        location = locationTarget;
    }
}
