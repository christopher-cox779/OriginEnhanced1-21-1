package com.originrealms.enhanced.core.networking.adapter;

import com.originrealms.enhanced.core.networking.ModPacketBuffer;
import com.originrealms.enhanced.core.networking.NetworkHandler;
import com.originrealms.enhanced.networking.OriginPacketBuffer;
import com.originrealms.enhanced.networking.adapter.DataAdapter;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_8824;

@Environment(EnvType.CLIENT)
public class TextComponentAdapter implements DataAdapter {
   private class_2561 component;

   public void read(OriginPacketBuffer buf) {
      this.component = (class_2561)class_8824.field_49666.decode(((ModPacketBuffer)buf).createRegistryBuf(NetworkHandler.INSTANCE.getRegistryAccess()));
   }

   public void write(OriginPacketBuffer buf) {
      class_8824.field_49666.encode(((ModPacketBuffer)buf).createRegistryBuf(NetworkHandler.INSTANCE.getRegistryAccess()), this.component);
   }

   public class_2561 get() {
      return this.component;
   }
}
