package com.originrealms.enhanced.core.mixin.client.renderer.entity;

import com.originrealms.enhanced.core.networking.ModPacketHandler;
import com.originrealms.enhanced.core.util.OriginUtil;
import com.originrealms.enhanced.core.util.mixin.EntityExtensions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1297;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_897;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin({class_897.class})
public abstract class EntityRendererMixin<T extends class_1297> {
   private class_1297 renderedEntity;

   @Shadow
   protected abstract void method_3926(T var1, class_2561 var2, class_4587 var3, class_4597 var4, int var5, float var6);

   @Inject(
      method = {"render"},
      at = {@At("HEAD")}
   )
   public void renderNameTag(T entity, float f, float g, class_4587 poseStack, class_4597 multiBufferSource, int i, CallbackInfo ci) {
      if (OriginUtil.isConnected() && class_310.method_1498() && entity != class_310.method_1551().method_1560()) {
         ModPacketHandler.NameTag tag = ((EntityExtensions)entity).getOrCreateEnhancedTag();
         this.renderedEntity = entity;
         poseStack.method_22903();

         for(int index = 0; index < tag.getLines().size(); ++index) {
            if (index > 0) {
               poseStack.method_22904(0.0D, 0.31049996614456177D, 0.0D);
            }

            this.method_3926(entity, (class_2561)tag.getLines().get(index), poseStack, multiBufferSource, i, g);
         }

         poseStack.method_22909();
      }
   }

   @ModifyArg(
      method = {"renderNameTag"},
      index = 1,
      at = @At(
   value = "INVOKE",
   target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V"
)
   )
   public double nameTagOffset(double original) {
      return this.renderedEntity != null && OriginUtil.isConnected() ? original + (double)((EntityExtensions)this.renderedEntity).getOrCreateEnhancedTag().getOffset() : original;
   }
}
