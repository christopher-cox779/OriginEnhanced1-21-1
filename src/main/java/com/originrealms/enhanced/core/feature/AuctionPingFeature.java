package com.originrealms.enhanced.core.feature;

import com.originrealms.enhanced.core.bridge.Platform;
import com.originrealms.enhanced.core.util.OriginUtil;
import com.originrealms.enhanced.core.util.TextUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3414;
import net.minecraft.class_746;

@Environment(EnvType.CLIENT)
public class AuctionPingFeature {
   public static final AuctionPingFeature INSTANCE = new AuctionPingFeature();
   private final class_3414 sound = class_3414.method_47908(new class_2960("custom.soundeffect.ruby.gain"));

   public void handle(class_2561 message) {
      class_746 player = class_310.method_1551().field_1724;
      if (player != null && OriginUtil.isConnected()) {
         if (Platform.getConfig().isFeatureEnabled(OriginFeature.AUCTION_PING) && TextUtil.AUCTION_PURCHASE.matcher(TextUtil.getStripped(message)).find()) {
            player.method_5783(this.sound, 1.0F, 1.0F);
         }

      }
   }
}
