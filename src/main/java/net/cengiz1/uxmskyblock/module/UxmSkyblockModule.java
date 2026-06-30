package net.cengiz1.uxmskyblock.module;

/**
 * Implemented by the main class of every uxmSkyblock module jar dropped into
 * {@code plugins/uxmSkyblock/modules/}. The implementing class must have a public
 * no-argument constructor; the {@link ModuleManager} instantiates it, calls
 * {@link #onLoad(ModuleContext)} once with the module's context and then
 * {@link #onEnable()}. On shutdown {@link #onDisable()} is called and every
 * listener/command registered through the context is removed automatically.
 */
public interface UxmSkyblockModule {

    /**
     * Called once right after instantiation, before {@link #onEnable()}.
     * Store the context here; it is the module's only handle to the core API.
     */
    default void onLoad(ModuleContext context) {
    }

    /**
     * Called when the module should start: register listeners/commands, load
     * data, create worlds, etc.
     */
    void onEnable();

    /**
     * Called when the core plugin disables. Persist state here.
     */
    default void onDisable() {
    }
}
