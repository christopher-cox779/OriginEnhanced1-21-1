package com.originrealms.enhanced.core.server.location;

import com.originrealms.enhanced.core.server.OriginLocation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class GenericLocation extends OriginLocation {
   private final String location;

   public GenericLocation(String location) {
      super("Generic (" + location + ")");
      this.location = location;
   }

   public String getLocation() {
      return this.location;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof GenericLocation)) {
         return false;
      } else {
         GenericLocation other = (GenericLocation)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (!super.equals(o)) {
            return false;
         } else {
            Object this$location = this.getLocation();
            Object other$location = other.getLocation();
            if (this$location == null) {
               if (other$location != null) {
                  return false;
               }
            } else if (!this$location.equals(other$location)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof GenericLocation;
   }

   public int hashCode() {
      int PRIME = true;
      int result = super.hashCode();
      Object $location = this.getLocation();
      result = result * 59 + ($location == null ? 43 : $location.hashCode());
      return result;
   }
}
