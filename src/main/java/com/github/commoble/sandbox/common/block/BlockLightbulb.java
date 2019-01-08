package com.github.commoble.sandbox.common.block;

import com.github.commoble.sandbox.common.tileentity.TileEntityLightbulb;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockLightbulb implements ITileEntityProvider
{

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		// TODO Auto-generated method stub
		return new TileEntityLightbulb();
	}

}
