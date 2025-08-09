package com.originrealms.enhanced.core.mixin.world.entity.decoration;

import com.originrealms.enhanced.core.bridge.Platform;
import com.originrealms.enhanced.core.feature.OriginFeature;
import com.originrealms.enhanced.core.util.OriginUtil;
import com.originrealms.enhanced.core.util.mixin.ArmorStandExtensions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.Rotations;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ArmorStand.class)
public abstract class ArmorStandMixin extends LivingEntity implements ArmorStandExtensions {
   @Shadow
   @Final
   private static Rotations DEFAULT_HEAD_POSE;
   @Shadow
   @Final
   private static Rotations DEFAULT_BODY_POSE;
   @Shadow
   @Final
   private static Rotations DEFAULT_LEFT_ARM_POSE;
   @Shadow
   @Final
   private static Rotations DEFAULT_RIGHT_ARM_POSE;
   @Shadow
   @Final
   private static Rotations DEFAULT_LEFT_LEG_POSE;
   @Shadow
   @Final
   private static Rotations DEFAULT_RIGHT_LEG_POSE;

   private Rotations oe$prevHeadPose;
   private Rotations oe$prevBodyPose;
   private Rotations oe$prevLeftArmPose;
   private Rotations oe$prevRightArmPose;
   private Rotations oe$prevLeftLegPose;
   private Rotations oe$prevRightLegPose;

   private ArmorStandMixin(EntityType<? extends LivingEntity> entityType, Level level) {
      super(entityType, level);
   }

   @Inject(method = "tick", at = @At("HEAD"))
   public void tick(CallbackInfo ci) {
      if (Platform.getConfig().isFeatureEnabled(OriginFeature.SMOOTH_ANIMATIONS) && OriginUtil.isConnected()) {
         ArmorStand armorStand = (ArmorStand)(Object)this;
         this.oe$prevHeadPose = armorStand.getHeadPose();
         this.oe$prevBodyPose = armorStand.getBodyPose();
         this.oe$prevLeftArmPose = armorStand.getLeftArmPose();
         this.oe$prevRightArmPose = armorStand.getRightArmPose();
         this.oe$prevLeftLegPose = armorStand.getLeftLegPose();
         this.oe$prevRightLegPose = armorStand.getRightLegPose();
      }
   }

   @Override
   public Rotations oe$getPrevHeadPose() {
      return this.oe$prevHeadPose != null ? this.oe$prevHeadPose : DEFAULT_HEAD_POSE;
   }

   @Override
   public Rotations oe$getPrevBodyPose() {
      return this.oe$prevBodyPose != null ? this.oe$prevBodyPose : DEFAULT_BODY_POSE;
   }

   @Override
   public Rotations oe$getPrevLeftArmPose() {
      return this.oe$prevLeftArmPose != null ? this.oe$prevLeftArmPose : DEFAULT_LEFT_ARM_POSE;
   }

   @Override
   public Rotations oe$getPrevRightArmPose() {
      return this.oe$prevRightArmPose != null ? this.oe$prevRightArmPose : DEFAULT_RIGHT_ARM_POSE;
   }

   @Override
   public Rotations oe$getPrevLeftLegPose() {
      return this.oe$prevLeftLegPose != null ? this.oe$prevLeftLegPose : DEFAULT_LEFT_LEG_POSE;
   }

   @Override
   public Rotations oe$getPrevRightLegPose() {
      return this.oe$prevRightLegPose != null ? this.oe$prevRightLegPose : DEFAULT_RIGHT_LEG_POSE;
   }
}