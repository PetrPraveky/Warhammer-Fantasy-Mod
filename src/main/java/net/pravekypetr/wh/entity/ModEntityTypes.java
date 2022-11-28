package net.pravekypetr.wh.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pravekypetr.wh.WH;
import net.pravekypetr.wh.entity.passive.RatEntity;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, WH.MOD_ID);

    public static final RegistryObject<EntityType<RatEntity>> RAT = ENTITY_TYPES.register("rat", () -> EntityType.Builder
        .of(RatEntity::new, MobCategory.MISC)
        .sized(0.5f, 0.1875f)
        .build(new ResourceLocation(WH.MOD_ID, "rat").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
