package com.originrealms.enhanced.core.feature;

import com.google.common.base.Suppliers;
import java.util.Arrays;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public enum OriginFeature {
   RICH_PRESENCE(OriginFeature.Category.FEATURES, "Rich Presence", new String[]{"Display your current network location as your Discord rich presence"}),
   LOCATION_TRACKER(OriginFeature.Category.FEATURES, "Location Tracker", new String[]{"Tracks your location across the network and provides your location to other features"}),
   SMOOTH_ANIMATIONS(OriginFeature.Category.ENHANCEMENTS, "Smooth Animations", new String[]{"Interpolate armor stand poses to create smoother animations"}),
   BLOCK_ENHANCEMENTS(OriginFeature.Category.ENHANCEMENTS, "Block Enhancements", new String[]{"Fixes visual issues with custom blocks", "§7* Foliage no longer renders incorrectly (Requires restart)", "§7* Removed right click action on non-interactable blocks"}),
   AUTO_COMPLETION_ENHANCEMENTS(OriginFeature.Category.ENHANCEMENTS, "Auto Completion Enhancements", new String[]{"Improves auto completion", "§7* Removes emojis from autocompletion without a colon", "§7* Adds autocompletion for pings"}),
   BACK_SWAG_SLOT(OriginFeature.Category.ENHANCEMENTS, "Back Swag Slot", new String[]{"Adds a back swag slot to your inventory"}),
   GESTURE_COMPATIBILITY(OriginFeature.Category.ENHANCEMENTS, "Gestures Compatibility", new String[]{"Improves client-side gesture compatibility", "§7* Natively renders gesture parts to add compatibility for shaders", "§7* Allows players to walk with mobile gestures using WASD"}),
   COSMETIC_LOCKING(OriginFeature.Category.ENHANCEMENTS, "Cosmetic Locking", new String[]{"Prevent cosmetics from being clicked out of their slots"}),
   CUTSCENE_ENHANCEMENTS(OriginFeature.Category.ENHANCEMENTS, "Cutscene Enhancements", new String[]{"Enables minor tweaks to improve cutscenes", "§7* Sets FOV to 70 for fine tuning", "§7* Locks mouse in place to prevent moving during cutscenes"}),
   EXPAND_MODEL_BOUNDING_BOX(OriginFeature.Category.ENHANCEMENTS, "Expand Model Bounding Box", new String[]{"Prevent models from cutting off by expanding their bounding boxes"}),
   HIDE_NAMES(OriginFeature.Category.ENHANCEMENTS, "Hide Names", new String[]{"When the HUD is hidden, hide custom nametags for cleaner screenshots"}),
   CUSTOM_ITEM_IDS(OriginFeature.Category.ENHANCEMENTS, "Custom Item IDs", new String[]{"Replaces item ids with the Origin Realms item id when hidden tooltips is enabled"}),
   SOCIAL_MENU_FILTER(OriginFeature.Category.ENHANCEMENTS, "Social Menu Filter", new String[]{"Removes emojis and NPCs from the social interaction menu"}),
   AUCTION_PING(OriginFeature.Category.UTILITIES, "Auction Ping", new String[]{"Play a ping noise when your auction item is sold"});

   private final OriginFeature.Category category;
   private final boolean defaultValue;
   private final String name;
   private final String[] descriptionLines;

   private OriginFeature(OriginFeature.Category category, boolean defaultValue, String name, String... descriptionLines) {
      this.category = category;
      this.defaultValue = defaultValue;
      this.name = name;
      this.descriptionLines = descriptionLines;
   }

   private OriginFeature(OriginFeature.Category category, String name, String... descriptionLines) {
      this(category, true, name, descriptionLines);
   }

   public boolean getDefaultValue() {
      return this.defaultValue;
   }

   public OriginFeature.Category getCategory() {
      return this.category;
   }

   public String getName() {
      return this.name;
   }

   public String[] getDescriptionLines() {
      return this.descriptionLines;
   }

   // $FF: synthetic method
   private static OriginFeature[] $values() {
      return new OriginFeature[]{RICH_PRESENCE, LOCATION_TRACKER, SMOOTH_ANIMATIONS, BLOCK_ENHANCEMENTS, AUTO_COMPLETION_ENHANCEMENTS, BACK_SWAG_SLOT, GESTURE_COMPATIBILITY, COSMETIC_LOCKING, CUTSCENE_ENHANCEMENTS, EXPAND_MODEL_BOUNDING_BOX, HIDE_NAMES, CUSTOM_ITEM_IDS, SOCIAL_MENU_FILTER, AUCTION_PING};
   }

   @Environment(EnvType.CLIENT)
   public static enum Category {
      FEATURES,
      ENHANCEMENTS,
      UTILITIES;

      private final Supplier<OriginFeature[]> features = Suppliers.memoize(() -> {
         return (OriginFeature[])Arrays.stream(OriginFeature.values()).filter((feature) -> {
            return feature.getCategory() == this;
         }).toArray((x$0) -> {
            return new OriginFeature[x$0];
         });
      });

      public OriginFeature[] getFeatures() {
         return (OriginFeature[])this.features.get();
      }

      // $FF: synthetic method
      private static OriginFeature.Category[] $values() {
         return new OriginFeature.Category[]{FEATURES, ENHANCEMENTS, UTILITIES};
      }
   }
}
