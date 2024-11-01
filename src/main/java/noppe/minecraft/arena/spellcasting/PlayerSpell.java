package noppe.minecraft.arena.spellcasting;

import noppe.minecraft.arena.entities.Plyer;
import noppe.minecraft.arena.helpers.M;

public abstract class PlayerSpell {
    public Plyer plyer;
    public Spell spell;

    public PlayerSpell(Spell spell, Plyer plyer){
        this.spell = spell;
        this.plyer = plyer;
        this.plyer.playerSpell = this;
        M.print("Casting " + spell.getName());
    }

    public abstract void onTick();

    public void onSpellEnd(){
        if (plyer.playerSpell == this){
            plyer.playerSpell = null;
        }
    }
}
