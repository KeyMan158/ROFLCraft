package bruce.roflcraft.gui.GUIComponent;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

/**
 * This class is the root GUI component container that provides screen level
 * control.
 * The way that this behaves is similar to the GUIComponenManager class but
 * cannot be parented to another. All user interface screens should extend
 * this class!
 * @author Lorrtath
 */
public class GUIComponentScreen extends GuiScreen 
{
	private List<IGUIComponent> m_components;
	private boolean m_visability;
	
	public GUIComponentScreen()
	{
		super();
		m_components = new ArrayList<IGUIComponent>();
		m_visability = true;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		if(!m_visability)
		{
			return;
		}
		for(int i = 0; i < m_components.size(); i++)
		{
			m_components.get(i).drawComponent(mc, mouseX, mouseY, partialTicks);
		}
	}
	
	@Override
	public void initGui()
	{
		for(int i = 0; i < m_components.size(); i++)
		{
			m_components.get(i).init(0, 0);
		}
	}
	
	/**
	 * Registers a component as the child of this component
	 * @param component
	 */
	public void registerComponent(IGUIComponent component)
	{
		m_components.add(component);
	}
}
