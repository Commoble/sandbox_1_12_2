package com.github.commoble.sandbox.common.block;

import com.github.commoble.sandbox.common.electrical.ChargePacket;
import com.github.commoble.sandbox.common.item.ItemRegistrar;
import com.github.commoble.sandbox.common.tileentity.TileEntityLightbulb;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockLightbulb extends Block implements ITileEntityProvider, IElectricalBlock
{

	public BlockLightbulb()
	{
		super(Material.GLASS);
		this.setCreativeTab(ItemRegistrar.tab);
		this.setSoundType(SoundType.GLASS);
		this.setHardness(0.3F);	// same as Glass block
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		// TODO Auto-generated method stub
		return new TileEntityLightbulb();
	}

}
