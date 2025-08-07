package com.originrealms.enhanced.core.mixin.client.gui.screens;

import com.originrealms.enhanced.core.feature.screen.FeatureScreen;
import com.originrealms.enhanced.core.util.OriginUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_344;
import net.minecraft.class_433;
import net.minecraft.class_437;
import net.minecraft.class_8666;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin({class_433.class})
public abstract class PauseScreenMixin extends class_437 {
   private static final class_8666 OE_BUTTON_SPRITES = new class_8666(new class_2960("originsenhanced", "config_button"), new class_2960("originsenhanced", "config_button"), new class_2960("originsenhanced", "config_button_focused"));

   public PauseScreenMixin(class_2561 title) {
      super(title);
   }

   @Inject(
      method = {"init"},
      at = {@At("RETURN")}
   )
   public void init(CallbackInfo ci) {
      if (OriginUtil.isConnected()) {
         this.method_37063(new class_344(this.field_22789 / 2 - 126, this.field_22790 / 4 + 96 + -16, 20, 20, OE_BUTTON_SPRITES, (button) -> {
            this.field_22787.method_1507(new FeatureScreen(this));
         }, class_2561.method_43473()));
      }
   }
}
