package com.originrealms.enhanced.core.mixin.client.multiplayer;

import com.originrealms.enhanced.core.bridge.EventBridge;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2901;
import net.minecraft.class_635;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin({class_635.class})
public abstract class ClientHandshakePacketListenerImplMixin {
   @Inject(
      method = {"handleGameProfile"},
      at = {@At("TAIL")}
   )
   public void handleGameProfile(class_2901 packet, CallbackInfo ci) {
      EventBridge.postLoginSuccess(packet);
   }
}
