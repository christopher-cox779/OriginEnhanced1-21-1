package com.originrealms.enhanced.core.networking;

import com.originrealms.enhanced.core.networking.adapter.EntityDataAdapter;
import com.originrealms.enhanced.core.networking.adapter.ItemAdapter;
import com.originrealms.enhanced.core.networking.adapter.TextComponentAdapter;
import com.originrealms.enhanced.networking.OriginPacket;
import com.originrealms.enhanced.networking.PacketRegistry;
import com.originrealms.enhanced.networking.adapter.AdapterTypes;
import com.originrealms.enhanced.networking.packet.server.HandshakeResponsePacket;
import java.util.concurrent.CompletableFuture;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientLoginNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.class_2540;
import net.minecraft.class_2960;
import net.minecraft.class_5455;
import net.minecraft.class_9129;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Environment(EnvType.CLIENT)
public class NetworkHandler {
   public static final NetworkHandler INSTANCE = new NetworkHandler();
   public static final class_2960 DATA_CHANNEL = new class_2960("originrealms", "enhanced");
   private static final Logger LOGGER = LogManager.getLogger();
   private final PacketRegistry<ModPacketBuffer> registry = new ModPacketRegistry();
   private boolean handshakeComplete = false;
   private boolean outdated = false;
   private class_5455 registryAccess;

   public void setup() {
      AdapterTypes.ENTITY_DATA.setAdapter(EntityDataAdapter::new);
      AdapterTypes.ITEM.setAdapter(ItemAdapter::new);
      AdapterTypes.TEXT_COMPONENT.setAdapter(TextComponentAdapter::new);
      PayloadTypeRegistry.playC2S().register(ModPacketPayload.PACKET_TYPE, ModPacketPayload.CODEC);
      PayloadTypeRegistry.playS2C().register(ModPacketPayload.PACKET_TYPE, ModPacketPayload.CODEC);
      ClientPlayNetworking.registerGlobalReceiver(ModPacketPayload.PACKET_TYPE, (payload, ctx) -> {
         this.handle(payload.packet());
      });
      ClientLoginNetworking.registerGlobalReceiver(DATA_CHANNEL, (client, handler, buf, listenerAdder) -> {
         this.readAndHandle(buf);
         if (INSTANCE.isHandshakeComplete()) {
            ModPacketBuffer responseBuf = (ModPacketBuffer)this.registry.write(new HandshakeResponsePacket(5));
            return CompletableFuture.completedFuture(responseBuf.getBuf());
         } else {
            return null;
         }
      });
      ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
         this.handshakeComplete = false;
         this.outdated = false;
      });
   }

   public class_5455 getRegistryAccess() {
      if (this.registryAccess == null) {
         throw new IllegalStateException("Registry accessed too soon");
      } else {
         return this.registryAccess;
      }
   }

   public OriginPacket read(class_9129 buf) {
      try {
         this.registryAccess = buf.method_56349();
         return this.registry.read(new ModPacketBuffer(buf));
      } catch (Throwable var3) {
         throw new IllegalStateException("Failed to parse custom packet", var3);
      }
   }

   public void readAndHandle(class_2540 buf) {
      try {
         OriginPacket packet = this.registry.read(new ModPacketBuffer(buf));
         packet.handle(ModPacketHandler.INSTANCE);
      } catch (Throwable var3) {
         throw new IllegalStateException("Failed to parse custom packet", var3);
      }
   }

   public void handle(OriginPacket packet) {
      try {
         packet.handle(ModPacketHandler.INSTANCE);
      } catch (Throwable var3) {
         throw new IllegalStateException("Failed to handle custom packet", var3);
      }
   }

   public PacketRegistry<ModPacketBuffer> getRegistry() {
      return this.registry;
   }

   public void setHandshakeComplete(boolean handshakeComplete) {
      this.handshakeComplete = handshakeComplete;
   }

   public boolean isHandshakeComplete() {
      return this.handshakeComplete;
   }

   public void setOutdated(boolean outdated) {
      this.outdated = outdated;
   }

   public boolean isOutdated() {
      return this.outdated;
   }
}
