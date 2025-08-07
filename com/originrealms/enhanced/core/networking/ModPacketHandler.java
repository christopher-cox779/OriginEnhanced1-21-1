package com.originrealms.enhanced.core.networking;

import com.originrealms.enhanced.core.bridge.Platform;
import com.originrealms.enhanced.core.networking.adapter.ItemAdapter;
import com.originrealms.enhanced.core.networking.adapter.TextComponentAdapter;
import com.originrealms.enhanced.core.server.OriginStateTracker;
import com.originrealms.enhanced.core.util.OriginToast;
import com.originrealms.enhanced.core.util.mixin.EntityExtensions;
import com.originrealms.enhanced.networking.PacketHandler;
import com.originrealms.enhanced.networking.packet.client.EntityNametagPacket;
import com.originrealms.enhanced.networking.packet.client.HandshakePacket;
import com.originrealms.enhanced.networking.packet.client.SetBackItemPacket;
import com.originrealms.enhanced.networking.packet.client.SpawnMobsPacket;
import com.originrealms.enhanced.networking.packet.client.UpdateEntitiesPropertiesPacket;
import com.originrealms.enhanced.networking.packet.client.UpdateLocationPacket;
import com.originrealms.enhanced.networking.packet.client.EntityNametagPacket.Action;
import com.originrealms.enhanced.networking.packet.client.EntityNametagPacket.AddLine;
import com.originrealms.enhanced.networking.packet.client.EntityNametagPacket.RemoveLine;
import com.originrealms.enhanced.networking.packet.client.EntityNametagPacket.UpdateLine;
import com.originrealms.enhanced.networking.packet.client.EntityNametagPacket.UpdateOffset;
import java.util.Objects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1799;
import net.minecraft.class_2371;
import net.minecraft.class_2561;
import net.minecraft.class_310;

@Environment(EnvType.CLIENT)
public class ModPacketHandler implements PacketHandler {
   public static final ModPacketHandler INSTANCE = new ModPacketHandler();

   public void onHandshake(HandshakePacket packet) {
      if (packet.getVersion() == 5) {
         NetworkHandler.INSTANCE.setHandshakeComplete(true);
      } else {
         NetworkHandler.INSTANCE.setOutdated(true);
         class_310.method_1551().method_1566().method_1999(OriginToast.multiline(class_310.method_1551(), class_2561.method_43470(Platform.getName()), class_2561.method_43471("feature.originsenhanced.network.toast.outdated")));
      }
   }

   public void onUpdateLocation(UpdateLocationPacket packet) {
      OriginStateTracker.INSTANCE.update(packet.getLocation());
   }

   public void onUpdateEntitiesProperties(UpdateEntitiesPropertiesPacket packet) {
      throw new UnsupportedOperationException("Invalid packet: UpdateEntitiesPropertiesPacket");
   }

   public void onSpawnMobs(SpawnMobsPacket packet) {
      throw new UnsupportedOperationException("Invalid packet: SpawnMobsPacket");
   }

   public void onEntityNametag(EntityNametagPacket packet) {
      class_310 minecraft = class_310.method_1551();
      int id = packet.getEntityId();
      if (minecraft.field_1687 != null && minecraft.field_1687.method_8469(id) != null) {
         Action action = packet.getAction();

         try {
            ModPacketHandler.NameTag tag = ((EntityExtensions)Objects.requireNonNull(minecraft.field_1687.method_8469(id))).getOrCreateEnhancedTag();
            if (action instanceof AddLine) {
               AddLine act = (AddLine)action;
               tag.addLine(((TextComponentAdapter)act.getText()).get());
            } else if (action instanceof UpdateLine) {
               UpdateLine act = (UpdateLine)action;
               tag.setLine(act.getIndex(), ((TextComponentAdapter)act.getText()).get());
            } else if (action instanceof RemoveLine) {
               RemoveLine act = (RemoveLine)action;
               tag.removeLine(act.getIndex());
            } else if (action instanceof UpdateOffset) {
               UpdateOffset act = (UpdateOffset)action;
               tag.setOffset((float)act.getOffset());
            }
         } catch (Throwable var10) {
            var10.printStackTrace();
         }

      }
   }

   public void onSetBackItem(SetBackItemPacket packet) {
      class_310 minecraft = class_310.method_1551();
      int id = packet.getEntityId();
      if (minecraft.field_1687 != null && minecraft.field_1687.method_8469(id) != null) {
         class_1799 stack = ((ItemAdapter)packet.getItemStack()).get();
         ((EntityExtensions)Objects.requireNonNull(minecraft.field_1687.method_8469(id))).setBackItem(stack == class_1799.field_8037 ? null : stack);
      }
   }

   @Environment(EnvType.CLIENT)
   public static class NameTag {
      private final class_2371<class_2561> lines = class_2371.method_10211();
      private float offset;

      public void addLine(class_2561 component) {
         this.lines.add(component);
      }

      public void setLine(int line, class_2561 component) {
         this.lines.set(line, component);
      }

      public void removeLine(int line) {
         this.lines.remove(line);
      }

      public class_2371<class_2561> getLines() {
         return this.lines;
      }

      public float getOffset() {
         return this.offset;
      }

      public void setOffset(float offset) {
         this.offset = Math.max(0.0F, offset);
      }
   }
}
