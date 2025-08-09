package com.originrealms.enhanced.core.mixin.client.renderer.entity;

import com.originrealms.enhanced.core.bridge.Platform;
import com.originrealms.enhanced.core.feature.OriginFeature;
import com.originrealms.enhanced.core.util.OriginUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.decoration.ArmorStand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(ArmorStandRenderer.class)
public abstract class ArmorStandRendererMixin extends LivingEntityRenderer<ArmorStand, HumanoidModel<ArmorStand>> {
   public ArmorStandRendererMixin(EntityRendererProvider.Context context, HumanoidModel<ArmorStand> entityModel, float f) {
      super(context, entityModel, f);
   }

   @Inject(
           method = "shouldShowName(Lnet/minecraft/world/entity/decoration/ArmorStand;)Z",
           at = @At("HEAD"),
           cancellable = true
   )
   public void shouldShowName(ArmorStand armorStandEntity, CallbackInfoReturnable<Boolean> cir) {
      if (Platform.getConfig().isFeatureEnabled(OriginFeature.HIDE_NAMES) && OriginUtil.isConnected() && !Minecraft.getInstance().isPaused()) {
         cir.setReturnValue(false);
      }
   }
}