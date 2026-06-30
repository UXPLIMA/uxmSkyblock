package net.cengiz1.uxmskyblock.island;

import net.cengiz1.uxmskyblock.UxmSkyblockPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Computes the island leaderboard, ordered by level then points. Works for every
 * island in the cache, so it functions the same whether the world is a classic
 * skyblock world or a Chunklock-style natural world.
 */
public class TopService {

    private final UxmSkyblockPlugin plugin;

    public TopService(UxmSkyblockPlugin plugin) {
        this.plugin = plugin;
    }

    public List<Island> getTop(int limit) {
        List<Island> all = new ArrayList<>(plugin.getIslandManager().getAllIslands());
        all.sort((a, b) -> {
            int byLevel = Integer.compare(b.getLevel(), a.getLevel());
            return byLevel != 0 ? byLevel : Double.compare(b.getPoints(), a.getPoints());
        });
        if (limit > 0 && all.size() > limit)
            return new ArrayList<>(all.subList(0, limit));
        return all;
    }

    /** 1-based rank of an island in the leaderboard, or -1 if it has none. */
    public int getRank(Island island) {
        if (island == null)
            return -1;
        List<Island> all = getTop(0);
        for (int i = 0; i < all.size(); i++)
            if (all.get(i).getUniqueId().equals(island.getUniqueId()))
                return i + 1;
        return -1;
    }
}
