package com.github.commoble.sandbox.common;

import com.github.commoble.sandbox.common.block.BlockExtendedFire;
import com.github.commoble.sandbox.common.world.WorldGenManager;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy
{
	// config values
	
	// blocks
	
	// items
	
	// sounds
	
	// misc
	public static WorldGenManager worldGenManager = new WorldGenManager();
	
	public static int modEntityID = 0;
	
	/**
	 * Run before anything else;
	 * Declare configuration parameters;
	 * Register everything that doesn't require something else to be registered first
	 */
	public void preInit(FMLPreInitializationEvent event)
	{
		// Registration
		// Blocks, Enchantments, Items, Potions, SoundEvents, and Biomes should be registered with registry events
		// Entities, Tile Entities, and Dimensions need to be registered here
		//this.registerTileEntities();
		//GameRegistry.registerTileEntity(TileEntityTransporter.class, new ResourceLocation(SandboxMod.MODID, "te_transporter"));
		//this.registerEntities();
		//this.registerPlanes();
		GameRegistry.registerWorldGenerator(worldGenManager, 0);
	}
	
	/**
	 * The most important things to do in the main load event are:
	 * Register recipes, send FMLInterModComms messages to other mods, build data structures that shouldn't be in the other events
	 */
	public void load(FMLInitializationEvent event)
	{
		// register recipes
		
		// redo flammability init
		// must delay until this point or it'll affect the vanilla fire block instead
		BlockExtendedFire.init();
	}
	
	/**
	 * Handle interactions with other mods and complete setup
	 * e.g. registering creature spawning should go here due to other mods potentially creating new biomes
	 */
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
