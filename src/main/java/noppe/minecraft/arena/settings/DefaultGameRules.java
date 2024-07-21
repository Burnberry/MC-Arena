package noppe.minecraft.arena.settings;

import org.bukkit.GameRule;
import org.bukkit.World;

public class DefaultGameRules {
    public static void SetDefaultGameRules(World world){
        world.setGameRule(GameRule.BLOCK_EXPLOSION_DROP_DECAY, false);
        world.setGameRule(GameRule.DISABLE_RAIDS, true);
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
        world.setGameRule(GameRule.DO_ENTITY_DROPS, false);
        world.setGameRule(GameRule.DO_FIRE_TICK, false);
        world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
        world.setGameRule(GameRule.DO_INSOMNIA, false);
        world.setGameRule(GameRule.DO_MOB_LOOT, false);
        world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        world.setGameRule(GameRule.DO_PATROL_SPAWNING, false);
        world.setGameRule(GameRule.DO_TILE_DROPS, false);
        world.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
        world.setGameRule(GameRule.DO_VINES_SPREAD, false);
        world.setGameRule(GameRule.DO_WARDEN_SPAWNING, false);
        world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        world.setGameRule(GameRule.KEEP_INVENTORY, true);
        world.setGameRule(GameRule.MOB_EXPLOSION_DROP_DECAY, false);
        world.setGameRule(GameRule.MOB_GRIEFING, false);
        world.setGameRule(GameRule.NATURAL_REGENERATION, false);
        world.setGameRule(GameRule.PROJECTILES_CAN_BREAK_BLOCKS, false);
        world.setGameRule(GameRule.TNT_EXPLOSION_DROP_DECAY, false);
    }
}
