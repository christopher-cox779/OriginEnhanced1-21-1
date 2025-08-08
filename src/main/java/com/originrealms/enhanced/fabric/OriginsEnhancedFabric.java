package com.originrealms.enhanced.fabric;

import com.originrealms.enhanced.core.OriginsEnhanced;
import com.originrealms.enhanced.fabric.config.OriginFabricConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;

@Environment(EnvType.CLIENT)
public class OriginsEnhancedFabric implements ClientModInitializer {
   public static final ModMetadata METADATA = (ModMetadata)FabricLoader.getInstance().getModContainer("originsenhanced").map(ModContainer::getMetadata).orElse((Object)null);

   public void onInitializeClient() {
      OriginFabricConfig.INSTANCE.setup();
      OriginsEnhanced.setup();
      OriginsEnhanced.postSetup();
   }
}
