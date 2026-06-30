package net.cengiz1.uxmskyblock.module;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Parsed contents of a module's {@code module.yml} (a mini plugin.yml living at
 * the root of the module jar). Required keys: {@code name}, {@code main}.
 */
public final class ModuleDescriptor {

    private final String name;
    private final String main;
    private final String version;

    private ModuleDescriptor(String name, String main, String version) {
        this.name = name;
        this.main = main;
        this.version = version;
    }

    static ModuleDescriptor read(InputStream moduleYml) {
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(
                new InputStreamReader(moduleYml, StandardCharsets.UTF_8));

        String name = yaml.getString("name");
        String main = yaml.getString("main");
        String version = yaml.getString("version", "1.0.0");

        if (name == null || name.isBlank())
            throw new IllegalArgumentException("module.yml is missing 'name'");
        if (main == null || main.isBlank())
            throw new IllegalArgumentException("module.yml is missing 'main'");

        return new ModuleDescriptor(name.trim(), main.trim(), version);
    }

    public String getName() {
        return name;
    }

    public String getMain() {
        return main;
    }

    public String getVersion() {
        return version;
    }
}
