package com.originrealms.enhanced.core.server.location;

import com.originrealms.enhanced.core.server.OriginLocation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;

@Environment(EnvType.CLIENT)
public class RealmLocation extends OriginLocation {
   private final String username;
   private final boolean owner;

   public RealmLocation(String username) {
      super("Realm (" + username + ")");
      this.username = username;
      this.owner = class_310.method_1551().method_1548().method_1676().equalsIgnoreCase(username);
   }

   public String getUsername() {
      return this.username;
   }

   public boolean isOwner() {
      return this.owner;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RealmLocation)) {
         return false;
      } else {
         RealmLocation other = (RealmLocation)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (!super.equals(o)) {
            return false;
         } else if (this.isOwner() != other.isOwner()) {
            return false;
         } else {
            Object this$username = this.getUsername();
            Object other$username = other.getUsername();
            if (this$username == null) {
               if (other$username == null) {
                  return true;
               }
            } else if (this$username.equals(other$username)) {
               return true;
            }

            return false;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof RealmLocation;
   }

   public int hashCode() {
      int PRIME = true;
      int result = super.hashCode();
      result = result * 59 + (this.isOwner() ? 79 : 97);
      Object $username = this.getUsername();
      result = result * 59 + ($username == null ? 43 : $username.hashCode());
      return result;
   }
}
