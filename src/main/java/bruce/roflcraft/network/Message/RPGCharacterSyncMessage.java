package bruce.roflcraft.network.Message;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * Data packet message class for sending all of the RPGCharacter
 * data to the players client from the server
 * @author Lorrtath
 *
 */
public class RPGCharacterSyncMessage implements IMessage
{
	NBTTagCompound m_characterData;

	public RPGCharacterSyncMessage()
	{
	}
	
	public RPGCharacterSyncMessage(NBTTagCompound characterData)
	{
		m_characterData = characterData;
	}
	
	public NBTTagCompound getCharacterData()
	{
		return m_characterData;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		m_characterData = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		ByteBufUtils.writeTag(buf, m_characterData);
	}

}
