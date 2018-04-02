package bruce.roflcraft.gui;

import bruce.roflcraft.main.Reference;
import net.minecraft.util.ResourceLocation;

@Deprecated
public class TestAnimation 
{
	private int m_tickIndex;
	private int m_frame;
	private final int MAX_INDEX = 3;
	
	public TestAnimation()
	{
		m_tickIndex = -1;
		m_frame = 4;
	}
	
	public void onRenderTick()
	{
		m_tickIndex++;
		if (m_tickIndex > MAX_INDEX)
		{
			m_tickIndex = 0;
			m_frame++;
		}
		
		if (m_frame > 7)
		{
			m_frame = 0;
		}
	}
	
	public ResourceLocation getResourceLocation()
	{
		return new ResourceLocation( Reference.MODID + ":textures/gui/StatTransition.png");
	}
	
	public int getWidth()
	{
		return 32;
	}
	
	public int getHeight()
	{
		return 32;
	}
	
	public int getLeft()
	{
		return 0;
	}
	
	public int getTop()
	{
		return 32* m_frame;
	}
	
	public int getU()
	{
		return 32;
	}
	
	public int getV()
	{
		return 32 + (m_tickIndex * 32);
	}
}
