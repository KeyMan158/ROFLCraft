package bruce.roflcraft.client.gui.component;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

/**
 * This class is the root GUI component container that provides screen level
 * control.
 * The way that this behaves is similar to the GUIComponenManager class but
 * cannot be the child to another. All user interface screens should extend
 * this class!
 * @author Lorrtath
 */
public class GUIComponentScreen extends GuiScreen implements IGUIComponent
{
	private List<IGUIComponent> m_components;
	private boolean m_visability;
	private List<String> m_toolTipText;
	private boolean m_drawToolTip;
	
	public GUIComponentScreen()
	{
		super();
		m_components = new ArrayList<IGUIComponent>();
		m_visability = true;
	}
	
	/**
	 * Tells the GUI screen to draw a tool tip. Call this method 
	 * and not drawHoveringText from a component to ensure that
	 * the tool tip is at the front of the screen
	 * @param toolTipText
	 */
	public void setToolTip(List<String> toolTipText)
	{
		m_toolTipText = toolTipText;
		m_drawToolTip = true;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		drawComponent(mc, mouseX, mouseY, partialTicks);
		if (m_drawToolTip)
		{
			drawHoveringText(m_toolTipText, mouseX + 8, mouseY);
			m_drawToolTip = false;
		}
	}
	
	@Override
	public void initGui()
	{
		init(0, 0);
	}
	
	/**
	 * Registers a component as the child of this component
	 * @param component
	 */
	public void registerComponent(IGUIComponent component)
	{
		m_components.add(component);
		component.registerParent(this);
		component.registerRoot(this);
	}

	@Override
	public void init(int parentLeft, int parentTop) 
	{
		for(int i = 0; i < m_components.size(); i++)
		{
			m_components.get(i).init(0, 0);
		}
	}

	@Override
	public void drawComponent(Minecraft mc, int mouseX, int mouseY, float deltaSeconds) 
	{
		if(!m_visability)
		{
			return;
		}
		for(int i = 0; i < m_components.size(); i++)
		{
			m_components.get(i).drawComponent(mc, mouseX, mouseY, deltaSeconds);
		}
	}

	@Override
	public int getActualTop() 
	{
		return 0;
	}

	@Override
	public int getTop() 
	{
		return 0;
	}

	@Override
	public void setTop(int top) 
	{
		// Do nothing
	}

	@Override
	public int getActualLeft() 
	{
		return 0;
	}

	@Override
	public int getLeft() 
	{
		return 0;
	}

	@Override
	public void setLeft(int left) 
	{
		// Do nothing
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
		return false;
	}

	@Override
	public void setVisibility(boolean visability) 
	{
		// Do nothing		
	}

	@Override
	public void registerParent(IGUIComponent parent) 
	{
		// Do nothing
	}

	@Override
	public void registerRoot(GUIComponentScreen root) 
	{
		// Do nothing
	}

	@Override
	public IGUIComponent getParent() 
	{
		return this;
	}

	@Override
	public GUIComponentScreen getRoot() 
	{
		return this;
	}
}
