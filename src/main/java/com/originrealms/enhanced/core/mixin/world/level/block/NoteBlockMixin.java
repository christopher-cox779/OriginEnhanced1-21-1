package com.originrealms.enhanced.core.mixin.world.level.block;

import com.originrealms.enhanced.core.OriginsEnhanced;
import com.originrealms.enhanced.core.bridge.Platform;
import com.originrealms.enhanced.core.feature.OriginFeature;
import com.originrealms.enhanced.core.server.RemoteRegistry;
import com.originrealms.enhanced.core.util.OriginUtil;
import java.util.Optional;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2428;
import net.minecraft.class_2680;
import net.minecraft.class_3965;
import net.minecraft.class_4970.class_2251;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin({class_2428.class})
public abstract class NoteBlockMixin extends class_2248 {
   public NoteBlockMixin(class_2251 settings) {
      super(settings);
   }

   @Inject(
      method = {"getStateForPlacement"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void getStateForPlacement(class_1750 context, CallbackInfoReturnable<class_2680> cir) {
      if (Platform.getConfig().isFeatureEnabled(OriginFeature.BLOCK_ENHANCEMENTS) && OriginUtil.isConnected()) {
         cir.setReturnValue(this.method_9564());
      }
   }

   @Inject(
      method = {"updateShape"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void updateShape(class_2680 blockState, class_2350 direction, class_2680 blockState2, class_1936 levelAccessor, class_2338 blockPos, class_2338 blockPos2, CallbackInfoReturnable<class_2680> cir) {
      if (Platform.getConfig().isFeatureEnabled(OriginFeature.BLOCK_ENHANCEMENTS) && OriginUtil.isConnected()) {
         cir.setReturnValue(blockState);
      }
   }

   @Inject(
      method = {"useWithoutItem"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void use(class_2680 blockState, class_1937 level, class_2338 blockPos, class_1657 player, class_3965 blockHitResult, CallbackInfoReturnable<class_1269> cir) {
      if (Platform.getConfig().isFeatureEnabled(OriginFeature.BLOCK_ENHANCEMENTS) && OriginUtil.isConnected()) {
         Optional<RemoteRegistry.CustomBlock> block = OriginsEnhanced.getRegistry().getBlock(blockState);
         if (block.isEmpty() || !((RemoteRegistry.CustomBlock)block.get()).isIntractable()) {
            cir.setReturnValue(class_1269.field_5811);
         }

      }
   }
}
