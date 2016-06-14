package bruce.roflcraft.proxy;

import bruce.roflcraft.handlers.GUIHandler;
import bruce.roflcraft.main.RoflCraft;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy 
{
	public void preInit(FMLPreInitializationEvent event)
	{
		
	}
	
	public void init(FMLInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(RoflCraft.instance, new GUIHandler());
	}
	
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
