package com.originrealms.enhanced.core;

import com.originrealms.enhanced.core.bridge.Platform;
import com.originrealms.enhanced.core.bridge.RegistryBridge;
import com.originrealms.enhanced.core.feature.GestureEnhancementsFeature;
import com.originrealms.enhanced.core.feature.KeyBindingFeature;
import com.originrealms.enhanced.core.feature.OriginFeature;
import com.originrealms.enhanced.core.feature.RichPresenceFeature;
import com.originrealms.enhanced.core.networking.NetworkHandler;
import com.originrealms.enhanced.core.server.OriginStateTracker;
import com.originrealms.enhanced.core.server.RemoteRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1921;
import net.minecraft.class_2246;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Environment(EnvType.CLIENT)
public class OriginsEnhanced {
   public static final String MOD_ID = "originsenhanced";
   public static final Logger LOGGER = LogManager.getLogger();
   private static RemoteRegistry registry;

   public static void setup() {
      registry = RemoteRegistry.fetch();
      OriginStateTracker.INSTANCE.setup();
      NetworkHandler.INSTANCE.setup();
      GestureEnhancementsFeature.INSTANCE.setup();
      KeyBindingFeature.INSTANCE.setup();
      RichPresenceFeature.INSTANCE.setup();
   }

   public static void postSetup() {
      if (Platform.getConfig().isFeatureEnabled(OriginFeature.BLOCK_ENHANCEMENTS)) {
         RegistryBridge.putRenderType(class_2246.field_10589, class_1921.method_23581());
      }

   }

   public static RemoteRegistry getRegistry() {
      return registry;
   }
}
