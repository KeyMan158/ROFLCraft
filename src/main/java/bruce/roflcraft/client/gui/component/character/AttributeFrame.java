package bruce.roflcraft.client.gui.component.character;

import org.lwjgl.opengl.GL11;

import bruce.roflcraft.client.gui.component.GUIComponentScreen;
import bruce.roflcraft.client.gui.component.IGUIComponent;
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
	private static final String FRAME_TEXTURE_NAME = "attribute_frame.png";
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
	private int m_leftOffset;
	private int m_topOffset;
	
	private boolean m_isVisable;
	private AttributeIndex m_attribute;
	private AttributeCollection m_attributes;
	private int m_backgroundIndex;
	private float m_backgroundTickCounter;
	private IGUIComponent m_parent;
	private GUIComponentScreen m_root;
	
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
		switch(attribute)
		{
		case AT_BODY:
			m_leftOffset = 32;
			m_topOffset = -32;
			break;
		case AT_MIND:
			m_leftOffset = 88;
			m_topOffset = 64;
			break;
		case AT_SOUL:
			m_leftOffset = -24;
			m_topOffset = 64;
			break;
		default:
			break;
		
		}
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
	        GL11.glEnable(GL11.GL_BLEND);
			mc.getTextureManager().bindTexture(FRAME_RESOURCE);
			// Draw background
			drawTexturedModalRect(
					0, //((m_root.width - FRAME_BACK_WIDTH)/2) + getActualLeft() + FRAME_BACK_MARGIN, 
					0,  //((m_root.height + FRAME_BACK_HEIGHT)/2) + getActualTop() + FRAME_BACK_MARGIN, 
					FRAME_BACK_LEFT + m_backgroundIndex * FRAME_BACK_WIDTH, 
					FRAME_BACK_TOP + m_attribute.ordinal() * FRAME_HEIGHT, 
					FRAME_BACK_WIDTH, 
					FRAME_BACK_HEIGHT);
			// Draw foreground
			drawTexturedModalRect(
					((m_root.width - FRAME_WIDTH)/2), 
					(m_root.height/2) + 64, 
					0, 
					0 + m_attribute.ordinal() * FRAME_HEIGHT, 
					FRAME_WIDTH, 
					FRAME_HEIGHT);

	        mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID , "textures/gui/character_sheet_details.png"));
	        drawTexturedModalRect(((m_root.width - FRAME_WIDTH)/2) + 16, (m_root.height/2) + 80, 0, 0, 32, 32 );
		}
	}
	
	@Override
	public int getActualTop() 
	{
		return m_top + m_topOffset + m_parent.getActualTop();
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
		return m_left + m_leftOffset + m_parent.getActualLeft();
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

	@Override
	public void registerParent(IGUIComponent parent) 
	{
		m_parent = parent;
	}

	@Override
	public IGUIComponent getParent() 
	{
		return m_parent;
	}

	@Override
	public void registerRoot(GUIComponentScreen root) 
	{
		m_root = root;
	}

	@Override
	public GUIComponentScreen getRoot() 
	{
		return m_root;
	}
}
