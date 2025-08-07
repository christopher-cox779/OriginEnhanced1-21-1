package com.originrealms.enhanced.core.util;

import com.originrealms.enhanced.core.OriginsEnhanced;
import com.originrealms.enhanced.core.server.RemoteRegistry;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_124;
import net.minecraft.class_2561;

@Environment(EnvType.CLIENT)
public class TextUtil {
   public static final Pattern REALM_BOSS_BAR = Pattern.compile("(?<username>[A-Za-z0-9_]{3,16})'s Realm", 2);
   public static final Pattern DM_PATTERN = Pattern.compile("^(?<from>\\w{1,16}) -> (?<to>\\w{1,16}): (?<message>.*)$", 2);
   public static final Pattern CHAT_MESSAGE = Pattern.compile("\uf821(?<rank>.)\uf801 (?<username>\\w{1,16}): (?<message>.*)", 2);
   public static final Pattern NPC_DIALOGUE = Pattern.compile("\\uF821\\uA301\\uF80A\\uF808\\uF807(?<npc>.)\\n(?<lines>[\\uF801-\\uF821]{1,5}.*\\n)*", 2);
   public static final Pattern AUCTION_PURCHASE = Pattern.compile("\\uF821\\uA460\\uF801.* (?<username>[A-Za-z0-9_]{1,16}) bought your (?<amount>[\\d,]*)x (?<item>[\\w ]*) for (?<rubies>[\\d,]*) rubies!", 2);
   public static final Pattern INFO_MESSAGE = Pattern.compile("\\uF821[\\uA460\\uA45F\\uA45C]\\uF801.*", 2);
   public static final Pattern IP_REGEX = Pattern.compile("([A-Za-z0-9.-])*\\.?(originrealms\\.com|piston\\.gg)", 2);

   public static String deobfuscate(String input) {
      StringBuilder out = new StringBuilder();
      char[] var2 = input.toCharArray();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         char c = var2[var4];
         if (c >= '\ue1b2' && c <= '\ue2b1') {
            out.append((char)(c - '\ue1b2'));
         } else {
            out.append(c);
         }
      }

      return out.toString();
   }

   public static boolean shouldBypass(String text) {
      if (NPC_DIALOGUE.matcher(text).find()) {
         return true;
      } else {
         Matcher matcher = CHAT_MESSAGE.matcher(text);
         if (!matcher.find()) {
            return false;
         } else {
            String rank = matcher.group("rank");
            if (rank == null) {
               return false;
            } else {
               Optional<RemoteRegistry.CustomRank> customRank = OriginsEnhanced.getRegistry().getRank(rank);
               return customRank.isPresent() && ((RemoteRegistry.CustomRank)customRank.get()).isStaff();
            }
         }
      }
   }

   public static String getStripped(class_2561 message) {
      String stripped = class_124.method_539(message.getString());
      return stripped == null ? "" : stripped;
   }
}
