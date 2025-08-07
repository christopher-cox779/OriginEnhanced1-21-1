package com.originrealms.enhanced.core.networking;

import com.originrealms.enhanced.networking.OriginPacketBuffer;
import java.util.UUID;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2540;
import net.minecraft.class_5455;
import net.minecraft.class_9129;

@Environment(EnvType.CLIENT)
public class ModPacketBuffer implements OriginPacketBuffer {
   private final class_2540 buf;

   public ModPacketBuffer(class_2540 buf) {
      this.buf = buf;
   }

   public int readVarInt() {
      return this.buf.method_10816();
   }

   public void writeVarInt(int i) {
      this.buf.method_10804(i);
   }

   public int readShort() {
      return this.buf.readShort();
   }

   public void writeShort(int i) {
      this.buf.method_52998(i);
   }

   public boolean readBoolean() {
      return this.buf.readBoolean();
   }

   public void writeBoolean(boolean b) {
      this.buf.method_52964(b);
   }

   public String readString() {
      return this.buf.method_19772();
   }

   public void writeString(String s) {
      this.buf.method_10814(s);
   }

   public float readFloat() {
      return this.buf.readFloat();
   }

   public void writeFloat(float v) {
      this.buf.method_52941(v);
   }

   public byte readByte() {
      return this.buf.readByte();
   }

   public void writeByte(int i) {
      this.buf.method_52997(i);
   }

   public double readDouble() {
      return this.buf.readDouble();
   }

   public void writeDouble(double v) {
      this.buf.method_52940(v);
   }

   public UUID readUUID() {
      return this.buf.method_10790();
   }

   public void writeUUID(UUID uuid) {
      this.buf.method_10797(uuid);
   }

   public class_2540 getBuf() {
      return this.buf;
   }

   public class_9129 createRegistryBuf(class_5455 access) {
      return new class_9129(this.buf, access);
   }
}
