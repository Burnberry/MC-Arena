package noppe.minecraft.arena.spellcasting;

import noppe.minecraft.arena.entities.Plyer;
import org.bukkit.util.Vector;

import java.util.List;

public abstract class Spell {
    List<Vector> points;

    /*
    Make sure first point has x = 0, z = 0
    First point player draws only has y nonzero
     */
    public Spell(List<Vector> points){
        this.points = points;
//        S.projectPointsXY(this.points);
    }

    public int size(){
        return this.points.size();
    }

    abstract public void cast(Plyer plyer);

    abstract public String getName();
}
