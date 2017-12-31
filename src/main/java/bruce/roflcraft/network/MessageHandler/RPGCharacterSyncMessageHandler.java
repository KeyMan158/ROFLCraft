package bruce.roflcraft.network.MessageHandler;

import bruce.roflcraft.network.Message.RPGCharacterSyncMessage;
import bruce.roflcraft.rpg.character.IRPGCharacterData;
import bruce.roflcraft.rpg.character.RPGCharacterProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class RPGCharacterSyncMessageHandler implements IMessageHandler<RPGCharacterSyncMessage, IMessage>
{

	@Override
	public IMessage onMessage(RPGCharacterSyncMessage message, MessageContext ctx) 
	{
		// Set the character data on the client based on what was sent from the server
		try
		{
			IRPGCharacterData characterData = Minecraft.getMinecraft().player.getCapability(RPGCharacterProvider.CHAR_CAP, null);
			characterData.rpgCharacterFromNBT(message.getCharacterData());
		}
		catch (Exception e)
		{
			// TODO This can take a while, so optimise ...
			System.out.println("Failed to sync player data, attempting re-sync");
			onMessage(message, ctx);
		}
		return null;
	}
}
