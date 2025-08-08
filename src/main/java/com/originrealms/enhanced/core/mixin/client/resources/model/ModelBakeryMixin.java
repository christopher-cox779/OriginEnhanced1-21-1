package com.originrealms.enhanced.core.mixin.client.resources.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1088;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin({class_1088.class})
public abstract class ModelBakeryMixin {
   @Inject(
      method = {"loadModel"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void loadModel(class_2960 location, CallbackInfo ci) {
      if (!class_310.method_1551().method_22108()) {
         ci.cancel();
      }
   }
}
