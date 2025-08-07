package com.originrealms.enhanced.core.networking;

import com.originrealms.enhanced.networking.OriginPacket;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2540;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;

@Environment(EnvType.CLIENT)
public record ModPacketPayload(OriginPacket packet) implements class_8710 {
   public static final class_9139<class_9129, ModPacketPayload> CODEC = class_8710.method_56484(ModPacketPayload::write, ModPacketPayload::new);
   public static final class_9154<ModPacketPayload> PACKET_TYPE;

   public ModPacketPayload(class_9129 buf) {
      this(NetworkHandler.INSTANCE.read(buf));
   }

   public ModPacketPayload(OriginPacket packet) {
      this.packet = packet;
   }

   public void write(class_2540 buf) {
      try {
         ModPacketBuffer buffer = (ModPacketBuffer)NetworkHandler.INSTANCE.getRegistry().write(this.packet);
         byte[] bytes = buffer.getBuf().array();
         buf.method_52983(bytes);
      } catch (Throwable var4) {
         throw new IllegalStateException("Failed to write custom packet", var4);
      }
   }

   public class_9154<? extends class_8710> method_56479() {
      return PACKET_TYPE;
   }

   public OriginPacket packet() {
      return this.packet;
   }

   static {
      PACKET_TYPE = new class_9154(NetworkHandler.DATA_CHANNEL);
   }
}
