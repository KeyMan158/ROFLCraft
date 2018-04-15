package bruce.roflcraft.client.gui.node;

import java.util.ArrayList;
import java.util.List;

import bruce.roflcraft.client.gui.component.button.GUIButtonComponent;
import bruce.roflcraft.client.gui.component.utl.MouseCollisionMode;

/**
 * A GUINodeComponent is a special type of component
 * that has four "sockets" that other node components
 * can be attached to. This is primarily designed for 
 * the main character screen skills, etc. 
 * 
 * Its important to note that the connected nodes are 
 * not always visible or even accessible based on an
 * internal switch that can be linked to a value
 * @author Lorrtath
 *
 */
public class GUINodeComponent extends GUIButtonComponent
{
	private List<GUINodeSocket> m_sockets;
	private GUINodeSide m_positionSide;
	
	public GUINodeComponent()
	{
		super();
		setUseToolTip(true);
		setCollisionMode(MouseCollisionMode.MOUSE_COLLISION_CIRCLE);
		m_sockets = new ArrayList<GUINodeSocket>();
		for(int i = 0; i < GUINodeSide.values().length; i++)
		{
			m_sockets.add(new GUINodeSocket(GUINodeSide.values()[i]));
		}
	}
	
	/**
	 * Checks to see if a socket is free
	 * @param side The side of the socket
	 * @return True if the socket is free
	 */
	public boolean isSocketFree(GUINodeSide side)
	{
		return m_sockets.get(side.ordinal()).isSocketFree();
	}
	
	/**
	 * Sets the side that this node is positioned by 
	 * @param side
	 */
	public void setPositionSocket(GUINodeSide side)
	{
		m_positionSide = side;
	}
	
	/** 
	 * Gets the node side that controls the positioning of
	 * this node
	 * @return
	 */
	public GUINodeSide getPositionSide()
	{
		return m_positionSide;
	}
}
