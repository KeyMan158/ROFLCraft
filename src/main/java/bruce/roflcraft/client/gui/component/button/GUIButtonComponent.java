package bruce.roflcraft.client.gui.component.button;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import bruce.roflcraft.client.gui.component.GUIComponentBase;

/**
 * This class represents the base object for a GUI button that uses the 
 * GUI component system. As such
 * @author Lorrtath
 *
 */
public abstract class GUIButtonComponent extends GUIComponentBase
{
	private List<IGUIButtonComponentListener> m_listeners;
	private boolean m_isMouseDown;
	
	public GUIButtonComponent()
	{
		m_listeners = new ArrayList<IGUIButtonComponentListener>();
	}
	
	/**
	 * Registers a Button component listener
	 * @param listener The listener being registered
	 */
	public void registerListener(IGUIButtonComponentListener listener)
	{
		m_listeners.add(listener);
	}
	
	/**
	 * Gets whether the button is pressed
	 * @return true if the button is pressed
	 */
	public boolean getIsMousePressed()
	{
		return m_isMouseDown;
	}
	
	/**
	 * Internal event dispatcher for when the left mouse is clicked down while 
	 * over the button
	 * @param mouseX The mouse X position
	 * @param mouseY The mouse Y position
	 */
	protected void onButtonPressed(int mouseX, int mouseY)
	{
		for (IGUIButtonComponentListener iguiButtonComponentListener : m_listeners) 
		{
			iguiButtonComponentListener.onButtonPressed(this, mouseX, mouseY);
		}
	}

	/**
	 * Internal event dispatcher for when the left mouse is no longer clicked down 
	 * while it is hovered over.
	 * @param mouseX The mouse X position
	 * @param mouseY The mouse Y position
	 */
	protected void onButtonReleased(int mouseX, int mouseY)
	{
		for (IGUIButtonComponentListener iguiButtonComponentListener : m_listeners) 
		{
			iguiButtonComponentListener.onButtonReleased(this, mouseX, mouseY);
		}
	}
	
	@Override
	protected void processMouseCollision(int mouseX, int mouseY)
	{
		super.processMouseCollision(mouseX, mouseY);
		processesMouseDown(mouseX, mouseY);
	}
	
	@Override
	protected void handle_MouseEnter()
	{
		super.handle_MouseEnter();
		for (IGUIButtonComponentListener iguiButtonComponentListener : m_listeners) 
		{
			iguiButtonComponentListener.onMouseEnter(this, 0, 0);
		}
	}
	
	@Override
	protected void handle_MouseExit()
	{
		super.handle_MouseExit();
		for (IGUIButtonComponentListener iguiButtonComponentListener : m_listeners) 
		{
			iguiButtonComponentListener.onMouseLeave(this, 0, 0);
		}	
	}
	
	/**
	 * processes and calls events related to interaction of the mouse
	 * and the button
	 */
	private void processesMouseDown(int mouseX, int mouseY)
	{
		boolean mouseIsDown = Mouse.isButtonDown(0);
		if(mouseIsDown && !m_isMouseDown)
		{
			m_isMouseDown = mouseIsDown;
			onButtonPressed(mouseX, mouseY);
		}
		else if (!mouseIsDown && m_isMouseDown)
		{
			m_isMouseDown = mouseIsDown;
			onButtonReleased(mouseX, mouseY);
		}
	}
}