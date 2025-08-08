package com.originrealms.enhanced.core.util.mixin;

import com.originrealms.enhanced.core.networking.ModPacketHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1799;

@Environment(EnvType.CLIENT)
public interface EntityExtensions {
   float getModWidth();

   void setModWidth(float var1);

   float getModHeight();

   void setModHeight(float var1);

   ModPacketHandler.NameTag getOrCreateEnhancedTag();

   class_1799 getBackItem();

   void setBackItem(class_1799 var1);
}
