package com.github.commoble.sandbox.common.block;

import com.github.commoble.sandbox.common.SandboxMod;
import com.github.commoble.sandbox.common.item.ItemRegistrar;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockSand;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockAsh extends BlockFalling
{
	public static final String name = "ash";
	
	public BlockAsh()
    {
        this.setHardness(0.3F);
        this.setSoundType(SoundType.SAND);
        this.setCreativeTab(ItemRegistrar.tab);
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return MapColor.CLAY;
    }

    @SideOnly(Side.CLIENT)
    public int getDustColor(IBlockState state)
    {
        return -8356741;	// copied from gravel
    }
}
