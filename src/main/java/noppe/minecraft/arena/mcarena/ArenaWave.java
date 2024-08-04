//package noppe.minecraft.arena.mcarena;
//
//import noppe.minecraft.arena.builder.Bld;
//import noppe.minecraft.arena.entities.Enmy;
//import noppe.minecraft.arena.entities.Plyer;
//import noppe.minecraft.arena.entities.monsters.MS;
//import noppe.minecraft.arena.entities.spawn.MonsterSpawn;
//import noppe.minecraft.arena.event.ArenaEventListener;
//import noppe.minecraft.arena.event.events.EventEntityDeath;
//import noppe.minecraft.arena.event.events.EventEntityRemove;
//import noppe.minecraft.arena.helpers.K;
//import noppe.minecraft.arena.helpers.M;
//import noppe.minecraft.arena.helpers.R;
//import noppe.minecraft.arena.location.Loc;
//import org.bukkit.GameMode;
//import org.bukkit.Location;
//import org.bukkit.Material;
//import org.bukkit.event.entity.EntityDeathEvent;
//import org.bukkit.event.entity.EntityRemoveEvent;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ArenaWave extends ArenaEventListener {
//    Arena arena;
//    ArenaGame arenaGame;
//
//    int ticks;
//    public List<Plyer> players = new ArrayList<>();
//    List<Enmy> monsters = new ArrayList<>();
//    List<MonsterSpawn> monsterSpawns = new ArrayList<>();
//    int rounds;
//    int roundSize;
//    enum WaveStateOld {
//        START,
//        SPAWNING,
//        BATTLE,
//        END
//    }
//    WaveStateOld waveState = WaveStateOld.START;
//
//    public ArenaWave(ArenaGame arenaGame){
//        this.arenaGame = arenaGame;
//        this.arena = arenaGame.arena;
//        Bld.boxHome(Loc.waveArena, Material.GILDED_BLACKSTONE, Material.BLACK_STAINED_GLASS, Material.TINTED_GLASS, 8, 8, 3);
//
//
//        this.ticks = 0;
//        this.players = this.arenaGame.players;
//        for (Plyer plyer: this.players){
//            plyer.player.teleport(Loc.waveArena);
////            M.setInventory(plyer, Inv.defaultArenaKit);
//            plyer.player.setGameMode(GameMode.ADVENTURE);
//        }
//        this.rounds = 2;
//        this.roundSize = 3;
//    }
//
//    public void onRemove(){
//        for (int i = this.monsters.size()-1; i >= 0; i--) {
//            this.monsters.get(i).remove();
//        }
//    }
//
//    public void onTick(){
//        this.ticks += 1;
//        for (MonsterSpawn monsterSpawn: this.monsterSpawns){
//            monsterSpawn.onTick();
//        }
//        for (int i=this.monsterSpawns.size()-1; i>=0; i--){
//            MonsterSpawn monsterSpawn = this.monsterSpawns.get(i);
//            if (monsterSpawn.ticks >= K.spawnTicks){
//                this.monsters.add(monsterSpawn.spawn());
//                this.monsterSpawns.remove(monsterSpawn);
//            }
//            else {
//                monsterSpawn.onTick();
//            }
//        }
//        for (Enmy enmy: this.monsters){
//            enmy.onTick();
//        }
//        this.updateState();
//    }
//
//    private void updateState(){
//        if (this.waveState == WaveStateOld.START){
//            this.startRound();
//            this.waveState = WaveStateOld.SPAWNING;
//        } else if (this.waveState == WaveStateOld.SPAWNING && this.monsterSpawns.isEmpty()) {
//            this.waveState = WaveStateOld.BATTLE;
//        } else if (this.waveState == WaveStateOld.BATTLE) {
//            if (this.rounds == 0 && this.monsters.isEmpty()){
//                this.arenaGame.onWaveEnd();
//            } else if (this.shouldStartRound()) {
//                this.waveState = WaveStateOld.SPAWNING;
//                this.startRound();
//            }
//        }
//    }
//
//    private void startRound(){
//        this.rounds -= 1;
//        for (int i = 0; i < this.roundSize; i++){
//            MS.Type type = MS.Type.ZOMBIE;
//            if (i==0){
//                type = MS.Type.SKELETON;
//            }
//            this.spawnMonster(20*i, type);
//        }
//    }
//
//    private boolean shouldStartRound(){
//        if (rounds <= 0){
//            return false;
//        }
//        return this.monsters.isEmpty();
//    }
//
//    private void spawnMonster(int delay, MS.Type type){
//        Location location = this.getMonsterSpawnLocation();
//        MonsterSpawn monsterSpawn = new MonsterSpawn(this, location, delay, type);
//        this.monsterSpawns.add(monsterSpawn);
////        Enmy enmy = new ArenaSkeleton(this, location);
////        this.monsters.add(enmy);
//    }
//
//    private Location getMonsterSpawnLocation(){
//        return Loc.waveArena.clone().add(R.rand.nextDouble()*5, 0, R.rand.nextDouble()*5);
//    }
//
//    @Override
//    public void onEntityDeath(EntityDeathEvent event, EventEntityDeath ev) {
//        if (ev.ent.isEnemy()){
//            Enmy enemy = (Enmy) ev.ent;
//            if (this.monsters.contains(enemy)){
//                int souls = enemy.souls;
//                this.arenaGame.collectSouls(souls);
//                Plyer killer = ev.plyerKiller;
//                if (killer == null) {
//                    killer = this.getDefaultKiller();
//                }
//                if (killer != null){
//                    killer.souls += souls;
//                    M.print(killer.getName() + " killed " + enemy.enemy.getName() + " + " + souls +  " souls | total: " + killer.souls);
//                }
//            }
//        }
//    }
//
//    @Override
//    public void onEntityRemove(EntityRemoveEvent event, EventEntityRemove ev){
//        if (ev.ent.isEnemy()){
//            Enmy enmy = (Enmy) ev.ent;
//            this.monsters.remove(enmy);
//        }
//    }
//
//    public Plyer getDefaultKiller(){
//        if (!this.players.isEmpty()){
//            return this.players.getFirst();
//        }
//        return null;
//    }
//
//    public boolean isArenaWave(){
//        return true;
//    }
//}
