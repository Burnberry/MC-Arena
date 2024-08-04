package noppe.minecraft.arena.entities.spawn;

import noppe.minecraft.arena.entities.Enmy;
import noppe.minecraft.arena.entities.monsters.MS;
import noppe.minecraft.arena.helpers.M;
import noppe.minecraft.arena.mcarena.ArenaWave;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.util.Vector;

public class MonsterSpawn {
    public int ticks = 0;
    ArenaWave arenaWave;
    Location location;
    MS.Type type;
    int delay = 0;

    public MonsterSpawn(ArenaWave arenaWave, Location location, int delay, MS.Type type){
        this.location = location;
        this.arenaWave = arenaWave;
        this.delay = delay;
        this.type = type;
    }

    public void onTick(){
        if (this.delay > 0){
            this.delay -= 1;
            return;
        }
        if (this.ticks%17 == 0){
            this.spawnParticle();
        }
        if (this.ticks == 0){
            M.playSound(this.location, Sound.ENTITY_BLAZE_SHOOT);
        }
        this.ticks += 1;
    }

    private void spawnParticle(){
        M.getWorld().spawnParticle(Particle.ANGRY_VILLAGER , this.getParticleLocation(), 0, 0, 0, 0, 0);
        // ANGRY_VILLAGER, RAID_OMEN, TRIAL_OMEN
    }

    private Location getParticleLocation(){
        return location.clone().add(Vector.getRandom());
    }

    public Enmy spawn(){
        return MS.spawnMonster(this.arenaWave, this.location, this.type);
    }
}
