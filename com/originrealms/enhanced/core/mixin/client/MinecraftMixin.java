package com.originrealms.enhanced.core.mixin.client;

import com.originrealms.enhanced.core.bridge.EventBridge;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;
import net.minecraft.class_437;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin({class_310.class})
public abstract class MinecraftMixin {
   @Inject(
      method = {"clearClientLevel"},
      at = {@At("HEAD")}
   )
   public void clearLevel(class_437 screen, CallbackInfo ci) {
      EventBridge.postDisconnect();
   }
}
