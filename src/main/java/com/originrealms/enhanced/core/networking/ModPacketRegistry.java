package com.originrealms.enhanced.core.networking;

import com.originrealms.enhanced.networking.PacketRegistry;
import com.originrealms.enhanced.networking.packet.client.EntityNametagPacket;
import com.originrealms.enhanced.networking.packet.client.HandshakePacket;
import com.originrealms.enhanced.networking.packet.client.SetBackItemPacket;
import com.originrealms.enhanced.networking.packet.client.SpawnMobsPacket;
import com.originrealms.enhanced.networking.packet.client.UpdateEntitiesPropertiesPacket;
import com.originrealms.enhanced.networking.packet.client.UpdateLocationPacket;
import com.originrealms.enhanced.networking.packet.server.HandshakeResponsePacket;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2540;

@Environment(EnvType.CLIENT)
public class ModPacketRegistry extends PacketRegistry<ModPacketBuffer> {
   protected ModPacketRegistry() {
      this.registerInbound(0, HandshakePacket.class, HandshakePacket::new);
      this.registerInbound(1, UpdateLocationPacket.class, UpdateLocationPacket::new);
      this.registerInbound(2, UpdateEntitiesPropertiesPacket.class, UpdateEntitiesPropertiesPacket::new);
      this.registerInbound(3, SpawnMobsPacket.class, SpawnMobsPacket::new);
      this.registerInbound(4, EntityNametagPacket.class, EntityNametagPacket::new);
      this.registerInbound(5, SetBackItemPacket.class, SetBackItemPacket::new);
      this.registerOutbound(0, HandshakeResponsePacket.class, HandshakeResponsePacket::new);
   }

   public ModPacketBuffer createPacketBuffer() {
      return new ModPacketBuffer(new class_2540(Unpooled.buffer()));
   }
}
