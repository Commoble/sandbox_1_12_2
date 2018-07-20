package com.github.commoble.sandbox.common.item;

import com.github.commoble.sandbox.common.SandboxMod;
import com.github.commoble.sandbox.common.block.BlockLedger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Class for registering items and itemblocks, and keeping their references
 * also handle creative tabs since those are closely related to items
 */
public class ItemLedger
{
	// creative tab for the stuff
	public static final CreativeTabs trtab = new CreativeTabs("dungeonfist") {
		@Override
		public ItemStack getTabIconItem()
		{
			return new ItemStack(Items.GOLD_INGOT);
		}
	};
	
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		//grinderItemBlock = registerItemBlock(event.getRegistry(), new ItemBlock(BlockLedger.grinderBlock), "grinder");
		//grinderItemBlock.setCreativeTab(trtab);
	}
	
	private static Item registerItem(IForgeRegistry<Item> registry, Item newItem, String name)
	{
		name = SandboxMod.appendPrefix(name);
		newItem.setUnlocalizedName(name);
		newItem.setRegistryName(name);
		registry.register(newItem);
		return newItem;
	}
	
	private static ItemBlock registerItemBlock(IForgeRegistry<Item> registry, ItemBlock newItemBlock, String name)
	{
		name = SandboxMod.appendPrefix(name);
		newItemBlock.setUnlocalizedName(name);
		newItemBlock.setRegistryName(name);
		registry.register(newItemBlock);
		return newItemBlock;
	}
}
