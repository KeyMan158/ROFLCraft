package bruce.roflcraft.client.gui.component.utl;

import bruce.roflcraft.client.gui.component.GUIComponentBase;

/**
 * Base abstract class for performing mouse collision
 * detection calculations
 * @author Lorrtath
 *
 */
public abstract class MouseCollisionDetector 
{
	private GUIComponentBase m_component;
	
	public MouseCollisionDetector(GUIComponentBase collisionComponent)
	{
		m_component = collisionComponent;
	}
	
	/**
	 * Gets the component that this detector is based on
	 * @return The component this detector is attached to
	 */
	protected GUIComponentBase getComponent()
	{
		return m_component;
	}
	
	/**
	 * Checks to see if a mouse position lies over the component
	 * @param mouseX The mouse X position
	 * @param mouseY the mouse Y position
	 * @return True if this detector believes the mouse colliding
	 */
	public abstract boolean mouseCollides(int mouseX, int mouseY);
	
	/**
	 * Gets the type of collision detection this is 
	 * @return
	 */
	public abstract MouseCollisionMode getDetectionMode();
}
