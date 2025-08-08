package com.originrealms.enhanced.core.mixin.world.entity.decoration;

import com.originrealms.enhanced.core.bridge.Platform;
import com.originrealms.enhanced.core.feature.OriginFeature;
import com.originrealms.enhanced.core.util.OriginUtil;
import com.originrealms.enhanced.core.util.mixin.ArmorStandExtensions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1531;
import net.minecraft.class_1937;
import net.minecraft.class_2379;
import net.minecraft.class_238;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin({class_1531.class})
public abstract class ArmorStandMixin extends class_1309 implements ArmorStandExtensions {
   @Shadow
   @Final
   private static class_2379 field_7113;
   @Shadow
   @Final
   private static class_2379 field_7119;
   @Shadow
   @Final
   private static class_2379 field_7124;
   @Shadow
   @Final
   private static class_2379 field_7115;
   @Shadow
   @Final
   private static class_2379 field_7121;
   @Shadow
   @Final
   private static class_2379 field_7117;
   private class_2379 oe$prevHeadPose;
   private class_2379 oe$prevBodyPose;
   private class_2379 oe$prevLeftArmPose;
   private class_2379 oe$prevRightArmPose;
   private class_2379 oe$prevLeftLegPose;
   private class_2379 oe$prevRightLegPose;

   private ArmorStandMixin(class_1299<? extends class_1531> entityType, class_1937 level) {
      super(entityType, level);
      this.oe$prevHeadPose = field_7113;
      this.oe$prevBodyPose = field_7119;
      this.oe$prevLeftArmPose = field_7124;
      this.oe$prevRightArmPose = field_7115;
      this.oe$prevLeftLegPose = field_7121;
      this.oe$prevRightLegPose = field_7117;
   }

   @Shadow
   public abstract class_2379 method_6921();

   @Shadow
   public abstract class_2379 method_6923();

   @Shadow
   public abstract class_2379 method_6930();

   @Shadow
   public abstract class_2379 method_6903();

   @Shadow
   public abstract class_2379 method_6917();

   @Shadow
   public abstract class_2379 method_6900();

   @Inject(
      method = {"tick"},
      at = {@At("HEAD")}
   )
   public void tick(CallbackInfo ci) {
      if (!this.oe$prevHeadPose.equals(this.method_6921())) {
         this.oe$prevHeadPose = this.method_6921();
      }

      if (!this.oe$prevBodyPose.equals(this.method_6923())) {
         this.oe$prevBodyPose = this.method_6923();
      }

      if (!this.oe$prevLeftArmPose.equals(this.method_6930())) {
         this.oe$prevLeftArmPose = this.method_6930();
      }

      if (!this.oe$prevRightArmPose.equals(this.method_6903())) {
         this.oe$prevRightArmPose = this.method_6903();
      }

      if (!this.oe$prevLeftLegPose.equals(this.method_6917())) {
         this.oe$prevLeftLegPose = this.method_6917();
      }

      if (!this.oe$prevRightLegPose.equals(this.method_6900())) {
         this.oe$prevRightLegPose = this.method_6900();
      }

   }

   public class_2379 oe$getPrevHeadPose() {
      return this.oe$prevHeadPose;
   }

   public class_2379 oe$getPrevBodyPose() {
      return this.oe$prevBodyPose;
   }

   public class_2379 oe$getPrevLeftArmPose() {
      return this.oe$prevLeftArmPose;
   }

   public class_2379 oe$getPrevRightArmPose() {
      return this.oe$prevRightArmPose;
   }

   public class_2379 oe$getPrevLeftLegPose() {
      return this.oe$prevLeftLegPose;
   }

   public class_2379 oe$getPrevRightLegPose() {
      return this.oe$prevRightLegPose;
   }

   public class_238 method_5830() {
      return Platform.getConfig().isFeatureEnabled(OriginFeature.EXPAND_MODEL_BOUNDING_BOX) && OriginUtil.isConnected() ? this.method_5829().method_1014(4.5D) : super.method_5830();
   }
}
