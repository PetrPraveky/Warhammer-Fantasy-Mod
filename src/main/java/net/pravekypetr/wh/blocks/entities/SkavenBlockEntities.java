package net.pravekypetr.wh.blocks.entities;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pravekypetr.wh.WH;
import net.pravekypetr.wh.blocks.ModStationBlocks;
import net.pravekypetr.wh.blocks.stations.skavenBlastFurnace.entity.SkavenBlastFurnaceBlockEntity;

public class SkavenBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, WH.MOD_ID);

    public static final RegistryObject<BlockEntityType<SkavenBlastFurnaceBlockEntity>> SKAVEN_BLAST_FURNACE = BLOCK_ENTITIES.register("skaven_blast_furnace", 
        () -> BlockEntityType.Builder.of(SkavenBlastFurnaceBlockEntity::new, ModStationBlocks.SKAVEN_BLAST_FURNACE.get()).build(null)
    );

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
