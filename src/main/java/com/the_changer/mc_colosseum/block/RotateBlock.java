package com.the_changer.mc_colosseum.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RotateBlock extends Block {
    //create the facing property for the block
    public static final DirectionProperty FACING = DirectionProperty.of("facing");

    //initialize the class
    public RotateBlock(Settings settings) {
        super(settings);
    }

    //execute method when blocked is placed
    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        //rotate the block to face the player
        world.setBlockState(pos, state.with(FACING, placer.getHorizontalFacing().getOpposite()), Block.NOTIFY_ALL);
    }

    //add the property to the block
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
