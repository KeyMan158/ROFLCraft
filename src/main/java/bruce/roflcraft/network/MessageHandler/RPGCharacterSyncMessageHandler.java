package bruce.roflcraft.network.MessageHandler;

import bruce.roflcraft.network.Message.RPGCharacterSyncMessage;
import bruce.roflcraft.rpg.character.IRPGCharacterData;
import bruce.roflcraft.rpg.character.RPGCharacterProvider;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class RPGCharacterSyncMessageHandler implements IMessageHandler<RPGCharacterSyncMessage, IMessage>
{

	@Override
	public IMessage onMessage(RPGCharacterSyncMessage message, MessageContext ctx) 
	{
		// Set the character data on the client based on what was sent from the server
		IRPGCharacterData characterData = Minecraft.getMinecraft().thePlayer.getCapability(RPGCharacterProvider.CHAR_CAP, null);
		characterData.rpgCharacterFromNBT(message.getCharacterData());
		return null;
	}
}
