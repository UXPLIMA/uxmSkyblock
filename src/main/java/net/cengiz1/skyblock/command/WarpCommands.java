package net.cengiz1.skyblock.command;

import net.cengiz1.skyblock.SkyblockPlugin;
import net.cengiz1.skyblock.island.Island;
import net.cengiz1.skyblock.island.IslandPermission;
import net.cengiz1.skyblock.util.SafeLocation;
import org.bukkit.entity.Player;

public class WarpCommands extends CommandHandler {

    public WarpCommands(SkyblockPlugin plugin) {
        super(plugin);
    }

    public void warp(Player player, String targetName) {
        if (targetName == null) {
            openWarpMenu(player);
            return;
        }
        plugin.getWarpService().warpByName(player, targetName);
    }

    public void openWarpMenu(Player player) {
        if (plugin.getMenuManager().has("warp"))
            plugin.getMenuManager().open(player, "warp", null);
        else
            plugin.getMessages().send(player, "unknown-subcommand");
    }

    public void setWarp(Player player) {
        Island island = requirePermission(player, IslandPermission.SET_WARP);
        if (island == null)
            return;
        if (plugin.getIslandManager().getIslandAt(player.getLocation()) != island) {
            plugin.getMessages().send(player, "must-be-on-island");
            return;
        }
        if (!SafeLocation.isSafe(player.getLocation())) {
            plugin.getMessages().send(player, "warp-set-unsafe");
            return;
        }
        island.setWarp(player.getLocation());
        plugin.getIslandManager().saveAsync(island);
        plugin.getMessages().send(player, "warp-set");
    }

    public void delWarp(Player player) {
        Island island = requirePermission(player, IslandPermission.SET_WARP);
        if (island == null)
            return;
        if (!island.hasWarp()) {
            plugin.getMessages().send(player, "warp-none");
            return;
        }
        island.clearWarp();
        plugin.getIslandManager().saveAsync(island);
        plugin.getMessages().send(player, "warp-removed");
    }
}
