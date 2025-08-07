package com.originrealms.enhanced.core.mixin.world.item;

import com.originrealms.enhanced.core.bridge.Platform;
import com.originrealms.enhanced.core.feature.OriginFeature;
import com.originrealms.enhanced.core.util.OriginUtil;
import java.util.Optional;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1799;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_9279;
import net.minecraft.class_9322;
import net.minecraft.class_9334;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.CLIENT)
@Mixin({class_1799.class})
public abstract class ItemStackMixin implements class_9322 {
   @Redirect(
      method = {"getTooltipLines"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/resources/ResourceLocation;toString()Ljava/lang/String;"
)
   )
   public String getTooltipLines(class_2960 location) {
      String key = location.toString();
      class_9279 tag = (class_9279)this.method_57824(class_9334.field_49628);
      if (Platform.getConfig().isFeatureEnabled(OriginFeature.CUSTOM_ITEM_IDS) && OriginUtil.isConnected() && tag != null) {
         Optional<String> registryKeyOptional = OriginUtil.getRegistryKey(tag, class_310.method_1551().field_1687.method_30349());
         if (registryKeyOptional.isEmpty()) {
            return key;
         } else {
            String registryKey = (String)registryKeyOptional.get();
            return registryKey.equals(location.method_12832()) ? key : "originrealms:" + registryKey;
         }
      } else {
         return key;
      }
   }
}
