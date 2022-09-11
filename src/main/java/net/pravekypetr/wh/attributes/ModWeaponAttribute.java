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
    public static final RegistryObject<Attribute> AOE_RADIUS = ATTRIBUTES.register("aoe_radius", () -> (Attribute) new RangedAttribute("attribute.aoe_radius", 5.0D, 0.0D, 1024.0D).setSyncable(true));

    // Modifiers
    public static final UUID AOE_RADIUS_MODIFIER = UUID.fromString("09db6a6e-c1b0-43d5-8be4-2025b38739d2");

    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }
}
