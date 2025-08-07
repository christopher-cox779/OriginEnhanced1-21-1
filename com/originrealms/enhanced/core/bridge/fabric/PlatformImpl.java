package com.originrealms.enhanced.core.bridge.fabric;

import com.originrealms.enhanced.core.OriginConfig;
import com.originrealms.enhanced.fabric.OriginsEnhancedFabric;
import com.originrealms.enhanced.fabric.config.OriginFabricConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;

@Environment(EnvType.CLIENT)
public class PlatformImpl {
   public static String getName() {
      return OriginsEnhancedFabric.METADATA == null ? "Origins Enhanced" : OriginsEnhancedFabric.METADATA.getName();
   }

   public static String getVersion() {
      return OriginsEnhancedFabric.METADATA == null ? "NONE" : OriginsEnhancedFabric.METADATA.getVersion().getFriendlyString();
   }

   public static OriginConfig getConfig() {
      return OriginFabricConfig.INSTANCE;
   }

   public static boolean isDevelopmentEnvironment() {
      return FabricLoader.getInstance().isDevelopmentEnvironment();
   }
}
