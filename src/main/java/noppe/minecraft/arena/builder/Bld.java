package noppe.minecraft.arena.builder;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class Bld {
    public static void test(Location location, Material material){
        int length = 3;
        int width = 6;
        int height = 3;

        Bld.box(location, Material.AIR, length-1, width-1, height);
        Bld.platform(location, material, length, width);
        Bld.walls(location, material, length, width, height);
        Bld.platform(location.clone().add(0, height+1, 0), material, length, width);
    }

    public static void platform(Location location, Material material, int length, int width){
        Block center = location.getBlock();
        for (int x=-length; x<=length; x++){
            for (int z=-width; z<=width; z++){
                center.getRelative(x, 0, z).setType(material);
            }
        }
    }

    public static void walls(Location location, Material material, int length, int width, int height){
        Block center = location.getBlock();
        for (int y=0; y<height; y++){
            for (int x=-length; x<=length; x++){
                center.getRelative(x, y, width).setType(material);
                center.getRelative(x, y, -width).setType(material);
            }
            for (int z=-width; z<=width; z++){
                center.getRelative(length, y, z).setType(material);
                center.getRelative(-length, y, z).setType(material);
            }
        }
    }

    public static void box(Location location, Material material, int length, int width, int height){
        Block center = location.getBlock();
        for (int y=0; y<=height; y++){
            for (int x=-length; x<=length; x++){
                for (int z=-width; z<=width; z++){
                    center.getRelative(x, y, z).setType(material);
                }
            }
        }
    }

    public static void boxHome(Location location, Material floor, Material wall, Material ceiling, int length, int width, int height){
        Bld.box(location, Material.AIR, length-1, width-1, height);
        Bld.platform(location.clone().add(0, -1, 0), floor, length, width);
        Bld.walls(location, wall, length, width, height);
        Bld.platform(location.clone().add(0, height, 0), ceiling, length, width);
        location.getBlock().getRelative(0, -1, 0).setType(Material.OCHRE_FROGLIGHT);
    }
}
