package com.originrealms.enhanced.core.feature;

import com.originrealms.enhanced.core.bridge.RegistryBridge;
import com.originrealms.enhanced.core.feature.gesture.part.GestureBody;
import com.originrealms.enhanced.core.feature.gesture.part.GestureHead;
import com.originrealms.enhanced.core.feature.gesture.part.GestureLeftArm;
import com.originrealms.enhanced.core.feature.gesture.part.GestureLeftLeg;
import com.originrealms.enhanced.core.feature.gesture.part.GesturePartBase;
import com.originrealms.enhanced.core.feature.gesture.part.GestureRightArm;
import com.originrealms.enhanced.core.feature.gesture.part.GestureRightLeg;
import io.netty.util.collection.IntObjectHashMap;
import io.netty.util.collection.IntObjectMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_5599;
import net.minecraft.class_5601;

@Environment(EnvType.CLIENT)
public class GestureEnhancementsFeature {
   public static final GestureEnhancementsFeature INSTANCE = new GestureEnhancementsFeature();
   public static final class_5601 GESTURE_HEAD = createLocation("gesture_head");
   public static final class_5601 GESTURE_BODY = createLocation("gesture_body");
   public static final class_5601 GESTURE_LEFT_ARM_SLIM = createLocation("gesture_left_arm_slim");
   public static final class_5601 GESTURE_LEFT_ARM = createLocation("gesture_left_arm");
   public static final class_5601 GESTURE_RIGHT_ARM_SLIM = createLocation("gesture_right_arm_slim");
   public static final class_5601 GESTURE_RIGHT_ARM = createLocation("gesture_right_arm");
   public static final class_5601 GESTURE_LEFT_LEG = createLocation("gesture_left_leg");
   public static final class_5601 GESTURE_RIGHT_LEG = createLocation("gesture_right_leg");
   private IntObjectMap<GesturePartBase> gestureModels = new IntObjectHashMap();

   public static IntObjectMap<GesturePartBase> createGestureRenderers(class_5599 entityModelSet) {
      IntObjectMap<GesturePartBase> map = new IntObjectHashMap();
      map.put(13, new GestureHead(entityModelSet.method_32072(GESTURE_HEAD)));
      map.put(12, new GestureBody(entityModelSet.method_32072(GESTURE_BODY)));
      map.put(9, new GestureLeftArm(entityModelSet.method_32072(GESTURE_LEFT_ARM_SLIM)));
      map.put(8, new GestureRightArm(entityModelSet.method_32072(GESTURE_RIGHT_ARM_SLIM)));
      map.put(7, new GestureLeftArm(entityModelSet.method_32072(GESTURE_LEFT_ARM)));
      map.put(6, new GestureRightArm(entityModelSet.method_32072(GESTURE_RIGHT_ARM)));
      map.put(5, new GestureLeftLeg(entityModelSet.method_32072(GESTURE_LEFT_LEG)));
      map.put(4, new GestureRightLeg(entityModelSet.method_32072(GESTURE_RIGHT_LEG)));
      return map;
   }

   public static class_5601 createLocation(String id) {
      return new class_5601(new class_2960("originsenhanced", id), "main");
   }

   public void setup() {
      RegistryBridge.registerLayerDefinition(GESTURE_HEAD, GestureHead::createLayer);
      RegistryBridge.registerLayerDefinition(GESTURE_BODY, GestureBody::createLayer);
      RegistryBridge.registerLayerDefinition(GESTURE_LEFT_ARM_SLIM, () -> {
         return GestureLeftArm.createLayer(true);
      });
      RegistryBridge.registerLayerDefinition(GESTURE_LEFT_ARM, () -> {
         return GestureLeftArm.createLayer(false);
      });
      RegistryBridge.registerLayerDefinition(GESTURE_RIGHT_ARM_SLIM, () -> {
         return GestureRightArm.createLayer(true);
      });
      RegistryBridge.registerLayerDefinition(GESTURE_RIGHT_ARM, () -> {
         return GestureRightArm.createLayer(false);
      });
      RegistryBridge.registerLayerDefinition(GESTURE_LEFT_LEG, GestureLeftLeg::createLayer);
      RegistryBridge.registerLayerDefinition(GESTURE_RIGHT_LEG, GestureRightLeg::createLayer);
   }

   public void onResourceManagerReload() {
      this.gestureModels = createGestureRenderers(class_310.method_1551().method_31974());
   }

   public IntObjectMap<GesturePartBase> getGestureModels() {
      return this.gestureModels;
   }
}
