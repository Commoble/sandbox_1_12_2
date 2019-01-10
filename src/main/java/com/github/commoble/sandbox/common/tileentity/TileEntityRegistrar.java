package com.github.commoble.sandbox.common.tileentity;

import com.github.commoble.sandbox.common.SandboxMod;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityRegistrar
{
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityBattery.class, new ResourceLocation(SandboxMod.MODID, "te_battery"));
		GameRegistry.registerTileEntity(TileEntityLightbulb.class, new ResourceLocation(SandboxMod.MODID, "te_lightbulb"));
		GameRegistry.registerTileEntity(TileEntityWire.class, new ResourceLocation(SandboxMod.MODID, "te_wire"));
		
	}
}
