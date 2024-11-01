package noppe.minecraft.arena.spellcasting.playerSpells;

import noppe.minecraft.arena.entities.Plyer;
import noppe.minecraft.arena.helpers.M;
import noppe.minecraft.arena.spellcasting.PlayerSpell;
import noppe.minecraft.arena.spellcasting.Spell;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.SculkShrieker;
import org.bukkit.entity.Enemy;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Warden;
import org.bukkit.entity.Wither;

public class FailedSummon extends PlayerSpell {
    public FailedSummon(Spell spell, Plyer plyer) {
        super(spell, plyer);
    }

    @Override
    public void onTick() {
        spawnWarden();
        onSpellEnd();
    }

    public void spawnWarden(){
        Location location = plyer.player.getLocation();
        Block block = location.getBlock().getRelative(0, -3, 0);
        block.setType(Material.SCULK_SHRIEKER);
        SculkShrieker shrieker = (SculkShrieker)block.getState();
        shrieker.setWarningLevel(4);
        shrieker.tryShriek(plyer.player);
    }
}
