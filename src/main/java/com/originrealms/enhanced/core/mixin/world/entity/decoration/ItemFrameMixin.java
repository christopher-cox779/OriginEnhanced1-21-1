package com.originrealms.enhanced.core.mixin.world.entity.decoration;

import com.originrealms.enhanced.core.OriginsEnhanced;
import com.originrealms.enhanced.core.bridge.Platform;
import com.originrealms.enhanced.core.feature.OriginFeature;
import com.originrealms.enhanced.core.server.RemoteRegistry;
import com.originrealms.enhanced.core.util.OriginUtil;
import java.util.Optional;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1299;
import net.minecraft.class_1530;
import net.minecraft.class_1533;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin({class_1533.class})
public abstract class ItemFrameMixin extends class_1530 {
   public ItemFrameMixin(class_1299<? extends class_1533> entityType, class_1937 level) {
      super(entityType, level);
   }

   public class_238 method_5830() {
      return Platform.getConfig().isFeatureEnabled(OriginFeature.EXPAND_MODEL_BOUNDING_BOX) && OriginUtil.isConnected() ? this.method_5829().method_1014(4.5D) : super.method_5830();
   }

   @Inject(
      method = {"interact"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void interact(class_1657 player, class_1268 hand, CallbackInfoReturnable<class_1269> cir) {
      if (Platform.getConfig().isFeatureEnabled(OriginFeature.BLOCK_ENHANCEMENTS) && OriginUtil.isConnected()) {
         Optional<RemoteRegistry.CustomBlock> block = OriginsEnhanced.getRegistry().getBlock(this.method_37908().method_8320(this.method_6896().method_10093(this.method_5735().method_10153())));
         if (block.isPresent() && ((RemoteRegistry.CustomBlock)block.get()).isIntractable()) {
            cir.setReturnValue(class_1269.field_5812);
         }

      }
   }
}
