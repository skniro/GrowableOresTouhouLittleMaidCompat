package com.skniro.growable_ores_touhou_little_maid_compat.entity.task;

import com.github.tartaricacid.touhoulittlemaid.api.task.IFarmTask;
import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import com.skniro.growable_ores_touhou_little_maid_compat.GrowableOresExtension;
import com.skniro.growableores.block.GrowableVanillaOresBlocks;
import com.skniro.growableores.block.init.GrowableOreCaneBlock;
import com.skniro.growableores.registry.tag.GrowableBlockTags;
import com.skniro.growableores.registry.tag.GrowableFluidTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class TaskSugarCane implements IFarmTask {
    private static final Identifier UID = Identifier.of(GrowableOresExtension.MOD_ID, "growable_cane");

    public Identifier getUid() {
        return UID;
    }

    public ItemStack getIcon() {
        return GrowableVanillaOresBlocks.Iron_Cane.asItem().getDefaultStack();
    }

    public boolean isSeed(ItemStack stack) {
        return false;
    }

    public boolean canHarvest(EntityMaid maid, BlockPos cropPos, BlockState cropState) {
        BlockState blockDownState = maid.getWorld().getBlockState(cropPos.down());
        BlockState blockDown2State = maid.getWorld().getBlockState(cropPos.down(2));
        return cropState.getBlock() instanceof GrowableOreCaneBlock && blockDownState.getBlock() instanceof GrowableOreCaneBlock && this.canSustainSugarCane(blockDown2State);
    }

    public void harvest(EntityMaid maid, BlockPos cropPos, BlockState cropState) {
        maid.destroyBlock(cropPos);
    }

    public boolean canPlant(EntityMaid maid, BlockPos basePos, BlockState baseState, ItemStack seed) {return false;}

    public ItemStack plant(EntityMaid maid, BlockPos basePos, BlockState baseState, ItemStack seed) {return seed;}

    public double getCloseEnoughDist() {
        return (double)2.0F;
    }

    private boolean canSustainSugarCane(BlockState state) {
        return state.isIn(BlockTags.DIRT) || state.isIn(BlockTags.SAND) || state.isIn(GrowableBlockTags.GrowBlock);
    }

    private boolean hasWaterSourceBlock(World world, BlockPos basePos) {
        for(Direction direction : Direction.Type.HORIZONTAL) {
            BlockState offsetState = world.getBlockState(basePos.offset(direction));
            FluidState fluidState = world.getFluidState(basePos.offset(direction));
            if (fluidState.isIn(FluidTags.WATER) || offsetState.isOf(Blocks.FROSTED_ICE) || fluidState.isIn(GrowableFluidTags.GrowFluid)) {
                return true;
            }
        }

        return false;
    }
}
