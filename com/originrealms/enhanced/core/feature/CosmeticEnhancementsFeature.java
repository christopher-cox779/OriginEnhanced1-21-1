package com.originrealms.enhanced.core.feature;

import com.originrealms.enhanced.core.util.OriginUtil;
import com.originrealms.enhanced.core.util.mixin.EntityExtensions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1297;
import net.minecraft.class_1799;
import net.minecraft.class_2487;
import net.minecraft.class_3883;
import net.minecraft.class_3887;
import net.minecraft.class_3902;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_591;
import net.minecraft.class_742;
import net.minecraft.class_759;
import net.minecraft.class_7833;
import net.minecraft.class_811;
import net.minecraft.class_9279;
import net.minecraft.class_9326;
import net.minecraft.class_9334;

@Environment(EnvType.CLIENT)
public class CosmeticEnhancementsFeature {
   public static final CosmeticEnhancementsFeature INSTANCE = new CosmeticEnhancementsFeature();

   public class_1799 getBackSwag(class_1297 entity) {
      if (entity != null && ((EntityExtensions)entity).getBackItem() != null) {
         class_1799 stack = ((EntityExtensions)entity).getBackItem();
         class_9279 data = (class_9279)stack.method_57353().method_57830(class_9334.field_49628, class_9279.field_49302);
         data.method_57451((tag) -> {
            class_2487 values = new class_2487();
            values.method_10556("Cosmetic", true);
            tag.method_10566("PublicBukkitValues", values);
         });
         stack.method_57366(class_9326.method_57841().method_57854(class_9334.field_49628, data).method_57854(class_9334.field_49638, class_3902.field_17274).method_57852());
         return stack;
      } else {
         return class_1799.field_8037;
      }
   }

   @Environment(EnvType.CLIENT)
   public static class BackSwagLayer extends class_3887<class_742, class_591<class_742>> {
      private final class_759 itemInHandRenderer;

      public BackSwagLayer(class_3883<class_742, class_591<class_742>> renderLayerParent, class_759 itemInHandRenderer) {
         super(renderLayerParent);
         this.itemInHandRenderer = itemInHandRenderer;
      }

      public void render(class_4587 poseStack, class_4597 buffer, int packedLight, class_742 entity, float f, float g, float partialTicks, float j, float k, float l) {
         if (OriginUtil.isConnected()) {
            class_1799 stack = CosmeticEnhancementsFeature.INSTANCE.getBackSwag(entity);
            if (!stack.method_7960()) {
               poseStack.method_22903();
               ((class_591)this.method_17165()).field_3391.method_22703(poseStack);
               poseStack.method_22904(0.0D, -2.075D, 0.0D);
               poseStack.method_22907(class_7833.field_40716.rotationDegrees(180.0F));
               poseStack.method_22905(0.625F, -0.625F, -0.625F);
               this.itemInHandRenderer.method_3233(entity, stack, class_811.field_4316, false, poseStack, buffer, packedLight);
               poseStack.method_22909();
            }
         }
      }
   }
}
