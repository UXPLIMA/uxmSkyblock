package net.cengiz1.uxmskyblock.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.cengiz1.uxmskyblock.UxmSkyblockPlugin;
import org.bukkit.OfflinePlayer;

public class UxmSkyblockExpansion extends PlaceholderExpansion {

    private final UxmSkyblockPlugin plugin;

    public UxmSkyblockExpansion(UxmSkyblockPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getIdentifier() {
        return "skyblock";
    }

    @Override
    public String getAuthor() {
        return plugin.getDescription().getAuthors().isEmpty()
                ? "cengiz1x" : plugin.getDescription().getAuthors().get(0);
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        return UxmSkyblockPlaceholders.resolve(plugin, player, params);
    }
}
