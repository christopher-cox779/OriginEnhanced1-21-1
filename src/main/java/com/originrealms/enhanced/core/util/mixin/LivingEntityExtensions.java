package com.originrealms.enhanced.core.util.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface LivingEntityExtensions {
   void lerpRotTo(double var1, double var3, int var5);

   void lerpPosTo(double var1, double var3, double var5, int var7);
}
