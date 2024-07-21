package noppe.minecraft.arena.block;

import noppe.minecraft.arena.event.ArenaEventListener;
import noppe.minecraft.arena.helpers.M;
import org.bukkit.block.Block;

public class Blck {
    public String type;
    public Block block;
    public ArenaEventListener origin;

    Blck(ArenaEventListener origin, Block block){
        this.origin = origin;
        this.block = block;
        M.setMetaData(block, "wrapper", this);
    }
}
