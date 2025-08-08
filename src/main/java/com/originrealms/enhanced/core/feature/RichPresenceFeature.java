package com.originrealms.enhanced.core.feature;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.DiscordBuild;
import com.jagrosh.discordipc.entities.RichPresence.Builder;
import com.jagrosh.discordipc.entities.RichPresence.Button;
import com.originrealms.enhanced.core.OriginsEnhanced;
import com.originrealms.enhanced.core.bridge.Platform;
import com.originrealms.enhanced.core.server.OriginLocation;
import com.originrealms.enhanced.core.server.OriginStateTracker;
import com.originrealms.enhanced.core.server.location.ChatroomLocation;
import com.originrealms.enhanced.core.server.location.GenericLocation;
import com.originrealms.enhanced.core.server.location.RealmLocation;
import com.originrealms.enhanced.core.server.location.ResourceWorldLocation;
import com.originrealms.enhanced.core.util.OriginUtil;
import java.util.concurrent.TimeUnit;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class RichPresenceFeature {
   public static final RichPresenceFeature INSTANCE = new RichPresenceFeature();
   private long lastUpdate = System.currentTimeMillis();
   private boolean initialized = false;
   private boolean connected = true;
   private IPCClient client = null;

   public void setup() {
      try {
         this.client = new IPCClient(719035723692376126L);
         this.client.setListener(new IPCListener() {
            public void onReady(IPCClient client) {
               RichPresenceFeature.this.initialized = true;
               OriginUtil.EXECUTOR_SERVICE.scheduleAtFixedRate(() -> {
                  RichPresenceFeature.this.handlePresence();
               }, 0L, 2L, TimeUnit.SECONDS);
            }
         });
         this.client.connect(new DiscordBuild[0]);
      } catch (Throwable var2) {
         OriginsEnhanced.LOGGER.error("Failed to connect to Discord", var2);
      }

   }

   private void handlePresence() {
      try {
         class_310 client = class_310.method_1551();
         if (!Platform.getConfig().isFeatureEnabled(OriginFeature.RICH_PRESENCE) || client == null || client.field_1687 == null || !OriginUtil.isConnected()) {
            if (this.connected) {
               this.connected = false;
               this.updatePresence((Builder)null);
            }

            return;
         }

         if (!this.connected) {
            this.connected = true;
         }

         this.update(OriginStateTracker.INSTANCE.getLocation());
      } catch (Throwable var2) {
         OriginsEnhanced.LOGGER.error("Presence update failed", var2);
      }

   }

   public void update(OriginLocation loc) {
      if (this.lastUpdate + 250L <= System.currentTimeMillis()) {
         this.lastUpdate = System.currentTimeMillis();
         if (loc == OriginLocation.UNKNOWN) {
            this.updatePresence(this.createBuilder().setState("play.originrealms.com").setDetails("In Game").setLargeImage("moon", RichPresenceFeature.PresenceImages.MOD_TEXT));
         } else {
            RichPresenceFeature.PresenceImages images;
            if (loc instanceof ChatroomLocation) {
               ChatroomLocation location = (ChatroomLocation)loc;
               images = this.getPresenceImages(location.getChatRoom());
               this.updatePresence(this.createBuilder().setState(location.getChatRoom()).setDetails("In a Chatroom").setLargeImage(images.key, images.text).setSmallImage("speech", RichPresenceFeature.PresenceImages.MOD_TEXT));
            } else if (loc instanceof RealmLocation) {
               RealmLocation realm = (RealmLocation)loc;
               this.updatePresence(this.createBuilder().setState(realm.getUsername()).setDetails(realm.isOwner() ? "In their Realm" : "Visiting a realm").setLargeImage("realms", "In a Realm").setSmallImage("moon", RichPresenceFeature.PresenceImages.MOD_TEXT));
            } else if (loc instanceof ResourceWorldLocation) {
               ResourceWorldLocation resourceWorld = (ResourceWorldLocation)loc;
               this.updatePresence(this.createBuilder().setDetails("In a Resource World").setLargeImage("resource", resourceWorld.getDimension()).setSmallImage("moon", RichPresenceFeature.PresenceImages.MOD_TEXT));
            } else if (loc instanceof GenericLocation) {
               GenericLocation generic = (GenericLocation)loc;
               images = this.getPresenceImages(generic.getLocation());
               boolean isIsles = generic.getLocation().equals("Origin Isles");
               this.updatePresence(this.createBuilder().setState(isIsles ? "At Origin Isles" : generic.getLocation()).setDetails(isIsles ? null : "At Origin Isles").setLargeImage(images.key, images.text).setSmallImage(images.smallKey, images.smallText));
            }
         }

      }
   }

   public void updatePresence(Builder presence) {
      if (this.initialized) {
         this.client.sendRichPresence(presence == null ? null : presence.build());
      }
   }

   private RichPresenceFeature.PresenceImages getPresenceImages(String location) {
      return location == null ? RichPresenceFeature.PresenceImages.FALLBACK : (RichPresenceFeature.PresenceImages)OriginsEnhanced.getRegistry().getLocation(location).map((data) -> {
         return new RichPresenceFeature.PresenceImages(data.getPresenceIcon(), location);
      }).orElse(RichPresenceFeature.PresenceImages.FALLBACK);
   }

   private Builder createBuilder() {
      return (new Builder()).setStartTimestamp(OriginStateTracker.INSTANCE.getLoginTime() / 1000L).setButtons(new Button[]{new Button("Join Origin Realms", "https://originrealms.com"), new Button("Get Origins Enhanced", "https://originrealms.com/enhanced")});
   }

   @Environment(EnvType.CLIENT)
   private static class PresenceImages {
      private static final String MOD_TEXT;
      private static final String MOD_KEY = "moon";
      private static final RichPresenceFeature.PresenceImages FALLBACK;
      private final String key;
      private final String text;
      private final String smallKey;
      private final String smallText;

      private PresenceImages(@Nullable String key, @Nullable String text) {
         this.key = key == null ? "moon" : key;
         this.text = text == null ? MOD_TEXT : text;
         this.smallKey = key == null ? null : "moon";
         this.smallText = key == null ? null : MOD_TEXT;
      }

      static {
         String var10000 = Platform.getName();
         MOD_TEXT = var10000 + " " + Platform.getDisplayVersion();
         FALLBACK = new RichPresenceFeature.PresenceImages((String)null, (String)null);
      }
   }
}
