package com.originrealms.enhanced.core.mixin.client.gui.screens.inventory;

import com.originrealms.enhanced.core.bridge.Platform;
import com.originrealms.enhanced.core.feature.CosmeticEnhancementsFeature;
import com.originrealms.enhanced.core.feature.OriginFeature;
import com.originrealms.enhanced.core.util.OriginUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1661;
import net.minecraft.class_1723;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_485;
import net.minecraft.class_490;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin({class_490.class})
public abstract class InventoryScreenMixin extends class_485<class_1723> {
   public InventoryScreenMixin(class_1723 abstractContainerMenu, class_1661 inventory, class_2561 component) {
      super(abstractContainerMenu, inventory, component);
   }

   @Inject(
      method = {"renderBg"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V",
   shift = Shift.AFTER
)}
   )
   public void renderBg(class_332 gfx, float f, int i, int j, CallbackInfo ci) {
      if (Platform.getConfig().isFeatureEnabled(OriginFeature.BACK_SWAG_SLOT) && !CosmeticEnhancementsFeature.INSTANCE.getBackSwag(class_310.method_1551().field_1724).method_7960() && OriginUtil.isConnected()) {
         gfx.method_51448().method_22903();
         gfx.method_51448().method_46416((float)this.field_2776, (float)this.field_2800, 0.0F);
         gfx.method_25293(field_2801, 76, 43, 18, 18, 76.0F, 61.0F, 18, 18, 256, 256);
         gfx.method_51448().method_22909();
      }
   }
}
