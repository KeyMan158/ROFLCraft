package bruce.roflcraft.client.gui.component.character;

import java.util.ArrayList;
import java.util.List;

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
import net.minecraftforge.fml.client.config.GuiEditArrayEntries.IArrayEntry;

/**
 * This class provides the GUI for an attribute
 * within the character sheet interface
 * @author Lorrtath
 *
 */
public class AttributeFrame extends Gui implements IGUIComponent, IAttributeBasedComponent
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
	private List<IAttributeControlledComponent> m_attributeDependancies;
	private float m_angle;
	
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
		m_attributeDependancies = new ArrayList<IAttributeControlledComponent>();
		switch(attribute)
		{
		case AT_BODY:
			m_angle = 0;
			break;
		case AT_MIND:
			m_angle = 120;
			break;
		case AT_SOUL:
			m_angle = 240;
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
		if(!m_isVisable)
		{
			return;
		}
		updateTickIndexes(deltaSeconds);
        GL11.glEnable(GL11.GL_BLEND);
		GL11.glPushMatrix();
		GL11.glTranslatef(getActualLeft(), getActualTop(), 0);
		GL11.glRotated(m_angle, 0, 0, 1);
		mc.getTextureManager().bindTexture(FRAME_RESOURCE);
		// Draw background
		drawTexturedModalRect(
				FRAME_BACK_WIDTH / -2,
				FRAME_BACK_HEIGHT / -2,
				FRAME_BACK_LEFT, 
				FRAME_BACK_TOP, 
				FRAME_BACK_WIDTH, 
				FRAME_BACK_HEIGHT);
		// Draw foreground
		drawTexturedModalRect(
				FRAME_WIDTH / -2, 
				FRAME_HEIGHT / -2, 
				0, 
				0, 
				FRAME_WIDTH, 
				FRAME_HEIGHT);
        mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID , "textures/gui/character_sheet_details.png"));
        drawTexturedModalRect( (FRAME_WIDTH /- 2) + 16, (FRAME_HEIGHT / -2) + 16, 0, 0, 32, 32 );
        GL11.glPopMatrix();
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

	@Override
	public void registerListener(IAttributeControlledComponent listener) 
	{
		m_attributeDependancies.add(listener);
	}

	@Override
	public void unregisterListener(IAttributeControlledComponent listener) 
	{
		m_attributeDependancies.remove(listener);
	}

	@Override
	public void setAttribute(AttributeIndex index) 
	{
		m_attribute = index;
	}

	@Override
	public AttributeIndex getAttribute() 
	{
		return m_attribute;
	}

	@Override
	public void broadcastAttribute() 
	{
		for(int i = 0; i < m_attributeDependancies.size(); i++)
		{
			m_attributeDependancies.get(i).changeAttribute(m_attribute, this);
		}
	}
}
