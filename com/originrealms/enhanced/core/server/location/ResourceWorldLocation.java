package com.originrealms.enhanced.core.server.location;

import com.originrealms.enhanced.core.server.OriginLocation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ResourceWorldLocation extends OriginLocation {
   private final String dimension;

   public ResourceWorldLocation(String dimension) {
      super("Resource World (" + dimension + ")");
      this.dimension = dimension;
   }

   public String getDimension() {
      return this.dimension;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourceWorldLocation)) {
         return false;
      } else {
         ResourceWorldLocation other = (ResourceWorldLocation)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (!super.equals(o)) {
            return false;
         } else {
            Object this$dimension = this.getDimension();
            Object other$dimension = other.getDimension();
            if (this$dimension == null) {
               if (other$dimension != null) {
                  return false;
               }
            } else if (!this$dimension.equals(other$dimension)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ResourceWorldLocation;
   }

   public int hashCode() {
      int PRIME = true;
      int result = super.hashCode();
      Object $dimension = this.getDimension();
      result = result * 59 + ($dimension == null ? 43 : $dimension.hashCode());
      return result;
   }
}
