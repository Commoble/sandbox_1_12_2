package com.github.commoble.sandbox.common.block;

import com.github.commoble.sandbox.common.item.ItemRegistrar;
import com.github.commoble.sandbox.common.tileentity.TileEntityBattery;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBattery extends BlockWithFacing implements ITileEntityProvider, IElectricalBlock
{
	// facing of block = facing of positive side
	
	public BlockBattery()
	{
		super(Material.IRON);
		this.setCreativeTab(ItemRegistrar.tab);
		this.setSoundType(SoundType.METAL);
		this.setHardness(2.0F);
		this.setResistance(5.0F);
		
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.WEST));
        
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		// TODO Auto-generated method stub
		return new TileEntityBattery();
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof TileEntityBattery)
		{
			TileEntityBattery teb = (TileEntityBattery)te;
			teb.setFacing(this.getFacingOfBlockState(state));
			if (!world.isRemote)
			{
				teb.circuit_update_check_pending = true;
			}
		}
		
	}
}
