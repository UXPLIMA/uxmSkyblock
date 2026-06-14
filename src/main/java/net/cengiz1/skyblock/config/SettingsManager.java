package net.cengiz1.skyblock.config;

import net.cengiz1.skyblock.SkyblockPlugin;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SettingsManager {

    private final SkyblockPlugin plugin;

    private String worldName;
    private int islandHeight;
    private int islandDistance;
    private int islandSize;

    private int maxConcurrentCreations;
    private int creationThreads;

    private String commandName;
    private List<String> commandAliases = new ArrayList<>();
    private Map<String, List<String>> subcommandAliases = new LinkedHashMap<>();
    private int inviteExpireSeconds;
    private boolean economyEnabled;

    private String storageType;
    private String host;
    private int port;
    private String database;
    private String username;
    private String password;
    private boolean useSsl;

    public SettingsManager(SkyblockPlugin plugin) {
        this.plugin = plugin;
        reload();
    }

    public void reload() {
        FileConfiguration config = plugin.getConfig();

        this.worldName = config.getString("world.name", "islands");
        this.islandHeight = config.getInt("world.island-height", 100);
        this.islandDistance = config.getInt("world.island-distance", 250);
        this.islandSize = config.getInt("world.island-size", 100);

        this.maxConcurrentCreations = Math.max(1, config.getInt("creation.max-concurrent", 3));
        this.creationThreads = Math.max(1, config.getInt("creation.threads", 3));

        this.commandName = config.getString("command.name", "ada").toLowerCase();
        this.commandAliases = config.getStringList("command.aliases");
        this.inviteExpireSeconds = Math.max(10, config.getInt("command.invite-expire-seconds", 120));
        this.economyEnabled = config.getBoolean("economy.enabled", true);

        this.subcommandAliases = new LinkedHashMap<>();
        ConfigurationSection subs = config.getConfigurationSection("command.subcommands");
        if (subs != null) {
            for (String key : subs.getKeys(false)) {
                List<String> aliases = subs.getStringList(key);
                if (aliases.isEmpty())
                    aliases.add(key);
                this.subcommandAliases.put(key.toLowerCase(), aliases);
            }
        }

        this.storageType = config.getString("storage.type", "sqlite").toLowerCase();
        this.host = config.getString("storage.mysql.host", "localhost");
        this.port = config.getInt("storage.mysql.port", 3306);
        this.database = config.getString("storage.mysql.database", "skyblock");
        this.username = config.getString("storage.mysql.username", "root");
        this.password = config.getString("storage.mysql.password", "");
        this.useSsl = config.getBoolean("storage.mysql.ssl", false);
    }

    public String getWorldName() {
        return worldName;
    }

    public int getIslandHeight() {
        return islandHeight;
    }

    public int getIslandDistance() {
        return islandDistance;
    }

    public int getIslandSize() {
        return islandSize;
    }

    public int getMaxConcurrentCreations() {
        return maxConcurrentCreations;
    }

    public int getCreationThreads() {
        return creationThreads;
    }

    public String getCommandName() {
        return commandName;
    }

    public List<String> getCommandAliases() {
        return commandAliases;
    }

    public Map<String, List<String>> getSubcommandAliases() {
        return subcommandAliases;
    }

    public int getInviteExpireSeconds() {
        return inviteExpireSeconds;
    }

    public boolean isEconomyEnabled() {
        return economyEnabled;
    }

    public String getStorageType() {
        return storageType;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isUseSsl() {
        return useSsl;
    }
}
