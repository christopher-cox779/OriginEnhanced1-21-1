package com.originrealms.enhanced.fabric.event;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

@Environment(EnvType.CLIENT)
public class ConfigEvents {
   public static final Event<ConfigEvents.Reload> RELOAD = EventFactory.createArrayBacked(ConfigEvents.Reload.class, (listeners) -> {
      return () -> {
         ConfigEvents.Reload[] var1 = listeners;
         int var2 = listeners.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            ConfigEvents.Reload listener = var1[var3];
            listener.onReload();
         }

      };
   });

   @FunctionalInterface
   @Environment(EnvType.CLIENT)
   public interface Reload {
      void onReload();
   }
}
