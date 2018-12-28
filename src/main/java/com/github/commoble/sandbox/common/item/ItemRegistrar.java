package com.github.commoble.sandbox.common.item;

import com.github.commoble.sandbox.common.SandboxMod;
import com.github.commoble.sandbox.common.block.BlockNames;
import com.github.commoble.sandbox.common.block.BlockRegistrar;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Class for registering items and itemblocks, and keeping their references
 * also handle creative tabs since those are closely related to items
 */
public class ItemRegistrar
{
	// creative tab for the stuff
	public static final CreativeTabs tab = new CreativeTabs("sandbox") {
		@Override
		public ItemStack createIcon()
		{
			return new ItemStack(Blocks.SANDSTONE);
		}
	};
	
	//public static ItemBlock itemBlockTransporter;
	@ObjectHolder("sandbox:ash")
	public static final Item ash = null;
	
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		IForgeRegistry<Item> registry = event.getRegistry();
		//ItemLedger.itemBlockTransporter = registerItemBlock(event.getRegistry(), new ItemBlock(BlockLedger.blockTransporter), BlockLedger.TRANSPORTER_REGISTRY_NAME);
		//grinderItemBlock = registerItemBlock(event.getRegistry(), new ItemBlock(BlockLedger.grinderBlock), "grinder");
		//grinderItemBlock.setCreativeTab(trtab);
		registerItem(registry, new ItemBlock(BlockRegistrar.ash), BlockNames.ASH_NAME);
	}
	
	private static <T extends Item> T registerItem(IForgeRegistry<Item> registry, T newItem, String name)
	{
		name = SandboxMod.appendPrefix(name);
		newItem.setTranslationKey(name);
		newItem.setRegistryName(name);
		registry.register(newItem);
		return newItem;
	}
}
