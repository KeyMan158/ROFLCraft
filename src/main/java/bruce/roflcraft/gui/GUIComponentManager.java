package bruce.roflcraft.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;

/**
 * Class for managing the drawing and rendering of a 
 * GUI component
 * @author Lorrtath
 *
 */
public class GUIComponentManager 
{
	private List<IGUIComponent> m_components;
	
	public GUIComponentManager()
	{
		m_components = new ArrayList<IGUIComponent>();
	}
	
	/**
	 * Registers a GUIComponent with the manager
	 */
	public void drawComponents(Minecraft mc, int mouseX, int mouseY)
	{
		for (IGUIComponent iguiComponent : m_components) 
		{
			if(iguiComponent.getVisibility())
			{
				iguiComponent.drawComponent(mc, mouseX, mouseY);
			}
		}
	}
	
	/**
	 * Gets a GUIComponent that has been registered
	 * @param index The index of the GUIComponent
	 * @return The GUIComponent
	 */
	public IGUIComponent getComponent(int index)
	{
		return m_components.get(index);
	}
	
	/**
	 * Gets a count of the registered GUIComponents
	 * @return The count
	 */
	public int count()
	{
		return m_components.size();
	}
	
	/**
	 * Sets the viability of the GUIComponent to true
	 * @param index The index of the GUIComponent
	 */
	public void show(int index)
	{
		m_components.get(index).setVisibility(true);
	}
	
	/**
	 * Sets the viability of the GUIComponent to false
	 * @param index The index of the GUIComponent
	 */
	public void hide(int index)
	{
		m_components.get(index).setVisibility(false);
	}
	
	/**
	 * Sets the viability of all the GUIComponent to true
	 */
	public void showAll()
	{
		for (IGUIComponent iguiComponent : m_components) 
		{
			iguiComponent.setVisibility(true);
		}
	}
	
	/**
	 * Sets the viability of all the GUIComponent to false
	 */
	public void hideAll()
	{
		for (IGUIComponent iguiComponent : m_components) 
		{
			iguiComponent.setVisibility(false);
		}
	}
	
	/**
	 * Registers a GUIComponent
	 * @param component The component to register
	 */
	public void register(IGUIComponent component)
	{
		m_components.add(component);
	}
	
	/**
	 * UnRegisters a GUIComponent
	 * @param index The index of the component
	 */
	public void unRegister(int index)
	{
		m_components.remove(index);
	}
	
	/**
	 * UnRegisters a GUIComponent
	 * @param component The GUI Component
	 */
	public void unRegister(IGUIComponent component)
	{
		m_components.remove(component);
	}
}
