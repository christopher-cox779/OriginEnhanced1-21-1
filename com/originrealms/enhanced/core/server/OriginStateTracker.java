package com.originrealms.enhanced.core.server;

import com.originrealms.enhanced.core.OriginsEnhanced;
import com.originrealms.enhanced.core.bridge.Platform;
import com.originrealms.enhanced.core.feature.OriginFeature;
import com.originrealms.enhanced.core.feature.RichPresenceFeature;
import com.originrealms.enhanced.core.server.location.ChatroomLocation;
import com.originrealms.enhanced.core.server.location.GenericLocation;
import com.originrealms.enhanced.core.server.location.RealmLocation;
import com.originrealms.enhanced.core.server.location.ResourceWorldLocation;
import com.originrealms.enhanced.core.util.OriginUtil;
import com.originrealms.enhanced.core.util.TextUtil;
import java.util.regex.Matcher;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

@Environment(EnvType.CLIENT)
public class OriginStateTracker {
   public static final OriginStateTracker INSTANCE = new OriginStateTracker();
   private OriginLocation location;
   private long loginTime;

   public OriginStateTracker() {
      this.location = OriginLocation.UNKNOWN;
      this.loginTime = -1L;
   }

   public void setup() {
      ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
         this.loginTime = -1L;
      });
      ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
         if (OriginUtil.isConnected()) {
            this.loginTime = System.currentTimeMillis();
         }
      });
   }

   public void update(String location) {
      if (OriginUtil.isConnected()) {
         if (Platform.getConfig().isFeatureEnabled(OriginFeature.LOCATION_TRACKER) && location != null) {
            RemoteRegistry.LocationData locationData = (RemoteRegistry.LocationData)OriginsEnhanced.getRegistry().getLocation(location).orElse((Object)null);
            this.location = (OriginLocation)(locationData != null && locationData.isChatroom() ? new ChatroomLocation(location) : new GenericLocation(location));
            if (location.equals("Resource World")) {
               this.location = new ResourceWorldLocation(location);
            }

            Matcher realmMatcher = TextUtil.REALM_BOSS_BAR.matcher(location);
            if (realmMatcher.find()) {
               this.location = new RealmLocation(realmMatcher.group("username"));
            }

            if (Platform.getConfig().isFeatureEnabled(OriginFeature.RICH_PRESENCE)) {
               RichPresenceFeature.INSTANCE.update(this.location);
            }

         } else {
            this.location = OriginLocation.UNKNOWN;
         }
      }
   }

   public OriginLocation getLocation() {
      return this.location;
   }

   public long getLoginTime() {
      return this.loginTime;
   }
}
