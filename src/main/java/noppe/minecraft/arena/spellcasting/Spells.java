package noppe.minecraft.arena.spellcasting;

import noppe.minecraft.arena.helpers.M;
import noppe.minecraft.arena.spellcasting.spells.FourSpell;
import noppe.minecraft.arena.spellcasting.spells.NSpell;
import noppe.minecraft.arena.spellcasting.spells.USpell;
import noppe.minecraft.arena.spellcasting.spells.ZSpell;

import java.util.Arrays;
import java.util.List;

public class Spells {
    public static List<Spell> test = Arrays.asList(
            new FourSpell(),
            new NSpell(),
            new USpell(),
            new ZSpell()
    );

    public static void compareAll(){
        for (Spell spell1: Spells.test){
            for (Spell spell2: Spells.test){
                if (spell1 == spell2){
                    continue;
                }
                M.print(spell1.getName() + ", " + spell2.getName() + ": " + S.similarityError(spell1, spell2));
            }
        }
    }
}
