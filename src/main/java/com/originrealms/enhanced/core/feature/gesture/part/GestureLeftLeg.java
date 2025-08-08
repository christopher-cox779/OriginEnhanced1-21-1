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
public class GestureLeftLeg extends GesturePartBase {
   protected final class_630 leftLeg;
   protected final class_630 leftPants;

   public GestureLeftLeg(class_630 root) {
      super(root);
      this.leftLeg = root.method_32086("left_leg");
      this.leftPants = root.method_32086("left_pants");
   }

   public static class_5607 createLayer() {
      class_5609 meshDefinition = new class_5609();
      class_5610 partDefinition = meshDefinition.method_32111();
      partDefinition.method_32117("left_leg", class_5606.method_32108().method_32101(16, 48).method_32098(-2.0F, -8.0F, -2.0F, 4.0F, 12.0F, 4.0F, class_5605.field_27715), class_5603.field_27701);
      partDefinition.method_32117("left_pants", class_5606.method_32108().method_32101(0, 48).method_32098(-2.0F, -8.0F, -2.0F, 4.0F, 12.0F, 4.0F, new class_5605(0.25F)), class_5603.field_27701);
      return class_5607.method_32110(meshDefinition, 64, 64);
   }

   public void setupAnim(float f, float g, float h) {
      this.leftLeg.field_3675 = g * 0.017453292F;
      this.leftLeg.field_3654 = h * 0.017453292F;
      this.leftPants.method_17138(this.leftLeg);
   }
}
