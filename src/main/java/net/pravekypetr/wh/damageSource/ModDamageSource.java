package net.pravekypetr.wh.damageSource;

import net.minecraft.world.damagesource.DamageSource;

public class ModDamageSource {
    public static final DamageSource HAMMERED = (new DamageSource("hammered"));
    public static final DamageSource SLASHED = (new DamageSource("slashed"));
    public static final DamageSource STABBED = (new DamageSource("stabbed")).bypassArmor();
    public static final DamageSource RAPIER = (new DamageSource("rapier")).bypassArmor();
}
