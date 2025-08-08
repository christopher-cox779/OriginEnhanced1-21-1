package com.originrealms.enhanced.core.server;

import com.originrealms.enhanced.core.bridge.Platform;
import com.originrealms.enhanced.core.util.OriginUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2680;
import net.minecraft.class_2769;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class RemoteRegistry {
   private static final String REMOTE_URL = "https://raw.githubusercontent.com/OriginsEnhanced/assets/master/originsenhanced/assets/data.json";
   private final Map<String, RemoteRegistry.LocationData> locations;
   private final Map<String, RemoteRegistry.CustomBlock> blocks;
   private final Map<String, RemoteRegistry.CustomRank> ranks;

   @Nullable
   public static RemoteRegistry fetch() {
      if (!Platform.isDevelopmentEnvironment()) {
         try {
            CloseableHttpClient client = HttpClients.createDefault();

            RemoteRegistry var4;
            try {
               HttpGet request = new HttpGet("https://raw.githubusercontent.com/OriginsEnhanced/assets/master/originsenhanced/assets/data.json");
               HttpResponse response = client.execute(request);
               if (response.getStatusLine().getStatusCode() != 200) {
                  throw new HttpResponseException(response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
               }

               InputStreamReader reader = new InputStreamReader(response.getEntity().getContent());

               try {
                  var4 = (RemoteRegistry)OriginUtil.GSON.fromJson(reader, RemoteRegistry.class);
               } catch (Throwable var8) {
                  try {
                     reader.close();
                  } catch (Throwable var7) {
                     var8.addSuppressed(var7);
                  }

                  throw var8;
               }

               reader.close();
            } catch (Throwable var9) {
               if (client != null) {
                  try {
                     client.close();
                  } catch (Throwable var6) {
                     var9.addSuppressed(var6);
                  }
               }

               throw var9;
            }

            if (client != null) {
               client.close();
            }

            return var4;
         } catch (IOException var10) {
            var10.printStackTrace();
         }
      }

      return readLocal();
   }

   @Nullable
   private static RemoteRegistry readLocal() {
      InputStream resource = RemoteRegistry.class.getResourceAsStream("/data/originsenhanced/remote/data.json");
      if (resource == null) {
         return null;
      } else {
         try {
            InputStreamReader reader = new InputStreamReader(resource);

            RemoteRegistry var2;
            try {
               var2 = (RemoteRegistry)OriginUtil.GSON.fromJson(reader, RemoteRegistry.class);
            } catch (Throwable var5) {
               try {
                  reader.close();
               } catch (Throwable var4) {
                  var5.addSuppressed(var4);
               }

               throw var5;
            }

            reader.close();
            return var2;
         } catch (IOException var6) {
            var6.printStackTrace();
            return null;
         }
      }
   }

   public Optional<RemoteRegistry.LocationData> getLocation(String location) {
      return Optional.ofNullable((RemoteRegistry.LocationData)this.locations.get(location));
   }

   public Optional<RemoteRegistry.CustomRank> getRank(String text) {
      return this.ranks.values().stream().filter((v) -> {
         return v.text.equalsIgnoreCase(text);
      }).findFirst();
   }

   public Optional<RemoteRegistry.CustomBlock> getBlock(class_2680 state) {
      Map<String, ? extends Comparable<?>> values = (Map)state.method_11656().entrySet().stream().collect(Collectors.toMap((e) -> {
         return ((class_2769)e.getKey()).method_11899();
      }, (e) -> {
         Object patt0$temp = e.getValue();
         Object var10000;
         if (patt0$temp instanceof Enum) {
            Enum<?> val = (Enum)patt0$temp;
            var10000 = val.name();
         } else {
            var10000 = (Comparable)e.getValue();
         }

         return (Comparable)var10000;
      }));
      return this.blocks.values().stream().filter((block) -> {
         Collection<Object> remapped = (Collection)block.state.values().stream().map((o) -> {
            Object var10000;
            if (o instanceof Double) {
               Double val = (Double)o;
               var10000 = val.intValue();
            } else {
               var10000 = o;
            }

            return var10000;
         }).collect(Collectors.toList());
         return block.state.keySet().equals(values.keySet()) && remapped.containsAll(values.values());
      }).findFirst();
   }

   public RemoteRegistry(Map<String, RemoteRegistry.LocationData> locations, Map<String, RemoteRegistry.CustomBlock> blocks, Map<String, RemoteRegistry.CustomRank> ranks) {
      this.locations = locations;
      this.blocks = blocks;
      this.ranks = ranks;
   }

   public Map<String, RemoteRegistry.LocationData> getLocations() {
      return this.locations;
   }

   public Map<String, RemoteRegistry.CustomBlock> getBlocks() {
      return this.blocks;
   }

   public Map<String, RemoteRegistry.CustomRank> getRanks() {
      return this.ranks;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RemoteRegistry)) {
         return false;
      } else {
         RemoteRegistry other = (RemoteRegistry)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            label47: {
               Object this$locations = this.getLocations();
               Object other$locations = other.getLocations();
               if (this$locations == null) {
                  if (other$locations == null) {
                     break label47;
                  }
               } else if (this$locations.equals(other$locations)) {
                  break label47;
               }

               return false;
            }

            Object this$blocks = this.getBlocks();
            Object other$blocks = other.getBlocks();
            if (this$blocks == null) {
               if (other$blocks != null) {
                  return false;
               }
            } else if (!this$blocks.equals(other$blocks)) {
               return false;
            }

            Object this$ranks = this.getRanks();
            Object other$ranks = other.getRanks();
            if (this$ranks == null) {
               if (other$ranks != null) {
                  return false;
               }
            } else if (!this$ranks.equals(other$ranks)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof RemoteRegistry;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      Object $locations = this.getLocations();
      int result = result * 59 + ($locations == null ? 43 : $locations.hashCode());
      Object $blocks = this.getBlocks();
      result = result * 59 + ($blocks == null ? 43 : $blocks.hashCode());
      Object $ranks = this.getRanks();
      result = result * 59 + ($ranks == null ? 43 : $ranks.hashCode());
      return result;
   }

   public String toString() {
      String var10000 = String.valueOf(this.getLocations());
      return "RemoteRegistry(locations=" + var10000 + ", blocks=" + String.valueOf(this.getBlocks()) + ", ranks=" + String.valueOf(this.getRanks()) + ")";
   }

   @Environment(EnvType.CLIENT)
   public static class LocationData {
      private final String presenceIcon;
      private boolean chatroom = false;

      public LocationData(String presenceIcon) {
         this.presenceIcon = presenceIcon;
      }

      public String getPresenceIcon() {
         return this.presenceIcon;
      }

      public boolean isChatroom() {
         return this.chatroom;
      }

      public void setChatroom(boolean chatroom) {
         this.chatroom = chatroom;
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof RemoteRegistry.LocationData)) {
            return false;
         } else {
            RemoteRegistry.LocationData other = (RemoteRegistry.LocationData)o;
            if (!other.canEqual(this)) {
               return false;
            } else if (this.isChatroom() != other.isChatroom()) {
               return false;
            } else {
               Object this$presenceIcon = this.getPresenceIcon();
               Object other$presenceIcon = other.getPresenceIcon();
               if (this$presenceIcon == null) {
                  if (other$presenceIcon != null) {
                     return false;
                  }
               } else if (!this$presenceIcon.equals(other$presenceIcon)) {
                  return false;
               }

               return true;
            }
         }
      }

      protected boolean canEqual(Object other) {
         return other instanceof RemoteRegistry.LocationData;
      }

      public int hashCode() {
         int PRIME = true;
         int result = 1;
         int result = result * 59 + (this.isChatroom() ? 79 : 97);
         Object $presenceIcon = this.getPresenceIcon();
         result = result * 59 + ($presenceIcon == null ? 43 : $presenceIcon.hashCode());
         return result;
      }

      public String toString() {
         String var10000 = this.getPresenceIcon();
         return "RemoteRegistry.LocationData(presenceIcon=" + var10000 + ", chatroom=" + this.isChatroom() + ")";
      }
   }

   @Environment(EnvType.CLIENT)
   public static class CustomBlock {
      private final Map<String, Object> state;
      private boolean intractable = false;

      public CustomBlock(Map<String, Object> state) {
         this.state = state;
      }

      public Map<String, Object> getState() {
         return this.state;
      }

      public boolean isIntractable() {
         return this.intractable;
      }

      public void setIntractable(boolean intractable) {
         this.intractable = intractable;
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof RemoteRegistry.CustomBlock)) {
            return false;
         } else {
            RemoteRegistry.CustomBlock other = (RemoteRegistry.CustomBlock)o;
            if (!other.canEqual(this)) {
               return false;
            } else if (this.isIntractable() != other.isIntractable()) {
               return false;
            } else {
               Object this$state = this.getState();
               Object other$state = other.getState();
               if (this$state == null) {
                  if (other$state != null) {
                     return false;
                  }
               } else if (!this$state.equals(other$state)) {
                  return false;
               }

               return true;
            }
         }
      }

      protected boolean canEqual(Object other) {
         return other instanceof RemoteRegistry.CustomBlock;
      }

      public int hashCode() {
         int PRIME = true;
         int result = 1;
         int result = result * 59 + (this.isIntractable() ? 79 : 97);
         Object $state = this.getState();
         result = result * 59 + ($state == null ? 43 : $state.hashCode());
         return result;
      }

      public String toString() {
         String var10000 = String.valueOf(this.getState());
         return "RemoteRegistry.CustomBlock(state=" + var10000 + ", intractable=" + this.isIntractable() + ")";
      }
   }

   @Environment(EnvType.CLIENT)
   public static class CustomRank {
      private final String text;
      private boolean staff = false;

      public CustomRank(String text) {
         this.text = text;
      }

      public String getText() {
         return this.text;
      }

      public boolean isStaff() {
         return this.staff;
      }

      public void setStaff(boolean staff) {
         this.staff = staff;
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof RemoteRegistry.CustomRank)) {
            return false;
         } else {
            RemoteRegistry.CustomRank other = (RemoteRegistry.CustomRank)o;
            if (!other.canEqual(this)) {
               return false;
            } else if (this.isStaff() != other.isStaff()) {
               return false;
            } else {
               Object this$text = this.getText();
               Object other$text = other.getText();
               if (this$text == null) {
                  if (other$text != null) {
                     return false;
                  }
               } else if (!this$text.equals(other$text)) {
                  return false;
               }

               return true;
            }
         }
      }

      protected boolean canEqual(Object other) {
         return other instanceof RemoteRegistry.CustomRank;
      }

      public int hashCode() {
         int PRIME = true;
         int result = 1;
         int result = result * 59 + (this.isStaff() ? 79 : 97);
         Object $text = this.getText();
         result = result * 59 + ($text == null ? 43 : $text.hashCode());
         return result;
      }

      public String toString() {
         String var10000 = this.getText();
         return "RemoteRegistry.CustomRank(text=" + var10000 + ", staff=" + this.isStaff() + ")";
      }
   }
}
