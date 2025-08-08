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
public class GestureHead extends GesturePartBase {
   protected final class_630 head;

   public GestureHead(class_630 root) {
      super(root);
      this.head = root.method_32086("head");
   }

   public static class_5607 createLayer() {
      class_5609 meshDefinition = new class_5609();
      class_5610 partDefinition = meshDefinition.method_32111();
      partDefinition.method_32117("head", class_5606.method_32108().method_32101(0, 0).method_32097(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), class_5603.field_27701);
      partDefinition.method_32116("head").method_32117("hat", class_5606.method_32108().method_32101(32, 0).method_32098(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new class_5605(0.5F)), class_5603.field_27701);
      return class_5607.method_32110(meshDefinition, 64, 64);
   }

   public void setupAnim(float f, float g, float h) {
      this.head.field_3675 = g * 0.017453292F;
      this.head.field_3654 = h * 0.017453292F;
   }
}
