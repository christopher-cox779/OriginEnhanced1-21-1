package com.originrealms.enhanced.core.mixin.world.entity.animal.horse;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1496;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Environment(EnvType.CLIENT)
@Mixin({class_1496.class})
public abstract class AbstractHorseMixin {
   @Shadow
   public abstract boolean method_6725();
}
