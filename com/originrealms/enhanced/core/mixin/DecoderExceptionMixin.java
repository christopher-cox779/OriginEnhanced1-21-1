package com.originrealms.enhanced.core.mixin;

import io.netty.handler.codec.DecoderException;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(
   value = {DecoderException.class},
   remap = false
)
public class DecoderExceptionMixin {
   @Inject(
      method = {"<init>(Ljava/lang/String;Ljava/lang/Throwable;)V"},
      at = {@At("TAIL")},
      remap = false
   )
   public void init(String message, Throwable cause, CallbackInfo ci) {
      System.out.println(message);
      cause.printStackTrace();
   }
}
