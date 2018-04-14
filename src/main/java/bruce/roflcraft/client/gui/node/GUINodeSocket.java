package bruce.roflcraft.client.gui.node;

/**
 * This class represents the connection point
 * of a node for connecting to another node
 * @author Lorrtath
 *
 */
public class GUINodeSocket 
{
	private GUINodeSide m_side;
	private GUINodeComponent m_attachedComponent;
	
	/**
	 * New GUINodeSocket
	 * @param side The side of this socket
	 */
	public GUINodeSocket(GUINodeSide side)
	{
		m_side = side;
	}
	
	/**
	 * Gets the side of the owning node that this uses
	 * @return The side of the socket
	 */
	GUINodeSide getSide()
	{
		return m_side;
	}
	
	/**
	 * Sets the node attached to this socket
	 * @param node The node to attach
	 */
	public void setAttachedNode(GUINodeComponent node)
	{
		m_attachedComponent = node;
	}
	
	/**
	 * Gets the node component that is attached to this socket
	 * @return
	 */
	public GUINodeComponent getAttachedNode()
	{
		return m_attachedComponent;
	}
	
	/**
	 * Checks to see if this socket is free or if there 
	 * is an attaching socket
	 * @return true if this socket is free
	 */
	public boolean isSocketFree()
	{
		if(m_attachedComponent == null)
		{
			return false;
		}
		return true;
	}
}
