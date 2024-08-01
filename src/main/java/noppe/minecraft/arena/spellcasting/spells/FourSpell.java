package noppe.minecraft.arena.spellcasting.spells;

import noppe.minecraft.arena.entities.Plyer;
import noppe.minecraft.arena.helpers.M;
import noppe.minecraft.arena.spellcasting.Spell;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;

public class FourSpell extends Spell {
    public FourSpell() {
        super(Arrays.asList(
                new Vector(0, 0, 0),
                new Vector(0, 1, 0),
                new Vector(-0.5, 0.25, 0),
                new Vector(0.1, 0.25, 0)
        ));
    }

    @Override
    public void cast(Plyer plyer) {
        M.print("Casting: 4");
    }

    @Override
    public String getName() {
        return "4";
    }
}
