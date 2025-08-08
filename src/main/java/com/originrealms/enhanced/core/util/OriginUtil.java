package com.originrealms.enhanced.core.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.originrealms.enhanced.core.bridge.Platform;
import com.originrealms.enhanced.core.networking.NetworkHandler;
import com.originrealms.enhanced.core.util.json.PatternTypeAdapter;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.regex.Pattern;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_5455;
import net.minecraft.class_9279;
import net.minecraft.class_2561.class_2562;
import org.apache.commons.lang3.StringUtils;

@Environment(EnvType.CLIENT)
public class OriginUtil {
   public static final ScheduledExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor();
   public static final Gson GSON;

   public static boolean isConnected() {
      return Platform.isDevelopmentEnvironment() || NetworkHandler.INSTANCE.isHandshakeComplete();
   }

   public static Optional<String> getRegistryKey(class_9279 data, class_5455 registryAccess) {
      class_2487 tag = data.method_57461();
      if (tag.method_10573("CustomBlock", 8)) {
         return Optional.of(tag.method_10558("CustomBlock"));
      } else {
         class_2487 values = tag.method_10562("PublicBukkitValues");
         if (values.method_10573("xcore:item-registry-key", 8)) {
            return Optional.of(values.method_10558("xcore:item-registry-key"));
         } else if (values.method_10577("Cosmetic")) {
            class_2487 display = tag.method_10562("display");
            if (display.method_10573("Name", 8)) {
               try {
                  class_2561 component = class_2562.method_10877(tag.method_10562("display").method_10558("Name"), registryAccess);
                  if (component != null) {
                     return Optional.of(StringUtils.normalizeSpace(component.getString().toLowerCase(Locale.ROOT).replaceAll("[^\\x00-\\x7F]", "")).replace(' ', '_'));
                  }
               } catch (JsonParseException var6) {
               }
            }

            return Optional.of("cosmetic");
         } else {
            return Optional.empty();
         }
      }
   }

   static {
      GSON = (new GsonBuilder()).setPrettyPrinting().registerTypeAdapter(Pattern.class, PatternTypeAdapter.INSTANCE).create();
   }
}
