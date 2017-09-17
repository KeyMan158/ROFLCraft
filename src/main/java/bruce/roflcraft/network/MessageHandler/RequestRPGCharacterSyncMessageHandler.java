package bruce.roflcraft.network.MessageHandler;

import bruce.roflcraft.network.Message.RPGCharacterSyncMessage;
import bruce.roflcraft.network.Message.RequestRPGCharacterSyncMessage;
import bruce.roflcraft.rpg.character.IRPGCharacterData;
import bruce.roflcraft.rpg.character.RPGCharacterProvider;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class RequestRPGCharacterSyncMessageHandler implements IMessageHandler<RequestRPGCharacterSyncMessage, RPGCharacterSyncMessage>
{

	@Override
	public RPGCharacterSyncMessage onMessage(RequestRPGCharacterSyncMessage message, MessageContext ctx) 
	{
		EntityPlayerMP player = ctx.getServerHandler().playerEntity;
		IRPGCharacterData characterData = player.getCapability(RPGCharacterProvider.CHAR_CAP, null);
		return new RPGCharacterSyncMessage((NBTTagCompound)characterData.rpgCharacterToNBT());
	}

}
