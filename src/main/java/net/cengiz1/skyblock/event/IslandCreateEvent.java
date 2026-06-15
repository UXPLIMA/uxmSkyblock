package net.cengiz1.skyblock.event;

import net.cengiz1.skyblock.island.Island;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Fired (on the main thread) right after a new island has been registered and
 * the owner teleported. Lets modules react to island creation, e.g. the
 * Chunklock module sets up its per-island locked-chunk state.
 */
public class IslandCreateEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Island island;

    public IslandCreateEvent(Island island) {
        this.island = island;
    }

    public Island getIsland() {
        return island;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
