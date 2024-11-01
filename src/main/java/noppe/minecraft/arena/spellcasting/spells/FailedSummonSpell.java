package noppe.minecraft.arena.spellcasting.spells;

import noppe.minecraft.arena.entities.Plyer;
import noppe.minecraft.arena.helpers.M;
import noppe.minecraft.arena.spellcasting.Spell;
import noppe.minecraft.arena.spellcasting.playerSpells.FailedSummon;

public class FailedSummonSpell extends Spell {
    public FailedSummonSpell() {
        super(M.pentagram());
    }

    @Override
    public void cast(Plyer plyer) {
        new FailedSummon(this, plyer);
    }

    @Override
    public String getName() {
        return "Summon";
    }
}
