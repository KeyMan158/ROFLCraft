package bruce.roflcraft.client.gui.component;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import bruce.roflcraft.client.gui.component.utl.GUITextureLayer;
import bruce.roflcraft.client.gui.component.utl.MouseCollisionDetector;
import bruce.roflcraft.client.gui.component.utl.MouseCollisionDetectorCircle;
import bruce.roflcraft.client.gui.component.utl.MouseCollisionDetectorRectangle;
import bruce.roflcraft.client.gui.component.utl.MouseCollisionMode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

/**
 * This class is the base implementation of the IGUI Component and
 * provides default functionality in regards to 
 * @author Lorrtath
 *
 */
public class GUIComponentBase extends Gui implements IGUIComponent
{
	private int m_left;
	private int m_top;
	private float m_rotation;
	private boolean m_useScreenCenter;
	private boolean m_isVisable;
	private IGUIComponent m_parent;
	private GUIComponentScreen m_root;
	private MouseCollisionMode m_collisionMode;
	private MouseCollisionDetector m_mouseCollisionDetector;
	private boolean m_mouseIsOver;
	private List<GUITextureLayer> m_textures;
	private boolean m_useToolTip;
	private List<String> m_toolTipText;
	
	public GUIComponentBase()
	{
		m_left = 0;
		m_top = 0;
		m_rotation = 0;
		m_isVisable = true;
		m_useScreenCenter = false;
		m_parent = null;
		m_root = null;
		m_textures = new ArrayList<GUITextureLayer>();
		m_collisionMode = MouseCollisionMode.MOUSE_COLLISION_NONE;
		m_mouseCollisionDetector = null;
		m_mouseIsOver = false;
		m_useToolTip = false;
		m_toolTipText = new ArrayList<String>();
	}
	
	/**
	 * Adds a GUI texture resource to the bottom of the draw list
	 * @param Resource The resource to use
	 */
	public void AddResource(GUITextureLayer Resource)
	{
		m_textures.add(Resource);
	}
	
	/**
	 * Gets a count of the texture layers for this component
	 * @return The number of texture layers
	 */
	public int getTextureLayerCount()
	{
		return m_textures.size();
	}
	
	/**
	 * Gets a texture layer
	 * @param index The index of the texture layer
	 * @return The texture layer
	 */
	public GUITextureLayer getTextureLayer(int index)
	{
		if(index < 0 || index >= m_textures.size())
		{
			return null;
		}
		return m_textures.get(index);
	}
	
	/**
	 * Removes a given texture layer
	 * @param index The index of the layer to remove
	 */
	public void removeTextureLayer(int index)
	{
		if(index < 0 || index >= m_textures.size())
		{
			return;
		}
		m_textures.remove(index);
	}
	
	/**
	 * Sets the components rotation
	 * @param rotation The components rotation
	 */
	public void setRotation(float rotation)
	{
		m_rotation = rotation;
	}
	
	/**
	 * Sets whether to use the tool tip
	 * @param useToolTip Whether to use the tool tip
	 */
	public void setUseToolTip(boolean useToolTip)
	{
		m_useToolTip = useToolTip;
	}
	
	/**
	 * Sets whether to use the tool tip
	 * @return Whether to use the tool tip
	 */
	public boolean getUseToolTip()
	{
		return m_useToolTip;
	}

	/**
	 * Sets the tool tip text 
	 * @param text The tool tip text 
	 */
	public void setToolTipText(List<String> text)
	{
		m_toolTipText = text;
	}
	
	/**
	 * Gets the tool tip text
	 * @return The tool tip text
	 */
	public List<String> getToolTipText()
	{
		return m_toolTipText;
	}
	
	/**
	 * Gets the check value for if the mouse is considered over the
	 * component
	 * @return True if the mouse is considered to be over the component 
	 */
	public boolean getMouseIsOver()
	{
		return m_mouseIsOver;
	}
	
	/**
	 * Sets the mouse collision mode that this component uses
	 * @param mode The collision mode to use
	 */
	public void setCollisionMode(MouseCollisionMode mode)
	{
		m_collisionMode = mode;
		switch (m_collisionMode)
		{
		case MOUSE_COLLISION_CIRCLE:
			m_mouseCollisionDetector = new MouseCollisionDetectorCircle(this);
			break;
		case MOUSE_COLLISION_NONE:
			// do nothing ...
			break;
		case MOUSE_COLLISION_RECTANGLE:
			m_mouseCollisionDetector = new MouseCollisionDetectorRectangle(this);
			break;
		default:
			break;
		}
	}
	
	/**
	 * Gets the active mouse collision detector
	 * @return The active mouse collision detector
	 */
	public MouseCollisionDetector getCollisionDetector()
	{
		return m_mouseCollisionDetector;
	}
	
	/**
	 * Gets the components rotation
	 * @return The components rotation
	 */
	public float gerRotation()
	{
		return m_rotation;
	}
	
	public void setUseCenter(boolean useScreenCenter)
	{
		m_useScreenCenter = useScreenCenter;
	}
	
	public boolean getUseCenter()
	{
		return m_useScreenCenter;
	}
	
	/**
	 * Processes the mouse collision stuff
	 * @param mouseX The X position of the mouse
	 * @param mouseY The Y position of the mouse
	 */
	protected void processMouseCollision(int mouseX, int mouseY)
	{
		boolean result = m_mouseCollisionDetector.mouseCollides(mouseX, mouseY);
		if(!m_mouseIsOver && result)
		{
			handle_MouseEnter();
		}
		if (m_mouseIsOver && !result)
		{
			handle_MouseExit();
		}
		m_mouseIsOver = result;
		if(m_mouseIsOver && m_useToolTip)
		{
			m_root.getRoot().setToolTip(m_toolTipText);
		}
	}
	
	/**
	 * Fired when the mouse enters the component
	 */
	protected void handle_MouseEnter()
	{
		// Override this for child extends of this class
	}
	
	/**
	 * Fired when the mouse exits the component
	 */
	protected void handle_MouseExit()
	{
		// Override this for child extends of this class
	}
	
	@Override
	public void init(int parentLeft, int parentTop) 
	{
		
	}

	@Override
	public void drawComponent(Minecraft mc, int mouseX, int mouseY, float deltaSeconds) 
	{
		if(!m_isVisable)
		{
			return;
		}
		GL11.glPushMatrix();
		GL11.glTranslatef(getActualLeft(), getActualTop(), 0);
		GL11.glRotated(m_rotation, 0, 0, 1);
		for(int i = 0; i < m_textures.size(); i++)
		{
			GUITextureLayer resource = m_textures.get(i);
			mc.getTextureManager().bindTexture(resource.TextureResource);
			drawTexturedModalRect(resource.getLeft(), resource.getTop(), resource.U, resource.V, resource.Width, resource.Height);
		}
		GL11.glPopMatrix();
		if(m_collisionMode != MouseCollisionMode.MOUSE_COLLISION_NONE)
		{
			processMouseCollision(mouseX, mouseY);
		}
	}

	@Override
	public int getActualTop() 
	{
		if (m_useScreenCenter)
		{
			return (m_root.height / 2) + m_top;
		}
		return m_parent.getActualTop() + m_top;
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
		if (m_useScreenCenter)
		{
			return (m_root.width / 2) + m_left;
		}
		return m_parent.getActualLeft() + m_left;
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
		return 0;
	}

	@Override
	public int getHeight() 
	{
		return 0;
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

	@Override
	public void registerParent(IGUIComponent parent) 
	{
		m_parent = parent;
		m_root = m_parent.getRoot();
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
