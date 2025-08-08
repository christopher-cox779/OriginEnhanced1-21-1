package com.originrealms.enhanced.core.mixin.client.renderer;

import com.originrealms.enhanced.core.bridge.Platform;
import com.originrealms.enhanced.core.feature.OriginFeature;
import com.originrealms.enhanced.core.util.OriginUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1304;
import net.minecraft.class_1802;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_757;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin({class_757.class})
public abstract class GameRendererMixin {
   @Shadow
   private float field_3999;
   @Shadow
   private float field_4019;

   @Inject(
      method = {"getFov"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void getFov(class_4184 camera, float f, boolean bl, CallbackInfoReturnable<Double> cir) {
      if (Platform.getConfig().isFeatureEnabled(OriginFeature.CUTSCENE_ENHANCEMENTS) && OriginUtil.isConnected() && class_310.method_1551().field_1724 != null && class_310.method_1551().field_1724.method_6118(class_1304.field_6169).method_7909() == class_1802.field_17519) {
         cir.setReturnValue(70.0D * (double)class_3532.method_16439(f, this.field_3999, this.field_4019));
      }

   }
}
