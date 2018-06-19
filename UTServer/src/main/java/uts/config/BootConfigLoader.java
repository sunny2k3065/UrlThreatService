package uts.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BootConfigLoader {
    public static Map<String, BootConfig> bootstrapConfig = new ConcurrentHashMap<>(1);

    public static BootConfig getBootstrapConfig(String bootConfLocation) {
        return bootstrapConfig.computeIfAbsent(bootConfLocation, value -> {
            Path configPath = Paths.get(bootConfLocation);
            if (!Files.exists(configPath)) {
                throw new IllegalArgumentException("File " + bootConfLocation + " doesn't exist.");
            }
            try (InputStream input = Files.newInputStream(configPath)) {
                return BootConfig.fromJson(input);
            } catch (IOException e) {
                throw new IllegalStateException("Unable to read the configuration from file [" + bootConfLocation + "].", e);
            }
        });
    }
}