package noppe.minecraft.arena.spellcasting.spells;

import noppe.minecraft.arena.entities.Plyer;
import noppe.minecraft.arena.spellcasting.Spell;
import noppe.minecraft.arena.spellcasting.playerSpells.ChainLightning;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;

public class ChainLightningSpell extends Spell {
    public ChainLightningSpell() {
        super(Arrays.asList(
                new Vector(0, 0, 0),
                new Vector(-1, -1, 0),
                new Vector(1, -2, 0),
                new Vector(0, -3, 0),
                new Vector(0, 0, 0)
        ));
    }

    @Override
    public void cast(Plyer plyer) {
        new ChainLightning(this, plyer);
    }

    @Override
    public String getName() {
        return "Chain Lightning";
    }
}
