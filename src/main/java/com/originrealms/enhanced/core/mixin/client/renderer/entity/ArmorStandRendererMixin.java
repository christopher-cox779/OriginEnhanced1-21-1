package com.originrealms.enhanced.core.mixin.client.renderer.entity;

import com.originrealms.enhanced.core.bridge.Platform;
import com.originrealms.enhanced.core.feature.OriginFeature;
import com.originrealms.enhanced.core.util.OriginUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1531;
import net.minecraft.class_310;
import net.minecraft.class_548;
import net.minecraft.class_877;
import net.minecraft.class_922;
import net.minecraft.class_5617.class_5618;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin({class_877.class})
public abstract class ArmorStandRendererMixin extends class_922<class_1531, class_548> {
   public ArmorStandRendererMixin(class_5618 context, class_548 entityModel, float f) {
      super(context, entityModel, f);
   }

   @Inject(
      method = {"shouldShowName(Lnet/minecraft/world/entity/decoration/ArmorStand;)Z"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void shouldShowName(class_1531 armorStandEntity, CallbackInfoReturnable<Boolean> cir) {
      if (Platform.getConfig().isFeatureEnabled(OriginFeature.HIDE_NAMES) && OriginUtil.isConnected() && !class_310.method_1498()) {
         cir.setReturnValue(false);
      }

   }
}
