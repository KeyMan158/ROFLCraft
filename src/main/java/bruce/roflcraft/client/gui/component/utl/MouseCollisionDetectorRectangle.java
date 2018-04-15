package bruce.roflcraft.client.gui.component.utl;


import bruce.roflcraft.client.gui.component.GUIComponentBase;

/**
 * This mouse collision detector class performs collision detection
 * on a rotated rectangular box area
 * @author Lorrtath
 *
 */
public class MouseCollisionDetectorRectangle extends MouseCollisionDetector
{
	int m_collisionTextureLayer;
	boolean m_allwaysCalcVertex;
	CollisionVector m_p1; // Top Left corner
	CollisionVector m_p2; // Top Right corner
	CollisionVector m_p3; // Bottom right corner 
	CollisionVector m_p4; // Bottom left corner

	/**
	 * Private inner class for acting as a float based
	 * X & Y controlled point or vector in space
	 * rotated collision box
	 * @author Lorrtath
	 *
	 */
	private class CollisionVector
	{
		public float X;
		public float Y;
	}
	
	public MouseCollisionDetectorRectangle(GUIComponentBase collisionComponent) 
	{
		super(collisionComponent);
		m_collisionTextureLayer = 0;
		m_allwaysCalcVertex = true;
		m_p1 = new CollisionVector();
		m_p2 = new CollisionVector();
		m_p3 = new CollisionVector();
		m_p4 = new CollisionVector();
	}

	/**
	 * Gets the index of the texture layer used for collisions
	 * @return The index of the texture layer
	 */
	public int getCollisionTextureIndex()
	{
		return m_collisionTextureLayer;
	}
	
	/**
	 * Sets the texture layer used for collisions
	 * @param index The index of the texture layer
	 */
	public void setCollisionLayerIndex(int index)
	{
		if(index < 0 || index >= getComponent().getTextureLayerCount())
		{
			return;
		}
		m_collisionTextureLayer = index;
	}
	
	/**
	 * Gets whether the detector will always perform a calculation of the 
	 * collision boxes bounding vertexes. 
	 * 
	 * By default this is set to false to reduce calculation overheads, but 
	 * should be done if there has been a change in the component or layers
	 * position
	 * @return true if calculating vertex positions on every update
	 */
	public boolean getAlwaysCalcVertex()
	{
		return m_allwaysCalcVertex;
	}
	
	public void setAlwaysCalcVertex(boolean alwaysCalc)
	{
		m_allwaysCalcVertex = alwaysCalc;
	}
	
	public void calculateCollsionVertexes()
	{
		GUITextureLayer layer = getComponent().getTextureLayer(m_collisionTextureLayer);
		float rotation = getComponent().gerRotation();
		m_p1.X = (float) ((layer.getLeft() * Math.cos(rotation) - (layer.getTop() * Math.cos(rotation)))) + getComponent().getActualLeft();
		m_p1.Y = (float) ((layer.getLeft() * Math.sin(rotation) + (layer.getTop() * Math.cos(rotation)))) + getComponent().getActualLeft();

		m_p2.X = (float) (((layer.getLeft() + layer.Width) * Math.cos(rotation) - (layer.getTop() * Math.cos(rotation)))) + getComponent().getActualLeft();
		m_p2.Y = (float) (((layer.getLeft() + layer.Width) * Math.sin(rotation) + (layer.getTop() * Math.cos(rotation)))) + getComponent().getActualLeft();
		
		m_p3.X= (float) (((layer.getLeft() + layer.Width) * Math.cos(rotation) - ((layer.getTop() + layer.Width )* Math.cos(rotation)))) + getComponent().getActualLeft();
		m_p3.Y = (float) (((layer.getLeft() + layer.Width) * Math.sin(rotation) + ((layer.getTop() + layer.Width ) * Math.cos(rotation)))) + getComponent().getActualLeft();
		
		m_p4.X = (float) ((layer.getLeft() * Math.cos(rotation) - ((layer.getTop() + layer.Width )* Math.cos(rotation)))) + getComponent().getActualLeft();
		m_p4.Y = (float) ((layer.getLeft() * Math.sin(rotation) + ((layer.getTop() + layer.Width ) * Math.cos(rotation)))) + getComponent().getActualLeft();
	}
	
	@Override
	public boolean mouseCollides(int mouseX, int mouseY) 
	{
		if (m_allwaysCalcVertex)
		{
			calculateCollsionVertexes();
		}
		
		CollisionVector mousePoint = new CollisionVector();
		mousePoint.X = mouseX;
		mousePoint.Y = mouseY;
		
		boolean result = pointIsInTriangle(m_p1, m_p2, m_p4, mousePoint);
		if(!result)
		{
			return false;
		}
		result = pointIsInTriangle(m_p3, m_p2, m_p4 ,mousePoint);
		if(!result)
		{
			return false;
		}
		return true;
	}

	@Override
	public MouseCollisionMode getDetectionMode() 
	{
		return MouseCollisionMode.MOUSE_COLLISION_RECTANGLE;
	}
	
	private boolean pointIsInTriangle(CollisionVector vertexA, CollisionVector vertexB, CollisionVector vertexC, CollisionVector pointP)
	{
		CollisionVector v0 = new CollisionVector();
		v0.X = vertexC.X - vertexA.X;
		v0.Y = vertexC.Y - vertexA.Y;
		
		CollisionVector v1 = new CollisionVector();
		v1.X = vertexB.X - vertexA.X;
		v1.Y = vertexB.Y - vertexA.Y;

		CollisionVector v2 = new CollisionVector();
		v2.X = pointP.X - vertexA.X;
		v2.Y = pointP.Y - vertexA.Y;
		
		float u = ((dotProduct(v1,v1) * dotProduct(v2, v0)) - (dotProduct(v1, v0) * dotProduct(v2, v1))) / ((dotProduct(v0, v0) * dotProduct(v1, v1)) - (dotProduct(v0, v1) * dotProduct(v1, v0)));
		float v = ((dotProduct(v0,v0) * dotProduct(v2, v1)) - (dotProduct(v0, v1) * dotProduct(v2, v0))) / ((dotProduct(v0, v0) * dotProduct(v1, v1)) - (dotProduct(v0, v1) * dotProduct(v1, v0)));
		
		if(u < 0 || v < 0  || u > 1 || v > 1 || u + v > 1)
		{
			return false;
		}
		
		return true;
	}
	
	private float dotProduct(CollisionVector vector1, CollisionVector vector2)
	{
		return (vector1.X * vector2.X) + (vector1.Y + vector2.Y);
	}
	
}
