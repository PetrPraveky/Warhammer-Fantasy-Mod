package net.pravekypetr.wh.attributes;

import java.util.UUID;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pravekypetr.wh.WH;

public class ModWeaponAttribute {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, WH.MOD_ID);

    // Attirubtes
    public static final RegistryObject<Attribute> AOE_RADIUS = ATTRIBUTES.register("aoe.radius", () -> (Attribute) new RangedAttribute("attribute.aoe.radius", 0.0D, 0.0D, 1024.0D));
    public static final RegistryObject<Attribute> AOE_DAMAGE = ATTRIBUTES.register("aoe.damage", () -> (Attribute) new RangedAttribute("attribute.aoe.damage", 0.0D, 0.0D, 1024.0D));
    public static final RegistryObject<Attribute> ADDITIONAL_DAMAGE = ATTRIBUTES.register("add.damage", () -> (Attribute) new RangedAttribute("attribute.add_damage", 0.0D, 0.0D, 1024.0D));
    public static final RegistryObject<Attribute> SPEACIAL_COOLDOWN = ATTRIBUTES.register("special.cooldown", () -> (Attribute) new RangedAttribute("attribute.special.cooldown", 0.0D, 0.0D, 1024.0D));

    // Modifiers
    public static final UUID AOE_RADIUS_MODIFIER = UUID.fromString("09db6a6e-c1b0-43d5-8be4-2025b38739d2");
    public static final UUID AOE_DAMAGE_MODIFIER = UUID.fromString("889f8607-f1de-4f88-9dfc-1e4e570b50a0");
    public static final UUID ADDITIONAL_DAMAGE_MODIFIER = UUID.fromString("2d07c7cb-45dd-4130-a5bc-f1767b7902f1");
    public static final UUID SPECIAL_COOLDOWN_MODIFIER = UUID.fromString("52b6ddf8-c2b2-4acb-acc1-7b21bb506fe2");

    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }
}
