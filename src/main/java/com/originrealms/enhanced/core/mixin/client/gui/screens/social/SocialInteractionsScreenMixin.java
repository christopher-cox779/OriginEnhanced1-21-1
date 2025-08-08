package com.originrealms.enhanced.core.mixin.client.gui.screens.social;

import com.originrealms.enhanced.core.bridge.Platform;
import com.originrealms.enhanced.core.feature.OriginFeature;
import com.originrealms.enhanced.core.util.OriginUtil;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_5522;
import net.minecraft.class_634;
import net.minecraft.class_640;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.CLIENT)
@Mixin({class_5522.class})
public class SocialInteractionsScreenMixin {
   @Redirect(
      method = {"updateServerLabel"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/multiplayer/ClientPacketListener;getOnlinePlayers()Ljava/util/Collection;"
)
   )
   public Collection<class_640> getOnlinePlayers(class_634 listener) {
      return Platform.getConfig().isFeatureEnabled(OriginFeature.SOCIAL_MENU_FILTER) && OriginUtil.isConnected() ? (Collection)listener.method_2880().stream().filter((info) -> {
         return !info.method_2966().getName().startsWith(":") && !info.method_2966().getName().equals("Herobrine");
      }).collect(Collectors.toList()) : listener.method_2880();
   }

   @Redirect(
      method = {"showPage"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/multiplayer/ClientPacketListener;getOnlinePlayerIds()Ljava/util/Collection;"
)
   )
   public Collection<UUID> getOnlinePlayerIds(class_634 listener) {
      return Platform.getConfig().isFeatureEnabled(OriginFeature.SOCIAL_MENU_FILTER) && OriginUtil.isConnected() ? (Collection)listener.method_31363().stream().filter((uuid) -> {
         class_640 info = listener.method_2871(uuid);
         if (info == null) {
            return true;
         } else {
            return !info.method_2966().getName().startsWith(":") && !info.method_2966().getName().equals("Herobrine");
         }
      }).collect(Collectors.toList()) : listener.method_31363();
   }
}
