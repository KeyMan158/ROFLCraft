package bruce.roflcraft.rpg.character;

//import com.sun.media.jfxmedia.events.PlayerEvent;

import bruce.roflcraft.handlers.RoflCraftPacketHandler;
import bruce.roflcraft.network.Message.RPGCharacterSyncMessage;
import bruce.roflcraft.network.Message.RequestRPGCharacterSyncMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

/**
 * Forge mod event bus subscriptions to handle 
 * character data
 * @author Lorrtath
 */
public class RPGCharacterEventHandler 
{
	private void syncClientToServer(EntityPlayerMP player)
	{
		IRPGCharacterData serverCharacterData = player.getCapability(RPGCharacterProvider.CHAR_CAP, null);
		RoflCraftPacketHandler.sendToPlayer(new RPGCharacterSyncMessage((NBTTagCompound)serverCharacterData.rpgCharacterToNBT()), (EntityPlayerMP)player);
	}
	
	@SubscribeEvent
	public void onPlayerLogsIn(PlayerLoggedInEvent event)
	{
		EntityPlayer player = event.player;
		IRPGCharacterData characterData = player.getCapability(RPGCharacterProvider.CHAR_CAP, null);
		player.sendMessage(new TextComponentString("Mod load test sucsesfull"));
		if(player instanceof EntityPlayerMP)
		{
			player.sendMessage(new TextComponentString("Mod load test sucsesfull - Server Side"));
			syncClientToServer((EntityPlayerMP)player);
		}
		else
		{
			player.sendMessage(new TextComponentString("Mod load test sucsesfull - Client Side"));
		}
	}
		
	/**
	 * Called when a player dies or moves to another world
	 * @param event
	 */
	@SubscribeEvent
	public void onPlayerClone(net.minecraftforge.event.entity.player.PlayerEvent.Clone event)
	{
		EntityPlayer player = event.getEntityPlayer();
		IRPGCharacterData characterData = player.getCapability(RPGCharacterProvider.CHAR_CAP, null);
		characterData.Duplicate(event.getOriginal().getCapability(RPGCharacterProvider.CHAR_CAP, null));
		if (player instanceof EntityPlayerMP)
		{
			syncClientToServer((EntityPlayerMP)player);
		}
	}
}
