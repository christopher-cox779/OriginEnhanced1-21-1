package com.originrealms.enhanced.core.networking.adapter;

import com.originrealms.enhanced.networking.OriginPacketBuffer;
import com.originrealms.enhanced.networking.adapter.DataAdapter;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2945.class_2946;

@Environment(EnvType.CLIENT)
public class EntityDataAdapter implements DataAdapter {
   private List<class_2946<?>> data;

   public void read(OriginPacketBuffer buf) {
      throw new UnsupportedOperationException("Synced entity data could not be read");
   }

   public void write(OriginPacketBuffer buf) {
      throw new UnsupportedOperationException("Synced entity data could not be written");
   }

   public List<class_2946<?>> get() {
      return this.data;
   }
}
