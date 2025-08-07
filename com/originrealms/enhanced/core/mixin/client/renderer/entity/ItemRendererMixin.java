package com.originrealms.enhanced.core.mixin.client.renderer.entity;

import com.originrealms.enhanced.core.bridge.Platform;
import com.originrealms.enhanced.core.feature.GestureEnhancementsFeature;
import com.originrealms.enhanced.core.feature.OriginFeature;
import com.originrealms.enhanced.core.feature.gesture.part.GesturePartBase;
import com.originrealms.enhanced.core.util.OriginUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1087;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2435;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_804;
import net.minecraft.class_811;
import net.minecraft.class_918;
import net.minecraft.class_9280;
import net.minecraft.class_9296;
import net.minecraft.class_9334;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin({class_918.class})
public abstract class ItemRendererMixin {
   @Inject(
      method = {"render"},
      at = {@At(
   value = "INVOKE",
   target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V",
   ordinal = 0,
   shift = Shift.AFTER
)},
      cancellable = true
   )
   public void renderGestureSkull(class_1799 itemStack, class_811 displayCtx, boolean bl, class_4587 poseStack, class_4597 multiBufferSource, int i, int j, class_1087 bakedModel, CallbackInfo ci) {
      if (Platform.getConfig().isFeatureEnabled(OriginFeature.GESTURE_COMPATIBILITY) && OriginUtil.isConnected()) {
         class_1792 var11 = itemStack.method_7909();
         if (var11 instanceof class_1747) {
            class_1747 bi = (class_1747)var11;
            if (bi.method_7711() instanceof class_2435 && itemStack.method_57826(class_9334.field_49637)) {
               class_9280 cmd = (class_9280)itemStack.method_57824(class_9334.field_49637);

               assert cmd != null;

               int model = cmd.comp_2382();
               GesturePartBase gesture = (GesturePartBase)GestureEnhancementsFeature.INSTANCE.getGestureModels().get(model);
               if (gesture == null) {
                  return;
               }

               class_9296 profile = (class_9296)itemStack.method_57824(class_9334.field_49617);
               if (profile == null) {
                  return;
               }

               class_2960 texture = class_310.method_1551().method_1582().method_52862(profile.comp_2413()).comp_1626();
               class_804 transforms = bakedModel.method_4709().method_3503(displayCtx);
               Vector3f previousScale = new Vector3f(transforms.field_4285);
               transforms.field_4285.set(0.9375F, 0.9375F, 0.9375F);
               transforms.method_23075(bl, poseStack);
               transforms.field_4285.set(previousScale.x(), previousScale.y(), previousScale.z());
               gesture.render(texture, 180.0F, 0.0F, poseStack, multiBufferSource, i);
               poseStack.method_22909();
               ci.cancel();
               return;
            }
         }

      }
   }
}
