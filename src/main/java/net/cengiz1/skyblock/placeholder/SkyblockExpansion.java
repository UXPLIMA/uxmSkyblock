package net.cengiz1.skyblock.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.cengiz1.skyblock.SkyblockPlugin;
import org.bukkit.OfflinePlayer;

public class SkyblockExpansion extends PlaceholderExpansion {

    private final SkyblockPlugin plugin;

    public SkyblockExpansion(SkyblockPlugin plugin) {
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
        return SkyblockPlaceholders.resolve(plugin, player, params);
    }
}
