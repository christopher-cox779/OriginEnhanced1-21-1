package com.originrealms.enhanced.core.mixin.client.model;

import com.originrealms.enhanced.core.bridge.Platform;
import com.originrealms.enhanced.core.feature.OriginFeature;
import com.originrealms.enhanced.core.util.OriginUtil;
import com.originrealms.enhanced.core.util.mixin.ArmorStandExtensions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ArmorStandModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.decoration.ArmorStand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ArmorStandModel.class)
public abstract class ArmorStandModelMixin extends HumanoidModel<ArmorStand> {

   public ArmorStandModelMixin(ModelPart modelPart) {
      super(modelPart);
   }

   @Inject(
           method = "setupAnim(Lnet/minecraft/world/entity/decoration/ArmorStand;FFFFF)V",
           at = @At("TAIL")
   )
   public void setupAnim(ArmorStand armorStand, float f, float g, float h, float i, float j, CallbackInfo ci) {
      super.setupAnim(armorStand, f, g, h, i, j);
      if (Platform.getConfig().isFeatureEnabled(OriginFeature.SMOOTH_ANIMATIONS) && OriginUtil.isConnected()) {
         ArmorStandExtensions extension = (ArmorStandExtensions)armorStand;

         // Use inherited fields from HumanoidModel
         ArmorStandExtensions.lerp(this.head, extension.oe$getPrevHeadPose(), armorStand.getHeadPose());
         ArmorStandExtensions.lerp(this.body, extension.oe$getPrevBodyPose(), armorStand.getBodyPose());
         ArmorStandExtensions.lerp(this.leftArm, extension.oe$getPrevLeftArmPose(), armorStand.getLeftArmPose());
         ArmorStandExtensions.lerp(this.rightArm, extension.oe$getPrevRightArmPose(), armorStand.getRightArmPose());
         ArmorStandExtensions.lerp(this.leftLeg, extension.oe$getPrevLeftLegPose(), armorStand.getLeftLegPose());
         ArmorStandExtensions.lerp(this.rightLeg, extension.oe$getPrevRightLegPose(), armorStand.getRightLegPose());

         this.hat.copyFrom(this.head);
      }
   }
}