package com.originrealms.enhanced.core.mixin.client.multiplayer;

import com.originrealms.enhanced.core.bridge.Platform;
import com.originrealms.enhanced.core.feature.OriginFeature;
import com.originrealms.enhanced.core.mixin.client.gui.screens.ChatScreenAccessor;
import com.originrealms.enhanced.core.util.OriginUtil;
import java.util.Collection;
import java.util.stream.Collectors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;
import net.minecraft.class_408;
import net.minecraft.class_437;
import net.minecraft.class_634;
import net.minecraft.class_637;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin({class_637.class})
public abstract class ClientSuggestionProviderMixin {
   @Shadow
   @Final
   private class_634 field_3722;

   @Inject(
      method = {"getOnlinePlayerNames"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void getOnlinePlayerNames(CallbackInfoReturnable<Collection<String>> ci) {
      if (Platform.getConfig().isFeatureEnabled(OriginFeature.AUTO_COMPLETION_ENHANCEMENTS) && OriginUtil.isConnected()) {
         class_437 screen = class_310.method_1551().field_1755;
         if (screen instanceof class_408) {
            class_408 chat = (class_408)screen;
            ChatScreenAccessor access = (ChatScreenAccessor)chat;
            String[] split = access.getInput().method_1882().split(" ");
            if (split.length > 0) {
               String word = split[split.length - 1];
               if (!access.getInput().method_1882().endsWith(" ")) {
                  if (word.startsWith("@")) {
                     ci.setReturnValue((Collection)this.field_3722.method_2880().stream().filter((info) -> {
                        return !info.method_2966().getName().startsWith(":");
                     }).map((info) -> {
                        return "@" + info.method_2966().getName();
                     }).collect(Collectors.toList()));
                     return;
                  }

                  if (word.startsWith(":")) {
                     ci.setReturnValue((Collection)this.field_3722.method_2880().stream().map((info) -> {
                        return info.method_2966().getName();
                     }).filter((s) -> {
                        return s.startsWith(":");
                     }).collect(Collectors.toList()));
                     return;
                  }
               }
            }
         }

         ci.setReturnValue((Collection)this.field_3722.method_2880().stream().map((info) -> {
            return info.method_2966().getName();
         }).filter((s) -> {
            return !s.startsWith(":");
         }).collect(Collectors.toList()));
      }
   }
}
