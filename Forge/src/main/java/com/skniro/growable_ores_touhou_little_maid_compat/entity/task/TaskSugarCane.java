package com.skniro.growable_ores_touhou_little_maid_compat.entity.task;

import com.github.tartaricacid.touhoulittlemaid.api.task.IFarmTask;
import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import com.skniro.growable_ores_touhou_little_maid_compat.GrowableOresExtension;
import com.skniro.growableores.block.GrowableVanillaOresBlocks;
import com.skniro.growableores.block.init.GrowableOreCaneBlock;
import com.skniro.growableores.registry.tag.GrowableBlockTags;
import com.skniro.growableores.registry.tag.GrowableFluidTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class TaskSugarCane implements IFarmTask {
    private static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(GrowableOresExtension.MOD_ID, "growable_cane");

    public ResourceLocation getUid() {
        return UID;
    }

    public ItemStack getIcon() {
        return GrowableVanillaOresBlocks.Iron_Cane.get().asItem().getDefaultInstance();
    }

    public boolean isSeed(ItemStack stack) {
        return false;
    }

    public boolean canHarvest(EntityMaid maid, BlockPos cropPos, BlockState cropState) {
        BlockState blockDownState = maid.level().getBlockState(cropPos.below());
        BlockState blockDown2State = maid.level().getBlockState(cropPos.below(2));
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
        return state.is(BlockTags.DIRT) || state.is(BlockTags.SAND) || state.is(GrowableBlockTags.GrowBlock);
    }

    private boolean hasWaterSourceBlock(Level world, BlockPos basePos) {
        for(Direction direction : Direction.Plane.HORIZONTAL) {
            BlockState offsetState = world.getBlockState(basePos.relative(direction));
            FluidState fluidState = world.getFluidState(basePos.relative(direction));
            if (fluidState.is(FluidTags.WATER) || offsetState.is(Blocks.FROSTED_ICE) || fluidState.is(GrowableFluidTags.GrowFluid)) {
                return true;
            }
        }

        return false;
    }
}
