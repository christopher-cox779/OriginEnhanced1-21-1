package com.originrealms.enhanced.core.bridge.fabric;

import java.util.Objects;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.class_1921;
import net.minecraft.class_2248;
import net.minecraft.class_304;
import net.minecraft.class_5601;
import net.minecraft.class_5607;

@Environment(EnvType.CLIENT)
public class RegistryBridgeImpl {
   public static void putRenderType(class_2248 block, class_1921 type) {
      BlockRenderLayerMap.INSTANCE.putBlock(block, type);
   }

   public static class_304 registerKeybinding(class_304 key) {
      return KeyBindingHelper.registerKeyBinding(key);
   }

   public static void registerLayerDefinition(class_5601 layerLocation, Supplier<class_5607> supplier) {
      Objects.requireNonNull(supplier);
      EntityModelLayerRegistry.registerModelLayer(layerLocation, supplier::get);
   }
}
