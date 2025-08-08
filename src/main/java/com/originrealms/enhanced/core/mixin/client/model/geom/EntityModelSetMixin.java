package com.originrealms.enhanced.core.mixin.client.model.geom;

import com.originrealms.enhanced.core.feature.GestureEnhancementsFeature;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_3300;
import net.minecraft.class_5599;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin({class_5599.class})
public class EntityModelSetMixin {
   @Inject(
      method = {"onResourceManagerReload"},
      at = {@At("TAIL")}
   )
   public void onResourceManagerReload(class_3300 resourceManager, CallbackInfo ci) {
      GestureEnhancementsFeature.INSTANCE.onResourceManagerReload();
   }
}
