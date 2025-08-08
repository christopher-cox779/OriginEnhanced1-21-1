package com.originrealms.enhanced.core.mixin.resources;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2960;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin({class_2960.class})
public abstract class ResourceLocationMixin {
   @Inject(
      method = {"isValidPath"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void isValidPath(String string, CallbackInfoReturnable<Boolean> ci) {
      if (string.equalsIgnoreCase("fml|hs")) {
         ci.setReturnValue(true);
      }

   }
}
