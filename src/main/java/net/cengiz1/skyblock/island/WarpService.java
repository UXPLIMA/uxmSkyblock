package net.cengiz1.skyblock.island;

import net.cengiz1.skyblock.SkyblockPlugin;
import net.cengiz1.skyblock.proxy.ProxyManager;
import net.cengiz1.skyblock.util.SafeLocation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WarpService {

    private final SkyblockPlugin plugin;

    public WarpService(SkyblockPlugin plugin) {
        this.plugin = plugin;
    }

    public List<Island> getWarps() {
        List<Island> result = new ArrayList<>();
        for (Island island : plugin.getIslandManager().getAllIslands())
            if (island.hasWarp())
                result.add(island);
        result.sort((a, b) -> {
            int byLevel = Integer.compare(b.getLevel(), a.getLevel());
            return byLevel != 0 ? byLevel : Double.compare(b.getPoints(), a.getPoints());
        });
        return result;
    }

    public void openWarpMenuOrWarp(Player viewer, Location signLocation) {
        Island island = plugin.getIslandManager().getIslandAt(signLocation);
        if (island != null && island.hasWarp()) {
            warp(viewer, island);
            return;
        }
        if (plugin.getMenuManager().has("warp"))
            plugin.getMenuManager().open(viewer, "warp", null);
    }

    public void warpByName(Player viewer, String targetName) {
        OfflinePlayer target = resolveOffline(targetName);
        if (target == null) {
            plugin.getMessages().send(viewer, "player-not-found", "{player}", targetName);
            return;
        }
        Island island = plugin.getIslandManager().getByOwner(target.getUniqueId());
        if (island == null || !island.hasWarp()) {
            plugin.getMessages().send(viewer, "warp-no-warp", "{player}", targetName);
            return;
        }
        warp(viewer, island);
    }

    public void warpToOwner(Player viewer, UUID ownerId) {
        Island island = plugin.getIslandManager().getByOwner(ownerId);
        if (island == null || !island.hasWarp()) {
            plugin.getMessages().send(viewer, "warp-no-warp", "{player}", nameOf(ownerId));
            return;
        }
        warp(viewer, island);
    }

    public void warp(Player viewer, Island island) {
        UUID playerId = viewer.getUniqueId();
        if (island.isBanned(playerId)) {
            plugin.getMessages().send(viewer, "visit-banned");
            return;
        }
        if (!island.hasWarp()) {
            plugin.getMessages().send(viewer, "warp-no-warp", "{player}", nameOf(island.getOwner()));
            return;
        }

        ProxyManager proxy = plugin.getProxyManager();
        if (proxy != null && proxy.handleWarpTeleport(viewer, island))
            return;

        World world = plugin.getWorldManager().getWorld();
        Location warp = island.getWarp(world);
        if (!SafeLocation.isSafe(warp)) {
            plugin.getMessages().send(viewer, "warp-unsafe");
            return;
        }
        plugin.getMessages().send(viewer, "warping", "{player}", nameOf(island.getOwner()));
        plugin.getIslandManager().teleportToWarp(viewer, island);
    }

    private OfflinePlayer resolveOffline(String name) {
        Player online = Bukkit.getPlayerExact(name);
        if (online != null)
            return online;
        OfflinePlayer offline = Bukkit.getOfflinePlayer(name);
        return offline.hasPlayedBefore() ? offline : null;
    }

    private String nameOf(UUID id) {
        String name = Bukkit.getOfflinePlayer(id).getName();
        return name != null ? name : id.toString().substring(0, 8);
    }
}
