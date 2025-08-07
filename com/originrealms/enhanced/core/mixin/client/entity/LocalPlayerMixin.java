package com.originrealms.enhanced.core.mixin.client.entity;

import com.originrealms.enhanced.core.util.OriginUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.CLIENT)
@Mixin({class_746.class})
public abstract class LocalPlayerMixin {
   @Shadow
   protected abstract boolean method_3134();

   @Redirect(
      method = {"serverAiStep"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/player/LocalPlayer;isControlledCamera()Z"
)
   )
   public boolean overrideGesture(class_746 instance) {
      boolean current = this.method_3134();
      if (!OriginUtil.isConnected()) {
         return current;
      } else {
         return current || instance.method_5765();
      }
   }
}
