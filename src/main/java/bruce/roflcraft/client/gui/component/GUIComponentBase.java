package bruce.roflcraft.client.gui.component;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import bruce.roflcraft.client.gui.component.utl.GUITextureLayer;
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
	
	private List<GUITextureLayer> m_textures;
	
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
	 * Sets the components rotation
	 * @param rotation The components rotation
	 */
	public void setRotation(float rotation)
	{
		m_rotation = rotation;
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
