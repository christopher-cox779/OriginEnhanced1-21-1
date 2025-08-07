package com.originrealms.enhanced.core;

import com.originrealms.enhanced.core.feature.OriginFeature;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface OriginConfig {
   void setFeatureEnabled(OriginFeature var1, boolean var2);

   boolean isFeatureEnabled(OriginFeature var1);
}
