package com.originrealms.enhanced.fabric.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.file.FileWatcher;
import com.google.common.collect.Maps;
import com.originrealms.enhanced.core.OriginConfig;
import com.originrealms.enhanced.core.OriginsEnhanced;
import com.originrealms.enhanced.core.feature.OriginFeature;
import com.originrealms.enhanced.fabric.event.ConfigEvents;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_156;

@Environment(EnvType.CLIENT)
public class OriginFabricConfig implements OriginConfig {
   public static final OriginFabricConfig INSTANCE = new OriginFabricConfig();
   public static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("originsenhanced.toml");
   private static final String CONFIG_VERSION = "3";
   private final boolean[] enabledFeatures = new boolean[OriginFeature.values().length];

   public void setup() {
      this.checkConfig(false);

      try {
         FileWatcher.defaultInstance().addWatch(CONFIG_PATH, () -> {
            this.checkConfig(true);
         });
      } catch (IOException var2) {
         OriginsEnhanced.LOGGER.error("Failed to initialize file watch. Your config will not auto-reload.", var2);
      }

   }

   private CommentedFileConfig read() {
      CommentedFileConfig config = (CommentedFileConfig)CommentedFileConfig.builder(CONFIG_PATH).sync().preserveInsertionOrder().autosave().onFileNotFound((file, cfgFormat) -> {
         Files.createDirectories(file.getParent());
         Files.createFile(file);
         cfgFormat.initEmptyFile(file);
         return false;
      }).build();
      config.load();
      return config;
   }

   private void checkConfig(boolean reload) {
      CommentedFileConfig config = this.read();
      this.migrate(config);
      OriginFeature.Category[] var3 = OriginFeature.Category.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         OriginFeature.Category category = var3[var5];
         OriginFeature[] var7 = category.getFeatures();
         int var8 = var7.length;

         for(int var9 = 0; var9 < var8; ++var9) {
            OriginFeature feature = var7[var9];
            String var10000 = category.toString().toLowerCase(Locale.ROOT);
            String path = var10000 + "." + feature.getName();
            String description = " " + String.join("\n ", feature.getDescriptionLines());
            if (config.get(path) == null) {
               config.set(path, feature.getDefaultValue());
            }

            if (config.getComment(path) == null || !config.getComment(path).equals(description)) {
               config.setComment(path, description);
            }

            this.enabledFeatures[feature.ordinal()] = (Boolean)config.getOrElse(path, feature.getDefaultValue());
         }
      }

      config.close();
      if (reload) {
         ((ConfigEvents.Reload)ConfigEvents.RELOAD.invoker()).onReload();
      }

   }

   private void migrate(CommentedFileConfig config) {
      String current = (String)config.get("version");
      if (current == null || !current.equals("3")) {
         Map<String, Object> configMap = (Map)class_156.method_654(Maps.newHashMap(), (map) -> {
            map.putAll(config.valueMap());
         });
         config.clear();
         Map<String, OriginFeature> migrationMap = (Map)class_156.method_654(Maps.newHashMap(), (map) -> {
         });
         migrationMap.forEach((path, feature) -> {
            if (configMap.containsKey(path)) {
               String var10000 = feature.getCategory().toString().toLowerCase(Locale.ROOT);
               String newPath = var10000 + "." + feature.getName();
               config.setComment(newPath, " " + String.join("\n ", feature.getDescriptionLines()));
               config.set(newPath, configMap.get(path));
            }

         });
         config.setComment("version", " Internal use only. Editing this line will wipe your config!");
         config.set("version", "3");
         migrationMap.clear();
         configMap.clear();
      }
   }

   public void setFeatureEnabled(OriginFeature feature, boolean enabled) {
      CommentedFileConfig config = this.read();
      config.set(feature.getCategory().toString().toLowerCase(Locale.ROOT) + "." + feature.getName(), enabled);
      this.checkConfig(true);
   }

   public boolean isFeatureEnabled(OriginFeature feature) {
      return this.enabledFeatures[feature.ordinal()];
   }
}
