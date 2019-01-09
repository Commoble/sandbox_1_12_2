package com.github.commoble.sandbox.common.block;

import com.github.commoble.sandbox.common.SandboxMod;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Class for registering blocks and storing their references
 */
public class BlockRegistrar
{	
	//public static final String TRANSPORTER_REGISTRY_NAME = "transporter";
	
	@ObjectHolder("sandbox:ash")
	public static final BlockAsh ash = null;
	
	@ObjectHolder("sandbox:battery")
	public static final BlockBattery battery = null;
	
	@ObjectHolder("sandbox:wire")
	public static final BlockWire wire = null;
	
	@ObjectHolder("sandbox:lightbulb")
	public static final BlockLightbulb lightbulb = null;
	
	

	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		IForgeRegistry<Block> registry = event.getRegistry();
		//BlockLedger.blockTransporter = (BlockTransporter)registerBlock(event.getRegistry(), new BlockTransporter(), BlockLedger.TRANSPORTER_REGISTRY_NAME);
		
		// override default fire block
		BlockExtendedFire blockExtendedFire = new BlockExtendedFire();
		blockExtendedFire.setRegistryName("minecraft:fire");
		blockExtendedFire.setTranslationKey("minecraft:fire");
		registry.register(blockExtendedFire);
		
		registerBlock(registry, new BlockAsh(), BlockNames.ASH_NAME);
		registerBlock(registry, new BlockBattery(), BlockNames.BATTERY_NAME);
		registerBlock(registry, new BlockWire(), BlockNames.WIRE_NAME);
		registerBlock(registry, new BlockLightbulb(), BlockNames.LIGHTBULB_NAME);
		
	}
	
	private static <T extends Block> T registerBlock(IForgeRegistry<Block> registry, T newBlock, String name)
	{
		name = SandboxMod.appendPrefix(name);
		newBlock.setTranslationKey(name);
		newBlock.setRegistryName(name);
		registry.register(newBlock);
		return newBlock;
	}
}
