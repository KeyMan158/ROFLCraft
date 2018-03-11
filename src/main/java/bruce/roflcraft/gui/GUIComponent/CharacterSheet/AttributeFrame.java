package bruce.roflcraft.gui.GUIComponent.CharacterSheet;

import bruce.roflcraft.gui.GUIComponent.IGUIComponent;
import bruce.roflcraft.main.Reference;
import bruce.roflcraft.rpg.character.RPGCharacterData;
import bruce.roflcraft.rpg.character.RPGCharacterProvider;
import bruce.roflcraft.rpg.character.stats.AttributeCollection;
import bruce.roflcraft.rpg.character.stats.AttributeIndex;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

public class AttributeFrame extends Gui implements IGUIComponent
{
	private static final String FRAME_TEXTURE_NAME = "attributeframe.png";
	private static final ResourceLocation FRAME_RESOURCE = new ResourceLocation(Reference.MODID , "textures/gui/" + FRAME_TEXTURE_NAME);
	
	private static final int FRAME_HEIGHT = 38;
	private static final int FRAME_WIDTH = 38;
	
	private static final int XP_BAR_TOP = 38;
	private static final int XP_BAR_LEFT = 0;
	private static final int XP_BAR_HEIGHT = 6;
	private static final int XP_BAR_WIDTH = 38;
	
	private int m_parentLeft;
	private int m_parentTop;
	private int m_left;
	private int m_top;
	
	private boolean m_isVisable;
	private AttributeIndex m_attribute;
	private AttributeCollection m_attributes;
	
	public AttributeFrame(AttributeIndex attribute)
	{
		m_isVisable = true;
		m_attribute = attribute;
		m_parentLeft = 0;
		m_parentTop = 0;
		m_left = 0;
		m_top = 0;
	}
	
	@Override
	public void init(int parentLeft, int parentTop) 
	{
		RPGCharacterData characterData = (RPGCharacterData)Minecraft.getMinecraft().player.getCapability(RPGCharacterProvider.CHAR_CAP, null);
		m_attributes = characterData.getAttributes();
	}
	
	@Override
	public void drawComponent(Minecraft mc, int mouseX, int mouseY) 
	{
		if(m_isVisable)
		{
			mc.getTextureManager().bindTexture(FRAME_RESOURCE);
			// Draw XP bar Background:
			drawTexturedModalRect(getActualLeft(), getActualTop() + FRAME_HEIGHT, XP_BAR_LEFT, XP_BAR_TOP + (2* XP_BAR_HEIGHT), XP_BAR_WIDTH, XP_BAR_HEIGHT);
			// Draw Xp:
			int xpWidth = (int) (((XP_BAR_WIDTH - 2) / 3) * m_attributes.getAttributeProgress(m_attribute)); // Make this tick based
			drawTexturedModalRect(getActualLeft(), getActualTop() + FRAME_HEIGHT, XP_BAR_LEFT, XP_BAR_TOP + XP_BAR_HEIGHT, xpWidth, XP_BAR_HEIGHT);
			//Draw the frame:
			drawTexturedModalRect(getActualLeft(), getActualTop(), 0, 0, FRAME_WIDTH, FRAME_HEIGHT + XP_BAR_HEIGHT);
		}
	}
	
	@Override
	public int getActualTop() 
	{
		return m_top + m_parentTop;
	}
	
	@Override
	public int getTop() 
	{
		return m_top;
	}
	
	@Override
	public void setTop(int top) 
	{
		m_top = top;
	}
	
	@Override
	public int getActualLeft() 
	{
		return m_left + m_left;
	}
	
	@Override
	public int getLeft() 
	{
		return m_left;
	}
	@Override
	public void setLeft(int left) 
	{
		m_left = left;
	}
	
	@Override
	public int getWidth() 
	{
		return FRAME_WIDTH;
	}
	
	@Override
	public int getHeight() 
	{
		return FRAME_HEIGHT + XP_BAR_HEIGHT;
	}
	
	@Override
	public boolean getVisibility() 
	{
		return m_isVisable;
	}
	
	@Override
	public void setVisibility(boolean visability) 
	{
		m_isVisable = visability;
	}
	
	
}
