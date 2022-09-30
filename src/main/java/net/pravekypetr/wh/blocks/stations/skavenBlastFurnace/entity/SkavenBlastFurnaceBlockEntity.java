package net.pravekypetr.wh.blocks.stations.skavenBlastFurnace.entity;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.pravekypetr.wh.blocks.entities.SkavenBlockEntities;
import net.pravekypetr.wh.blocks.stations.skavenBlastFurnace.SkavenBlastFurnaceBlock;
import net.pravekypetr.wh.fluid.ModFluids;
import net.pravekypetr.wh.items.ModTools;
import net.pravekypetr.wh.networking.ModMessages;
import net.pravekypetr.wh.networking.packet.C2S.stations.FluidSyncS2C;
import net.pravekypetr.wh.recipe.BlastFurnaceRecipe;
import net.pravekypetr.wh.screen.skavenBlastFurnace.SkavenBlastFurnaceMenu;

public class SkavenBlastFurnaceBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(5) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return switch (slot) {
                case 4 -> stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).isPresent();
                default -> super.isItemValid(slot, stack);
            };
        }

    };



    private final FluidTank FLUID_TANK = new FluidTank(4000) {
        @Override
        protected void onContentsChanged() {
            setChanged();
            if (!level.isClientSide()) {
                ModMessages.sendToClients(new FluidSyncS2C(this.fluid, worldPosition));
            }

        }
        @Override
        public boolean isFluidValid(net.minecraftforge.fluids.FluidStack stack) {
            return stack.getFluid() == ModFluids.SOURCE_WARPSTONE_SLUDGE.get();
        }
    };

    public void setFluid(FluidStack stack) {
        this.FLUID_TANK.setFluid(stack);
    }

    public FluidStack getFluidStack() {
        return this.FLUID_TANK.getFluid();
    }



    private LazyOptional<IItemHandler> lazyItemHander = LazyOptional.empty();
    private LazyOptional<IFluidHandler> lazyFluidHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 100; // do 400

    private int fuel = 0;

    private int rotation = 0;

    public SkavenBlastFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(SkavenBlockEntities.SKAVEN_BLAST_FURNACE.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> SkavenBlastFurnaceBlockEntity.this.progress;
                    case 1 -> SkavenBlastFurnaceBlockEntity.this.maxProgress;
                    case 2 -> SkavenBlastFurnaceBlockEntity.this.fuel;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch(index) {
                    case 0 -> SkavenBlastFurnaceBlockEntity.this.progress = value;
                    case 1 -> SkavenBlastFurnaceBlockEntity.this.maxProgress = value;
                    case 2 -> SkavenBlastFurnaceBlockEntity.this.fuel = value;
                };
            }

            @Override
            public int getCount() {
                return 3;
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

        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return lazyFluidHandler.cast();
        }
        return super.getCapability(cap, side);
    }
    
    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHander = LazyOptional.of(() -> itemHandler);
        lazyFluidHandler = LazyOptional.of(() -> FLUID_TANK);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHander.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());

        nbt = FLUID_TANK.writeToNBT(nbt);
        
        super.saveAdditional(nbt);

    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        FLUID_TANK.readFromNBT(nbt);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots()+1);
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, SkavenBlastFurnaceBlockEntity pEntity) {
        System.out.println(pEntity.getFluidStack().getAmount());
        RandomSource randomsource = level.getRandom();
        if (level.isClientSide()) {
            if (pEntity.rotation % 20 == 0 && state.getValue(SkavenBlastFurnaceBlock.UNLIT) == false) {
                level.addAlwaysVisibleParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, true, (double)pos.getX() + 0.5D + randomsource.nextDouble() / 3.0D * (double)(randomsource.nextBoolean() ? 1 : -1), (double)pos.above().getY() + randomsource.nextDouble() + randomsource.nextDouble(), (double)pos.getZ() + 0.5D + randomsource.nextDouble() / 3.0D * (double)(randomsource.nextBoolean() ? 1 : -1), 0.0D, 0.07D, 0.0D);
                level.addParticle(ParticleTypes.SMOKE, (double)pos.getX() + 0.5D + randomsource.nextDouble() / 4.0D * (double)(randomsource.nextBoolean() ? 1 : -1), (double)pos.getY() + 0.4D, (double)pos.getZ() + 0.5D + randomsource.nextDouble() / 4.0D * (double)(randomsource.nextBoolean() ? 1 : -1), 0.0D, 0.005D, 0.0D);
            }
            if (pEntity.rotation % 60 == 0 && state.getValue(SkavenBlastFurnaceBlock.UNLIT) == false) {
                level.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.CAMPFIRE_CRACKLE, SoundSource.BLOCKS, 0.5F + randomsource.nextFloat(), randomsource.nextFloat() * 0.7F + 0.6F, false);
            }
            return;
        }
        
        if (pEntity.fuel > 0) {;
            pEntity.rotation++;
            pEntity.fuel--;
        }

        if (hasRecipe(pEntity) && !pEntity.itemHandler.getStackInSlot(0).isEmpty() || hasRecipe(pEntity) && pEntity.fuel > 0) {
            if (ForgeHooks.getBurnTime(pEntity.itemHandler.getStackInSlot(0), BlastFurnaceRecipe.Type.INSTANCE) > 0 && pEntity.fuel == 0) {
                pEntity.itemHandler.extractItem(0, 1, false);
                pEntity.fuel = ForgeHooks.getBurnTime(pEntity.itemHandler.getStackInSlot(0), BlastFurnaceRecipe.Type.INSTANCE);
            }

            pEntity.progress++;

            setChanged(level, pos, state);
            level.setBlock(pos, state.setValue(SkavenBlastFurnaceBlock.UNLIT, false), 3);
            if (pEntity.progress >= pEntity.maxProgress) {
                craftItem(pEntity);
            }
        } else {
            pEntity.resetProgress();
            if (pEntity.fuel == 0) {

                level.setBlock(pos, state.setValue(SkavenBlastFurnaceBlock.UNLIT, true), 3);
            }
            setChanged(level, pos, state);
        }

        // Fluid handeling
        if (hasFluidItemInOutputSlot(pEntity)) {
            transferFluidIntoItem(pEntity);
        }
    }

    private static void transferFluidIntoItem(SkavenBlastFurnaceBlockEntity pEntity) {
        pEntity.itemHandler.getStackInSlot(4).getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).ifPresent(handler -> {
            if (pEntity.FLUID_TANK.getFluidAmount() >= 1000) {
                // if there is a bucket
                if (pEntity.itemHandler.getStackInSlot(4).getItem() == Items.BUCKET) {
                    pEntity.FLUID_TANK.drain(1000, IFluidHandler.FluidAction.EXECUTE);

                    pEntity.itemHandler.extractItem(4, 1, false);
                    pEntity.itemHandler.insertItem(4, new ItemStack(ModTools.WARPSTONE_SLUDGE_BUCKET.get(), 1), false);
                }
            }
        });
    }

    private static boolean hasFluidItemInOutputSlot(SkavenBlastFurnaceBlockEntity pEntity) {
        return pEntity.itemHandler.getStackInSlot(4).getCount() > 0;
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

            entity.FLUID_TANK.fill(new FluidStack(ModFluids.SOURCE_WARPSTONE_SLUDGE.get(), 100), IFluidHandler.FluidAction.EXECUTE);
        }
    }

    private static boolean hasRecipe(SkavenBlastFurnaceBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<BlastFurnaceRecipe> recipe = level.getRecipeManager().getRecipeFor(BlastFurnaceRecipe.Type.INSTANCE, inventory, level);

        return recipe.isPresent() && canInsertAmountIntoOutputSlot(inventory) && canInsertItemIntoOutputSlot(inventory, recipe.get().getResultItem());
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack itemStack) {
        return inventory.getItem(3).getItem() == itemStack.getItem() || inventory.getItem(3).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(3).getMaxStackSize() > inventory.getItem(3).getCount();
    }
}
