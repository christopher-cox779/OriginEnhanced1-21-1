package com.originrealms.enhanced.core.feature.screen;

import com.originrealms.enhanced.core.bridge.Platform;
import com.originrealms.enhanced.core.feature.OriginFeature;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_342;
import net.minecraft.class_4185;
import net.minecraft.class_437;
import net.minecraft.class_5244;
import net.minecraft.class_5481;
import net.minecraft.class_7845;
import net.minecraft.class_7845.class_7939;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class FeatureScreen extends class_437 {
   private final class_437 parent;
   protected class_342 searchBox;
   private FeatureList featureList;
   private class_4185 resetButton;
   @Nullable
   private List<class_5481> tooltip;

   public FeatureScreen(class_437 screen) {
      super(class_2561.method_43471("screen.originsenhanced.config.title"));
      this.parent = screen;
   }

   protected void setTooltip(@Nullable List<class_5481> list) {
      this.tooltip = list;
   }

   protected void method_25426() {
      this.searchBox = new class_342(this.field_22793, this.field_22789 / 2 - 100, 22, 200, 20, this.searchBox, class_2561.method_43471("selectWorld.search"));
      this.searchBox.method_1863((string) -> {
         this.featureList.refreshList(() -> {
            return string;
         });
      });
      this.method_37063(this.searchBox);
      this.method_48265(this.searchBox);
      this.featureList = (FeatureList)this.method_37063(new FeatureList(this, this.field_22787, () -> {
         return this.searchBox.method_1882();
      }));
      class_7939 rowHelper = (new class_7845()).method_48635(10).method_47610(2);
      this.resetButton = (class_4185)rowHelper.method_47612((class_4185)this.method_37063(class_4185.method_46430(class_2561.method_43471("screen.originsenhanced.config.reset"), (p_213125_1_) -> {
         OriginFeature[] var1 = OriginFeature.values();
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            OriginFeature feature = var1[var3];
            Platform.getConfig().setFeatureEnabled(feature, feature.getDefaultValue());
         }

      }).method_46431()));
      rowHelper.method_47612((class_4185)this.method_37063(class_4185.method_46430(class_5244.field_24334, (p_213124_1_) -> {
         this.field_22787.method_1507(this.parent);
      }).method_46434(this.field_22789 / 2 - 155 + 160, this.field_22790 - 29, 150, 20).method_46431()));
      rowHelper.method_48638().method_48206((guiEventListener) -> {
      });
      rowHelper.method_48638().method_48229(this.field_22789 / 2 - 155, this.field_22790 - 28);
      rowHelper.method_48638().method_48222();
   }

   public void method_25419() {
      this.field_22787.method_1507(this.parent);
   }

   public boolean method_25404(int i, int j, int k) {
      return super.method_25404(i, j, k) || this.searchBox.method_25404(i, j, k);
   }

   public boolean method_25400(char c, int i) {
      return this.searchBox.method_25400(c, i);
   }

   public void method_25394(class_332 gfx, int mouseX, int mouseY, float partialTicks) {
      super.method_25394(gfx, mouseX, mouseY, partialTicks);
      this.tooltip = null;
      gfx.method_27534(this.field_22793, this.field_22785, this.field_22789 / 2, 8, 16777215);
      boolean flag = false;
      OriginFeature[] var6 = OriginFeature.values();
      int var7 = var6.length;

      for(int var8 = 0; var8 < var7; ++var8) {
         OriginFeature feature = var6[var8];
         if (Platform.getConfig().isFeatureEnabled(feature) != feature.getDefaultValue()) {
            flag = true;
            break;
         }
      }

      this.resetButton.field_22763 = flag;
      if (this.tooltip != null) {
         gfx.method_51447(this.field_22793, this.tooltip, mouseX, mouseY);
      }

   }
}
