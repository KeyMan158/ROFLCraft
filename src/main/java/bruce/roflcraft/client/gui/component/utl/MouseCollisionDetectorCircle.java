package bruce.roflcraft.client.gui.component.utl;

import bruce.roflcraft.client.gui.component.GUIComponentBase;

/**
 * Collision detection based on a radius around 
 * the components [0,0] position on the screen
 * @author Lorrtath
 *
 */
public class MouseCollisionDetectorCircle extends MouseCollisionDetector
{
	private float m_radius;
	
	public MouseCollisionDetectorCircle(GUIComponentBase collisionComponent) 
	{
		super(collisionComponent);
		m_radius = 16f; // Diameter / square width of 32
	}

	/**
	 * Gets the radius this detector uses for collision checks
	 * @return The hit radius used
	 */
	public float getHitRadius()
	{
		return m_radius;
	}

	/**
	 * Sets the radius this detector uses for collision checks
	 * @param radius The hit radius to use
	 */
	public void setHitRadius(float radius)
	{
		m_radius = radius;
	}
	
	@Override
	public boolean mouseCollides(int mouseX, int mouseY) 
	{
		double distance = Math.sqrt(0.5 * (Math.pow(mouseX - getComponent().getActualLeft(), 2) + Math.pow(mouseY - getComponent().getActualTop(), 2)));
		if(m_radius < distance)
		{
			return false;
		}
		return true;
	}

	@Override
	public MouseCollisionMode getDetectionMode() 
	{
		return MouseCollisionMode.MOUSE_COLLISION_CIRCLE;
	}
}
