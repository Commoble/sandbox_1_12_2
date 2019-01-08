package com.github.commoble.sandbox.common.block;

import com.github.commoble.sandbox.common.tileentity.TileEntityWire;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockWire implements ITileEntityProvider
{

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		// TODO Auto-generated method stub
		return new TileEntityWire();
	}

}
