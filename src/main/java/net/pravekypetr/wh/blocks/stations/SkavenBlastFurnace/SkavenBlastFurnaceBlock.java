package net.pravekypetr.wh.blocks.stations.skavenBlastFurnace;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import net.pravekypetr.wh.blocks.ModStationBlocks;
import net.pravekypetr.wh.blocks.entities.SkavenBlockEntities;
import net.pravekypetr.wh.blocks.stations.skavenBlastFurnace.entity.SkavenBlastFurnaceBlockEntity;
import net.pravekypetr.wh.networking.ModMessages;
import net.pravekypetr.wh.networking.packet.C2S.stations.FluidSyncS2C;

public class SkavenBlastFurnaceBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public static final BooleanProperty DOWN = BooleanProperty.create("down");
    public static final BooleanProperty UNLIT = BooleanProperty.create("unlit");

    private static VoxelShape SHAPE;

    public SkavenBlastFurnaceBlock(Properties properties) {
        super(properties);
        SHAPE = Block.box(0, 0, 0, 16, 16, 16);
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_,
            CollisionContext p_60558_) {
        return SHAPE;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(DOWN);
        builder.add(UNLIT);
    }

    @Override
    public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState p_60569_, boolean p_60570_) {
        if (!level.isClientSide() && blockState.getValue(DOWN)) {
            level.setBlock(blockPos.above(), blockState.cycle(DOWN).setValue(FACING, blockState.getValue(FACING)), 3);
        }
        super.onPlace(blockState, level, blockPos, p_60569_, p_60570_);
    }

    // BLOCK ENTITY
    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest,
            FluidState fluid) {
        SimpleContainer inventory = new SimpleContainer(1);
        inventory.setItem(0,new ItemStack(ModStationBlocks.SKAVEN_BLAST_FURNACE.get().asItem()));
        Containers.dropContents(level, pos, inventory);

        if (state.getValue(DOWN) == true && level.getBlockState(pos.above()).getBlock() instanceof SkavenBlastFurnaceBlock) {
            level.destroyBlock(pos.above(), true, null, 3);
        } else {
            if (level.getBlockState(pos.below()).getBlock() instanceof SkavenBlastFurnaceBlock) {
                level.destroyBlock(pos.below(), true, null, 3);
            }
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState,
            boolean bool) {
        if (state.getBlock() != newState.getBlock() && state.getValue(DOWN) == true) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof SkavenBlastFurnaceBlockEntity) {
                ((SkavenBlastFurnaceBlockEntity) blockEntity).drops();
            }
        }

    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer,
            InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity;
            if (pLevel.getBlockState(pPos).getValue(DOWN) == true) {
                entity = pLevel.getBlockEntity(pPos);
            } else {
                entity = pLevel.getBlockEntity(pPos.below());
            }
            if (entity instanceof SkavenBlastFurnaceBlockEntity) {
                ModMessages.sendToClients(new FluidSyncS2C(((SkavenBlastFurnaceBlockEntity) entity).getFluidStack(), pPos));
                NetworkHooks.openScreen((ServerPlayer)pPlayer, ((SkavenBlastFurnaceBlockEntity)entity), pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        
        return new SkavenBlastFurnaceBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
            BlockEntityType<T> type) {
        return createTickerHelper(type, SkavenBlockEntities.SKAVEN_BLAST_FURNACE.get(), SkavenBlastFurnaceBlockEntity::tick);
    }
}
