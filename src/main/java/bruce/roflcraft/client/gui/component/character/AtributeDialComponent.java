package bruce.roflcraft.client.gui.component.character;

import org.lwjgl.opengl.GL11;

import bruce.roflcraft.client.gui.component.GUIComponentScreen;
import bruce.roflcraft.client.gui.component.IGUIComponent;
import bruce.roflcraft.main.Reference;
import bruce.roflcraft.rpg.character.stats.AttributeCollection;
import bruce.roflcraft.rpg.character.stats.AttributeIndex;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

/**
 * This class provides the GUI component for the centeral 
 * level up wheel and the rotating attribute dial
 * @author Lorrtath
 *
 */
public class AtributeDialComponent extends Gui implements IGUIComponent
{
	private static final String DIAL_TEXTURE_NAME = "attribute_dial.png";
	private static final ResourceLocation DIAL_RESOURCE = new ResourceLocation(Reference.MODID , "textures/gui/" + DIAL_TEXTURE_NAME);
	private static final int WIDTH = 128;
	private static final int HEIGHT = 128;
	private static final int BACKGROUND_HEIGHT = 32;
	private static final int BACKGROUND_WIDTH = 32;
	private static final int MAX_BACKGROUND_MODIFIER = 4;
	private static final float MAX_BACK_TICK_DUR = 3f;

	private int m_parentLeft;
	private int m_parentTop;
	private int m_left;
	private int m_top;
	
	private boolean m_isVisable;
	private AttributeIndex m_attribute;
	private AttributeCollection m_attributes;
	private int m_backgroundTopModifier;
	private int m_backgroundWidthModifier;
	private float m_backgroundTickCounter;
	private IGUIComponent m_parent;
	private GUIComponentScreen m_root;
	
	@Override
	public void init(int parentLeft, int parentTop) 
	{
		m_parentLeft = parentLeft;
		m_parentTop = parentTop;
		m_isVisable = true;
	}

	@Override
	public void drawComponent(Minecraft mc, int mouseX, int mouseY, float deltaSeconds) 
	{
		if(!m_isVisable)
		{
			return;
		}
		UpdateTicks(deltaSeconds);
		mc.getTextureManager().bindTexture(DIAL_RESOURCE);
        GL11.glEnable(GL11.GL_BLEND);
		drawTexturedModalRect(m_top + (m_root.width - BACKGROUND_WIDTH) / 2, 
				m_left + (m_root.height - BACKGROUND_HEIGHT) / 2, 
				128 + (m_backgroundWidthModifier * BACKGROUND_WIDTH), 
				128 + (m_backgroundTopModifier * BACKGROUND_HEIGHT), 
				BACKGROUND_WIDTH, 
				BACKGROUND_HEIGHT);
		drawTexturedModalRect(m_top + (m_root.width - WIDTH) / 2, 
				m_left + (m_root.height - HEIGHT) / 2, 
				0, 
				0, 
				WIDTH, 
				HEIGHT);
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
		return m_left + m_parentLeft;
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
		return WIDTH;
	}

	@Override
	public int getHeight() 
	{
		return HEIGHT;
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

	private void UpdateTicks(float deltaSeconds)
	{
		m_backgroundTickCounter += deltaSeconds;
		if(m_backgroundTickCounter >= MAX_BACK_TICK_DUR)
		{
			m_backgroundTickCounter = 0;
			m_backgroundWidthModifier ++;
		}
		if(m_backgroundWidthModifier >= MAX_BACKGROUND_MODIFIER)
		{
			m_backgroundWidthModifier = 0;
			m_backgroundWidthModifier ++;
		}
		if(m_backgroundTopModifier >= MAX_BACKGROUND_MODIFIER)
		{
			m_backgroundTopModifier = 0;
			m_backgroundTopModifier++;
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
