package com.originrealms.enhanced.core.server;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class OriginLocation {
   public static final OriginLocation UNKNOWN = new OriginLocation("Unknown");
   private final String name;

   public String toString() {
      return this.name;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof OriginLocation)) {
         return false;
      } else {
         OriginLocation other = (OriginLocation)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof OriginLocation;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      Object $name = this.getName();
      int result = result * 59 + ($name == null ? 43 : $name.hashCode());
      return result;
   }

   public OriginLocation(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }
}
