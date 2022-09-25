package net.pravekypetr.wh.blocks.stations.skavenBlastFurnace.entity;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BlastFurnaceBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.pravekypetr.wh.blocks.entities.SkavenBlockEntities;
import net.pravekypetr.wh.blocks.stations.skavenBlastFurnace.SkavenBlastFurnaceBlock;
import net.pravekypetr.wh.items.ModMetalItems;
import net.pravekypetr.wh.recipe.BlastFurnaceRecipe;
import net.pravekypetr.wh.screen.skavenBlastFurnace.SkavenBlastFurnaceMenu;

public class SkavenBlastFurnaceBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(6) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHander = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    // private int steelProgress = 78;
    // private int defaultSmeltProsgress = 70;
    private int maxProgress = 70;

    private int rotation = 0;
    private boolean hasRecipe = false;

    public SkavenBlastFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(SkavenBlockEntities.SKAVEN_BLAST_FURNACE.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> SkavenBlastFurnaceBlockEntity.this.progress;
                    case 1 -> SkavenBlastFurnaceBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch(index) {
                    case 0 -> SkavenBlastFurnaceBlockEntity.this.progress = value;
                    case 1 -> SkavenBlastFurnaceBlockEntity.this.maxProgress = value;
                };
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
        
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        
        return new SkavenBlastFurnaceMenu(id, inventory, this, this.data);
    }

    @Override
    public Component getDisplayName() {
        
        return Component.literal("Skaven Blast Furnace");
    }

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHander.cast();
        }
        return super.getCapability(cap, side);
    }
    
    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHander = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHander.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());
        
        super.saveAdditional(nbt);

    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots()+1);
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, SkavenBlastFurnaceBlockEntity pEntity) {
        if (level.isClientSide()) {
            if (pEntity.rotation % 20 == 0 && state.getValue(SkavenBlastFurnaceBlock.UNLIT) == false) {
                RandomSource randomsource = level.getRandom();
                level.addAlwaysVisibleParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, true, (double)pos.getX() + 0.5D + randomsource.nextDouble() / 3.0D * (double)(randomsource.nextBoolean() ? 1 : -1), (double)pos.above().getY() + randomsource.nextDouble() + randomsource.nextDouble(), (double)pos.getZ() + 0.5D + randomsource.nextDouble() / 3.0D * (double)(randomsource.nextBoolean() ? 1 : -1), 0.0D, 0.07D, 0.0D);
                level.addParticle(ParticleTypes.SMOKE, (double)pos.getX() + 0.5D + randomsource.nextDouble() / 4.0D * (double)(randomsource.nextBoolean() ? 1 : -1), (double)pos.getY() + 0.4D, (double)pos.getZ() + 0.5D + randomsource.nextDouble() / 4.0D * (double)(randomsource.nextBoolean() ? 1 : -1), 0.0D, 0.005D, 0.0D);
            }
            return;
        }
        
        if (hasRecipe(pEntity)) {
            pEntity.progress++;

            setChanged(level, pos, state);
            level.setBlock(pos, state.setValue(SkavenBlastFurnaceBlock.UNLIT, false), 3);
            if (pEntity.progress >= pEntity.maxProgress) {
                craftItem(pEntity);
            }

            pEntity.rotation++;
        } else {
            pEntity.hasRecipe = false;
            pEntity.resetProgress();
            level.setBlock(pos, state.setValue(SkavenBlastFurnaceBlock.UNLIT, true), 3);
            setChanged(level, pos, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
        this.rotation = 0;
    }

    private static void craftItem(SkavenBlastFurnaceBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<BlastFurnaceRecipe> recipe = level.getRecipeManager().getRecipeFor(BlastFurnaceRecipe.Type.INSTANCE, inventory, level);

        if (hasRecipe(entity)) {
            entity.itemHandler.extractItem(1, 1, false);
            entity.itemHandler.extractItem(2, 1, false);
            entity.itemHandler.setStackInSlot(3, new ItemStack(recipe.get().getResultItem().getItem(), entity.itemHandler.getStackInSlot(3).getCount()+1));
            entity.resetProgress();
        }
    }

    private static boolean hasRecipe(SkavenBlastFurnaceBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<BlastFurnaceRecipe> recipe = level.getRecipeManager().getRecipeFor(BlastFurnaceRecipe.Type.INSTANCE, inventory, level);

        return recipe.isPresent() && canInsertAmountIntoOutputSlot(inventory) &&canInsertItemIntoOutputSlot(inventory, recipe.get().getResultItem());
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack itemStack) {
        return inventory.getItem(3).getItem() == itemStack.getItem() || inventory.getItem(3).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(3).getMaxStackSize() > inventory.getItem(3).getCount();
    }
}
