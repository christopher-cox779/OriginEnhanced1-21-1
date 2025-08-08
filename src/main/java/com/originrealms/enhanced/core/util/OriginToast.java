package com.originrealms.enhanced.core.util;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_368;
import net.minecraft.class_374;
import net.minecraft.class_5481;
import net.minecraft.class_368.class_369;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class OriginToast implements class_368 {
   public static final class_2960 TEXTURE = new class_2960("originsenhanced", "textures/gui/toasts.png");
   private final int width;
   private final class_2561 title;
   private final List<class_5481> messageLines;
   private long lastChanged;
   private boolean changed;

   public OriginToast(class_2561 component, @Nullable class_2561 component2) {
      this(component, nullToEmpty(component2), 160);
   }

   private OriginToast(class_2561 component, List<class_5481> list, int i) {
      this.title = component;
      this.messageLines = list;
      this.width = i;
   }

   public static OriginToast multiline(class_310 minecraft, class_2561 component, class_2561 component2) {
      class_327 font = minecraft.field_1772;
      List<class_5481> list = font.method_1728(component2, 189);
      Stream var10001 = list.stream();
      Objects.requireNonNull(font);
      int i = Math.max(200, var10001.mapToInt(font::method_30880).max().orElse(200));
      return new OriginToast(component, list, i + 30);
   }

   private static ImmutableList<class_5481> nullToEmpty(@Nullable class_2561 component) {
      return component == null ? ImmutableList.of() : ImmutableList.of(component.method_30937());
   }

   public static void add(class_374 toastComponent, class_2561 component, @Nullable class_2561 component2) {
      toastComponent.method_1999(new OriginToast(component, component2));
   }

   public int method_29049() {
      return this.width;
   }

   public class_369 method_1986(class_332 gfx, class_374 toastComponent, long time) {
      if (this.changed) {
         this.lastChanged = time;
         this.changed = false;
      }

      int width = this.method_29049();
      int i;
      if (width == 160 && this.messageLines.size() <= 1) {
         gfx.method_25302(TEXTURE, 0, 0, 0, 32, width, this.method_29050());
      } else {
         i = this.method_29050() + Math.max(0, this.messageLines.size() - 1) * 12;
         int n = Math.min(4, i - 28);
         this.renderBackgroundRow(gfx, toastComponent, width, 0, 0, 28);

         for(int o = 28; o < i - n; o += 10) {
            this.renderBackgroundRow(gfx, toastComponent, width, 16, o, Math.min(16, i - o - n));
         }

         this.renderBackgroundRow(gfx, toastComponent, width, 32 - n, i - n, n);
      }

      gfx.method_27535(toastComponent.method_1995().field_1772, this.title, 30, 7, 6709331);

      for(i = 0; i < this.messageLines.size(); ++i) {
         gfx.method_35720(toastComponent.method_1995().field_1772, (class_5481)this.messageLines.get(i), 30, 18 + i * 12, 10721666);
      }

      return time - this.lastChanged < 5000L ? class_369.field_2210 : class_369.field_2209;
   }

   private void renderBackgroundRow(class_332 gfx, class_374 toastComponent, int i, int vOffset, int k, int height) {
      int width = vOffset == 0 ? 23 : 5;
      int n = Math.min(60, i - width);
      gfx.method_25302(TEXTURE, 0, k, 0, 32 + vOffset, width, height);

      for(int o = width; o < i - n; o += 64) {
         gfx.method_25302(TEXTURE, o, k, 32, 32 + vOffset, Math.min(64, i - o - n), height);
      }

      gfx.method_25302(TEXTURE, i - n, k, 160 - n, 32 + vOffset, n, height);
   }
}
