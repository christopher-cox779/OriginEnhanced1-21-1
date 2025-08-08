package com.originrealms.enhanced.core.feature.screen;

import com.google.common.collect.ImmutableList;
import com.originrealms.enhanced.core.bridge.Platform;
import com.originrealms.enhanced.core.feature.OriginFeature;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_364;
import net.minecraft.class_4185;
import net.minecraft.class_4265;
import net.minecraft.class_5244;
import net.minecraft.class_5348;
import net.minecraft.class_5481;
import net.minecraft.class_6379;
import net.minecraft.class_6381;
import net.minecraft.class_6382;
import net.minecraft.class_4265.class_4266;
import net.minecraft.class_6379.class_6380;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class FeatureList extends class_4265<FeatureList.Entry> {
   private final FeatureScreen screen;
   private int maxNameWidth;

   public FeatureList(FeatureScreen screen, class_310 mcIn, Supplier<String> search) {
      super(mcIn, screen.field_22789, screen.field_22790 - 80, 48, 24);
      this.screen = screen;
      this.refreshList(search);
   }

   public void method_48579(class_332 gfx, int mouseX, int mouseY, float partialTicks) {
      super.method_48579(gfx, mouseX, mouseY, partialTicks);
      FeatureList.Entry entry = (FeatureList.Entry)this.method_37019();
      if (entry != null && entry.tooltip != null) {
         this.screen.method_47414(entry.tooltip);
      }

   }

   private void populate() {
      OriginFeature.Category[] var1 = OriginFeature.Category.values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         OriginFeature.Category category = var1[var3];
         this.method_25321(new FeatureList.CategoryEntry((String[])null, class_2561.method_43470(StringUtils.capitalize(category.toString().toLowerCase(Locale.ROOT))).method_27692(class_124.field_1067)));
         OriginFeature[] var5 = category.getFeatures();
         int var6 = var5.length;

         for(int var7 = 0; var7 < var6; ++var7) {
            OriginFeature feature = var5[var7];
            this.method_25321(new FeatureList.FeatureEntry(feature.getDescriptionLines(), feature));
            int i = this.field_22740.field_1772.method_1727(feature.getName());
            if (i > this.maxNameWidth) {
               this.maxNameWidth = i;
            }
         }
      }

   }

   public void refreshList(Supplier<String> supplier) {
      this.method_25307(0.0D);
      String string = ((String)supplier.get()).toLowerCase(Locale.ROOT);
      if (string.isEmpty()) {
         this.method_25339();
         this.populate();
      } else {
         List<FeatureList.Entry> entries = (List)this.method_25396().stream().filter((e) -> {
            boolean var10000;
            if (e instanceof FeatureList.FeatureEntry) {
               FeatureList.FeatureEntry feature = (FeatureList.FeatureEntry)e;
               if (feature.label.getString().toLowerCase(Locale.ROOT).contains(string)) {
                  var10000 = true;
                  return var10000;
               }
            }

            var10000 = false;
            return var10000;
         }).collect(Collectors.toList());
         this.method_25339();
         Iterator var4 = entries.iterator();

         while(var4.hasNext()) {
            FeatureList.Entry entry = (FeatureList.Entry)var4.next();
            this.method_25321(entry);
         }

      }
   }

   protected int method_25329() {
      return super.method_25329() + 15 + 20;
   }

   public int method_25322() {
      return super.method_25322() + 32;
   }

   @Environment(EnvType.CLIENT)
   public abstract class Entry extends class_4266<FeatureList.Entry> {
      @Nullable
      private final List<class_5481> tooltip;

      public Entry(@Nullable final FeatureList this$0, String[] list) {
         this.tooltip = list != null ? this$0.field_22740.field_1772.method_1728(class_5348.method_29430(String.join("\n", list)), 175) : null;
      }
   }

   @Environment(EnvType.CLIENT)
   public class CategoryEntry extends FeatureList.Entry {
      private final class_2561 label;

      public CategoryEntry(@Nullable String[] tooltip, class_2561 label) {
         super(FeatureList.this, tooltip);
         this.label = label;
      }

      public void method_25343(class_332 gfx, int index, int y, int x, int width, int height, int mouseX, int mouseY, boolean selected, float partialTicks) {
         gfx.method_27534(FeatureList.this.field_22740.field_1772, this.label, x + width / 2, y + 5, 16777215);
      }

      public boolean changeFocus(boolean focus) {
         return false;
      }

      public List<? extends class_364> method_25396() {
         return Collections.emptyList();
      }

      public List<? extends class_6379> method_37025() {
         return ImmutableList.of(new class_6379() {
            // $FF: synthetic field
            final FeatureList.CategoryEntry this$1;

            {
               this.this$1 = this$1;
            }

            public class_6380 method_37018() {
               return class_6380.field_33785;
            }

            public void method_37020(class_6382 narrationElementOutput) {
               narrationElementOutput.method_37034(class_6381.field_33788, this.this$1.label);
            }
         });
      }
   }

   @Environment(EnvType.CLIENT)
   public class FeatureEntry extends FeatureList.Entry {
      private final OriginFeature feature;
      private final class_4185 changeButton;
      private final class_2561 label;

      private FeatureEntry(@Nullable String[] tooltip, OriginFeature feature) {
         super(FeatureList.this, tooltip);
         this.feature = feature;
         this.label = class_2561.method_43470(feature.getName());
         this.changeButton = class_4185.method_46430(class_5244.method_36134(Platform.getConfig().isFeatureEnabled(feature)), (button) -> {
            boolean bl = !Platform.getConfig().isFeatureEnabled(feature);
            Platform.getConfig().setFeatureEnabled(feature, bl);
         }).method_46434(10, 5, 44, 20).method_46431();
      }

      public void method_25343(class_332 gfx, int index, int y, int x, int width, int height, int mouseX, int mouseY, boolean selected, float partialTicks) {
         this.renderLabel(gfx, y, x, mouseX, mouseY);
         this.changeButton.method_46421(x + width - 45);
         this.changeButton.method_46419(y);
         this.changeButton.method_25355(class_5244.method_36134(Platform.getConfig().isFeatureEnabled(this.feature)));
         this.changeButton.method_25394(gfx, mouseX, mouseY, partialTicks);
      }

      protected void renderLabel(class_332 gfx, int y, int x, int mouseX, int mouseY) {
         boolean hovering = this.method_25405((double)mouseX, (double)mouseY);
         class_2561 trueLabel = this.label.method_27661().method_27694((style) -> {
            return style.method_30938(hovering);
         });
         List<class_5481> sequence = FeatureList.this.field_22740.field_1772.method_1728(trueLabel, 175);
         if (sequence.size() == 1) {
            gfx.method_35720(FeatureList.this.field_22740.field_1772, (class_5481)sequence.get(0), x, y + 5, 16777215);
         } else if (sequence.size() >= 2) {
            gfx.method_35720(FeatureList.this.field_22740.field_1772, (class_5481)sequence.get(0), x, y, 16777215);
            gfx.method_35720(FeatureList.this.field_22740.field_1772, (class_5481)sequence.get(1), x, y + 10, 16777215);
         }

      }

      public List<? extends class_364> method_25396() {
         return ImmutableList.of(this.changeButton);
      }

      public boolean method_25402(double mouseX, double mouseY, int button) {
         return this.changeButton.method_25402(mouseX, mouseY, button);
      }

      public boolean method_25406(double mouseX, double mouseY, int button) {
         return this.changeButton.method_25406(mouseX, mouseY, button);
      }

      public List<? extends class_6379> method_37025() {
         return ImmutableList.of(this.changeButton);
      }
   }
}
