package com.originrealms.enhanced.core.mixin.world.entity;

import com.originrealms.enhanced.core.networking.ModPacketHandler;
import com.originrealms.enhanced.core.util.mixin.EntityExtensions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1297;
import net.minecraft.class_1799;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Environment(EnvType.CLIENT)
@Mixin({class_1297.class})
public abstract class EntityMixin implements EntityExtensions {
   @Unique
   private class_1799 backItem;
   @Unique
   private ModPacketHandler.NameTag enhancedTag;

   public ModPacketHandler.NameTag getOrCreateEnhancedTag() {
      return this.enhancedTag == null ? (this.enhancedTag = new ModPacketHandler.NameTag()) : this.enhancedTag;
   }

   public class_1799 getBackItem() {
      return this.backItem;
   }

   public void setBackItem(class_1799 stack) {
      this.backItem = stack;
   }
}
