package bruce.roflcraft.proxy;

import bruce.roflcraft.handlers.KeyInputHandler;
import bruce.roflcraft.init.KeyBindings;
import bruce.roflcraft.init.ROFLCraftBlocks;
import bruce.roflcraft.init.ROFLCraftItems;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
	KeyBindings keyBindings;
	
	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);
		
		//Initialise Key Bindings:
		FMLCommonHandler.instance().bus().register(new KeyInputHandler());
		keyBindings = new KeyBindings();//should use .init()?
		
		//Initialise Items:
		ROFLCraftItems.init();
		
		//Initialise Blocks
		ROFLCraftBlocks.init();
	}
	
	@Override
	public void init(FMLInitializationEvent event)
	{
		super.init(event);
		ROFLCraftItems.registerRenders();
		ROFLCraftBlocks.registerRenders();
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
		super.postInit(event);
	}
}
