package com.originrealms.enhanced.core.bridge;

import com.originrealms.enhanced.core.OriginConfig;
import com.originrealms.enhanced.core.bridge.fabric.PlatformImpl;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public final class Platform {
   public static <T> T safeAssertionError() {
      throw new AssertionError();
   }

   public static String getDisplayVersion() {
      String var10000 = getVersion();
      return var10000 + (isDevelopmentEnvironment() ? "-dev" : "");
   }

   public static String getName() {
      return PlatformImpl.getName();
   }

   public static String getVersion() {
      return PlatformImpl.getVersion();
   }

   @NotNull
   public static OriginConfig getConfig() {
      return PlatformImpl.getConfig();
   }

   public static boolean isDevelopmentEnvironment() {
      return PlatformImpl.isDevelopmentEnvironment();
   }
}
