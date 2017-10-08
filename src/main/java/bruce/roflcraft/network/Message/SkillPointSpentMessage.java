package bruce.roflcraft.network.Message;

import bruce.roflcraft.rpg.character.stats.SkillIndex;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class SkillPointSpentMessage implements IMessage
{
	private int m_skillPoints;
	private int m_index;
	
	public SkillPointSpentMessage()
	{
	}
	
	public SkillPointSpentMessage(int points, SkillIndex index)
	{
		m_skillPoints = points;
		m_index = index.ordinal();
	}
	
	public int getPoints()
	{
		return m_skillPoints;
	}
	
	public SkillIndex getIndex()
	{
		return SkillIndex.values()[m_index];
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		m_skillPoints = buf.readInt();
		m_index = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(m_skillPoints);
		buf.writeInt(m_index);
	}

}
