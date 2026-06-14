package net.cengiz1.skyblock.level;

import net.cengiz1.skyblock.SkyblockPlugin;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.TreeMap;

/**
 * levels.yml dosyasını yükler ve puandan seviye hesaplar.
 * Her seviyeye ulaşmak için gereken TOPLAM puan tanımlıdır; tanımlı en yüksek
 * seviyeden sonrası 'auto' formülüyle üretilir.
 */
public class LevelManager {

    private final SkyblockPlugin plugin;

    private final TreeMap<Integer, Double> requirements = new TreeMap<>();
    private int maxDefinedLevel;
    private boolean autoEnabled;
    private double autoStep;
    private double autoMultiplier;

    // Güvenlik tavanı: sonsuz döngüyü önlemek için.
    private static final int LEVEL_CAP = 100000;

    public LevelManager(SkyblockPlugin plugin) {
        this.plugin = plugin;
        reload();
    }

    public void reload() {
        this.requirements.clear();

        File file = new File(plugin.getDataFolder(), "levels.yml");
        if (!file.exists())
            plugin.saveResource("levels.yml", false);

        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        ConfigurationSection section = config.getConfigurationSection("requirements");
        if (section != null) {
            for (String key : section.getKeys(false)) {
                try {
                    int level = Integer.parseInt(key);
                    if (level >= 1)
                        this.requirements.put(level, section.getDouble(key));
                } catch (NumberFormatException ignored) {
                }
            }
        }
        this.maxDefinedLevel = this.requirements.isEmpty() ? 0 : this.requirements.lastKey();

        this.autoEnabled = config.getBoolean("auto.enabled", true);
        this.autoStep = config.getDouble("auto.step", 5000);
        this.autoMultiplier = config.getDouble("auto.multiplier", 1.15);
    }

    /** Bir seviyeye ulaşmak için gereken toplam puan. */
    public double requiredPoints(int level) {
        if (level <= 0)
            return 0;
        Double explicit = this.requirements.get(level);
        if (explicit != null)
            return explicit;
        if (!this.autoEnabled || this.maxDefinedLevel == 0)
            return Double.MAX_VALUE;
        if (level <= this.maxDefinedLevel)
            return Double.MAX_VALUE; // tanımlı aralıkta boşluk varsa erişilemez

        double total = this.requirements.get(this.maxDefinedLevel);
        double increment = this.autoStep;
        for (int l = this.maxDefinedLevel + 1; l <= level; l++) {
            total += increment;
            increment *= this.autoMultiplier;
        }
        return total;
    }

    /** Verilen puana karşılık gelen seviye. */
    public int levelFromPoints(double points) {
        int level = 0;
        while (level < LEVEL_CAP) {
            double needed = requiredPoints(level + 1);
            if (needed == Double.MAX_VALUE || points < needed)
                break;
            level++;
        }
        return level;
    }

    /** Bir sonraki seviyeye geçmek için gereken toplam puan (yoksa -1). */
    public double pointsForNextLevel(int currentLevel) {
        double needed = requiredPoints(currentLevel + 1);
        return needed == Double.MAX_VALUE ? -1 : needed;
    }
}
