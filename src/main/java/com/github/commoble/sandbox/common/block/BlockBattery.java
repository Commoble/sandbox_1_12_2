package com.github.commoble.sandbox.common.block;

import com.github.commoble.sandbox.common.item.ItemRegistrar;
import com.github.commoble.sandbox.common.tileentity.TileEntityBattery;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBattery extends Block implements ITileEntityProvider
{

	public BlockBattery()
	{
		super(Material.IRON);
		this.setCreativeTab(ItemRegistrar.tab);
		this.setSoundType(SoundType.METAL);
		this.setHardness(2.0F);
		this.setResistance(5.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		// TODO Auto-generated method stub
		return new TileEntityBattery();
	}

}
