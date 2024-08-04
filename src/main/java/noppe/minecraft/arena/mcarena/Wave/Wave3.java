package noppe.minecraft.arena.mcarena.Wave;

import noppe.minecraft.arena.entities.monsters.MS;
import noppe.minecraft.arena.mcarena.colosseum.Colosseum;

public class Wave3 extends Wave{
    public Wave3(Colosseum colosseum) {
        super(colosseum);
        rounds = 2;
    }

    @Override
    protected void spawnRoundMonsters(){
        this.spawnMonster(10, MS.Type.ZOMBIE);
        this.spawnMonster(10, MS.Type.ZOMBIE);
        this.spawnMonster(10, MS.Type.ZOMBIE);
        this.spawnMonster(10, MS.Type.ZOMBIE);
        this.spawnMonster(10, MS.Type.ZOMBIE);
        if (rounds == 0){
            this.spawnMonster(20, MS.Type.SKELETON);
        }
    }
}