package com.originrealms.enhanced.core.mixin.client.gui.screens.inventory;

import com.originrealms.enhanced.core.bridge.Platform;
import com.originrealms.enhanced.core.feature.CosmeticEnhancementsFeature;
import com.originrealms.enhanced.core.feature.OriginFeature;
import com.originrealms.enhanced.core.util.OriginUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_465;
import net.minecraft.class_490;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin({class_465.class})
public abstract class AbstractContainerScreenMixin extends class_437 {
   @Shadow
   protected int field_2792;

   protected AbstractContainerScreenMixin(class_2561 component) {
      super(component);
   }

   @Shadow
   protected abstract boolean method_2378(int var1, int var2, int var3, int var4, double var5, double var7);

   @Inject(
      method = {"render"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/core/NonNullList;size()I",
   ordinal = 0,
   shift = Shift.BEFORE
)}
   )
   public void render(class_332 gfx, int mouseX, int mouseY, float f, CallbackInfo ci) {
      if (Platform.getConfig().isFeatureEnabled(OriginFeature.BACK_SWAG_SLOT) && this instanceof class_490 && OriginUtil.isConnected()) {
         class_1799 stack = CosmeticEnhancementsFeature.INSTANCE.getBackSwag(class_310.method_1551().field_1724);
         if (!stack.method_7960()) {
            int x = 77;
            int y = 44;
            gfx.method_51448().method_22903();
            gfx.method_51448().method_46416(0.0F, 0.0F, 100.0F);
            gfx.method_51423(this.field_22787.field_1724, stack, x, y, x + y * this.field_2792);
            gfx.method_51432(this.field_22793, stack, x, y, (String)null);
            gfx.method_51448().method_22909();
            if (this.method_2378(x, y, 16, 16, (double)mouseX, (double)mouseY)) {
               class_465.method_33285(gfx, x, y, 0);
            }

         }
      }
   }

   @Inject(
      method = {"renderTooltip"},
      at = {@At("TAIL")}
   )
   public void renderTooltip(class_332 gfx, int mouseX, int mouseY, CallbackInfo ci) {
      if (Platform.getConfig().isFeatureEnabled(OriginFeature.BACK_SWAG_SLOT) && this instanceof class_490 && OriginUtil.isConnected() && this.field_22787.field_1724.method_31548().method_7391().method_7960()) {
         class_1799 stack = CosmeticEnhancementsFeature.INSTANCE.getBackSwag(class_310.method_1551().field_1724);
         if (!stack.method_7960()) {
            int x = 77;
            int y = 44;
            if (this.method_2378(x, y, 16, 16, (double)mouseX, (double)mouseY)) {
               gfx.method_51446(this.field_22793, stack, mouseX, mouseY);
            }

         }
      }
   }
}
