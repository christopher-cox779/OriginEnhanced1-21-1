package com.originrealms.enhanced.core.networking.adapter;

import com.originrealms.enhanced.core.networking.ModPacketBuffer;
import com.originrealms.enhanced.core.networking.NetworkHandler;
import com.originrealms.enhanced.networking.OriginPacketBuffer;
import com.originrealms.enhanced.networking.adapter.DataAdapter;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1799;

@Environment(EnvType.CLIENT)
public class ItemAdapter implements DataAdapter {
   private class_1799 item;

   public void read(OriginPacketBuffer buf) {
      this.item = (class_1799)class_1799.field_49268.decode(((ModPacketBuffer)buf).createRegistryBuf(NetworkHandler.INSTANCE.getRegistryAccess()));
   }

   public void write(OriginPacketBuffer buf) {
      class_1799.field_49268.encode(((ModPacketBuffer)buf).createRegistryBuf(NetworkHandler.INSTANCE.getRegistryAccess()), this.item);
   }

   public class_1799 get() {
      return this.item;
   }
}
