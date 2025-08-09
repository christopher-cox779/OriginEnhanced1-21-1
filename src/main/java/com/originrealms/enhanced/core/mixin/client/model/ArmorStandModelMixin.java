package com.originrealms.enhanced.core.mixin.client.model;

import com.originrealms.enhanced.core.bridge.Platform;
import com.originrealms.enhanced.core.feature.OriginFeature;
import com.originrealms.enhanced.core.util.OriginUtil;
import com.originrealms.enhanced.core.util.mixin.ArmorStandExtensions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.class_1531;
import net.minecraft.class_548;
import net.minecraft.class_551;
import net.minecraft.class_630;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin({class_551.class})
public abstract class ArmorStandModelMixin extends class_548 {
   @Shadow
   @Final
   private class_630 field_3313;
   @Shadow
   @Final
   private class_630 field_27392;
   @Shadow
   @Final
   private class_630 field_27391;

   public ArmorStandModelMixin(class_630 modelPart) {
      super(modelPart);
   }

   @Inject(
      method = {"setupAnim(Lnet/minecraft/world/entity/decoration/ArmorStand;FFFFF)V"},
      at = {@At("TAIL")}
   )
   public void setupAnim(class_1531 armorStand, float f, float g, float h, float i, float j, CallbackInfo ci) {
      super.method_17066(armorStand, f, g, h, i, j);
      if (Platform.getConfig().isFeatureEnabled(OriginFeature.SMOOTH_ANIMATIONS) && OriginUtil.isConnected()) {
         ArmorStandExtensions extension = (ArmorStandExtensions)armorStand;
         ArmorStandExtensions.lerp(this.field_3398, extension.oe$getPrevHeadPose(), armorStand.method_6921());
         ArmorStandExtensions.lerp(this.field_3391, extension.oe$getPrevBodyPose(), armorStand.method_6923());
         ArmorStandExtensions.lerp(this.field_27433, extension.oe$getPrevLeftArmPose(), armorStand.method_6930());
         ArmorStandExtensions.lerp(this.field_3401, extension.oe$getPrevRightArmPose(), armorStand.method_6903());
         ArmorStandExtensions.lerp(this.field_3397, extension.oe$getPrevLeftLegPose(), armorStand.method_6917());
         ArmorStandExtensions.lerp(this.field_3392, extension.oe$getPrevRightLegPose(), armorStand.method_6900());
         ArmorStandExtensions.lerp(this.field_27391, extension.oe$getPrevBodyPose(), armorStand.method_6923());
         ArmorStandExtensions.lerp(this.field_27392, extension.oe$getPrevBodyPose(), armorStand.method_6923());
         ArmorStandExtensions.lerp(this.field_3313, extension.oe$getPrevBodyPose(), armorStand.method_6923());
         this.field_3394.method_17138(this.field_3398);
      }

   }
}
