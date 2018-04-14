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
public class AtributeDialComponent extends Gui implements IGUIComponent, IAttributeControlledComponent
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
	
	private float m_angle;
	private float m_targetAngle;
	private boolean m_isRotating;
	private static final float m_rotationSpeed = 60;
	
	@Override
	public void init(int parentLeft, int parentTop) 
	{
		m_parentLeft = parentLeft;
		m_parentTop = parentTop;
		m_isVisable = true;
		m_angle = 0;
	}

	@Override
	public void drawComponent(Minecraft mc, int mouseX, int mouseY, float deltaSeconds) 
	{
		if(!m_isVisable)
		{
			return;
		}
		
		updateRotation(deltaSeconds);
		
		GL11.glPushMatrix();
		GL11.glTranslatef((m_root.width)/2, (m_root.height)/2, 0);
		GL11.glRotated(m_angle, 0, 0, 1);
		
		updateTicks(deltaSeconds);
		mc.getTextureManager().bindTexture(DIAL_RESOURCE);
        GL11.glEnable(GL11.GL_BLEND);
		drawTexturedModalRect(BACKGROUND_WIDTH / -2, 
				BACKGROUND_HEIGHT / -2, 
				128 + (m_backgroundWidthModifier * BACKGROUND_WIDTH), 
				128 + (m_backgroundTopModifier * BACKGROUND_HEIGHT), 
				BACKGROUND_WIDTH, 
				BACKGROUND_HEIGHT);
		drawTexturedModalRect(WIDTH / -2, 
				HEIGHT / -2, 
				0, 
				0, 
				WIDTH, 
				HEIGHT);
		GL11.glPopMatrix();
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

	private void updateTicks(float deltaSeconds)
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

	@Override
	public void changeAttribute(AttributeIndex index, IAttributeBasedComponent sender) 
	{
		switch (index) 
		{
		case AT_BODY:
			m_targetAngle = 0;
			break;
		case AT_MIND:
			m_targetAngle = 120;
			break;
		case AT_SOUL:
			m_targetAngle = 240;
			break;
		default:
			return;
		}
		m_isRotating = true;
	}
	
	/**
	 * Updates the angle if the dial is rotating
	 * @param deltaSeconds The time step in partial ticks
	 */
	private void updateRotation(float deltaSeconds)
	{
		float step = deltaSeconds * m_rotationSpeed;
		if(Math.abs(step) >= Math.abs(m_targetAngle - m_angle))
		{
			m_angle = m_targetAngle;
			m_isRotating = false;
		}
		else
		{
			float directon = Math.abs(m_targetAngle - m_angle) / (m_targetAngle - m_angle);
			m_angle += directon * step;
		}
	}
}
