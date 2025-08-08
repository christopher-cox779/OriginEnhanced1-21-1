package com.originrealms.enhanced.core.mixin.client.multiplayer;

import com.originrealms.enhanced.core.feature.AuctionPingFeature;
import com.originrealms.enhanced.core.util.OriginUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_634;
import net.minecraft.class_7439;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin({class_634.class})
public abstract class ClientPacketListenerMixin {
   @Inject(
      method = {"handleSystemChat"},
      at = {@At("HEAD")}
   )
   public void handleChat(class_7439 packet, CallbackInfo ci) {
      if (OriginUtil.isConnected()) {
         AuctionPingFeature.INSTANCE.handle(packet.comp_763());
      }
   }
}
