package elocindev.vrcombat.fabric.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.fabricmc.loader.api.FabricLoader;

public class ConfigBuilder {
    public static final Gson BUILDER = (new GsonBuilder()).setPrettyPrinting().create();
  
    public static final Path file = FabricLoader.getInstance().getConfigDir()
    .resolve("vr-combat.json");
    
    public static ConfigEntries loadConfig() {
      try {
          if (Files.notExists(file)) {
              ConfigEntries defaultConfig = new ConfigEntries();

              defaultConfig.enabled = true;

              String defaultJson = BUILDER.toJson(defaultConfig);
              Files.writeString(file, defaultJson);
          }

          return BUILDER.fromJson(Files.readString(file), ConfigEntries.class);

      } catch (IOException exception) {
          throw new RuntimeException(exception);
      }
  }
}
