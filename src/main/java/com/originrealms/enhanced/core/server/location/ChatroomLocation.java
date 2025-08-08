package com.originrealms.enhanced.core.server.location;

import com.originrealms.enhanced.core.server.OriginLocation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ChatroomLocation extends OriginLocation {
   private final String chatRoom;

   public ChatroomLocation(String chatRoom) {
      super("Chatroom (" + chatRoom + ")");
      this.chatRoom = chatRoom;
   }

   public String getChatRoom() {
      return this.chatRoom;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ChatroomLocation)) {
         return false;
      } else {
         ChatroomLocation other = (ChatroomLocation)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (!super.equals(o)) {
            return false;
         } else {
            Object this$chatRoom = this.getChatRoom();
            Object other$chatRoom = other.getChatRoom();
            if (this$chatRoom == null) {
               if (other$chatRoom != null) {
                  return false;
               }
            } else if (!this$chatRoom.equals(other$chatRoom)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ChatroomLocation;
   }

   public int hashCode() {
      int PRIME = true;
      int result = super.hashCode();
      Object $chatRoom = this.getChatRoom();
      result = result * 59 + ($chatRoom == null ? 43 : $chatRoom.hashCode());
      return result;
   }
}
