package net.cengiz1.uxmskyblock.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public final class SafeLocation {

    private SafeLocation() {
    }

    public static boolean isSafe(Location location) {
        if (location == null || location.getWorld() == null)
            return false;

        World world = location.getWorld();
        Block feet = world.getBlockAt(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        Block head = feet.getRelative(BlockFace.UP);
        Block ground = feet.getRelative(BlockFace.DOWN);

        if (!isPassable(feet) || !isPassable(head))
            return false;
        if (!isSolidSafe(ground))
            return false;

        if (isDangerous(feet) || isDangerous(head) || isDangerous(ground))
            return false;

        for (BlockFace face : new BlockFace[]{BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST}) {
            if (isDangerous(feet.getRelative(face)) || isDangerous(head.getRelative(face)))
                return false;
        }
        return true;
    }

    private static boolean isPassable(Block block) {
        Material type = block.getType();
        return block.isPassable() && !type.isSolid() && type != Material.LAVA && type != Material.WATER;
    }

    private static boolean isSolidSafe(Block block) {
        return block.getType().isSolid() && !isDangerous(block);
    }

    private static boolean isDangerous(Block block) {
        switch (block.getType()) {
            case LAVA:
            case FIRE:
            case SOUL_FIRE:
            case MAGMA_BLOCK:
            case CACTUS:
            case CAMPFIRE:
            case SOUL_CAMPFIRE:
            case SWEET_BERRY_BUSH:
            case WITHER_ROSE:
            case POWDER_SNOW:
            case END_PORTAL:
            case NETHER_PORTAL:
                return true;
            default:
                return false;
        }
    }
}
