package com.originrealms.enhanced.core.mixin.client.renderer.entity;

import com.originrealms.enhanced.core.feature.CosmeticEnhancementsFeature;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1007;
import net.minecraft.class_591;
import net.minecraft.class_742;
import net.minecraft.class_922;
import net.minecraft.class_5617.class_5618;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin({class_1007.class})
public abstract class PlayerRendererMixin extends class_922<class_742, class_591<class_742>> {
   public PlayerRendererMixin(class_5618 context, class_591<class_742> entityModel, float f) {
      super(context, entityModel, f);
   }

   @Inject(
      method = {"<init>"},
      at = {@At("TAIL")}
   )
   public void addRenderLayer(class_5618 context, boolean bl, CallbackInfo ci) {
      this.method_4046(new CosmeticEnhancementsFeature.BackSwagLayer(this, context.method_43338()));
   }
}
