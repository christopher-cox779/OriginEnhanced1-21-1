package com.originrealms.enhanced.core.util;

import com.originrealms.enhanced.core.mixin.client.KeyMappingAccessor;
import java.util.function.Consumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_3675.class_307;

@Environment(EnvType.CLIENT)
public class OriginKeyBinding extends class_304 {
   private final Consumer<class_310> onPress;

   public OriginKeyBinding(String translationKey, int code, String category, Consumer<class_310> onPress) {
      super(translationKey, code, category);
      this.onPress = onPress;
      this.setup();
   }

   public OriginKeyBinding(String translationKey, class_307 type, int code, String category, Consumer<class_310> onPress) {
      super(translationKey, type, code, category);
      this.onPress = onPress;
      this.setup();
   }

   private void setup() {
      ClientTickEvents.END_CLIENT_TICK.register((client) -> {
         if (client.field_1724 != null) {
            if (this.method_1436() && ((KeyMappingAccessor)this).getClickCount() == 0) {
               this.onPress.accept(client);
            }

         }
      });
   }
}
