package noppe.minecraft.arena.spellcasting.playerSpells;

import noppe.minecraft.arena.entities.Plyer;
import noppe.minecraft.arena.helpers.M;
import noppe.minecraft.arena.spellcasting.PlayerSpell;
import noppe.minecraft.arena.spellcasting.Spell;
import org.bukkit.Location;

public class Fireball extends PlayerSpell {
    public Fireball(Spell spell, Plyer plyer) {
        super(spell, plyer);
    }

    @Override
    public void onTick() {
        Location location = plyer.player.getEyeLocation().clone();
        location = location.add(location.getDirection().multiply(3));
        org.bukkit.entity.Fireball fireball =  plyer.player.launchProjectile(org.bukkit.entity.Fireball.class, location.getDirection());
        onSpellEnd();
    }
}
