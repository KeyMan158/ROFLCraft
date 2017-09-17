package bruce.roflcraft.proxy;

import bruce.roflcraft.handlers.KeyInputHandler;
import bruce.roflcraft.handlers.RoflCraftPacketHandler;
import bruce.roflcraft.init.KeyBindings;
import bruce.roflcraft.init.ROFLCraftBlocks;
import bruce.roflcraft.init.ROFLCraftItems;
import bruce.roflcraft.rpg.character.IRPGCharacterData;
import bruce.roflcraft.rpg.character.RPGCharacterData;
import bruce.roflcraft.rpg.character.RPGCharacterDataStorage;
import bruce.roflcraft.rpg.character.RPGCharacterEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
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
		RoflCraftPacketHandler.registerMessages();
		ROFLCraftItems.registerRenders();
		ROFLCraftBlocks.registerRenders();
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
		super.postInit(event);
	}
}
