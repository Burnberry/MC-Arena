package noppe.minecraft.arena.entities.monsters;

import noppe.minecraft.arena.entities.Enmy;
import noppe.minecraft.arena.event.ArenaEventListener;
import noppe.minecraft.arena.event.events.EventEntityDamage;
import noppe.minecraft.arena.helpers.K;
import noppe.minecraft.arena.helpers.M;
import noppe.minecraft.arena.item.Gear;
import org.bukkit.Location;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class ArenaSkeleton extends Enmy {
    boolean chargeUpped = false;

    public ArenaSkeleton(ArenaEventListener origin, Location location) {
        super(origin, (Skeleton) M.spawnEntity(origin, location, Skeleton.class));
        this.getSkeleton().getEquipment().setItemInMainHand(Gear.bow);
    }

//    public void onTick(){
//        super.onTick();
//        if (this.getSkeleton().getItemInUse() != null){
//            if (!this.chargeUpped){
//                this.chargeUpped = true;
//                this.getSkeleton().setItemInUseTicks(K.bowShootTicks+200);
//            }
//        }
//    }

    public void onEntityDamage(EntityDamageEvent event, EventEntityDamage ev){
        this.resetWeaponCharge();
    }

    public Skeleton getSkeleton(){
        return (Skeleton) this.entity;
    }

    public void resetWeaponCharge(){
        ItemStack mainHand = this.getSkeleton().getEquipment().getItemInMainHand();
        this.getSkeleton().getEquipment().setItemInMainHand(null);
        this.getSkeleton().getEquipment().setItemInMainHand(mainHand);
    }

}
