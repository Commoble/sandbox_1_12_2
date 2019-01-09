package com.github.commoble.sandbox.common.block;

import com.github.commoble.sandbox.common.item.ItemRegistrar;
import com.github.commoble.sandbox.common.tileentity.TileEntityWire;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockWire extends Block implements ITileEntityProvider
{

	public BlockWire()
	{
		super(Material.CIRCUITS);
		this.setCreativeTab(ItemRegistrar.tab);
		this.setSoundType(SoundType.STONE);
		this.setHardness(0.2F);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		// TODO Auto-generated method stub
		return new TileEntityWire();
	}

}
