package bruce.roflcraft.network.Message;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * A network packet to communicate player XP drain when purchasing RPG
 * skill points
 * @author Lorrtath
 */
public class SkillPointPurchaseMessage implements IMessage
{
	int m_xpLevelsDrained;
	
	public SkillPointPurchaseMessage(){}
	
	public SkillPointPurchaseMessage(int xpDrained)
	{
		m_xpLevelsDrained = xpDrained;
	}
	
	/**
	 * Gets the number of player XP levels drained
	 * @return
	 */
	public int getXPLevels()
	{
		return m_xpLevelsDrained;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		m_xpLevelsDrained = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(m_xpLevelsDrained);
	}

}
