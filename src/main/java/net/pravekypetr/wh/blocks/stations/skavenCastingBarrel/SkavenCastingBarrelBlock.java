package net.pravekypetr.wh.blocks.stations.skavenCastingBarrel;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.pravekypetr.wh.items.ModMetalItems;
import net.minecraft.world.level.block.CauldronBlock;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.CampfireBlock;

public class SkavenCastingBarrelBlock extends HorizontalDirectionalBlock {
    public static final IntegerProperty LEVEL = IntegerProperty.create("level", 1, 3);
    
    private static VoxelShape SHAPE = Block.box(0, 0, 0, 16, 16, 16);

    public static final IntegerProperty AMOUNT = IntegerProperty.create("amount", 0, 1000);

    private int ingot_casting_cost = 25;
    private int weapon_casting_cost = 100;

    public SkavenCastingBarrelBlock(Properties properties) {
        super(properties);
    }
    
    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_,
            CollisionContext p_60558_) {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
        builder.add(AMOUNT);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
            InteractionHand hand, BlockHitResult result) {
        if (level.isClientSide()) {
            if (player.getMainHandItem().getItem() == ModMetalItems.HOT_STEEL_INGOT.get() && state.getValue(AMOUNT)-ingot_casting_cost >= 0) {
                player.getMainHandItem().setCount(player.getMainHandItem().getCount()-1);    
                

                RandomSource randomsource = level.getRandom();
                level.addParticle(ParticleTypes.SMOKE, (double)pos.getX() + 0.5D + randomsource.nextDouble() / 4.0D * (double)(randomsource.nextBoolean() ? 1 : -1), (double)pos.above().getY(), (double)pos.getZ() + 0.5D + randomsource.nextDouble() / 4.0D * (double)(randomsource.nextBoolean() ? 1 : -1), 0.0D, 0.005D, 0.0D);
                level.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundSource.BLOCKS, 1, 1, false);
            }
        }
        if (!level.isClientSide()) {
            if (player.getMainHandItem().getItem() == Items.WATER_BUCKET.asItem() && state.getValue(AMOUNT) < 1000) {
                level.setBlock(pos, state.setValue(AMOUNT, 1000).setValue(LEVEL, 3), 3);
                if (!player.isCreative()) {
                    player.setItemInHand(hand, new ItemStack(Items.BUCKET.asItem()));
                }
                return InteractionResult.sidedSuccess(level.isClientSide());
            }

            if (player.getMainHandItem().getItem() == ModMetalItems.HOT_STEEL_INGOT.get() && state.getValue(AMOUNT)-ingot_casting_cost >= 0) {
                player.getMainHandItem().setCount(player.getMainHandItem().getCount()-1);

                SimpleContainer inventory = new SimpleContainer(1);
                inventory.setItem(0,new ItemStack(Items.IRON_INGOT.asItem()));
                Containers.dropContents(level, pos.above(), inventory);


                if (state.getValue(AMOUNT)-ingot_casting_cost < 1 && state.getValue(LEVEL) != 1) {
                    level.setBlock(pos, state.setValue(AMOUNT, state.getValue(AMOUNT)-ingot_casting_cost).setValue(LEVEL, 1), 3);
                } else if (state.getValue(AMOUNT)-ingot_casting_cost < 501 && state.getValue(LEVEL) != 2) {
                    level.setBlock(pos, state.setValue(AMOUNT, state.getValue(AMOUNT)-ingot_casting_cost).setValue(LEVEL, 2), 3);
                } else {
                    level.setBlock(pos, state.setValue(AMOUNT, state.getValue(AMOUNT)-ingot_casting_cost), 3);
                }
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

}
