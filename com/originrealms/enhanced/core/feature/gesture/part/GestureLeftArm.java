package com.originrealms.enhanced.core.feature.gesture.part;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_5603;
import net.minecraft.class_5605;
import net.minecraft.class_5606;
import net.minecraft.class_5607;
import net.minecraft.class_5609;
import net.minecraft.class_5610;
import net.minecraft.class_630;

@Environment(EnvType.CLIENT)
public class GestureLeftArm extends GesturePartBase {
   protected final class_630 leftArm;
   protected final class_630 leftSleeve;

   public GestureLeftArm(class_630 root) {
      super(root);
      this.leftArm = root.method_32086("left_arm");
      this.leftSleeve = root.method_32086("left_sleeve");
   }

   public static class_5607 createLayer(boolean slim) {
      class_5609 meshDefinition = new class_5609();
      class_5610 partDefinition = meshDefinition.method_32111();
      if (slim) {
         partDefinition.method_32117("left_arm", class_5606.method_32108().method_32101(32, 48).method_32097(-1.5F, -8.0F, -2.0F, 3.0F, 12.0F, 4.0F), class_5603.field_27701);
         partDefinition.method_32117("left_sleeve", class_5606.method_32108().method_32101(48, 48).method_32098(-1.5F, -8.0F, -2.0F, 3.0F, 12.0F, 4.0F, new class_5605(0.25F)), class_5603.field_27701);
      } else {
         partDefinition.method_32117("left_arm", class_5606.method_32108().method_32101(32, 48).method_32098(-2.0F, -8.0F, -2.0F, 4.0F, 12.0F, 4.0F, class_5605.field_27715), class_5603.field_27701);
         partDefinition.method_32117("left_sleeve", class_5606.method_32108().method_32101(48, 48).method_32098(-2.0F, -8.0F, -2.0F, 4.0F, 12.0F, 4.0F, new class_5605(0.25F)), class_5603.field_27701);
      }

      return class_5607.method_32110(meshDefinition, 64, 64);
   }

   public void setupAnim(float f, float g, float h) {
      this.leftArm.field_3675 = g * 0.017453292F;
      this.leftArm.field_3654 = h * 0.017453292F;
      this.leftSleeve.method_17138(this.leftArm);
   }
}
