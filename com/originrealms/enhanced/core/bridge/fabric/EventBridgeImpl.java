package com.originrealms.enhanced.core.bridge.fabric;

import com.originrealms.enhanced.fabric.event.ConfigEvents;
import com.originrealms.enhanced.fabric.event.NetworkEvents;
import java.util.Objects;
import java.util.function.Consumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.Event;
import net.minecraft.class_2901;
import net.minecraft.class_310;

@Environment(EnvType.CLIENT)
public class EventBridgeImpl {
   public static void registerClientTickEnd(Consumer<class_310> event) {
      Event var10000 = ClientTickEvents.END_CLIENT_TICK;
      Objects.requireNonNull(event);
      var10000.register(event::accept);
   }

   public static void postDisconnect() {
      ((NetworkEvents.Disconnect)NetworkEvents.DISCONNECT.invoker()).onDisconnect();
   }

   public static void registerDisconnect(Runnable event) {
      Event var10000 = NetworkEvents.DISCONNECT;
      Objects.requireNonNull(event);
      var10000.register(event::run);
   }

   public static void postLoginSuccess(class_2901 packet) {
      ((NetworkEvents.LoginSuccess)NetworkEvents.LOGIN_SUCCESS.invoker()).onLoginSuccess(packet);
   }

   public static void registerLoginSuccess(Consumer<class_2901> event) {
      Event var10000 = NetworkEvents.LOGIN_SUCCESS;
      Objects.requireNonNull(event);
      var10000.register(event::accept);
   }

   public static void registerConfigReload(Runnable event) {
      Event var10000 = ConfigEvents.RELOAD;
      Objects.requireNonNull(event);
      var10000.register(event::run);
   }
}
