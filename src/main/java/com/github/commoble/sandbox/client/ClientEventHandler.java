package com.github.commoble.sandbox.client;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientEventHandler
{
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent e)
	{
		
	}
	
	private  void registerItemRenderer(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
