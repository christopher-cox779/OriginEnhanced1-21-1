package com.originrealms.enhanced.core.feature.gesture.part;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1921;
import net.minecraft.class_2960;
import net.minecraft.class_3879;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_630;

@Environment(EnvType.CLIENT)
public abstract class GesturePartBase extends class_3879 {
   private final class_630 root;

   public GesturePartBase(class_630 root) {
      super(class_1921::method_23689);
      this.root = root;
   }

   public abstract void setupAnim(float var1, float var2, float var3);

   public void method_2828(class_4587 poseStack, class_4588 vertexConsumer, int i, int j, float f, float g, float h, float k) {
      this.root.method_22699(poseStack, vertexConsumer, i, j, f, g, h, k);
   }

   public void render(class_2960 texture, float rotation, float mouthAnimation, class_4587 poseStack, class_4597 multiBufferSource, int packedLight) {
      poseStack.method_22903();
      poseStack.method_22904(0.0D, -0.5D, 0.0D);
      poseStack.method_22905(-1.0F, -1.0F, 1.0F);
      class_4588 vertexConsumer = multiBufferSource.getBuffer(this.method_23500(texture));
      this.setupAnim(mouthAnimation, rotation, 0.0F);
      this.method_2828(poseStack, vertexConsumer, packedLight, class_4608.field_21444, 1.0F, 1.0F, 1.0F, 1.0F);
      poseStack.method_22909();
   }
}
