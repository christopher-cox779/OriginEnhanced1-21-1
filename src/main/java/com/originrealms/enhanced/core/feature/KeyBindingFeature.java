package com.originrealms.enhanced.core.feature;

import com.originrealms.enhanced.core.bridge.RegistryBridge;
import com.originrealms.enhanced.core.util.OriginKeyBinding;
import com.originrealms.enhanced.core.util.OriginUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_3675;

@Environment(EnvType.CLIENT)
public class KeyBindingFeature {
   public static final KeyBindingFeature INSTANCE = new KeyBindingFeature();

   public void setup() {
      this.registerCommandMacro(78, "navigator", "navigator");
      this.registerCommandMacro(71, "gesture_menu", "gesture");
      this.registerCommandMacro(class_3675.field_16237.method_1444(), "spawn", "spawn");
      this.registerCommandMacro(class_3675.field_16237.method_1444(), "realm", "realm tp");
   }

   private void registerCommandMacro(int key, String name, String command) {
      RegistryBridge.registerKeybinding(new OriginKeyBinding("key.originsenhanced." + name, key, "key.categories.originsenhanced", (client) -> {
         if (client.field_1724 != null && OriginUtil.isConnected()) {
            client.field_1724.field_3944.method_45731(command);
         }
      }));
   }
}
