package com.originrealms.enhanced.core.mixin.world.level.block;

import com.originrealms.enhanced.core.bridge.Platform;
import com.originrealms.enhanced.core.feature.OriginFeature;
import com.originrealms.enhanced.core.util.OriginUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1750;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2538;
import net.minecraft.class_2680;
import net.minecraft.class_4970.class_2251;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin({class_2538.class})
public abstract class TripWireBlockMixin extends class_2248 {
   public TripWireBlockMixin(class_2251 settings) {
      super(settings);
   }

   @Inject(
      method = {"getStateForPlacement"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void getStateForPlacement(class_1750 ctx, CallbackInfoReturnable<class_2680> cir) {
      if (Platform.getConfig().isFeatureEnabled(OriginFeature.BLOCK_ENHANCEMENTS) && OriginUtil.isConnected()) {
         cir.setReturnValue(this.method_9564());
      }
   }

   @Inject(
      method = {"updateShape"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void updateShape(class_2680 state, class_2350 direction, class_2680 newState, class_1936 world, class_2338 pos, class_2338 posFrom, CallbackInfoReturnable<class_2680> cir) {
      if (Platform.getConfig().isFeatureEnabled(OriginFeature.BLOCK_ENHANCEMENTS) && OriginUtil.isConnected()) {
         cir.setReturnValue(state);
      }
   }

   @Inject(
      method = {"onPlace", "onRemove"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void onPlace(class_2680 blockState, class_1937 level, class_2338 blockPos, class_2680 blockState2, boolean bl, CallbackInfo ci) {
      if (Platform.getConfig().isFeatureEnabled(OriginFeature.BLOCK_ENHANCEMENTS) && OriginUtil.isConnected()) {
         ci.cancel();
      }
   }
}
