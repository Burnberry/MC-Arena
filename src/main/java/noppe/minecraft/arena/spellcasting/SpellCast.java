package noppe.minecraft.arena.spellcasting;

import noppe.minecraft.arena.entities.Plyer;
import noppe.minecraft.arena.helpers.C;
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
    int chargeRate = 10;
    int chargeCooldown = chargeRate;
    int pointsConnected = 0;
    Vector norm;
    List<Vector> points = new ArrayList<>();
    List<Vector> linePoints = new ArrayList<>();

    public enum State {
        CASTING,
        CHARGING,
        CHARGED,
        CAST
    }
    public State state;

    private final double threshold = 0.5;
    private final double yThreshold = 0.5;
    private final double scale = 3;

    public SpellCast(Plyer plyer){
        this.plyer = plyer;
        this.state = State.CASTING;
    }

    public void onTick(){
        this.tick += 1;
        if (this.state == State.CHARGING){
            this.charge();
        }
        if (this.state != State.CAST){
            this.spawnParticles();
        }
    }

    public void castNode(){
        Location location = this.plyer.player.getEyeLocation();
        Vector playerVec = location.getDirection().normalize();
        Vector playerDirection = playerVec.clone().setY(0).normalize();

        if (Math.abs(playerVec.getY()) > this.yThreshold){
            this.plyer.player.playSound(this.plyer.player, Sound.ENTITY_VILLAGER_NO, 1, 1);
            return;
        }

        if (this.norm == null){
            this.norm = playerDirection.clone();
        }
        if (this.norm.dot(playerDirection) < this.threshold){
            this.plyer.player.playSound(this.plyer.player, Sound.ENTITY_VILLAGER_NO, 1, 1);
            return;
        }
        double dot = this.norm.dot(playerVec);
        this.points.add(playerVec.clone().multiply(1/dot).subtract(this.norm).multiply(this.scale));
        playerVec.subtract(this.norm);
        this.plyer.player.playSound(this.plyer.player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
    }

    public void spawnParticles(){
        Location location = this.plyer.player.getEyeLocation().clone().add(this.norm.clone().multiply(this.scale));
        List<Vector> particlePoints;
        if (this.state == State.CASTING){
            particlePoints = this.points;
        }
        else {
            particlePoints = this.linePoints;
        }
        for (Vector point: particlePoints){
            this.plyer.player.spawnParticle(Particle.ELECTRIC_SPARK , location.clone().add(point), 0, 0, 0, 0, 0);
        }
    }

    public void cast(){
        if (this.state == State.CASTING){
            this.state = State.CHARGING;
            if (this.points.size() == 4){
                List<Vector> newPoints = C.clone(this.points);
                S.projectPointsXY(newPoints, this.norm.clone().crossProduct(S.unitY));
                Spell bestSpell = null;
                double minError = 1;
                for (Spell spell: Spells.test){
                    double error = S.similarityError(spell, newPoints);
//                    M.print(spell.getName() + " error: " + error);
                    if (error < minError){
                        minError = error;
                        bestSpell = spell;
                    }
                }
                bestSpell.cast(this.plyer);
                this.replacePoints(bestSpell);
            }
        }
        else {
            this.state = State.CAST;
            this.linePoints.clear();
        }
    }

    private void charge(){
        this.chargeRate -= 1;
        if (this.chargeRate <= 0){
            this.chargeRate = this.chargeCooldown;
            this.drawLine();
        }
    }

    private void drawLine(){
        this.plyer.player.playSound(this.plyer.player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
        Vector P0 = this.points.get(this.pointsConnected).clone();
        Vector P1 = this.points.get(this.pointsConnected+1).clone();

        if (this.linePoints.isEmpty()){
            this.linePoints.add(P0.clone());
            return;
        }

        int jumps = (int)(P0.distance(P1)/0.2);
//        M.print("jumps: "+jumps);
        for (double i=0; i<jumps; i+=1){
            Vector P = P0.clone().multiply(i/jumps).add(P1.clone().multiply((jumps-i)/jumps));
            P.add(P1.clone().multiply((jumps-i)/jumps));
            this.linePoints.add(P0.clone().multiply(i/jumps).add(P1.clone().multiply((jumps-i)/jumps)));
//            this.linePoints.add(P0.clone().multiply(i/jumps).add(P1.clone().multiply((jumps-i)/jumps)));
        }

        this.pointsConnected += 1;
        if (this.pointsConnected + 1 >= this.points.size()){
            this.state = State.CHARGED;
        }
//        M.print(""+this.linePoints.size());
    }
    private void replacePoints(Spell spell){
        List<Vector> newPoints = new ArrayList<>();
        Vector unitX = this.norm.clone().crossProduct(S.unitY);
        for (Vector point: spell.points){
            Vector newPoint = point.clone().add(this.points.get(0));
            newPoint = unitX.clone().multiply(newPoint.getX()).add(S.unitY.clone().multiply(newPoint.getY()));
            newPoints.add(newPoint);
        }
        this.points = newPoints;
    }
}
