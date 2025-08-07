package com.originrealms.enhanced.core.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_329;
import net.minecraft.class_5455;

@Environment(EnvType.CLIENT)
public class MinecraftUtil {
   public static void displayTitle(class_2561 title, class_2561 subtitle, int fadeIn, int stay, int fadeOut) {
      class_329 hud = class_310.method_1551().field_1705;
      hud.method_34004(title);
      hud.method_34002(subtitle);
      hud.method_34001(fadeIn, stay, fadeOut);
   }

   public static class_5455 getRegistryAccess() {
      if (class_310.method_1551().field_1687 == null) {
         throw new IllegalStateException("Registry accessed too soon");
      } else {
         return class_310.method_1551().field_1687.method_30349();
      }
   }
}
