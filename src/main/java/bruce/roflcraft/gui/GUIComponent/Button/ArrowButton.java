package bruce.roflcraft.gui.GUIComponent.Button;

import bruce.roflcraft.main.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class ArrowButton extends GUIButtonComponent
{
	private static final String BUTTON_TEXTURE_NAME = "ArrowButton.png";
	private static final ResourceLocation BUTTON_RESOURCE = new ResourceLocation(Reference.MODID + ":textures/gui/" + BUTTON_TEXTURE_NAME);
	private static final int BUTTON_WIDTH = 32;
	private static final int BUTTON_HEIGHT = 32;
	private static final int MAX_IMAGE_INDEX = 7;
	private static final int MAX_PULSE_INDEX = 7;
	private static final int MAX_TICK_INDEX = 4;
	private int m_imageIndex;
	private int m_pulseIndex;
	private int m_tickIndex;
	
	public ArrowButtonDirection arrowDirection;
	
	public ArrowButton()
	{
		arrowDirection = ArrowButtonDirection.ARROW_BUTTON_UP;
	}
	
	@Override
	public void init(int parentLeft, int parentTop)
	{
		super.init(parentLeft, parentTop);
	}
	
	@Override
	public int getWidth() 
	{
		return BUTTON_WIDTH;
	}

	@Override
	public int getHeight() 
	{
		return BUTTON_HEIGHT;
	}

	@Override
	public void drawComponent(Minecraft mc, int mouseX, int mouseY, float deltaSeconds)
	{
		super.drawComponent(mc, mouseX, mouseY, deltaSeconds);
		m_tickIndex++;
		updateImageIndexes();
		mc.getTextureManager().bindTexture(BUTTON_RESOURCE);
		drawTexturedModalRect(getActualLeft(), getActualTop(), getTextureU(), getTextureV(), BUTTON_WIDTH, BUTTON_HEIGHT);
		//drawTexturedModalRect(getActualLeft(), getActualTop(), getTextureU() + 32, getTextureV() + m_pulseIndex * 32, BUTTON_WIDTH, BUTTON_HEIGHT);
	}
	
	private void updateImageIndexes()
	{
		if(m_tickIndex >= MAX_TICK_INDEX)
		{
			m_tickIndex = 0;
			m_pulseIndex++;
			if(m_pulseIndex > MAX_PULSE_INDEX)
			{
				m_pulseIndex = 0;
			}
			if(getIsMouseOverButton() && m_imageIndex < MAX_IMAGE_INDEX)
			{
				m_imageIndex++;
			}
			else if(m_imageIndex > 0 && getIsMouseOverButton() == false)
			{
				m_imageIndex--;
			}
		}
	}
	
	private int getTextureU()
	{
		return arrowDirection.ordinal() * BUTTON_WIDTH;
	}
	
	private int getTextureV()
	{
		return m_imageIndex * BUTTON_HEIGHT;
	}
}
