package noppe.minecraft.arena.mcarena.Wave;

import noppe.minecraft.arena.mcarena.colosseum.Colosseum;

public class WaveBuilder {
    public static Wave getWave(Colosseum colosseum, int i){
        if (i == 1){
            return new Wave1(colosseum);
        } else if (i == 2) {
            return new Wave2(colosseum);
        } else if (i == 3) {
            return new Wave3(colosseum);
        }
        else {
            return new Wave3(colosseum);
        }
    }
}
