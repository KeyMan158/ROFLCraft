package bruce.roflcraft.network.Message;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * Empty message sent to the server to request a sync of the server side
 * RPGCharacter data
 * @author Lorrtath
 */
public class RequestRPGCharacterSyncMessage implements IMessage
{
	public RequestRPGCharacterSyncMessage()
	{
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{		
	}

}
