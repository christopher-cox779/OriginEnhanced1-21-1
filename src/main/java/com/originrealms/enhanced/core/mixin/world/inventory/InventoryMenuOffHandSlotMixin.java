package com.originrealms.enhanced.core.mixin.world.inventory;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1263;
import net.minecraft.class_1657;
import net.minecraft.class_1735;
import org.spongepowered.asm.mixin.Mixin;

@Environment(EnvType.CLIENT)
@Mixin(
   targets = {"net/minecraft/world/inventory/InventoryMenu$2"}
)
public abstract class InventoryMenuOffHandSlotMixin extends class_1735 {
   public InventoryMenuOffHandSlotMixin(class_1263 container, int i, int j, int k) {
      super(container, i, j, k);
   }

   public boolean method_7674(class_1657 playerIn) {
      return super.method_7674(playerIn);
   }
}
