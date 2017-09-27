package bruce.roflcraft.gui.GUIComponent.Button;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import bruce.roflcraft.gui.GUIComponent.IGUIComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

/**
 * This class represents the base object for a GUI button that uses the 
 * GUI component system. As such
 * @author Lorrtath
 *
 */
public abstract class GUIButtonComponent extends Gui implements IGUIComponent
{
	/**
	 * was the mouse over the button last tick
	 */
	private boolean m_mouseIsOver;
	
	/**
	 * Is the mouse last checked as being down
	 */
	private boolean m_mouseIsDown;
	
	/**
	 * The last stored mouse X position
	 */
	private int m_mouseX;
	
	/**
	 * The last stored mouse Y position
	 */
	private int m_mouseY;
	
	/**
	 * Collection of registered button listeners
	 */
	private List<IGUIButtonComponentListener> m_listeners;
	
	/**
	 * Static flag to prevent multiple button presses
	 */
	private static boolean BUTTON_ALREADY_ACTIVE = false;
	
	@Override
	public void init(int parentLeft, int parentTop)
	{
		m_listeners = new ArrayList<IGUIButtonComponentListener>();
	}

	@Override
	public void drawComponent(Minecraft mc, int mouseX, int mouseY)
	{
		boolean mouseIsOver = isMouseOver(mouseX, mouseY);
		if(mouseIsOver)
		{
			processesMouseOver(mouseX, mouseY);
		}
		else if (m_mouseIsOver)
		{
			m_mouseIsOver = false;
			onMouseLeave(mouseX, mouseY);
		}
		else
		{
			if(m_mouseIsDown && !Mouse.isButtonDown(0))
			{
				onButtonReleased(mouseX, mouseY);
			}
		}
	}

	@Override
	public abstract int getActualTop();

	@Override
	public abstract void setTop(int top);
	
	@Override
	public abstract int getActualLeft();

	@Override
	public abstract void setLeft(int left);
	
	@Override
	public abstract int getWidth();

	@Override
	public abstract int getHeight();

	@Override
	public abstract boolean getVisibility();

	@Override
	public abstract void setVisibility(boolean visability);

	/**
	 * Registers a Button component listener
	 * @param listener The listener being registered
	 */
	public void registerListener(IGUIButtonComponentListener listener)
	{
		m_listeners.add(listener);
	}
	
	/**
	 * Gets whether the mouse is over the button
	 */
	public boolean getIsMouseOverButton()
	{
		return m_mouseIsOver;
	}
	
	/**
	 * Gets whether the button is pressed
	 * @return true if the button is pressed
	 */
	public boolean getIsMousePressed()
	{
		return m_mouseIsDown;
	}
	
	/**
	 * Internal event dispatcher for when the mouse enters over the button
	 * @param mouseX The mouse X position
	 * @param mouseY The mouse Y position
	 */
	protected void onMouseEnter(int mouseX, int mouseY)
	{
		for (IGUIButtonComponentListener iguiButtonComponentListener : m_listeners) 
		{
			iguiButtonComponentListener.onMouseEnter(this, mouseX, mouseY);
		}
	}
	
	/**
	 * Internal event dispatcher for when the mouse is no longer over 
	 * the button
	 * @param mouseX The mouse X position
	 * @param mouseY The mouse Y position
	 */
	protected void onMouseLeave(int mouseX, int mouseY)
	{
		for (IGUIButtonComponentListener iguiButtonComponentListener : m_listeners) 
		{
			iguiButtonComponentListener.onMouseLeave(this, mouseX, mouseY);
		}
	}
	
	/**
	 * Internal event dispatcher for when the mouse moves while over the 
	 * button.
	 * @param mouseX The button X position
	 * @param mouseY The button Y position
	 */
	protected void onMouseMove(int mouseX, int mouseY)
	{
		for (IGUIButtonComponentListener iguiButtonComponentListener : m_listeners) 
		{
			iguiButtonComponentListener.onMouseMoveOver(this, mouseX, mouseY);
		}
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
	
	/**
	 * Checks to see if the mouse is over the button
	 * @param mouseX Mouse X position
	 * @param mouseY Mouse Y position
	 * @return True if the mouse is over the 
	 */
	private boolean isMouseOver(int mouseX, int mouseY)
	{
		return mouseX >= getActualLeft() && mouseX <= getActualLeft() + getWidth() && mouseY >= getActualTop() && mouseY <= getActualTop() + getHeight();
	}
	
	/**
	 * processes and calls events related to interaction of the mouse
	 * and the button
	 */
	private void processesMouseOver(int mouseX, int mouseY)
	{
		if(!m_mouseIsOver)
		{
			m_mouseIsOver = true;
			onMouseEnter(mouseX, mouseY);
		}
		if(mouseX != m_mouseX || mouseY != m_mouseY)
		{
			m_mouseX = mouseX;
			m_mouseY = mouseY;
			onMouseMove(mouseX, mouseY);
		}
		boolean mouseIsDown = Mouse.isButtonDown(0);
		if(mouseIsDown && !m_mouseIsDown && !BUTTON_ALREADY_ACTIVE)
		{
			BUTTON_ALREADY_ACTIVE = true;
			m_mouseIsDown = mouseIsDown;
			onButtonPressed(mouseX, mouseY);
		}
		else if (!mouseIsDown && m_mouseIsDown)
		{
			BUTTON_ALREADY_ACTIVE = false;
			m_mouseIsDown = mouseIsDown;
			onButtonReleased(mouseX, mouseY);
		}
	}
}