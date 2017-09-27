package bruce.roflcraft.gui.GUIComponent;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;

/**
 * Class for managing the drawing and rendering of a 
 * GUI component
 * @author Lorrtath
 *
 */
public class GUIComponentManager  implements IGUIComponent
{
	private List<IGUIComponent> m_components;
	private int m_parentLeft;
	private int m_left;
	private int m_parentTop;
	private int m_top;
	private boolean m_visability;
	
	public GUIComponentManager()
	{
		m_components = new ArrayList<IGUIComponent>();
		m_visability = true;
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
		m_visability = true;
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
		m_visability = false;
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
	
	private void updateChildPositions()
	{
		for (IGUIComponent iguiComponent : m_components) 
		{
			iguiComponent.init(getActualLeft(), getActualTop());
		}
	}

	@Override
	public void init(int parentLeft, int parentTop) 
	{
		m_parentLeft = parentLeft;
		m_parentTop = parentTop;
		updateChildPositions();
	}

	@Override
	public void drawComponent(Minecraft mc, int mouseX, int mouseY) 
	{
		for (IGUIComponent iguiComponent : m_components) 
		{
			if(iguiComponent.getVisibility())
			{
				iguiComponent.drawComponent(mc, mouseX, mouseY);
			}
		}		
	}

	@Override
	public int getActualTop() 
	{
		return m_parentTop + m_top;
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
		return m_parentLeft + m_left;
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
		return m_visability;
	}

	@Override
	public void setVisibility(boolean visability) 
	{
		m_visability = visability;
		if(visability)
		{
			showAll();
		}
		else
		{
			hideAll();
		}
	}
}
