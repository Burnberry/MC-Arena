package noppe.minecraft.arena.spell;

import noppe.minecraft.arena.entities.Plyer;
import noppe.minecraft.arena.helpers.M;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class SpellCast {
    Plyer plyer;
    int tick = 0;
    Vector direction;
    List<Vector> points = new ArrayList<>();

    private final double threshold = 0.5;
    private final double yThreshold = 0.5;
    private final double scale = 3;

    public SpellCast(Plyer plyer){
        this.plyer = plyer;
    }

    public void onTick(){
        this.tick += 1;
        this.spawnParticles();
    }

    public void cast(){
        Location location = this.plyer.player.getEyeLocation();
        Vector playerVec = location.getDirection().normalize();
        Vector playerDirection = playerVec.clone().setY(0).normalize();

        if (Math.abs(playerDirection.getY()) > this.yThreshold){
            this.plyer.player.playSound(this.plyer.player, Sound.ENTITY_VILLAGER_NO, 1, 1);
            return;
        }

        if (this.direction == null){
            this.direction = playerDirection.clone();
        }
        if (this.direction.dot(playerDirection) < this.threshold){
            this.plyer.player.playSound(this.plyer.player, Sound.ENTITY_VILLAGER_NO, 1, 1);
            return;
        }
        double dot = this.direction.dot(playerVec);
        this.points.add(playerVec.clone().multiply(this.scale/dot));
        this.plyer.player.playSound(this.plyer.player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
    }

    public void spawnParticles(){
        Location location = this.plyer.player.getEyeLocation();
        for (Vector point: this.points){
            this.plyer.player.spawnParticle(Particle.ELECTRIC_SPARK , location.clone().add(point), 0, 0, 0, 0, 0);
        }
    }
}
