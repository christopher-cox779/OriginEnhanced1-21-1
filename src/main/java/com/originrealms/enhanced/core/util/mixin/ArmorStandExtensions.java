package com.originrealms.enhanced.core.util.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.Rotations;
import org.joml.Quaternionf;
import org.joml.Vector3f;

@Environment(EnvType.CLIENT)
public interface ArmorStandExtensions {
   static void lerp(ModelPart part, Rotations start, Rotations end) {
      if (start.getX() == end.getX() && start.getY() == end.getY() && start.getZ() == end.getZ()) {
         part.setRotation(0.017453292F * end.getX(), 0.017453292F * end.getY(), 0.017453292F * end.getZ());
      } else {
         Quaternionf from = (new Quaternionf()).rotateXYZ(0.017453292F * start.getX(), 0.017453292F * start.getY(), 0.017453292F * start.getZ());
         Quaternionf to = (new Quaternionf()).rotateXYZ(0.017453292F * end.getX(), 0.017453292F * end.getY(), 0.017453292F * end.getZ());
         Vector3f result = from.slerp(to, Minecraft.getInstance().getTimer().getGameTimeDeltaPartialTick(false), new Quaternionf()).getEulerAnglesXYZ(new Vector3f());
         part.setRotation(result.x(), result.y(), result.z());
      }
   }

   Rotations oe$getPrevHeadPose();

   Rotations oe$getPrevBodyPose();

   Rotations oe$getPrevLeftArmPose();

   Rotations oe$getPrevRightArmPose();

   Rotations oe$getPrevLeftLegPose();

   Rotations oe$getPrevRightLegPose();
}