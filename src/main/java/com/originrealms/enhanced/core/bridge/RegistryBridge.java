package com.originrealms.enhanced.core.bridge;

import com.originrealms.enhanced.core.bridge.fabric.RegistryBridgeImpl;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1921;
import net.minecraft.class_2248;
import net.minecraft.class_304;
import net.minecraft.class_5601;
import net.minecraft.class_5607;

@Environment(EnvType.CLIENT)
public final class RegistryBridge {
   public static void putRenderType(class_2248 block, class_1921 type) {
      RegistryBridgeImpl.putRenderType(block, type);
   }

   public static class_304 registerKeybinding(class_304 key) {
      return RegistryBridgeImpl.registerKeybinding(key);
   }

   public static void registerLayerDefinition(class_5601 layerLocation, Supplier<class_5607> supplier) {
      RegistryBridgeImpl.registerLayerDefinition(layerLocation, supplier);
   }
}
