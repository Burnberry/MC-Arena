package noppe.minecraft.arena.mcarena.Wave;

import noppe.minecraft.arena.entities.monsters.MS;
import noppe.minecraft.arena.mcarena.colosseum.Colosseum;

public class Wave2 extends Wave{
    public Wave2(Colosseum colosseum) {
        super(colosseum);
        rounds = 1;
    }

    @Override
    protected void spawnRoundMonsters(){
        this.spawnMonster(20, MS.Type.ZOMBIE);
        this.spawnMonster(20, MS.Type.SKELETON);
        this.spawnMonster(20, MS.Type.ZOMBIE);
    }
}
