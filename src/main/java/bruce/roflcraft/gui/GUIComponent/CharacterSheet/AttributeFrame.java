package bruce.roflcraft.gui.GUIComponent.CharacterSheet;

import org.lwjgl.opengl.GL11;

import bruce.roflcraft.gui.GUIComponent.IGUIComponent;
import bruce.roflcraft.main.Reference;
import bruce.roflcraft.rpg.character.RPGCharacterData;
import bruce.roflcraft.rpg.character.RPGCharacterProvider;
import bruce.roflcraft.rpg.character.stats.AttributeCollection;
import bruce.roflcraft.rpg.character.stats.AttributeIndex;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

/**
 * This class provides the GUI for an attribute
 * within the character sheet interface
 * @author Lorrtath
 *
 */
public class AttributeFrame extends Gui implements IGUIComponent
{
	private static final String FRAME_TEXTURE_NAME = "attributeframe.png";
	private static final ResourceLocation FRAME_RESOURCE = new ResourceLocation(Reference.MODID , "textures/gui/" + FRAME_TEXTURE_NAME);
	
	private static final int FRAME_HEIGHT = 64;
	private static final int FRAME_WIDTH = 64;
	
	private static final int FRAME_BACK_TOP = 16;
	private static final int FRAME_BACK_LEFT = 64;
	private static final int FRAME_BACK_HEIGHT = 32;
	private static final int FRAME_BACK_WIDTH = 32;
	private static final int FRAME_BACK_MARGIN = 16;
	private static final int FRAME_BACK_COUNT = 6;
	private static final float FRAME_BACK_TICK_DUR = 3f;
	
	private int m_parentLeft;
	private int m_parentTop;
	private int m_left;
	private int m_top;
	
	private boolean m_isVisable;
	private AttributeIndex m_attribute;
	private AttributeCollection m_attributes;
	private int m_backgroundIndex;
	private float m_backgroundTickCounter;
	
	public AttributeFrame(AttributeIndex attribute)
	{
		m_isVisable = true;
		m_attribute = attribute;
		m_parentLeft = 0;
		m_parentTop = 0;
		m_left = 0;
		m_top = 0;
		m_backgroundIndex = 0;
		m_backgroundTickCounter = 0;
	}
	
	@Override
	public void init(int parentLeft, int parentTop) 
	{
		RPGCharacterData characterData = (RPGCharacterData)Minecraft.getMinecraft().player.getCapability(RPGCharacterProvider.CHAR_CAP, null);
		m_attributes = characterData.getAttributes();
	}
	
	@Override
	public void drawComponent(Minecraft mc, int mouseX, int mouseY, float deltaSeconds) 
	{
		if(m_isVisable)
		{
			updateTickIndexes(deltaSeconds);
			mc.getTextureManager().bindTexture(FRAME_RESOURCE);
			// Draw background
			drawTexturedModalRect(
					getActualLeft() + FRAME_BACK_MARGIN, 
					getActualTop() + FRAME_BACK_MARGIN, 
					FRAME_BACK_LEFT + m_backgroundIndex * FRAME_BACK_WIDTH, 
					FRAME_BACK_TOP + m_attribute.ordinal() * FRAME_HEIGHT, 
					FRAME_BACK_WIDTH, 
					FRAME_BACK_HEIGHT);
			// Draw forground
			drawTexturedModalRect(getActualLeft(), getActualTop(), 0, 0 + m_attribute.ordinal() * FRAME_HEIGHT , FRAME_WIDTH, FRAME_HEIGHT);
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
		return FRAME_HEIGHT;
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
	
	/**
	 * Method for updating animation tick counters etc
	 * @param deltaSeconds The time since the last frame
	 */
	private void updateTickIndexes(float deltaSeconds)
	{
		m_backgroundTickCounter += deltaSeconds;
		if(m_backgroundTickCounter >= FRAME_BACK_TICK_DUR)
		{
			m_backgroundTickCounter = 0;
			m_backgroundIndex ++;
		}
		if(m_backgroundIndex >= FRAME_BACK_COUNT)
		{
			m_backgroundIndex = 0;
		}
	}
}
