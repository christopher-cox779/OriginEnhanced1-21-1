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
public class GestureBody extends GesturePartBase {
   private final class_630 body;
   private final class_630 jacket;

   public GestureBody(class_630 root) {
      super(root);
      this.body = root.method_32086("body");
      this.jacket = root.method_32086("jacket");
   }

   public static class_5607 createLayer() {
      class_5609 meshDefinition = new class_5609();
      class_5610 partDefinition = meshDefinition.method_32111();
      partDefinition.method_32117("body", class_5606.method_32108().method_32101(16, 16).method_32097(-4.0F, -8.0F, -2.0F, 8.0F, 12.0F, 4.0F), class_5603.field_27701);
      partDefinition.method_32117("jacket", class_5606.method_32108().method_32101(16, 32).method_32098(-4.0F, -8.0F, -2.0F, 8.0F, 12.0F, 4.0F, new class_5605(0.25F)), class_5603.field_27701);
      return class_5607.method_32110(meshDefinition, 64, 64);
   }

   public void setupAnim(float f, float g, float h) {
      this.body.field_3675 = g * 0.017453292F;
      this.body.field_3654 = h * 0.017453292F;
      this.jacket.method_17138(this.body);
   }
}
