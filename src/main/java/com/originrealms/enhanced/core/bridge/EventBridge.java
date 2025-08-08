package com.originrealms.enhanced.core.bridge;

import com.originrealms.enhanced.core.bridge.fabric.EventBridgeImpl;
import java.util.function.Consumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2901;
import net.minecraft.class_310;

@Environment(EnvType.CLIENT)
public final class EventBridge {
   public static void registerClientTickEnd(Consumer<class_310> event) {
      EventBridgeImpl.registerClientTickEnd(event);
   }

   public static void postDisconnect() {
      EventBridgeImpl.postDisconnect();
   }

   public static void registerDisconnect(Runnable event) {
      EventBridgeImpl.registerDisconnect(event);
   }

   public static void postLoginSuccess(class_2901 packet) {
      EventBridgeImpl.postLoginSuccess(packet);
   }

   public static void registerLoginSuccess(Consumer<class_2901> event) {
      EventBridgeImpl.registerLoginSuccess(event);
   }

   public static void registerConfigReload(Runnable event) {
      EventBridgeImpl.registerConfigReload(event);
   }
}
