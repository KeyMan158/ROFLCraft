package bruce.roflcraft.network.Message;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * Message class that represents the return data packet when skill point
 * is purchased on the server. This data packet is returned to the client
 * to assign values matching those of the server
 * @author Lorrtath
 */
public class SkillPointReciptMessage implements IMessage
{
	int m_skillLevel;
	int m_poitnsAvailable;
	int m_xp;
	
	public SkillPointReciptMessage()
	{
		m_skillLevel = 0;
		m_poitnsAvailable = 0;
		m_xp = 0;
	}
	
	public SkillPointReciptMessage(int level, int pointsAvailable, int xp)
	{
		m_skillLevel = level;
		m_poitnsAvailable = pointsAvailable;
		m_xp = xp;
	}

	public int getLevel()
	{
		return m_skillLevel;
	}
	
	public int getAvailable()
	{
		return m_poitnsAvailable;
	}
	
	public int getXP()
	{
		return m_xp;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		m_skillLevel = buf.readInt();
		m_poitnsAvailable = buf.readInt();
		m_xp = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(m_skillLevel);
		buf.writeInt(m_poitnsAvailable);
		buf.writeInt(m_xp);
	}

}
