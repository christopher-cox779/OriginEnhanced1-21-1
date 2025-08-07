package com.originrealms.enhanced.core.util.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2379;
import net.minecraft.class_310;
import net.minecraft.class_630;
import org.joml.Quaternionf;
import org.joml.Vector3f;

@Environment(EnvType.CLIENT)
public interface ArmorStandExtensions {
   static void lerp(class_630 part, class_2379 start, class_2379 end) {
      if (start.method_10256() == end.method_10256() && start.method_10257() == end.method_10257() && start.method_10258() == end.method_10258()) {
         part.method_33425(0.017453292F * end.method_10256(), 0.017453292F * end.method_10257(), 0.017453292F * end.method_10258());
      } else {
         Quaternionf from = (new Quaternionf()).rotateXYZ(0.017453292F * start.method_10256(), 0.017453292F * start.method_10257(), 0.017453292F * start.method_10258());
         Quaternionf to = (new Quaternionf()).rotateXYZ(0.017453292F * end.method_10256(), 0.017453292F * end.method_10257(), 0.017453292F * end.method_10258());
         Vector3f result = from.slerp(to, class_310.method_1551().method_1488(), new Quaternionf()).getEulerAnglesXYZ(new Vector3f());
         part.method_33425(result.x(), result.y(), result.z());
      }
   }

   class_2379 oe$getPrevHeadPose();

   class_2379 oe$getPrevBodyPose();

   class_2379 oe$getPrevLeftArmPose();

   class_2379 oe$getPrevRightArmPose();

   class_2379 oe$getPrevLeftLegPose();

   class_2379 oe$getPrevRightLegPose();
}
