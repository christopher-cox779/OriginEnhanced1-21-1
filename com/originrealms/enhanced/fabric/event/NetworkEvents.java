package com.originrealms.enhanced.fabric.event;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.class_2901;

@Environment(EnvType.CLIENT)
public class NetworkEvents {
   public static final Event<NetworkEvents.LoginSuccess> LOGIN_SUCCESS = EventFactory.createArrayBacked(NetworkEvents.LoginSuccess.class, (listeners) -> {
      return (packet) -> {
         NetworkEvents.LoginSuccess[] var2 = listeners;
         int var3 = listeners.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            NetworkEvents.LoginSuccess listener = var2[var4];
            listener.onLoginSuccess(packet);
         }

      };
   });
   public static final Event<NetworkEvents.Disconnect> DISCONNECT = EventFactory.createArrayBacked(NetworkEvents.Disconnect.class, (listeners) -> {
      return () -> {
         NetworkEvents.Disconnect[] var1 = listeners;
         int var2 = listeners.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            NetworkEvents.Disconnect listener = var1[var3];
            listener.onDisconnect();
         }

      };
   });

   @FunctionalInterface
   @Environment(EnvType.CLIENT)
   public interface Disconnect {
      void onDisconnect();
   }

   @FunctionalInterface
   @Environment(EnvType.CLIENT)
   public interface LoginSuccess {
      void onLoginSuccess(class_2901 var1);
   }
}
