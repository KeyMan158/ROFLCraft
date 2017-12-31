package bruce.roflcraft.proxy;

import bruce.roflcraft.handlers.GUIHandler;
import bruce.roflcraft.handlers.RoflCraftPacketHandler;
import bruce.roflcraft.handlers.SkillHandler;
import bruce.roflcraft.main.RoflCraft;
import bruce.roflcraft.rpg.RPGCapabilityHandler;
import bruce.roflcraft.rpg.character.IRPGCharacterData;
import bruce.roflcraft.rpg.character.RPGCharacterData;
import bruce.roflcraft.rpg.character.RPGCharacterDataStorage;
import bruce.roflcraft.rpg.character.RPGCharacterEventHandler;
import bruce.roflcraft.rpg.character.Race.RaceManager;
import bruce.roflcraft.rpg.rolls.SkillRollTableManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy 
{
	public RaceManager m_raceManager;
	
	public void preInit(FMLPreInitializationEvent event)
	{

	}
	
	public void init(FMLInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(RoflCraft.instance, new GUIHandler());
		
		// Register the RPGCharacterData System
		CapabilityManager.INSTANCE.register(IRPGCharacterData.class, new RPGCharacterDataStorage(), RPGCharacterData.class);
				
		//Register packet handlers
		if(event.getSide() == Side.CLIENT)
		{
			RoflCraftPacketHandler.registerMessages();
		}
		else
		{
			RoflCraftPacketHandler.registerServerMessages();
		}
		
		//Register event handlers
		MinecraftForge.EVENT_BUS.register(new RPGCapabilityHandler());
		MinecraftForge.EVENT_BUS.register(new RPGCharacterEventHandler());
		
	}
	
	public void postInit(FMLPostInitializationEvent event)
	{		
		// Initialise skills via the handler
		SkillRollTableManager.init();
		SkillHandler.init();
		
		if (event.getSide() == Side.SERVER)
		{
			m_raceManager = new RaceManager();
			m_raceManager.initRaces();
		}
	}
}
