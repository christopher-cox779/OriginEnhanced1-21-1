package com.originrealms.enhanced.core.mixin.client;

import com.originrealms.enhanced.core.bridge.Platform;
import com.originrealms.enhanced.core.feature.OriginFeature;
import com.originrealms.enhanced.core.util.OriginUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1304;
import net.minecraft.class_1802;
import net.minecraft.class_310;
import net.minecraft.class_312;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin({class_312.class})
public abstract class MouseHandlerMixin {
   @Inject(
      method = {"turnPlayer"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/Options;sensitivity()Lnet/minecraft/client/OptionInstance;",
   shift = Shift.BEFORE
)},
      cancellable = true
   )
   public void updateMouse(CallbackInfo ci) {
      if (Platform.getConfig().isFeatureEnabled(OriginFeature.CUTSCENE_ENHANCEMENTS)) {
         class_746 player = class_310.method_1551().field_1724;
         if (player != null && player.method_6118(class_1304.field_6169).method_7909() == class_1802.field_17519 && OriginUtil.isConnected()) {
            ci.cancel();
         }
      }
   }
}
