package noppe.minecraft.arena.helpers;

import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class C {
    public static List<Vector> clone(List<Vector> vectorsOld){
        List<Vector> vectors = new ArrayList<>();
        for (Vector vector: vectorsOld){
            vectors.add(vector.clone());
        }
        return vectors;
    }
}
