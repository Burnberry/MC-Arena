package noppe.minecraft.arena.spellcasting.playerSpells;

import noppe.minecraft.arena.entities.Plyer;
import noppe.minecraft.arena.helpers.M;
import noppe.minecraft.arena.spellcasting.PlayerSpell;
import noppe.minecraft.arena.spellcasting.Spell;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Heal extends PlayerSpell {
    double health = 4;

    public Heal(Spell spell, Plyer plyer) {
        super(spell, plyer);
    }

    @Override
    public void onTick() {
        plyer.heal(health);
        plyer.player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20*60, 1));
        plyer.player.playSound(plyer.player.getEyeLocation(), Sound.BLOCK_AMETHYST_BLOCK_CHIME, 10, 1);
        onSpellEnd();
    }
}
