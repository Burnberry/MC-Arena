package noppe.minecraft.arena.spellcasting.spells;

import noppe.minecraft.arena.entities.Plyer;
import noppe.minecraft.arena.helpers.M;
import noppe.minecraft.arena.spellcasting.Spell;
import org.bukkit.util.Vector;

import java.util.Arrays;

public class ZSpell extends Spell {

    public ZSpell(){
        super(Arrays.asList(
                new Vector(0, 0, 0),
                new Vector(1, 0, 0),
                new Vector(0, -1, 0),
                new Vector(1, -1, 0)
        ));
    }

    @Override
    public void cast(Plyer plyer) {
        M.print("Casting: Z");
        return;
    }

    @Override
    public String getName() {
        return "Z";
    }
}
