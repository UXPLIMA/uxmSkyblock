package net.cengiz1.skyblock.event;

import net.cengiz1.skyblock.island.Island;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Fired (on the main thread) when an island is being deleted, before its region
 * is cleared. Lets modules clean up per-island state.
 */
public class IslandDeleteEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Island island;

    public IslandDeleteEvent(Island island) {
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
